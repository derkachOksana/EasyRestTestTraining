package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    @FindBy(xpath = "//a[@href='/log-in']")
    private WebElement signInBtn;

    @FindBy(xpath = "//a[@href='/sign-up']")
    private WebElement signUpBtn;

    @FindBy(xpath = "//a[contains(.,'Restaurants List')]")
    private WebElement restaurantsListBtn;

    @FindBy(xpath = "//a[contains(.,'View All')]")
    private WebElement viewAllTag;



    public void signInAccess() {
        signInBtn.click();
    }
    public void signUpAccess() {
        signUpBtn.click();
    }
    public void restaurantsListAccess() {
        restaurantsListBtn.click();
    }
    public void viewAllAccess() {
        viewAllTag.click();
    }






}
