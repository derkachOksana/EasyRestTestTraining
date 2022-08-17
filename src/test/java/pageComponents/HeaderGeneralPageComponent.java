package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.SignUpPage;
import pages.waiter.WaiterMainPage;
import utility.ConfProperties;

import java.time.Duration;

public class HeaderGeneralPageComponent {

    private final WebDriver driver;
    private WebDriverWait wait;
    private Duration duration;


    public HeaderGeneralPageComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[contains(@class, 'UserMenu')]/*[button]")
    private WebElement userMenuBtn;

    @FindBy(xpath = "//span[text()='Home']")
    private WebElement homeBtn;

    @FindBy(xpath = "//span[text()='Restaurants List']")
    private WebElement restaurantListBtn;

    @FindBy(xpath = "//span[text()='Sign In']")
    private WebElement signInBtn;

    @FindBy(xpath = "//span[text()='Sign Up']")
    private WebElement signUpBtn;


    public HeaderGeneralDropDownMenuPageComponent userMenu()   {
        userMenuBtn.click();
        return new HeaderGeneralDropDownMenuPageComponent(driver);
    }
    public HeaderGeneralDropDownMenuPageComponent userMenu(Duration duration)   {
        wait = new WebDriverWait(driver, duration);
        for (int i = 0; i <= 2; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'UserMenu')]/*[button]")));
                break;
            } catch (Exception e) {

            }
        }
        userMenuBtn.click();
        return new HeaderGeneralDropDownMenuPageComponent(driver);
    }

    public RestaurantsPage restaurantsListAccess(WebDriver driver)  {
        restaurantListBtn.click();
        return new RestaurantsPage(driver);
    }

    public SignInPage signInAccess()    {
        signInBtn.click();
        return new SignInPage(driver);
    }

    public SignUpPage signUpAccess() {
        signUpBtn.click();
        return new SignUpPage(driver);
    }

    public HomePage homeAccess()    {
        homeBtn.click();
        return new HomePage(driver);
    }
}
