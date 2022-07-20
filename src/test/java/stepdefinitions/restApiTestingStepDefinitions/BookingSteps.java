package stepdefinitions.restApiTestingStepDefinitions;

import actions.BookingActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import models.BookingDetailsDTO;
import models.BookingId;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import utils.Deserializer;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BookingSteps {

    private Response response;

    @Steps
    BookingActions bookingActions;

    @When("user makes a request to check the health of booking service")
    public void userMakesARequestToCheckTheHealthOfBookingService() {
        bookingActions.userMakesARequestToCheckTheHealthOfBookingService();
    }

    @Then("user should get the response code {int}")
    public void userShouldGetTheResponseCode(Integer statusCode) {
        assertThat(bookingActions.getResponse().statusCode()).isEqualTo(statusCode);
    }

    @When("user makes a request to view booking IDs")
    public void userMakesARequestToViewBookingIDs() {
        bookingActions.userMakesARequestToViewBookingIDs();
    }

    @Then("user should see all the booking IDs")
    public void userShouldSeeAllTheBookingIDS() {
        BookingId[] bookingIDs = Deserializer.deserializeResponse(bookingActions.getResponse(), BookingId[].class);
        assertThat(bookingIDs).isNotNull();
        assertThat(bookingIDs).isNotEmpty();
        log.info("Booking IDs were found!", bookingIDs);
    }

    @Then("user makes a request to view details of a booking ID {string}")
    public void userMakesARequestToViewDetailsOfBookingID(String bookingID) {
        bookingActions.userMakesARequestToViewDetailsOfBookingID(bookingID);
        BookingDetailsDTO bookingDetails = Deserializer.deserializeResponse(bookingActions.getResponse(), BookingDetailsDTO.class);

        Serenity.setSessionVariable("First name").to(bookingDetails.getFirstname());
        Serenity.setSessionVariable("Last name").to(bookingDetails.getLastname());
    }

    @Then("user makes a request to view details of a booking ID")
    public void userMakesARequestToViewDetailsOfBookingID() {
        bookingActions.userMakesARequestToViewDetailsOfBookingID();
        BookingDetailsDTO bookingDetails = Deserializer.deserializeResponse(bookingActions.getResponse(), BookingDetailsDTO.class);

        Serenity.setSessionVariable("First name").to(bookingDetails.getFirstname());
        Serenity.setSessionVariable("Last name").to(bookingDetails.getLastname());
    }

    @And("user should see the name:{string} and surname:{string} in response")
    public void userShouldSeeTheNameAndSurnameInResponse(String name, String lastname) {
        String actualFirstName = Serenity.sessionVariableCalled("First name");
        String actualLastName = Serenity.sessionVariableCalled("Last name");
        assertThat(actualFirstName).isEqualTo(name);
        assertThat(actualLastName).isEqualTo(lastname);
    }

    @Given("user makes a request to view booking IDs from {string} to {string}")
    public void userMakesARequestToViewBookingFromTo(String checkin, String checkout) {
        bookingActions.userMakesARequestToViewBookingFromTo(checkin, checkout);
    }

    @Then("user makes a request to view all the booking IDs of the user")
    public void userMakesARequestToViewBookingIDByUserName() {
        String userFirstName = Serenity.sessionVariableCalled("First name");
        String userLastName = Serenity.sessionVariableCalled("Last name");
        bookingActions.userMakesARequestToViewBookingIDByUserName("Priya", "Sri");
        BookingId[] bookingIDs = Deserializer.deserializeResponse(bookingActions.getResponse(), BookingId[].class);
        verifyBookingIdsAreOfTheUserWithNameAndLastname(bookingIDs, userFirstName, userLastName);
    }

    private void verifyBookingIdsAreOfTheUserWithNameAndLastname(BookingId[] bookingIds, String firstName, String lastName) {
        for (BookingId bookingId : bookingIds) {
            response = bookingActions.getBookingDetailsByBookingId(bookingId.getBookingId());
            assertThat(response.body().jsonPath().get("firstname").toString()).isEqualTo(firstName);
            assertThat(response.body().jsonPath().get("lastname").toString()).isEqualTo(lastName);
        }
    }
}
