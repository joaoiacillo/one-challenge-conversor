package com.john.api;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropsLoader extends Properties {
	
	public PropsLoader() {
		super();
		
		try {
			FileInputStream fis = new FileInputStream(new File("api.config"));
			load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}