package com.jarvis.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

	Keyword keyword = new Keyword();
	@BeforeMethod
	public void setUp() throws Exception {
		keyword.openBrowser("Firefox");
		keyword.launchUrl("https://www.myntra.com");
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		keyword.closeBrowser();
	}
}
