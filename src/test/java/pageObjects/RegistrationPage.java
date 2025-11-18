package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BasePage;

public class RegistrationPage extends BasePage {
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") 
	public WebElement msgConfirmation;
	
	public void enterFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void enterLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
		public void enterTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
		
		public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
			
		public void enterConfirmPassword(String confirmpassword) {
		txtConfirmPassword.sendKeys(confirmpassword);
	}
		public void clickOnPrivacyPolicyCheckbox() {
		chkdPolicy.click();
	}
		
		public void clickOnContinueButton() {
		btnContinue.click();
	}
		
	public String getConformationMsg() {
		try {
			return msgConfirmation.getText();
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	
	
	
}
