package com.test.listener;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.test.manager.ConfigurationManager;
import com.test.manager.DriverManager;
import com.test.manager.ExecutionManager;
import com.test.report.ExtentReport;
import com.test.utils.JsonParser;

public class TestListener implements ITestListener, ISuiteListener {
	
	@Override
	public void onTestStart(ITestResult result) {
		DriverManager.start();
		ExtentReport.startTestReport(result);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReport.logTest(result);
		try {
			onTestEnd(result, true);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentReport.logTest(result);
		try {

			onTestEnd(result, false);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		DriverManager.closeDriver();
	}
	
	@Override
	public void onStart(ITestContext context) {
		ExecutionManager.launchGrid();
	}
	
	@Override
	public void onFinish(ITestContext context) {
		ExecutionManager.stopGrid();
	}
	
	@Override
	public void onStart(ISuite suite) {
		String configFilePath = suite.getXmlSuite().getAllParameters()
				.get(ConfigurationManager.CONFIG_FILE_TESTNG_PARAM_NAME);
		ConfigurationManager config = null;
		try {
			config =  new JsonParser<ConfigurationManager>().parse(configFilePath, ConfigurationManager.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(ConfigurationManager.JSON_MAPPING_ERROR + configFilePath);
			e.printStackTrace();
		}
		ExecutionManager.setConfiguration(config);
		ExecutionManager.setReport(ExtentReport.createReport(suite.getName()));
	}
	
	protected void afterTest(ITestResult testResult, boolean isSuccess) {
	}
	
	private void onTestEnd(ITestResult testResult, boolean isSuccess) throws InterruptedException, IOException {
		try {
			afterTest(testResult, isSuccess);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ExtentReport.endTest();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DriverManager.closeDriver();
			}
		}

	}
}
