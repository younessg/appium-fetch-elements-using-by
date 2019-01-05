package com.sigma.appium.pages.android;

import org.openqa.selenium.By;

import com.sigma.appium.android.BasePage;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/*
 * The pop up page, supposed to handle the modal dialog with the dismiss button and check-box. 
 * */

public class PopUpPage extends BasePage {

	private By chromeIcon = MobileBy.id("io.selendroid.testapp:id/buttonStartWebview");

	public AndroidElement getChromeIcon() {
		return getMobileElement(chromeIcon);
	}

	private By displayPopUpWindowList = MobileBy.AccessibilityId("showPopupWindowButtonCD");

	public AndroidElement displayPopUpBtn;

	public By throwExceptionBtn = MobileBy.id("//android.widget.Button[@content-desc=\"exceptionTestButtonCD\"]");

	public PopUpPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	public boolean returnToHomePage() {
		boolean isHomePage = false;
		driver.navigate().back();
		if (waitToAppear(chromeIcon, 3)) {
			if (getMobileElement(chromeIcon).isDisplayed())
				isHomePage = true;
		}
		return isHomePage;
	}

	public String getPopUpWindowText() {
		System.out.println("\n");
		String pageSource = driver.getPageSource();
		System.out.println(pageSource);
		System.out.println("\n");
		
		String popUpText = null;
		// Click button to show modal dialog with the dismiss button
		displayPopUpBtn = getMobileElement(displayPopUpWindowList);
		displayPopUpBtn.click();
		// Fetch the text in the modal dialog
		By element = MobileBy.AndroidUIAutomator("It's a PopupWindow");
		if (waitToAppear(element, 2)) {
			popUpText = getMobileElement(element).getText();
			// popUpText = driver.findElementByAndroidUIAutomator("new
			// UiSelector().text(\"It's a PopupWindow\")").getText();
			System.out.println("Pop up window text:" + popUpText);
		}
		return popUpText;
	}

	public boolean dismissPopUpWindow() {
		boolean isDismissed = false;
		By dismissBtnRef = MobileBy.xpath("//android.widget.Button[@text='Dismiss']");
		AndroidElement dismissBtn = getMobileElement(dismissBtnRef);
		dismissBtn.click();
		if (waitToDisappear(dismissBtnRef, 2)) {
			if (!dismissBtn.isDisplayed())
				isDismissed = true;
		}
		return isDismissed;
	}

	public boolean clickThrowExceptionButton() {
		boolean isClicked = false;
		try {
			getMobileElement(throwExceptionBtn).click();
			isClicked = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isClicked;
	}

}
