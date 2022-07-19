package stepdefinitions.restApiTestingStepDefinitions;

import actions.CreateBookingsActions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import models.Booking;
import models.BookingDetailsDTO;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import utils.Deserializer;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CreateBookingSteps {

    @Steps
    CreateBookingsActions createBookingsActions;

    @And("user creates a booking")
    public void userCreatesABooking(DataTable dataTable) {

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

        createBookingsActions.createBooking(bookingBody.toString());

        Booking createdBooking = Deserializer.deserializeResponse(createBookingsActions.getResponse(), Booking.class);

        Serenity.setSessionVariable("Created Booking").to(createdBooking);
    }

    @And("user should get the response code {int} for creating a booking")
    public void userShouldGetTheResponseCode(Integer statusCode) {
        assertThat(createBookingsActions.getResponse().statusCode()).isEqualTo(statusCode);
    }

    @And("user verifies the booking of user: {string} {string} is created")
    public void userVerifiesTheBookingOfUser(String firstname, String lastname) {
        Booking createdUserFromResponse = Serenity.sessionVariableCalled("Created Booking");
        assertThat(createdUserFromResponse.getBookingDetailsDTO().getFirstname()).isEqualTo(firstname);
        assertThat(createdUserFromResponse.getBookingDetailsDTO().getLastname()).isEqualTo(lastname);
    }

    @And("user verifies the booking of user is created")
    public void userVerifiesTheBookingOfUserIsCreated(DataTable bookingData) {
        Map<String, String> expectedBookingData = bookingData.asMaps().get(0);

        Booking createdUserFromResponse = Serenity.sessionVariableCalled("Created Booking");
        assertThat(createdUserFromResponse.getBookingDetailsDTO().getFirstname()).isEqualTo(expectedBookingData.get("firstname"));
        assertThat(createdUserFromResponse.getBookingDetailsDTO().getLastname()).isEqualTo(expectedBookingData.get("lastname"));
        assertThat(createdUserFromResponse.getBookingDetailsDTO().getTotalprice()).isEqualTo(expectedBookingData.get("totalprice"));
        assertThat(createdUserFromResponse.getBookingDetailsDTO().getDepositpaid()).isEqualTo(expectedBookingData.get("depositpaid"));
        assertThat(createdUserFromResponse.getBookingDetailsDTO().getAdditionalneeds()).isEqualTo(expectedBookingData.get("additionalneeds"));

        assertThat(createdUserFromResponse.getBookingDetailsDTO().getBookingdates().getCheckin())
                .isEqualTo(expectedBookingData.get("checkin"));
        assertThat(createdUserFromResponse.getBookingDetailsDTO().getBookingdates().getCheckout())
                .isEqualTo(expectedBookingData.get("checkout"));
    }

    @When("user creates a booking using data from JSON file {string}")
    public void userVerifiesTheBooking(String jsonFile) throws JsonProcessingException {
        BookingDetailsDTO createBookingBodyRequest = Deserializer.jsonFileToModel(jsonFile, BookingDetailsDTO.class);

        ObjectMapper objectMapper = new ObjectMapper();  //added
        String jsonString = objectMapper.writeValueAsString(createBookingBodyRequest);

        createBookingsActions.createBooking(jsonString);

        Booking b = Deserializer.deserializeResponse(createBookingsActions.getResponse(), Booking.class);
        Serenity.setSessionVariable("Created Booking").to(b);
    }

    @When("user gets the booking ID from created booking")
    public void userGetsTheBookingId() {
        Booking createdUserFromResponse = Serenity.sessionVariableCalled("Created Booking");
        assertThat(createdUserFromResponse.getBookingId()).isNotNull();

        Serenity.setSessionVariable("Booking ID").to(createdUserFromResponse.getBookingId());
    }
}
