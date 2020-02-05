#Simplified Automation Framework

Simplified Automation project is a combination of UI as well as API testing.
This provides an end to end Solution for both Web and API Automation.
The major advantage of this framework is its flexibility. In one test case we can accomodate both API and UI tests.

Summary :

#For UI  : This project is a maven project where Selenium Webdriver and Junit is used. Page Object Model is used to maintain the elements and corresponding functions.
1. For reporting, extent-report jar is used and it generated report in /target/ExtentReportResults.html
2. Base Framework is isolated under src/main/java
	2.1. Under com.hellofresh.cucumber package, TestContext is present which is used to initiate the PageObject and Webdriver Manager class
	2.2. Under com.hellofresh.managers package, PageObject and Webdriver Manager class are present which loads all the Page Element and driver instance.
	2.3. Under com.hellofresh.dataProviders package, ConfigFileReader is present which is used to load the config and data property files.
	2.4. Under com.hellofresh.pageObjects package, all the Page Object files are maintained.
	2.5. Under com.hellofresh.selenium package, Selenium related common utilities are mantained.
3. Cucumber related files are present under src/main/test
    3.1. Under com.hellofresh.feature package, we are maintaining different test-suites eg. Regression.feature is for UI and API.feature for Rest API tests.
	3.2. Under com.hellofresh.resources package, we are maintaining the config and data properties.
	3.3. Under com.hellofresh.resources.drivers package, we are storing chrome and firefox driver execs.
	3.4. Under com.hellofresh.restapi.steps package, we are maintaining the logical step definition files for API features.
	3.5. Under com.hellofresh.ui.steps package, we are maintaining the logical step definition files for UI features.
	3.5. Under com.hellofresh.ui.steps package, we are maintaining the logical step definition files for UI features.
	3.6. Under com.hellofresh.runner package, it contains the Cucumber runner file which helps to trigger the tests based on tags provided in feature file. Eg @API for API tests and @UI for UI tests.

#For API : Here are using rest-assured jars and json jars to send the POST, GET and PUT requests and capture the response.

Test Tasks :

1. API tests are present under API.feature
2. UI tests are present under Regression.feature

Test Data :
1. config_qa.properties is used to set envType, browserType, Application URL etc.
2. data_qa.config is used to retrive tests related data like emailID, first name, last name, mobile, address, etc.
3. API JSON request files are stored under com.hellofresh.resources.testdata

Step up project:

1. Download the project from git.
2. Import the maven project in eclipse.
3. Right click on project folder and click on Maven then Update Project.
4. Check all the dependent jars are downloaded and project is build successfully.

Steps to Execute through Eclipse:

1. Navigate to src/test/java > com.hellofresh.runner
2. Right click on CucumberRunner.java then Run As "JUnit Test"

Steps to Execute through Command line:

1. Open Command Prompt and navigate to project folder.
2. mvn clean install
3. mvn test -Dbrowser="chrome" -Dcucumber.options="./src/test/java/com/hellofresh/feature/*" -Dcucumber.options="--tags @UI,@API"
    -Dbrowser supported is chrome, firefox and iexplorer
	-Dcucumber.options as path of Feature file
	-Dcucumber.options="--tags is used to group set of test cases or test suites

Report :

Currently we are storing following reports :
1. Extent HTML Report : target/ExtentReportResults.html
2. JSON Report : target/reports/cucumber.json (Used to publish Jenkins Report)
3. Cucumber Default Report : target/reports/index.html

