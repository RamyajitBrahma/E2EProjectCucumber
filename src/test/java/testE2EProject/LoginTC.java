package testE2EProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.landingPage;
import pageObjects.loginPage;
import resources.baseOR;

public class LoginTC extends baseOR {
	//landingPage homePage; can be done also
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(LoginTC.class.getName());
	public landingPage homePage;
	public loginPage loginPg;
	
	@Test(dataProvider="LoginTCgetData")
	public void basePageNavigation(String emailID,String passWord,String RunFlag) throws IOException {

		driver = InitializeDriver();
		String URL = propObj.getProperty("URL");
		//System.out.println(URL);
		driver.get(URL);
		varCheck=5;
		System.out.println("LoginTC"+varCheck);
		driver.manage().window().maximize();
		//landing on home clickijng signin buttomn
		navigateToSignInPage(emailID,passWord,RunFlag);
		
	}

	public void navigateToSignInPage(String emailID,String passWord,String RunFlag) throws IOException {
		homePage = new landingPage(driver);
		String imagePath;
		if(RunFlag.contains("Sign In")) {
			
			if(objExistence(homePage.signInButton(),"SignIn Button")) {
				homePage.signInButton().click();
				log.info("Sign In Button clicked");
				// listenersE2E.ExttestcaseReport.info("Sign In Button CLicked"); // was used when ran in Non Parallel mode
				 listenersE2E.ThreadSafeExtentReport.get().info("Sign In Button CLicked");
				 takeScreenShots("SignOnButton",driver);
			}
						
			// enter email and passwird and click login
			loginPg = new loginPage(driver);
			
			if(objExistence(loginPg.emailIdBox(),"Email ID Box")) {
				loginPg.emailIdBox().sendKeys(emailID);
				log.info("Entered valid Email ID In Email ID Box");
				//listenersE2E.ExttestcaseReport.info("Entered valid Email ID In Email ID Box"); // was used when ran in Non Parallel mode
				listenersE2E.ThreadSafeExtentReport.get().info("Entered valid Email ID In Email ID Box");
				takeScreenShots("EmailIDBox",driver);
				
			}
			
			loginPg.pswrd().sendKeys(passWord);
			loginPg.loginBtn().click();
			
			//Validatijg if the title of the page is correct
			//System.out.println(driver.getTitle());
			if(driver.getTitle().contains("Rahul Shetty Academy")) {
				Assert.assertEquals(driver.getTitle(),"Rahul Shetty Academy");
				listenersE2E.ThreadSafeExtentReport.get().pass("Correct title was displayed");
				log.info("Correct title was displayed");

			}
			else {
				Assert.assertEquals(driver.getTitle(),"Rahul Shetty Academy");
				listenersE2E.ThreadSafeExtentReport.get().fail("Correct title was not displayed");
				log.error("Correct title was not displayed");
				takeScreenShots("correcttitle",driver);
			}
			
		}

	}
	
	@AfterTest
	public void CloseBrowser() {
		driver.close();
	}
	
	//This function is the data sheet
	@DataProvider
	public Object[][] LoginTCgetData() {
		Object[][] data = new Object[1][3];
		data[0][0] = "Ramyajit";
		data[0][1] = "Anuradha@123";
		data[0][2] = "GoTo Sign In Page";
		return data;
		
		
	}
	
}
