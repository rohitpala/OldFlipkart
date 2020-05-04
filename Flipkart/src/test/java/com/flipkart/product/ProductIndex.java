package com.flipkart.product;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.flipkart.init.Common;
import com.flipkart.init.Init;

public class ProductIndex extends Init {
	
	int no =1;
	  int numOfFailure = 0;
	  @Test //(enabled = false)
	  public void flipkart() throws InterruptedException
		{
		
			common.log(no++, "Open the URL -> " + url);
			productVerification = productIndexPage.mouseHoveronElectronics();
			common.log(no++, "Mouse hover on 'electronics'");
			Common.Pause(1);
	
			productVerification = productIndexPage.clickonRealme();
			common.log(no++, "Click on 'Realme'");
		 
			
			productIndexPage.clickonFilter();
			common.log(no++, "Select price range from dragger");
			common.log(no++, "Select '4 GB' checkbox");
			common.log(no++, "Click on 'Price low to high'");
			
			productIndexPage.selectProduct();
			common.log(no++, "Click on First product");
			Common.Pause(1);
			
			productIndexPage.addToCart();
			common.log(no++, "Click on 'Add to Cart' button");
			
			common.logOnlyMsg("Verify to check cart is not empty: -> ");
			if(productVerification.verifyProductInCart())
			{
				common.rlog("Pass");
			}
			else {
				common.rlog("Fail");
				numOfFailure++;
			}	
			if (numOfFailure > 0) {
				Assert.assertTrue(false);
			} 
			Common.Pause(2);	
	  }
}
