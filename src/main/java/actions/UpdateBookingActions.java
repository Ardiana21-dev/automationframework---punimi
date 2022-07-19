package actions;

import enums.Endpoints;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;

@Slf4j
public class UpdateBookingActions {

    public Response response;
    public String token;

    @Step
    public void updateBooking(String bodyRequest, Integer bookingId) {
        authorize();
        response = SerenityRest.given()
                .baseUri(Endpoints.BOOKING_BASE_URI.getUrl())
                .contentType("application/json")
                .param("bookingID", bookingId)
                .header("Cookie", "token=" + token)
                .body(bodyRequest)
                .when()
                .put("/booking/" + bookingId);
    }

    private void authorize() {
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

        if (token == null) {
            throw new NullPointerException("Token value is " + token);
        }
        log.info("There is a token value" + token);
    }

    public Response getResponse() { //this method to return the API call response whenever needed to verify in assertions
        return response;
    }
}
