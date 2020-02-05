package com.hellofresh.pageObjects;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.hellofresh.selenium.SeleniumUtilities;
import com.hellofresh.ui.steps.Hooks;

/**
 * @author SUNI This class is used to maintain all the SignUp Page element and
 *         related functions
 */

public class SignUpPage {
	WebDriver driver;
	private final static Logger logger = Logger.getLogger(SignUpPage.class.getName());

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Create an account')]")
	public WebElement pageHeading;

	@FindBy(how = How.ID, using = "email_create")
	public WebElement emailTextbox;

	@FindBy(how = How.ID, using = "SubmitCreate")
	public WebElement createAccountButton;

	@FindBy(how = How.ID, using = "id_gender1")
	public WebElement maleGenderRadioButton;

	@FindBy(how = How.ID, using = "id_gender2")
	public WebElement femaleGenderRadioButton;

	@FindBy(how = How.ID, using = "customer_firstname")
	public WebElement firstNameTestbox;

	@FindBy(how = How.ID, using = "customer_lastname")
	public WebElement lastNameTestbox;

	@FindBy(how = How.ID, using = "passwd")
	public WebElement passwordTextbox;

	@FindBy(how = How.ID, using = "days")
	public WebElement selectDaysDropdown;

	@FindBy(how = How.ID, using = "months")
	public WebElement selectMonthsDropdown;

	@FindBy(how = How.ID, using = "years")
	public WebElement selectYearsDropdown;

	@FindBy(how = How.ID, using = "company")
	public WebElement companyTextbox;

	@FindBy(how = How.ID, using = "address1")
	public WebElement address1Textbox;

	@FindBy(how = How.ID, using = "address2")
	public WebElement address2Textbox;

	@FindBy(how = How.ID, using = "city")
	public WebElement cityTextbox;

	@FindBy(how = How.ID, using = "id_state")
	public WebElement selectStateDropdown;

	@FindBy(how = How.ID, using = "postcode")
	public WebElement postcodeTextbox;

	@FindBy(how = How.ID, using = "other")
	public WebElement otherTextbox;

	@FindBy(how = How.ID, using = "phone")
	public WebElement phoneTextbox;

	@FindBy(how = How.ID, using = "phone_mobile")
	public WebElement phoneMobileTextbox;

	@FindBy(how = How.ID, using = "alias")
	public WebElement aliasTextbox;

	@FindBy(how = How.ID, using = "submitAccount")
	public WebElement submitAccountButton;

	public void enterUserEmailAddress(String email) {
		SeleniumUtilities.verifyAndHighlightElement(driver, emailTextbox);
		Hooks.reportLog("Pass", "Enter " + email + " email textbox.");
		logger.info("Enter " + email + " email textbox.");
		emailTextbox.clear();
		emailTextbox.sendKeys(email);
	}

	public void clickOnCreateAccountButton() {
		SeleniumUtilities.verifyAndHighlightElement(driver, createAccountButton);
		Hooks.reportLog("Pass", "Click on Create Account Button.");
		logger.info("Click on Create Account Button.");
		createAccountButton.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public String getPageHeading() {
		Hooks.reportLog("Pass", "Get page heading " + pageHeading.getText());
		logger.info("Get page heading " + pageHeading.getText());
		return pageHeading.getText();
	}

	public void selectUserTitleDropdown(String gender) {

		if (gender.toLowerCase().equals("male")) {
			SeleniumUtilities.verifyAndHighlightElement(driver, maleGenderRadioButton);
			Hooks.reportLog("Pass", "Select " + gender + " in Gender Radiobutton.");
			logger.info("Select " + gender + " in Gender Radiobutton.");
			maleGenderRadioButton.click();
		} else {
			SeleniumUtilities.verifyAndHighlightElement(driver, femaleGenderRadioButton);
			Hooks.reportLog("Pass", "Select " + gender + " in Gender Radiobutton.");
			logger.info("Select " + gender + " in Gender Radiobutton.");
			femaleGenderRadioButton.click();
		}
	}

	public void enterUserFirstName(String fname) {
		SeleniumUtilities.verifyAndHighlightElement(driver, firstNameTestbox);
		Hooks.reportLog("Pass", "Enter " + fname + " Firstname textbox.");
		logger.info("Enter " + fname + " Firstname textbox.");
		firstNameTestbox.clear();
		firstNameTestbox.sendKeys(fname);
	}

	public void enterUserLastName(String lname) {
		SeleniumUtilities.verifyAndHighlightElement(driver, lastNameTestbox);
		Hooks.reportLog("Pass", "Enter " + lname + " in Lastname textbox.");
		logger.info("Enter " + lname + " in Lastname textbox.");
		lastNameTestbox.clear();
		lastNameTestbox.sendKeys(lname);
	}

	public void enterUserPassword(String password) {
		SeleniumUtilities.verifyAndHighlightElement(driver, passwordTextbox);
		Hooks.reportLog("Pass", "Enter " + password + " password textbox.");
		logger.info("Enter " + password + " password textbox.");
		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);
	}

	public void selectUserBirthDay(String day) {
		SeleniumUtilities.verifyAndHighlightElement(driver, selectDaysDropdown);
		Hooks.reportLog("Pass", "Select " + day + " in Day Dropdown.");
		logger.info("Select " + day + " in Day Dropdown.");
		Select dropdown = new Select(selectDaysDropdown);
		dropdown.selectByValue(day);
	}

	public void selectUserBirthMonth(String month) {
		SeleniumUtilities.verifyAndHighlightElement(driver, selectMonthsDropdown);
		Hooks.reportLog("Pass", "Select " + month + " in Month Dropdown.");
		logger.info("Select " + month + " in Month Dropdown.");
		Select dropdown = new Select(selectMonthsDropdown);
		dropdown.selectByValue(month);
	}

	public void selectUserBirthYear(String year) {
		SeleniumUtilities.verifyAndHighlightElement(driver, selectYearsDropdown);
		Hooks.reportLog("Pass", "Select " + year + " in Year Dropdown.");
		logger.info("Select " + year + " in Year Dropdown.");
		Select dropdown = new Select(selectYearsDropdown);
		dropdown.selectByValue(year);
	}

	public void enterUserCompany(String company) {
		SeleniumUtilities.verifyAndHighlightElement(driver, companyTextbox);
		Hooks.reportLog("Pass", "Enter " + company + " in Company textbox.");
		logger.info("Enter " + company + " in Company textbox.");
		companyTextbox.sendKeys(company);
	}

	public void enterUserAddress1(String address) {
		SeleniumUtilities.verifyAndHighlightElement(driver, address1Textbox);
		Hooks.reportLog("Pass", "Enter " + address + " in Address1 textbox.");
		logger.info("Enter " + address + " in Address1 textbox.");
		address1Textbox.sendKeys(address);
	}

	public void enterUserAddress2(String address) {
		SeleniumUtilities.verifyAndHighlightElement(driver, address2Textbox);
		Hooks.reportLog("Pass", "Enter " + address + " in Address2 textbox.");
		logger.info("Enter " + address + " in Address2 textbox.");
		address2Textbox.sendKeys(address);
	}

	public void enterUserCity(String value) {
		SeleniumUtilities.verifyAndHighlightElement(driver, cityTextbox);
		Hooks.reportLog("Pass", "Enter " + value + " in City textbox.");
		logger.info("Enter " + value + " in City textbox.");
		cityTextbox.sendKeys(value);
	}

	public void selectUserState(String state) {
		SeleniumUtilities.verifyAndHighlightElement(driver, selectStateDropdown);
		Hooks.reportLog("Pass", "Select " + state + " in State Dropdown.");
		logger.info("Select " + state + " in State Dropdown.");
		Select dropdown = new Select(selectStateDropdown);
		dropdown.selectByVisibleText(state);
	}

	public void enterUserPostCode(String code) {
		SeleniumUtilities.verifyAndHighlightElement(driver, postcodeTextbox);
		Hooks.reportLog("Pass", "Enter " + code + " in Postal Code textbox.");
		logger.info("Enter " + code + " in Postal Code textbox.");
		postcodeTextbox.sendKeys(code);
	}

	public void enterUserMobileNumber(String number) {
		SeleniumUtilities.verifyAndHighlightElement(driver, phoneMobileTextbox);
		Hooks.reportLog("Pass", "Enter " + number + " in Mobile Number textbox.");
		logger.info("Enter " + number + " in Mobile Number textbox.");
		phoneMobileTextbox.sendKeys(number);
	}

	public void enterUserAddressAlias(String value) {
		SeleniumUtilities.verifyAndHighlightElement(driver, aliasTextbox);
		Hooks.reportLog("Pass", "Enter " + value + " in Address alias textbox.");
		logger.info("Enter " + value + " in Address alias textbox.");
		aliasTextbox.clear();
		aliasTextbox.sendKeys(value);
	}

	public void clickOnRegisterButton() {
		SeleniumUtilities.verifyAndHighlightElement(driver, submitAccountButton);
		Hooks.reportLog("Pass", "Click on Register Button.");
		logger.info("Click on Register Button.");
		submitAccountButton.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}
}
