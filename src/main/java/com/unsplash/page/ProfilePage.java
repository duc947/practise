package com.unsplash.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

	public ProfilePage goToEditProfilePage() {
		lnk_editProfile.click();
		ExtentReport.log(Status.INFO, "Go to Edit Profile page");
		return this;
	}

	public ProfilePage verifyEditedLocation() {
		String location = lbl_location.getText();
		Assert.assertTrue(location.contains(getData("location")));
		return this;
	}

	public ProfilePage verifyEditedBio() {
		String bio = lbl_bio.getText();
		Assert.assertTrue(bio.contains(getData("bio")));
		return this;
	}
}
