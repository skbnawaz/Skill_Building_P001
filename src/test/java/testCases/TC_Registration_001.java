package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC_Registration_001 extends BaseClass {
    // REMOVE THIS LINE: WebDriver driver;

    @Test
    public void verify_account_registration() {
    	System.out.println("********** Starting TC_Registration_001 **********");
    	logger.info("********** Starting TC_Registration_001 **********");
    	try {
    		
    	
        HomePage hp = new HomePage(driver);
        hp.clickOnMyAccount();
        logger.info("Clicked on My Account link");
        hp.clickOnRegister();
        logger.info("Clicked on Register link");
        
        
        RegistrationPage regpage = new RegistrationPage(driver);
        regpage.enterFirstName(getRandomString(5));
        regpage.enterLastName(getRandomString(7));
        regpage.enterEmail(getRandomString(5) + "@gmail.com");
        regpage.enterTelephone(getRandomPhoneNumber());
        String pass = getPasswordAlphaNumaric();
        regpage.enterPassword(pass);
        regpage.enterConfirmPassword(pass);
        regpage.clickOnPrivacyPolicyCheckbox();
        logger.info("Entered all the details in registration form");
        regpage.clickOnContinueButton();
        logger.info("Clicked on Continue button");
        
        String confmsg = regpage.getConformationMsg();
        logger.info("Fetched the confirmation message after registration");
        if(confmsg.equals("Your Account Has Been Created")) {
			logger.info("Account registration successful");
			Assert.assertTrue(true);
        } else {
        	logger.error("Test case TC_Registration_001 failed due to an exception: msg conformation not matched");
        	Assert.assertTrue(false);
        }

    	}catch(AssertionError e) {
    		
    		logger.debug("Debug log...");
    		Assert.fail();
		}
    	logger.info("********** Finished TC_Registration_001 **********");
    	}
    
}