package org.imaginea.mviewer.page;

import java.util.Iterator;
import java.util.Set;
import org.imaginea.mviewer.common.BaseClass;
import org.imaginea.mviewer.common.ReadExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePageClass extends BaseClass {

	//String excelpath = "C:\\Users\\preethamd\\Desktop\\preethamjava\\testing.xls";
	
	WebDriver driver;

	String hostLableLocator = getLocator("hostLableLocator");
	String userLableLocator = getLocator("userLableLocator");

	public HomePageClass(WebDriver driver) {
		this.driver = driver;
		reader = docFinder();
	}

	public void checkAllElementsDisplay(String elementLocator) {
		Assert.assertTrue(driver.findElement(By.cssSelector(elementLocator))
				.isDisplayed(), elementLocator + " is not displayed");
		System.out
				.println("Page Elements and its default values had been verified");
	}

	public void verifyDefaultElementValues() {
		String hostLable = "127.0.0.1";
		String portLable = "27017";
		String userLable = "Guest";

		Assert.assertEquals(driver
				.findElement(By.cssSelector(hostLableLocator)).getText(),
				hostLable + ":" + portLable);
		Assert.assertEquals(driver
				.findElement(By.cssSelector(userLableLocator)).getText(),
				userLable);

	}

	public ServerStatisticsPage serverStatistics() {
		driver.findElement(By.cssSelector("#serverStats.navigable")).click();
		return new ServerStatisticsPage(driver);
	}

	public void help() {

		handleNewWindow("a#help.navigable");

		Assert.assertEquals(driver.getTitle(), "mViewer by Imaginea @ GitHub");
	}

	public void handleNewWindow(String clickButtonLocator) {
		String finalWindow = null;
		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.cssSelector(clickButtonLocator)).click();
		Set<String> windws = driver.getWindowHandles();
		Iterator<String> ite = windws.iterator();
		while (ite.hasNext()) {
			String newWindow = ite.next();
			if (!(newWindow == mainWindow)) {
				finalWindow = newWindow;
			}
		}
		driver.switchTo().window(finalWindow);

	}

	public ShowConsole showConsole() {
		driver.findElement(By.cssSelector("button#console.bttn.navigable"))
				.click();
		driver.switchTo().frame(
				driver.findElement(By.cssSelector("#_yuiResizeMonitor")));
		return new ShowConsole(driver);
	}

	public MongoGraphsPage mongoGraphs() {
		handleNewWindow("#graphs.navigable");
		return new MongoGraphsPage(driver);
	}

	public TroubleShootWindow troubleshoot() {
		handleNewWindow("a#troubleshoot.navigable");
		return new TroubleShootWindow(driver);
	}
}

