package org.imaginea.mviewer.tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


import org.imaginea.mviewer.common.BaseClass;
import org.imaginea.mviewer.common.mViewerTestDataClass;
import org.imaginea.mviewer.page.HomePageClass;
import org.imaginea.mviewer.page.LoginPageClass;
import org.imaginea.mviewer.page.MongoGraphsPage;
import org.imaginea.mviewer.page.ServerStatisticsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNGClass extends BaseClass {
	WebDriver driver;
	LoginPageClass loginPage;
	HomePageClass homePage;
	ServerStatisticsPage serverPage;
	//BaseClass baseClass = new BaseClass();
	mViewerTestDataClass testData = new mViewerTestDataClass();

	@DataProvider(name = "elementLocatorValues")
	public Object[][] elementdata() {
		return testData.loginPageelementLocatorValues();
	}

	@DataProvider(name = "elementLocatorValuesHome")
	public Object[][] elementdataHome() {
		return testData.loginPageelementLocatorValues1();
	}

	@Test(dataProvider = "elementLocatorValues")
	public void verifyLoginpage(String elementLocator, String elementValue) {
		// checking the presence of the WebElemets on the Login Page
		System.out.println("Started verifying webelements on the Login Page");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		loginPage.checkAllElementsDisplay(elementLocator, elementValue);

	}

	@Test(dependsOnMethods = { "verifyLoginpage" })
	public void loginmViewer() {
		// checking the Login functionality with Default values
		System.out.println("Started checking the login functionality");
		homePage = loginPage.login();

		Assert.assertTrue((driver.findElement(By.cssSelector(getLocator("homeLocator")))).isDisplayed(),
				"Login is not successful");
		System.out.println("Login is successful");
	}

	@Test(dataProvider = "elementLocatorValuesHome", dependsOnMethods = { "loginmViewer" })
	public void verifyHomePage(String elementLocator) {

		// checking the presence of webelements on the Home page

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Started verifying the webelements on the Homepage");
		homePage.checkAllElementsDisplay(elementLocator);
		homePage.Reader();

	}

	@Test(dependsOnMethods = "verifyHomePage")
	public void navigatetoServerStatistics(){
		//serverPage = homePage.serverStatistics();
		serverPage = serverStatistics(driver);
		serverPage.verifyTable();
	}
	
	//@Test(dependsOnMethods = "verifyHomePage")
	public void navigatetoGraphsPage(){
		
		//MongoGraphsPage graphsPage = homePage.mongoGraphs();
		MongoGraphsPage graphsPage = mongoGraphs(driver);
		graphsPage.verifyGraphsPage();
		driverControlBacktoMain(driver);
		//driver.findElement(By.cssSelector(""))
		Assert.assertEquals(driver.getTitle(), "mViewer");
	}
	
	//@Test(dependsOnMethods = "navigatetoServerStatistics")
	public void disconnect(){
		loginPage = disconnect(driver);
		System.out.println("Home page is logged out succesfully");
	}
	@BeforeMethod
	public void beforeMethod() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {

		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	@Parameters({"Browser"})
	public void beforeClass(String Browser) throws MalformedURLException {
		this.driver = driverInitialization(driver, Browser );
		loginPage = new LoginPageClass(driver);

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
