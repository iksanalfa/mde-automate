package runners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.BeforeParam;

import com.vimalselvam.cucumber.listener.ExtentProperties;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;
import utils.ExcelReader;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/ReportRecon/TestRekonWithExcel.feature", glue = "scenarios", monochrome = true
, plugin = {"json:target/cucumber.json"})
public class TestRunner_ReportReconWithExcel {
				
	@AfterClass
	public static void tearDown() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		//Report Configuration
		File reportOutputDirectory = new File("report/report_"+timeStamp);
		List jsonFiles = new ArrayList();
		jsonFiles.add("target/cucumber.json");
		Configuration config = new Configuration(reportOutputDirectory, "eCP Project");
		config.setBuildNumber("011");
		config.addClassifications("Environment", "DEV");
		config.addClassifications("Browser", "Chrome");
		config.addClassifications("Platform", System.getProperty("os.name").toUpperCase());
		config.setSortingMethod(SortingMethod.NATURAL);
		config.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
		config.setTrendsStatsFile(new File("target/test-classes/demo-trends.json"));
		net.masterthought.cucumber.ReportBuilder reportBuilder = new net.masterthought.cucumber.ReportBuilder(jsonFiles, config);
		reportBuilder.generateReports();
	}
}
