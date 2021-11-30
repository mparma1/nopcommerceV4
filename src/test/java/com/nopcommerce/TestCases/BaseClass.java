package com.nopcommerce.TestCases;
//import com.nocommerce.utilies.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.nopcommerce.utilities.ReadConfig;



// GIT URL:-https://github.com/mparma1/nopcommerceV2.git
//Token :   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
public class BaseClass {
	ReadConfig readconfig= new ReadConfig();
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUserEmail();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br)
	{
		logger=Logger.getLogger("nopCommerceV2");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\caddstudio\\eclipse-workspace\\nopCommerceV2\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\caddstudio\\eclipse-workspace\\nopCommerceV2\\Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
			
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Users\\caddstudio\\eclipse-workspace\\nopCommerceV2\\Drivers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
	}
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/screenshots/"+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public static String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
}
