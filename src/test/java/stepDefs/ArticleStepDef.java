package stepDefs;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ArticlePage;
import pages.DeleteArticlePage;
import pages.LoginPage;

public class ArticleStepDef {
	
	 WebDriver driver;
	 LoginPage loginP;
	 ArticlePage articleP;
	 DeleteArticlePage deleteP;
	
	
	 public ArticleStepDef()
	 {
		// include the code of initialize 
			driver = TestBase.getDriver();
			loginP= new LoginPage(driver);
			articleP = new ArticlePage(driver);
			deleteP= new DeleteArticlePage(driver);
	 }
	
	 	
	//Login Process
	@Given("User is on login Page")
	public void user_is_on_login_page() throws IOException {
		
		TestBase.openURL("https://conduit-realworld-example-app.fly.dev/");
		loginP.launchLoginPage();
	}	
	@When("User enters {string} and {string}")
	public void user_enters_and(String name, String pwd) {
		 loginP.login(name, pwd);
		 
	}
	@Then("User should be on Home page")
	public void user_should_be_on_home_page() {
		loginP.verifyLoginSuccess();
		Assert.assertTrue(loginP.verifyLoginSuccess(),"Your Feeds not shown");
		System.out.println (" Login is successful!!! ");
	}
	
	//Create Article Page
	@Given("User should be Article Page")
	public void user_should_be_article_page() {
	    articleP.launchArticle();
		Assert.assertTrue(articleP.isArticlePageDisp(),"Article page not displayed");
	}
	@When("User Create Article {string} and {string} and {string} and {string}")
	public void user_create_article_and_and_and(String title, String desc, String body, String tag) {
		  System.out.println (" Landed on to Article Creation Page!!! ");
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
		
		List<Map<String,String>> users = dataTable.asMaps(String.class, String.class);
	    String oldTitle = users.get(0).get("oldtitle");
	    String newTitle = users.get(0).get("newtitle");
	    String desc  = users.get(0).get("desc");
	    String body  = users.get(0).get("body");
	    String tag   = users.get(0).get("tag");

	    System.out.println("Updating article from " + oldTitle + " to " + newTitle);
	    
	    if(oldTitle.equalsIgnoreCase("TestRP22")) {
	    // Navigate to edit page
	    articleP.editArticle();
	    // Update details
	    articleP.updateArticleDetails(newTitle, desc, body, tag);
	    // Publish changes
	    articleP.publishArticle();}
	}
	
	@Then("Article Should be Updated")
	public void article_should_be_updated() {

	    boolean success = articleP.verifyHeader();
		if(success) {
		  System.out.println("Edit Article Published Successfully");}
		else { System.out.println("Title Already Exists");
		}
	}

	
	@When("User Delete an Article")
	public void user_delete_an_article(DataTable dataTable) {
		List<Map<String,String>> users= dataTable.asMaps();
		String title1 = users.get(0).get("title");
		if(title1.equalsIgnoreCase("TestRP23")) {
			deleteP.DeleteArtcile();}
		  	}
	
	
	@Then("Article Should be Deleted")
	public void article_should_be_deleted() {
		
//		   String deletedS = deleteP.isDeleted();
		boolean deleteCheck = deleteP.isDeleted();
		System.out.println("Deleted: "+ deleteCheck);
		Assert.assertTrue(deleteCheck, "Article deletion failed!");
		
	}
	
	

	}
