package api.utils;
import java.util.Map;

import api.endpoints.GetApiEndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredClient {

	/**
	 * @param method
	 * @param url
	 * @param body
	 * @param headers
	 * @return
	 * @author lokeshay 
	 */
    public Response sendRequest(String method, String endpoint, Object body, Map<String, String> headers) {
    	
		RestAssured.baseURI = GetApiEndPoints.base_url;
        RequestSpecification request = RestAssured.given().basePath(endpoint).contentType(ContentType.JSON);

        if (headers != null) {
            request.headers(headers);
        }

        if (body != null) {
            request.body(body);
        }

        Response response = null;
        switch (method.toUpperCase()) {
            case "GET":
                response = request.get();
                break;
            case "POST":
                response = request.post();
                break;
            case "PUT":
                response = request.put();
                break;
            case "DELETE":
                response = request.delete();
                break;
            case "PATCH":
                response = request.patch();
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
        return response;
    }
    
    /**
     * 
     * @param response
     * @param expectedStatusCode
     */
    public void validateStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        if (actualStatusCode != expectedStatusCode) {
            throw new AssertionError("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
        } else {
            System.out.println("Status code validation passed: " + actualStatusCode);
        }
    }
    
    /**
     * 
     * @param response
     * @param expectedValues
     */
    public void validateJsonResponse(Response response, Map<String, Object> expectedValues) {
        for (Map.Entry<String, Object> entry : expectedValues.entrySet()) {
            String jsonPath = entry.getKey();
            Object expectedValue = entry.getValue();
            try {
                response.then().assertThat().body(jsonPath, equalTo(expectedValue));
                System.out.println("JSON validation passed for path: " + jsonPath);
            } catch (AssertionError e) {
                throw new AssertionError("JSON validation failed for path: " + jsonPath + ". " + e.getMessage());
            }
        }
    }

}


