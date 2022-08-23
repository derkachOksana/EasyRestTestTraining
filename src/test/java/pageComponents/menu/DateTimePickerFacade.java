package pageComponents.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Date;

public class DateTimePickerFacade {

    public DateTimePickerFacade(DatePickerPageComponent datePicker, TimePickerPageComponent timePicker) {
        this.datePicker = datePicker;
        this.timePicker = timePicker;
    }

    private final DatePickerPageComponent datePicker;
    private final TimePickerPageComponent timePicker;

    public OrderConfirmationPageComponent setDate(WebDriver driver) {
        driver.findElement(By.xpath(
                "//label[contains(., 'Date picker')]/..//*[@type='text']"))
                .click();
        datePicker.setDesiredYear();
        datePicker.setDesiredMonth();
        datePicker.setDesiredDay();
        return datePicker.confirmDate();
    }

    public OrderConfirmationPageComponent setTime(WebDriver driver) {
        driver.findElement(By.xpath(
                "//label[contains(., 'Time picker')]/..//*[@type='text']"))
                .click();
        timePicker.setAmPm();
        timePicker.setHours();
        timePicker.setMinutes();
        return timePicker.confirmTime();
    }
}
