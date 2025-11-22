package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

/*
 * Data is valid - login success -test pass -logout
 * Data is valid - login fail - test fail
 * 
 * Data is invalid - login fail - test pass
 * Data is invalid - login success - test fail
 * */

public class TC_Login_DDT_002001 extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=utilities.DataProviders.class)
	public void verify_login_DDT(String email, String password, String expectedresult) {
	
		logger.info("********** Starting TC_Login_DDT_002001 **********");
		try {
		
		HomePage hp = new HomePage(driver);
	hp.clickOnMyAccount();
	hp.clickOnLogin();
	logger.info("Navigated to Login page");
	
	LoginPage lp = new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(password);
	lp.clickLogin();
	logger.info("Provided login details and clicked on Login button");
	MyAccountPage ma = new MyAccountPage(driver);
	logger.info("Verifying Login test");
	boolean targetpage = ma.AccountTestDisplayed();
	
	/*Data is valid - login success -test pass -logout
	 * Data is valid - login fail - test fail*/
	if(expectedresult.equalsIgnoreCase("Valid")) {
		logger.info("In Valid block");
		if(targetpage==true) {
			logger.info("Login test passed");
			ma.clickLogout();
			Assert.assertTrue(true);
		} else {
			logger.error("Login test failed");
			Assert.assertTrue(false);
		}
	}/*Data is invalid - login fail - test pass
	 * Data is invalid - login success - test fail*/
	if(expectedresult.equalsIgnoreCase("Invalid")) {
		logger.info("In Invalid block");
		System.out.println("Target page status: "+targetpage);
		if(targetpage==true) {
			logger.error("Login test failed");
			ma.clickLogout();
			Assert.assertTrue(false);
		} else {
			logger.info("Login test passed");
			Assert.assertTrue(true);
		}
	}
	} catch(AssertionError e) {
		logger.debug("Debug log...Test case failed execution");
		Assert.fail();
	}
		logger.info("********** Finished TC_Login_DDT_002001 **********");
	}
}
