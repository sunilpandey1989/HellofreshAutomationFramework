package com.hellofresh.managers;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.hellofresh.enums.DriverType;
import com.hellofresh.enums.EnvironmentType;

/**
 * @author SUNI This class initializes the WebDriver object
 */

public class WebDriverManager {

	private WebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private final static Logger logger = Logger.getLogger(WebDriverManager.class.getName());

	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}

	/*
	 * This method returns driver Object if not exists it creates new
	 */
	public WebDriver getDriver() {
		if (driver == null)
			driver = createDriver();
		return driver;
	}

	/*
	 * This method returns driver is initialized or not
	 */
	public boolean isDriverInitialized() {
		if (driver == null)
			return false;
		return true;
	}

	/*
	 * This method returns driver Object based on environmentType
	 */
	private WebDriver createDriver() {
		logger.info("Environment Type is " + environmentType);
		switch (environmentType) {
		case LOCAL:
			driver = createLocalDriver();
			break;
		case REMOTE:
			driver = createRemoteDriver();
			break;
		}
		return driver;
	}

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	private WebDriver createLocalDriver() {
		switch (driverType) {
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver",
					FileReaderManager.getInstance().getConfigReader().getDriverPath() + "geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case CHROME:
			System.setProperty(CHROME_DRIVER_PROPERTY,
					FileReaderManager.getInstance().getConfigReader().getDriverPath() + "chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case INTERNETEXPLORER:
			driver = new InternetExplorerDriver();
			break;
		}
		logger.info("Invoked browser is " + driverType);
		if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
			driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(),
				TimeUnit.SECONDS);
		return driver;
	}

	/*
	 * This method closes all the driver active browsers
	 */
	public void closeDriver() {
		driver.close();
		driver.quit();
		logger.info("Closing Selenium initiallised browser.");
	}
}
