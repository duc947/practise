package com.test.report;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.test.configuration.ConfigurationManager;
import com.test.driver.DriverManager;

public class ExtentReport extends ExtentReports {

	public final static String EXTENT_REPORT_PATH = "./target/extent-reports/";
	public final static String EXTENT_REPORT_EXTENSION = ".html";
	public final static String CLOSING_REPORT_ERROR = "[ERROR] Problem happen when closing extentReport report!!!\n";
	public final static String CREATING_REPORT_ERROR = "[ERROR] Somethings wrong when create reporter. Check config";

	public static ExtentReport createReport(String suiteName) {
		ExtentReport report = new ExtentReport(EXTENT_REPORT_PATH + suiteName + EXTENT_REPORT_EXTENSION, true);
		Map<String, String> configd = new HashMap<String, String>();
		DesiredCapabilities desiredCapabilities = DriverManager.getConfiguration().getCapabilities();
		Map<String, Object> caps = (Map<String, Object>) desiredCapabilities.asMap();
		for (String capabilityName : caps.keySet()) {
			if (desiredCapabilities.getCapability(capabilityName) != null) {
				configd.put(capabilityName, desiredCapabilities.getCapability(capabilityName).toString());
			} else {
				configd.put(capabilityName, "");
			}
		}
		for (Map.Entry<String, String> entry : configd.entrySet()) {
			report.setSystemInfo(entry.getKey(), entry.getValue());
		}
		return report;
	}

	public ExtentReport(String name, boolean b) {
		createFolderIfNotExist(ExtentReport.EXTENT_REPORT_PATH);
		configureReport(name);
	}

	public static void createFolderIfNotExist(String path) {
		try {
			File directory = new File(path);
			if (!directory.exists()) {
				directory.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ExtentReport configureReport(String fileName) {

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		this.setAnalysisStrategy(AnalysisStrategy.TEST);
		this.attachReporter(htmlReporter);
		return this;
	}

	public static void startTestReport(ITestResult testResult) {
		String testDescription = testResult.getMethod().getDescription();
		DriverManager.setTestReport(ConfigurationManager.getReport()
				.createTestCase(testResult.getInstanceName() + "." + testResult.getName(), testDescription));

	}

	public static void endTestReport(ITestResult testResult) {
		DriverManager.getTestReport().assignCategory(testResult.getMethod().getGroups());
	}

	public synchronized ExtentTest createTestCase(String name, String description) {
		try {
			return this.createTest(name, description);
		} catch (Exception e) {
			throw new RuntimeException(CREATING_REPORT_ERROR);
		}
	}

	public static void endTest() {
		if (ConfigurationManager.getReport() != null) {
			try {
				ConfigurationManager.getReport().flush();
			} catch (Exception e) {
				System.out.println(CLOSING_REPORT_ERROR);
				e.printStackTrace();
			}
		}
	}

	public static void log(Status logStatus, String step) {
		if (DriverManager.getWebDriver() == null) {
			return;
		}
		if (DriverManager.getTestReport() != null) {
			DriverManager.getTestReport().log(logStatus, step);
		}
	}
	


	public static void logSuccessedTest() {
		Status a = DriverManager.getTestReport().getStatus();
		switch (DriverManager.getTestReport().getStatus()) {
		case FAIL:
			DriverManager.getTestReport().log(Status.FAIL, " test ends  with error!");
			break;
		case WARNING:
			DriverManager.getTestReport().log(Status.PASS,
					" test ends with success and have one or many warnings!");
			break;
		default:
			DriverManager.getTestReport().log(Status.PASS, " test ends with success!");
			break;
		}
	}
}
