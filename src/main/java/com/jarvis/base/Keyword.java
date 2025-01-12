package com.jarvis.base;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Keyword {

	public static RemoteWebDriver driver; //Instance
	
	public void openBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("Safari")){
			driver = new SafariDriver();
		}else if(browserName.equalsIgnoreCase("Edge")){
			driver = new EdgeDriver();
		}else {
			System.err.println("Invalid browser name");
		}
		
		System.out.println("Launched "+browserName+" browser");
	}
	public void closeBrowser() {
		driver.quit();		
	}
	public void launchUrl(String url) {
		driver.get(url);
	}
	
	public void mouseHoverOn(WebElement element) {
		Actions mouse = new Actions(driver);
		mouse.moveToElement(element).perform();
	}
	public void clickOn(WebElement element) {
		element.click();
	}
	
	public void clickOn(String locator) {
		getWebElement(locator).click();
	}
	
	public List<String> getTextOfAllElements(String locator) {
		List<WebElement> elements = getWebElements(locator);
		List<String> data = new ArrayList<String>();
		for (WebElement element : elements) {
			data.add(element.getText());
		}
		
		return data;
		
	}
	
	public List<WebElement> getWebElements(String locator) {
		String locatorType = locator.split("##")[0];
		locator = locator.split("##")[1];
		
		if (locatorType.equalsIgnoreCase("xpath")) {
			return driver.findElements(By.xpath(locator));
		} else if(locatorType.equalsIgnoreCase("id")){
			return driver.findElements(By.id(locator));
		}else if(locatorType.equalsIgnoreCase("name")){
			return driver.findElements(By.name(locator));
		}else if(locatorType.equalsIgnoreCase("tagname")){
			return driver.findElements(By.tagName(locator));
		}else if(locatorType.equalsIgnoreCase("classname")){
			return driver.findElements(By.className(locator));
		}else if(locatorType.equalsIgnoreCase("linktext")){
			return driver.findElements(By.linkText(locator));
		}else if(locatorType.equalsIgnoreCase("partial")){
			return driver.findElements(By.partialLinkText("css"));
		}else {
			return null;
		}
	}
	public WebElement getWebElement(String locator) {
		String locatorType = locator.split("##")[0];
		locator = locator.split("##")[1];
		
		if (locatorType.equalsIgnoreCase("xpath")) {
			return driver.findElement(By.xpath(locator));
		} else if(locatorType.equalsIgnoreCase("id")){
			return driver.findElement(By.id(locator));
		}else if(locatorType.equalsIgnoreCase("name")){
			return driver.findElement(By.name(locator));
		}else if(locatorType.equalsIgnoreCase("tagname")){
			return driver.findElement(By.tagName(locator));
		}else if(locatorType.equalsIgnoreCase("classname")){
			return driver.findElement(By.className(locator));
		}else if(locatorType.equalsIgnoreCase("linktext")){
			return driver.findElement(By.linkText(locator));
		}else if(locatorType.equalsIgnoreCase("partial")){
			return driver.findElement(By.partialLinkText("css"));
		}else {
			return null;
		}
	}
}
