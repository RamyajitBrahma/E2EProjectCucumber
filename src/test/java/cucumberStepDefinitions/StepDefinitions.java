package cucumberStepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import pageObjects.landingPage;
import pageObjects.loginPage;
import resources.baseOR;
import testE2EProject.LoginTC;
import testE2EProject.listenersE2E;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@RunWith(Cucumber.class)

public class StepDefinitions extends baseOR {

	public WebDriver driver;
	public landingPage homePage;
	public loginPage loginPg;
	//public static Logger log = LogManager.getLogger(stepDefinitions.class.getName());
	
    @Given("^Initialising the driver$")
    public void initialising_the_driver() throws Throwable {
    	
    	driver = InitializeDriver();
    	
    }

    /*
    @And("^Initialising ExtentReport$")
    public void initialising_extentreport() throws Throwable {
    	cucumberExtentReport.extentReportGen("Login TC Cucumber");
    }
   */
    @Then("^Verify the error message is displayed$")
    public void verify_the_error_message_is_displayed() throws Throwable {
        String errMesg;
        boolean errMsgRunFlag;
        errMsgRunFlag=objExistence(loginPg.errormessage(),"Error message");
    	Assert.assertTrue(errMsgRunFlag,"Error Message is not displayed");
    	
    	if(errMsgRunFlag=true) {
        	errMesg=loginPg.errormessage().getText();
        	if(errMesg.contains("Invalid")) {
        		listenersE2E.ThreadSafeExtentReport.get().pass("Correct Error message : "+errMesg+ "displayed");
        		//log.info("Correct error message was displayed");
        	}
        	else {
        		listenersE2E.ThreadSafeExtentReport.get().fail("InCorrect Error message : "+errMesg+ "displayed");
    			///log.error("InCorrect title was not displayed");
    			takeScreenShots("Errormessage",driver);
        	}
    	}

    	
    }
    
    
    @And("^Navigating to the (.+) site$")
    public void navigating_to_the_site(String url) throws Throwable {
    	driver.get(url);
    	driver.manage().window().maximize();
    }



 
    @When("^Click login link$")
    public void click_login_link() throws Throwable {
    	homePage = new landingPage(driver);
		if(objExistence(homePage.signInButton(),"SignIn Button")) {
			homePage.signInButton().click();
			//log.info("Sign In Button clicked");
			// listenersE2E.ExttestcaseReport.info("Sign In Button CLicked"); // was used when ran in Non Parallel mode
			listenersE2E.ThreadSafeExtentReport.get().info("Sign In Button CLicked");
			 takeScreenShots("SignOnButton",driver);
		}
    }

    @And("^Log in with valid (.+) and (.+)$")
    public void log_in_with_valid_and(String username, String password) throws Throwable {
		// enter email and passwird and click login
		loginPg = new loginPage(driver);
		
		if(objExistence(loginPg.emailIdBox(),"Email ID Box")) {
			loginPg.emailIdBox().sendKeys(username);
			//log.info("Entered valid Email ID In Email ID Box");
			//listenersE2E.ExttestcaseReport.info("Entered valid Email ID In Email ID Box"); // was used when ran in Non Parallel mode
			listenersE2E.ThreadSafeExtentReport.get().info("Entered valid Email ID In Email ID Box");
			takeScreenShots("EmailIDBox",driver);
			
		}
		
		loginPg.pswrd().sendKeys(password);
		Thread.sleep(3000);
		loginPg.loginBtn().click();
    }
    
    @And("^Close Broswers$")
    public void close_broswers() throws Throwable {
        driver.close();
    }
	
}
