package stepDefs;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ArticlePage;
import pages.DeleteArticlePage;
import pages.LoginPage;

public class ArticleStepDef {
	
	static WebDriver driver;
	static LoginPage loginP;
	static ArticlePage articleP;
	static DeleteArticlePage deleteP;
	
	@BeforeAll
	public static void setup() throws IOException	{
		
		System.out.println("Before any scenarios.........."); 	}
	
	//Login Process
	@Given("User is on login Page")
	public void user_is_on_login_page() throws IOException {
		TestBase.initDriver();
		driver = TestBase.getDriver();
		loginP= new LoginPage(driver);
		articleP = new ArticlePage(driver);
		deleteP= new DeleteArticlePage(driver);
		TestBase.openURL("https://conduit-realworld-example-app.fly.dev/");
		loginP.launchLoginPage();
	}	
	@When("User enters {string} and {string}")
	public void user_enters_and(String name, String pwd) {
		 loginP.login(name, pwd);
		 loginP.verifyLoginSuccess();
	}
	@Then("User should be on Home page")
	public void user_should_be_on_home_page() {
		Assert.assertTrue(loginP.verifyLoginSuccess(),"Your Feeds not shown");
	}
	
	//Create Article Page
	@Given("User should be Article Page")
	public void user_should_be_article_page() {
	    articleP.launchArticle();
		Assert.assertTrue(articleP.isArticlePageDisp(),"Article page not displayed");
	}
	@When("User Create Article {string} and {string} and {string} and {string}")
	public void user_create_article_and_and_and(String title, String desc, String body, String tag) {
		
		  articleP.enterArticleDetails(title,desc,body,tag);
		  articleP.publishArticle();
		  
		  }
	
	@Then("Article must be Created")
	public void article_must_be_created() {
		boolean success = articleP.verifyHeader();
		if(success) {
		  System.out.println("New Article Published Successfully");}
		else { System.out.println("Title Already Exists");
		}
		}
	
	//Update Article
	@When("User Update an Article")
	public void user_update_an_article(DataTable dataTable) {
		List<Map<String,String>> users= dataTable.asMaps();
		String title1 = users.get(0).get("title");
		System.out.println("....."+title1);
		if(title1.equalsIgnoreCase("TestRP17")) {
		  articleP.editArticle();
		  articleP.updateArticleDetails("TESTRP13","JAVA","Testing Concepts - JAVA and Selenium","RP1");
		  articleP.publishArticle();}
		  	}
	
	@Then("Article Should be Updated")
	public void article_should_be_updated() {
		  articleP.verifyHeader();
		  System.out.println("Updated Article");
	}

	
	@When("User Delete an Article")
	public void user_delete_an_article(DataTable dataTable) {
		List<Map<String,String>> users= dataTable.asMaps();
		String title1 = users.get(0).get("title");
		if(title1.equalsIgnoreCase("TestRP17")) {
			deleteP.DeleteArtcile();}
		  	}
	
	
	@Then("Article Should be Deleted")
	public void article_should_be_deleted() {
		
		   String deletedS = deleteP.isDeleted();
		   System.out.println("Deleted: "+deletedS);
	}
	
	@After
	  public void attachScreen(Scenario scenario) {
		  if(scenario.isFailed()) {
			  TakesScreenshot screen = (TakesScreenshot) driver;
			  byte[] img = screen.getScreenshotAs(OutputType.BYTES);
			  scenario.attach(img, "image/png", "FailedScenarioImage");
		  }

	}}
