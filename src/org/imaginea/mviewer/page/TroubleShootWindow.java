package org.imaginea.mviewer.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TroubleShootWindow {

	WebDriver driver;
	public TroubleShootWindow(WebDriver driver){
		this.driver = driver;
	}
	
	public void verifyTroubleShootPage(){
		Assert.assertTrue(driver.findElement(By.cssSelector("button#logLevelChangeBtn.bttn")).isDisplayed(), "Change button on TroubleShoot page is not displayed.");
	}
}
