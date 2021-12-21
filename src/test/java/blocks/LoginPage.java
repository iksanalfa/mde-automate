package blocks;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;
import utils.DriverSetup;
import utils.HighlightAndScreenShot;

public class LoginPage {
	
	public ChromeDriver driver;
	public WebDriverWait wait;
	public 	ChromeOptions options;
	public Actions actions;
	public JavascriptExecutor jse;
	public File screenshotFile;
	public HighlightAndScreenShot hns;
	
	public LoginPage(ChromeDriver driver) {
		wait = new WebDriverWait(driver, 10);
		actions = new Actions(driver);
		jse = (JavascriptExecutor) driver;
		this.driver = driver;
		hns = new HighlightAndScreenShot(driver);
	}
	
	public void userLogin(String username, String password, Scenario scenario) throws InterruptedException, IOException {
		
		driver.get(DriverSetup.WEB_ADDRESS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userName\"]")));
		
		WebElement txt_username = driver.findElementByXPath("//*[@id=\"userName\"]");
		txt_username.sendKeys(username);
		hns.doProcess(txt_username, "Input Username", scenario);
		
		WebElement txt_password = driver.findElementByXPath("//*[@id=\"password\"]");
		txt_password.sendKeys(password);
		hns.doProcess(txt_password, "Input Password", scenario);
		
		WebElement btn_login = driver.findElementByXPath("//button[normalize-space()='Login']");
		hns.doProcess(btn_login, "Click Login Button", scenario);
		btn_login.click();
		
		Thread.sleep(1000);
	}
	
	public void testLogin(String username, String password, Scenario scenario) throws InterruptedException, IOException {
		
		driver.get(DriverSetup.WEB_ADDRESS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userName\"]")));
		
		WebElement txt_username = driver.findElementByXPath("//*[@id=\"userName\"]");
		txt_username.sendKeys(username);
		
		WebElement txt_password = driver.findElementByXPath("//*[@id=\"password\"]");
		txt_password.sendKeys(password);
		
		WebElement btn_login = driver.findElementByXPath("//button[normalize-space()='Login']");
		btn_login.click();
		
		Thread.sleep(1000);
	}

}
