package pexels;

import org.testng.annotations.Test;

import com.pexels.page.HomePage;
import com.pexels.page.LoginPage;

public class TestPexels {
	
	@Test
	public void testMethodOne() {
		new HomePage().openPage().goToLogin();
//		new LoginPage().login();
	}
	

	@Test
	public void testMethodTwo() {
		new HomePage().openPage();
	}
	
}
