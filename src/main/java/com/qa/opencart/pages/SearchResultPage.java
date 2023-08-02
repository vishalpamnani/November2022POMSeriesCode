package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchProductResults = By.cssSelector("div#content div.product-layout");

	// page constructor
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public int getProductsCount() {
		int productCount = eleUtil.waitForElementsVisible(searchProductResults, OpenCartConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Product Count: "+productCount);
		return productCount;

	}

	public ProductDetailPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisibility(productLocator, OpenCartConstants.DEFAULT_MEDIUM_TIMEOUT).click();
		return new ProductDetailPage(driver);
	}

}
