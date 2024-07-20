package common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtilities {
	
	/**
	 * @description This method is used to get data from property file
	 * @author Lokesh
	 * @param key
	 * @return getProperty
	 * @throws Throwable
	 */
	public static String getDataFromPropertyFile(String key) throws Throwable {
		
		try {
		
		FileInputStream file = new FileInputStream(new File("./src/main/resources/propertyFiles/config.property"));
		Properties prop = new Properties();
		prop.load(file);
		return prop.getProperty(key);
		
		}catch(Exception error) {
			
			return error.getMessage();
		}
		
	}

}
