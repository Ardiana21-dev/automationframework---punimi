package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailPage extends PageObject {

    @FindBy(xpath = "//img[@class='inventory_details_img' and @alt='Sauce Labs Bike Light']")
    WebElement sauce_labs_bike_light_image;

    @FindBy(css = "div[class='inventory_details_desc_container']")
    WebElement sauce_Labs_Bike_Light_product_details_container;

    @FindBy(name = "back-to-products")
    WebElement back_to_products_button;

    public void sauceLabsBikeLightProductDetailPageIsOpened() {
        waitFor(ExpectedConditions.visibilityOf(sauce_labs_bike_light_image));
        $(sauce_labs_bike_light_image).shouldBeVisible();
        $(sauce_Labs_Bike_Light_product_details_container).shouldBeVisible();
    }

    public void clickBAckToProductsButton() {
        $(back_to_products_button).shouldBeVisible();
        $(back_to_products_button).isClickable();
        $(back_to_products_button).click();
    }
}