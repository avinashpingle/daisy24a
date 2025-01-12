package com.jarvis.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.jarvis.base.Keyword;

public class SearchResultPage {
	Keyword keyword = new Keyword();

	By categoryList = By.xpath("//ul[@class=\"categories-list\"]");
	By productDescriptions = By.xpath("//h4[@class=\"product-product\"]");

	public void clickOnCategory(String categoryName) {
		WebElement cat = Keyword.driver.findElement(categoryList)
				.findElement(By.xpath("//label[text()='" + categoryName + "']"));
		keyword.clickOn(cat);
		System.out.println("Clicked on " + categoryName + " category");
	}

	public List<String> getAllProductDescriptions() {
		List<WebElement> descriptions = Keyword.driver.findElements(productDescriptions);
		List<String> prodDescriptions = new ArrayList<String>();
		for (WebElement description : descriptions) {
			prodDescriptions.add(description.getText());
		}
		
		return prodDescriptions;
	}
	
	public void verifyAllProductDescriptionsContains(String textToSearch) {
		SoftAssert softly = new SoftAssert();
		for (String description : getAllProductDescriptions()) {
			softly.assertTrue(description.contains(textToSearch));
		}
		softly.assertAll();
	}
}
