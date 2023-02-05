package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testFactory.DriverFactory;

public class ProductsPage {

    private By burgerButton = By.xpath("//button[@id='react-burger-menu-btn']");
    private By logoutLink = By.xpath("//*[@id='logout_sidebar_link']");    //By.id("logout_sidebar_link");

    public LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private WebDriver driver;
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }


    public void openSideMenu(){
        driver.findElement(burgerButton).click();
    }

    public void logout(){
        driver.findElement(logoutLink).click();;
    }
}
