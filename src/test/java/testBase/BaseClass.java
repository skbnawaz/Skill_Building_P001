package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		logger=LogManager.getLogger(this.getClass());
		
		//Loading CONFIG.PROPERTIES file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		System.out.println(file);
		p=new Properties();
		p.load(file);
		
		
		
//		 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver");
		switch(br.toLowerCase()) {
		case "chrome": driver = new ChromeDriver(); break;
		case "edge":driver = new EdgeDriver(); break;
		case "firefox":driver=new FirefoxDriver(); break;
		default: System.out.println("Invalid Browser"); return;
		}
		
//		driver=new ChromeDriver();
//		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("URL"));
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
