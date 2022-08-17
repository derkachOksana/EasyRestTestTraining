package pageComponents.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Date;

public class DateTimePickerFacade {

    public static OrderConfirmationPageComponent setDate(WebDriver driver, Date desiredDate) {
        DatePickerPageComponent datePicker = new DatePickerPageComponent(driver, desiredDate);
        driver.findElement(By.xpath(
                "//label[contains(., 'Date picker')]/..//*[@type='text']"))
                .click();
        datePicker.setDesiredYear();
        datePicker.setDesiredMonth();
        datePicker.setDesiredDay();
        return datePicker.confirmDate();
    }

    public static OrderConfirmationPageComponent setTime(WebDriver driver, Date desiredDate) {
        TimePickerPageComponent timePicker = new TimePickerPageComponent(driver, desiredDate);
        driver.findElement(By.xpath(
                "//label[contains(., 'Time picker')]/..//*[@type='text']"))
                .click();
        timePicker.setAmPm();
        timePicker.setHours();
        timePicker.setMinutes();
        return timePicker.confirmTime();
    }
}
