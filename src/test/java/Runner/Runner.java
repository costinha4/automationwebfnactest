package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/Features",
        glue={"StepDefinitions"},
        monochrome = true,
        plugin={ "pretty", "html:src/test/java/Report/report.html"})
public class Runner {

}
