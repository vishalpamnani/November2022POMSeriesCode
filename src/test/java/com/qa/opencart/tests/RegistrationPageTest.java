package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void regPageSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random random  = new Random();
		//String email = "automation" + random.nextInt(1000) + "@gmail.com";
		
		String email = "automation" + System.currentTimeMillis() + "@gmail.com";
		
		return email;
	}
	
	@DataProvider
	public Object[][] getRegistrationTestData() {
		Object registrationData[][] = ExcelUtil.getTestData(OpenCartConstants.REGISTER_SHEET_NAME);
		return registrationData;
	}
	
	@Test(dataProvider = "getRegistrationTestData")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		
		Assert.assertTrue(registrationPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
		
	}

}
