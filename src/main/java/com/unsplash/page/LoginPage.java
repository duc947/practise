package com.unsplash.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;
import com.test.page.TestPage;
import com.test.report.ExtentReport;

public class LoginPage extends TestPage {

	@FindBy(id = "user_email")
	private WebElement input_email;

	@FindBy(id = "user_password")
	private WebElement input_password;

	@FindBy(css = "[name='commit']")
	private WebElement btn_login;
	
	public LoginPage() {
		this(false);
	}

	public LoginPage(boolean waitPageLoad) {
		waitUntilReadyState(10);
	}

	public LoginPage login() {
		String user = getData("email");
		String password = getData("password");
		input_email.sendKeys(user);
		ExtentReport.log(Status.INFO, "Input user: " + user);
		input_password.sendKeys(password);
		ExtentReport.log(Status.INFO, "Input password: " + password);
		btn_login.click();
		ExtentReport.log(Status.INFO, "Login with account: " + user + "/" + password);
		return this;
	}
}
