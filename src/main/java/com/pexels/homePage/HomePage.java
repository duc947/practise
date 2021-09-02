package com.pexels.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.test.driver.DriverManager;

public class HomePage {
	
	WebDriver driver = DriverManager.getWebDriver();

	WebElement btn_menu;
	WebElement btn_login;

	public HomePage() {
		driver.get("https://unsplash.com/");
		btn_menu = driver.findElement(By.xpath("//*[@class='when-not-signed-in hide-when-ham-is-visible']"));
	}

	public HomePage openPage() {
//		driver.navigate().refresh();
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Free Stock Photos · Pexels"));
		return this;
	}

	public HomePage goToLogin() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn_menu);
//		btn_menu.click();
		btn_login = driver.findElement(By.xpath("//*[@data-track-label='login']"));
		btn_login.click();
		return this;
	}
	
	

}
