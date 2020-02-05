/**
 * 
 */
package com.hellofresh.ui.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.hellofresh.cucumber.TestContext;
import com.hellofresh.pageObjects.CheckoutPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author SUNI
 *
 */
public class CheckoutSteps {

	TestContext testContext;
	CheckoutPage checkoutPage;
	WebDriver driver;

	public CheckoutSteps(TestContext context) {
		testContext = context;
		checkoutPage = testContext.getPageObjectManager().getCheckoutPage();
		driver = testContext.getWebDriverManager().getDriver();
	}

	@When("^User clicks on 'Proceed To Checkout'$")
	public void user_clicks_on_proceed_to_checkout() throws Throwable {
		checkoutPage.clickOnProceedToCheckout();
		checkoutPage.clickOnCartProceedToCheckout();
	}

	@And("^User clicks on 'Proceed To Checkout' in Address Section$")
	public void user_clicks_on_proceed_to_checkout_in_address_section() throws Throwable {
		checkoutPage.clickOnProceedToCheckoutAfterAddressSelection();
	}

	@And("^User clicks on 'Proceed To Checkout' in Shipping Section$")
	public void user_clicks_on_proceed_to_checkout_in_shipping_section() throws Throwable {
		checkoutPage.checkTermAndCondition();
		checkoutPage.clickOnProceedToCheckoutAfterCarrierSelection();
	}

	@And("^User selects 'Bankwire' payment method$")
	public void user_selects_bankwire_payment_method() throws Throwable {
		checkoutPage.clickOnBankwirePaymentModeLink();
	}

	@And("^User confirms the order$")
	public void user_confirms_the_order() throws Throwable {
		checkoutPage.clickConfirmOrder();
	}

	@Then("^User should be able to verify order completion details$")
	public void user_should_be_able_to_verify_order_completion_details() throws Throwable {
		Assert.assertEquals("ORDER CONFIRMATION text is present in Header.",
				checkoutPage.getOrderConfirmationHeaderText(), "ORDER CONFIRMATION");
		Assert.assertTrue("Payment is the last Step in the Checkout Process.",
				checkoutPage.getPaymentLastStepLabelText().contains("Payment"));
		Assert.assertTrue("Shipping is the second last Step in the Checkout Process",
				checkoutPage.getShippingStepLabelText().contains("Shipping"));
		Assert.assertTrue("Your order on My Store is complete is displayed successfully.",
				checkoutPage.getOrderCompletionMessageLabelText().contains("Your order on My Store is complete."));
		Assert.assertTrue("Order confirmation URL is matched with expected URL.",
				driver.getCurrentUrl().contains("controller=order-confirmation"));
	}
}
