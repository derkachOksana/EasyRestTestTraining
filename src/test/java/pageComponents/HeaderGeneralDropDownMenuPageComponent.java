package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignInPage;
import pages.profile.MyProfilePage;
import pages.waiter.WaiterMainPage;

import java.time.Duration;

public class HeaderGeneralDropDownMenuPageComponent {
    private final WebDriver driver;
    private WebDriverWait wait;
    public HeaderGeneralDropDownMenuPageComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[text()='Waiter panel']")
    private WebElement waiterPanelBtn;

    @FindBy(xpath = "//*[text()='Log Out']")
    private WebElement logOutBtn;

    @FindBy(xpath = "//*[text()='My Profile']")
    private WebElement myProfileBtn;

    public WaiterMainPage waiterPanelAccess() {
        waiterPanelBtn.click();
        return new WaiterMainPage(driver);
    }
    public SignInPage logOut()    {
        logOutBtn.click();
        return new SignInPage(driver);
    }
    public MyProfilePage myProfileAccess(Duration duration) {
        wait = new WebDriverWait(driver, duration);
        for (int i = 0; i <= 2; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//*[text()='My Profile']")));
                break;
            } catch (Exception e) {

            }
        }
        myProfileBtn.click();
        return new MyProfilePage(driver);
    }

    public MyProfilePage myProfileAccess() {
        myProfileBtn.click();
        return new MyProfilePage(driver);
    }
}
