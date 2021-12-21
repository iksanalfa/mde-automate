package blocks;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	
	public void logout(Scenario scenario) throws IOException, InterruptedException {
		String xUser = "//*[@id=\"user-select\"]/img";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xUser)));
		
		WebElement we_user = driver.findElementByXPath(xUser);
		//hns.doProcess(we_user, "Click User ", scenario);
		we_user.click();
		
		
		String xLogout = "//li[normalize-space()='Logout']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xLogout)));
		WebElement we_logout = driver.findElementByXPath(xLogout);
		//hns.doProcess(we_logout, "Click Logout ", scenario);
		we_logout.click();
		
		Thread.sleep(2000);
		
		
	}
	
	public void clickMenu(String labelMenu, Scenario scenario) throws InterruptedException, IOException {
		xMenu = "//span[normalize-space()='" + labelMenu  +"']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xMenu)));
		
		we_menu = driver.findElementByXPath(xMenu);
		//hns.doProcess(we_menu, "Click Menu " +labelMenu, scenario);
		we_menu.click();
	}
	
	public void clickSubMenu(String labelMenu, String labelSubMenu, Scenario scenario) throws InterruptedException, IOException {
		xMenu = "//span[normalize-space()='" + labelMenu  +"']";
		xSubMenu = "//*[normalize-space()='" + labelMenu +"']/parent::*//*[normalize-space()='" + labelSubMenu + "']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xMenu)));
		
		we_menu = driver.findElementByXPath(xMenu);
		actions.moveToElement(we_menu).perform();
		//hns.doProcess(we_menu, "Move to Menu " +labelMenu, scenario);
		
		we_subMenu = driver.findElementByXPath(xSubMenu);
		//hns.doProcess(we_subMenu, "Click Sub Menu " +labelSubMenu, scenario);
		we_subMenu.click();
	}
	
	public void doubleClickData(String labelData, Scenario scenario) throws InterruptedException, IOException {
		xDataTable = "//*[normalize-space()='" + labelData + "']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xDataTable)));
		
		we_tableData = driver.findElementByXPath(xDataTable);
		//hns.doProcess(we_tableData, "Double Click on Data " +labelData, scenario);
		actions.doubleClick(we_tableData).perform();
	}
	
	public void clickButton(String labelButton, Scenario scenario) throws InterruptedException, IOException {
		xButton = "//button[normalize-space()='" + labelButton + "']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xButton)));
		
		we_button = driver.findElementByXPath(xButton);
		//hns.doProcess(we_button, "Click Button " +labelButton, scenario);
		we_button.click();
	}
	
	public List<String> getFirstRow(String headerName, Scenario scenario) throws InterruptedException, IOException {
		List<String> listFirstRow =  new ArrayList<String>();
		Thread.sleep(2000);
//		xFirstRow = "//span[normalize-space()='" + headerName + "']/ancestor::*//tbody/tr[1]";
		xFirstRow = "//*[@class='table']/tbody/tr[1]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xFirstRow)));
		List<WebElement> listElement = driver.findElementsByXPath(xFirstRow);
		//hns.doProcessList(listElement, "First Row Data", scenario);
		for(int i = 0; i < listElement.size(); i++) {
			listFirstRow.add(listElement.get(i).getText());
		}
		return listFirstRow;
	}
	
	public void clickElement(String elementId, Scenario scenario) throws IOException, InterruptedException {
		String xElement = "//input[@id='" + elementId + "']";
		//input[@id='transactionDateRange']
		WebElement element = driver.findElementByXPath(xElement);
		//hns.doProcess(element, "Click Element " +elementId, scenario);
		element.click();
	}
	
	public void clickSelect(String xSelect, String visibleValue, Scenario scenario) throws IOException, InterruptedException {
//		String xSelect = "//div[@id='startPicker']//div//select[@class='ren-form-control month']";
		WebElement element = driver.findElementByXPath(xSelect);
		//hns.doProcess(element, "Click Element " +visibleValue, scenario);
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
	}
	
	public void clickSelectByValue(String xSelect, String value, Scenario scenario) throws IOException, InterruptedException {
//		String xSelect = "//div[@id='startPicker']//div//select[@class='ren-form-control month']";
		WebElement element = driver.findElementByXPath(xSelect);
		//hns.doProcess(element, "Click Element " +value, scenario);
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	public void inputElement(String xElement, String value, Scenario scenario) throws IOException, InterruptedException {
		WebElement element = driver.findElementByXPath(xElement);
		element.clear();
		element.sendKeys(value);
		//hns.doProcess(element, "Input " +value, scenario);
	}
	
	public void clickDayElement(String xElement, String value, Scenario scenario) throws IOException, InterruptedException {
		WebElement element = driver.findElementByXPath(xElement);
		element.click();
		//hns.doProcess(element, "Click Day " +value, scenario);
	}
	
	public void clickElementByXpath(String xElement, Scenario scenario) throws IOException, InterruptedException {
		WebElement element = driver.findElementByXPath(xElement);
		//hns.doProcess(element, "Click Element", scenario);
		element.click();
	}
	
	public String getValueElement(String xElement, Scenario scenario) throws IOException, InterruptedException {
		WebElement element = driver.findElementByXPath(xElement);
		String value = element.getAttribute("value");
		return value;
	}
	
	
	public void checkTransaction(Scenario scenario, String transactionDate, String rrn) throws InterruptedException, IOException {
		
		String dateDay = Integer.valueOf(transactionDate.substring(6, 8)).toString();
		String strMonth = String.valueOf(Integer.parseInt(transactionDate.substring(4, 6)) - 1);
		
		clickMenu("Transaction Manager", scenario);
		Thread.sleep(3000);
		clickElementByXpath("//input[@id='transactionDateRange']", scenario);
		Thread.sleep(1000);
//		clickSelect("//div[@id='startPicker']//div//select[@class='ren-form-control month']", "September",
//				scenario);
		clickSelectByValue("//div[@id='startPicker']//div//select[@class='ren-form-control month']", strMonth,
				scenario);
		Thread.sleep(1000);
		inputElement("//div[@id='startPicker']//div//input[@name='year']", "", scenario);
		Thread.sleep(1000);
		clickDayElement(
				"//div[@id='startPicker']//div//div//div[contains(@class,'calendar-day')][normalize-space()='" + dateDay +"']",
				dateDay, scenario);		
		Thread.sleep(1000);
//		clickSelect("//div[@id='endPicker']//div//select[@class='ren-form-control month']", "September", scenario);
		clickSelectByValue("//div[@id='endPicker']//div//select[@class='ren-form-control month']", strMonth, scenario);
		Thread.sleep(1000);
		inputElement("//div[@id='endPicker']//div//input[@name='year']", "", scenario);
		Thread.sleep(1000);
		clickDayElement("//div[@id='endPicker']//div//div//div[contains(@class,'calendar-day')][normalize-space()='" + dateDay +"']", dateDay, scenario);
		Thread.sleep(1000);
		clickElementByXpath("//div[@class='ren-btn-sm ren-btn-primary']", scenario);
		Thread.sleep(1000);
		inputElement("//input[@placeholder='Acquirer RRN']", rrn, scenario);
		Thread.sleep(1000);
		clickButton("Search Transactions", scenario);
		Thread.sleep(10000);
		clickElementByXpath("//div[@class='ren-card ren-flex-column-full-space']", scenario);
	}
	
	public void testChangeDate(Scenario scenario, String processingDate) throws InterruptedException, IOException, ParseException {
		//START PREVIOUS DATE
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date myDate = dateFormat.parse(processingDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        Date prevDate = calendar.getTime();
        String previousDate = dateFormat.format(prevDate);
        //END PREVIOUSDATE
        
		String processingDateDay = Integer.valueOf(processingDate.substring(6, 8)).toString();
		String processingStrMonth = String.valueOf(Integer.parseInt(processingDate.substring(4, 6)) - 1);
		
		String previousDateDay = Integer.valueOf(previousDate.substring(6, 8)).toString();
		String previousStrMonth = String.valueOf(Integer.parseInt(previousDate.substring(4, 6)) - 1);
		
//		clickMenu("Administration", scenario);
		Thread.sleep(1000);
		clickSubMenu("Administration", "Process Control", scenario);
		Thread.sleep(1000);
		doubleClickData("LINK", scenario);
		Thread.sleep(1000);
		
		clickElementByXpath("//label[@for='processingDate']/parent::*//*[contains(@class,'date-input-container')]", scenario);
		Thread.sleep(1000);
		clickSelectByValue("//select[@class='ren-form-control month']", processingStrMonth, scenario);
		Thread.sleep(1000);
		inputElement("//input[@name='year']", "2021", scenario);
		Thread.sleep(1000);
		clickDayElement("//div[contains(@class,'calendar-day')][normalize-space()='" + processingDateDay +"']", processingDateDay, scenario);
		Thread.sleep(1000);
		
		clickElementByXpath("//label[@for='previousProcessingDate']/parent::*//*[contains(@class,'date-input-container')]", scenario);
		Thread.sleep(1000);
		clickSelectByValue("//select[@class='ren-form-control month']", previousStrMonth, scenario);
		Thread.sleep(1000);
		inputElement("//input[@name='year']", "2021", scenario);
		Thread.sleep(1000);
		clickDayElement("//div[contains(@class,'calendar-day')][normalize-space()='" + previousDateDay +"']", previousDateDay, scenario);
		Thread.sleep(1000);
		
		clickElementByXpath("//button[@class='ren-btn ren-btn-success ren-float-right']", scenario);
	}
}
