package utils;

import actions.DeleteBookingActions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import models.Booking;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.stepdata.CSVTestDataSource;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Slf4j
public class Hooks {

    @Steps
    DeleteBookingActions deleteBookingActions;

    @After
    public void cleanTestData() {
        Booking createdUserFromResponse = Serenity.sessionVariableCalled("Created Booking");
        if(Optional.ofNullable(createdUserFromResponse).isEmpty()) {
            logMessage();
        } else {
            deleteBookingActions.deleteBooking(createdUserFromResponse.getBookingId());
        }
    }

    private void logMessage() {
        log.info("there was no data to be deleted!");
    }
}




