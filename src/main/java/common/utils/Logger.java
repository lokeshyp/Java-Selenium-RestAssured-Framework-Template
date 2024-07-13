package common.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Logger {
	
	public static ExtentTest info(String message) {
		
		return ExtentManager.getTest().log(Status.INFO, message);
	}

}
