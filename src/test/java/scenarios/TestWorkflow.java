package scenarios;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.model.Report.ReportBuilder;
import com.vimalselvam.cucumber.listener.ExtentProperties;

import blocks.LoginPage;
import blocks.MenuPage;
import utils.DriverSetup;
import utils.HighlightAndScreenShot;
import utils.SeleniumUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;

public class TestWorkflow {

	ChromeDriver driver;
	WebDriverWait wait;
	Actions action;
	JavascriptExecutor jse;
	File screenshotFile;
	
	Scenario scenario = ScenarioSetup.scenario;
	
	DriverSetup driverSetup = new DriverSetup(driver);
	LoginPage login = new LoginPage(DriverSetup.driver);
	MenuPage menu = new MenuPage(login.driver);
	HighlightAndScreenShot hns = new HighlightAndScreenShot(DriverSetup.driver);
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	String userDir =System.getProperty("user.dir"); 

	
	@Given("^User login using (.*) and (.*)$")
	public void user_login(String username, String password) throws InterruptedException, IOException {
		login.userLogin(username, password, scenario);
	}

	@When("^User Click Main Menu (.*) and Sub Menu (.*)$")
	public void user_click_main_menu_workflow_manager_and_sub_menu_workflows(String mainmenu, String submenu) throws InterruptedException, IOException {
//		menu = new MenuPage(login.driver);
		menu.clickSubMenu(mainmenu, submenu, scenario);
	}

	@And("^User Choose Workflow (.*) want to be processed$")
	public void user_choose_workflow_jalin_itmx_eod_want_to_be_processed(String workflowName) throws InterruptedException, IOException {
		menu.doubleClickData(workflowName, scenario);
	}

	@And("^User Click (.*) button$")
	public void user_click_run_history_button(String btn) throws InterruptedException, IOException {
		menu.clickButton(btn, scenario);
	}

	@Then("User get run history information for workflow JALIN_ITMX_EOD")
	public void user_get_run_history_information_for_workflow_jalin_itmx_eod() throws InterruptedException, IOException {
		String headerName = "Run ID";
		System.out.println(menu.getFirstRow(headerName, scenario));
	}
}
