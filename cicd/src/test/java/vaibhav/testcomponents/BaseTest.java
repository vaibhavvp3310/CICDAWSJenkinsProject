package vaibhav.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	
	public WebDriver driver;
	public String registerPageTitle = "Register";
	public String loginPageTitle = "Login";
	
	
	public void initializeDriver() throws IOException {
	Properties properties = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "vaibhav" + File.separator + "resources" + File.separator + "globalData.properties");
    properties.load(fis);
    String browser = System.getProperty("browser")!= null? System.getProperty("browser"): properties.getProperty("browser");
    if(browser.contains("chrome")) {
    	//firefox code
    	System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		 ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
        options.addArguments("--disable-gpu"); // Applicable to Windows
        options.addArguments("--remote-debugging-port=9222");
		driver = new ChromeDriver(options);
    	
    }else if(browser.contains("edge")) {
    	System.setProperty("webdriver.edge.driver","C:\\Users\\vaibh\\Downloads\\msedgedriver.exe");
		driver = new EdgeDriver();
    }else if(browser.contains("firefox")) {
    	//firefox code
    }
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void landingPage() throws IOException {
		initializeDriver();
		driver.get("http://3.97.11.241");
	}
	
	@AfterMethod
	public void afterExecution() {
		driver.close();
	}
	
	public List<HashMap<Object, Object>> getJsonData(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
	    ObjectMapper mapper = new ObjectMapper();
	    List<HashMap<Object,Object>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<Object,Object>>>() {
		});
	    return data;
	}
	
}
