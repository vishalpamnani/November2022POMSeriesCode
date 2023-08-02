package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountsPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void accountsPageTitleTest() {
		String actualTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actualTitle, OpenCartConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accountsPageURLTest() {
		String actualURL = accPage.getAccountsPageURL();
		Assert.assertTrue(actualURL.contains(OpenCartConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}

	@Test
	public void isLogoutVisibleTest() {
		Assert.assertTrue(accPage.isLogoutLinkVisible());
	}

	@Test
	public void accountsPageHeadersTest() {
		List<String> actualAccountsPageHeadersList = accPage.getAccountPageHeadersList();
		System.out.println(actualAccountsPageHeadersList);
		Assert.assertEquals(actualAccountsPageHeadersList.size(), OpenCartConstants.ACCOUNTS_PAGE_HEADERS_COUNT);

	}

	@Test
	public void accountsPageHeadersValueTest() {
		List<String> actualAccountsPageHeadersList = accPage.getAccountPageHeadersList();
		System.out.println("Actual Headers List: " + actualAccountsPageHeadersList);
		System.out.println("Expected Headers List: " + OpenCartConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actualAccountsPageHeadersList, OpenCartConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);

	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { 
		
			{ "Macbook" }, 
			{ "iMac" }, 
			{ "Apple" }, 
			{ "Samsung" } 
			
		};
	}

	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getProductsCount() > 0);

	}

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { 
		
			{ "Macbook", "MacBook Pro" }, 
			{ "Macbook", "MacBook Air" }, 
			{ "iMac", "iMac" },
			{ "Apple", "Apple Cinema 30\"" }, 
			{ "Samsung", "Samsung SyncMaster 941BW" },
			{ "Samsung", "Samsung Galaxy Tab 10.1" } 
			
		};
	}

	@Test(dataProvider = "getProductTestData")
	public void selectProductTest(String searchKey, String procuctName) {
		searchPage = accPage.performSearch(searchKey);

		if (searchPage.getProductsCount() > 0) {
			pdPage = searchPage.selectProduct(procuctName);
			String actualProductHeader = pdPage.getProductHeaderValue();
			Assert.assertEquals(actualProductHeader, procuctName);
		}
	}

}
