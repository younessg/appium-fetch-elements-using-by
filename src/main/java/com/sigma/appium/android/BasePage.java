package com.sigma.appium.android;

import java.time.Duration;
import java.util.List;

import org.apache.tools.ant.util.SymbolicLinkUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sigma.appium.utils.SwipeDirection;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {

	@SuppressWarnings("rawtypes")
	protected TouchAction touchAction;
	protected WebDriverWait wait;
	protected int pixelClickDelay = 200;
	protected Dimension dimension;
	protected AndroidDriver<AndroidElement> driver;

	@SuppressWarnings("rawtypes")
	public BasePage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		touchAction = new TouchAction(driver);
		dimension = driver.manage().window().getSize();
	}

	protected AndroidElement fetchXpathUntilFound(String xpath, int duration) {
		AndroidElement element = null;
		long startTime = System.currentTimeMillis();
		while (listItem(xpath).size() == 0) {
			if (System.currentTimeMillis() - startTime >= duration * 1000) {
				System.out.println("Element can't be found...");
				break;
			}
		}
		System.out.println("\n");
		System.out.println("listItem(xpath).size() = " + listItem(xpath).size());
		System.out.println(listItem(xpath).get(0).getAttribute("name"));
		System.out.println("\n");
		if (listItem(xpath).size() > 0) {
			element = listItem(xpath).get(0);
		}
		return element;
	}

	protected List<AndroidElement> listItem(String xpath) {
		return driver.findElementsByXPath(xpath);
	}

	protected boolean waitToAppear(By element, int duration) {
		wait = new WebDriverWait(driver, duration);		
		return wait.until(ExpectedConditions.presenceOfElementLocated(element)).isDisplayed();
	}

	protected boolean waitToDisappear(By element, int duration) {
		wait = new WebDriverWait(driver, duration);
		return !wait.until(ExpectedConditions.presenceOfElementLocated(element)).isDisplayed();
	}

	protected void swipe(String direction, Double fromFactor, Double toFactor) {
		Dimension dimension = driver.manage().window().getSize();

		double height = dimension.getHeight() * 0.5;
		double width = dimension.getWidth() * 0.5;

		double startY = 0, endY = 0, startX = 0, endX = 0;

		if (direction == SwipeDirection.DOWN || direction == SwipeDirection.UP) {
			startY = height * fromFactor;
			endY = height * toFactor;
		} else if (direction == SwipeDirection.LEFT || direction == SwipeDirection.RIGHT) {
			startX = width * fromFactor;
			endX = width * toFactor;
		}

		int xFrom = (int) startX;
		int yFrom = (int) startY;

		int xTo = (int) endX;
		int yTo = (int) endY;

		// Duration of seconds set to zero is enough, if removed the swipe will happen super fast 
		touchAction.press(PointOption.point(xFrom, yFrom)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(0)))
				.moveTo(PointOption.point(xTo, yTo)).release().perform();
	}

	protected AndroidElement swipeUntilFound(String xpath, String direction, Double fromFactor, Double toFactor,
			int swipeCount) {
		AndroidElement element = null;
		int counter = 0;
		while (listItem(xpath).size() == 0) {
			swipe(direction, fromFactor, toFactor);
			if (counter++ >= swipeCount) {
				System.out.println("Item not found while swiping");
				break;
			}
		}
		if (listItem(xpath).size() > 0) {
			if (listItem(xpath).get(0) != null)
				element = listItem(xpath).get(0);
		}
		return element;
	}

	protected AndroidElement findElementByID(String id) {
		return driver.findElement(MobileBy.id(id));
	}

	protected AndroidElement findElementByXPath(String path) {
		return driver.findElement(MobileBy.xpath(path));
	}

	protected AndroidElement findElementByAccessibilityID(String id) {
		return driver.findElement(MobileBy.AccessibilityId(id));
	}
	
	public boolean pixelPerfectClick(By element) {
		boolean isClicked = false;
		AndroidElement mobileElement = getMobileElement(element);
		Point point = mobileElement.getLocation();
		int x = point.getX();
		int y = point.getY();
		int centerX = x + (mobileElement.getSize().getWidth() / 2);
		int centerY = y + (mobileElement.getSize().getHeight() / 2);
		System.out.println("Clicked element at x:" + x + ", y:" + y);
		try {
			touchAction.press(PointOption.point(centerX, centerY)).release().perform();
			isClicked = true;
		} catch (Exception e) {
			System.out.println("Couldn't click on UI element!");
		}
		return isClicked;
	}
	
	protected AndroidElement getMobileElement(By element) {
		AndroidElement mElement = null;
		try {
			mElement = (AndroidElement) element.findElement(driver);
		} catch (Exception e) {
			System.out.println("Couldn't extract element from By object");
			e.printStackTrace();
		}
		return mElement;
	}
	
	protected AndroidElement scrollByTextTo(String desc) {
	    return  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
	            ".scrollIntoView(new UiSelector().text(" + "\"" + desc + "\"" + "));");
	    }

}
