package com.jarvis.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.jarvis.base.Keyword;
import com.jarvis.base.WaitFor;

public class ResultPage {

	@FindBy(css = "ul.categories-list")
	WebElement categoryList;

	@FindBy(xpath = "//h4[@class=\"product-product\"]")
	List<WebElement> descriptions;

	public void clickOnCategory(String categoryName) {
		WebElement tShirtsCat = categoryList.findElement(By.xpath("//label[text()='" + categoryName + "']"));
		tShirtsCat.click();
	}

	public ResultPage(){
		PageFactory.initElements(Keyword.driver, this);
	}
	public List<String> getAllProductDescriptions() {
		List<String> prodDescriptions = new ArrayList<String>();
		for (WebElement description : descriptions) {
			try {
				prodDescriptions.add(description.getText());
			} catch (StaleElementReferenceException e) {
				WaitFor.elementToBeFresh(description);
				prodDescriptions.add(description.getText());
			}
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
