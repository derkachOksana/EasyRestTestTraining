package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DateHandler;

import java.util.Date;

public class SignUpPage {
    private final WebDriver driver;

    @FindBy(name = "name")
    private WebElement nameInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "phoneNumber")
    private WebElement phoneNumberInput;

    @FindBy(name = "birthDate")
    private WebElement birthDateInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "repeated_password")
    private WebElement repeatedPasswordInput;

    @FindBy(xpath = "//button")
    private WebElement createAccountButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div/div[1]/div[2]/div[1]/div/p")
    private WebElement dateYearMonthElement;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div/div[1]/div[2]/div[1]/button[1]")
    private WebElement calendarLeftBtn;

    @FindBy(xpath = "//button[.//span[text()='OK']]")
    private WebElement calendarOkBtn;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputName(String name) {
        nameInput.sendKeys(name);
    }

    public void inputPhoneNumber(String phoneNumber)    {
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void inputEmail(String email)    {
        emailInput.sendKeys(email);
    }

    public void calendarAccess()    {
        birthDateInput.click();
    }

    public void setYearCalendar(Date birthDate) {
        String date;
        int year;
        int birthDateYear = getBirthDateRealYear(birthDate.getYear());
        do
        {
            calendarLeftBtn.click();
            date = dateYearMonthElement.getAttribute("innerText");
            year = Integer.parseInt(date.replaceAll("\\D", ""));
        }   while (year != birthDateYear);
    }

    public void setMonthCalendar(Date birthDate)    {
        String month;
        String birthDateMonth = DateHandler.getMonthFromDate(birthDate.getMonth());
        String date;
        do
        {
            calendarLeftBtn.click();
            date = dateYearMonthElement.getAttribute("innerText");
            month = date.replaceAll("[^A-z]", "");
        }   while (!month.equals(birthDateMonth));
    }

    public void setDayCalendar(Date birthDate) throws  InterruptedException {
        Thread.sleep(100);
        driver.findElement(By.xpath("//*[text() = '" + (birthDate.getDay() + 1) + "']")).click();
    }

    public void calendarAcceptChanges() {
        calendarOkBtn.click();
    }

    public void inputPassword(String password)  {
        passwordInput.sendKeys(password);
    }

    public void inputRepeatedPassword(String password)  {
        repeatedPasswordInput.sendKeys(password);
    }

    public void createAccountAccept()   {
        createAccountButton.click();
    }

    private int getBirthDateRealYear(int birthDateYear)    {
        return birthDateYear + 1900;
    }
}