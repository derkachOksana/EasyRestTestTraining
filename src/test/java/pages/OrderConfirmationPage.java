package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DateHandler;

import java.util.Date;
import java.util.List;

public class OrderConfirmationPage {
    private final WebDriver driver;
    private final Actions action;

    public OrderConfirmationPage(WebDriver driver)  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        action = new Actions(driver);
    }

    private Date desiredDate;
    private boolean desiredDateIsFuture, presentYear, presentMonth;

    public void setDesiredDate(Date desiredDate)   {
        this.desiredDate = desiredDate;
        Date currentDate = new Date();
        if(desiredDate.after(currentDate))   {
            desiredDateIsFuture = true;
        }
        if (desiredDate.getYear() == currentDate.getYear())   {
            presentYear = true;
        }
        if (desiredDate.getMonth() == currentDate.getMonth())   {
            presentMonth = true;
        }
    }

    @FindBy(xpath = "//label[contains(., 'Date picker')]/../div[contains(@class, 'Input')]")
    private WebElement datePickerField;

    @FindBy(xpath = "//label[contains(., 'Time picker')]/../div[contains(@class, 'Input')]")
    private WebElement timePickerField;

    @FindBy(xpath = "//div[contains(@class, 'CalendarHeader')]//button[1]")
    private WebElement calendarPrevBtn;

    @FindBy(xpath = "//div[contains(@class, 'CalendarHeader')]//button[2]")
    private WebElement calendarNextBtn;

    @FindBy(xpath = "//div[contains(@class, 'CalendarHeader')]//div")
    private WebElement dateCurrentYearMonth;

    @FindBy(xpath = "//button[.//span[text()='OK']]")
    private WebElement calendarClockOkBtn;

    @FindBy(xpath = "//*[text() = 'AM']")
    private WebElement amBtn;

    @FindBy(xpath = "//*[text() = 'PM']")
    private WebElement pmBtn;

    @FindBy(xpath = "//div[contains(@class, 'squareMask')]")
    private WebElement clockTimePicker;

    @FindBy(xpath = "//table//tbody//tr")
    private List<WebElement> itemsInOrder;

    @FindBy(xpath = "//table//*[text()='Total:']//..//td[3]")
    private WebElement totalOrderPrice;

    public void datePickerAccess()  {
        datePickerField.click();
    }

    public void timePickerAccess()  {
        timePickerField.click();
    }

    public void calendarAcceptChanges() {
        calendarClockOkBtn.click();
    }

    public void setYearCalendar()  {
        String date;
        int year;
        int desiredDateRealYear = getDesiredDateRealYear(desiredDate.getYear());

        if(!presentYear)    {
            if(!desiredDateIsFuture)  {
                do
                {
                    calendarPrevBtn.click();
                    date = dateCurrentYearMonth.getAttribute("innerText");
                    year = Integer.parseInt(date.replaceAll("\\D", ""));
                }   while (year != desiredDateRealYear);
            }

            if (desiredDateIsFuture) {
                do
                {
                    calendarNextBtn.click();
                    date = dateCurrentYearMonth.getAttribute("innerText");
                    year = Integer.parseInt(date.replaceAll("\\D", ""));
                }   while (year != desiredDateRealYear);
            }
        }
    }

    public void setMonthCalendar()  {
        String month;
        String desiredDateMonth = DateHandler.getMonthFromDate(desiredDate.getMonth());
        String date;

        if (!presentMonth)  {
            if (!desiredDateIsFuture)   {
                do
                {
                    calendarPrevBtn.click();
                    date = dateCurrentYearMonth.getAttribute("innerText");
                    month = date.replaceAll("[^A-z]", "");
                }   while (!month.equals(desiredDateMonth));
            }

            if (desiredDateIsFuture)   {
                do
                {
                    calendarNextBtn.click();
                    date = dateCurrentYearMonth.getAttribute("innerText");
                    month = date.replaceAll("[^A-z]", "");
                }   while (!month.equals(desiredDateMonth));
            }
        }
    }

    public void setDayCalendar()    {
        driver.findElement(By.xpath("//*[text() = '" + (desiredDate.getDay() + 1) + "']")).click();
    }

    public void setAM() {
        amBtn.click();
    }

    public void setPM() {
        pmBtn.click();
    }

    public void setHours()  {
        action.dragAndDrop(clockTimePicker,
                driver.findElement(By.xpath("//div[contains(@class, 'PickersClock')]" +
                        "//span[text()=" + DateHandler.getHours(desiredDate) + "]")))
                .build()
                .perform();
    }

    public void setMinutes()    {
        action.moveToElement(clockTimePicker,
                        (int)Math.sin(DateHandler.getMinutes(desiredDate) * 6) * 100,
                (int)Math.cos(DateHandler.getMinutes(desiredDate) * 6) * 100)
                .click()
                .perform();
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

    public String getTotalOrderPrice()   {
        return totalOrderPrice.getText();
    }

    private int getDesiredDateRealYear(int birthDateYear)    {
        return birthDateYear + 1900;
    }
}
