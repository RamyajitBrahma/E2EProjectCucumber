package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {
    WebDriver driver;
    
    public loginPage(WebDriver driver) {
    	this.driver = driver;
    	PageFactory.initElements(driver, this);
    }
    
    @FindBy(id="user_email")
    private WebElement emailIDEditBox;
    
    
    @FindBy(id="user_password")
    private WebElement pswrdEditBox;
    
    @FindBy(xpath="//input[@value='Log In']")
    private WebElement loginButton;
    
    public WebElement emailIdBox() {
    	return emailIDEditBox;
    }
    
    public WebElement pswrd() {
    	return pswrdEditBox;
    }
    
    public WebElement loginBtn() {
    	return pswrdEditBox;
    }
    
}
