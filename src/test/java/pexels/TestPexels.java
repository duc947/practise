package pexels;

import org.testng.annotations.Test;

import com.pexels.page.HomePage;
import com.pexels.page.LoginPage;
import com.test.data.InjectData;

public class TestPexels {

	@InjectData(json = "./dataTest/testMethodOne.json")
	@Test
	public void testMethodOne() {
		new HomePage().openPage().goToLogin();
//		new LoginPage().login();
	}
	
//
//	@Test
//	public void testMethodTwo() {
//		new HomePage().openPage();
//	}
	
}
