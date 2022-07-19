package stepdefinitions.uiSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends ScenarioSteps {

    @Managed(driver = "chrome")
    public WebDriver driver;

    LoginPage loginPage;

    @Given("user can navigate to saucelabs url")
    public void userCanNavigateToSauceLabsUrl() {
        String url = "https://www.saucedemo.com/";
        Serenity.setSessionVariable("URL").to(url);
    }

    @When("user navigates to saucelabe url")
    public void userNavigatesToSauceLabsUrl() {
        String urlToNavigate = Serenity.sessionVariableCalled("URL");
        driver.manage().window().maximize();
        driver.navigate().to(urlToNavigate);
    }

    @Then("user can see the login saucelabs page")
    public void userNavigatesToLogInSauceLabsPage() {
        assertThat(loginPage.verifyLoginPageIsDisplayed()).isTrue();
    }

    @When("user types username: {string}")
    public void typeUserName(String username) {
        loginPage.typeUserName(username);
    }

    @And("user types password: {string}")
    public void typePassword(String password) {
        loginPage.typePassword(password);
    }

    @And("user clicks LOGIN button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }
}
