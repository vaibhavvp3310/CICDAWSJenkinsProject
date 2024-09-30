package vaibhav.tests;

import org.testng.annotations.Test;

import vaibhav.pageobjects.Login;
import vaibhav.pageobjects.Register;
import vaibhav.testcomponents.BaseTest;

public class ErrorValidation extends BaseTest {
	String actualErrorMsg = "Invalid username or password.";
	//test comment
	@Test
	public void errorCheck() {
		Register register = new Register(driver);
		Login login = register.registration(registerPageTitle, "vaibh", "abc123");
		login.errorCheck(actualErrorMsg);
	}
	
}
