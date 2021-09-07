package com.test.driver;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.test.data.DataMapper;

public class DriverWeb extends RemoteWebDriver implements WebDriver{
	
	private ExtentTest testReport;
	private DataMapper dataMapper;

	public DriverWeb(URL remoteAddress, Capabilities desiredCapabilities) {
		super(remoteAddress, desiredCapabilities);
	}

	public ExtentTest getTestReport() {
		return testReport;
	}

	public void setTestReport(ExtentTest test) {
		this.testReport = test;
	}
	
	public DataMapper getDataMapper() {
		return dataMapper;
	}

	public void setDataMapper(DataMapper dataMapper) {
		this.dataMapper = dataMapper;
		
	}
}
