package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
	
	@FindBy(xpath="//a[contains(.,'Login')]") WebElement loginH;
	@FindBy(xpath="//input[@name='email']") WebElement email;
	@FindBy(xpath="//input[@name='password']") WebElement pwd;
	@FindBy(xpath="//button[@class='btn btn-lg btn-primary pull-xs-right']") WebElement loginBtn;
	@FindBy(xpath="//button[@class='nav-link active']") WebElement loginS;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		}
	
	public void launchLoginPage()
	{
		loginH.click();
	}
	public void login(String username, String pass)
	{
		email.sendKeys(username);
		pwd.sendKeys(pass);
		loginBtn.click();
	}
	public boolean verifyLoginSuccess()
	{
			return loginS.isDisplayed();
	}
}
