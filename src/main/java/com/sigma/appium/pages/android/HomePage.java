package com.sigma.appium.pages.android;

import org.openqa.selenium.By;

import com.sigma.appium.android.BasePage;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class HomePage extends BasePage {

	private By chromeIcon = MobileBy.id("buttonStartWebview");

	public HomePage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	public boolean clickChromeIcon() {
		return pixelPerfectClick(chromeIcon);
	}

	public boolean isChromeIconPresent() {
		return waitToAppear(chromeIcon, 4);
	}
}
