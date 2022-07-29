package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.SignInPage;
import pages.waiter.WaiterMainPage;

public class HeaderGeneralDropDownMenuPageComponent {
    @FindBy(xpath = "//div[@role='document']")
    private WebElement userMenu;

    private final WebElement waiterPanelBtn = userMenu.findElement(By.xpath(
            ".//*[text()='Waiter panel']"));

    private final WebElement logOutBtn = userMenu.findElement(By.xpath(
            ".//*[text()='Log Out']"));

    public WaiterMainPage waiterPanelAccess() {
        waiterPanelBtn.click();
        return new WaiterMainPage();
    }

    public SignInPage logOut()    {
        logOutBtn.click();
        return new SignInPage();
    }
}
