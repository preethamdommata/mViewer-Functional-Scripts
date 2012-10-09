package org.imaginea.mviewer.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.imaginea.mviewer.page.HomePageClass;
import org.imaginea.mviewer.page.LoginPageClass;
import org.imaginea.mviewer.page.MongoGraphsPage;
import org.imaginea.mviewer.page.ServerStatisticsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
	String docPath = "C:\\Users\\preethamd\\Desktop\\sampleData.txt";
	protected DocReader reader;

	// WebDriver driver;
	String mainWindow;

	public String getLocator(String elementLocator) {
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(
					"C:\\Users\\preethamd\\workspace\\mViewerAutomationTestScripts\\resources\\testdata\\mViewerLocatordata.properties"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		elementLocator = prop.getProperty(elementLocator);
		return elementLocator;
	}

	public void handleNewWindow(WebDriver driver, String clickButtonLocator,
			String finalWindowName) {
		// String finalWindow = null;
		WebDriver newWindow = null;
		mainWindow = driver.getWindowHandle();
		driver.findElement(By.cssSelector(clickButtonLocator)).click();
		Set<String> windws = driver.getWindowHandles();
		Iterator<String> ite = windws.iterator();
		while (ite.hasNext()) {
			String newWindowHandle = ite.next();
			newWindow = driver.switchTo().window(newWindowHandle);
			if (newWindow.getTitle().equals(finalWindowName)) {
				break;
			}
			// if (!(newWindow == mainWindow)) {
			// finalWindow = newWindow;
			// }
		}
		// driver.switchTo().window(finalWindow);

	}

	public HomePageClass driverControlBacktoMain(WebDriver driver) {
		driver.close();
		driver.switchTo().window(mainWindow);
		System.out.println("Home Page is displayed correctly");
		return new HomePageClass(driver);
	}

	public LoginPageClass disconnect(WebDriver driver) {
		driver.findElement(By.cssSelector("a#disconnect.disconnect.navigable"))
				.click();
		return new LoginPageClass(driver);
	}

	public ServerStatisticsPage serverStatistics(WebDriver driver) {
		driver.findElement(By.cssSelector("#serverStats.navigable")).click();
		return new ServerStatisticsPage(driver);
	}

	public MongoGraphsPage mongoGraphs(WebDriver driver) {
		handleNewWindow(driver, "#graphs.navigable", "Graphs in a TabView");
		return new MongoGraphsPage(driver);
	}

	public WebDriver driverInitialization(WebDriver driver, String driverName)
			throws MalformedURLException {

		DesiredCapabilities capability = null;
		String remoteURL = getLocator("remoteURL");
		String appURL = getLocator("appURL");

		if (driverName.equalsIgnoreCase("firefox")) {
			capability = DesiredCapabilities.firefox();
		} else if (driverName.equalsIgnoreCase("IE")) {
			capability = DesiredCapabilities.internetExplorer();
		} else if (driverName.equalsIgnoreCase("Chrome")) {
			// System.setProperty("webdriver.chrome.driver",
			// "C:\\Users\\preethamd\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
			// driver = new ChromeDriver();
			capability = DesiredCapabilities.chrome();
		}

		driver = new RemoteWebDriver(new URL(remoteURL), capability);
		driver.get(appURL);
		driver.manage().window().maximize();
		return driver;
	}

	public void Reader() {
		reader.getnumberOfRows();
		reader.getnumberOfColumns();
	}

	public DocReader docFinder() {
		DocReader reader1=null;
		String excel = ".xls";
		String csv = ".txt";
		String docext = docPath.substring(docPath.length()-4);
		if (excel.equalsIgnoreCase(docext)) {
			reader1 = new ReadExcel(docPath);
		} else if (csv.equalsIgnoreCase(docext)) {
			try {
				reader1 = new CSVutil(docPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return reader1;
	}
}
