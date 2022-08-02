package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DateHandler;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class OrderConfirmationPageComponent {
    private final WebDriver driver;

    private DatePickerPageComponent datePicker;
    private TimePickerPageComponent timePicker;

    public OrderConfirmationPageComponent(WebDriver driver)  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//label[contains(., 'Date picker')]/..//*[@type='text']")
    private WebElement datePickerField;

    @FindBy(xpath = "//label[contains(., 'Time picker')]/..//*[@type='text']")
    private WebElement timePickerField;

    @FindBy(xpath = "//table//tbody//tr")
    private List<WebElement> itemsInOrder;

    @FindBy(xpath = "//table//*[text()='Total:']//..//td[3]")
    private WebElement totalOrderPrice;

    public OrderConfirmationPageComponent setDate(Date desiredDate) {
        datePicker = new DatePickerPageComponent(driver, desiredDate);
        datePickerField.click();
        datePicker.setDesiredYear();
        datePicker.setDesiredMonth();
        datePicker.setDesiredDay();
        return datePicker.confirmDate();
    }

    public OrderConfirmationPageComponent setTime(Date desiredDate) {
        timePicker = new TimePickerPageComponent(driver, desiredDate);
        timePickerField.click();
        //timepicker.setAmPm();
        timePicker.setHours();
        timePicker.setMinutes();
        return timePicker.confirmTime();
    }

    public String getVolumeByItemName(String itemName) {
        String volume = "";
        for(WebElement item: itemsInOrder) {
            if (itemName.equals(item.findElement(By.xpath("//td[2]")).getText()))   {
                volume = item.findElement(By.xpath("//td[3]")).getText();
            }
        }
        return volume;
    }

    public String getPricePerItemByItemName(String itemName) {
        String pricePerItem = "";
        for(WebElement item: itemsInOrder) {
            if (itemName.equals(item.findElement(By.xpath("//td[2]")).getText()))   {
                pricePerItem = item.findElement(By.xpath("//td[4]")).getText();
            }
        }
        return pricePerItem;
    }

    public String getQuantityByItemName(String itemName) {
        String quantity = "";
        for(WebElement item: itemsInOrder) {
            if (itemName.equals(item.findElement(By.xpath("//td[2]")).getText()))   {
                quantity = item.findElement(By.xpath("//td[5]")).getText();
            }
        }
        return quantity;
    }

    public String getTotalPriceByItemName(String itemName) {
        String totalPrice = "";
        for(WebElement item: itemsInOrder) {
            if (itemName.equals(item.findElement(By.xpath("//td[2]")).getText()))   {
                totalPrice = item.findElement(By.xpath("//td[6]")).getText();
            }
        }
        return totalPrice;
    }

    public String getTime() {
        return timePickerField.getAttribute("value");
    }

    public String getDate() {
        return datePickerField.getAttribute("value");
    }

    public String getTotalOrderPrice()   {
        return totalOrderPrice.getText();
    }
}
