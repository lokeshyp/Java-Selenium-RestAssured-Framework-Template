package common.utils;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestUtils {
	
	
	public static Response post(String endPoint, Object obj ) throws Throwable {
		
		  RestAssured.baseURI = CommonUtilities.getDataFromPropertyFile("BASE_URL");
		  Response response = RestAssured.given().contentType(ContentType.JSON).baseUri(endPoint).body(obj).post();		 
          return response;
		
	}
	

}
