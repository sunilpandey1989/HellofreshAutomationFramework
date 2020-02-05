package com.hellofresh.restapi.steps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import com.hellofresh.managers.FileReaderManager;
import com.hellofresh.ui.steps.Hooks;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAPISteps {

	public static String apiEndPointUri;
	public static String testName;
	public static String CONTENT_TYPE;
	public static String Authorization;
	public static String STATUS_CODE;
	public static String PARAM;
	public static String FILE_PATH;
	public static String REQUESTBODY;
	public static String RESPONSEBODY;
	public static Response response;

	public RestAPISteps() {
	}

	@Given("^I want to set URL as \"([^\"]*)\"$")
	public void setAPIEndpointURL(String URL) {
		String apiHostName = FileReaderManager.getInstance().getConfigReader().getPropertyValue("apiurl");
		apiEndPointUri = String.format("%s%s", apiHostName, URL);
		Hooks.reportLog("Pass", "API Hostname URL is :: " + apiEndPointUri);
		System.out.println("API Hostname URL is :: " + apiEndPointUri);
	}

	@When("^I set header content type as \"([^\"]*)\"$")
	public void setHeader(String contentType) {
		if (contentType != null && !contentType.isEmpty()) {
			CONTENT_TYPE = contentType;
			Hooks.reportLog("Pass", "Content type is :: " + CONTENT_TYPE);
			System.out.println("Content type is :: " + CONTENT_TYPE);
		} else {
			Hooks.reportLog("Pass", "Content type cannot be null or empty!");
			System.out.println("Content type cannot be null or empty!");
		}
	}

	@When("^I set param roomid as \"([^\"]*)\"$")
	public void setBookingID(String param) {
		if (param != null && !param.isEmpty()) {
			PARAM = param;
			Hooks.reportLog("Pass", "Parameter is :: " + param);
			System.out.println("Parameter is :: " + param);
		} else {
			Hooks.reportLog("Info", "Parameter cannot be null or empty!");
			System.out.println("Parameter cannot be null or empty!");
		}
	}

	@And("I hit the API with requestbody \"([^\"]*)\" having request method is \"([^\"]*)\" and parameter is \"([^\"]*)\" and filetype \"([^\"]*)\"$")
	public void submitRequest(String requestBodyPath, String requestType, String param, String fileType)
			throws Throwable {
		sendRequest(requestBodyPath, requestType, param, fileType);

	}

	@And("^I try to verify the response value \"([^\"]*)\" is \"([^\"]*)\"$")
	public void verifyResponseValue(String responseKey, String expectedValue) throws Throwable {
		String actualValue = getValueFromKey(RESPONSEBODY, responseKey);
		compareResponseValues(expectedValue, actualValue);
	}

	@Then("^I try to verify the status code is \"([^\"]*)\"$")
	public void verifyStatusCode(String statusCode) {
		if (statusCode.equals(String.valueOf(STATUS_CODE))) {
			Hooks.reportLog("Pass", "Status Code is :: " + STATUS_CODE);
			Assert.assertEquals(STATUS_CODE, statusCode);
			System.out.println("Status Code is :: " + STATUS_CODE);
		} else {
			Hooks.reportLog("Fail", "Status Code " + statusCode + " is not as actal :: " + STATUS_CODE);
			System.out.println("Status Code " + statusCode + " is not as actal :: " + STATUS_CODE);
			Assert.fail("Status Code " + statusCode + " is not as actal :: " + STATUS_CODE);
		}
	}

	@Then("^I try to verify the number of bookings is \"([^\"]*)\"$")
	public void verifyNumberOfBookings(int expectedCount) throws Throwable {

		int actualCount = countNumberOfBookings(RESPONSEBODY);
		if (actualCount == expectedCount) {
			Hooks.reportLog("Pass", "Actual Count " + actualCount + " is same as expected Count " + expectedCount);
			Assert.assertEquals(actualCount, expectedCount);
			System.out.println("Actual Count " + actualCount + " is same as expected Count " + expectedCount);
		} else {
			Hooks.reportLog("Fail", "Actual Count " + actualCount + " is not same as expected Count " + expectedCount);
			System.out.println("Actual Count " + actualCount + " is not same as expected Count " + expectedCount);
			Assert.fail("Actual Count " + actualCount + " is not same as expected Count " + expectedCount);
		}
	}

	@Then("^I try to verify the number of booking is \"([^\"]*)\" for \"([^\"]*)\"$")
	public void verifyNumberOfBooking(int expectedCount, String key) throws Throwable {

		int actualCount = countOccurrenceOfKey(RESPONSEBODY, key);
		if (actualCount == expectedCount) {
			Hooks.reportLog("Pass", "Actual Count " + actualCount + " is same as expected Count " + expectedCount);
			Assert.assertEquals(actualCount, expectedCount);
			System.out.println("Actual Count " + actualCount + " is same as expected Count " + expectedCount);
		} else {
			Hooks.reportLog("Fail", "Actual Count " + actualCount + " is not same as expected Count " + expectedCount);
			System.out.println("Actual Count " + actualCount + " is not same as expected Count " + expectedCount);
			Assert.fail("Actual Count " + actualCount + " is not same as expected Count " + expectedCount);
		}
	}

	/*
	 * This method gets the value of Key from JSON RESPONSEBODY it takes
	 * arguments @resp which is RESPONSEBODY, @keyName is name of the key in the
	 * RESPONSEBODY
	 */

	public String getValueFromKey(String resp, String keyName) throws ParseException {

		String value = null;
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(resp);
		JSONArray tagsArray = (JSONArray) jsonObject.get("bookings");
		for (int i = 0; i < tagsArray.size(); i++) {
			JSONObject jsonObjecttr1 = (JSONObject) jsonParser.parse(tagsArray.get(i).toString());
			String key = (String) jsonObjecttr1.get("key");
			if (key.equals(keyName)) {
				value = (String) jsonObjecttr1.get("value");
				Hooks.reportLog("Info", "Value for the key " + key + " is " + value);
				System.out.println("Value is " + value);
			}
		}
		return value;
	}

	/*
	 * This method returns the booking count present based on RESPONSEBODY it
	 * takes arguments @resp which is RESPONSEBODY
	 */
	public int countNumberOfBookings(String resp) throws ParseException {

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(resp);
		JSONArray tagsArray = (JSONArray) jsonObject.get("bookings");
		Hooks.reportLog("Info", "Number of bookings " + tagsArray);
		return tagsArray.size();
	}

	/*
	 * This method return the occurrences of Key present in RESPONSEBODY it
	 * takes arguments @resp which is RESPONSEBODY, @keyName is name of the key
	 * in the RESPONSEBODY
	 */
	public int countOccurrenceOfKey(String resp, String keyName) {
		int occurrenceCounter = 0;
		String[] jsonParts = resp.split(","); // maybe another splitter if comma
												// appears within values
		for (int i = 0; i < jsonParts.length; i = i + 2) { // step to every
															// second index so
															// you only check
															// the keys. start
															// with i = 1 to
															// check only values
			String part = jsonParts[i];
			if (part.contains(keyName)) {
				occurrenceCounter++;
			}
		}
		return occurrenceCounter;
	}

	/*
	 * This method asserts the actual and expected values it takes
	 * arguments @expected which is expected Value, @actual is the actual Value
	 */
	private void compareResponseValues(String expected, String actual) {

		System.out.println("Actual Value is  ::" + actual);
		System.out.println("Expected Value is  ::" + expected);
		if (expected.equals(actual)) {
			Hooks.reportLog("Pass", "Actual value " + actual + " is same as expected value " + expected);
			Assert.assertEquals(actual, expected);
		} else {
			Hooks.reportLog("Fail", "Actual value " + actual + " is different from expected value " + expected);
			Assert.fail("Actual value " + actual + " is different from expected value " + expected);
		}
	}

	/*
	 * This method sends the Request to Server based on requestType,
	 * REQUESTBODY, fileType and parameters and stores it in RESPONSEBODY and
	 * STATUS_CODE it takes arguments @requestBodyPath requestbody comes either
	 * comes from a file or feature file, @requestType is the type of request
	 * like POST GET, PUT, @param is the header parameters used for GET
	 * request, @fileType is the form of request like JSON, XML or plain text
	 */
	private void sendRequest(String requestBodyPath, String requestType, String param, String fileType)
			throws Exception {
		RestAssured.baseURI = apiEndPointUri;
		RequestSpecification request = RestAssured.given()
				.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
		request.header("Content-Type", CONTENT_TYPE);
		if (param.equals("NA")) {
			Hooks.reportLog("Info", "Parameter is not present for this request.");
			System.out.println("Parameter is not present for this request.");
		} else {
			request.param("roomid", PARAM);
		}

		if (requestBodyPath != null && !requestBodyPath.isEmpty() && requestType.equalsIgnoreCase("POST")
				|| requestType.equalsIgnoreCase("PUT")) {
			JSONParser jsonParser = new JSONParser();

			if (fileType.equals("JSON")) {
				FILE_PATH = System.getProperty("user.dir") + "//src//test//java//com//hellofresh//resources//testdata//"
						+ requestBodyPath;
				System.out.println("Path of requestbody file is :: " + FILE_PATH);
				FileReader reader = new FileReader(FILE_PATH);
				Object obj = jsonParser.parse(reader);
				REQUESTBODY = obj.toString();
			} else {
				REQUESTBODY = requestBodyPath;
			}
			Hooks.reportLog("Pass", "Request is :: " + REQUESTBODY);
			if (REQUESTBODY.length() > 0) {
				request.body(REQUESTBODY);
				response = request.post();
				Thread.sleep(1 * 1000);
			} else {
				System.out.println(" Request Body cannot be null or empty!");
			}
		} else if (requestType.equalsIgnoreCase("GET")) {
			response = request.get();
		}
		STATUS_CODE = String.valueOf(response.getStatusCode());
		RESPONSEBODY = response.getBody().asString();
		Hooks.reportLog("Pass", "Response is :: " + RESPONSEBODY);
		System.out.println("Response is :: " + RESPONSEBODY);
	}

	/*
	 * This method converts the file into plain String it takes arguments @path
	 * is the file path
	 */

	public static String readFile(String path) throws IOException {
		String sCurrentLine;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
		}
		return sb.toString();
	}
}