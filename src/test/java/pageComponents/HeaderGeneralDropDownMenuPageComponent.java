package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.SignInPage;
import pages.profile.MyProfilePage;
import pages.waiter.WaiterMainPage;

public class HeaderGeneralDropDownMenuPageComponent {

    private final WebDriver driver;

    public HeaderGeneralDropDownMenuPageComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//div[@role='document']")
    private WebElement userMenu;

    private final WebElement waiterPanelBtn = userMenu.findElement(By.xpath(
            ".//*[text()='Waiter panel']"));
    private final WebElement logOutBtn = userMenu.findElement(By.xpath(
            ".//*[text()='Log Out']"));

    private final WebElement myProfileBtn = userMenu.findElement(By.xpath(
            ".//*[text()='My Profile']"));

    public WaiterMainPage waiterPanelAccess() {
        waiterPanelBtn.click();
        return new WaiterMainPage(driver);
    }
    public SignInPage logOut()    {
        logOutBtn.click();
        return new SignInPage(driver);
    }
    public MyProfilePage myProfileAccess() {
        myProfileBtn.click();
        return new MyProfilePage(driver);
    }
}
