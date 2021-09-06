package com.pexels.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.test.driver.DriverWeb;
import com.test.manager.DriverManager;

public class LoginPage {
	
	DriverWeb driver;

	@FindBy(id = "user_email")
	private WebElement input_username;
	
	@FindBy(id = "user_password")
	private WebElement input_password;

	@FindBy(css = ".rd__button rd__button--jumbo rd__button--full-width")
	private WebElement btn_login;
	
	public LoginPage() {
		driver = DriverManager.getDriver();
	}
	
	public LoginPage login() {
		input_username.click();
		input_username.sendKeys("AUTO-1606819033728-386-1@yopmail.com");
		input_password.click();
		input_password.sendKeys("P@ssword1");
		btn_login.click();
		return this;
	}
	
	
}
