package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Date;

public class SignUpPage {
    private static WebDriver driver;

    @FindBy(xpath = "//input[@name='name']")
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
    public WebElement calendarOkBtn;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void Register(String name, String email, String phoneNumber, Date birthDate, String password) throws InterruptedException {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        phoneNumberInput.sendKeys(phoneNumber);
        birthDateInput.click();
        String dateYearMonth = dateYearMonthElement.getAttribute("innerText");
        String month = dateYearMonth.replaceAll("[^A-z]", "");
        int year = Integer.parseInt(dateYearMonth.replaceAll("\\D", ""));
        System.out.println(year);
        System.out.println(birthDate.getYear() + 1900);
        while (year != (birthDate.getYear() + 1900))
        {
            //!month.equals(getMonthFromDate(birthDate.getMonth()))
            Thread.sleep(200);
            calendarLeftBtn.click();
            dateYearMonth = dateYearMonthElement.getAttribute("innerText");
            month = dateYearMonth.replaceAll("[^A-z]", "");
            year = Integer.parseInt(dateYearMonth.replaceAll("\\D", ""));
        }
        System.out.println(birthDate.getYear() + 1900);
        System.out.println(getMonthFromDate(birthDate.getMonth()));
        System.out.println(birthDate.getDay());
        Thread.sleep(200);
        driver.findElement(By.xpath("//*[text() = '" + birthDate.getDay() + "']")).click();
        calendarOkBtn.click();
        passwordInput.sendKeys(password);
        repeatedPasswordInput.sendKeys(password);
        createAccountButton.click();
    }

    public static String getMonthFromDate(int monthNumber)  {
        String[] monthNames = {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[monthNumber];
    }
}