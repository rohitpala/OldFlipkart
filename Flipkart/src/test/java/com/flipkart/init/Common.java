package com.flipkart.init;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Common extends Init{

	public WebDriver driver;
	public Common(WebDriver driver) {
		super();
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void clickonMenu(String mainmenu, String submenu) 
	{
		driver.findElement(By.xpath(".//a[text()='"+mainmenu+"']")).click();
		driver.findElement(By.xpath(".//ul[@id='treemenu']//li//a[text()='"+submenu+"']")).click();
	}
	
	public static void clickableElement(By locator, WebDriver driver) 
	{	
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static void Pause(int secs) 
	{
		try 
		{
			Thread.sleep(secs * 1000);
		} 
		catch (InterruptedException interruptedException) 
		{
			interruptedException.printStackTrace();
		}
	}
	public String takeScreenshot(String name) throws IOException
	{
		String dirPath= "C:\\Users\\admin\\eclipse-workspace\\Flipkart\\";
		String newDirName ="Screenshot1";
		boolean isCreated;
		File oneMoreDirectory = new File(dirPath+File.separator+newDirName);
		String destination =oneMoreDirectory.getAbsolutePath()+"\\";
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		isCreated = oneMoreDirectory.mkdir();
		driver.navigate().refresh(); 
		if (isCreated) 
		{
            System.out.printf("\n Successfully created new directory, path:%s", oneMoreDirectory.getCanonicalPath());
        } 
		else
		{ //Directory may already exist
            System.out.println("\n Unable to create directory or directory is available ");
        }
		
		File screenShotUrl;
		String filePath;
		screenShotUrl= new File(destination+name+".png");
		FileHandler.copy(scrFile,screenShotUrl);
		filePath = screenShotUrl.toString();	
		return filePath;
	}
	
	public void log(int no,String msg)
	  {
		  Reporter.log("Step: -> "+ no++ + " " + msg + "<br>");
		  System.out.println("Step: -> "+ no++ + " " + msg);
	  }
	  public void logOnlyMsg(String msg)
	  {
		  Reporter.log(msg + "<br>");
		  System.out.println(msg);
	  }
	  
	  public void rlog(String r)
	  {
			if(r.equals("Pass"))
			{
			Reporter.log("<b><font color='green'>" + r + "</font></b>" +"</br>");
			System.out.println(r);
			}
			else if(r.equals("Fail"))
			{
			Reporter.log("<b><font color='red'>" + "<b>" + r + "</font></b>" +"</br>");
			System.out.println(r);
			}
	}
	  
	  public static String getCurrentTimeStampString() 
		{
			java.util.Date date = new java.util.Date();
			SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSS");
			TimeZone timeZone = TimeZone.getDefault();
			Calendar cal = Calendar.getInstance(new SimpleTimeZone(timeZone.getOffset(date.getTime()), "IST"));
			sd.setCalendar(cal);
			return sd.format(date);
		}
	  
//	  public static void clickableElement(WebElement webelement, WebDriver driver) 
	  public static void clickableElement(WebElement webelement, WebDriver driver) 
		{
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(webelement));
		}
	  
	  public static void waitForElement(WebElement webelement, WebDriver driver) 
		{
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(webelement));
		}
}
