package com.sigma.appium.tests.android;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.sigma.appium.core.DriversFactory;
import com.sigma.appium.core.Server;
import com.sigma.appium.utils.Platforms;

import io.appium.java_client.remote.MobileCapabilityType;

public class TestPrimer {

	private DesiredCapabilities caps;
	private Server server;

	@BeforeSuite
	public void setup() {
		System.out.println("\n");
		System.out.println("BEGINNING OF TEST-SUITE --------------------------------------");
		caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		caps.setCapability(MobileCapabilityType.NO_RESET, false);
		// Path to AUT
		caps.setCapability(MobileCapabilityType.APP, "C:\\appium_files\\selendroid-test-app-0.17.0.apk");
		// Default as null will use the local Appium default url with IP address and
		// port. You may use a string for concrete full qualified URI
		server = new Server(null);
		// Explicitly creating an Android driver in this case
		// Use Platforms.IOS to create IOS test driver.
		DriversFactory.createDriver(Platforms.ANDROID, server, caps);
	}

	@AfterSuite
	public void tearDown() {
		System.out.println("\n");
		System.out.println("END OF TEST-SUITE --------------------------------------");
		DriversFactory.getAndroidDriver().quit();
		caps = null;
		server = null;
	}

}
