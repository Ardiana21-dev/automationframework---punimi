Feature: To update a booking in restful-booker

  @debug
  Scenario: To update a booking using cucumber Data Table
    Given user has access to endpoint "/booking" of Booking API
    And user creates a booking
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | dbdb      | dbdbdb   | 3535       | true       | 2017-06-06 | 2017-06-06 | Lunch           |
    And user should get the response code 200 for creating a booking
    And user verifies the booking of user is created
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | dbdb      | dbdbdb   | 3535       | true       | 2017-06-06 | 2017-06-06 | Lunch           |
    And user gets the booking ID from created booking
    When user makes a request to update the booking details
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Jack      | Smith    | 24242      | true        | 2017-06-06 | 2017-06-06 | Breakfast       |
    Then user should get the response code 200 of update API