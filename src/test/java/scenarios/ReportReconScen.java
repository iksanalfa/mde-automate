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
import runners.MainRunner;
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
	String transactionAmount;
	String acquirerId;
	String issuerId;
	String responseCode;
	String dateFormat1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	String dateFormat2 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	String dateFormat3 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
	String reportName_ACQ;
	String reportName_ISS;
	

	@Given("^Transaksi (.*) dengan Processing Code (.*) senilai (.*) Acquirer Paynet (.*) Issuer (.*) Rrn (.*) TransId (.*) Response Code (.*) Tanggal Transaksi (.*) Loop (.*)$")
	public void hitTransaction(String transactionType,
			String processingCode, String transactionAmount, String acquirerId, String issuerId,
			String rrn, String transId, String responseCode, String transactionDate,
			String loop)
			throws IOException {
				
		ArrayList<String> listTrxType = new ArrayList<String>();
		ArrayList<String> listTrxAmount = new ArrayList<String>();
		ArrayList<String> listResponseCode = new ArrayList<String>();
		
		if(transactionType.contains(";")) {
			listTrxType = jpr.getDataAsList(transactionType);
		}
		
		if(transactionAmount.contains(";")) {
			listTrxAmount = jpr.getDataAsList(transactionAmount);
		}
		
		if(responseCode.contains(";")) {
			listResponseCode = jpr.getDataAsList(responseCode);
		}
		
		if (loop.equalsIgnoreCase("y")) {
			for(int i =0; i < listTrxType.size(); i++) {
				jpr.postRequest(scenario, rrn, transId, listTrxAmount.get(i), transactionDate, acquirerId, issuerId, processingCode, listResponseCode.get(i)
						,listTrxType.get(i));
			}			
		} else {
			//Post Request
			jpr.postRequest(scenario, rrn, transId, transactionAmount, transactionDate, acquirerId, issuerId, processingCode, responseCode,
					transactionType);
		}

	}

	@And("^Check Transaction Manager Date (.*) Rrn (.*)$")
	public void checkTransactionManager(String transactionDate, String rrn)
			throws InterruptedException, IOException {
//		String dateDay = String.valueOf(LocalDate.now().getDayOfMonth());
		String dateDay = transactionDate.substring(6, 8);
		
//		login.userLogin("Sigmauser-12", "S!gm4user12@12#$", scenario); //Jalin
		login.userLogin("Sigmauser-4", "S1gm41234*", scenario); //EN
		
		menu.checkTransaction(scenario, transactionDate, rrn);

		//Logout
		menu.logout(scenario);
		//Close Driver
		driverSetup.closeDriver();
	}

	@When("^Running workflow report$")
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
