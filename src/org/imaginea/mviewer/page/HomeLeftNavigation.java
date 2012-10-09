package org.imaginea.mviewer.page;

import java.util.ArrayList;
import java.util.List;

import org.imaginea.mviewer.common.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomeLeftNavigation extends BaseClass{

	WebDriver driver;
	
	List<WebElement> dbElements;
	ArrayList<String> presentDBNames = new ArrayList<String>();
	ArrayList<String> options = new ArrayList<String>();
	
	String dbNewButtonLocator = getLocator("dbNewButtonLocator");
	String dbSubmitButtonLocator = "button#yui-gen0-button";
	String createDBDialogLocator = "#addDBDialog_h.hd";
	
	public HomeLeftNavigation(WebDriver driver){
		this.driver = driver;
	}
	
	
	public void dbOptionsCheck() {
		
		ArrayList<String> optionNameList = new ArrayList<String>();
		optionNameList.add("Add Collection");
		optionNameList.add("Add GridFS Bucket");
		optionNameList.add("Drop Database");
		optionNameList.add("Statistics");

		for (WebElement element : dbElements) {
			for (int j = 0; j < presentDBNames.size(); j++) {
				String alldbName = presentDBNames.get(j);
				String optionClick = "span.yui3-menu-label>a.yui3-menu-toggle";
				String optionCss = "#" + alldbName
						+ "_subMenu ul.first-of-type > li > a";

				List<WebElement> dbName1 = element.findElements(By
						.cssSelector(optionCss));
				WebElement dbOptionClick = element.findElement(By
						.cssSelector(optionClick));
				dbOptionClick.click();

				for (int i = 0; i < dbName1.size(); i++) {

					JavascriptExecutor js = (JavascriptExecutor) driver;

					String optionName = (String) js
							.executeScript("return $($('" + optionCss + "')["
									+ i + "]).text();");
					// Assert.assertTrue(dbName1.get(i).isDisplayed(),"the Element is not displayed."
					// );
					System.out.println(dbName1.get(i)
							+ " is displayed correctly");
					options.add(optionName);
				}
			}
		}

	}

	
	public void CreateDB(String dbName) {

		String dbCss = "#dbNames ul.lists > li";

		dbElements = driver.findElements(By.cssSelector(dbCss));

		for (WebElement element : dbElements) {
			String dbName1 = element.getAttribute("label");
			presentDBNames.add(dbName1);
		}

		if (presentDBNames.contains(dbName)) {
			System.out.println("this DB is already present");
		} else {
			presentDBNames.add(dbName);
			driver.findElement(By.cssSelector(dbNewButtonLocator)).click();
			Assert.assertTrue(
					driver.findElement(By.cssSelector(createDBDialogLocator))
							.isDisplayed(), "The Pop window is not displayed.");
			driver.findElement(By.name("name")).clear();
			driver.findElement(By.name("name")).sendKeys(dbName);
			driver.findElement(By.cssSelector(dbSubmitButtonLocator)).click();
			System.out.println("DB is created successfully");
		}
	}

}
