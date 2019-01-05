package com.sigma.appium.android;

import org.testng.annotations.BeforeClass;

import com.sigma.appium.core.DriversFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseTest {

	protected AndroidDriver<AndroidElement> driver;

	@BeforeClass
	public void init() {
		driver = DriversFactory.getAndroidDriver();
	}

	// For setting up test cases prerequisites
	protected void setup() {
	};

	// For checking if the page under test is loaded
	protected void isPageLoadedTest() {
	};

	// For checking if the page under test is unloaded
	protected void isPageUnloadedTest() {
	};

	// For tearing down test cases and doing some in-house cleaning if needed
	protected void tearDown() {
	};

}
