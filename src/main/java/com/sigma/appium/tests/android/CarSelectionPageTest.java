package com.sigma.appium.tests.android;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sigma.appium.android.BaseTest;
import com.sigma.appium.pages.android.CarSelectionPage;

/*
 * Test case for user name entry, car selection and submitting the keyed-in data. 
 * */

public class CarSelectionPageTest extends BaseTest {

	// Reference to the car selection page
	CarSelectionPage page;

	@BeforeClass
	public void setup() {
		page = new CarSelectionPage(driver);
		System.out.println("\n");
		System.out.println("NAME ENTRY AND CAR SELECTION PAGE TESTS ------------------------------");
	}

	@Test(priority = 3)
	public void isPageLoadedTest() {
		Assert.assertTrue(page.isTitleDisplayed(), "Failed to load car selection page!");
	}

	@Test(priority = 4)
	public void enterUserNameTest() {
		String name = "Someone";
		boolean isUserName = page.enterUserName(name);
		Assert.assertTrue(isUserName, "Failed to key-in user name!");
	}

	@Test(priority = 5)
	public void performCarSelectionTest() {
		String car = "Audi";
		String expected = null;
		expected = page.performCarSelection(car);
		Assert.assertTrue(expected.equals(car), "Failed to select a car!");
	}

	@Test(priority = 6)
	public void clickSendNameButtonTest() {
		Assert.assertTrue(page.clickSendNameButton(), "Failed to click send user name button!");
	}

	@Test(priority = 7)
	public void isUserDataShownTest() {
		Assert.assertTrue(page.isUserDataShown(), "Failed to verify user name and selected car!");
	}

	@Test(priority = 8)
	public void isPageUnloadedTest() {
		Assert.assertTrue(page.isSummaryScreenShown(), "Page failed to unload user name and car selection page!");
	}

	@AfterClass
	public void tearDown() {
		page = null;
		System.out.println(" ");
	}

}
