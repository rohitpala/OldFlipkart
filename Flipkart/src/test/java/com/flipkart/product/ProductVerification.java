package com.flipkart.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.init.AbstractPage;
import com.flipkart.init.Init;

public class ProductVerification extends AbstractPage{

	public ProductVerification(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath=".//div[@class='_3ycxrs _2Rwa71']")
	WebElement productDiv;
	public boolean verifyProductInCart()
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='_3ycxrs _2Rwa71']")));
		
		if(productDiv.isDisplayed())
			return true;
		else
			return false;
	}
}
