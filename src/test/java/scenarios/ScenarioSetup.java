package scenarios;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ScenarioSetup {
	
	public static Scenario scenario;
		
	@Before
	public void beforeScenario(Scenario scenario) {
		ScenarioSetup.scenario = scenario;
		System.out.println("=== TEST BEGIN ===");
	}
	
	@After
	public void afterScenario() {
		System.out.println("=== TEST END ===");
	}

}
