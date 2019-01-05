package com.sigma.appium.tests.android;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sigma.appium.android.BaseTest;
import com.sigma.appium.pages.android.HomePage;

/*
 * Test case for clicking Chrome icon in home page. 
 * */

public class HomePageTest extends BaseTest {

	// Reference to the home page
	HomePage page;

	@BeforeClass
	public void setup() {
		page = new HomePage(driver);
		System.out.println("\n");
		System.out.println("HOME PAGE TESTS ------------------------------");
	}

	@Test(priority = 0)
	public void isPageLoadedTest() {
		Assert.assertTrue(page.isChromeIconPresent(), "Failed to load home page!");
	}

	@Test(priority = 1)
	public void clickChromeIconTest() {
		Assert.assertTrue(page.clickChromeIcon(), "Failed to click Chrome icon!");
	}

	@Test(priority = 2)
	public void isPageUnloadedTest() {
		Assert.assertTrue(!page.isChromeIconPresent(), "Failed to unload Home page!");
	}

	@AfterClass
	public void tearDown() {
		page = null;
		System.out.println(" ");
	}

}
