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

	@FindBys({ @FindBy(css = "[itemprop=image]"), })
	private List<WebElement> img_itemList;

	@FindBy(xpath = "//*[contains(@class,'ReactModal__Content--after-open')]//button[@title='Like photo']/*")
	private WebElement btn_likeSelectedImage;

	@FindBy(xpath = "//*[contains(@class,'ReactModal__Content--after-open')]/div/button")
	private WebElement btn_closeSelectedImage;

	@FindBy(xpath = "(//*[contains(@class,'ReactModal__Content--after-open')]/div/button)[2]")
	private WebElement btn_closeCollection;

	@FindBy(css = ".ReactModal__Content--after-open button[title='Add to collection']")
	private WebElement btn_addToCollection;

	@FindBy(xpath = "/html/body/div[5]/div/div/div[2]/div/div[3]/div[1]/div/button")
	private WebElement btn_createNewCollection;

	@FindBy(css = "input[name='title']")
	private WebElement input_collectionName;

	@FindBy(css = "input[type='checkbox']")
	private WebElement chkbox_makePrivateCollection;

	@FindBy(xpath = "//*[contains(text(),'Create collection')]")
	private WebElement btn_submitCreateCollection;

	@FindBy(css = "div[data-test='photos-route'] a[title='Download photo']")
	private WebElement btn_downloadImage;

	@FindBy(css = "[title='Choose your download size']")
	private WebElement btn_chooseDownloadSizeImage;

	public static final String LIKED_BUTTON_COLOR = "rgba(17, 17, 17, 1)";
	public static final String LIKE_BUTTON_BACKGROUND_COLOR_1 = "rgba(224, 76, 76, 1)";
	public static final String LIKE_BUTTON_BACKGROUND_COLOR_2 = "rgba(241, 81, 81, 1)";
	public static final String LIKE_BUTTON_BACKGROUND_COLOR_3 = "rgba(226, 86, 86, 1)";

	public HomePage() {
		this(false);
	}

	public HomePage(boolean waitPageLoad) {
		waitUntilReadyState(10);
	}

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
		waitElementToBeClickable(image);
		image.click();
		ExtentReport.log(Status.INFO, "Select an image");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	public HomePage unlikeSelectedImageIfNeeded() {
		if (btn_likeSelectedImage.getCssValue("color").contains(LIKED_BUTTON_COLOR)) {
			btn_likeSelectedImage.click();
			btn_chooseDownloadSizeImage.click();
			Assert.assertTrue(!btn_likeSelectedImage.getCssValue("color").contains(LIKED_BUTTON_COLOR), "Like button unselect unsuccessfully");
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
		String btn_likeBackground = btn_likeSelectedImage.getCssValue("color");
		Assert.assertTrue(btn_likeBackground.contains(LIKED_BUTTON_COLOR),
				"Like button is not selected" + btn_likeBackground);
		ExtentReport.log(Status.INFO, "Like button background is change:" + btn_likeBackground);
		return this;
	}

	public HomePage selectRandomImage() {
		int imgNumber = img_itemList.size();
		int quantity = 1;
		ExtentReport.log(Status.INFO, "Select: " + quantity + " random image from " + imgNumber);
		Set<Integer> imgNumberPicked = getRandomNumberFromRange(0, imgNumber, quantity);
		for (int element : imgNumberPicked) {
			selectImage(img_itemList.get(element));
		}
		return this;
	}

	public HomePage selectAndLikeRandomImage() {
		int imgNumber = img_itemList.size();
		int quantity = Integer.valueOf(getData("numberOfLikedImg"));
		ExtentReport.log(Status.INFO, "Select: " + quantity + " random image from " + imgNumber);
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

	public HomePage addToCollection() {
		waitElementVisibility(btn_addToCollection);
		waitElementToBeClickable(btn_addToCollection);
		btn_addToCollection.click();
		ExtentReport.log(Status.INFO, "Click AddToCollection button");
		return this;
	}

	public HomePage addToSpecificCollection(String collectionName) {
		WebElement lbl_collectionName = getElementByXPATH("//h4[text()='" + collectionName + "']");
		lbl_collectionName.click();
		ExtentReport.log(Status.INFO, "Click AddToCollection button");
		return this;
	}

	public HomePage createPrivateCollection() {
		selectFirstImage();
		addToCollection();
		waitElementToBeClickable(btn_createNewCollection);
		btn_createNewCollection.click();
		input_collectionName.sendKeys(getData("collectionName"));
		chkbox_makePrivateCollection.click();
		btn_submitCreateCollection.click();
		btn_closeCollection.click();
		btn_closeSelectedImage.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	/* Add another image to collection after add 1st image on homepage */
	public HomePage addThenRemoveAnotherImgToCollection() {
		int imgNumber = img_itemList.size();
		Set<Integer> imgNumberPicked = getRandomNumberFromRange(1, imgNumber, 1);
		for (int element : imgNumberPicked) {
			try {
				selectImage(img_itemList.get(element));
				addToCollection();
				addToSpecificCollection(getData("collectionName"));
				Thread.sleep(300);
				btn_closeCollection.click();
				addToCollection();
				addToSpecificCollection(getData("collectionName"));
				Thread.sleep(3000);
				btn_closeCollection.click();
				closeSelectedImage();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this;
	}

	public HomePage goToCollectionTab() {
		String URL = getData("URL") + getData("accountName") + "/collections";
		openURL(URL);
		ExtentReport.log(Status.INFO, "Go to Collection tab: " + getURL());
		return this;
	}

	public HomePage downloadSelectedImage() {
		btn_downloadImage.click();
		ExtentReport.log(Status.INFO, "Click Download button");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	public HomePage verifyDownloadSuccess() {
		verifyNetworkLogContainValue("\"status\":200", getData("requestDownload"));
		ExtentReport.log(Status.INFO, "Download Successful");
		return this;
	}

	public HomePage verifyNetworkLogContainValue(String expectedValue, String request) {
		List<String> networkLog = getNetworkLog(request);
		verifyNetworkLogContainValue(networkLog, expectedValue);
		return this;
	}

	public static void verifyNetworkLogContainValue(List<String> networkLog, String expectedValue) {
		boolean check = false;
		for (String network : networkLog) {
			if (network.contains(expectedValue)) {
				check = true;
				break;
			}
		}
		Assert.assertTrue(check, "Verify that network log contain correct value: [" + expectedValue
				+ "]. If test fails, it can be caused by network log contain incorrect value");
	}

	public HomePage verifyDeleteSuccess() {
		verifyNetworkLogContainValue("\"status\":200", getData("requestDelete"));
		ExtentReport.log(Status.INFO, "Delete Successful");
		return this;
	}
}
