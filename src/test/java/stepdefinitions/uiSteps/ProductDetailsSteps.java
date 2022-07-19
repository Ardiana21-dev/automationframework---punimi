package stepdefinitions.uiSteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.steps.ScenarioSteps;
import pages.ProductDetailPage;

public class ProductDetailsSteps extends ScenarioSteps {

    ProductDetailPage productDetailPage;

    @Then("'Sauce Labs Bike Light' product details are opened")
    public void sauceLabsBikeLightProductDetailPageIsOpened() {
      productDetailPage.sauceLabsBikeLightProductDetailPageIsOpened();
    }

    @When("user clicks 'Back to products' button")
    public void clickBAckToProductsButton() {
        productDetailPage.clickBAckToProductsButton();
    }
}
