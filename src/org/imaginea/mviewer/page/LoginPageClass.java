package org.imaginea.mviewer.page;


import org.imaginea.mviewer.common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageClass extends BaseClass {
	WebDriver driver;
	

	String connectButtonLocator = getLocator("connectButtonLocator");

	public LoginPageClass(WebDriver driver) {
		this.driver = driver;
		/*if (!(driver.getTitle().equals("Login"))) {
			System.out.println("This is not Login Page");
		}*/
	}

	public void checkAllElementsDisplay(String elementLocator, String elementValue) {
		Assert.assertTrue(driver.findElement(By.cssSelector(elementLocator))
				.isDisplayed(), elementLocator + " is not displayed");
		Assert.assertEquals(driver.findElement(By.cssSelector(elementLocator))
				.getAttribute("value"), elementValue);
		System.out
				.println("Page Elements and its default values had been verified");
	}

	public HomePageClass login() {
		driver.findElement(By.cssSelector(connectButtonLocator)).click();
		return new HomePageClass(driver);
	}
	public HomeLeftNavigation loginleft() {
		driver.findElement(By.cssSelector(connectButtonLocator)).click();
		return new HomeLeftNavigation(driver);
	}
}
