package actions;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

@Slf4j
public class BookingActions {

    public Response response;

    @Step
    public void userMakesARequestToCheckTheHealthOfBookingService() {
        String preparedRequestToExecute = Serenity.sessionVariableCalled("Called Endpoint");
        response = SerenityRest.when().get(preparedRequestToExecute);
    }

    @Step
    public void userMakesARequestToViewBookingIDs() {
        String preparedRequestToExecute = Serenity.sessionVariableCalled("Called Endpoint");

        int bookingId = SerenityRest.when().get(preparedRequestToExecute)
                .getBody().jsonPath().getInt("[0].bookingid");
        Serenity.setSessionVariable("Booking ID").to(bookingId);
        log.info("Booking ID:", bookingId);

        response = SerenityRest.when().get(preparedRequestToExecute);
    }

    @Step
    public void userMakesARequestToViewDetailsOfBookingID(String bookingId) {
        String preparedRequestToExecute = Serenity.sessionVariableCalled("Called Endpoint");
        Integer urlParameter = Integer.valueOf(bookingId);

        response = SerenityRest.with().pathParam("bookingID", urlParameter)
                .when().get(preparedRequestToExecute + "/{bookingID}");
    }

    @Step
    public void userMakesARequestToViewDetailsOfBookingID() {
        String preparedRequestToExecute = Serenity.sessionVariableCalled("Called Endpoint");
        int preparedURLParameter = Serenity.sessionVariableCalled("Booking ID");

        response = SerenityRest.with().pathParam("bookingID", preparedURLParameter)
                .when().get(preparedRequestToExecute + "/{bookingID}");
    }

    @Step
    public void userMakesARequestToViewBookingFromTo(String checkIn, String checkOut) {
        String preparedRequestToExecute = Serenity.sessionVariableCalled("Called Endpoint");
        response = SerenityRest.with()
                .queryParams("checkin", checkIn, "checkout", checkOut)
                .when()
                .get(preparedRequestToExecute);
    }

    @Step
    public void userMakesARequestToViewBookingIDByUserName(String firstName, String lastName) {
        String preparedRequestToExecute = Serenity.sessionVariableCalled("Called Endpoint");
        response = SerenityRest.with()
                .queryParams("firstname", firstName, "lastname", lastName)
                .when()
                .get(preparedRequestToExecute);
    }

    @Step
    public Response getBookingDetailsByBookingId(Integer bookingId) {
        String preparedRequestToExecute = Serenity.sessionVariableCalled("Called Endpoint");
        response = SerenityRest.with().pathParam("bookingID", bookingId)
                .when().get(preparedRequestToExecute + "/{bookingID}");
        return response;
    }

    public Response getResponse() {   //this method to return the API call response whenever needed to verify in assertions
        return response;
    }
}
