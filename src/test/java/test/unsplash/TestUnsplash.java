package test.unsplash;

import org.testng.annotations.Test;

import com.test.data.InjectData;
import com.unsplash.page.HomePage;
import com.unsplash.page.LoginPage;

public class TestUnsplash {
	
	@InjectData(json = "./dataTest/unsplash/testOne.json")
	@Test
	public void testOne() {
		new HomePage().openPage().goToLogin();
		new LoginPage().login();
		new HomePage().selectFirstImage().likeSelectedImage().verifySelectedImageIsLiked();
	}
}
