package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private By locators
	private By emailId = By.id("input-email");	
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2. page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. page actions/methods
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIMEOUT, OpenCartConstants.LOGIN_PAGE_TITLE);
		System.out.println("Login page title is: " + title);
		return title;
	}

	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContainsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIMEOUT, OpenCartConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login page url is: " + url);
		return url;
	}

	public boolean isForgotPasswordLinkAvailable() {
		return eleUtil.waitForElementVisibility(forgotPwdLink, OpenCartConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();

	}
	
	public AccountsPage doLogin(String userName, String pwd) {
		
		System.out.println("App credentials are: "+ userName + " : " + pwd);
		eleUtil.waitForElementVisibility(emailId, OpenCartConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}

}
