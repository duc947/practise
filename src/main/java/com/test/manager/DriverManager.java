package com.test.manager;

import java.net.URL;

import com.test.data.DataMapper;
import com.test.driver.DriverWeb;

public class DriverManager {

	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String WEBDRIVER_CHROME_DRIVER_Value = System.getProperty("user.dir") + "/src/test/resources/common/grid/driver/chromedriver.exe";
	private static final String COULD_NOT_START = "Can't start the driver. \n";
	private static final String CLOSE_DRIVER_ERROR = "Can't close the driver!! \n";
	private static final String DRIVER_NOT_FOUND = "Driver is not instantiated yet, there is a problem some where \n";

	private static ThreadLocal<DriverWeb> sessionDriver = new InheritableThreadLocal<DriverWeb>();
	
	public static void start() {
		System.out.print("======================[STARTING DRIVER]====================== \n");
		DriverWeb driver = null;
		try {
			System.setProperty(WEBDRIVER_CHROME_DRIVER, WEBDRIVER_CHROME_DRIVER_Value);
			Thread.sleep(2000);
			driver = new DriverWeb(new URL(ExecutionManager.getConfiguration().getHubUrl()),
					ExecutionManager.getConfiguration().getCapabilities());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(COULD_NOT_START);
			e.printStackTrace();
		}

		System.out.print("======================[DRIVER STARTED]====================== \n");

		sessionDriver.set(driver);
	}
	
	public static void closeDriver() {
		System.out.print("======================[CLOSE RUNNING DRIVER]====================== \n");
		DriverWeb driver = DriverManager.getDriver();
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(CLOSE_DRIVER_ERROR);
			e.printStackTrace();
		} finally {
			DriverManager.removeDriver();
		}
	}

	public static void removeDriver() {
		sessionDriver.remove();
	}

	public static DriverWeb getDriver() {
		if (sessionDriver.get() == null) {
			System.out.print(DRIVER_NOT_FOUND);
		}
		return sessionDriver.get();
	}

	public static void intitializeTestInfo(String dataFilePath) {
		DataMapper dataMapper = new DataMapper();
		if (dataFilePath != null) {
			System.out.print("======================[MAPPED DATA] : " + dataFilePath + " ====================== \n");
			dataMapper.initMapper(dataFilePath);
		}
		DriverManager.getDriver().setDataMapper(dataMapper);
	}
	
}
