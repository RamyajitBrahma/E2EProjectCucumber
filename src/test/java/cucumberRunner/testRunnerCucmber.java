package cucumberRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;


//@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",
		glue="cucumberStepDefinitions"
		)

public class testRunnerCucmber extends AbstractTestNGCucumberTests{

}
