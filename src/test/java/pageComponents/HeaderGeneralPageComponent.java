package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.SignUpPage;
import pages.waiter.WaiterMainPage;

public class HeaderGeneralPageComponent {

    private final WebDriver driver;

    public HeaderGeneralPageComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//body/div/header")
    private WebElement header;

    private final HeaderGeneralDropDownMenuPageComponent dropDownMenu = userMenu();

    private final WebElement userMenuBtn = header.findElement(By.xpath(
            ".//div[contains(@class, 'UserMenu')]/*[button]"));

    private final WebElement homeBtn = header.findElement(By.xpath(
            ".//span[text()='Home']"));

    private final WebElement restaurantListBtn = header.findElement(By.xpath(
            ".//span[text()='Restaurants List']"));

    private final WebElement signInBtn = header.findElement(By.xpath(
            ".//span[text()='Sign In']"));

    private final WebElement signUpBtn = header.findElement(By.xpath(
            ".//span[text()='Sign Up']"));

    private HeaderGeneralDropDownMenuPageComponent userMenu()   {
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
        return new SignUpPage();
    }

    public HomePage homeAccess()    {
        homeBtn.click();
        return new HomePage(driver);
    }

    public WaiterMainPage waiterPanelAccess() {
        return dropDownMenu.waiterPanelAccess();
    }

    public SignInPage logOut()  {
        return dropDownMenu.logOut();
    }
}
