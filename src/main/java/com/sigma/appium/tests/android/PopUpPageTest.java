package com.sigma.appium.tests.android;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sigma.appium.android.BaseTest;
import com.sigma.appium.pages.android.PopUpPage;


/*
 * Test case for manipulating the pop up window with the dismiss button. 
 * */

public class PopUpPageTest extends BaseTest {

	// Reference to the Pop up page, a sub-pager of the home page
	PopUpPage page;

	@BeforeClass
	public void setup() {
		page = new PopUpPage(driver);
		System.out.println("\n");
		System.out.println("POP UP PAGE TESTS --------------------------------------");
	}

	@Test(priority = 9)
	public void returnToHomePageTest() {
		Assert.assertTrue(page.returnToHomePage(), "Failed to return to home page!");
	}

	@Test(priority = 10)
	public void isPageLoadedTest() {
		Assert.assertTrue(page.getChromeIcon().isDisplayed(), "Failed to load home page!");
	}

	@Test(priority = 11)
	public void getPopUpWindowTextTest() {
		boolean isTitleFound = page.getPopUpWindowText().equals("It's a PopupWindow");
		Assert.assertTrue(isTitleFound, "Failed to read pop up window text!");
	}

	@Test(priority = 12)
	public void dismissPopUpWindowTest() {
		Assert.assertTrue(page.dismissPopUpWindow(), "Failed to dismiss pop up window!");
	}

	@Test(priority = 13)
	public void clickThrowExceptionButton() {
		try {
			Assert.assertTrue(page.clickThrowExceptionButton(),
					"Expected to faild. Exception thrown by clicking the exception button!");
		} catch (Exception e) {
			System.out.println(
					"Clikcing on 'Throw Exception Button' caused a run time exception, exception message from stack trace:"
							+ e.getMessage());
		}
	}

	@Test(priority = 14)
	public void isPageUnloadedTest() {
		// Intentionally left empty, by now the app is closed
	}

	@AfterClass
	public void tearDown() {
		page = null;
		System.out.println(" ");
	}

}
