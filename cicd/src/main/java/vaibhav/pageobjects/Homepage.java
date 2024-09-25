package vaibhav.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Homepage {
	WebDriver driver;

	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "welcomeMessage")
	WebElement welcomeMsg;

	public void homepageMsg(String user) {

		String actualWelcomeMsg = welcomeMsg.getText();
		String expectedWelcomeMsg = "Hello, " + user;
		Assert.assertEquals(actualWelcomeMsg, expectedWelcomeMsg);
	}

}
