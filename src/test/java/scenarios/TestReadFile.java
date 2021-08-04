package scenarios;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.runtime.RuntimeConstants;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import utils.SimpleFileReader;
import utils.SimpleFileWriter;

public class TestReadFile {
	
	SimpleFileReader sfr = new SimpleFileReader();
	SimpleFileWriter sfw = new SimpleFileWriter();
	Scenario scenario;
	String dateFormat = new SimpleDateFormat("yyMMdd").format(new Date());
	String userDir =System.getProperty("user.dir") + "\\"; 
	
//	@Before
//	public void beforeScenario(Scenario scenario) {
//		this.scenario = scenario;
//	}
	
	@Given("^Write a textfile (.*) with line1 is (.*) and line2 is (.*)$")
	public void write_a_textfile_with_filename_is_filename_excecute_time(String fileName, String line1, String line2) {
		fileName = fileName + "_" + dateFormat;
		String folderPath = userDir;
		String filePath = folderPath + fileName + ".txt";
		System.out.println(filePath);
		List<String> listString = new ArrayList<String>();
		listString.add(line1);
		listString.add(line2);
		
		sfw.writeTextLine(filePath, listString);
	}

	@When("^Result File (.*) Created$")
	public void result_file_created(String fileName) {
		fileName = fileName + "_" + dateFormat;
		String folderPath = userDir;
		String filePath = folderPath + fileName + ".txt";
		
		if (new File(filePath).exists()) {
			scenario.attach("File Exist", "text/plain", "File Exist");
		}
	}

	@Then("^Read and Validate Result File (.*)$")
	public void read_and_validate_result_file(String fileName) throws IOException {
		fileName = fileName + "_" + dateFormat;
		String folderPath = userDir;
		String filePath = folderPath + fileName + ".txt";
		sfr.readLine(filePath, scenario);
	}

}
