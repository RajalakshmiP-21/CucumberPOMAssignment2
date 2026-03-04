package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	private static TestBase testBase;
	private static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	
	  	  
	private TestBase() throws IOException {
		 Map<String, Object> prefs = new HashMap<String, Object>();
		 //Disable the "save password" pop-up
		 prefs.put("credentials_enabled_service", false);
		 prefs.put("profile.password_managed_enabled", false);
		 //Disable the change your password pop-up related to data breach
		 prefs.put("profile.password_manager_leak_detection", false);
		 //Create ChromeOptions object
		 ChromeOptions options = new ChromeOptions();
	
		options.addArguments("--incognito");
		options.setExperimentalOption("prefs", prefs);
		
		String path =	System.getProperty("user.dir")+"//src//test//resources//configs//config.properties";
		  FileInputStream fin = new FileInputStream(new File(path));
		  prop = new Properties();
		  prop.load(fin);
		  String strBrowser = prop.getProperty("browser");
		 		  
		  if(strBrowser.equalsIgnoreCase("chrome"))
		  { 
			  driver=new ChromeDriver(options);
			 wait= new WebDriverWait(driver, Duration.ofSeconds(10));
			
			}
			  
		  else if(strBrowser.equalsIgnoreCase("edge"))
		  { 
			  driver = new EdgeDriver();
			  wait= new WebDriverWait(driver, Duration.ofSeconds(10));
			 
		  }
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		
	}
	
	public static void initDriver() throws IOException {
		if(testBase== null) {
			testBase = new TestBase();
		}
	}
	public static WebDriver getDriver() {
		return driver;
	}
	public static void openURL(String url){
			driver.get(url);
	}
	public static void tearDown() {
		if(driver!=null) {
			driver.close();
		}
		testBase = null;
	}
}
