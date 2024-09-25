package vaibhav.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Login {

	WebDriver driver;
	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div#login-container h2")
	WebElement loginTitle;
	
	@FindBy(id="loginUsername")
	WebElement loginUsername;
	
	@FindBy(id="loginPassword")
	WebElement loginPassword;
	
	@FindBy(css="form#loginForm button")
	WebElement button;
	
	@FindBy(id="loginMessage")
	WebElement loginMsg;
	
	public Homepage loginForm(String loginPageTitle, String user, String password ) {
		String actualLoginTitle =  loginTitle.getText();
		Assert.assertEquals(actualLoginTitle, loginPageTitle);
		loginUsername.sendKeys(user);
		loginPassword.sendKeys(password);
		button.click();
		return new Homepage(driver);
	}
	
	public void errorCheck(String actualErrorMsg) {
		loginUsername.sendKeys("vabhav");
		loginPassword.sendKeys("123");
		button.click();
		Assert.assertEquals(loginMsg.getText(), "Invalid username or password.");
	}
	
	
}

