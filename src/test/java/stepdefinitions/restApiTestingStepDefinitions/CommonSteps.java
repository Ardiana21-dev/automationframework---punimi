package stepdefinitions.restApiTestingStepDefinitions;

import actions.CommonActions;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class CommonSteps {

    @Steps
    CommonActions commonActions;

    @Given("user has access to endpoint {string} of Booking API")
    public void userHasAccessToEndpointOfBookingAPI(String endpoint) {
        commonActions.prepareRequest(endpoint);
    }
}
