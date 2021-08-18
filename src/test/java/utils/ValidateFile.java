package utils;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;

import io.cucumber.java.Scenario;
import junit.framework.Assert;

public class ValidateFile {

	public void valReportRecon(ArrayList<ArrayList<String>> list, Scenario scenario, String processingCode,
			String transactionAmount, String yyyymmdd, String responseCode, String acquirerId, String issuerId) {
		/*
		 * 1 - Processing Code = <1,7> 
		 * 2 - Transaction Amount = <1,8> 
		 * 3 - RH Date = <0,3> yyyymmdd
		 * 4 - RT Date = <2,3> yyyymmdd
		 * 5 - Recon Amount = <2,5> 
		 * 6 - Response Code = <1,16>
		 * 7 - Acquirer Id = <1,13> 
		 * 8 - Issuer Id = <1,14>
		 */
		String data1 = list.get(1).get(7);
		BigDecimal data2 = new BigDecimal(list.get(1).get(8));
		String data3 = list.get(0).get(3);
		String data4 = list.get(2).get(3);
		BigDecimal data5 = new BigDecimal(list.get(2).get(5));
		String data6 = list.get(1).get(16);
		String data7 = list.get(1).get(13).substring(3, list.get(1).get(13).length());
		String data8 = list.get(1).get(14).substring(3, list.get(1).get(14).length());
		ArrayList<Boolean> compareResults = new ArrayList<Boolean>();
		String failedCompare = "";
		try {
			if(!processingCode.equalsIgnoreCase(data1)) {
				compareResults.add(false);
				failedCompare =  failedCompare + processingCode + " | " + data1 + " -> Processing Code" + "\n"; 
			} else {
				compareResults.add(true);
			}
			
			if(!(new BigDecimal(transactionAmount).compareTo(data2) == 0)) {
				compareResults.add(false);
				failedCompare =  failedCompare + transactionAmount + " | " + data2 + " -> Transaction Amount"  + "\n";
			} else {
				compareResults.add(true);
			}
			if(!yyyymmdd.equalsIgnoreCase(data3)) {
				compareResults.add(false);
				failedCompare =  failedCompare + yyyymmdd + " | " + data3 + " -> RH Date"  + "\n";
			} else {
				compareResults.add(true);
			}
			
			if(!yyyymmdd.equalsIgnoreCase(data4)) {
				compareResults.add(false);
				failedCompare =  failedCompare + yyyymmdd + " | " + data4 + " -> RT Date"  + "\n";
			} else {
				compareResults.add(true);
			}
			
			if(!(new BigDecimal(transactionAmount).compareTo(data5) == 0)) {
				compareResults.add(false);
				failedCompare =  failedCompare + transactionAmount + " | " + data5 + " -> Recon Amount"  + "\n";
			} else {
				compareResults.add(true);
			}
			
			if(!responseCode.equalsIgnoreCase(data6)) {
				compareResults.add(false);
				failedCompare =  failedCompare + responseCode + " | " + data6 + " -> Response Code"  + "\n";
			} else {
				compareResults.add(true);
			}
			
			if(!acquirerId.equalsIgnoreCase(data7)) {
				compareResults.add(false);
				failedCompare =  failedCompare + acquirerId + " | " + data7 + " -> Acquirer Id"  + "\n";
			} else {
				compareResults.add(true);
			}
			
			if(!issuerId.equalsIgnoreCase(data8)) {
				compareResults.add(false);
				failedCompare =  failedCompare + issuerId + " | " + data8 + " -> Issuer Id"  + "\n";
			} else {
				compareResults.add(true);
			}
			if(compareResults.contains(false)) {
				scenario.attach(failedCompare, "text/plain", "Unmatched Data");
				fail("Comparing Data Failed");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
