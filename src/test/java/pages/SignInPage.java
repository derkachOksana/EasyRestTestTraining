package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ConfProperties;

public class SignInPage {
    private WebDriver driver;
    public SignInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }



    @FindBy(xpath = "//span[contains(text(),'Sign In')]")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailButton;

    @FindBy(xpath = "/html/body/div/main/div/div[2]/form/div/div[3]/div/button/span[1]")
    private WebElement buttonSignInOnPageSignIn;

    @FindBy(xpath = "/html/body/div/main/div/div[2]/form/div/div[1]/div/div/p")
    private WebElement warningTextEmail;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordButton;




    public String warningTextEnterEmptyEmail() {
        signInButton.click();
        String currentText;
        String exceptedText = "Email is required";
        emailButton.click();
        emailButton.clear();
        buttonSignInOnPageSignIn.click();
        currentText = warningTextEmail.getText();

        return currentText;
    }
    public boolean enterToAccount () {
        String email = "jasonbrown@test.com";
        String password = "1111";
        signInButton.click();
        emailButton.click();
        emailButton.clear();
        emailButton.sendKeys(email);
        passwordButton.click();
        passwordButton.clear();
        passwordButton.sendKeys(password);
        passwordButton.sendKeys(Keys.ENTER);

        boolean emptyURL = driver.getCurrentUrl().isEmpty();
        return emptyURL;
    }
}
