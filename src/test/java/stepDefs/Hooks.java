package stepDefs;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {
	
	@BeforeAll
  public static void setupDriver() throws IOException {
	  TestBase.initDriver();
//	  Art = new ArticleStepDef();
  }
  
  @After
  public void attachScreen(Scenario scenario) {
	  if(scenario.isFailed()) {
		  TakesScreenshot screen = (TakesScreenshot) TestBase.getDriver();
		  byte[] img = screen.getScreenshotAs(OutputType.BYTES);
		  scenario.attach(img, "image/png", "FailedScenarioImage");
	  }  
}

  @AfterAll
  public static void tearDown() {
    TestBase.tearDown();
	}

}