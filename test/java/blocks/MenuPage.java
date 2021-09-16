package blocks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;
import utils.HighlightAndScreenShot;

public class MenuPage {

	ChromeDriver driver;
	WebDriverWait wait;
	ChromeOptions options;
	Actions actions;
	JavascriptExecutor jse;
	File screenshotFile;
	HighlightAndScreenShot hns;
	
	WebElement we_menu;
	WebElement we_subMenu;
	WebElement we_tableData;
	WebElement we_button;
	
	String labelMenu;
	String labelSubMenu;
	String labelData;
	String labelButton;
	String labelFirstRow;
	
	String xMenu = "//span[normalize-space()='" + labelMenu  +"']";
	String xSubMenu = "//*[normalize-space()='" + labelMenu +"']/parent::*//*[normalize-space()='" + labelSubMenu + "']";
	String xDataTable = "//*[normalize-space()='" + labelData + "']";
	String xButton = "//button[normalize-space()='" + labelButton + "']";
	String xFirstRow = "//span[normalize-space()='" + labelFirstRow + "']/ancestor::*//tbody/tr[1]";
	
	public MenuPage(ChromeDriver driver) {
		wait = new WebDriverWait(driver, 10);
		actions = new Actions(driver);
		jse = (JavascriptExecutor) driver;
		this.driver = driver;
		hns = new HighlightAndScreenShot(driver);
	}
	
	public void clickMenu(String labelMenu, Scenario scenario) throws InterruptedException, IOException {
		xMenu = "//span[normalize-space()='" + labelMenu  +"']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xMenu)));
		
		we_menu = driver.findElementByXPath(xMenu);
		hns.doProcess(we_menu, "Click Menu " +labelMenu, scenario);
		we_menu.click();
	}
	
	public void clickSubMenu(String labelMenu, String labelSubMenu, Scenario scenario) throws InterruptedException, IOException {
		xMenu = "//span[normalize-space()='" + labelMenu  +"']";
		xSubMenu = "//*[normalize-space()='" + labelMenu +"']/parent::*//*[normalize-space()='" + labelSubMenu + "']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xMenu)));
		
		we_menu = driver.findElementByXPath(xMenu);
		actions.moveToElement(we_menu).perform();
		hns.doProcess(we_menu, "Move to Menu " +labelMenu, scenario);
		
		we_subMenu = driver.findElementByXPath(xSubMenu);
		hns.doProcess(we_subMenu, "Click Sub Menu " +labelSubMenu, scenario);
		we_subMenu.click();
	}
	
	public void doubleClickData(String labelData, Scenario scenario) throws InterruptedException, IOException {
		xDataTable = "//*[normalize-space()='" + labelData + "']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xDataTable)));
		
		we_tableData = driver.findElementByXPath(xDataTable);
		hns.doProcess(we_tableData, "Double Click on Data " +labelData, scenario);
		actions.doubleClick(we_tableData).perform();
	}
	
	public void clickButton(String labelButton, Scenario scenario) throws InterruptedException, IOException {
		xButton = "//button[normalize-space()='" + labelButton + "']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xButton)));
		
		we_button = driver.findElementByXPath(xButton);
		hns.doProcess(we_button, "Click Button " +labelButton, scenario);
		we_button.click();
	}
	
	public List<String> getFirstRow(String headerName, Scenario scenario) throws InterruptedException, IOException {
		List<String> listFirstRow =  new ArrayList<String>();
		Thread.sleep(2000);
//		xFirstRow = "//span[normalize-space()='" + headerName + "']/ancestor::*//tbody/tr[1]";
		xFirstRow = "//*[@class='table']/tbody/tr[1]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xFirstRow)));
		List<WebElement> listElement = driver.findElementsByXPath(xFirstRow);
		hns.doProcessList(listElement, "First Row Data", scenario);
		for(int i = 0; i < listElement.size(); i++) {
			listFirstRow.add(listElement.get(i).getText());
		}
		return listFirstRow;
	}
	
	public void clickElement(String elementId, Scenario scenario) throws IOException, InterruptedException {
		String xElement = "//input[@id='" + elementId + "']";
		//input[@id='transactionDateRange']
		WebElement element = driver.findElementByXPath(xElement);
		hns.doProcess(element, "Click Element " +elementId, scenario);
		element.click();
	}
	
	public void clickSelect(String xSelect, String visibleValue, Scenario scenario) throws IOException, InterruptedException {
//		String xSelect = "//div[@id='startPicker']//div//select[@class='ren-form-control month']";
		WebElement element = driver.findElementByXPath(xSelect);
		hns.doProcess(element, "Click Element " +visibleValue, scenario);
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
	}
	
	public void inputElement(String xElement, String value, Scenario scenario) throws IOException, InterruptedException {
		WebElement element = driver.findElementByXPath(xElement);
		element.sendKeys(value);
		hns.doProcess(element, "Input " +value, scenario);
	}
	
	public void clickDayElement(String xElement, String value, Scenario scenario) throws IOException, InterruptedException {
		WebElement element = driver.findElementByXPath(xElement);
		element.click();
		hns.doProcess(element, "Click Day " +value, scenario);
	}
	
	public void clickElementByXpath(String xElement, Scenario scenario) throws IOException, InterruptedException {
		WebElement element = driver.findElementByXPath(xElement);
		hns.doProcess(element, "Click Element", scenario);
		element.click();
	}
	
	public String getValueElement(String xElement, Scenario scenario) throws IOException, InterruptedException {
		WebElement element = driver.findElementByXPath(xElement);
		String value = element.getAttribute("value");
		return value;
	}
	
}
