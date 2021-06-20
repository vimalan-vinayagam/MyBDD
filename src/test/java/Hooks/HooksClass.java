package Hooks;

import Tools.BaseClass;
import Utils.ConfigDataProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.util.Properties;
import java.util.logging.Logger;

public class HooksClass {

    Logger log = Logger.getLogger(HooksClass.class.getName());
    Properties prop;
    private WebDriver driver;


    @Before(value = "@Skip", order = 0)

    public void SkipScenario(Scenario scenario){
        log.info("*************************Skiped the scenario*****************************");
        System.out.println("Scenario skiped are "+scenario.getName());
        Assume.assumeTrue(false);

    }

    @Before(order = 1)
    public void getProperty() {
        ConfigDataProvider CDP = new ConfigDataProvider();
        prop = CDP.DataProvider();
    }


    @Before(order = 2)
    public void LaunchBrowser() {
        log.info("*************************Launching browser*****************************");
        String browserName = prop.getProperty("browser");
        BaseClass BC = new BaseClass();
        driver = BC.init_driver(browserName);
        driver.get(prop.getProperty("URL"));
    }

    @After(order = 0)
    public void teardown() {
        log.info("*************************Browser got closed*****************************");
        driver.quit();
    }

    @After(order = 1)
    public void ScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
            log.info("*************************Screen shot for failed test cases*****************************");
        }
    }

}
