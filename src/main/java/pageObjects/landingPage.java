package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage {
    
	WebDriver driver;
	public landingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//"//a[contains(@href,'sign_in')]"
	@FindBy(xpath="//a[contains(@href,'sign_in')]")
	private WebElement signInButton;
	
	@FindBy(xpath="//h2[contains(text(),'Featured Courses2')]")   //can do it this way also
	//@FindBy(xpath="(//div[@class='text-center'])[1]/h2")   //can do it this way also
	private WebElement FeaturedCourses;
	
	public WebElement signInButton() {
		return signInButton;
	}
	
	public WebElement FeaturedCOursePane() {
		return FeaturedCourses;
	}
	
}
