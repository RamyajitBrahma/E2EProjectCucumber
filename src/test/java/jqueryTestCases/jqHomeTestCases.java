package jqueryTestCases;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jqueryModulePageObject.jqHomePage;
import resources.baseOR;
import testE2EProject.listenersE2E;


public class jqHomeTestCases extends baseOR {

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(jqHomeTestCases.class.getName());
	public jqHomePage HomePage;


	
	@Test
	public void DragDropTC1() throws IOException {


		HomePage = new jqHomePage(driver);
		// validating the header of the page
		if (objExistence(HomePage.homePageHeader(), "jquery Home page header")) {
			listenersE2E.ThreadSafeExtentReport.get().pass("Landed on jqUery home successfully");
			log.info("Landed on jqUery home successfully");
			Assert.assertTrue(true, "Landed on jqUery home successfully");
		} else {
			listenersE2E.ThreadSafeExtentReport.get().fail("Did not Land on jqUery home ");
			log.error("Did not Land on jqUery home");
			Assert.assertTrue(false, "Did not Land on jqUery home");
			takeScreenShots("HomePageLanding", driver);
		}

		//Switching framw
		driver=switchFrame(HomePage.iFrameFirst());

		
        // drag dropping an abject
		if ((objExistence(HomePage.draggableObj(), "Draggable object"))
				&& (objExistence(HomePage.droppableObj(), "Droppable object"))) {

			Actions actionObj = new Actions(driver);
			actionObj.dragAndDrop(HomePage.draggableObj(), HomePage.droppableObj());
			listenersE2E.ThreadSafeExtentReport.get().pass("Drag and Drop is successful");
			Assert.assertTrue(true, "Drag and Drop is successful");
			log.info("Drag and Drop is successful");
		} 
		else {
			listenersE2E.ThreadSafeExtentReport.get().fail("Drag and Drop is unsuccessful");
			log.error("Drag and Drop is unsuccessful");
			Assert.assertTrue(false, "Drag and Drop is unsuccessful");
			takeScreenShots("DragDrop", driver);
		}

		driver=switchToNoFrame();//switching to default content
		
	}
	
	@Test(dependsOnMethods= {"DragDropTC1"})
	public void acceptLinkFuncTC2() throws IOException, InterruptedException {
		List<WebElement> examplelinkList = HomePage.exampleslinkList();
		
		for(int lnkcnt=1;lnkcnt<examplelinkList.size();lnkcnt++) {
			if(examplelinkList.get(lnkcnt).getText().equalsIgnoreCase("Accept")) {
				examplelinkList.get(lnkcnt).click();
				listenersE2E.ThreadSafeExtentReport.get().pass("Accept link found and clicked");
				Assert.assertTrue(true, "Accept link found and clicked");
				log.info("Accept link found and clicked");
				break;
			}
			else if(lnkcnt>examplelinkList.size()){
				listenersE2E.ThreadSafeExtentReport.get().fail("Accept link not found");
				log.error("Accept link not found");
				Assert.assertTrue(false, "Accept link not found");
				takeScreenShots("AcceptLink", driver);
				break;
			}
		}
		
		   Thread.sleep(3000);
			//Switching framw
			driver=switchFrame(HomePage.iFrameFirst());
			//System.out.println("After inframe switch");
		//validating if one non draggable item gets created
		    boolean acceptClickRunFlag = objExistence(HomePage.draggableInvalid(), "Non Draggable object after clicking Accept Link");
			Assert.assertTrue(acceptClickRunFlag, "Non Draggable object after clicking Accept Link");
		   
			driver=switchToNoFrame();//switching to default content
		
	}
	
	@BeforeTest
	public void OpenBrowser() throws IOException {
		driver = InitializeDriver();
		String URL = propObj.getProperty("jqURL");
		driver.get(URL);
		driver.manage().window().maximize();
	}

	@AfterTest
	public void CloseBrowser() {
		driver.close();
	}
	
}
