package com.unsplash.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.test.page.TestPage;
import com.test.report.ExtentReport;

public class HomePage extends TestPage {

	@FindBy(css = "[href='/login']")
	private WebElement btn_login;

	@FindBys({ @FindBy(css = "[itemprop=image]"), })
	private List<WebElement> img_itemList;

	@FindBy(xpath = "//*[contains(@class,'ReactModal__Content--after-open')]//button[@title='Like photo']")
	private WebElement btn_likeSelectedImage;

	public static final String LIKE_BUTTON_BACKGROUND_COLOR_SELECTED = "rgba(224, 76, 76, 1)";
	public static final String LIKE_BUTTON_SELECTED_BACKGROUND_COLOR = "rgba(241, 81, 81, 1)";

	public HomePage openPage() {
		openURL("https://unsplash.com/");
		String title = getTitle();
		Assert.assertTrue(title.contains("Beautiful Free Images & Pictures | Unsplash"));
		ExtentReport.log(Status.INFO, "Page title: " + title);
		return this;
	}

	public HomePage goToLogin() {
		btn_login.click();
		ExtentReport.log(Status.INFO, "go To Login");
		return this;
	}

	public HomePage selectFirstImage() {
		selectImage(img_itemList.get(0));
		ExtentReport.log(Status.INFO, "Select 1st image");
		return this;
	}

	public HomePage selectImage(WebElement image) {
		image.click();
		ExtentReport.log(Status.INFO, "Select an image");
		return this;
	}

	public HomePage likeSelectedImage() {
		String btn_likeBackground = btn_likeSelectedImage.getCssValue("background-color");
		if (btn_likeBackground.contains(LIKE_BUTTON_BACKGROUND_COLOR_SELECTED)
				|| btn_likeBackground.contains(LIKE_BUTTON_SELECTED_BACKGROUND_COLOR)) {
			btn_likeSelectedImage.click();
			Assert.assertTrue(
					!btn_likeBackground.contains(LIKE_BUTTON_BACKGROUND_COLOR_SELECTED)
							|| !btn_likeBackground.contains(LIKE_BUTTON_SELECTED_BACKGROUND_COLOR),
					"Like button unselect unsuccessfully");
		}
		impliWait(3);
		btn_likeSelectedImage.click();
		ExtentReport.log(Status.INFO, "Like selected image");
		return this;
	}

	public HomePage verifySelectedImageIsLiked() {
		String btn_likeBackground = btn_likeSelectedImage.getCssValue("background-color");
		Assert.assertTrue(btn_likeBackground.contains(LIKE_BUTTON_BACKGROUND_COLOR_SELECTED),
				"Like button is not selected");
		ExtentReport.log(Status.INFO, "Like button background is change");
		return this;
	}
}
