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
	@Test(enabled = true)
	public void testScenarioOne() {
		new HomePage().openPage().goToLoginPage();
		new LoginPage().login();
		new HomePage().selectFirstImage().unlikeSelectedImageIfNeeded().likeSelectedImage().verifySelectedImageIsLiked()
				.unlikeSelectedImageIfNeeded();
	}

	@InjectData(json = "./dataTest/unsplash/testOne.json")
	@Test(enabled = true)
	public void testScenarioTwo() {
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
	public void testScenarioThree() {
		new HomePage().openPage().goToLoginPage();
		new LoginPage().login();
		new HomePage().goToLikeTab();
		new ProfilePage().unlikeAllLikedImg().goToHomePage();
		new HomePage().selectAndLikeRandomImage().goToLikeTab();
		new ProfilePage().verifyNumberLiked().verifyNumberLikedImage().unlikeAllLikedImg();
	}

	@InjectData(json = "./dataTest/unsplash/testOne.json")
	@Test(enabled = true)
	public void testScenarioFour() {
		new HomePage().openPage().goToLoginPage();
		new LoginPage(true).login();
		new HomePage(true).createPrivateCollection().addThenRemoveAnotherImgToCollection().verifyDeleteSuccess()
				.goToCollectionTab();
		new ProfilePage().openSpecificCollection().verifyNumberOfImg();
		new TopHearder().openPersonalMenu().goToProfilePage();
		new ProfilePage().goToCollectionTab().clearAllSpecificCollectionIfNeeded();
	}

	@InjectData(json = "./dataTest/unsplash/testOne.json")
	@Test(enabled = true)
	public void testScenarioFive() {
		new HomePage().openPage().goToLoginPage();
		new LoginPage(true).login();
		new HomePage().selectRandomImage().downloadSelectedImage().verifyDownloadSuccess();
	}
}
