package actions;

import enums.Endpoints;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;

public class DeleteBookingActions {

    public Response response;
    public String token;

    @Step
    public void deleteBooking(Integer bookingId) {
        auth();
        response = SerenityRest.given()
                .baseUri(Endpoints.BOOKING_BASE_URI.getUrl())
                .contentType("application/json")
                .param("bookingID", bookingId)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId);
    }

    private void auth() {
        String payload = "{\n" +
                "         \"username\" : \"admin\",\n" +
                "         \"password\" : \"password123\"\n" +
                "}";
        Response tokenResponse = given()
                .baseUri(Endpoints.BOOKING_BASE_URI.getUrl())
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/auth") //maybe refactor
                .then().extract().response();
        token = tokenResponse.jsonPath().getString("token");
    }

    public Response getResponse() {   //this method to return the API call response whenever needed to verify in assertions
        return response;
    }
}
