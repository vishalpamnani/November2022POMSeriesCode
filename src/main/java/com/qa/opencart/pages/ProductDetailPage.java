package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductDetailPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartButton = By.id("button-cart");
	private By addToCartSuccessMessage = By.cssSelector("div.alert.alert-success");

	private Map<String, String> productInfoMap;

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderValue() {
		String productHeaderValue = eleUtil.doElementGetText(productHeader);
		System.out.println("Product Header: " + productHeaderValue);
		return productHeaderValue;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, OpenCartConstants.DEFAULT_MEDIUM_TIMEOUT)
				.size();
		System.out.println("Product Images Count is: " + imagesCount);
		return imagesCount;
	}
	
	public void enterProductQuantity(int qty) {
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}
	
	public String addProductToCart() {
		eleUtil.doClick(addToCartButton);
		String successMessage = eleUtil.waitForElementVisibility(addToCartSuccessMessage, OpenCartConstants.DEFAULT_SHORT_TIMEOUT).getText();
		
		StringBuilder sb = new StringBuilder(successMessage);
		String message = sb.substring(0, successMessage.length()-1).replace("\n", "").toString();
		
		System.out.println("Add to Cart success message is: "+message);
		
		return message;
	}

	public Map<String, String> getProductInfo() {

		//productInfoMap = new HashMap<String, String>(); // does not maintain the order
		productInfoMap = new LinkedHashMap<String, String>(); // maintains the order
		//productInfoMap = new TreeMap<String, String>(); // maintains the keys in sorted manner

		// header
		productInfoMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productInfoMap);
		return productInfoMap;

	}

	// meta data
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaDataList) {
			String metaDataText = e.getText();
			String metaDataArray[] = metaDataText.split(":");
			String key = metaDataArray[0].trim();
			String value = metaDataArray[1].trim();
			productInfoMap.put(key, value);
		}
	}

	// price data
	private void getProductPriceData() {
		List<WebElement> priceDataList = eleUtil.getElements(productPricingData);
		String price = priceDataList.get(0).getText();
		String exTax = priceDataList.get(1).getText();
		String exTaxValue = exTax.split(":")[1].trim();

		productInfoMap.put("productprice", price);
		productInfoMap.put("exTax", exTaxValue);
	}

}
