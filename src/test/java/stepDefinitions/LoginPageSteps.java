package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import org.junit.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import testFactory.DriverFactory;

public class LoginPageSteps {

    private  String sURL;
    //private static String sActual ;
    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private ProductsPage productsPage;
    @Given("user is on the application Login Page")
    public void user_is_on_the_application_login_page() {
                DriverFactory.getDriver()
                .get("https://www.saucedemo.com/");
    }



    @When("user enters the {string} in the username-field")
    public void user_enters_the_in_the_username_field(String string) {
        try {
            loginPage.enterUserName(string);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @When("user enters the {string} in the password-field")
    public void user_enters_the_in_the_password_field(String string) {
        try {
            loginPage.enterPassword(string);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @When("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        try {

            productsPage = loginPage.clickOnLoginButton();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    @Then("user navigates to the homepage url {string}")
    public void user_navigates_to_the_homepage_url(String string) {
        sURL =loginPage.getCurrentPageURL();
        Assert.assertEquals(sURL, string);
        System.out.println("Page title is: " + sURL);
    }



    @When("user clicks on the burger-button the side-menu opens")
    public void user_clicks_on_the_burger_button_the_side_menu_opens() {
        productsPage.openSideMenu();
    }
    @When("user hovers and clicks on the logout menuItem")
    public void user_hovers_and_clicks_on_the_logout_menu_item() {
        productsPage.logout();
    }
    @Then("user returns to login page")
    public void user_returns_to_login_page() {
        loginPage.getCurrentPageURL();
    }
}


/*


//    @Then("user sees the appropriate {string}")
//    public void user_sees_the_appropriate(String string) {
//        Assert.assertEquals(string, sActual);
//        System.out.println(string + " = " + sActual);
//    }


//    @Then("homepage url should be {string}")
//    public void homepage_url_should_be(String url) {
//        Assert.assertEquals(sURL, url);
//        System.out.println("Page title is: " + sURL);
//    }


    //////// login fail with appropriate message  /////

*/


