package com.hellofresh.pageObjects;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.hellofresh.selenium.SeleniumUtilities;
import com.hellofresh.ui.steps.Hooks;

/**
 * @author SUNI This class is used to maintain all the SignIn Page element and
 *         related functions
 */

public class SignInPage {
	WebDriver driver;
	private final static Logger logger = Logger.getLogger(SignInPage.class.getName());

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "email")
	public WebElement emailTextbox;

	@FindBy(how = How.ID, using = "passwd")
	public WebElement passwordTextbox;

	@FindBy(how = How.ID, using = "SubmitLogin")
	public WebElement signInButton;

	public void ClickSignInButton() {
		SeleniumUtilities.verifyAndHighlightElement(driver, signInButton);
		Hooks.reportLog("Pass", "Click on Sign In Button.");
		logger.info("Click on Sign In Button.");
		signInButton.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void enterSignInEmailID(String emailID) {
		SeleniumUtilities.verifyAndHighlightElement(driver, emailTextbox);
		Hooks.reportLog("Pass", "Enter " + emailID + " email textbox.");
		logger.info("Enter " + emailID + " email textbox.");
		emailTextbox.clear();
		emailTextbox.sendKeys(emailID);
	}

	public void enterSignInPassword(String passwd) {
		SeleniumUtilities.verifyAndHighlightElement(driver, passwordTextbox);
		Hooks.reportLog("Pass", "Enter " + passwd + " password textbox.");
		logger.info("Enter " + passwd + " password textbox.");
		passwordTextbox.clear();
		passwordTextbox.sendKeys(passwd);
	}
}
