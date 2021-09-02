package com.test.driver;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.internal.utils.configuration.GridNodeConfiguration;
import org.openqa.grid.shared.GridNodeServer;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.SeleniumServer;

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
	private static WebDriver driver;
	
	public static WebDriver getWebDriver() {
        return driver;
    }
	
	public static void start() {
		System.out.print("======================[STARTING DRIVER]======================");
		try {
			System.setProperty(WEBDRIVER_CHROME_DRIVER, WEBDRIVER_CHROME_DRIVER_Value);
			DesiredCapabilities capabilities = new DesiredCapabilities("chrome", "", Platform.ANY);
			ChromeOptions options = new ChromeOptions();
			capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-ssl-errors=yes"));
			options.addArguments("--start-maximized");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--allow-insecure-localhost=yes");
			options.addArguments("--ignore-urlfetcher-cert-requests=yes");
			options.addArguments("disable-infobars");
			options.addArguments("--test-type");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			options.setExperimentalOption("prefs", prefs);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			capabilities.setCapability("goog:loggingPrefs", logPrefs);
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(COULD_NOT_START);
			e.printStackTrace();
		}
	}
	
	public static void closeDriver() {
		System.out.print("======================[CLOSE RUNNING DRIVER]======================");
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(DRIVER_NOT_FOUND);
			e.printStackTrace();
		}
	}
	
	
	public static void launchGrid() {
		System.out.print("======================[STARTING GRID]======================");
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
		System.out.print("======================[STOP GRID]======================");
		node.deleteAllBrowsers();
		node.stopRemoteServer();
	}
}
