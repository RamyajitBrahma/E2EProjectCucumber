package testE2EProject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReportConfig;
import resources.baseOR;

public class listenersE2E extends baseOR implements ITestListener {

	public ExtentReports extReportObj=ExtentReportConfig.extentReportOrg();
	public static ExtentTest ExttestcaseReport;
	public static String testCaseName;
	public static ThreadLocal<ExtentTest> ThreadSafeExtentReport = new ThreadLocal<ExtentTest>();//making it threwadsafe
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//extReportObj=ExtentReportConfig.extentReportOrg();
		testCaseName=result.getMethod().getMethodName();
		ExttestcaseReport=extReportObj.createTest(result.getMethod().getMethodName());
		ThreadSafeExtentReport.set(ExttestcaseReport);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ExttestcaseReport.pass("Test Case Has PASSED"); //was used when the suite was in non parallel mode
		ThreadSafeExtentReport.get().pass("Test Case Has PASSED");

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver = null;

		//ExttestcaseReport.log(Status.FAIL, result.getThrowable()); //was used when the suite was in non parallel mode
		ThreadSafeExtentReport.get().log(Status.FAIL, result.getThrowable());
		String TcName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			takeScreenShots(TcName,driver);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extReportObj.flush();
	}

}
