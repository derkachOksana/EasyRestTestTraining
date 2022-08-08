package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.SignInPage;
import pages.waiter.WaiterMainPage;

public class HeaderGeneralDropDownMenuPageComponent {

    private final WebDriver driver;

    public HeaderGeneralDropDownMenuPageComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[text()='Waiter panel']")
    private WebElement waiterPanelBtn;

    @FindBy(xpath = "//*[text()='Log Out']")
    private WebElement logOutBtn;

    public WaiterMainPage waiterPanelAccess() {
        waiterPanelBtn.click();
        return new WaiterMainPage(driver);
    }

    public SignInPage logOut()    {
        logOutBtn.click();
        return new SignInPage(driver);
    }
}
