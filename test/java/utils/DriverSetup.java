package utils;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class DriverSetup {
	
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static ChromeOptions options;
	public static Actions actions;
	public static JavascriptExecutor jse;
	public static File screenshotFile;
	private static String userDir =System.getProperty("user.dir") + "\\"; 
	
	public static final String WEB_DRIVER = "webdriver.chrome.driver";
//	public static final String WEB_DRIVER_LOC = "D:\\Telkom Sigma\\SeleniumTutorial\\browserdrivers\\chromedriver.exe";
	public static final String WEB_DRIVER_LOC =  userDir + "resource\\browserdrivers\\chromedriver.exe";
//	public static final String WEB_ADDRESS = "https://10.20.16.30:8444/renaissance-portal/#/";
	public static final String WEB_ADDRESS = "https://10.132.130.254:8444/renaissance-portal/#/";
	
	public DriverSetup(ChromeDriver driver) {
		System.setProperty(WEB_DRIVER, WEB_DRIVER_LOC);
		options = new ChromeOptions();
		options.addArguments("--ignore-ssl-errors=yes");
		options.addArguments("--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 10);
		actions = new Actions(driver);
		jse = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		this.driver = driver;
	}
	
	public static WebDriver getDriver(){
		
		if(driver==null){
			System.setProperty(WEB_DRIVER, WEB_DRIVER_LOC);
			driver = new ChromeDriver();
		}
		
		return driver;
		
	}

}
