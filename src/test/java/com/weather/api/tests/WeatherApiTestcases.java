package com.weather.api.tests;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.utils.ApachePoiUtility;
import common.utils.Logger;
import pages.WeatherData;

@Listeners(common.utils.TestListeners.class)
public class WeatherApiTestcases {

	ApachePoiUtility excel = new ApachePoiUtility();
	WeatherData weather = new WeatherData();

	@Test(description = "Weather api: get the Temp and humidity ", dataProvider = "data", priority = 1)
	public void weatherApiTest(String cities) throws Throwable {

		Map<String, double[]> res = weather.getLatLon(cities);
		Logger.info("got the lattitude and longitude of city" + cities);

		double lat = res.get(cities)[0];
		double lan = res.get(cities)[1];
		Logger.info("lattitude is " + lat);
		Logger.info("lattitude is " + lan);

		Map<String, Object> weatherData = weather.getWeatherData(lat, lan);
		Logger.info("Successfully got the weather data : temp" + weatherData.get("temp"));
		Logger.info("Successfully got the weather data : humidity" + weatherData.get("humidity"));

		excel.writeToExcel("./src/test/resources/Cities.xlsx", "Sheet1", 1, weatherData.get("temp"), cities);
		Logger.info("Write temp to excel");

		excel.writeToExcel("./src/test/resources/Cities.xlsx", "Sheet1", 2, weatherData.get("humidity"), cities);
		Logger.info("Write humidity to excel");

	}

	@Test(description = "compare: maximun temprature", priority = 2)
	public void maximumTemprature() throws IOException, Throwable {

		// compare tem
		double maxtemp = 0;
		for (int i = 0; i < 3; i++) {

			String temp =excel.readExcel("./src/test/resources/Cities.xlsx", "Sheet1", i, 1);
			double temprature = Double.parseDouble(temp);
			if(i==0) {
				maxtemp = temprature;
			}else {
				
				if(temprature>maxtemp) {
					maxtemp = temprature;
				}
			}
			
		}
		
		Logger.info("Maximun temp is "+ maxtemp);

	}

	@DataProvider(name = "data")
	public Object[][] dataProvider() throws Throwable, Throwable {

		Object[][] obj = new Object[3][1];

		for (int i = 0; i < 3; i++) {

			obj[i][0] = excel.readExcel("./src/test/resources/Cities.xlsx", "Sheet1", i, 0);
		}

		return obj;

	}

}
