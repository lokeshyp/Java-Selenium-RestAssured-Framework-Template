package common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestListeners implements ITestListener {

	private static String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	private static ExtentReports extent = ExtentManager.createInstance("extent_Report_"+timestamp+".html");

	public void onTestStart(ITestResult result) {

        ExtentTest extentTest = ExtentManager.getExtent().createTest(result.getMethod().getDescription());
		ExtentManager.setTest(extentTest);
		ExtentManager.getTest().log(Status.INFO, "Starting test: " + result.getMethod().getDescription());

	}

	public void onTestSuccess(ITestResult result) {

		ExtentManager.getTest().log(Status.PASS, "Test passed: " + result.getMethod().getDescription());

	}

	public void onTestFailure(ITestResult result) {

		ExtentManager.getTest().log(Status.FAIL, "Test failed: " + result.getMethod().getDescription());
		ExtentManager.getTest().log(Status.FAIL, result.getThrowable());

		// Capture and attach screenshot
		String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
		ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
	}

	public void onTestSkipped(ITestResult result) {
		ExtentManager.getTest().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		extent.flush();

	}

	private String captureScreenshot(String methodName) {

		return System.getProperty("user.dir") + "\\reports\\screenshot.png";
	}

}
