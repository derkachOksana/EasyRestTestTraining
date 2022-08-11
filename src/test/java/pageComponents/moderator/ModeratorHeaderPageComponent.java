package pageComponents.moderator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.moderator.ModeratorOwnersPage;
import pages.moderator.ModeratorRestaurantsPage;
import pages.waiter.WaiterAssignedWaiterPage;
import pages.waiter.WaiterHistoryPage;
import pages.waiter.WaiterInProgressPage;
import pages.waiter.WaiterMainPage;

public class ModeratorHeaderPageComponent {

    private final WebDriver driver;

    private final JavascriptExecutor js;

    public ModeratorHeaderPageComponent(WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        js = (JavascriptExecutor)driver;
    }

    @FindBy(xpath = "//main//header//button//*[text()[contains(., 'All')]]")
    private WebElement moderatorAllTab;

    @FindBy(xpath = "//main//header//button//*[text()[contains(., 'Unapproved')]]")
    private WebElement moderatorUnapprovedTab;

    @FindBy(xpath = "//main//header//button//*[text()[contains(., 'Approved')]]")
    private WebElement moderatorApprovedTab;

    @FindBy(xpath = "//main//header//button//*[text()[contains(., 'Archived')]]")
    private WebElement moderatorArchivedTab;

    @FindBy(xpath = "//main//header//button//*[text()[contains(., 'Active')]]")
    private WebElement moderatorActiveTab;

    @FindBy(xpath = "//main//header//button//*[text()[contains(., 'Banned')]]")
    private WebElement moderatorBannedTab;

    public ModeratorRestaurantsPage moderatorAllTabAccess()  {
        js.executeScript("window.scrollTo(0,0)");
        moderatorAllTab.click();
        return new ModeratorRestaurantsPage(driver);
    }

    public ModeratorRestaurantsPage moderatorUnapprovedTabAccess()  {
        js.executeScript("window.scrollTo(0,0)");
        moderatorUnapprovedTab.click();
        return new ModeratorRestaurantsPage(driver);
    }

    public ModeratorRestaurantsPage moderatorApprovedTabAccess()    {
        js.executeScript("window.scrollTo(0,0)");
        moderatorApprovedTab.click();
        return new ModeratorRestaurantsPage(driver);
    }

    public ModeratorRestaurantsPage moderatorArchivedTabAccess()    {
        js.executeScript("window.scrollTo(0,0)");
        moderatorArchivedTab.click();
        return new ModeratorRestaurantsPage(driver);
    }

    public void moderatorActiveTabAccess()  {
        moderatorActiveTab.click();
    }

    public void moderatorBannedTabAccess()  {
        moderatorBannedTab.click();
    }
}
