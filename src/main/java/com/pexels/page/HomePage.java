package com.pexels.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.test.driver.DriverWeb;
import com.test.manager.DriverManager;
import com.test.report.ExtentReport;

public class HomePage {
	
	DriverWeb driver;
	
	@FindBy(xpath = "//*[@data-track-label='login']")
	private WebElement btn_login;
	
	@FindBy(xpath = "//*[@class='when-not-signed-in hide-when-ham-is-visible']")
	private WebElement btn_menu;

	public HomePage() {
		driver = DriverManager.getDriver();
	}

	public HomePage openPage() {
		driver.get("https://liquipedia.net/");
		String title = driver.getTitle();
		Assert.assertTrue(title.contains(""));
//		Assert.assertTrue(title.contains("Beautiful Free Images & Pictures | Unsplash"));
		System.out.print(title + "\n");
		ExtentReport.log(Status.INFO, "Page title: " + title);
		ExtentReport.log(Status.INFO, driver.getDataMapper().mapData("email"));
		return this;
	}

	public HomePage goToLogin() {
		ExtentReport.log(Status.INFO, "go To Login");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_menu);
//		btn_menu.click();
		btn_login.click();
		return this;
	}


}
