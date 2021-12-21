package scenarios;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import blocks.LoginPage;
import blocks.MenuPage;

//import org.springframework.http.MediaType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import utils.DriverSetup;
import utils.ExcelReader;
import utils.HighlightAndScreenShot;
import utils.JavaPostRequest;
import utils.SFTPUtil;
import utils.SimpleFileReader;
import utils.SimpleFileWriter;
import utils.TrustAllCertificates;
import utils.ValidateFile;
//import utils.WSRestClient;

public class ReportRekonWithExcel {

	Scenario scenario = ScenarioSetup.scenario;
	SimpleFileReader sfr = new SimpleFileReader();
	SimpleFileWriter sfw = new SimpleFileWriter();
	ValidateFile vf = new ValidateFile();
	String userDir = System.getProperty("user.dir") + "\\";
	JavaPostRequest jpr = new JavaPostRequest();
	SFTPUtil ftp = new SFTPUtil();

	ChromeDriver driver;
	WebDriverWait wait;
	Actions action;
	JavascriptExecutor jse;
	File screenshotFile;

	DriverSetup driverSetup = new DriverSetup(driver);
	LoginPage login = new LoginPage(DriverSetup.driver);
	MenuPage menu = new MenuPage(login.driver);
	HighlightAndScreenShot hns = new HighlightAndScreenShot(DriverSetup.driver);
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	/*
	 * Transaksi Inbound { File ACQ: 00 + ACQUIRER_ID(Last 4 digits) File ISS:
	 * ISSUER_ID(Digit 2,3,4) + 001 }
	 * 
	 * Transaksi Outbound { File ACQ: ACQUIRER_ID(Digit 2,3,4) + 001 File ISS: 00 +
	 * ISSUER_ID(Last 4 digits) }
	 */
	
	String transactionType;
	String processingCode;
	String transactionAmount;
	String acquirerId;
	String issuerId;
	String responseCode;
	String rrn;
	String transId;
	String dateFormat1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	String dateFormat2 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	String dateFormat3 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
	String reportName_ACQ;
	String reportName_ISS;
	
	List<Map<String, String>> testData;
	

	@Given("^Transaksi Menggunakan Parameter Pada Excel Sheet (.*) Row (.*)$")
	public void hitTransaction(String sheetName, Integer rowNumber)
			throws IOException, InvalidFormatException {

	}

	@And("^Cek Transaksi Pada Transaction Manager$")
	public void checkTransaction()
			throws InterruptedException, IOException {

	}

	@When("^Jalankan Worklow Report$")
	public void runningWorkflow() throws InterruptedException, IOException {


	}
	
	@And("^Memindahkan File Report$")
	public void movingFileReport() throws InterruptedException, IOException {


	}

	@Then("^Validasi Report Rekon Acquirer$")
	public void validateReportACQ()
			throws IOException {

	}

	@And("^Validasi Report Rekon Issuer$")
	public void validateReportISS()
			throws IOException {
		
		driverSetup.closeDriver();
	}

}
