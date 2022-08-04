package pageComponents.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.menu.OrderConfirmationPageComponent;
import pageComponents.menu.YearPickerPageComponent;
import utility.DateHandler;

import java.util.Date;

public class DatePickerPageComponent {

    private final WebDriver driver;
    private final Date desiredDate;

    public DatePickerPageComponent(WebDriver driver, Date desiredDate)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.desiredDate = desiredDate;
    }

    @FindBy(xpath = "//h6[contains(@class, 'Pickers')]")
    WebElement yearPickerAccess;

    @FindBy(xpath = "//div[contains(@class, 'CalendarHeader')]//button[1]")
    private WebElement prevBtn;

    @FindBy(xpath = "//div[contains(@class, 'CalendarHeader')]//button[2]")
    private WebElement nextBtn;

    @FindBy(xpath = "//div[contains(@class, 'CalendarHeader')]//div")
    private WebElement dateCurrentYearMonth;

    @FindBy(xpath = "//button[.//span[text()='OK']]")
    private WebElement okBtn;

    public void setDesiredYear()    {
        yearPickerAccess.click();
        YearPickerPageComponent yearPicker = new YearPickerPageComponent(driver,
                getDesiredDateRealYear(desiredDate.getYear()));
        yearPicker.setYear();
    }

    public void setDesiredMonth()   {
        boolean desiredDateIsFuture = false, presentMonth = false;
        Date currentDate = new Date();

        if(desiredDate.after(currentDate))   {
            desiredDateIsFuture = true;
        }
        if (desiredDate.getMonth() == currentDate.getMonth())   {
            presentMonth = true;
        }

        String month;
        String desiredDateMonth = DateHandler.getMonthFromDate(desiredDate.getMonth());
        String date;

        if (!presentMonth)  {
            if (!desiredDateIsFuture)   {
                do
                {
                    prevBtn.click();
                    date = dateCurrentYearMonth.getAttribute("innerText");
                    month = date.replaceAll("[^A-z]", "");
                }   while (!month.equals(desiredDateMonth));
            }

            if (desiredDateIsFuture)   {
                do
                {
                    nextBtn.click();
                    date = dateCurrentYearMonth.getAttribute("innerText");
                    month = date.replaceAll("[^A-z]", "");
                }   while (!month.equals(desiredDateMonth));
            }
        }
    }

    public void setDesiredDay()    {
        driver.findElement(By.xpath(
                "//*[text() = '" + (desiredDate.getDay() + 1) + "']"))
                .click();
    }

    public OrderConfirmationPageComponent confirmDate() {
        okBtn.click();
        return new OrderConfirmationPageComponent(driver);
    }

    private int getDesiredDateRealYear(int desiredDateYear)    {
        return desiredDateYear + 1900;
    }
}
