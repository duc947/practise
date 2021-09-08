package com.unsplash.page;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

	@FindBys({ 
		@FindBy(css = "[itemprop=image]"), 
		})
	private List<WebElement> img_itemList;

	@FindBy(xpath = "//*[contains(@class,'ReactModal__Content--after-open')]//button[@title='Like photo']")
	private WebElement btn_likeSelectedImage;

	@FindBy(xpath = "//*[contains(@class,'ReactModal__Content--after-open')]//button")
	private WebElement btn_closeSelectedImage;

	public static final String LIKE_BUTTON_BACKGROUND_COLOR_SELECTED = "rgba(224, 76, 76, 1)";
	public static final String LIKE_BUTTON_SELECTED_BACKGROUND_COLOR = "rgba(241, 81, 81, 1)";

	public HomePage openPage() {
		openURL(getData("URL"));
		String title = getTitle();
		Assert.assertTrue(title.contains("Beautiful Free Images & Pictures | Unsplash"));
		ExtentReport.log(Status.INFO, "Page title: " + title);
		return this;
	}

	public HomePage goToLoginPage() {
		btn_login.click();
		ExtentReport.log(Status.INFO, "Go to Login page");
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

	public HomePage unlikeSelectedImageIfNeeded() {
		String btn_likeBackground = btn_likeSelectedImage.getCssValue("background-color");
		if (btn_likeBackground.contains(LIKE_BUTTON_BACKGROUND_COLOR_SELECTED)
				|| btn_likeBackground.contains(LIKE_BUTTON_SELECTED_BACKGROUND_COLOR)) {
			btn_likeSelectedImage.click();
			Assert.assertTrue(
					!btn_likeBackground.contains(LIKE_BUTTON_BACKGROUND_COLOR_SELECTED)
							|| !btn_likeBackground.contains(LIKE_BUTTON_SELECTED_BACKGROUND_COLOR),
					"Like button unselect unsuccessfully");
		}
		ExtentReport.log(Status.INFO, "Unike selected image If needed");
		return this;
	}

	public HomePage likeSelectedImage() {
		btn_likeSelectedImage.click();
		ExtentReport.log(Status.INFO, "Like selected image");
		return this;
	}

	public HomePage closeSelectedImage() {
		btn_closeSelectedImage.click();
		ExtentReport.log(Status.INFO, "Close selected image");
		return this;
	}

	public HomePage verifySelectedImageIsLiked() {
		String btn_likeBackground = btn_likeSelectedImage.getCssValue("background-color");
		Assert.assertTrue(btn_likeBackground.contains(LIKE_BUTTON_BACKGROUND_COLOR_SELECTED),
				"Like button is not selected");
		ExtentReport.log(Status.INFO, "Like button background is change");
		return this;
	}

	public HomePage selectAndLikeRandomImage() {
		int imgNumber = img_itemList.size();
		int quantity = Integer.valueOf(getData("numberOfLikedImg"));
		Set<Integer> imgNumberPicked = getRandomNumberFromRange(0, imgNumber, quantity);
		for (int element : imgNumberPicked) {
			selectImage(img_itemList.get(element));
			likeSelectedImage();
			closeSelectedImage();
		}
		return this;
	}

	public Set<Integer> getRandomNumberFromRange(int min, int max, int quantity) {
		Set<Integer> result = new HashSet<Integer>();
		int temp;
		while (result.size() < quantity) {
			Random random = new Random();
			temp = random.nextInt(max - min) + min;
			result.add(temp);
		}
		return result;
	}

	public HomePage goToLikeTab() {
		String URL = getData("URL") + getData("accountName") + "/likes";
		openURL(URL);
		ExtentReport.log(Status.INFO, "Go to Like tab: " + getURL());
		return this;
	}
}
