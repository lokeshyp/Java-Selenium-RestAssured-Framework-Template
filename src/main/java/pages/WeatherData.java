package pages;

import java.util.HashMap;
import java.util.Map;

import common.utils.CommonUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherData {

	/**
	 * 
	 * @param cityName
	 * @return
	 * @throws Throwable
	 */
	public Map<String, double[]> getLatLon(String cityName) throws Throwable {

		try {
			String apiKey = CommonUtilities.getDataFromPropertyFile("WEATHERAPI");
			String endpoint = "http://api.openweathermap.org/geo/1.0/direct?q="+cityName+"&limit=1&appid=" + apiKey;
			Response response = RestAssured.get(endpoint);
			double lat = response.jsonPath().getDouble("[0].lat");
			double lon = response.jsonPath().getDouble("[0].lon");
			Map<String, double[]> geoData = new HashMap<>();
			geoData.put(cityName, new double[] { lat, lon });
			return geoData;
		} catch (Exception error) {
			throw new Exception("Error occured in getLatLon ");
		}
	}

	/**
	 * 
	 * @param lat
	 * @param lon
	 * @return
	 * @throws Throwable
	 */
	public  Map<String, Object> getWeatherData(double lat, double lon) throws Throwable {

		String apiKey = CommonUtilities.getDataFromPropertyFile("WEATHERAPI");
		String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
				+ "&exclude=minutely,hourly,alerts&appid=" + apiKey;
		Response response = RestAssured.get(url);
		response.then().log().all();
		Map<String, Object> weatherStats = new HashMap<>();
		weatherStats.put("temp", response.jsonPath().getDouble("main.temp"));
		weatherStats.put("humidity", response.jsonPath().getInt("main.humidity"));
		return weatherStats;
	}

}
