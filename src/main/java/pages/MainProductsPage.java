package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class MainProductsPage extends PageObject {

    @FindBy(xpath = "//div[@id='header_container']//span[text()='Products']")
    WebElement products_header;

    @FindBy(css = "img[alt='Sauce Labs Bike Light']")
    WebElement sauce_Labs_Bike_Light_product;

    @FindBy(css = "a[class='shopping_cart_link'] > span[class='shopping_cart_badge']")
    WebElement shopping_cart_badge;

    @FindBy(css = "button[id='add-to-cart-sauce-labs-backpack")
    WebElement add_to_cart_button_for_sauce_labs_backpack;



    public Boolean verifyMainProductsIsDisplayed() {
        waitFor(products_header).waitUntilVisible();
        return products_header.isDisplayed();
    }

    public void addToCartSauceLabsBackpackProduct() {
        $(add_to_cart_button_for_sauce_labs_backpack).shouldBeVisible();
        $(add_to_cart_button_for_sauce_labs_backpack).isClickable();
        $(add_to_cart_button_for_sauce_labs_backpack).click();
    }

    public void verifyProductIsAddedToShoppingCartBadge() {
        $(shopping_cart_badge).shouldBePresent();
        $(shopping_cart_badge).shouldBeVisible();
    }

    public String getProductNumberOfShoppingCartBadge() {
        return $(shopping_cart_badge).getText();
    }

    public void clickSauceLabsBikeLightProduct() {
        $(sauce_Labs_Bike_Light_product).shouldBeVisible();
        $(sauce_Labs_Bike_Light_product).isClickable();
        $(sauce_Labs_Bike_Light_product).click();
    }
}