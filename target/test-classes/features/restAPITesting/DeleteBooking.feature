Feature: To delete a booking in restful-booker

  @debug
  Scenario Outline: To perform a delete operation on restful-booker
    Given user has access to endpoint "/booking" of Booking API
    When user creates a booking
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    And user should get the response code 200 for creating a booking
    And user verifies the booking of user is created
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    And user gets the booking ID from created booking
    When user makes a request to delete the booking
    Then user should get the response code 201 of delete API

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | John12341 | Doen341  | 1250       | true        | 2012-05-05 | 2012-05-15 | Breakfast       |
