package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageComponents.HeaderGeneralPageComponent;

import java.time.Duration;

public class SignInPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement userEmailInputField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement userPasswordInputField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInBtn;

    @FindBy(xpath = "//*[@role='alertdialog']")
    private WebElement alertMessage;

    @FindBy(xpath = "//p[contains(.,'Email')]")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//p[contains(.,'Password')]")
    private WebElement passwordErrorMessage;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public HeaderGeneralPageComponent getHeaderGeneralPageComponent() {
        return new HeaderGeneralPageComponent(driver);
    }

    public void setUserEmailInputField(String email) {
        userEmailInputField.sendKeys(email);
    }

    public void setUserPasswordInputField(String password) {
        userPasswordInputField.sendKeys(password);
    }

    public void clickSignInBtn() {
        signInBtn.click();
    }

    public String getAlertMessage() {
        wait.until(ExpectedConditions.visibilityOf(alertMessage));
        return alertMessage.getText().trim();
    }

    public String getEmailErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage));
        return emailErrorMessage.getText().trim();
    }

    public String getPasswordErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
        return passwordErrorMessage.getText().trim();
    }
}
