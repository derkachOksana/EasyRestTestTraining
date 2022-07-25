package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

        private final WebDriver driver;
        private String userEmail;
        private String userPassword;

        public SignInPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        @FindBy(xpath = "//input[@name='email']")
        WebElement userEmailField;

        @FindBy(xpath = "//input[@name='password']")
        WebElement userPasswordField;

        @FindBy(xpath = "//button[@type='submit']")
        WebElement signInBtn;

        @FindBy(xpath = "//*[@id=\"root\"]/header/div/a")
        WebElement homeBtn;



        public WebElement emailId() {
            return userEmailField;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public WebElement password() {
            return userPasswordField;
        }
        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public WebElement signIn() {
            return signInBtn;
        }

        public WebElement home() {
            return homeBtn;
        }
    }
