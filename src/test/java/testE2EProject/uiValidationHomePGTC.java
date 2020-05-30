package testE2EProject;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.landingPage;
import resources.baseOR;

public class uiValidationHomePGTC extends baseOR {
	public WebDriver driver; 
	public static Logger log = LogManager.getLogger(uiValidationHomePGTC.class.getName());
	@Test
	public void UIValidationTCHomePage() throws IOException {
	
	HashMap<Boolean,String> hsExceptionObjExistence = new HashMap<Boolean,String>();	
	driver = InitializeDriver();
	String URL = propObj.getProperty("URL");
	//System.out.println(URL);
	driver.get(URL);
	driver.manage().window().maximize();

	System.out.println("UI Val"+varCheck);
	//to vbalidate if the Featured Course header is displayed or not
	//loginClassObj.homePage.FeaturedCOursePane().isDisplayed(); // can be done if homepage object is marked as  class variable in LoginTc.class
	landingPage homePage = new landingPage(driver);
	System.out.println(driver.getTitle());
	//Assert.assertTrue(homePage.FeaturedCOursePane().isDisplayed());
		
	Assert.assertTrue(objExistence(homePage.FeaturedCOursePane(),"Featured Course Header"),"Featured Course Header Object could not be found/not displayed");
	
	
	}

	@AfterTest
	public void CloseBrowser() {
		driver.close();
	}

}
