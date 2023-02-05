package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.ProductsPage;
import testFactory.DriverFactory;

import java.util.List;
import java.util.Map;

public class ProductsPageSteps {

    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    private ProductsPage productsPage;

    @Given("user has already logged in to application with username as {string} and password as {string}")
    public void user_has_logged_into_application(String user, String pwd) {
    try {
        loginPage.enterUserName("user");
        loginPage.enterPassword("pwd");
        DriverFactory.getDriver().get("https://www.saucedemo.com/");
        productsPage = loginPage.doLogin(user, pwd);
    }catch(Exception ex){
        ex.getStackTrace();}
    }


   /* @When("user clicks on the burger-button the side-menu opens")
    public  void user_clicks_on_the_burger_button_the_side_menu_opens1() {
        productsPage.openSideMenu();
    }
    @When("user hovers and clicks on the logout menuItem")
    public void user_hovers_and_clicks_on_the_logout_menu_item1() {
        productsPage.logout();
    }
    @Then("user returns to login page")
    public void user_returns_to_login_page1() {
        loginPage.getCurrentPageURL();
    }
*/


}

