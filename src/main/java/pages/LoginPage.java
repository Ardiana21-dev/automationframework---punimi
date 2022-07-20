package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageObject {

    @FindBy(xpath = "//div[@class='login_logo']")
    WebElement loginLogo;

    @FindBy(id = "user-name")
    WebElement username_input;

    @FindBy(id = "password")
    WebElement password_input;

    @FindBy(css = "input[id='login-button']")
    WebElement login_button;

    public Boolean verifyLoginPageIsDisplayed() {
        waitFor(loginLogo).waitUntilVisible();
        return loginLogo.isDisplayed();
    }

    public void typeUserName(String username) {
        $(username_input).waitUntilVisible();
        $(username_input).type(username);
    }

    public void typePassword(String password) {
        $(password_input).waitUntilVisible();
        $(password_input).type(password);
    }

    public void clickLoginButton() {
        $(login_button).click();
    }
}
