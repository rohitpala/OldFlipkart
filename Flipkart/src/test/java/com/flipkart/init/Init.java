package com.flipkart.init;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.flipkart.product.ProductIndexPage;
import com.flipkart.product.ProductVerification;
import com.flipkart.utility.TestData;

public class Init {
	protected WebDriver driver;
	public String testName = "";
//	protected String url = "https://www.flipkart.com/";
	public static String url;
	public static String browser;
	public static String path;
	public static String browserVersion;
	public static String osName;
	DesiredCapabilities capability;
	
	protected ProductIndexPage productIndexPage;
    protected ProductVerification productVerification;
    protected Common common;
    
    
//		@Parameters({"browser","path","url"})
		@BeforeMethod(alwaysRun = true)
//		public void openbrowser(String browser, String path, String url) throws IOException
		public void openbrowser() throws IOException
		{
			url=  TestData.getValueFromConfig("config.properties","URL");
			browser = TestData.getValueFromConfig("config.properties","Browser");
			path = TestData.getValueFromConfig("config.properties","Path");
			capability = DesiredCapabilities.chrome();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
			
			System.setProperty("webdriver."+browser+".driver", path);
			
			if(browser.equalsIgnoreCase("chrome"))
			{
			driver = new ChromeDriver();
			}
			else if (browser.equalsIgnoreCase("gecko")) {
				driver = new FirefoxDriver();	
			}
//			driver.manage().window().maximize();
			Dimension d = new Dimension(1382,744); 
			driver.manage().window().setSize(d); 
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			
			productIndexPage = new ProductIndexPage(driver);
			productVerification =  new ProductVerification(driver);
			common = new Common(driver);
		}
		
		@AfterMethod(alwaysRun = true)
		public void postConfig(ITestResult TestResult,ITestContext testContext) throws IOException
		{ 
			testName = TestResult.getName();
			int i= 1;
			if(TestResult.getStatus()==ITestResult.FAILURE)
				{
				String path  =  common.takeScreenshot(TestResult.getName());
				String s1 = "Screenshot";
				Reporter.setCurrentTestResult(TestResult);
				Reporter.log("Please look to the screenshot: ->" + "<br>");
				Reporter.log("<a href=\""+ path + "\">" + s1+i++ + "</a>" );					
				}	
			
			try {
	             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("target/teststream.txt", true)));
	             out.println("{\"metodo\":\""+testName.toString()+"\", \"status\":\""+TestResult.getStatus()+"\", "
	        + "\"classe\":\""+testContext.getClass().getName()+"\", \"descricao\":\""+testContext.getCurrentXmlTest().getName().toString()+"\"}");
	             out.close();
	         } catch (IOException e) {
	          e.printStackTrace();
	         }
			
			driver.close();
			driver.quit(); 
		}
}
