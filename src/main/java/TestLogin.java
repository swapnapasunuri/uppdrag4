//https://github.com/ObjectiveTester/AllThingsTesting.com-examples/blob/master/SeleniumParallel/src/test/java/com/example/objects/CartPage.java
//https://stackoverflow.com/questions/31555652/how-to-count-the-products-on-a-page-using-selenium
//https://github.com/ObjectiveTester/AllThingsTesting.com-examples/blob/master/SeleniumParallel/src/test/java/com/example/objects/InventoryPage.java
//https://allthingstesting.com/ui-testing-with-selenium-record-and-refactor/
//edge://favorites/?id=243


package test_page.shop;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import static org.junit.Assert.*;

public class TestLogin {

    public WebDriver driver;
    public String URL="https://www.saucedemo.com/";

    //--> Junit //@BeforeEach
    //TestNG -->
    @BeforeTest
    public void setup(){
        File file = new File("src/main/resources/chromedriver.exe");
        System.setProperty("web-driver.chrome.driver", file.getAbsoluteFile().toString());

    }

/////////////////////////////////////////////////////////////
//@Test
//@DisplayName("Test if login succeeded, if so we should see correct page title")
// ///////////////////////////////////////////////////////////

    @Test
    public void login() throws InterruptedException {

        var options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");

        WebDriver driver=new ChromeDriver(options);

        driver.get(URL);
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ; //Deprecated

        driver.findElement(By.id("login-button")).click();


        assertEquals(driver.getTitle(), "Swag Labs");
        System.out.println(driver.getTitle());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ; //Deprecated

/////////////////////////////////////////////////////////////
//@Test
// @DisplayName("Add some items. Try to duplicate one of them. It should fail")
///////////////////////////////////////////////////////////

        float p1, p2, total_sum = 0;

        List<WebElement> price_list = driver.findElements(By.className("inventory_item_price"));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ; //Deprecated

        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ; //Deprecated

        StringBuilder sb1 = new StringBuilder(price_list.get(0).getText());
        sb1.delete(0,1);
        StringBuilder sb2 = new StringBuilder(price_list.get(1).getText());
        sb2.delete(0,1);
        //System.out.println(sb1 + " " + sb2);
        p1 = Float.parseFloat(sb1.toString());
        p2 = Float.parseFloat(sb2.toString());
        total_sum = p1+p2;
        System.out.println("Total amount purchased: " +  total_sum);


        boolean isPresent=true;
        try {
            driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        }catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Add item disabled");
            isPresent=false;
        }
        assertFalse(isPresent);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ; //Deprecated


////////////////////////////////////////////////////////
// @Test
//@DisplayName("Try to remove an item and count the number of items")
////////////////////////////////////////////////////////

        driver.findElement(By.id("shopping_cart_container")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ; //Deprecated

        System.out.println("Attempt to remove Sauce Labs Bike Light..");
        //@Test//-- >
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ; //Deprecated
        total_sum -=p2; //reduce the bike light price
        System.out.println("New sum:" + total_sum);

        //Remove same item again. Did remove worked?
        try {
            driver.findElement(By.id("remove-sauce-labs-bike-light"));
        }catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Already removed");
        }
        List<WebElement> items_available = driver.findElements(By.className("inventory_item_name"));
        System.out.println("Number of items available now: " + items_available.size());


        WebElement we = driver.findElement(By.className("inventory_item_name"));
        String s = we.getText();

        System.out.println("Number of items available now: " + items_available.size() + s );
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ; //Deprecated

////////////////////////////////////////////////////////
// @Test
////////////////////////////////////////////////////////
        /*
        //improvement: show available to remove
        for (WebElement element : items_available) {
            String myText = element.findElement( By.className("inventory_item_name")).getText();
            System.out.println(myText);
        }
        */
        //assertEquals(list.size(), 1);

        //System.out.println("Number of items in the basket" + list.size())

////////////////////////////////////////////
// @Test
//@DisplayName("Check out. Compare total sum in the basket and our calculated sum")
////////////////////////////////////////////////////////

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Swag");
        driver.findElement(By.id("last-name")).sendKeys("Labs");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ; //Deprecated
        //WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10)); //?


        String store_sum = driver.findElement(By.className("summary_subtotal_label")).getText();
        StringBuilder store_sum_b = new StringBuilder(store_sum);
        store_sum_b.delete(0,13);
        System.out.println("Store sum " +store_sum_b);
        String sf = store_sum_b.toString();
        float f = Float.parseFloat(sf);


        System.out.println(total_sum + " ==? " + f);
        Assertions.assertEquals(total_sum, f);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ; //Deprecated

////////////////////////////////////////////////////////
// @Test
// @DisplayName("Finish the process of ordering")
////////////////////////////////////////////////////////

        driver.findElement(By.id("finish")).click();

        WebElement imageWebElement = (WebElement) driver.findElement(By.className("pony_express"));

        boolean ponyImageLoaded;
        ponyImageLoaded = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageWebElement);


        Assert.assertTrue(ponyImageLoaded);
        System.out.println("Image is loaded ---> " + ponyImageLoaded);


    }


}
