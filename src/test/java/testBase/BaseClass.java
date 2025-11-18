package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public WebDriver driver;
	public Logger logger;
	@BeforeClass
	public void setup() {
		logger=LogManager.getLogger(this.getClass());
		
//		 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver");
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.manage().window().maximize();
		
	}
	@AfterClass
	public void tearDown() {
		if (driver != null) {
            driver.quit();
        }
	}
	
	public String getRandomString(int num) {
		String randomstring=RandomStringUtils.randomAlphabetic(num);
		return randomstring;
	}
	
	public String getPasswordAlphaNumaric() {
		String randomAlpha= RandomStringUtils.randomAlphanumeric(8);
		String specialChar=RandomStringUtils.randomNumeric(2);
		String randomPass=randomAlpha+"@#$"+specialChar;
		return randomPass;
	}
	
	public String getRandomPhoneNumber() {
	return	RandomStringUtils.randomNumeric(10);
	}

}
