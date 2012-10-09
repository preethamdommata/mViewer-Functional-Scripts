package org.imaginea.mviewer.tests;

import org.imaginea.mviewer.page.HomeLeftNavigation;
import org.imaginea.mviewer.page.LoginPageClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class LeftNavigationTest {
	WebDriver driver;
	HomeLeftNavigation homeleftNavigation;
	LoginPageClass loginPage;

	@Test
	public void createDB() {
		String dbName = "Preetham5";

		// Creating a DB
		homeleftNavigation.CreateDB(dbName);

	}

	@Test(dependsOnMethods = "createDB")
	public void checkdbOptions() {
		// Checking the DB options for all the dbs created till now.
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homeleftNavigation.dbOptionsCheck();
	}

	@BeforeMethod
	public void beforeMethod() {
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		loginPage = new LoginPageClass(driver);
		homeleftNavigation = loginPage.loginleft();

	}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
