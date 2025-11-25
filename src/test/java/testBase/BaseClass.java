package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String browser) throws IOException {
		logger=LogManager.getLogger(this.getClass());
		
		//Loading CONFIG.PROPERTIES file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		System.out.println(file);
		p=new Properties();
		p.load(file);

    	if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			System.out.println("Executing on remote environment");
			DesiredCapabilities cap=new DesiredCapabilities();
		//	cap.setPlatform(Platform.WIN11);
		//	cap.setBrowserName("chrome");
			//os
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}else if(os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}else {
				System.out.println("Please provide correct os name");
				
			}
			//browser
			switch(browser.toLowerCase()) {
			case "chrome":
			cap.setBrowserName("chrome");
			break;
			case "firefox":
			cap.setBrowserName("firefox");
			break;
			case "edge":
			cap.setBrowserName("edge");
			break;
			case "safari":
				cap.setBrowserName("safari");
				break;
			default:System.out.println("Please provide correct browser name");
						
			}
			//driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			URI uri = URI.create("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(uri.toURL(), cap);

			
		} else {
			System.out.println("Executing on local environment");
			switch(browser.toLowerCase()) {
			case "edge":
				System.setProperty("webdriver.edge.driver", "C:\\Users\\Nawaz\\eclipse-workspace\\seleniumTestEnvironment\\drivers\\msedgedriver.exe");
			    driver = new EdgeDriver();
			    break;
			case "chrome":
				driver=new ChromeDriver();
				break;
			case "firefox":
				driver=new FirefoxDriver();
				break;
				default:System.out.println("Please provide a valid browser name");return;
	    	}
		}
    	
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(p.getProperty("URL"));
        driver.manage().window().maximize();
    }
	
		
	
	@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
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
	
	 public String captureScreen(String tname) throws IOException {
	        
	        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	        
	        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	        
	        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	        File targetFile = new File(targetFilePath);
	        
	        sourceFile.renameTo(targetFile);
	        
	        return targetFilePath;
	    }

}
