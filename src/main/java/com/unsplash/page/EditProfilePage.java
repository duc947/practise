package com.unsplash.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;
import com.test.page.TestPage;
import com.test.report.ExtentReport;

public class EditProfilePage extends TestPage {

	@FindBy(css = ".upload-circular-container.profile-image-container.js-general-uploader-pseudo-file-field")
	private WebElement lnk_changeProfileImage;

	@FindBy(id = "user_location")
	private WebElement input_location;

	@FindBy(id = "user_bio")
	private WebElement input_bio;

	@FindBy(css = ".btn.btn-default")
	private WebElement btn_update;

	@FindBy(css = ".flash__close.link--no-underline.js-close-flash")
	private WebElement btn_closeComfirmBanner;

	public EditProfilePage changeProfileImage() {
		String filePath = System.getProperty("user.dir") + getData("imgPath");
		uploadImage(filePath);
		return this;
	}

	public EditProfilePage uploadImage(String filePath) {
//		lnk_changeProfileImage.sendKeys(filePath);
		lnk_changeProfileImage.click();

		Robot rb;
		try {
			rb = new Robot();
			StringSelection str = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

			lnk_changeProfileImage.click();
			try {
				Thread.sleep(500);
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_V);
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_V);
				Thread.sleep(1000);
				rb.keyPress(KeyEvent.VK_ALT);
				rb.keyPress(KeyEvent.VK_O);
				rb.keyRelease(KeyEvent.VK_ALT);
				rb.keyRelease(KeyEvent.VK_O);
				Thread.sleep(500);
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_V);
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_V);
				Thread.sleep(1000);
				rb.keyPress(KeyEvent.VK_ALT);
				rb.keyPress(KeyEvent.VK_O);
				rb.keyRelease(KeyEvent.VK_ALT);
				rb.keyRelease(KeyEvent.VK_O);
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentReport.log(Status.INFO, "Upload image: " + filePath);
		return this;
	}

	public EditProfilePage editLocation() {
		input_location.sendKeys(getData("location"));
		ExtentReport.log(Status.INFO, "Edit location");
		return this;
	}

	public EditProfilePage editBio() {
		input_bio.sendKeys(getData("bio"));
		ExtentReport.log(Status.INFO, "Edit bio");
		return this;
	}

	public EditProfilePage submitUpdate() {
		btn_update.click();
		ExtentReport.log(Status.INFO, "Submit Update");
		if (btn_closeComfirmBanner.isDisplayed()) {
			btn_closeComfirmBanner.click();
			ExtentReport.log(Status.INFO, "Close comfirm banner");
		}
		return this;
	}

	public EditProfilePage rollbackData() {
		ExtentReport.log(Status.INFO, "Rollback data for next time");
		String filePath = System.getProperty("user.dir") + getData("imgPathRollback");
		uploadImage(filePath);
		input_location.clear();
		ExtentReport.log(Status.INFO, "Clear location");
		input_bio.clear();
		ExtentReport.log(Status.INFO, "Clear bio");
		submitUpdate();
		return this;
	}
}
