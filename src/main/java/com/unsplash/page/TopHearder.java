package com.unsplash.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;
import com.test.page.TestPage;
import com.test.report.ExtentReport;

public class TopHearder extends TestPage {

	@FindBy(xpath = "//*[@id='popover-avatar-loggedin-menu-desktop']//button[@role='button'] | //*[contains(@class,'dropdown')]/a")
	private WebElement btn_personalMenu;

	@FindBy(xpath = "//*[@href='/@" + "duc5553333" + "']")
	private WebElement lnk_profile;

	public TopHearder openPersonalMenu() {
		if (btn_personalMenu.isDisplayed()) {
			waitElementToBeClickable(btn_personalMenu);
			btn_personalMenu.click();
			impliWait(5);
			if (btn_personalMenu.getAttribute("aria-expanded").equals("false")) {
				btn_personalMenu.click();
				ExtentReport.log(Status.INFO, "Open personal menu");
			} else {
				ExtentReport.log(Status.INFO, "Personal menu already opened");
			}
		} else {
			ExtentReport.log(Status.WARNING, "Not logged in yet");
		}
		return this;
	}

	public TopHearder goToProfilePage() {
		lnk_profile.click();
		ExtentReport.log(Status.INFO, "Go to Profile page");
		return this;
	}
}
