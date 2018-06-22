package com.test.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.test.configutils.LoadProperties;

public class EtsyPlatform {

	private WebDriver driver;

	@FindBy(css = "#search-query")
	private WebElement searchfield;
	
	@FindBy(css = "span.currency-value")
	private List<WebElement> itemslist;
	
	@FindBy(css = "position-absolute position-top>span")
	private WebElement adText;

	@FindBy(css = ".as-suggestion>span")
	private List<WebElement> suggestions;

	@FindBy(css = ".as-suggestion>span")
	private WebElement suggestion;

	@FindBy(css = ".btn.btn-secondary.caret.normal")
	private WebElement sortBy;

	@FindBy(css = ".list-nav-item.text_smaller")
	private List<WebElement> sortOptions;

	@FindBy(css = ".navbar-brand")
	private WebElement pageTitle;

	@FindBy(css = ".width-full.btn.btn-outline.btn-outline-black")
	private WebElement acceptPrivateSettingsButton;

	public void clickAcceptPrivateSettingsButton() {

		acceptPrivateSettingsButton.click();
	}

	public EtsyPlatform(WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(finder, this);
		this.driver = driver;
	}

	public String getPageTitle() {
		return pageTitle.getText();
	}

	public void gotoHomePage() {
		driver.get(LoadProperties.getWebEndPoint());
	}

	/*
	 * public List<String> getListOfSavedHotelNames() { List<String>
	 * listofNames=new ArrayList<String>(); if(getRowCount()==0) { return
	 * listofNames; } List<WebElement> elements=
	 * driver.findElements(By.xpath(".//*[@class='row detail']/div/div[1]/p"));
	 * for(WebElement e:elements){ listofNames.add(e.getText()); } return
	 * listofNames; }
	 */

	public void searchtext(String searchtext) {
		searchfield.sendKeys(searchtext);
	}

	public void selectValueSuggestionOption(String value) {
		for (WebElement el : suggestions) {
			String text = el.getText();

			if (text.contains(value)) {
				suggestion = el;
				break;
			}
		}
		Actions action = new Actions(driver);
		action.moveToElement(suggestion).click().build().perform();
	}

	public void clickSortBy() {
		sortBy.click();
	}

	public void selectOptionSortBy(String value) {
		for (WebElement el : sortOptions) {
			String text = el.getText();

			if (text.contains(value)) {
				el.click();
				break;
			}
		}
	}

	public void selectValueDropDownOptions(String value) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Thread.sleep(2000);

			js.executeScript("return window.jQuery('.as-suggestion').click()");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printItems() {
		
		int i=1;
		for (WebElement el : itemslist) {
			if(adText.getText()!="Ad") {
				System.out.println(el.getText());
				i++;
				if(i==11)
					break;
			}
		}
	}
}
