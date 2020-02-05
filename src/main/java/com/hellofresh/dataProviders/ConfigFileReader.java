package com.hellofresh.dataProviders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import com.hellofresh.enums.DriverType;
import com.hellofresh.enums.EnvironmentType;

/**
 * @author SUNI This class loads the properties files such as config and data
 *         files that is used throughout the test execution
 */

public class ConfigFileReader {

	private final static Logger logger = Logger.getLogger(ConfigFileReader.class.getName());
	private Properties properties;
	private String configPropertyFilePath = null;
	private String dataPropertyFilePath = null;

	public ConfigFileReader() {
		BufferedReader reader = null;
		BufferedReader reader1 = null;

		try {
			configPropertyFilePath = "src//test//java//com//hellofresh//resources//config_qa.properties";
			dataPropertyFilePath = "src//test//java//com//hellofresh//resources//data_qa.properties";
			reader = new BufferedReader(new FileReader(configPropertyFilePath));
			reader1 = new BufferedReader(new FileReader(dataPropertyFilePath));
			properties = new Properties();
			properties.load(reader);
			properties.load(reader1);
			reader.close();
			logger.info("Properties are loaded successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			logger.severe("Loading of properties failed.");
			throw new RuntimeException(
					"Configuration.properties not found at " + configPropertyFilePath + " and " + dataPropertyFilePath);
		}
	}

	/*
	 * This method returns the path of driver present in config_qa.properties.
	 */
	public String getDriverPath() {
		String driverPath = properties.getProperty("driverPath");
		logger.info("Driver path is " + driverPath);
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException(
					"Driver Path not specified in the Configuration.properties file for the Key:driverPath");
	}

	/*
	 * This method returns the implicit wait present in config_qa.properties.
	 */
	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		logger.info("Implicit wait is " + implicitlyWait);
		if (implicitlyWait != null) {
			try {
				return Long.parseLong(implicitlyWait);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 10;
	}

	/*
	 * This method returns the Application URL present in config_qa.properties.
	 */
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if (url != null)
			return url;
		else
			throw new RuntimeException(
					"Application Url not specified in the Configuration.properties file for the Key:url");
	}

	/*
	 * This method returns the browser type present in config_qa.properties.
	 */
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if (browserName == null || browserName.equals("chrome"))
			return DriverType.CHROME;
		else if (browserName.equalsIgnoreCase("firefox"))
			return DriverType.FIREFOX;
		else if (browserName.equals("iexplorer"))
			return DriverType.INTERNETEXPLORER;
		else
			throw new RuntimeException(
					"Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}

	/*
	 * This method returns the Local or Remote environment type present in
	 * config_qa.properties.
	 */
	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if (environmentName == null || environmentName.equalsIgnoreCase("local"))
			return EnvironmentType.LOCAL;
		else if (environmentName.equals("remote"))
			return EnvironmentType.REMOTE;
		else
			throw new RuntimeException(
					"Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	}

	/*
	 * This method maximizes the browser property present in
	 * config_qa.properties.
	 */
	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if (windowSize != null)
			return Boolean.valueOf(windowSize);
		return true;
	}

	/*
	 * This method returns the report Config path for extent report present in
	 * config_qa.properties.
	 */
	public String getReportConfigPath() {
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}

	/*
	 * This method return the value based on key present in property files.
	 * 
	 * @key is the key stored in properties file
	 */
	public String getPropertyValue(String key) {
		return properties.getProperty(key);
	}
}
