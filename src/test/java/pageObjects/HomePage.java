package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BasePage;

public class HomePage extends BasePage {
	
//	1. Create a constructor for HomePage class which will call the constructor of BasePage class.
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
//	2. Locators for HomePage elements
	//a[normalize-space()='Register']
	//
	
	@FindBy(xpath="//a[@title='My Account']")
	WebElement myAccountLink;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement registerLink;
	
	//	3. Methods to interact with the elements on HomePage

	public void clickOnMyAccount() {
		myAccountLink.click();
	}
	
	public void clickOnRegister() {
		registerLink.click();
	}



}

