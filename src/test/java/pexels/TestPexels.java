package pexels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestPexels {
	public static WebDriver driver;

	@Test
	public void testDemo() {
		driver.get("http://automationpractice.com/index.php/");
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("My Store"));
		System.out.print("Demo Test Jenkins");
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/data/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
