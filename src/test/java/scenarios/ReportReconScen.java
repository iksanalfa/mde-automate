package scenarios;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import utils.SimpleFileReader;
import utils.SimpleFileWriter;
import utils.ValidateFile;

public class ReportReconScen {

	Scenario scenario = ScenarioSetup.scenario;
	SimpleFileReader sfr = new SimpleFileReader();
	SimpleFileWriter sfw = new SimpleFileWriter();
	ValidateFile vf = new ValidateFile();
	String userDir = System.getProperty("user.dir") + "\\";
	
	/*
	 * Transaksi Inbound { 
	 * File ACQ: 00 + ACQUIRER_ID(Last 4 digits) 
	 * File ISS: ISSUER_ID(Digit 2,3,4) + 001
	 * }
	 * 
	 * Transaksi Outbound {
	 * File ACQ: ACQUIRER_ID(Digit 2,3,4) + 001
	 * File ISS: 00 + ISSUER_ID(Last 4 digits)
	 * }
	 */

	@Given("^Transaksi (.*) dengan Processing Code (.*) senilai (.*) Acquirer Paynet (.*) Issuer (.*)$")
	public void transaksi_outbound_dengan_processing_code_senilai_acquirer_paynet_issuer(String transactionType,
			String processingCode, BigDecimal transactionAmount, String acquirerId, String issuerId) {
		scenario.attach("Test Create Scene", "text/plain", "Test Create Scenario");
	}

	@And("^Response Code (.*) dan Tanggal Transaksi (.*)$")
	public void response_code_dan_tanggal_transaksi(String responseCode, String YYyymmdd) {

	}

	@When("^Running workflow report Recon$")
	public void running_workflow_report_recon() {

	}

	@Then("^Validate Report QRX_RECON_360004_(.*)_(.*)_ACQ_1 compare with (.*) (.*) (.*) (.*) (.*) (.*)$")
	public void validate_report_qrx_recon__acq(String transactionType, String yymmdd, String processingCode,
			String transactionAmount, String yyyymmdd, String responseCode, String acquirerId, String issuerId)
			throws IOException {
		ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
		String destination = "";
		
		if(transactionType.equalsIgnoreCase("Inbound")) {
			destination = "00" + acquirerId.substring(4, acquirerId.length());
		} else if(transactionType.equalsIgnoreCase("Outbound")) {
			destination = acquirerId.substring(1, 4) + "001";
		}
		
		String fileName = "QRX_RECON_360004_" + destination + "_" + yymmdd + "_ACQ_1";
		String filePath = userDir + fileName;
		list1 = sfr.getEveryColumn(filePath, scenario);
		vf.valReportRecon(list1, scenario, processingCode, transactionAmount, yyyymmdd, responseCode, acquirerId,
				issuerId);
	}

	@And("^Validate Report QRX_RECON_360004_(.*)_(.*)_ISS_1 compare with (.*) (.*) (.*) (.*) (.*) (.*)$")
	public void validate_report_qrx_recon__iss(String transactionType, String yymmdd, String processingCode,
			String transactionAmount, String yyyymmdd, String responseCode, String acquirerId, String issuerId)
			throws IOException {
		ArrayList<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();
		String destination = "";
		
		if(transactionType.equalsIgnoreCase("Inbound")) {
			destination = issuerId.substring(1, 4) + "001";
		} else if(transactionType.equalsIgnoreCase("Outbound")) {
			destination = "00" + issuerId.substring(4, issuerId.length());
		}
		
		String fileName = "QRX_RECON_360004_" + destination + "_" + yymmdd + "_ISS_1";
		String filePath = userDir + fileName;
		list2 = sfr.getEveryColumn(filePath, scenario);
		vf.valReportRecon(list2, scenario, processingCode, transactionAmount, yyyymmdd, responseCode, acquirerId,
				issuerId);
	}

}
