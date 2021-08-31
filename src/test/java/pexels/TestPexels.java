package pexels;

import org.testng.annotations.Test;

import com.pexels.homePage.HomePage;

public class TestPexels {

	@Test
	public void testDemo() {
		new HomePage().openPage().goToLogin();
	}
}
