package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BasePage;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement enterEmail;
	
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement enterPassword;
	
	
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement clickLoginButton;
	
	
	public void setEmail(String email) {
		enterEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		enterPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		clickLoginButton.click();
	}
	
	

}
