package com.test.driver;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverManager {

	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String WEBDRIVER_CHROME_DRIVER_Value = System.getProperty("user.dir") + "/src/test/resources/data/driver/chromedriver.exe";
	private static final String COULD_NOT_START = "Can't start the driver.";
	private static final String DRIVER_NOT_FOUND = "Can't close the driver. The driver is not found!!";

	public static WebDriver driver;
	
//	public static WebDriver getWebDriver() {
//        return driver;
//    }
	
	public static void start() {
		System.out.print("======================[STARTING DRIVER]======================");
		try {
			System.setProperty(WEBDRIVER_CHROME_DRIVER, WEBDRIVER_CHROME_DRIVER_Value);
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//			ChromeOptions options = new ChromeOptions();
//			capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-ssl-errors=yes"));
//			options.addArguments("--start-maximized");
//			options.addArguments("--ignore-certificate-errors");
//			options.addArguments("--allow-insecure-localhost=yes");
//			options.addArguments("--ignore-urlfetcher-cert-requests=yes");
//			options.addArguments("disable-infobars");
//			options.addArguments("--test-type");
//			Map<String, Object> prefs = new HashMap<String, Object>();
//			prefs.put("profile.default_content_setting_values.notifications", 2);
//			prefs.put("credentials_enable_service", false);
//			options.setExperimentalOption("prefs", prefs);
//			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//
//			LoggingPreferences logPrefs = new LoggingPreferences();
//			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
//			capabilities.setCapability("goog:loggingPrefs", logPrefs);
//			options.merge(capabilities);
//
//			driver = new ChromeDriver(options);
			
			driver = new ChromeDriver();
		} catch (Exception e) {
			System.out.println(COULD_NOT_START);
			e.printStackTrace();
		}
	}
	
	public static void closeDriver() {
		System.out.print("======================[Close Running Driver]======================");
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println(DRIVER_NOT_FOUND);
			e.printStackTrace();
		}
	}
}
