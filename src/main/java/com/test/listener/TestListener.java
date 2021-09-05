package com.test.listener;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.JsonParser;
import com.test.driver.DriverManager;

public class TestListener implements ITestListener, ISuiteListener {
	
	@Override
	public void onTestStart(ITestResult result) {
		DriverManager.start();
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		DriverManager.closeDriver();
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		DriverManager.closeDriver();
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		DriverManager.closeDriver();
	}
	
	@Override
	public void onStart(ITestContext context) {
		DriverManager.launchGrid();
	}
	
	@Override
	public void onFinish(ITestContext context) {
		DriverManager.stopGrid();
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
		DriverManager.setConfiguration(config);
	}
}
