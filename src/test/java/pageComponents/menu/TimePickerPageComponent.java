package pageComponents.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DateHandler;

import java.util.Date;

public class TimePickerPageComponent {
    private final WebDriver driver;
    private final Actions action;

    private final Date desiredDate;

    public TimePickerPageComponent(WebDriver driver, Date desiredDate)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.desiredDate = desiredDate;
        action = new Actions(driver);
    }

    @FindBy(xpath = "//*[text() = 'AM']")
    private WebElement amBtn;

    @FindBy(xpath = "//*[text() = 'PM']")
    private WebElement pmBtn;

    @FindBy(xpath = "//div[contains(@class, 'squareMask')]")
    private WebElement clock;

    @FindBy(xpath = "//button[.//span[text()='OK']]")
    private WebElement okBtn;

    public void setAmPm() {
        if(DateHandler.getHours(desiredDate) <= 11) {
            amBtn.click();
        }
        if(DateHandler.getHours(desiredDate) > 11)  {
            pmBtn.click();
        }
    }


    public void setHours()  {
        action.dragAndDrop(clock,
                        driver.findElement(By.xpath("//div[contains(@class, 'PickersClock')]" +
                                "//span[text()=" + DateHandler.getHours(desiredDate) + "]")))
                .build()
                .perform();
    }

    public void setMinutes()    {
        action.moveToElement(clock,
                        (int)Math.sin(DateHandler.getMinutes(desiredDate) * 6) * 100,
                        (int)Math.cos(DateHandler.getMinutes(desiredDate) * 6) * 100)
                .click()
                .perform();
    }

    public OrderConfirmationPageComponent confirmTime() {
        okBtn.click();
        return new OrderConfirmationPageComponent(driver);
    }
}
