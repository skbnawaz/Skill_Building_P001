package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_Login_002 extends BaseClass {
	
	@Test(groups= {"Regression","Master"})
	public void Login_verification_test() {
		logger.info("********** Starting TC_Login_002 **********");
		try {
		
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccount();
		hp.clickOnLogin();
		logger.info("Navigated to Login page");
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail("panda17@gmail.com");
		lp.setPassword("pandapanda");
		lp.clickLogin();
		logger.info("Provided login details and clicked on Login button");
		MyAccountPage ma = new MyAccountPage(driver);
		logger.info("Verifying Login test");
		boolean status = ma.AccountTestDisplayed();
		if(status) {
			logger.info("Login test passed");
		Assert.assertTrue(true);
		//	Assert.assertEquals(loginstatus, true,"Login Successful");
		} else {
			logger.error("Login test failed");
			Assert.assertTrue(false);
		//	Assert.assertEquals(loginstatus, false,"Login Failed");

		}
		} catch(AssertionError e) {
			logger.debug("Debug log...");
			Assert.fail();
		}
		
	}

}
