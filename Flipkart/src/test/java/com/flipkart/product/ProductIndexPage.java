package com.flipkart.product;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.init.AbstractPage;
import com.flipkart.init.Common;

public class ProductIndexPage extends AbstractPage{
	

	public ProductIndexPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
//	protected WebDriver driver;
	
	@FindBy(xpath=".//span[@class='_1QZ6fC _3Lgyp8'][text()='Electronics']")
	WebElement electronics;
	@FindBy(xpath=".//button[@class='_2AkmmA _29YdH8']")
	WebElement closeLoginPopup;
		
	public ProductVerification mouseHoveronElectronics()
	{
		closeLoginPopup.click();
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@class='_1QZ6fC _3Lgyp8'][text()='Electronics']")));
		Actions action = new Actions(driver);
		action.moveToElement(electronics).perform();
		return new ProductVerification(driver);
	}
	
	@FindBy(xpath=".//a[@title='Realme']")
	WebElement realme6;
	public ProductVerification clickonRealme()
	{
		realme6.click();
		return new ProductVerification(driver);
	}
	
	@FindBy(xpath=".//div[@class='_3G9WVX oVjMho']/div[@class='_3aQU3C']")
	WebElement sourceLocator;
	@FindBy(xpath=".//div[@class='_1GEhLw'][text()='4 GB']")
	WebElement gb4Checkbox;
	@FindBy(xpath=".//div[@class='_1xHtJz'][text()='Price -- Low to High']")
	WebElement lowtohighPrice;
	
	public ProductVerification clickonFilter()
	{
		Actions action = new Actions(driver);
		action.dragAndDropBy(sourceLocator, 80, 0).build().perform();
//		Common.Pause(1);
		gb4Checkbox.click();
//		Common.Pause(1);
		lowtohighPrice.click();
		return new ProductVerification(driver);
	}
	
	@FindBy(xpath=".//div[@class='_3wU53n'][text()='Realme 1 (Diamond Red, 64 GB)']")
	WebElement clickOnProductimg;
	public ProductVerification selectProduct()
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='_3wU53n'][text()='Realme 1 (Diamond Red, 64 GB)']")));
		clickOnProductimg.click();
		return new ProductVerification(driver);
	}
	
	@FindBy(xpath=".//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")
	WebElement productAddtoCart;
	public ProductVerification addToCart()
	{
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		productAddtoCart.click();
		return new ProductVerification(driver);
	}
}
