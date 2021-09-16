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

import javax.net.ssl.HttpsURLConnection;

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
import utils.HighlightAndScreenShot;
import utils.JavaPostRequest;
import utils.SFTPUtil;
import utils.SimpleFileReader;
import utils.SimpleFileWriter;
import utils.TrustAllCertificates;
import utils.ValidateFile;
//import utils.WSRestClient;

public class ReportReconScen {

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
	BigDecimal transactionAmount;
	String acquirerId;
	String issuerId;
	String responseCode;
	String dateFormat1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	String dateFormat2 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	String dateFormat3 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
	String reportName_ACQ;
	String reportName_ISS;
	

	@Given("^Transaksi (.*) dengan Processing Code (.*) senilai (.*) Acquirer Paynet (.*) Issuer (.*)$")
	public void transaksi_outbound_dengan_processing_code_senilai_acquirer_paynet_issuer(String transactionType,
			String processingCode, BigDecimal transactionAmount, String acquirerId, String issuerId)
			throws IOException {
		this.transactionType = transactionType;
		this.processingCode = processingCode;
		this.transactionAmount = transactionAmount;
		this.acquirerId = acquirerId;
		this.issuerId = issuerId;
		
		//Post Request
		jpr.postRequest(scenario);

	}

	@And("^Response Code (.*) dan Tanggal Transaksi (.*)$")
	public void response_code_dan_tanggal_transaksi(String responseCode, String YYyymmdd)
			throws InterruptedException, IOException {
		String dateDay = String.valueOf(LocalDate.now().getDayOfMonth());
		login.userLogin("Sigmauser-12", "S!gm4user12@12#$", scenario);
		menu.clickMenu("Transaction Manager", scenario);
		Thread.sleep(3000);
		menu.clickElementByXpath("//input[@id='transactionDateRange']", scenario);
		Thread.sleep(1000);
		menu.clickSelect("//div[@id='startPicker']//div//select[@class='ren-form-control month']", "September",
				scenario);
		Thread.sleep(1000);
		menu.inputElement("//div[@id='startPicker']//div//input[@name='year']", "", scenario);
		Thread.sleep(1000);
		menu.clickDayElement(
				"//div[@id='startPicker']//div//div//div[contains(@class,'calendar-day')][normalize-space()='" + dateDay +"']",
				dateDay, scenario);		
		Thread.sleep(1000);
		menu.clickSelect("//div[@id='endPicker']//div//select[@class='ren-form-control month']", "September", scenario);
		Thread.sleep(1000);
		menu.inputElement("//div[@id='endPicker']//div//input[@name='year']", "", scenario);
		Thread.sleep(1000);
		menu.clickDayElement("//div[@id='endPicker']//div//div//div[contains(@class,'calendar-day')][normalize-space()='" + dateDay +"']", dateDay, scenario);
		Thread.sleep(1000);
		menu.clickElementByXpath("//div[@class='ren-btn-sm ren-btn-primary']", scenario);
		Thread.sleep(1000);
		menu.inputElement("//input[@placeholder='Acquirer RRN']", "123980180382", scenario);
		Thread.sleep(1000);
		menu.clickButton("Search Transactions", scenario);
		Thread.sleep(10000);
		menu.clickElementByXpath("//div[@class='ren-card ren-flex-column-full-space']", scenario);
	}

	@When("^Running workflow report Recon$")
	public void running_workflow_report_recon() throws InterruptedException, IOException {
		menu.clickSubMenu("Workflow Manager", "Workflows", scenario);
		menu.doubleClickData("JALIN_LINK_EOD", scenario);
		Thread.sleep(3000);
		menu.clickElementByXpath("//span[normalize-space()='JALIN_LINK_EOD Details']", scenario);

	}
	
	@And("^Moving File Report$")
	public void moving_file_report() throws InterruptedException, IOException {
		reportName_ACQ = vf.createReportReconName(scenario, dateFormat2, acquirerId, issuerId, transactionType, "ACQ");
		reportName_ISS = vf.createReportReconName(scenario, dateFormat2, acquirerId, issuerId, transactionType, "ISS");
		
		//Download/Moving File to Local
		ftp.downloadFile(scenario, "Recon", reportName_ACQ);
		ftp.downloadFile(scenario, "Recon", reportName_ISS);

	}

	@Then("^Validate Report QRX_RECON_360004_(.*)_(.*)_ACQ_1 compare with (.*) (.*) (.*) (.*) (.*) (.*)$")
	public void validate_report_qrx_recon__acq(String transactionType, String yymmdd, String processingCode,
			String transactionAmount, String yyyymmdd, String responseCode, String acquirerId, String issuerId)
			throws IOException {
		ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();

		String filePath = userDir + reportName_ACQ;
		list1 = sfr.getEveryColumn(filePath, scenario);
		vf.valReportRecon(list1, scenario, processingCode, transactionAmount, yyyymmdd, responseCode, acquirerId,
				issuerId);
	}

	@And("^Validate Report QRX_RECON_360004_(.*)_(.*)_ISS_1 compare with (.*) (.*) (.*) (.*) (.*) (.*)$")
	public void validate_report_qrx_recon__iss(String transactionType, String yymmdd, String processingCode,
			String transactionAmount, String yyyymmdd, String responseCode, String acquirerId, String issuerId)
			throws IOException {
		ArrayList<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();

		String filePath = userDir + reportName_ISS;
		list2 = sfr.getEveryColumn(filePath, scenario);
		vf.valReportRecon(list2, scenario, processingCode, transactionAmount, yyyymmdd, responseCode, acquirerId,
				issuerId);
	}

}
