package scenarios;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import utils.SimpleFileReader;
import utils.SimpleFileWriter;

public class TestReadReport {
	
	SimpleFileReader sfr = new SimpleFileReader();
	SimpleFileWriter sfw = new SimpleFileWriter();
	String dateFormat = new SimpleDateFormat("yyMMdd").format(new Date());
	String userDir =System.getProperty("user.dir") + "\\"; 
	Scenario scenario = ScenarioSetup.scenario;
	
	
	@Given("^Report Result Exist (.*)$")
	public void report_result_exist(String fileName) {
//		fileName = fileName + "_" + dateFormat;
		String folderPath = userDir;
		String filePath = folderPath + fileName;
		
		if (new File(filePath).exists()) {
			scenario.attach("File Exist", "text/plain", "File Exist");
		}
	}

	@When("^Read Report File$")
	public void read_report_file() {


	}
	@Then("^Get Every Column Value (.*)$")
	public void get_every_column_value(String fileName) throws IOException {
//		fileName = fileName + "_" + dateFormat;
		String folderPath = userDir;
		String filePath = folderPath + fileName;
		sfr.readEveryColumn(filePath, scenario);
	}
	
}
