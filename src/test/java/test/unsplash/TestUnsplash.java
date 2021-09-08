package test.unsplash;

import org.testng.annotations.Test;

import com.test.data.InjectData;
import com.unsplash.page.EditProfilePage;
import com.unsplash.page.HomePage;
import com.unsplash.page.LoginPage;
import com.unsplash.page.ProfilePage;
import com.unsplash.page.TopHearder;

public class TestUnsplash {

	@InjectData(json = "./dataTest/unsplash/testOne.json")
	@Test(enabled = false)
	public void testOne() {
		new HomePage().openPage().goToLoginPage();
		new LoginPage().login();
		new HomePage().selectFirstImage().unlikeSelectedImageIfNeeded().likeSelectedImage()
				.verifySelectedImageIsLiked().unlikeSelectedImageIfNeeded();
	}

	@InjectData(json = "./dataTest/unsplash/testOne.json")
	@Test(enabled = false)
	public void testTwo() {
		new HomePage().openPage().goToLoginPage();
		new LoginPage().login();
		new TopHearder().openPersonalMenu().goToProfilePage();
		new ProfilePage().goToEditProfilePage();
		new EditProfilePage().changeProfileImage().editLocation().editBio().submitUpdate();
		new TopHearder().openPersonalMenu().goToProfilePage();
		new ProfilePage().verifyEditedLocation().verifyEditedBio();
		new ProfilePage().goToEditProfilePage();
		new EditProfilePage().rollbackData().submitUpdate();
	}

	@InjectData(json = "./dataTest/unsplash/testOne.json")
	@Test(enabled = true)
	public void testThree() {
		new HomePage().openPage().goToLoginPage();
		new LoginPage().login();
		new HomePage().selectAndLikeRandomImage().goToLikeTab();
		new ProfilePage().verifyNumberLiked().verifyNumberLikedImage().unlikeAllLikedImg();
	}
}
