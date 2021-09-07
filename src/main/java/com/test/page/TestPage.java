package com.test.page;

import org.openqa.selenium.support.PageFactory;

import com.test.driver.DriverWeb;
import com.test.manager.DriverManager;

public class TestPage {

    public DriverWeb driver;
    
    public TestPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(this.driver, this);
    }
    
    public void openURL(String URL) {
		driver.get(URL);
    }
    
    public String getTitle() {
    	return driver.getTitle();
    }
    
}
