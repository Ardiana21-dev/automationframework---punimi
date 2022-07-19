package stepdefinitions.restApiTestingStepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.is;

public class CountrySearchSteps {

    private String ISO_CODE_SEARCH = "http://services.groupkt.com/country/get/iso2code/";
    private Response response;

    @When("I try to search for the country code {string}")
    public void searchCountryByCode(String code){
        response = SerenityRest.when().get("http://services.groupkt.com/country/get/all");
    }

    @Then("The search is successful")
    public void searchIsExecutedSuccesfully(){
        response.then().statusCode(200);
    }

    @And("The result shows the country name is {string}")
    public void iShouldFindCountry(String country){
        response.then().body("RestResponse.result.name", is(country));
    }
}
