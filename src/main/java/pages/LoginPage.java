package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;


    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginInButton = By.id("login-button");

    private String homepageTitle = "Swag Labs";

    private By errorMsg = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");        // h3[data-test='error']");

    // 2. Constructor of the page class:
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. page actions: features(behavior) of the page the form of methods:
    public String getLoginPageTitle() {
        //driver.navigate()
        return driver.getTitle();
    }

    public void enterUserName(String user) throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pwd) throws InterruptedException {
      //  Thread.sleep(5000);
        driver.findElement(password).sendKeys(pwd);
    }

//    public void clickOnLoginButton() throws InterruptedException { //String loginSuccessful
//        driver.findElement(loginInButton).click();
//        Thread.sleep(100);
//    }

    public String getCurrentPageURL() {
        System.out.println("login with: " +driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public ProductsPage clickOnLoginButton() throws InterruptedException { //String loginSuccessful
        driver.findElement(loginInButton).click();
        Thread.sleep(100);
        return new ProductsPage(driver);
    }

    public ProductsPage doLogin(String usr, String pwd) {
        System.out.println("login with: " + usr + " and " + pwd);
        driver.findElement(username).sendKeys(usr);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(loginInButton).click();
        return new ProductsPage(driver);
    }
}