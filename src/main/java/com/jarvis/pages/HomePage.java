package com.jarvis.pages;

import org.openqa.selenium.By;

import com.jarvis.base.Keyword;
import com.jarvis.base.WaitFor;


public class HomePage {
	Keyword keyword = new Keyword();
	By menMenu = By.xpath("//a[@href=\"/shop/men\"]");
	By womenMenu = By.xpath("");
	By tShirtMenu = By.xpath("//a[@href=\"/men-tshirts\"]");
	
	public void hoverOnMenMenu() {
		keyword.mouseHoverOn(Keyword.driver.findElement(menMenu));
	}
	
	public void clickOnTShirtsMenu() {
		WaitFor.elementToBeClickable(keyword.driver.findElement(tShirtMenu));
		keyword.clickOn(keyword.driver.findElement(tShirtMenu));
		System.out.println("Clicked on T-Shirts Menu");
	}
	
}
