package com.hellofresh.runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.hellofresh.managers.FileReaderManager;
import com.relevantcodes.extentreports.ExtentReports;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/com/hellofresh/feature" }, glue = "com/hellofresh", tags = {
		"@API" }, plugin = { "pretty", "html:target/reports", "json:target/reports/cucumber.json",
				"junit:target/reports/cucumber.xml" }, monochrome = true)

public class CucumberRunner {

	public static ExtentReports report;

	@BeforeClass
	public static void setUp() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\target\\ExtentReportResults.html", true);
		report.loadConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
	}

	@AfterClass
	public static void tearDown() {
		report.flush();
	}

}
