package testE2EProject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.landingPage;
import resources.baseOR;

public class uiValidationHomePGTCerror extends baseOR {
	public WebDriver driver;
	@Test(dataProvider="UIValidationHomePageGetData")
	public void UIValidationTCHomePage(String emailID,String passWord,String RunFlag) throws IOException {
	/*		
	driver = InitializeDriver();
	String URL = propObj.getProperty("URL");
	//System.out.println(URL);
	driver.get(URL);
	driver.manage().window().maximize();
	*/
	driver = InitializeDriver();	
		
	LoginTC loginClassObj = new LoginTC();
	loginClassObj.basePageNavigation(emailID, passWord, RunFlag);
	System.out.println("UI Val"+varCheck);
	//to vbalidate if the Featured Course header is displayed or not
	//loginClassObj.homePage.FeaturedCOursePane().isDisplayed(); // can be done if homepage object is marked as  class variable in LoginTc.class
	landingPage homePage = new landingPage(driver);
	System.out.println(driver.getTitle());
	//Assert.assertTrue(homePage.FeaturedCOursePane().isDisplayed());
	Assert.assertTrue(homePage.FeaturedCOursePane().isDisplayed(), "Featureed Header is not displayed in Home Page");
	
	
	}
	
	@DataProvider
	public Object[][] UIValidationHomePageGetData() {
		Object[][] data = new Object[1][3];
		data[0][0] = "NA";
		data[0][1] = "NA";
		data[0][2] = "Stay On HomePage";
		return data;
		
		
	}
}
