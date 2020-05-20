package Runner_Class;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="Feature File",
					tags={"@ResultSheet"},
					glue="Step_Defination_Class",
					plugin={"pretty","html:target/cucumber-html-report","json:target/cucumber-report.json"})
public class Run {

}
