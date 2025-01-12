package com.jarvis.locators;

public interface Locator {

	String menMenu = "xpath##//a[@href=\"/shop/men\"]"; 
	String tShirtsMenu = "xpath##//a[@href=\"/men-tshirts\"]";
	String tShirtFilter = "xpath##//input[@value=\"Tshirts\"]/parent::label";
	String description = "xpath##//h4[@class=\"product-product\"]";
	
}
