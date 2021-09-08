package com.test.listener;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

import com.test.data.InjectData;
import com.test.manager.ConfigurationManager;
import com.test.manager.DriverManager;
import com.test.manager.ExecutionManager;
import com.test.report.ExtentReport;
import com.test.utils.JsonParser;

public class TestListener implements ITestListener, ISuiteListener {
	
	@Override
	public void onTestStart(ITestResult result) {
		ITestNGMethod method = result.getMethod();
		DriverManager.start();
		ExtentReport.startTestReport(result);
		DriverManager.intitializeTestInfo(getDataMapperPath(method));
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReport.logTest(result);
		onTestEnd();
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentReport.logTest(result);
		onTestEnd();

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
	
	
	private void onTestEnd() {
		try {
			ExtentReport.endTest();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DriverManager.closeDriver();
		}
	}

	private String getDataMapperPath(ITestNGMethod method) {
		Method m = getMethod(method);
		if (m == null || m.getAnnotation(InjectData.class) == null) {
			return null;
		}
		String jsonFile = m.getAnnotation(InjectData.class).json();
		return jsonFile;
	}
	
	private Method getMethod(ITestNGMethod method) {
		if (!method.isTest()) {
			return null;
		}
		ConstructorOrMethod com = method.getConstructorOrMethod();
		if (com.getMethod() == null) {
			return null;
		}
		return com.getMethod();
	}
}
