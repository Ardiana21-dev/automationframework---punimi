package stepdefinitions.uiSteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.steps.ScenarioSteps;
import pages.MainProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MainProductsSteps extends ScenarioSteps {

    MainProductsPage mainProductsPage;

    @Then("user is directed to main product saucelabs page")
    public void userNavigatesToMainProductSauceLabsPage() {
        assertThat(mainProductsPage.verifyMainProductsIsDisplayed()).isTrue();
    }

    @When("user adds to cart the 'Sauce Labs Backpack'")
    public void userAddsToCartTheSauceLabsPack() {
        mainProductsPage.addToCartSauceLabsBackpackProduct();
    }

    @Then("the product is added to shopping cart badge")
    public void productIsAddedToShoppingCartBadge() {
        mainProductsPage.verifyProductIsAddedToShoppingCartBadge();
        assertThat(mainProductsPage.getProductNumberOfShoppingCartBadge()).isEqualTo("1");
    }

    @When("user clicks the 'Sauce Labs Bike Light' product")
    public void clicksSauceLabsBikeLightProduct() {
        mainProductsPage.clickSauceLabsBikeLightProduct();
    }
}
