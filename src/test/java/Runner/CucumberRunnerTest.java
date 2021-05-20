package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "Feature",
        glue = {"Flow","Hooks"},
        plugin = {"pretty","html:testoutput/testReport.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
        )
//com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:
//com.cucumber.listener.ExtentCucumberFormatter:testoutput/testReport.html
public class CucumberRunnerTest {


}