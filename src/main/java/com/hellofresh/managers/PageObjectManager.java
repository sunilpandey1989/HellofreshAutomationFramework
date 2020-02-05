package com.hellofresh.managers;

import org.openqa.selenium.WebDriver;

import com.hellofresh.pageObjects.CheckoutPage;
import com.hellofresh.pageObjects.HomePage;
import com.hellofresh.pageObjects.SignInPage;
import com.hellofresh.pageObjects.SignUpPage;

/**
 * @author SUNI This class initializes the Pages only when driver is initialized
 */

public class PageObjectManager {

	private WebDriver driver;

	private WebDriverManager driverManager;

	private SignUpPage signUpPage;
	private HomePage homePage;
	private SignInPage signInPage;
	private CheckoutPage checkoutPage;

	public PageObjectManager() {
	}

	public void initializeDriver() {
		if (null == driver) {
			driver = driverManager.getDriver();
		}
	}

	public PageObjectManager(WebDriverManager webDriverManager) {
		this.driverManager = webDriverManager;
	}

	public SignUpPage getSignUpPage() {
		initializeDriver();
		return (signUpPage == null) ? signUpPage = new SignUpPage(driver) : signUpPage;
	}

	public HomePage getHomePage() {
		initializeDriver();
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}

	public SignInPage getSignInPage() {
		initializeDriver();
		return (signInPage == null) ? signInPage = new SignInPage(driver) : signInPage;
	}

	public CheckoutPage getCheckoutPage() {
		initializeDriver();
		return (checkoutPage == null) ? checkoutPage = new CheckoutPage(driver) : checkoutPage;
	}
}
