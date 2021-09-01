package com.pexels.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.test.driver.DriverManager;

public class HomePage {
	
//	WebDriver driver = DriverManager.getWebDriver();
	
	WebElement btn_menu;
	WebElement btn_login;

	public HomePage() {
		DriverManager.driver.get("https://www.pexels.com/");
		btn_menu = DriverManager.driver.findElement(By.xpath("//*[@class='when-not-signed-in hide-when-ham-is-visible']"));
	}

	public HomePage openPage() {
		String title = DriverManager.driver.getTitle();
		Assert.assertTrue(title.contains("Free Stock Photos · Pexels"));
		return this;
	}

	public HomePage goToLogin() {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.driver;
		js.executeScript("arguments[0].click();", btn_menu);
//		btn_menu.click();
		btn_login = DriverManager.driver.findElement(By.xpath("//*[@data-track-label='login']"));
		btn_login.click();
		return this;
	}
	
	

}
