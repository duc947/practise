package com.test.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.driver.DriverWeb;
import com.test.manager.DriverManager;

public class TestPage {

    public DriverWeb driver;
    public WebDriverWait wait;
    
    public TestPage() {
        this.driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(this.driver, this);
    }
    
    public void openURL(String URL) {
		driver.get(URL);
    }
    
    public String getTitle() {
    	return driver.getTitle();
    }
    
    public String getURL() {
    	return driver.getCurrentUrl();
    }

    public String getData(String key) {
    	return driver.getDataMapper().mapData(key);
    }
    
    public void impliWait(long time) {
       driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }
    
    public WebElement waitElementVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public WebElement waitElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public void clickByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
    }
    
    public WebElement getElementByXPATH(String key) {
    	return driver.findElement(By.xpath(key));
    }
	
	public void waitUntilReadyState(int timeCount) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		do {
			timeCount--;
			impliWait(1);
		} while (!js.executeScript("return document.readyState").equals("complete") && timeCount > 0);
	}
    
}
