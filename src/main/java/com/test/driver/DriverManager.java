package com.test.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String WEBDRIVER_CHROME_DRIVER_Value = System.getProperty("user.dir") + "/src/test/resources/data/driver/chromedriver.exe";
	private static final String COULD_NOT_START = "Can't start the driver.";
	private static final String DRIVER_NOT_FOUND = "Can't close the driver. The driver is not found!!";

	public static WebDriver driver;
	
	public static void start() {
		System.out.print("======================[STARTING DRIVER]======================");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
		}
		try {
			System.setProperty(WEBDRIVER_CHROME_DRIVER, WEBDRIVER_CHROME_DRIVER_Value);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
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
