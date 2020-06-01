package cucumberStepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import resources.ExtentReportConfig;

public class cucumberExtentReport {
	public static ExtentReports extReportObj;
	public static ExtentTest ExttestcaseReport;
	public static String testCaseName;
	public static ThreadLocal<ExtentTest> ThreadSafeExtentReport = new ThreadLocal<ExtentTest>();//making it threwadsafe
	
	public static void extentReportGen(String tcName) {
		extReportObj=ExtentReportConfig.extentReportOrg();
		ExttestcaseReport=extReportObj.createTest(tcName);
		ThreadSafeExtentReport.set(ExttestcaseReport);
	}
}
