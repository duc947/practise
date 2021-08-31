package com.test.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.test.driver.DriverManager;

public class TestListener implements ITestListener {

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
	}

	@Override
	public void onFinish(ITestContext context) {
	}
}
