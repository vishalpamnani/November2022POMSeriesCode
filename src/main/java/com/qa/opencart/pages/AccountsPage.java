package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private by locators
	private By logoutLink = By.linkText("Logout");
	private By accountHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("#search button");

	// 2. page constructor
	public AccountsPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	
	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIMEOUT, OpenCartConstants.ACCOUNTS_PAGE_TITLE);
		System.out.println("Accounts Page Title is: "+title);
		return title;
	}
	
	public String getAccountsPageURL() {
		String url = eleUtil.waitForURLContainsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIMEOUT, OpenCartConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println("Accounts Page URL is: "+url);
		return url;
	}
	
	public boolean isLogoutLinkVisible() {
		return eleUtil.waitForElementVisibility(logoutLink, OpenCartConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();			
	}
	
	public boolean isSearchFieldVisible() {
		return eleUtil.waitForElementVisibility(search, OpenCartConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
	}
	
	public List<String> getAccountPageHeadersList() {
		
		List<WebElement> accountHeadersList = eleUtil.waitForElementsVisible(accountHeaders, OpenCartConstants.DEFAULT_MEDIUM_TIMEOUT);
		List<String> accountHeadersValue = new ArrayList<String>();
		
		for(WebElement w : accountHeadersList) {
			String text = w.getText();
			accountHeadersValue.add(text);
		}
		return accountHeadersValue;
	}
	
	public SearchResultPage performSearch(String searchKey) {
		
		if(isSearchFieldVisible()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultPage(driver);
		}
		else {
			System.out.println("Search is not available on the page.");
			return null;
		}
		
	}

}
