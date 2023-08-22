package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		//co.addArguments("--remote-allow-origins=*");
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {

			System.out.println("-----------Running Chrome in headless mode-----------");
			co.addArguments("--headless");

		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {

			System.out.println("-----------Running Chrome in incognito mode-----------");
			co.addArguments("--incognito");
		}
		
		return co;

	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {

			System.out.println("-----------Running Firefox in headless mode-----------");
			fo.addArguments("--headless");

		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			
			System.out.println("-----------Running Firefox in incognito mode-----------");
			fo.addArguments("--incognito");
		}
		
		return fo;

	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			
			System.out.println("-----------Running Edge in headless mode-----------");
			eo.addArguments("--headless");

		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			
			System.out.println("-----------Running Edge in incognito mode-----------");
			eo.addArguments("--incognito");
		}
		
		return eo;

	}

}
