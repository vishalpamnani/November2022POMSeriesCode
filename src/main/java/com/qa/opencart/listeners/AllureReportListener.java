package com.qa.opencart.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Attachment;

public class AllureReportListener implements ITestListener{
	
	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}
	
	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}
	
	public void onStart(ITestContext testContext) {
		System.out.println("I am in onStart method " + testContext.getName());
		//testContext.setAttribute("WebDriver", BaseTest.getDriver());
	}
	
	public void onFinish(ITestContext testContext) {
		System.out.println("I am in onFinish method " + testContext.getName());
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("I am in onTestStart method " + getTestMethodName(result) + "start");
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(result) + "succeeded");
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(result) + "failed");
		Object testClass = result.getInstance();
		//WebDriver driver = BaseTest.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if(DriverFactory.getDriver() instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(result));
			saveScreenshotPNG(DriverFactory.getDriver());
		}
		// Save a log on allure
		saveTextLog(getTestMethodName(result) + "failed and screenshot taken!");
	}
	
	public void onTestSkipped(ITestResult result) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(result) + "skipped");
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(result));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
