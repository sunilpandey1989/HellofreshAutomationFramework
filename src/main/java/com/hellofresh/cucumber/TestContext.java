package com.hellofresh.cucumber;

import com.hellofresh.managers.PageObjectManager;
import com.hellofresh.managers.WebDriverManager;

/**
 * @author SUNI This class is the starting point which initiates the PageObjects
 *         and WebDriver Class
 */

public class TestContext {

	private WebDriverManager webDriverManager;
	private PageObjectManager pageObjectManager;

	public TestContext() {
		webDriverManager = new WebDriverManager();
		pageObjectManager = new PageObjectManager(webDriverManager);
	}

	public WebDriverManager getWebDriverManager() {
		return webDriverManager;
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
}
