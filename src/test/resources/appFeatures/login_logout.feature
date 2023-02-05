Feature: Login  feature

Background: I am on saucedemo page

  Scenario Outline: user tries to login with different username/passwords
    Given user is on the application Login Page
    When user enters the "<username>" in the username-field
    And user enters the "<password>" in the password-field
    And user clicks on the login button
    And user navigates to the homepage url "https://www.saucedemo.com/inventory.html"
    And user clicks on the burger-button the side-menu opens
    And user hovers and clicks on the logout menuItem
    Then user returns to login page
  #  And homepage url should be "https://www.saucedemo.com/inventory.html"

    Examples:
      |username               |password     |
      |standard_user          |secret_sauce |
      |problem_user           |secret_sauce |
      |performance_glitch_user|secret_sauce |
      |locked_out_user        |secret_sauce |



