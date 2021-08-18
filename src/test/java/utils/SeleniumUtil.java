package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

import io.cucumber.java.Scenario;

public class SeleniumUtil {
	
	private static SeleniumUtil seleniumUtil;
	private static WebDriver driver; 
	public static Properties configProperties;
	static{
		 String configFile=System.getProperty("user.dir")+"//src//test//resources//Config.properties";
	    configProperties=new Properties();
			
			try{
				FileInputStream fisConfig=new FileInputStream(configFile);
				
				configProperties.load(fisConfig);
				
	}catch(Exception e){
		e.printStackTrace();
	}
		
	}
	private SeleniumUtil(){
		
	}
  
	public static SeleniumUtil getInstance(){
		
		if(seleniumUtil==null){
			seleniumUtil= new SeleniumUtil();
		}
		
		return seleniumUtil; 
		
	}
	
	public static WebDriver getDriver(){
		
		if(driver==null){
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		return driver;
		
	}
	
	public static void navigate(String url){
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	
	public static void setDriver(WebDriver driver){
		SeleniumUtil.driver=driver;
	}
    
	public static void clickLink(By locator){
		driver.findElement(locator).click();
		
	}
	
	public static void isElementDiplayed(By Locator){
		driver.findElement(Locator).isDisplayed();
		
	}
	
	public static void takeScreenShot() throws IOException{
		String screenShotFolder=System.getProperty("user.dir")+"//src//test//resources//ScreenShot//";
		Date date= new Date();
		Long time=date.getTime();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(scrFile, new File(screenShotFolder  + time+".png"));
		
	}
	
	public static String takeScreenShotReturnPath() throws IOException{
		String screenShotFolder=System.getProperty("user.dir")+"//src//test//resources//ScreenShot//";
		Date date= new Date();
		Long time=date.getTime();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imagePath=screenShotFolder  + time+".png";
		Files.copy(scrFile, new File(imagePath));
		return imagePath;
	}
	
	public static void EmbedScreenShot(Scenario scenario){
		final byte[] screenshot =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", "Image"); // stick it in the report
	}

}
