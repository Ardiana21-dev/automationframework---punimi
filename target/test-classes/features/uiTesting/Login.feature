@debug
Feature: Login saucelabs page

  Scenario: To verify navigation to saucelabs page
    Given user can navigate to saucelabs url
    When user navigates to saucelabe url
    Then user can see the login saucelabs page

  Scenario: Log in to saucelabs page
    Given user can navigate to saucelabs url
    And user navigates to saucelabe url
    When user types username: "standard_user"
    And user types password: "secret_sauce"
    And user clicks LOGIN button
    Then user is directed to main product saucelabs page