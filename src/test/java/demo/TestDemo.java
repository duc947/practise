package demo;

import org.testng.annotations.Test;

import com.test.data.InjectData;
import com.test.page.DemoPage;

public class TestDemo {

	@InjectData(json = "./dataTest/demo/testMethodOne.json")
	@Test
	public void testMethodOne() {
		new DemoPage().openPage().goToLogin();
	}

	@InjectData(json = "./dataTest/demo/testMethodOne.json")
	@Test
	public void testMethodTwo() {
		new DemoPage().openPage();
	}
	
}
