package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;

public class SignUpPage {

    private final WebDriver driver;

    public final HeaderGeneralPageComponent headerGlobal;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        headerGlobal = new HeaderGeneralPageComponent(driver);
    }

    @FindBy(name = "name")
    private WebElement nameInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "phoneNumber")
    private WebElement phoneNumberInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "repeated_password")
    private WebElement repeatedPasswordInput;

    @FindBy(xpath = "//*[text()='Create account']")
    private WebElement createAccountButton;

    public void inputName(String name) {
        nameInput.sendKeys(name);
    }

    public void inputPhoneNumber(String phoneNumber)    {
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void inputEmail(String email)    {
        emailInput.sendKeys(email);
    }

    public void inputPassword(String password)  {
        passwordInput.sendKeys(password);
    }

    public void inputRepeatedPassword(String password)  {
        repeatedPasswordInput.sendKeys(password);
    }

    public SignInPage createAccountAccept()   {
        createAccountButton.click();
        return new SignInPage(driver);
    }
}