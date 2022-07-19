@debug
Feature: View Booking details

  Scenario: To confirm whether the API is up and running
    Given user has access to endpoint "/ping" of Booking API
    When user makes a request to check the health of booking service
    Then user should get the response code 201

  Scenario: View all the booking IDs
    Given user has access to endpoint "/booking" of Booking API
    When user makes a request to view booking IDs
    Then user should get the response code 200
    And user should see all the booking IDs

  @test
  Scenario: To view booking details
    Given user has access to endpoint "/booking" of Booking API
    When user makes a request to view booking IDs
    And user makes a request to view details of a booking ID "5000"
    Then user should get the response code 200
    And user should see the name:"Michael" and surname:"12345_12345" in response

  Scenario Outline: To view all the booking IDs by booking dates
    Given user has access to endpoint "/booking" of Booking API
    When user makes a request to view booking IDs from "<checkin>" to "<checkout>"
    Then user should get the response code 200
    And user should see all the booking IDs

    Examples:
      | checkin    | checkout   |
      | 2019-05-06 | 2019-05-16 |

  Scenario: To view all the booking IDs by booking names
    Given user has access to endpoint "/booking" of Booking API
    When user makes a request to view booking IDs
    Then user should see all the booking IDs
    And user makes a request to view details of a booking ID
    And user should get the response code 200
    And user makes a request to view all the booking IDs of the user



    #for  | Gerald      |  Rivera    |