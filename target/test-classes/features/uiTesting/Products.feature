@debug
Feature: Main Products page of sauce labs

  Scenario: Add a product in saucelabs page
    Given user can navigate to saucelabs url
    And user navigates to saucelabe url
    And user types username: "standard_user"
    And user types password: "secret_sauce"
    And user clicks LOGIN button
    And user is directed to main product saucelabs page
    When user adds to cart the 'Sauce Labs Backpack'
    Then the product is added to shopping cart badge

  Scenario: Open product details in saucelabs page
    Given user can navigate to saucelabs url
    And user navigates to saucelabe url
    And user types username: "standard_user"
    And user types password: "secret_sauce"
    And user clicks LOGIN button
    And user is directed to main product saucelabs page
    When user clicks the 'Sauce Labs Bike Light' product
    Then 'Sauce Labs Bike Light' product details are opened

  Scenario: Navigate back to main products page in saucelabs
    Given user can navigate to saucelabs url
    And user navigates to saucelabe url
    And user types username: "standard_user"
    And user types password: "secret_sauce"
    And user clicks LOGIN button
    And user is directed to main product saucelabs page
    And user clicks the 'Sauce Labs Bike Light' product
    And 'Sauce Labs Bike Light' product details are opened
    When user clicks 'Back to products' button
    Then user is directed to main product saucelabs page
