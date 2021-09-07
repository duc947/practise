package com.test.page;

import com.test.driver.DriverWeb;
import com.test.manager.DriverManager;

public class TestPage {

    public DriverWeb driver;
    
    public TestPage() {
        this.driver = DriverManager.getDriver();
    }
    
    public void openURL(String URL) {
		driver.get(URL);
    }
    
    public String getTitle() {
    	return driver.getTitle();
    }
    
    
}
