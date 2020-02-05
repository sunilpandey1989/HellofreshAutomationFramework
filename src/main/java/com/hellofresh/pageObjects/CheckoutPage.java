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
 * @author SUNI This class is used to maintain all the Checkout Page element and
 *         related functions
 */

public class CheckoutPage {
	WebDriver driver;
	private final static Logger logger = Logger.getLogger(CheckoutPage.class.getName());

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
	public WebElement proceedToCheckoutButton;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
	public WebElement cartProceedToCheckoutButton;

	@FindBy(how = How.NAME, using = "processAddress")
	public WebElement cartAddressProceedToCheckoutButton;

	@FindBy(how = How.NAME, using = "processCarrier")
	public WebElement cartCarrierProceedToCheckoutButton;

	@FindBy(how = How.ID, using = "uniform-cgv")
	public WebElement termAndConditionCheckbox;

	@FindBy(how = How.CLASS_NAME, using = "bankwire")
	public WebElement bankwirePaymentModeLink;

	@FindBy(how = How.XPATH, using = "//*[@id='cart_navigation']/button")
	public WebElement confirmOrderButton;

	@FindBy(how = How.CSS, using = "h1")
	public WebElement orderConfirmationHeaderLabel;

	@FindBy(how = How.XPATH, using = "//li[@id='step_end' and @class='step_current last']")
	public WebElement paymentLastStepLabel;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'cheque-indent')]/strong")
	public WebElement orderConfirmationMessageLabel;

	@FindBy(how = How.XPATH, using = "//li[@class='step_done step_done_last four']")
	public WebElement shippingDetailsStepLabel;

	public void clickOnProceedToCheckout() {
		SeleniumUtilities.verifyAndHighlightElement(driver, proceedToCheckoutButton);
		Hooks.reportLog("Pass", "Click on Proceed To Checkout Button");
		logger.info("Click on Proceed To Checkout Button");
		proceedToCheckoutButton.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void clickOnCartProceedToCheckout() {
		SeleniumUtilities.verifyAndHighlightElement(driver, cartProceedToCheckoutButton);
		Hooks.reportLog("Pass", "Click on Cart's Proceed To Checkout Button");
		logger.info("Click on Cart's Proceed To Checkout Button");
		cartProceedToCheckoutButton.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void clickOnProceedToCheckoutAfterAddressSelection() {
		SeleniumUtilities.verifyAndHighlightElement(driver, cartAddressProceedToCheckoutButton);
		Hooks.reportLog("Pass", "Click on Proceed To Checkout Button after Address Selection.");
		logger.info("Click on Proceed To Checkout Button after Address Selection.");
		cartAddressProceedToCheckoutButton.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void clickOnProceedToCheckoutAfterCarrierSelection() {
		SeleniumUtilities.verifyAndHighlightElement(driver, cartCarrierProceedToCheckoutButton);
		Hooks.reportLog("Pass", "Click on Proceed To Checkout Button After Carrier Selection.");
		logger.info("Click on Proceed To Checkout Button After Carrier Selection.");
		cartCarrierProceedToCheckoutButton.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void checkTermAndCondition() {
		SeleniumUtilities.verifyAndHighlightElement(driver, termAndConditionCheckbox);
		Hooks.reportLog("Pass", "Click on Terms and Condition checkbox.");
		logger.info("Click on Terms and Condition checkbox.");
		termAndConditionCheckbox.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void clickOnBankwirePaymentModeLink() {
		SeleniumUtilities.verifyAndHighlightElement(driver, bankwirePaymentModeLink);
		Hooks.reportLog("Pass", "Click on Bankwire Payment Mode Link.");
		logger.info("Click on Bankwire Payment Mode Link.");
		bankwirePaymentModeLink.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public void clickConfirmOrder() {
		SeleniumUtilities.verifyAndHighlightElement(driver, confirmOrderButton);
		Hooks.reportLog("Pass", "Click on Confirm Order Button.");
		logger.info("Click on Confirm Order Button.");
		confirmOrderButton.click();
		SeleniumUtilities.waitForPageLoaded(driver);
	}

	public String getOrderConfirmationHeaderText() {
		SeleniumUtilities.verifyAndHighlightElement(driver, orderConfirmationHeaderLabel);
		Hooks.reportLog("Pass", "Order confirmation text is " + orderConfirmationHeaderLabel.getText());
		logger.info("Order confirmation text is " + orderConfirmationHeaderLabel.getText());
		return orderConfirmationHeaderLabel.getText();
	}

	public String getPaymentLastStepLabelText() {
		SeleniumUtilities.verifyAndHighlightElement(driver, orderConfirmationHeaderLabel);
		Hooks.reportLog("Pass", "Payment Last Step text is " + paymentLastStepLabel.getText());
		logger.info("Payment Last Step text is " + paymentLastStepLabel.getText());
		return paymentLastStepLabel.getText();
	}

	public String getOrderCompletionMessageLabelText() {
		SeleniumUtilities.verifyAndHighlightElement(driver, orderConfirmationHeaderLabel);
		Hooks.reportLog("Pass", "Order confirmation Message is " + orderConfirmationMessageLabel.getText());
		logger.info("Order confirmation Message is " + orderConfirmationMessageLabel.getText());
		return orderConfirmationMessageLabel.getText();
	}

	public String getShippingStepLabelText() {
		SeleniumUtilities.verifyAndHighlightElement(driver, orderConfirmationHeaderLabel);
		Hooks.reportLog("Pass", "Shipping Last Step text is " + shippingDetailsStepLabel.getText());
		logger.info("Shipping Last Step text is " + shippingDetailsStepLabel.getText());
		return shippingDetailsStepLabel.getText();
	}
}
