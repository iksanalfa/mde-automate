package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.Scenario;

public class SimpleFileReader {
	
	FileReader fileReader;
	
	public SimpleFileReader() {
		
	}
	
	public void readLine(String filePath, Scenario scenario) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		try {
			String line;
			while((line = br.readLine()) != null) {
				System.out.println(line);
				scenario.attach(line, "text/plain", line);
			}
		} catch (Exception e) {
			br.close();
		}
	}
	
	public void readEveryColumn(String filePath, Scenario scenario) throws IOException {
		List<String> listValue = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		try {
			String line;
			String value;
			String[] columnsValue;
			int lineNumber = 1;
			while((line = br.readLine()) != null) {
				columnsValue = line.split("\\|");
				for(int i=0; i < columnsValue.length; i++) {
					value = columnsValue[i];
					String log = "Data<"+String.valueOf(lineNumber)+" , "+String.valueOf(i+1)+"> : "+value;
					System.out.println(log);
					scenario.attach(log, "text/plain", log);
				}
				lineNumber++;
			}
		} catch (Exception e) {
			br.close();
		}
	}

}
