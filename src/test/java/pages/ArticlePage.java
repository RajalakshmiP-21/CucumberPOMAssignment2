package pages;


import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticlePage {
	
	@FindBy (xpath="//a[contains(.,'New Article')]") WebElement newArticle;
	@FindBy(xpath="//input[@name='title']") WebElement titleEle;
	@FindBy(xpath ="//input[@name='description']") WebElement descEle;
	@FindBy(xpath="//textarea[@name='body']") WebElement bodyEle;
	@FindBy(xpath="//input[@name='tags']") WebElement tagEle;
	@FindBy(xpath="//button[@type='submit']") WebElement publish;
	@FindBy(xpath="//span[@class='error-messages']") List<WebElement> errora;
	
	@FindBy(xpath="//h1") WebElement headerVer;
	
	@FindBy(xpath="//a[contains(.,'Edit Article')]") WebElement editArticle;
	@FindBy(xpath="//button[contains(.,'Update Article')]") WebElement updatetArticle;
	
	public ArticlePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		}
	
	public void launchArticle()
	{
		newArticle.click();
	}
	
	public boolean isArticlePageDisp() {	
		return newArticle.isDisplayed();
	}
	
	
	public void enterArticleDetails(String title, String desc,String body, String tag) {
		titleEle.clear();
		titleEle.sendKeys(title);
		descEle.clear();
		descEle.sendKeys(desc);
		bodyEle.clear();
		bodyEle.sendKeys(body);
		tagEle.clear();
		tagEle.sendKeys(tag);
			}
	
	public void updateArticleDetails(String title1, String desc1,String body1, String tag1) {
		titleEle.clear();
		titleEle.sendKeys(title1);
		descEle.clear();
		descEle.sendKeys(desc1);
		bodyEle.clear();
		bodyEle.sendKeys(body1);
		tagEle.clear();
		tagEle.sendKeys(tag1);
			}
	
	public void publishArticle() {
		publish.submit();
		
	}
		public boolean verifyHeader() {
		
		if(headerVer.isDisplayed()) {return true;
	}
		else {return false;
		}
		}
	
	public boolean checkErr() 
	{
//		assertTrue(error.isDisplayed());
		List<WebElement> errors = errora;
		if (!errors.isEmpty()) {
		    System.out.println("Error message: " + errors.get(0).getText());
		    return true;
		} else {
		    System.out.println("No error message displayed.");
		    return false;
		}
		
		}

		/*
		 * if(error.isDisplayed()) {return true;} else {return false;}
		 */
	
	public void editArticle() {
		editArticle.click();
			}
	
	public void updatetArticle() {
		updatetArticle.click();
	}
	
}
