package com.sigma.appium.pages.android;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sigma.appium.android.BasePage;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CarSelectionPage extends BasePage {

	private By webViewText = MobileBy.xpath("//android.widget.TextView[@text='Web View Interaction' and @index='0']");

	private By userNameTextInputView = MobileBy.id("name_input");

	private By carOptionsList = MobileBy.xpath("//android.widget.Spinner[@index='2']");

	private By sendUserNameBtn = MobileBy.xpath("//android.widget.Button[@index='6']");

	private By sayingHelloTextView = MobileBy
			.xpath("//android.view.View[@content-desc=\"This is my way of saying hello\"]");

	private String userName;

	private String selectedCar;

	public CarSelectionPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	public boolean isTitleDisplayed() {
		boolean isTitleDisplayed = false;
		if (waitToAppear(webViewText, 1)) {
			isTitleDisplayed = true;
		}
		return isTitleDisplayed;
	}

	public boolean enterUserName(String name) {
		userName = name;
		AndroidElement mElement = (AndroidElement) getMobileElement(userNameTextInputView);
		// mElement.click();
		mElement.clear();
		System.out.println("Keying in user name takes time!");
		mElement.sendKeys(name);
		WebDriverWait wait = new WebDriverWait(driver, 3);
		boolean result = wait.until(ExpectedConditions.textToBePresentInElementLocated(userNameTextInputView, name));
		
		System.out.println("\n");
		System.out.println("Waited for name entry = " + result);
		System.out.println("Finished keying in the user name!");
		System.out.println("\n");
		
		driver.hideKeyboard();
		return result;
	}

	public String performCarSelection(String car) {
		pixelPerfectClick(carOptionsList);
		By carOption = MobileBy.xpath("//android.widget.CheckedTextView[@text='" + car + "' and @index='2']");
		if (waitToAppear(carOption, 3)) {
			pixelPerfectClick(carOption);
		}
		if (waitToAppear(carOptionsList, 3))
			selectedCar = getMobileElement(carOptionsList).getAttribute("name");
		return selectedCar;
	}

	public boolean clickSendNameButton() {
		return pixelPerfectClick(sendUserNameBtn);
	}

	public boolean isSummaryScreenShown() {
		return getMobileElement(sayingHelloTextView).isDisplayed();
	}

	public boolean isUserDataShown() {
		boolean isDataShown = false;
		// Checking if user name is displayed in "say hello" screen
		String path = "//*/android.webkit.WebView/android.view.View[4]";
		AndroidElement keyedName = fetchXpathUntilFound(path, 4);
		System.out.println("keyedName:" + keyedName.getAttribute("name"));
		String adjustedUserName = keyedName.getAttribute("name").replaceAll("\"", "").toLowerCase().trim();
		// Checking if car is displayed in "say hello" screen
		path = "//*/android.webkit.WebView/android.view.View[6]";
		AndroidElement chosenCar = fetchXpathUntilFound(path, 4);
		System.out.println("chosenCar:" + chosenCar.getAttribute("name"));
		String adjustedCarName = chosenCar.getAttribute("name").replaceAll("\"", "").toLowerCase().trim();
		if (adjustedUserName.equals(userName.toLowerCase().trim())
				&& adjustedCarName.equals(selectedCar.toLowerCase().trim()))
			isDataShown = true;
		return isDataShown;
	}

}
