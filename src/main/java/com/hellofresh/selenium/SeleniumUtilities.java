package com.hellofresh.selenium;

import java.util.logging.Logger;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hellofresh.ui.steps.Hooks;

/**
 * @author SUNI This class is used to add common utilities for UI
 */

public class SeleniumUtilities {

	private final static Logger logger = Logger.getLogger(SeleniumUtilities.class.getName());

	/*
	 * This method waits till the DOM state becomes complete
	 */
	public static void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(expectation);
			Hooks.reportLog("Pass", "Page is loaded successfully.");
			logger.info("Page is loaded successfully.");
		} catch (Throwable error) {
			Hooks.reportLog("Fail", "Timeout waiting for Page Load Request to complete.");
			logger.severe("Timeout waiting for Page Load Request to complete.");
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	/*
	 * This method validates and highlight the Webpage element
	 */
	public static void verifyAndHighlightElement(WebDriver driver, WebElement element) {
		if (isElementPresent(element)) {
			Hooks.reportLog("Pass", "Element exists in the page." + element);
			logger.info("Element exists in the page." + element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style','border: 2px solid black;');", element);
		} else {
			Hooks.reportLog("Fail", "Element doesn't exist in the page." + element);
			logger.severe("Element doesn't exist in the page." + element);
			Assert.fail("Element doesn't exist in the page." + element);
		}
	}

	public static boolean isElementPresent(WebElement element) {
		int flag = 0;
		try {
			element.isDisplayed();
		} catch (NoSuchElementException e) {
			flag = 1;
		}
		if (flag == 1)
			return false;
		else
			return true;
	}
}
