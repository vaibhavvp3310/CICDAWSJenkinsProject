package vaibhav.pageobjects;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class Register {
	
	WebDriver driver;
	
	public Register(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".container h2")
	WebElement registerTitle;
	
	@FindBy(id="registerUsername")
	WebElement registerUsername;
	
	@FindBy(id="registerPassword")
	WebElement registerPassword;
	
	@FindBy(css="button[type='submit']")
	WebElement submit;
	
	public Login registration(String registerPageTitle, String user, String pass) {
		String actualRegisterTitle = registerTitle.getText();
		Assert.assertEquals(actualRegisterTitle, registerPageTitle);
		
		registerUsername.sendKeys(user);
		
		
		registerPassword.sendKeys(pass);
		
		submit.click();
		
		return new Login(driver);
	}
	
	

}
