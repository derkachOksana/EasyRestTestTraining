package pageComponents.menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Date;

public class OrderConfirmationPageComponent {
    private final WebDriver driver;

    public OrderSummaryPageComponent orderSummary;

    public OrderConfirmationPageComponent(WebDriver driver)  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        orderSummary = new OrderSummaryPageComponent(driver);
    }
    @FindBy(xpath = "//label[contains(., 'Date picker')]/..//*[@type='text']")
    private WebElement datePickerField;

    @FindBy(xpath = "//label[contains(., 'Time picker')]/..//*[@type='text']")
    private WebElement timePickerField;

    public OrderConfirmationPageComponent setDate(Date desiredDate) {
        DatePickerPageComponent datePicker = new DatePickerPageComponent(driver, desiredDate);
        datePickerField.click();
        datePicker.setDesiredYear();
        datePicker.setDesiredMonth();
        datePicker.setDesiredDay();
        return datePicker.confirmDate();
    }

    public OrderConfirmationPageComponent setTime(Date desiredDate) {
        TimePickerPageComponent timePicker = new TimePickerPageComponent(driver, desiredDate);
        timePickerField.click();
        timePicker.setAmPm();
        timePicker.setHours();
        timePicker.setMinutes();
        return timePicker.confirmTime();
    }

    public String getTime() {
        return timePickerField.getAttribute("value");
    }

    public String getDate() {
        return datePickerField.getAttribute("value");
    }
}
