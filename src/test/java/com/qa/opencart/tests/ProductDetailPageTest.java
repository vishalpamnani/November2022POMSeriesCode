package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductDetailPageTest extends BaseTest {
	
	
	@BeforeClass
	public void productDetailPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] { 
		
			{ "Macbook", "MacBook Pro" , 4},  
			{ "iMac", "iMac", 3 },
			{ "Apple", "Apple Cinema 30\"", 6 }, 
			{ "Samsung", "Samsung SyncMaster 941BW", 1 }, 			
		};
	}
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchPage = accPage.performSearch(searchKey);
		pdPage = searchPage.selectProduct(productName);
		int actualImagesCount = pdPage.getProductImagesCount();
		Assert.assertEquals(actualImagesCount, imagesCount);
	}
	
	@Test
	public void productInfoTest() {
		searchPage = accPage.performSearch("MacBook");
		pdPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductInfoMap = pdPage.getProductInfo();
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actualProductInfoMap.get("productprice"), "$2,000.00");
		
		softAssert.assertAll();
		
	}
	
	
	@DataProvider
	public Object[][] getAddToCartTestData(){
		return new Object[][] {
			
			{"MacBook", "MacBook Pro"},
			{ "iMac", "iMac"},
			{ "Samsung", "Samsung SyncMaster 941BW"},
		
		};
	}
	
	
	@Test(dataProvider = "getAddToCartTestData")
	public void addToCartTest(String searchKey, String productName) {
		searchPage = accPage.performSearch(searchKey);
		pdPage = searchPage.selectProduct(productName);
		pdPage.enterProductQuantity(2);
		String actualSuccessMessage = pdPage.addProductToCart();
		softAssert.assertTrue(actualSuccessMessage.indexOf("Success")>=0);
		softAssert.assertTrue(actualSuccessMessage.indexOf(productName)>=0);
		softAssert.assertEquals(actualSuccessMessage, "Success: You have added "+productName+" to your shopping cart!");
		softAssert.assertAll();
	}
	
	

}
