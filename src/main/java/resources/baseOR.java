package resources;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import testE2EProject.listenersE2E;



//keep all the objects here


public class baseOR {
   public WebDriver driver;
   public Properties propObj;
   public FileInputStream fisObj;
   public Integer varCheck;
   public static Logger log = LogManager.getLogger(baseOR.class.getName());
   public String projectPath;
	
   //Initializing the chrome , IE and fiorefox Drivers
   public WebDriver InitializeDriver() throws IOException {
	    
	   
	   projectPath = System.getProperty("user.dir");
	   propObj = new Properties();
	   FileInputStream fisObj = new FileInputStream(projectPath+"\\src\\main\\java\\resources\\globalvar.properties");
	   propObj.load(fisObj);
	   varCheck=4;
	   System.out.println("baseOR"+varCheck);
	   
	   
	   //[ mvn test -Dbrowser=chrome ] --> This is the maven command to run a test on chrome browser
	   //once u crerate parameters in Jenkins named browser then maven command inside jenkins is  [test -Dbrowser="$browser" ]
	 //if we parametrise the browser and URL from maven instead of properties file then use the below commented code line 59 and comment line 61
	   //String browser=System.getProperty("browser");
	   
	   String browser = propObj.getProperty("browser"); // use this if not running from jenkins parameterized build and comment line 59
	   
	   if(browser.contains("chrome")) {
		   System.setProperty("webdriver.chrome.driver",projectPath+"\\src\\main\\java\\resources\\chromedriver.exe");
		   ChromeOptions chrmOptns = new ChromeOptions(); //done for headless execution
		   if(browser.contains("headless")) {  //done for headless execution
			   chrmOptns.addArguments("headless");  //done for headless execution
		   }

			   driver = new ChromeDriver(chrmOptns); //done for headless execution - in this  part the chrmOptns is blank if it doesnt go inside the headless if condition hence it will open the browsers and wont run in headless mode		   
	   }
	   
	   else if(browser.equalsIgnoreCase("IE")) {
		   
	   }
	   else if(browser.equalsIgnoreCase("firefox")) {
		   
	   }
	   
	   
	   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   log.info("driver is initialised");
	   return driver;
	   
   }
   
   public boolean objExistence(WebElement element,String ObjectDesc) {
	   boolean runVal = true;
	   
	   try {
		   if(element.isDisplayed()) {
			   log.info(ObjectDesc + "was displayed");
			   //listenersE2E.ExttestcaseReport.pass(ObjectDesc+"was displayed");   // was used when ran in Non Parallel mode
			   listenersE2E.ThreadSafeExtentReport.get().pass(ObjectDesc+"was displayed");
			   return runVal;
		   }
		   else {
			   runVal = false;
			   log.error(ObjectDesc+"was not displayed");
			   //listenersE2E.ExttestcaseReport.fail(ObjectDesc+"was not displayed");  // was used when ran in Non Parallel mode
			   listenersE2E.ThreadSafeExtentReport.get().fail(ObjectDesc+"was not displayed");
			   return runVal;
		   }
	   }
	   catch(Exception e){
		   runVal = false;
		   log.error(ObjectDesc+"was not found");
		  // listenersE2E.ExttestcaseReport.fail(ObjectDesc+"was not found"); // was used when ran in Non Parallel mode
		   listenersE2E.ThreadSafeExtentReport.get().fail(ObjectDesc+"was not found");
		  // assert.assertTrue(false, e.getMessage());
		   return runVal;
	   }
	
	
	   
   }
   
   public WebDriver switchFrame(WebElement frame) {
	   driver.switchTo().frame(frame);
	   return driver;
   }
   
   public WebDriver switchToNoFrame() {
	   driver.switchTo().defaultContent();
	   return driver;
   }
   
   //By this fun ctipn I have tried to return two different data types which is the Boolean for assertion and other is for error value
   public HashMap<Boolean,String> objExistenceHash(WebElement element) {
	   boolean runVal = true;
	   HashMap<Boolean,String> hsExceptionObj = new HashMap<Boolean,String>();
	   
	   try {
		   if(element.isDisplayed()) {
			   hsExceptionObj.put(runVal, "Object is found and displyed");
			   return hsExceptionObj;
		   }
		   else {
			   runVal = false;
			   hsExceptionObj.put(runVal, "Object is found but not displyed");
			   return hsExceptionObj;
		   }
	   }
	   catch(Exception e){
		   runVal = false;
		  // assert.assertTrue(false, e.getMessage());
		   hsExceptionObj.put(runVal, "Object is not found");
		   return hsExceptionObj;
	   }
		
	   
   }
   
   
   //TAking Screeshots
   
   public void takeScreenShots(String fileName,WebDriver driver) throws IOException {
	   TakesScreenshot ts = (TakesScreenshot) driver;
	   File source = ts.getScreenshotAs(OutputType.FILE);
	   String destinationFilePath = System.getProperty("user.dir")+"\\reports\\screenshots\\"+fileName+".png";
	   File dstfile = new File(destinationFilePath);
	   FileUtils.copyFile(source,dstfile);
	   listenersE2E.ThreadSafeExtentReport.get().addScreenCaptureFromPath(destinationFilePath,fileName);
	 //  return destinationFilePath;
	  
   }
   
 
   
}
