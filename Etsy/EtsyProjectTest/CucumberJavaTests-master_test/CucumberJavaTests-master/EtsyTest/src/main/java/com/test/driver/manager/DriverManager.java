package com.test.driver.manager;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.test.configutils.LoadProperties;

public class DriverManager {

	
	public static RemoteWebDriver getDriverDynamic() throws MalformedURLException {
		String defaultBrowser = LoadProperties.getASpecificProperty("browser");

		RemoteWebDriver driver = null;
		
		System.setProperty("webdriver.chrome.driver", "C:/Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		if (defaultBrowser.equals("firefox") || defaultBrowser.equals("ff")) {

//			System.setProperty("webdriver.gecko.driver","C:/Driver/geckodriver");
			// set driver properties
			FirefoxProfile profile = new FirefoxProfile();			
			profile.setPreference("javascript.enabled", true);
			profile.setEnableNativeEvents(true);
			
			
			driver = new FirefoxDriver(profile);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} 		
		return driver;
	}

}
