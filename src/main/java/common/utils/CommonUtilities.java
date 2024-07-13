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
	public  static String getDataFromPropertyFile(String key) throws Throwable {
		
		FileInputStream file = new FileInputStream(new File("./src/main/resources/propertyFiles/env.property"));
		Properties prop = new Properties();
		prop.load(file);
		return prop.getProperty(key);
		
	}
	
	/**
	 * @description this method is used to get end points from endPointsData file
	 * @author Lokesh
	 * @param fileName
	 * @return endPoint
	 * @throws Throwable
	 */
	public String getEndPoints(String fileName) throws Throwable {
		ObjectMapper objMap = new ObjectMapper();
		JsonNode node = objMap.readTree(new File("./src/test/resources/endpointsData/"+ fileName));
		String endPoint = node.get("post").asText();
		return endPoint;
	}
}
