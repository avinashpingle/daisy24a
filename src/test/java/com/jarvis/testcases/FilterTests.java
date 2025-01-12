package com.jarvis.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.jarvis.base.Keyword;
import com.jarvis.base.TestBase;
import com.jarvis.base.WaitFor;
import com.jarvis.locators.Locator;
import com.jarvis.pages.HomePage;
import com.jarvis.pages.ResultPage;
import com.jarvis.pages.SearchResultPage;
import com.jarvis.utils.OR;

public class FilterTests extends TestBase{

	@Test
	public void verifyDescriptionOfSearchResultForTShirts() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(Keyword.driver, Duration.ofSeconds(20));
		wait.ignoring(NoSuchElementException.class);
		WebElement menMenu = Keyword.driver.findElement(By.xpath("//a[@href=\"/shop/men\"]"));
		Actions act = new Actions(Keyword.driver);
		act.moveToElement(menMenu).perform();
		Thread.sleep(3000);
		Keyword.driver.findElement(By.xpath("//a[@href=\"/men-tshirts\"]")).click(); // Click on Tshirts
		Thread.sleep(3000);
		Keyword.driver.findElement(By.xpath("//input[@value=\"Tshirts\"]/parent::label")).click();

		List<WebElement> descriptions = Keyword.driver.findElements(By.xpath("//h4[@class=\"product-product\"]"));
		SoftAssert softly = new SoftAssert();
		for (WebElement description : descriptions) {
			String text = null;
			try {
				text = description.getText();
				System.out.println("Description: "+text);
			} catch (StaleElementReferenceException e) {
				wait.until(ExpectedConditions.stalenessOf(description));
				text  =description.getText();
			}
			softly.assertTrue(text.contains("T-shirt"), "Product Description: " + text);
		}

		softly.assertAll();

	}
	
	@Test
	public void verifyDescriptionOfSearchResultForTShirtsUsingKeywords() {
		Keyword keyword = new Keyword();
		WebElement menMenu = keyword.getWebElement(OR.getLocator("menMenu"));
		keyword.mouseHoverOn(menMenu);
		WebElement tShirt = keyword.getWebElement(Locator.tShirtsMenu);
		WaitFor.elementToBeClickable(tShirt);
		keyword.clickOn(tShirt);
		keyword.clickOn(Locator.tShirtFilter);
		List<String> descriptions = keyword.getTextOfAllElements(Locator.description);
		SoftAssert softly = new SoftAssert();
		for (String description : descriptions) {
			softly.assertTrue(description.contains("T-Shirt"));
		}
		softly.assertAll();
	}
	
	@Test
	public void verifyDescriptionOfSearchResultForTShirtsUsingPom() {
		HomePage homepage = new HomePage();
		homepage.hoverOnMenMenu();
		homepage.clickOnTShirtsMenu();
		ResultPage resultPage = new ResultPage();
		resultPage.clickOnCategory("Tshirts");
		resultPage.verifyAllProductDescriptionsContains("T-Shirts");
	}
}
