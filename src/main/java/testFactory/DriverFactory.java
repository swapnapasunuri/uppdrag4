package testFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    public WebDriver driver;

    public static ThreadLocal<WebDriver> thlDriver = new ThreadLocal<>();

    /**
     * This method is used to initialize the Threadlocal driver on the basis of given
     * browser
     *
     */
    public WebDriver init_driver(String browser) {

        System.out.println("browser  is: " + browser);

        if (browser.equals("chrome")) {
            thlDriver.set(new ChromeDriver());
        } else if (browser.equals("firefox")) {
            thlDriver.set(new FirefoxDriver());
        } else if (browser.equals("safari")) {
            thlDriver.set(new SafariDriver());
        } else {
            System.out.println("Please enter the correct browser name: " + browser);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();

    }

    /**
     * this is used to get the driver with ThreadLocal
     */
    public static synchronized WebDriver getDriver() {
        return thlDriver.get();
    }
}
