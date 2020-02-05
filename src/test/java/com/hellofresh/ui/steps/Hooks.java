package com.hellofresh.ui.steps;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.google.common.io.Files;
import com.hellofresh.cucumber.TestContext;
import com.hellofresh.runner.CucumberRunner;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * @author SUNI
 *
 */

public class Hooks {

	TestContext testContext;

	public static ExtentTest test;

	public Hooks() {

	}

	public Hooks(TestContext context) {
		testContext = context;
	}

	@Before
	public void beforeScenario(Scenario scenario) {
		test = CucumberRunner.report.startTest(scenario.getName());
	}

	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed() && testContext.getWebDriverManager().isDriverInitialized()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				File sourcePath = ((TakesScreenshot) testContext.getWebDriverManager().getDriver())
						.getScreenshotAs(OutputType.FILE);

				File destinationPath = new File(
						System.getProperty("user.dir") + "/target/reports/screenshots" + screenshotName + ".png");
				Files.copy(sourcePath, destinationPath);
				reportLog("Fail", test.addScreenCapture(destinationPath.getAbsolutePath())
						+ Thread.currentThread().getStackTrace().toString());
			} catch (WebDriverException wde) {
				System.out.println(wde.getMessage());
			} catch (Exception cce) {
				cce.printStackTrace();
			}
		}
	}

	@After(order = 0)
	public void AfterSteps() {
		if (testContext.getWebDriverManager().isDriverInitialized()) {
			testContext.getWebDriverManager().closeDriver();
		}
		CucumberRunner.report.endTest(test);
	}

	// Method for adding logs passed from test cases
	public static void reportLog(String logType, String message) {
		if (logType.equals("Info")) {
			test.log(LogStatus.INFO, message);
		} else if (logType.equals("Fail")) {
			test.log(LogStatus.FAIL, message);
		} else if (logType.equals("Pass")) {
			test.log(LogStatus.PASS, message);
		} else
			test.log(LogStatus.SKIP, message);
	}
}
