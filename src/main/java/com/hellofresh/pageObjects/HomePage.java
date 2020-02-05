package com.hellofresh.pageObjects;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.hellofresh.managers.FileReaderManager;
import com.hellofresh.selenium.SeleniumUtilities;
import com.hellofresh.ui.steps.Hooks;

/**
 * @author SUNI This class is used to maintain all the Home Page element and
 *         related functions
 */

public class HomePage {
	WebDriver driver;
	private final static Logger logger = Logger.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.CSS, using = "h1")
	public WebElement headingText;

	@FindBy(how = How.CLASS_NAME, using = "info-account")
	public WebElement welcomeLabel;

	@FindBy(how = How.CLASS_NAME, using = "account")
	public WebElement userFullNameLabel;

	@FindBy(how = How.CLASS_NAME, using = "login")
	public WebElement signupLink;

	@FindBy(how = How.CLASS_NAME, using = "logout")
	public WebElement logoutLink;

	@FindBy(how = How.ID, using = "SubmitLogin")
	public WebElement loginButton;

	@FindBy(how = How.LINK_TEXT, using = "Women")
	public WebElement womenPageLink;

	@FindBy(how = How.LINK_TEXT, using = "Dresses")
	public WebElement dressesPageLink;

	@FindBy(how = How.LINK_TEXT, using = "T-shirts")
	public WebElement tshirtPageLink;

	@FindBy(how = How.CSS, using = "a.product-name[title='Faded Short Sleeve T-shirts']")
	public WebElement fadedTshirtProductLabel;

	@FindBy(how = How.NAME, using = "Submit")
	public WebElement addToCartButton;

	@FindBy(how = How.XPATH, using = "//div[@id='layer_cart' and not(contains(@style,'display: none'))]//div[contains(@class,'layer_cart_product')]/h2")
	public WebElement productAddedToCartMessageLabel;

	@FindBy(how = How.XPATH, using = "//div[@id='layer_cart' and contains(@style,'display: none')]")
	public WebElement popUpLayerNotPresent;

	public void navigateToHomePage() {
		Hooks.reportLog("Pass",
				"Navigate to url " + FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		logger.info("Navigate to url " + FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		driver.navigate().to(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void ClickOnSignInLink() {
		SeleniumUtilities.verifyAndHighlightElement(driver, signupLink);
		Hooks.reportLog("Pass", "Click on Sign In Button present in header.");
		logger.info("Click on Sign In Button present in header.");
		signupLink.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public String getHeadingText() {
		SeleniumUtilities.verifyAndHighlightElement(driver, headingText);
		Hooks.reportLog("Pass", "Get the Heading Text " + headingText.getText());
		logger.info("Get the Heading Text " + headingText.getText());
		return headingText.getText();
	}

	public String getUserFullname() {
		SeleniumUtilities.verifyAndHighlightElement(driver, userFullNameLabel);
		Hooks.reportLog("Pass", "Get the User Full Name Text " + userFullNameLabel.getText());
		logger.info("Get the User Full Name Text " + userFullNameLabel.getText());
		return userFullNameLabel.getText();
	}

	public String getWelcomeText() {
		SeleniumUtilities.verifyAndHighlightElement(driver, welcomeLabel);
		Hooks.reportLog("Pass", "Get the Welcome Text " + welcomeLabel.getText());
		logger.info("Get the Welcome Text " + welcomeLabel.getText());
		return welcomeLabel.getText();
	}

	public void clickOnWomensSectionLink() {
		SeleniumUtilities.verifyAndHighlightElement(driver, womenPageLink);
		Hooks.reportLog("Pass", "Click on Women Link present in header.");
		logger.info("Click on Women Link present in header.");
		womenPageLink.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void clickOnDressesSectionLink() {
		SeleniumUtilities.verifyAndHighlightElement(driver, dressesPageLink);
		Hooks.reportLog("Pass", "Click on Dresses Link present in header.");
		logger.info("Click on Dresses Link present in header.");
		dressesPageLink.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void clickOnTshirtSectionLink() {
		SeleniumUtilities.verifyAndHighlightElement(driver, tshirtPageLink);
		Hooks.reportLog("Pass", "Click on T-Shirt Link present in header.");
		logger.info("Click on T-Shirt Link present in header.");
		tshirtPageLink.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void clickOnFadedTshirtProduct() {
		SeleniumUtilities.verifyAndHighlightElement(driver, fadedTshirtProductLabel);
		Hooks.reportLog("Pass", "Click on Faded T-Shirt Label.");
		logger.info("Click on Faded T-Shirt Label.");
		fadedTshirtProductLabel.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void clickOnAddToCartButton() {
		SeleniumUtilities.verifyAndHighlightElement(driver, addToCartButton);
		Hooks.reportLog("Pass", "Click on Add to Cart Button.");
		logger.info("Click on Add to Cart Button.");
		addToCartButton.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void verifyProductAddedToCartMessage() throws Throwable {
		SeleniumUtilities.verifyAndHighlightElement(driver, productAddedToCartMessageLabel);
		Hooks.reportLog("Pass", "Get the Text " + productAddedToCartMessageLabel.getText());
		logger.info("Get the Text " + productAddedToCartMessageLabel.getText());
		productAddedToCartMessageLabel.getText().contains("Product successfully added to your shopping cart");
	}
}
