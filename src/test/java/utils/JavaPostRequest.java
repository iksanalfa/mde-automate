package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



import io.cucumber.java.Scenario;

public class JavaPostRequest {
		
	public void postRequest(Scenario scenario) {
		try {			
			TrustAllCertificates.install();
			URL url = new URL("https://10.132.130.252:8443/online-data-capture/1.0.0");
			HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");

			String data = "{\r\n" + 
					"    \"InternalData\": {\r\n" + 
					"        \"IN.ACCOUNT_FROM\": \"0\",\r\n" + 
					"        \"IN.ADD_DATA\": \"61051295062070703A01\",\r\n" + 
					"        \"IN.ADD_INFO\": \"\",\r\n" + 
					"        \"IN.AMOUNT\": \"7000000\",\r\n" + 
					"        \"IN.API_IS_REQ\": \"1\",\r\n" + 
					"        \"IN.APPROVAL_CODE\": \"28260\",\r\n" + 
					"        \"IN.COUNTRY\": \"ID\",\r\n" + 
					"        \"IN.CURRENCY\": \"360\",\r\n" + 
					"        \"IN.CUSTOMER_PAN\": \"@0002tdhMjvufNJ/qgkFEFLoasYlO29PvbYPR4Mv0atvClXU=\",\r\n" + 
					"        \"IN.DT\": \"2021-09-16 10:14:32\",\r\n" + 
					"        \"IN.FORWARDING_ID\": \"360004\",\r\n" + 
					"        \"IN.MASK_PAN\": \"936000XXXXXXXXX7221\",\r\n" + 
					"        \"IN.MC\": \"2\",\r\n" + 
					"        \"IN.MCC\": \"5713\",\r\n" + 
					"        \"IN.MERCHANT_CITY\": \"Jakarta Selatan\",\r\n" + 
					"        \"IN.MERCHANT_COUNTRY_CODE\": \"ID\",\r\n" + 
					"        \"IN.MERCHANT_CRITERIA\": \"UME\",\r\n" + 
					"        \"IN.MERCHANT_NAME\": \"SPBU TEST\",\r\n" + 
					"        \"IN.MERCHANT_PAN\": \"@0002X/QU2QzVtjJ4Htwgf71ZpTPvyRdrqqF3XOgoh1TICIA=\",\r\n" + 
					"        \"IN.MID\": \"71000247229\",\r\n" + 
					"        \"IN.MSG_ID\": \"20210916200233890990360004123666789395\",\r\n" + 
					"        \"IN.MT\": \"3001\",\r\n" + 
					"        \"IN.PARTACQ\": \"1801\",\r\n" + 
					"        \"IN.PARTISS\": \"1792\",\r\n" + 
					"        \"IN.PCI_DATA_KEY\": \"f03e66f0-dd40-11eb-8c3a-000c29a72de6\",\r\n" + 
					"        \"IN.PCI_DATA_SOURCE\": \"JALIN_CONTAINER1\",\r\n" + 
					"        \"IN.PERDAY_ACCUMULATED_AMOUNT_O\": \"7000000\",\r\n" + 
					"        \"IN.POSTAL_CODE\": \"12950\",\r\n" + 
					"        \"IN.PROCCODE\": \"260000\",\r\n" + 
					"        \"IN.RRN\": \"123980180382\",\r\n" + 
					"        \"IN.SETTLEMENT_DATE\": \"2021-09-16\",\r\n" + 
					"        \"IN.SRC_HTTP_BODY\": \"{\\n  \\\"pmtcrInf\\\": {\\n    \\\"msgId\\\": \\\"20210916200233890990360004123666789395\\\",\\n    \\\"proCode\\\": \\\"260000\\\",\\n    \\\"rrn\\\": \\\"123980180382\\\",\\n    \\\"stan\\\": \\\"255367\\\",\\n    \\\"apprCd\\\": \\\"28260\\\",\\n    \\\"locAmt\\\": 7000000,\\n    \\\"locCurr\\\": \\\"360\\\",\\n    \\\"tipconvVal\\\": 0,\\n    \\\"cpan\\\": \\\"9458099100000282605\\\",\\n    \\\"loctrnDt\\\": \\\"2021-09-16T10:14:32\\\",\\n    \\\"stmDt\\\": \\\"2021-07-06\\\"\\n  },\\n  \\\"mchtinfId\\\": {\\n    \\\"mpan\\\": \\\"9360000801000247221\\\",\\n    \\\"id\\\": \\\"71000247229\\\",\\n    \\\"nm\\\": \\\"SPBU TEST\\\",\\n    \\\"crit\\\": \\\"UME\\\",\\n    \\\"mcc\\\": \\\"5713\\\",\\n    \\\"addr\\\": {\\n      \\\"city\\\": \\\"Jakarta Selatan\\\",\\n      \\\"ctCd\\\": \\\"ID\\\"\\n    },\\n    \\\"tid\\\": \\\"A01\\\",\\n    \\\"additionalData\\\": {\\n      \\\"nat\\\": \\\"61051295062070703A01\\\"\\n    }\\n  }\\n}\",\r\n" + 
					"        \"IN.SRC_HTTP_TYPE\": \"3\",\r\n" + 
					"        \"IN.SRC_HTTP_URL\": \"/jalin/V2.1.8.0/qr/cb/paymentCredit\",\r\n" + 
					"        \"IN.STAN\": \"255367\",\r\n" + 
					"        \"IN.TAG_62\": \"0703A01\",\r\n" + 
					"        \"IN.TID\": \"A01\",\r\n" + 
					"        \"IN.TIP_VALUE_FIXED\": \"0\",\r\n" + 
					"        \"IN.TRANSTIME\": \"2021-09-16 10:14:32\",\r\n" + 
					"        \"IN.TRANSTIME_ISO_UTC\": \"20210916T032734.320\",\r\n" + 
					"        \"IN.TRANSTIME_UTC\": \"2021-09-16 03:27:34\",\r\n" + 
					"        \"OT.ACQ_SETTLEMENT_DATE\": \"2021-09-16\",\r\n" + 
					"        \"OT.FEE_AMOUNT\": \"0\",\r\n" + 
					"        \"OT.FEE_TYPE\": \"C\",\r\n" + 
					"        \"OT.INVOICE_NUMBER\": \"17458027341745802734\",\r\n" + 
					"        \"OT.ISS_SETTLEMENT_DATE\": \"2021-09-16\",\r\n" + 
					"        \"OT.MA\": \"1\",\r\n" + 
					"        \"OT.MSG_ID\": \"\",\r\n" + 
					"        \"OT.RC\": \"00\",\r\n" + 
					"        \"IR.RC\": \"00\",\r\n" + 
					"        \"OT.SWITCH_SETTLEMENT_DATE\": \"2021-09-16\",\r\n" + 
					"        \"OT.TOTAL_LEGS\": \"1\",\r\n" + 
					"        \"IR.ACQUIRER_ID\": \"93600008\",\r\n" + 
					"        \"IR.FORWARDING_ID\": \"360004\",\r\n" + 
					"        \"IR.ISSUER_ID\": \"94580991\"\r\n" + 
					"    },\r\n" + 
					"    \"EndpointData_PAYNET_REQ_IN\": {\r\n" + 
					"        \"MSG_DIRECTION\": \"IN\",\r\n" + 
					"        \"REQ_RESP\": \"REQ\",\r\n" + 
					"        \"NETWORK_TYPE\": \"PAYNET\",\r\n" + 
					"        \"msgId\": \"20210916200233890990360004123666789395\",\r\n" + 
					"        \"proCode\": \"260000\",\r\n" + 
					"        \"rrn\": \"123980180382\",\r\n" + 
					"        \"stan\": \"255367\",\r\n" + 
					"        \"apprCd\": \"28260\",\r\n" + 
					"        \"locCurr\": \"360\",\r\n" + 
					"        \"cpan\": \"9458099100000282605\",\r\n" + 
					"        \"loctrnDt\": \"2021-09-16T10:14:32\",\r\n" + 
					"        \"stmDt\": \"2021-09-16\",\r\n" + 
					"        \"mpan\": \"9360000801000247221\",\r\n" + 
					"        \"id\": \"71000247229\",\r\n" + 
					"        \"nm\": \"SPBU TEST\",\r\n" + 
					"        \"crit\": \"UME\",\r\n" + 
					"        \"mcc\": \"5713\",\r\n" + 
					"        \"tid\": \"A01\",\r\n" + 
					"        \"city\": \"Jakarta Selatan\",\r\n" + 
					"        \"ctCd\": \"ID\",\r\n" + 
					"        \"nat\": \"61051295062070703A01\"\r\n" + 
					"    },\r\n" + 
					"    \"EndpointData_JALIN_REQ_OUT\": {\r\n" + 
					"        \"MSG_DIRECTION\": \"OUT\",\r\n" + 
					"        \"REQ_RESP\": \"REQ\",\r\n" + 
					"        \"NETWORK_TYPE\": \"JALIN\",\r\n" + 
					"        \"pan\": \"9360000801000247221\",\r\n" + 
					"        \"processingCode\": \"260000\",\r\n" + 
					"        \"transactionAmount\": \"70000.00\",\r\n" + 
					"        \"transmissionDateTime\": \"20210916032734\",\r\n" + 
					"        \"systemTraceAuditNumber\": \"255367\",\r\n" + 
					"        \"localTransactionDateTime\": \"20210916102734\",\r\n" + 
					"        \"settlementDate\": \"20210916\",\r\n" + 
					"        \"captureDate\": \"20210916\",\r\n" + 
					"        \"merchantType\": \"5713\",\r\n" + 
					"        \"posEntryMode\": \"011\",\r\n" + 
					"        \"feeType\": \"C\",\r\n" + 
					"        \"feeAmount\": \"0.00\",\r\n" + 
					"        \"acquirerId\": \"93600008\",\r\n" + 
					"        \"issuerId\": \"94580991\",\r\n" + 
					"        \"forwardingId\": \"360004\",\r\n" + 
					"        \"rrn\": \"123980180382\",\r\n" + 
					"        \"approvalCode\": \"28260\",\r\n" + 
					"        \"terminalId\": \"A01\",\r\n" + 
					"        \"merchantId\": \"71000247229\",\r\n" + 
					"        \"merchantName\": \"SPBU TEST\",\r\n" + 
					"        \"merchantCity\": \"Jakarta Selat\",\r\n" + 
					"        \"merchantCountry\": \"ID\",\r\n" + 
					"        \"productIndicator\": \"Q097\",\r\n" + 
					"        \"customerData\": \"Jalin QR Payment\",\r\n" + 
					"        \"merchantCriteria\": \"UME\",\r\n" + 
					"        \"currencyCode\": \"360\",\r\n" + 
					"        \"postalCode\": \"12950\",\r\n" + 
					"        \"additionalField\": \"0703A01\",\r\n" + 
					"        \"customerPan\": \"9458099100000282605\"\r\n" + 
					"    },\r\n" + 
					"    \"EndpointData_JALIN_RESP_IN\": {\r\n" + 
					"        \"MSG_DIRECTION\": \"IN\",\r\n" + 
					"        \"REQ_RESP\": \"RESP\",\r\n" + 
					"        \"NETWORK_TYPE\": \"JALIN\",\r\n" + 
					"        \"responseCode\": \"00\",\r\n" + 
					"        \"invoiceNumber\": \"17458027341745802734\"\r\n" + 
					"    },\r\n" + 
					"    \"EndpointData_PAYNET_RESP_OUT\": {\r\n" + 
					"        \"MSG_DIRECTION\": \"OUT\",\r\n" + 
					"        \"REQ_RESP\": \"RESP\",\r\n" + 
					"        \"NETWORK_TYPE\": \"PAYNET\",\r\n" + 
					"        \"msgId\": \"20210916200233890990360004123666789395\",\r\n" + 
					"        \"rspCd\": \"ACTC\",\r\n" + 
					"        \"invNum\": \"1745802734\",\r\n" + 
					"        \"rsnCd\": \"0000\",\r\n" + 
					"        \"stmDt\": \"2021-09-16\"\r\n" + 
					"    },\r\n" + 
					"    \"MetaData\": {\r\n" + 
					"        \"UUID\": \"f03e66f0-dd40-11eb-8c3a-000c29a72de6\",\r\n" + 
					"        \"TRANS_ID\": \"8002\",\r\n" + 
					"        \"ACQ_PARTICIPANT\": \"1801\",\r\n" + 
					"        \"ISS_PARTICIPANT\": \"1792\",\r\n" + 
					"        \"TRANS_RESULT\": \"0\",\r\n" + 
					"        \"REF_UUID\": \"\",\r\n" + 
					"        \"TRANS_STATUS\": \"1\"\r\n" + 
					"    }\r\n" + 
					"}";
			
			byte[] out = data.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = http.getOutputStream();
			stream.write(out);

			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			String responseHit = http.getResponseCode() + " " + http.getResponseMessage();
			scenario.attach(responseHit, "text/plain", "Response Code");
			http.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
