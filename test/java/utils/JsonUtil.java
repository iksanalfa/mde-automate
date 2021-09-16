package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtil {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("inbound-payment-credit-paynet.json"));
          
        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
        JSONObject jo_Internal = (JSONObject) jo.get("Internal");
        jo_Internal.put("IN.RRN", "123980180374");

	}

}
