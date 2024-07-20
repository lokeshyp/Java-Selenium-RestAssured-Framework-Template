package common.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	  private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	    public static ExtentReports createInstance(String fileName) {
	        if (extent == null) {
	            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
	            htmlReporter.config().setTheme(Theme.DARK);
	            htmlReporter.config().setDocumentTitle("Test Report");
	            htmlReporter.config().setReportName("Test Execution Report");

	            extent = new ExtentReports();
	            extent.attachReporter(htmlReporter);
	            extent.setSystemInfo("Environment", "QA");
	            extent.setSystemInfo("User", "Test User");
	        }
	        return extent;
	    }

	    public static ExtentReports getExtent() {
	        if (extent == null) {
	            throw new IllegalStateException("ExtentReports instance is not created. Call createInstance() first.");
	        }
	        return extent;
	    }

	    public static void setTest(ExtentTest extentTest) {
	        test.set(extentTest);
	    }

	    public static ExtentTest getTest() {
	        return test.get();
	    }
	}
