package actions;

import enums.Endpoints;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

public class CommonActions {

    public Response response;

    public void prepareRequest(String endpoint) {
        String restApi = Endpoints.BOOKING_BASE_URI.getUrl() + endpoint;
        Serenity.setSessionVariable("Called Endpoint").to(restApi);
    }
}
