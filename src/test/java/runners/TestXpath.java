package runners;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import blocks.LoginPage;
import blocks.MenuPage;
import io.cucumber.java.Scenario;
import scenarios.ScenarioSetup;
import utils.DriverSetup;
import utils.HighlightAndScreenShot;
import utils.JavaPostRequest;
import utils.SFTPUtil;
import utils.SimpleFileReader;
import utils.SimpleFileWriter;
import utils.ValidateFile;

public class TestXpath {
	
	static Scenario scenario = ScenarioSetup.scenario;
	SimpleFileReader sfr = new SimpleFileReader();
	SimpleFileWriter sfw = new SimpleFileWriter();
	ValidateFile vf = new ValidateFile();
	String userDir = System.getProperty("user.dir") + "\\";
	JavaPostRequest jpr = new JavaPostRequest();
	SFTPUtil ftp = new SFTPUtil();

	static ChromeDriver driver;
	WebDriverWait wait;
	Actions action;
	JavascriptExecutor jse;
	File screenshotFile;

	static DriverSetup driverSetup = new DriverSetup(driver);
	static LoginPage login = new LoginPage(DriverSetup.driver);
	static MenuPage menu = new MenuPage(login.driver);
	HighlightAndScreenShot hns = new HighlightAndScreenShot(DriverSetup.driver);
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		
		Thread.sleep(5000);
//		login.testLogin("Sigmauser-12", "S!gm4user12@12#$", scenario); //REN Jalin
		login.testLogin("Sigmauser-4", "S1gm41234*", scenario); //REN EuroNET
		Thread.sleep(5000);
//		menu.checkTransaction(scenario, "20211011", "123980180391");
		
		menu.testChangeDate(scenario, "20211101");
		Thread.sleep(5000);
		
		//Logout
		menu.logout(scenario);
		//Close Driver
		driverSetup.closeDriver();
		
	}

}
