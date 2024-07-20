package com.petstore.api.pet.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.endpoints.GetApiEndPoints;
import api.utils.ApiTestGroups;
import api.utils.RestAssuredClient;
import common.utils.ApachePoiUtility;
import common.utils.Logger;
import io.restassured.response.Response;

@Listeners(common.utils.TestListeners.class)
public class Api_Pet_Testcases {

	@Test(description = "Add new pet details", groups = {ApiTestGroups.PET})
	public void createPetDetails() throws Throwable {
		RestAssuredClient client = new RestAssuredClient();
		Map<String, Object> obj = new HashMap<>();

		obj.put("id", "0");
		obj.put("name", "tester");
		obj.put("status", "available");

		// send request
		Response response = client.sendRequest(GetApiEndPoints.base_url, "POST", "/pet", obj, null);
		Logger.info("Requested for Post");

		// validate the body
		client.validateStatusCode(response, 200);
		Logger.info("Validated the status code");

		// validate the response
		obj.clear();
		obj.put("name", "tester");
		obj.put("status", "available");
		client.validateJsonResponse(response, obj);
		Logger.info("Validated the responsebody");

	}

}
