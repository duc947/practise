package com.test.driver;

import java.net.URL;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.internal.utils.configuration.GridNodeConfiguration;
import org.openqa.grid.shared.GridNodeServer;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.SeleniumServer;

import com.aventstack.extentreports.ExtentTest;
import com.test.configuration.ConfigurationManager;

public class DriverManager extends RemoteWebDriver{

	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String WEBDRIVER_CHROME_DRIVER_Value = System.getProperty("user.dir") + "/src/test/resources/data/driver/chromedriver.exe";
	private static final String COULD_NOT_START = "Can't start the driver. \n";
	private static final String DRIVER_NOT_FOUND = "Can't close the driver. The driver is not found!! \n";
	private static final String STARTING_GRID_ERROR = "Problem when strating  grid !!! \n";
	
	private static final String HUB_CONFIG_JSON =  System.getProperty("user.dir") + "/src/test/resources/data/gridConfig/HubConfig.json";
	private static final String NODE_CONFIG_JSON =  System.getProperty("user.dir") + "/src/test/resources/data/gridConfig/NodeConfig.json";
	
	private static Hub hub = null;
	private static SelfRegisteringRemote node = null;
	private static ConfigurationManager configuration;
	private static WebDriver driver;
	private static ExtentTest testReport;


	public static ExtentTest getTestReport() {
		return testReport;
	}

	public static void setTestReport(ExtentTest testReport) {
		DriverManager.testReport = testReport;
	}
	
	public static WebDriver getWebDriver() {
        return driver;
    }
	
	public static void start() {
		System.out.print("======================[STARTING DRIVER]====================== \n");
		try {
			System.setProperty(WEBDRIVER_CHROME_DRIVER, WEBDRIVER_CHROME_DRIVER_Value);
			Thread.sleep(2000);
			
//			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), configuration.getCapabilities());
			driver = new RemoteWebDriver(new URL(configuration.getHubUrl()), configuration.getCapabilities());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(COULD_NOT_START);
			e.printStackTrace();
		}
	}
	
	public static void closeDriver() {
		System.out.print("======================[CLOSE RUNNING DRIVER]====================== \n");
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(DRIVER_NOT_FOUND);
			e.printStackTrace();
		}
	}
	
	
	public static void launchGrid() {
		System.out.print("======================[STARTING GRID]====================== \n");
		try {
			hub = new Hub(GridHubConfiguration.loadFromJSON(HUB_CONFIG_JSON));
			hub.start();
			GridNodeConfiguration nodeConfig = GridNodeConfiguration.loadFromJSON(NODE_CONFIG_JSON);
			RegistrationRequest req = new RegistrationRequest(nodeConfig);
			node = new SelfRegisteringRemote(req);
			GridNodeServer server = new SeleniumServer(req.getConfiguration());
			node.setRemoteServer(server);
			node.startRemoteServer();
			node.startRegistrationProcess();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(STARTING_GRID_ERROR);
			e.printStackTrace();
		}
		
	}
	
	public static void stopGrid() {
		System.out.print("======================[STOP GRID]====================== \n");
		node.deleteAllBrowsers();
		node.stopRemoteServer();
	}
	
	public static ConfigurationManager getConfiguration() {
		return configuration;
	}

	public static void setConfiguration(ConfigurationManager configuration) {
		DriverManager.configuration = configuration;
	}
	
}
