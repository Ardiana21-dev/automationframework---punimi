@debug
Feature: To create a new booking in restful-booker

  Scenario Outline: To create new booking using cucumber Data Table
    Given user has access to endpoint "/booking" of Booking API
    When user creates a booking
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then user should get the response code 200 for creating a booking
    And user verifies the booking of user: "<firstname>" "<lastname>" is created

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | John      | Smith    | 1440       | true        | 2017-06-06 | 2017-06-16 | Breakfast       |
      | Oliver    | Jake     | 1020       | true        | 2018-05-15 | 2018-05-23 | Dinner          |
      | Olivia    | O'Kelly  | 2000       | true        | 2021-09-10 | 2021-10-10 | Dinner          |
      | Emily     | Murphy   | 1210       | true        | 2021-05-30 | 2021-06-09 | Breakfast       |


  Scenario Outline: To create new booking using JSON data
    Given user has access to endpoint "/booking" of Booking API
    When user creates a booking using data from JSON file "<JsonFile>"
    Then user should get the response code 200 for creating a booking
    And user verifies the booking of user: "<firstname>" "<lastname>" is created

    Examples:
      | JsonFile           | firstname | lastname |
      | createBooking.json | Bill      | Jack  |
