/**
 * 
 */
package com.hellofresh.ui.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.hellofresh.cucumber.TestContext;
import com.hellofresh.pageObjects.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author SUNI
 *
 */
public class HomeSteps {

	TestContext testContext;
	HomePage homePage;
	WebDriver driver;

	public HomeSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
		driver = testContext.getWebDriverManager().getDriver();
	}

	@Given("^User navigates to HomePage$")
	public void user_navigates_to() throws Throwable {
		homePage.navigateToHomePage();
	}

	@When("^User clicks on SignIn link$")
	public void user_clicks_on_SignIn_link() throws Throwable {
		homePage.ClickOnSignInLink();
	}

	@Then("^User should see My Account Page$")
	public void user_should_see_My_Account_Page() throws Throwable {
		Assert.assertEquals("MY ACCOUNT", homePage.getHeadingText());
	}

	@Then("^Username should be visible in header$")
	public void username_should_be_visible_in_header() throws Throwable {
		Assert.assertEquals(homePage.getUserFullname(), SignUpSteps.firstName + " " + SignUpSteps.lastName);
		Assert.assertTrue((homePage.getWelcomeText()).contains("Welcome to your account"));
	}

	@Then("^User should be able to logout$")
	public void user_should_be_able_to_logout() throws Throwable {
		Assert.assertTrue(homePage.logoutLink.isDisplayed());
		Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
	}

	@And("^User clicks on Women Link$")
	public void user_clicks_on_women_link() throws Throwable {
		homePage.clickOnWomensSectionLink();
	}

	@And("^User adds a product and moves it in the cart$")
	public void user_adds_a_product_and_moves_it_in_the_cart() throws Throwable {
		homePage.clickOnFadedTshirtProduct();
		homePage.clickOnAddToCartButton();
	}

	@Then("^User should see the added product in cart$")
	public void user_should_see_the_added_product_in_cart() throws Throwable {
		homePage.verifyProductAddedToCartMessage();
	}

}
