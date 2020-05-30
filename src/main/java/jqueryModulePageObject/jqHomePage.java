package jqueryModulePageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class jqHomePage {

	WebDriver driver;
	public jqHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	By homePageHeader = By.xpath("//h2[@class='logo']/a[contains(text(),'jQuery')]");
	By draggableObj = By.xpath("//div[@id='draggable']");
	By droppableObj = By.xpath("//div[@id='droppable']");
	By draggableInvalid = By.xpath("//div[@id='draggable-nonvalid']");
	//first frame
	By iFrame1 = By.xpath("//iframe[@class='demo-frame']");
	By linkList = By.tagName("a");
	By exampleSection = By.xpath("//div[@class='demo-list']");
	
	
	public WebElement homePageHeader() {
		return driver.findElement(homePageHeader);
	}
	
	public WebElement exampleSection() {
		return driver.findElement(exampleSection);
	} 
	
	public List<WebElement> exampleslinkList() {
		WebElement exampleSectionObj = driver.findElement(exampleSection);
		return exampleSectionObj.findElements(By.tagName("a"));
	}
	
	public WebElement draggableObj() {
		return driver.findElement(draggableObj);
	}
	
	public WebElement draggableInvalid() {
		return driver.findElement(draggableInvalid);
	} 
	
	public WebElement droppableObj() {
		return driver.findElement(droppableObj);
	}
	
	public WebElement iFrameFirst() {
		return driver.findElement(iFrame1);
	}
	
}
