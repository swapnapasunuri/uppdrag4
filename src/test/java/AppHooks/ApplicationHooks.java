package AppHooks;
import testUtil.ElementUtils;

import io.cucumber.java.Scenario;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import testFactory.DriverFactory;
import testUtil.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ApplicationHooks {

    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    Properties prop;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_Properties();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browserName);
    }

    @After(order = 0)
    public void quitBrowser(Scenario scenario) {
        if(scenario.isFailed()){
//            scenario.attach(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenShot");
            ElementUtils.getScreenshot(driver,"loginFail");
        }
        driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
//        if (scenario.isFailed()) {
//            // take screenshot:
//            String screenshotName = scenario.getName().replaceAll(" ", "_");
//            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(sourcePath, "image/png", screenshotName);
//
//        }
    }

}
