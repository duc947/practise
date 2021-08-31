package com.pexels.homePage;

import org.testng.Assert;

import com.test.driver.DriverManager;

public class HomePage {

	public HomePage openPage() {
		DriverManager.driver.get("https://www.pexels.com/");
		String title = DriverManager.driver.getTitle();
		Assert.assertTrue(title.contains("Free Stock Photos · Pexels"));
		return this;
	}

	public HomePage goToLogin() {
		// TODO Auto-generated method stub
		return this;
	}
}
