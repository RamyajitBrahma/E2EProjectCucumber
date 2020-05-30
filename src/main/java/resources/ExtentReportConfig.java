package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportConfig {
  
	public static ExtentReports extReportObj;
	
	public static ExtentReports extentReportOrg() {
		String fileReportPath = System.getProperty("user.dir")+"\\reports\\Extreports\\index.html";
		ExtentSparkReporter extrSparkReporterObj = new ExtentSparkReporter(fileReportPath);
		extrSparkReporterObj.config().setReportName("E2E Porject Extent Report WEbResults");
		extrSparkReporterObj.config().setDocumentTitle("E2E Extent Report");
		
		 extReportObj = new ExtentReports();
		 extReportObj.attachReporter(extrSparkReporterObj);
		 extReportObj.setSystemInfo("Tester", "Jit");
		 return extReportObj;
		
	}
	

	

}
