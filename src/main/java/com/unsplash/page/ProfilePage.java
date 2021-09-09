package com.unsplash.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.test.page.TestPage;
import com.test.report.ExtentReport;

public class ProfilePage extends TestPage {

	@FindBy(css = "[data-test='users-route'] a")
	private WebElement lnk_editProfile;

	@FindBy(xpath = "//*[@data-test='users-route']/div[1]/div/div/div/div[2]/div/div[2]/div/div[2]/div/div[2]/a")
	private WebElement lbl_location;

	@FindBy(css = "[data-test='users-route'] > div > div > div > div > div:nth-child(2) > div > div:nth-child(2) > div > div")
	private WebElement lbl_bio;

	@FindBy(css = "[data-test='user-nav-link-likes'] > span > span")
	private WebElement lbl_likeNumber;

	@FindBys({ 
		@FindBy(css = "[data-test='masonry-grid-count-three'] > div"),
		})
	private List<WebElement> img_liked;

	@FindBys({ 
		@FindBy(css = "button[title='Like photo']"),
		})
	private List<WebElement> btn_likePhoto;

	@FindBy(css = "[data-test='user-nav-link-collections']")
	private WebElement btn_collectionTab;

	@FindBys({ 
		@FindBy(xpath = "//*[contains(@href,'/collections/')]/div[2]/div"),
		})
	private List<WebElement> lbl_collectionName;

	@FindBy(xpath = "//*[contains(@data-test,'page-header-title')]/..//button")
	private WebElement btn_editCollection;

	@FindBy(xpath = "//form[@method = 'post']//button[text()='Delete Collection']")
	private WebElement btn_deleteCollection;

	@FindBy(xpath = "//form[@method = 'post']//button[text()='Delete']")
	private WebElement btn_deleteCollectionConfirm;

	@FindBys({ 
		@FindBy(xpath = "//figure/div/div/a/div/div[2]/div/img"),
		})
	private List<WebElement> img_collectionImage;

	@FindBy(css = "a[title='Home — Unsplash']")
	private WebElement btn_logo;
	
	public ProfilePage goToEditProfilePage() {
		lnk_editProfile.click();
		ExtentReport.log(Status.INFO, "Go to Edit Profile page");
		return this;
	}

	public ProfilePage verifyEditedLocation() {
		String location = lbl_location.getText();
		Assert.assertTrue(location.contains(getData("location")), "Edited Location is wrong: " + location);
		ExtentReport.log(Status.INFO, "Edited Location: " + location);
		return this;
	}

	public ProfilePage verifyEditedBio() {
		String bio = lbl_bio.getText();
		Assert.assertTrue(bio.contains(getData("bio")), "Edited Bio is wrong: " + bio);
		ExtentReport.log(Status.INFO, "Edited Bio: " + bio);
		return this;
	}

	public ProfilePage verifyNumberLiked() {
		String numberOfLike = lbl_likeNumber.getText();
		Assert.assertTrue(numberOfLike.contains(getData("numberOfLikedImg")),
				"Number of Like is wrong: " + numberOfLike);
		ExtentReport.log(Status.INFO, "Number of Like: " + numberOfLike);
		return this;
	}

	public ProfilePage verifyNumberLikedImage() {
		int actual = img_liked.size();
		int expected = Integer.valueOf(getData("numberOfLikedImg"));
		Assert.assertEquals(actual, expected, "Number of Liked photo is wrong: " + actual);
		ExtentReport.log(Status.INFO, "Number of Liked photo: " + actual);
		return this;
	}

	public ProfilePage unlikeAllLikedImg() {
		for (int i = 0; i < btn_likePhoto.size(); i++) {
			unlikeLikedImg(i);
		}
		return this;
	}

	public ProfilePage unlikeLikedImg(int ordinal) {
//		btn_likePhoto.get(ordinal).click();
		clickByJS(btn_likePhoto.get(ordinal));
		ExtentReport.log(Status.INFO, "Unlike Liked photo: " + (ordinal + 1));
		return this;
	}

	public ProfilePage goToCollectionTab() {
		btn_collectionTab.click();
		ExtentReport.log(Status.INFO, "Go to Collection tab");
		return this;
	}

	public ProfilePage openSpecificCollection() {
		boolean isHave = false;
		for (int i = 0; i < lbl_collectionName.size(); i++) {
			if (lbl_collectionName.get(i).getText().contains(getData("collectionName"))) {
				lbl_collectionName.get(i).click();
				ExtentReport.log(Status.INFO, "Open Collection: " + getData("collectionName"));
				isHave = true;
				break;
			}
		}
		if (!isHave) {
			ExtentReport.log(Status.INFO, "Do not have any Collection: " + getData("collectionName"));
		}
		return this;
	}

	public ProfilePage editOpenedCollection() {
		btn_editCollection.click();
		ExtentReport.log(Status.INFO, "Edit opened Collection");
		return this;
	}

	public ProfilePage deleteOpenedCollection() {
		btn_deleteCollection.click();
		btn_deleteCollectionConfirm.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentReport.log(Status.INFO, "Delete opened Collection");
		return this;
	}

	public ProfilePage deleteSpecificCollection() {
		for (int j = 0; j < lbl_collectionName.size(); j++) {
			if (lbl_collectionName.get(j).getText().contains(getData("collectionName"))) {
				openSpecificCollection();
				editOpenedCollection();
				deleteOpenedCollection();
				break;
			}
		}
		return this;
	}

	public ProfilePage clearAllSpecificCollectionIfNeeded() {
		int numberOfSpecificCollection = getNumberOfCollection();
		for (int i = 0; i < numberOfSpecificCollection; i++) {
			deleteSpecificCollection();
		}
		return this;
	}

	public int getNumberOfCollection() {
		int result = 0;
		for (int i = 0; i < lbl_collectionName.size(); i++) {
			if (lbl_collectionName.get(i).getText().contains(getData("collectionName"))) {
				result++;
			}
		}
		return result;
	}

	public ProfilePage verifyNumberOfImg() {
		int actual = img_collectionImage.size();
		int expected = Integer.valueOf(getData("numberOfCollectionImg"));
		Assert.assertEquals(actual, expected, "Number of photo in Collection is wrong: " + actual);
		ExtentReport.log(Status.INFO, "Number of photo in Collection: " + actual);
		return this;
	}

	public ProfilePage goToHomePage() {
		btn_logo.click();
		ExtentReport.log(Status.INFO, "Go to Home Page");
		return this;
	}
}
