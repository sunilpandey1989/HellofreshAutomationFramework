/**
 * 
 */
package com.hellofresh.ui.steps;

import java.util.Random;

import org.openqa.selenium.WebDriver;

import com.hellofresh.cucumber.TestContext;
import com.hellofresh.managers.FileReaderManager;
import com.hellofresh.pageObjects.SignUpPage;

import cucumber.api.java.en.When;

/**
 * @author SUNI
 *
 */
public class SignUpSteps {

	TestContext testContext;
	SignUpPage signUpPage;
	WebDriver driver;

	public static String firstName = null;
	public static String lastName = null;
	public static String emailID = null;
	public static String password = null;

	public SignUpSteps(TestContext context) {
		testContext = context;
		signUpPage = testContext.getPageObjectManager().getSignUpPage();
		driver = testContext.getWebDriverManager().getDriver();
	}

	@When("^User enters valid EmailID in Email Address$")
	public void user_enters_valid_in_Email_Address() throws Throwable {
		int rand = (new Random()).nextInt(900000) + 100000;
		emailID = "hf_challenge_" + rand + "@hf" + rand + ".com";
		signUpPage.enterUserEmailAddress(emailID);
	}

	@When("^User clicks on Create an account button$")
	public void user_clicks_on_Create_an_account_button() throws Throwable {
		signUpPage.clickOnCreateAccountButton();
	}

	@When("^User fills all the details correctly$")
	public void user_fills_all_the_details_correctly() throws Throwable {
		signUpPage
				.selectUserTitleDropdown(FileReaderManager.getInstance().getConfigReader().getPropertyValue("Gender"));

		firstName = FileReaderManager.getInstance().getConfigReader().getPropertyValue("FirstName");
		lastName = FileReaderManager.getInstance().getConfigReader().getPropertyValue("LastName");
		password = FileReaderManager.getInstance().getConfigReader().getPropertyValue("Password");

		signUpPage.enterUserFirstName(FileReaderManager.getInstance().getConfigReader().getPropertyValue("FirstName"));
		signUpPage.enterUserLastName(FileReaderManager.getInstance().getConfigReader().getPropertyValue("LastName"));

		signUpPage.enterUserPassword(FileReaderManager.getInstance().getConfigReader().getPropertyValue("Password"));

		signUpPage.selectUserBirthDay(FileReaderManager.getInstance().getConfigReader().getPropertyValue("BirthDay"));
		signUpPage
				.selectUserBirthMonth(FileReaderManager.getInstance().getConfigReader().getPropertyValue("BirthMonth"));
		signUpPage.selectUserBirthYear(FileReaderManager.getInstance().getConfigReader().getPropertyValue("BirthYear"));

		signUpPage.enterUserCompany(FileReaderManager.getInstance().getConfigReader().getPropertyValue("Company"));
		signUpPage.enterUserAddress1(FileReaderManager.getInstance().getConfigReader().getPropertyValue("Address1"));
		signUpPage.enterUserAddress2(FileReaderManager.getInstance().getConfigReader().getPropertyValue("Address2"));
		signUpPage.enterUserCity(FileReaderManager.getInstance().getConfigReader().getPropertyValue("City"));
		signUpPage.selectUserState(FileReaderManager.getInstance().getConfigReader().getPropertyValue("State"));

		signUpPage.enterUserPostCode(FileReaderManager.getInstance().getConfigReader().getPropertyValue("PostalCode"));

		signUpPage.enterUserMobileNumber(
				FileReaderManager.getInstance().getConfigReader().getPropertyValue("MobilePhone"));

		signUpPage.enterUserAddressAlias(
				FileReaderManager.getInstance().getConfigReader().getPropertyValue("AddressAlias"));
	}

	@When("^User clicks on Registration button$")
	public void user_clicks_on_Registration_button() throws Throwable {
		signUpPage.clickOnRegisterButton();
	}

}
