package com.test.configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.test.report.ExtentReport;

public class ConfigurationManager {
	
	public static final String CONFIG_FILE_TESTNG_PARAM_NAME = "configfile";
	public static final String UNKNOWN_PLATFORM = "Unknown platform : ";
	public static final String JSON_MAPPING_ERROR = "Problem When reading the configuration file: ";

	private boolean localExecution;
	protected String browserName;
	private String hubUrl;
	private String version;
	private String platform;
	private static ExtentReport report;
	

	public void setLocalExecution(boolean localExecution) {
		this.localExecution = localExecution;
	}
	
	public boolean isLocalExecution() {
		return localExecution;
	}
	
	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getHubUrl() {
		return hubUrl;
	}

	public void setHubUrl(String hubUrl) {
		this.hubUrl = hubUrl;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public static ExtentReport getReport() {
		return report;
	}

	public static void setReport(ExtentReport report) {
		ConfigurationManager.report = report;
	}

	private Platform getPlateform() {
		Platform platformAny = Platform.ANY;
		if (!StringUtils.isEmpty(platform)) {
			try {
				platformAny = Platform.valueOf(platform);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(UNKNOWN_PLATFORM);
				e.printStackTrace();
			}
		}
		return platformAny;
	}
	
	public DesiredCapabilities getCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, version, getPlateform());

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
		return capabilities;
	}
	
	
	
}
