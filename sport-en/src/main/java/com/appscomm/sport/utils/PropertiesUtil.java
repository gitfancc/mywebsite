package com.appscomm.sport.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	private static Logger log = Logger.getLogger(PropertiesUtil.class);
	
	public static String getValueByKey(String filePath,String key){
		
		Properties properties = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			properties.load(in);
			String value = properties.getProperty(key);
		    return value;
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public static void main(String[] args){
		String value = PropertiesUtil.getValueByKey("src/main/resources/config.properties", "server.domain");
		System.out.println(value);
		
	}
}
