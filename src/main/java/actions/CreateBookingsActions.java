package actions;

import enums.Endpoints;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;

@Slf4j
public class CreateBookingsActions {

    public Response response;
    public String token;

    public Response getResponse() {
        return response;
    }

    @Step
    public void createBooking(String bodyRequest) {
        authorize();
        response = SerenityRest.given()
                .baseUri(Endpoints.BOOKING_BASE_URI.getUrl())
                .contentType("application/json")
                .body(bodyRequest)
                .header("Cookie", "token=" + token)
                .when()
                .post("/booking");
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
                .post(Endpoints.AUTH.getEndpoint())
                .then().extract().response();
        token = tokenResponse.jsonPath().getString("token");

        if (token == null) {
            throw new NullPointerException("Token value is " + token);
        }
        log.info("There is a token value: " + token);
    }
}
