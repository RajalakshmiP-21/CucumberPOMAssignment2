package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src\\test\\resources\\features\\",
		glue = {"stepDefs"},
		dryRun = false,
		monochrome=true,
		plugin = {"pretty",
				"html:test-output/report/HTMLreport.html",
				"json:test-output/report/Jsonreport.json",
				"rerun:test-output/report/failedscenarios.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				}
		)

public class ArticleRunner extends AbstractTestNGCucumberTests{

//	@DataProvider(parallel=true)
//	public  Object[][] scenarios(){
//		return super.scenarios();
//	}
}
