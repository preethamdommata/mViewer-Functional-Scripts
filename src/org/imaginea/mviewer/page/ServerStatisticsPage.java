package org.imaginea.mviewer.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ServerStatisticsPage {
WebDriver driver;
	public ServerStatisticsPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void verifyTable(){
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#mainBodyHeader.tab-cont")).isDisplayed(), "The Table Heading is not diaplyed");
	}
}
