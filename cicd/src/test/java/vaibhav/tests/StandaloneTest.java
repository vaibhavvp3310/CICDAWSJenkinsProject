package vaibhav.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vaibhav.pageobjects.Homepage;
import vaibhav.pageobjects.Login;
import vaibhav.pageobjects.Register;
import vaibhav.testcomponents.BaseTest;

public class StandaloneTest extends BaseTest {
	
	
	@Test(dataProvider = "getData")
	public void standaloneTest(HashMap<String,String> input) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Register register = new Register(driver);
		Login login = register.registration(registerPageTitle, input.get("user"), input.get("password"));
		Homepage homepage = login.loginForm(loginPageTitle, input.get("user"), input.get("password"));
		homepage.homepageMsg(input.get("user"));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		String filepath = System.getProperty("user.dir")+("\\src\\test\\java\\vaibhav\\data\\login.json");
		List<HashMap<Object, Object>> data = getJsonData(filepath);
		return new Object[][] { {data.get(0)}, {data.get(1)}};
	}

}




