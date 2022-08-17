package pageComponents.menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPageComponent {
    public OrderSummaryPageComponent orderSummary;

    public OrderConfirmationPageComponent(WebDriver driver)  {
        PageFactory.initElements(driver, this);
        orderSummary = new OrderSummaryPageComponent(driver);
    }
    @FindBy(xpath = "//label[contains(., 'Date picker')]/..//*[@type='text']")
    private WebElement datePickerField;

    @FindBy(xpath = "//label[contains(., 'Time picker')]/..//*[@type='text']")
    private WebElement timePickerField;

    @FindBy(xpath = "//button/span[text()='Submit']")
    private WebElement submitBtn;

   @FindBy(xpath = "//button/span[text()='Cancel']/ancestor::button")
   private WebElement cancelBtn;

    @FindBy(xpath = "//h6[contains(., 'Order confirmation')]")
    private WebElement orderConfirmationField;

    public String getTime() {
        return timePickerField.getAttribute("value");
    }

    public String getDate() {
        return datePickerField.getAttribute("value");
    }

    public void cancelOrder()    {
        cancelBtn.click();
    }

    public void submitOrder()    {
        submitBtn.click();
    }

    public String orderConfirmationFieldVisible() {
        return orderConfirmationField.getText();
    }
}
