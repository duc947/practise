package com.test.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.test.report.ExtentReport;

public class DemoPage extends TestPage {
	
	@FindBy(css = "[href='a/dota2/Main_Page']")
	private WebElement btn_login;
	
	public DemoPage openPage() {
		openURL("https://liquipedia.net/");
		String title = getTitle();
		Assert.assertTrue(title.contains(""));
//		Assert.assertTrue(title.contains("Beautiful Free Images & Pictures | Unsplash"));
		System.out.print(title + "\n");
		ExtentReport.log(Status.INFO, "Page title: " + title);
		ExtentReport.log(Status.INFO, driver.getDataMapper().mapData("email"));
		return this;
		
	}

	public DemoPage goToLogin() {
		ExtentReport.log(Status.INFO, "go To Login");
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", btn_menu);
//		btn_menu.click();
		btn_login.click();
		return this;
	}


}
