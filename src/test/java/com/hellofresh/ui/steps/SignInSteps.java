/**
 * 
 */
package com.hellofresh.ui.steps;

import org.openqa.selenium.WebDriver;

import com.hellofresh.cucumber.TestContext;
import com.hellofresh.pageObjects.SignInPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * @author SUNI
 *
 */
public class SignInSteps {

	TestContext testContext;
	SignInPage signInPage;
	WebDriver driver;

	public SignInSteps(TestContext context) {
		testContext = context;
		signInPage = testContext.getPageObjectManager().getSignInPage();
		driver = testContext.getWebDriverManager().getDriver();
	}

	@Given("^User enters existing UserName and Password$")
	public void user_enters_existing_UserName_and_Password() throws Throwable {
		signInPage.enterSignInEmailID(SignUpSteps.emailID);
		signInPage.enterSignInPassword(SignUpSteps.password);
	}

	@When("^User clicks on SignIn Button$")
	public void user_clicks_on_SignIn_button() throws Throwable {
		signInPage.ClickSignInButton();
	}
}
