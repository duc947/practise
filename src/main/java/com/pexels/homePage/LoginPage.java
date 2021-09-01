package com.pexels.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.test.driver.DriverManager;

public class LoginPage {
	
	WebElement input_username = DriverManager.driver.findElement(By.id("user_email"));
	WebElement input_password = DriverManager.driver.findElement(By.id("user_password"));
	WebElement btn_login = DriverManager.driver.findElement(By.cssSelector(".rd__button rd__button--jumbo rd__button--full-width"));
	
	public LoginPage login() {
		input_username.click();
		input_username.sendKeys("AUTO-1606819033728-386-1@yopmail.com");
		input_password.click();
		input_password.sendKeys("P@ssword1");
		btn_login.click();
		return this;
	}
	
	
}
