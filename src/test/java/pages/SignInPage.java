package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    private final WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='email']")
    private WebElement userEmailInputField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement userPasswordInputField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/header/div/a")
    private WebElement homeBtn;


    public void setUserEmailInputField(String email) {
        userEmailInputField.sendKeys(email);
    }

    public void setUserPasswordInputField(String password) {
        userPasswordInputField.sendKeys(password);
    }

    public void clickSignInBtn() {
        signInBtn.click();
    }

    public void getHomeBtn() {
        homeBtn.click();
    }
}
