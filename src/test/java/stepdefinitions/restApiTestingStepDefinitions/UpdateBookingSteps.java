package stepdefinitions.restApiTestingStepDefinitions;

import actions.UpdateBookingActions;
import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateBookingSteps {

    @Steps
    UpdateBookingActions updateBookingActions;

    @When("user makes a request to update the booking details")
    public void userMakesARequestToUpdateTheBookingDetails(DataTable dataTable) {
        Integer bookingId = Serenity.sessionVariableCalled("Booking ID");
        assertThat(bookingId).isNotNull();

        Map<String, String> bookingData = dataTable.asMaps().get(0);
        JsonObject bookingBody = new JsonObject();
        bookingBody.addProperty("firstname", bookingData.get("firstname"));
        bookingBody.addProperty("lastname", bookingData.get("lastname"));
        bookingBody.addProperty("totalprice", Integer.valueOf(bookingData.get("totalprice")));
        bookingBody.addProperty("depositpaid", Boolean.valueOf(bookingData.get("depositpaid")));

        JsonObject bookingDates = new JsonObject();
        bookingDates.addProperty("checkin", (bookingData.get("checkin"))); //string
        bookingDates.addProperty("checkout", (bookingData.get("checkout")));

        bookingBody.add("bookingdates", bookingDates);  //jsonElement
        bookingBody.addProperty("additionalneeds", bookingData.get("additionalneeds"));

        updateBookingActions.updateBooking(bookingBody.toString(), bookingId);
    }

    @And("user should get the response code {int} of update API")
    public void userShouldGetTheResponseCode(Integer statusCode) {
        assertThat(updateBookingActions.getResponse().statusCode()).isEqualTo(statusCode);
    }
}
