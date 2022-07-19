package stepdefinitions.restApiTestingStepDefinitions;

import actions.DeleteBookingActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteBookingSteps {

    @Steps
    DeleteBookingActions deleteBookingActions;

    @When("user makes a request to delete the booking")
    public void userMakesARequestToDeleteTheBooking() {
        Integer bookingId = Serenity.sessionVariableCalled("Booking ID");
        deleteBookingActions.deleteBooking(bookingId);
    }

    @Then("user should get the response code {int} of delete API")
    public void userShouldGetTheResponseCode(int expectedResponseStatusCode) {
        assertThat(deleteBookingActions.getResponse().statusCode()).isEqualTo(expectedResponseStatusCode);
    }
}
