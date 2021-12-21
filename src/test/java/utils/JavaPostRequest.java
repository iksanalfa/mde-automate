package utils;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



import io.cucumber.java.Scenario;

public class JavaPostRequest {
		
	public void postRequest(Scenario scenario, String rrn, String transId, String transactionAmount,
			String transactionDate, String acquirerId, String issuerId, String processingCode,
			String responseCode, String transactionType) {
		try {			
			TrustAllCertificates.install();
//			URL url = new URL("https://10.132.130.252:8443/online-data-capture/1.0.0"); //Jalin
			URL url = new URL("https://10.20.16.30:8443/online-data-capture/1.0.0"); //EN
			HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");
			
			String dateFormat3 = transactionDate.substring(0, 4) + "-" + transactionDate.substring(4, 6) + "-" + transactionDate.substring(6, 8);
			transactionAmount = transactionAmount + "00";
			
			String inCheckStatus = "{\r\n" + 
					"   \"InternalData\":{\r\n" + 
					"      \"IN.ACCOUNT_FROM\":\"0\",\r\n" + 
					"      \"IN.ADD_DATA\":\"61051295062070703A01\",\r\n" + 
					"      \"IN.ADD_INFO\":\"\",\r\n" + 
					"      \"IN.AMOUNT\":\"220000000\",\r\n" + 
					"      \"IN.API_IS_REQ\":\"1\",\r\n" + 
					"      \"IN.APPROVAL_CODE\":\"28260\",\r\n" + 
					"      \"IN.COUNTRY\":\"ID\",\r\n" + 
					"      \"IN.CURRENCY\":\"360\",\r\n" + 
					"      \"IN.CUSTOMER_PAN\":\"@0002tdhMjvufNJ/qgkFEFLoasYlO29PvbYPR4Mv0atvClXU=\",\r\n" + 
					"      \"IN.DT\":\"2021-07-05 10:14:32\",\r\n" + 
					"      \"IN.FORWARDING_ID\":\"360004\",\r\n" + 
					"      \"IN.MASK_PAN\":\"936000XXXXXXXXX7221\",\r\n" + 
					"      \"IN.MC\":\"2\",\r\n" + 
					"      \"IN.MCC\":\"5713\",\r\n" + 
					"      \"IN.MERCHANT_CITY\":\"Jakarta Selatan\",\r\n" + 
					"      \"IN.MERCHANT_COUNTRY_CODE\":\"ID\",\r\n" + 
					"      \"IN.MERCHANT_CRITERIA\":\"UME\",\r\n" + 
					"      \"IN.MERCHANT_NAME\":\"SPBU TEST\",\r\n" + 
					"      \"IN.MERCHANT_PAN\":\"@0002X/QU2QzVtjJ4Htwgf71ZpTPvyRdrqqF3XOgoh1TICIA=\",\r\n" + 
					"      \"IN.MID\":\"71000247229\",\r\n" + 
					"      \"IN.MSG_ID\":\"20210705200233890990360004123666789395\",\r\n" + 
					"      \"IN.MT\":\"3002\",\r\n" + 
					"      \"IN.PARTACQ\":\"1801\",\r\n" + 
					"      \"IN.PARTISS\":\"1792\",\r\n" + 
					"      \"IN.PCI_DATA_KEY\":\"aa648d7c-dd49-11eb-8c3a-000c29a72de6\",\r\n" + 
					"      \"IN.PCI_DATA_SOURCE\":\"JALIN_CONTAINER1\",\r\n" + 
					"      \"IN.POSTAL_CODE\":\"12950\",\r\n" + 
					"      \"IN.PROCCODE\":\"360000\",\r\n" + 
					"      \"IN.RRN\":\"123980180365\",\r\n" + 
					"      \"IN.SETTLEMENT_DATE\":\"2021-07-06\",\r\n" + 
					"      \"IN.SRC_HTTP_BODY\":\"{\\n  \\\"pmtcsInf\\\": {\\n    \\\"msgId\\\": \\\"20210705200233890990360004123666789395\\\",\\n    \\\"proCode\\\": \\\"360000\\\",\\n    \\\"rrn\\\": \\\"123980180365\\\",\\n    \\\"stan\\\": \\\"888583\\\",\\n    \\\"apprCd\\\": \\\"28260\\\",\\n    \\\"locAmt\\\": 220000000,\\n    \\\"locCurr\\\": \\\"360\\\",\\n    \\\"tipconvVal\\\": 0,\\n    \\\"cpan\\\": \\\"9458099100000282605\\\",\\n    \\\"loctrnDt\\\": \\\"2021-07-05T10:14:32\\\",\\n    \\\"stmDt\\\": \\\"2021-07-06\\\"\\n  },\\n  \\\"mchtinfId\\\": {\\n    \\\"mpan\\\": \\\"9360000801000247221\\\",\\n    \\\"id\\\": \\\"71000247229\\\",\\n    \\\"nm\\\": \\\"SPBU TEST\\\",\\n    \\\"crit\\\": \\\"UME\\\",\\n    \\\"mcc\\\": \\\"5713\\\",\\n    \\\"addr\\\": {\\n      \\\"city\\\": \\\"Jakarta Selatan\\\",\\n      \\\"ctCd\\\": \\\"ID\\\"\\n    },\\n    \\\"tid\\\": \\\"A01\\\",\\n    \\\"additionalData\\\": {\\n      \\\"nat\\\": \\\"61051295062070703A01\\\"\\n    }\\n  }\\n}\",\r\n" + 
					"      \"IN.SRC_HTTP_TYPE\":\"3\",\r\n" + 
					"      \"IN.SRC_HTTP_URL\":\"/jalin/V2.1.8.0/qr/cb/checkstatus\",\r\n" + 
					"      \"IN.STAN\":\"888583\",\r\n" + 
					"      \"IN.TID\":\"A01\",\r\n" + 
					"      \"IN.TIP_VALUE_FIXED\":\"0\",\r\n" + 
					"      \"IN.TRANSTIME\":\"2021-07-05 10:14:32\",\r\n" + 
					"      \"IN.TRANSTIME_ISO_UTC\":\"20210705T043002.600\",\r\n" + 
					"      \"IN.TRANSTIME_UTC\":\"2021-07-05 04:30:02\",\r\n" + 
					"      \"OT.ACQ_SETTLEMENT_DATE\":\"2021-07-05\",\r\n" + 
					"      \"OT.FEE_AMOUNT\":\"0\",\r\n" + 
					"      \"OT.FEE_TYPE\":\"C\",\r\n" + 
					"      \"OT.INVOICE_NUMBER\":\"17458027341745802734\",\r\n" + 
					"      \"OT.ISS_SETTLEMENT_DATE\":\"2021-07-05\",\r\n" + 
					"      \"OT.MA\":\"1\",\r\n" + 
					"      \"OT.MSG_ID\":\"\",\r\n" + 
					"      \"OT.RC\":\"00\",\r\n" + 
					"      \"OT.SWITCH_SETTLEMENT_DATE\":\"2021-07-05\",\r\n" + 
					"      \"OT.TOTAL_LEGS\":\"1\",\r\n" + 
					"      \"IR.ACQUIRER_ID\":\"93600008\",\r\n" + 
					"      \"IR.ISSUER_ID\":\"94580991\"\r\n" + 
					"   },\r\n" + 
					"   \"EndpointData_PAYNET_REQ_IN\":{\r\n" + 
					"      \"MSG_DIRECTION\":\"IN\",\r\n" + 
					"      \"REQ_RESP\":\"REQ\",\r\n" + 
					"      \"NETWORK_TYPE\":\"PAYNET\",\r\n" + 
					"      \"msgId\":\"20210705200233890990360004123666789395\",\r\n" + 
					"      \"proCode\":\"360000\",\r\n" + 
					"      \"rrn\":\"123980180365\",\r\n" + 
					"      \"stan\":\"888583\",\r\n" + 
					"      \"apprCd\":\"28260\",\r\n" + 
					"      \"locCurr\":\"360\",\r\n" + 
					"      \"cpan\":\"9458099100000282605\",\r\n" + 
					"      \"loctrnDt\":\"2021-07-05T10:14:32\",\r\n" + 
					"      \"stmDt\":\"2021-07-06\",\r\n" + 
					"      \"mpan\":\"9360000801000247221\",\r\n" + 
					"      \"id\":\"71000247229\",\r\n" + 
					"      \"nm\":\"SPBU TEST\",\r\n" + 
					"      \"crit\":\"UME\",\r\n" + 
					"      \"mcc\":\"5713\",\r\n" + 
					"      \"tid\":\"A01\",\r\n" + 
					"      \"city\":\"Jakarta Selatan\",\r\n" + 
					"      \"ctCd\":\"ID\",\r\n" + 
					"      \"nat\":\"61051295062070703A01\"\r\n" + 
					"   },\r\n" + 
					"   \"EndpointData_JALIN_REQ_OUT\":{\r\n" + 
					"      \"MSG_DIRECTION\":\"OUT\",\r\n" + 
					"      \"REQ_RESP\":\"REQ\",\r\n" + 
					"      \"NETWORK_TYPE\":\"JALIN\",\r\n" + 
					"      \"pan\":\"9360000801000247221\",\r\n" + 
					"      \"processingCode\":\"360000\",\r\n" + 
					"      \"transactionAmount\":\"2200000.00\",\r\n" + 
					"      \"systemTraceAuditNumber\":\"888583\",\r\n" + 
					"      \"localTransactionDateTime\":\"20210705043002\",\r\n" + 
					"      \"transmissionDateTime\":\"20210705043002\",\r\n" + 
					"      \"settlementDate\":\"20210706\",\r\n" + 
					"      \"captureDate\":\"20210706\",\r\n" + 
					"      \"merchantType\":\"5713\",\r\n" + 
					"      \"posEntryMode\":\"011\",\r\n" + 
					"      \"feeType\":\"C\",\r\n" + 
					"      \"feeAmount\":\"0.00\",\r\n" + 
					"      \"acquirerId\":\"93600008\",\r\n" + 
					"      \"issuerId\":\"94580991\",\r\n" + 
					"      \"forwardingId\":\"360004\",\r\n" + 
					"      \"rrn\":\"123980180365\",\r\n" + 
					"      \"approvalCode\":\"28260\",\r\n" + 
					"      \"terminalId\":\"A01\",\r\n" + 
					"      \"merchantId\":\"71000247229\",\r\n" + 
					"      \"merchantName\":\"SPBU TEST\",\r\n" + 
					"      \"merchantCity\":\"Jakarta Selat\",\r\n" + 
					"      \"merchantCountry\":\"ID\",\r\n" + 
					"      \"productIndicator\":\"Q097\",\r\n" + 
					"      \"merchantCriteria\":\"UME\",\r\n" + 
					"      \"currencyCode\":\"360\",\r\n" + 
					"      \"customerData\":\"Jalin QR Payment\",\r\n" + 
					"      \"postalCode\":\"12950\",\r\n" + 
					"      \"additionalField\":\"0703A01\",\r\n" + 
					"      \"customerPan\":\"9458099100000282605\"\r\n" + 
					"   },\r\n" + 
					"   \"EndpointData_JALIN_RESP_IN\":{\r\n" + 
					"      \"MSG_DIRECTION\":\"IN\",\r\n" + 
					"      \"REQ_RESP\":\"RESP\",\r\n" + 
					"      \"NETWORK_TYPE\":\"JALIN\",\r\n" + 
					"      \"responseCode\":\"00\",\r\n" + 
					"      \"invoiceNumber\":\"17458027341745802734\"\r\n" + 
					"   },\r\n" + 
					"   \"EndpointData_PAYNET_RESP_OUT\":{\r\n" + 
					"      \"MSG_DIRECTION\":\"OUT\",\r\n" + 
					"      \"REQ_RESP\":\"RESP\",\r\n" + 
					"      \"NETWORK_TYPE\":\"PAYNET\",\r\n" + 
					"      \"msgId\":\"20210705200233890990360004123666789395\",\r\n" + 
					"      \"rspCd\":\"ACTC\",\r\n" + 
					"      \"invNum\":\"1745802734\",\r\n" + 
					"      \"rsnCd\":\"0000\",\r\n" + 
					"      \"stmDt\":\"2021-07-06\"\r\n" + 
					"   },\r\n" + 
					"   \"MetaData\":{\r\n" + 
					"      \"UUID\":\"aa648d7c-dd49-11eb-8c3a-000c29a72de6\",\r\n" + 
					"      \"TRANS_ID\":\"7003\",\r\n" + 
					"      \"ACQ_PARTICIPANT\":\"1801\",\r\n" + 
					"      \"ISS_PARTICIPANT\":\"1792\",\r\n" + 
					"      \"TRANS_RESULT\":\"0\",\r\n" + 
					"      \"REF_UUID\":\"\",\r\n" + 
					"      \"TRANS_STATUS\":\"1\"\r\n" + 
					"   }\r\n" + 
					"}";
			
			String inInquiry = "{\r\n" + 
					"   \"InternalData\":{\r\n" + 
					"      \"IN.ACCOUNT_FROM\":\"0\",\r\n" + 
					"      \"IN.ADD_DATA_62\":\"0703A01\",\r\n" + 
					"      \"IN.ADD_TERMINAL_LABEL\":\"A01\",\r\n" + 
					"      \"IN.API_IS_REQ\":\"1\",\r\n" + 
					"      \"IN.BIN_CARD_BILLING_CURRENCY\":\"360\",\r\n" + 
					"      \"IN.BIN_CARD_ISSUED_REGION\":\"B\",\r\n" + 
					"      \"IN.BIN_ISSUEING_COUNTRY\":\"360\",\r\n" + 
					"      \"IN.BIN_TYPE\":\"1\",\r\n" + 
					"      \"IN.COUNTRY\":\"ID\",\r\n" + 
					"      \"IN.CRC\":\"6C60\",\r\n" + 
					"      \"IN.CURRENCY\":\"360\",\r\n" + 
					"      \"IN.DT\":\"2021-07-05 15:25:43\",\r\n" + 
					"      \"IN.FORWARDING_ID\":\"360004\",\r\n" + 
					"      \"IN.GATEWAY_ID\":\"2\",\r\n" + 
					"      \"IN.ISSUER_ID\":\"93600008\",\r\n" + 
					"      \"IN.MC\":\"2\",\r\n" + 
					"      \"IN.MCC\":\"5713\",\r\n" + 
					"      \"IN.MERCHANT_CITY\":\"Jakarta Selatan\",\r\n" + 
					"      \"IN.MERCHANT_CRITERIA\":\"UME\",\r\n" + 
					"      \"IN.MERCHANT_NAME\":\"SPBU TEST\",\r\n" + 
					"      \"IN.MSG_ID\":\"20210705200233899024360004567980187124\",\r\n" + 
					"      \"IN.MT\":\"3000\",\r\n" + 
					"      \"IN.PAN\":\"@0002k809PONLijAPk7wALKu9xbhYF/cBFaqa3pXVmHUHjlM=\",\r\n" + 
					"      \"IN.PARTACQ\":\"1801\",\r\n" + 
					"      \"IN.PARTISS\":\"1792\",\r\n" + 
					"      \"IN.PCI_DATA_KEY\":\"3f4c64ae-dd3c-11eb-b787-000c29a72de6\",\r\n" + 
					"      \"IN.PCI_DATA_SOURCE\":\"JALIN_CONTAINER1\",\r\n" + 
					"      \"IN.PERDAY_ACCUMULATED_AMOUNT_O\":\"-1\",\r\n" + 
					"      \"IN.POSTAL_CODE\":\"12950\",\r\n" + 
					"      \"IN.PROCCODE\":\"380000\",\r\n" + 
					"      \"IN.QR_MERCH_ACC_INFO_CRI_26\":\"UME\",\r\n" + 
					"      \"IN.QR_MERCH_ACC_INFO_CRI_51\":\"UME\",\r\n" + 
					"      \"IN.QR_MERCH_ACC_INFO_GUI_26\":\"ID.CO.BANKMANDIRI.WWW\",\r\n" + 
					"      \"IN.QR_MERCH_ACC_INFO_GUI_51\":\"ID.CO.QRIS.WWW\",\r\n" + 
					"      \"IN.QR_MERCH_ACC_INFO_ID_26\":\"71000247229\",\r\n" + 
					"      \"IN.QR_MERCH_ACC_INFO_ID_51\":\"ID1020032533686\",\r\n" + 
					"      \"IN.QR_MERCH_ACC_INFO_PAN_26\":\"936000XXXXXXXX4722\",\r\n" + 
					"      \"IN.QR_MERCH_ACC_INFO_PAN_51\":\"936000XXXXXXXX4722\",\r\n" + 
					"      \"IN.QR_PAYLOAD_FORMAT_IND\":\"01\",\r\n" + 
					"      \"IN.QR_POINT_OF_INIT\":\"11\",\r\n" + 
					"      \"IN.RRN\":\"567980187124\",\r\n" + 
					"      \"IN.SETTLEMENT_DATE\":\"2021-07-06\",\r\n" + 
					"      \"IN.SRC_HTTP_BODY\":\"{\\r\\n     \\\"pmtInf\\\":{ \\r\\n        \\\"msgId\\\": \\\"20210705200233899024360004567980187124\\\",\\r\\n        \\\"proCode\\\": \\\"380000\\\",\\r\\n        \\\"loctrnDt\\\": \\\"2021-07-05T15:25:43\\\", \\r\\n        \\\"stmDt\\\": \\\"2021-07-06\\\",\\r\\n        \\\"rrn\\\": \\\"567980187124\\\", \\r\\n        \\\"stan\\\": \\\"899024\\\"                \\r\\n     }, \\r\\n     \\\"qrStr\\\": {\\r\\n        \\\"qrstrInf\\\":\\r\\n\\\"00020101021126690021ID.CO.BANKMANDIRI.WWW01189360000801000247220211710002472290303UME51440014ID.CO.QRIS.WWW0215ID10200325336860303UME5204571353033605802ID5909SPBU TEST6015Jakarta Selatan61051295062070703A0163046C60\\\"         \\r\\n     }\\r\\n}\",\r\n" + 
					"      \"IN.SRC_HTTP_TYPE\":\"3\",\r\n" + 
					"      \"IN.SRC_HTTP_URL\":\"/jalin/V2.1.8.0/qr/cb/qrquery\",\r\n" + 
					"      \"IN.STAN\":\"899024\",\r\n" + 
					"      \"IN.TRANSTIME\":\"2021-07-05 15:25:43\",\r\n" + 
					"      \"IN.TRANSTIME_ISO_UTC\":\"20210705T025359.468\",\r\n" + 
					"      \"IN.TRANSTIME_UTC\":\"2021-07-05 02:53:59\",\r\n" + 
					"      \"OT.ACQ_SETTLEMENT_DATE\":\"2021-07-05\",\r\n" + 
					"      \"OT.ISS_SETTLEMENT_DATE\":\"2021-07-05\",\r\n" + 
					"      \"OT.MA\":\"1\",\r\n" + 
					"      \"OT.RC\":\"0\",\r\n" + 
					"      \"OT.SWITCH_SETTLEMENT_DATE\":\"2021-07-05\",\r\n" + 
					"      \"OT.TOTAL_LEGS\":\"1\",\r\n" + 
					"      \"IR.ISSUER_ID\":\"93600008\",\r\n" + 
					"      \"IN.MERCHANT_PAN\":\"@0002X/QU2QzVtjJ4Htwgf71ZpTPvyRdrqqF3XOgoh1TICIA=\",\r\n" + 
					"      \"IN.TID\":\"A01\"\r\n" + 
					"   },\r\n" + 
					"   \"EndpointData_PAYNET_REQ_IN\":{\r\n" + 
					"      \"MSG_DIRECTION\":\"IN\",\r\n" + 
					"      \"REQ_RESP\":\"REQ\",\r\n" + 
					"      \"NETWORK_TYPE\":\"PAYNET\",\r\n" + 
					"      \"msgId\":\"20210705200233899024360004567980187124\",\r\n" + 
					"      \"proCode\":\"380000\",\r\n" + 
					"      \"loctrnDt\":\"2021-07-05T15:25:43\",\r\n" + 
					"      \"stmDt\":\"2021-07-06\",\r\n" + 
					"      \"rrn\":\"567980187124\",\r\n" + 
					"      \"stan\":\"899024\",\r\n" + 
					"      \"qrstrInf\":\"00020101021126690021ID.CO.BANKMANDIRI.WWW01189360000801000247220211710002472290303UME51440014ID.CO.QRIS.WWW0215ID10200325336860303UME5204571353033605802ID5909SPBU TEST6015Jakarta Selatan61051295062070703A0163046C60\"\r\n" + 
					"   },\r\n" + 
					"   \"EndpointData_PAYNET_RESP_OUT\":{\r\n" + 
					"      \"MSG_DIRECTION\":\"OUT\",\r\n" + 
					"      \"REQ_RESP\":\"RESP\",\r\n" + 
					"      \"NETWORK_TYPE\":\"PAYNET\",\r\n" + 
					"      \"msgId\":\"20210705200233899024360004567980187124\",\r\n" + 
					"      \"rspCd\":\"ACTC\",\r\n" + 
					"      \"rsnCd\":\"0000\",\r\n" + 
					"      \"stmDt\":\"2021-07-06\",\r\n" + 
					"      \"qrTyp\":\"static\",\r\n" + 
					"      \"locCurr\":\"360\",\r\n" + 
					"      \"mpan\":\"936000XXXXXXXXX7221\",\r\n" + 
					"      \"id\":\"71000247229\",\r\n" + 
					"      \"nm\":\"SPBU TEST\",\r\n" + 
					"      \"crit\":\"UME\",\r\n" + 
					"      \"mcc\":\"5713\",\r\n" + 
					"      \"tid\":\"A01\",\r\n" + 
					"      \"city\":\"Jakarta Selatan\",\r\n" + 
					"      \"ctCd\":\"ID\",\r\n" + 
					"      \"nat\":\"61051295062070703A01\"\r\n" + 
					"   },\r\n" + 
					"   \"MetaData\":{\r\n" + 
					"      \"UUID\":\"3f4c64ae-dd3c-11eb-b787-000c29a72de6\",\r\n" + 
					"      \"TRANS_ID\":\"6988\",\r\n" + 
					"      \"ACQ_PARTICIPANT\":\"1801\",\r\n" + 
					"      \"ISS_PARTICIPANT\":\"1792\",\r\n" + 
					"      \"TRANS_RESULT\":\"0\",\r\n" + 
					"      \"REF_UUID\":\"\",\r\n" + 
					"      \"TRANS_STATUS\":\"1\"\r\n" + 
					"   }\r\n" + 
					"}";

			String inPayment = "{\r\n" + 
					"    \"InternalData\": {\r\n" + 
					"        \"IN.ACCOUNT_FROM\": \"0\",\r\n" + 
					"        \"IN.ADD_DATA\": \"61051295062070703A01\",\r\n" + 
					"        \"IN.ADD_INFO\": \"\",\r\n" + 
					"        \"IN.AMOUNT\": \"" +transactionAmount+ "\",\r\n" + 
					"        \"IN.API_IS_REQ\": \"1\",\r\n" + 
					"        \"IN.APPROVAL_CODE\": \"28260\",\r\n" + 
					"        \"IN.COUNTRY\": \"ID\",\r\n" + 
					"        \"IN.CURRENCY\": \"360\",\r\n" + 
					"        \"IN.CUSTOMER_PAN\": \"@0002tdhMjvufNJ/qgkFEFLoasYlO29PvbYPR4Mv0atvClXU=\",\r\n" + 
					"        \"IN.DT\": \"" +dateFormat3+ " 10:14:32\",\r\n" + 
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
					"        \"IN.MSG_ID\": \"" +transactionDate+ "200233890990360004123666789395\",\r\n" + 
					"        \"IN.MT\": \"3001\",\r\n" + 
					"        \"IN.PARTACQ\": \"1801\",\r\n" + 
					"        \"IN.PARTISS\": \"1792\",\r\n" + 
					"        \"IN.PCI_DATA_KEY\": \"f03e66f0-dd40-11eb-8c3a-000c29a72de6\",\r\n" + 
					"        \"IN.PCI_DATA_SOURCE\": \"JALIN_CONTAINER1\",\r\n" + 
					"        \"IN.PERDAY_ACCUMULATED_AMOUNT_O\": \"" +transactionAmount+ "\",\r\n" + 
					"        \"IN.POSTAL_CODE\": \"12950\",\r\n" + 
					"        \"IN.PROCCODE\": \"" +processingCode+ "\",\r\n" + 
					"        \"IN.RRN\": \"" +rrn+ "\",\r\n" + 
					"        \"IN.SETTLEMENT_DATE\": \"" +dateFormat3+ "\",\r\n" + 
					"        \"IN.SRC_HTTP_BODY\": \"{\\n  \\\"pmtcrInf\\\": {\\n    \\\"msgId\\\": \\\"" +transactionDate+ "200233890990360004123666789395\\\",\\n    \\\"proCode\\\": \\\"" +processingCode+ "\\\",\\n    \\\"rrn\\\": \\\"" +rrn+ "\\\",\\n    \\\"stan\\\": \\\"255367\\\",\\n    \\\"apprCd\\\": \\\"28260\\\",\\n    \\\"locAmt\\\": " +transactionAmount+ ",\\n    \\\"locCurr\\\": \\\"360\\\",\\n    \\\"tipconvVal\\\": 0,\\n    \\\"cpan\\\": \\\"" +issuerId+ "00000282605\\\",\\n    \\\"loctrnDt\\\": \\\"" +dateFormat3+ "T10:14:32\\\",\\n    \\\"stmDt\\\": \\\"2021-07-06\\\"\\n  },\\n  \\\"mchtinfId\\\": {\\n    \\\"mpan\\\": \\\"" +acquirerId+ "01000247221\\\",\\n    \\\"id\\\": \\\"71000247229\\\",\\n    \\\"nm\\\": \\\"SPBU TEST\\\",\\n    \\\"crit\\\": \\\"UME\\\",\\n    \\\"mcc\\\": \\\"5713\\\",\\n    \\\"addr\\\": {\\n      \\\"city\\\": \\\"Jakarta Selatan\\\",\\n      \\\"ctCd\\\": \\\"ID\\\"\\n    },\\n    \\\"tid\\\": \\\"A01\\\",\\n    \\\"additionalData\\\": {\\n      \\\"nat\\\": \\\"61051295062070703A01\\\"\\n    }\\n  }\\n}\",\r\n" + 
					"        \"IN.SRC_HTTP_TYPE\": \"3\",\r\n" + 
					"        \"IN.SRC_HTTP_URL\": \"/jalin/V2.1.8.0/qr/cb/paymentCredit\",\r\n" + 
					"        \"IN.STAN\": \"255367\",\r\n" + 
					"        \"IN.TAG_62\": \"0703A01\",\r\n" + 
					"        \"IN.TID\": \"A01\",\r\n" + 
					"        \"IN.TIP_VALUE_FIXED\": \"0\",\r\n" + 
					"        \"IN.TRANSTIME\": \"" +dateFormat3+ " 10:14:32\",\r\n" + 
					"        \"IN.TRANSTIME_ISO_UTC\": \"" +transactionDate+ "T032734.320\",\r\n" + 
					"        \"IN.TRANSTIME_UTC\": \"" +dateFormat3+ " 03:27:34\",\r\n" + 
					"        \"OT.ACQ_SETTLEMENT_DATE\": \"" +dateFormat3+ "\",\r\n" + 
					"        \"OT.FEE_AMOUNT\": \"0\",\r\n" + 
					"        \"OT.FEE_TYPE\": \"C\",\r\n" + 
					"        \"OT.INVOICE_NUMBER\": \"17458027341745802734\",\r\n" + 
					"        \"OT.ISS_SETTLEMENT_DATE\": \"" +dateFormat3+ "\",\r\n" + 
					"        \"OT.MA\": \"1\",\r\n" + 
					"        \"OT.MSG_ID\": \"\",\r\n" + 
					"        \"OT.RC\": \"00\",\r\n" + 
					"        \"IR.RC\": \"00\",\r\n" + 
					"        \"OT.SWITCH_SETTLEMENT_DATE\": \"" +dateFormat3+ "\",\r\n" + 
					"        \"OT.TOTAL_LEGS\": \"1\",\r\n" + 
					"        \"IR.ACQUIRER_ID\": \"" +acquirerId+ "\",\r\n" + 
					"        \"IR.FORWARDING_ID\": \"360004\",\r\n" + 
					"        \"IR.ISSUER_ID\": \"" +issuerId+ "\"\r\n" + 
					"    },\r\n" + 
					"    \"EndpointData_PAYNET_REQ_IN\": {\r\n" + 
					"        \"MSG_DIRECTION\": \"IN\",\r\n" + 
					"        \"REQ_RESP\": \"REQ\",\r\n" + 
					"        \"NETWORK_TYPE\": \"PAYNET\",\r\n" + 
					"        \"msgId\": \"" +transactionDate+ "200233890990360004123666789395\",\r\n" + 
					"        \"proCode\": \"" +processingCode+ "\",\r\n" + 
					"        \"rrn\": \"" +rrn+ "\",\r\n" + 
					"        \"stan\": \"255367\",\r\n" + 
					"        \"apprCd\": \"28260\",\r\n" + 
					"        \"locCurr\": \"360\",\r\n" + 
					"        \"cpan\": \"" +issuerId+ "00000282605\",\r\n" + 
					"        \"loctrnDt\": \"" +dateFormat3+ "T10:14:32\",\r\n" + 
					"        \"stmDt\": \"" +dateFormat3+ "\",\r\n" + 
					"        \"mpan\": \"" +acquirerId+ "01000247221\",\r\n" + 
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
					"        \"pan\": \"" +acquirerId+ "01000247221\",\r\n" + 
					"        \"processingCode\": \"" +processingCode+ "\",\r\n" + 
					"        \"transactionAmount\": \"" +transactionAmount+ ".00\",\r\n" + 
					"        \"transmissionDateTime\": \"" +transactionDate+ "032734\",\r\n" + 
					"        \"systemTraceAuditNumber\": \"255367\",\r\n" + 
					"        \"localTransactionDateTime\": \"" +transactionDate+ "102734\",\r\n" + 
					"        \"settlementDate\": \"" +transactionDate+ "\",\r\n" + 
					"        \"captureDate\": \"" +transactionDate+ "\",\r\n" + 
					"        \"merchantType\": \"5713\",\r\n" + 
					"        \"posEntryMode\": \"011\",\r\n" + 
					"        \"feeType\": \"C\",\r\n" + 
					"        \"feeAmount\": \"0.00\",\r\n" + 
					"        \"acquirerId\": \"93600008\",\r\n" + 
					"        \"issuerId\": \"" +issuerId+ "\",\r\n" + 
					"        \"forwardingId\": \"360004\",\r\n" + 
					"        \"rrn\": \"" +rrn+ "\",\r\n" + 
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
					"        \"customerPan\": \"" +issuerId+ "00000282605\"\r\n" + 
					"    },\r\n" + 
					"    \"EndpointData_JALIN_RESP_IN\": {\r\n" + 
					"        \"MSG_DIRECTION\": \"IN\",\r\n" + 
					"        \"REQ_RESP\": \"RESP\",\r\n" + 
					"        \"NETWORK_TYPE\": \"JALIN\",\r\n" + 
					"        \"responseCode\": \"" +responseCode+ "\",\r\n" + 
					"        \"invoiceNumber\": \"17458027341745802734\"\r\n" + 
					"    },\r\n" + 
					"    \"EndpointData_PAYNET_RESP_OUT\": {\r\n" + 
					"        \"MSG_DIRECTION\": \"OUT\",\r\n" + 
					"        \"REQ_RESP\": \"RESP\",\r\n" + 
					"        \"NETWORK_TYPE\": \"PAYNET\",\r\n" + 
					"        \"msgId\": \"" +transactionDate+ "200233890990360004123666789395\",\r\n" + 
					"        \"rspCd\": \"ACTC\",\r\n" + 
					"        \"invNum\": \"1745802734\",\r\n" + 
					"        \"rsnCd\": \"0000\",\r\n" + 
					"        \"stmDt\": \"" +dateFormat3+ "\"\r\n" + 
					"    },\r\n" + 
					"    \"MetaData\": {\r\n" + 
					"        \"UUID\": \"f03e66f0-dd40-11eb-8c3a-000c29a72de6\",\r\n" + 
					"        \"TRANS_ID\": \"" +transId+ "\",\r\n" + 
					"        \"ACQ_PARTICIPANT\": \"1801\",\r\n" + 
					"        \"ISS_PARTICIPANT\": \"1792\",\r\n" + 
					"        \"TRANS_RESULT\": \"0\",\r\n" + 
					"        \"REF_UUID\": \"\",\r\n" + 
					"        \"TRANS_STATUS\": \"1\"\r\n" + 
					"    }\r\n" + 
					"}";
			
			
			byte[] out = null;
			if(transactionType.equalsIgnoreCase("Inquiry")) {
				out = inInquiry.getBytes(StandardCharsets.UTF_8);	
			} else if(transactionType.equalsIgnoreCase("Payment Credit")) {
				out = inPayment.getBytes(StandardCharsets.UTF_8);	
			} else if(transactionType.equalsIgnoreCase("Check Status")) {
				out = inCheckStatus.getBytes(StandardCharsets.UTF_8);	
			}
			

			OutputStream stream = http.getOutputStream();
			stream.write(out);

			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			String responseHit = http.getResponseCode() + " " + http.getResponseMessage();
			scenario.attach(responseHit, "text/plain", "Response Code");
			http.disconnect();

		} catch (Exception e) {
			scenario.attach(e.getMessage(), "text/plain", "Exception Error");
			fail("Exception Error");
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getDataAsList(String stringData) {
		
		ArrayList<String> list = new ArrayList<String>();
		String[] arrOfStr = stringData.split(";");
		
		for(String str:arrOfStr) {
			list.add(str);
			System.out.println(str);
		}
		
		System.out.println();
		
		
		return list;
		
	}
	
	public static void main(String[] args) {

		getDataAsList("Inbound;Inbound;Inbound");
	}
}
