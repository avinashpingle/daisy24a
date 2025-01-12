package com.jarvis.utils;

import java.io.IOException;

public class OR {

	public static String getLocator(String locator) {
		String base = System.getProperty("user.dir");
		String value = "";
		try {
			value = PropUtil.getProperty(base + "/src/main/resources/OR.properties", locator);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	}
}
