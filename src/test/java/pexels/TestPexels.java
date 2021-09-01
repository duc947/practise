package pexels;

import org.testng.annotations.Test;

import com.pexels.homePage.HomePage;
import com.pexels.homePage.LoginPage;

public class TestPexels {
	
	@Test
	public void testDemo() {
		new HomePage().openPage().goToLogin();
		new LoginPage().login();
	}
}
