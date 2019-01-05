package com.sigma.appium.core;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	
	private int count = 0;
	private int retryLimit = 3;

	public boolean retry(ITestResult result) {
		if(count < retryLimit) {
			count++;
			return true;
		}
		return false;
	}

}
