/*
        *************************************************************
        *                                                           *
        *   Change methods to more abstract view                    *
        *   Change methods to return page component, not whole page *
        *                                                           *
        *************************************************************
*/

package pageComponents.moderator;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.moderator.ModeratorOwnersPage;
import pages.moderator.ModeratorRestaurantsPage;
import pages.moderator.ModeratorUsersPage;

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

    public ModeratorRestaurantsPage moderatorAllRestaurantsTabAccess()  {
        js.executeScript("window.scrollTo(0,0)");
        moderatorAllTab.click();
        return new ModeratorRestaurantsPage(driver);
    }

    public ModeratorRestaurantsPage moderatorUnapprovedRestaurantsTabAccess()  {
        js.executeScript("window.scrollTo(0,0)");
        moderatorUnapprovedTab.click();
        return new ModeratorRestaurantsPage(driver);
    }

    public ModeratorRestaurantsPage moderatorApprovedRestaurantsTabAccess()    {
        js.executeScript("window.scrollTo(0,0)");
        moderatorApprovedTab.click();
        return new ModeratorRestaurantsPage(driver);
    }

    public ModeratorRestaurantsPage moderatorArchivedRestaurantsTabAccess()    {
        js.executeScript("window.scrollTo(0,0)");
        moderatorArchivedTab.click();
        return new ModeratorRestaurantsPage(driver);
    }

    public ModeratorUsersPage moderatorActiveUsersTabAccess()  {
        js.executeScript("window.scrollTo(0,0)");
        moderatorActiveTab.click();
        return new ModeratorUsersPage(driver);
    }

    public ModeratorUsersPage moderatorBannedUsersTabAccess()  {
        js.executeScript("window.scrollTo(0,0)");
        moderatorBannedTab.click();
        return new ModeratorUsersPage(driver);
    }

    public ModeratorUsersPage moderatorAllUsersTabAccess()  {
        js.executeScript("window.scrollTo(0,0)");
        moderatorAllTab.click();
        return new ModeratorUsersPage(driver);
    }

    public ModeratorOwnersPage moderatorActiveOwnersTabAccess() {
        js.executeScript("window.scrollTo(0,0)");
        moderatorActiveTab.click();
        return new ModeratorOwnersPage(driver);
    }

    public ModeratorOwnersPage moderatorBannedOwnersTabAccess() {
        js.executeScript("window.scrollTo(0,0)");
        moderatorBannedTab.click();
        return new ModeratorOwnersPage(driver);
    }

    public ModeratorOwnersPage moderatorAllOwnersTabAccess() {
        js.executeScript("window.scrollTo(0,0)");
        moderatorAllTab.click();
        return new ModeratorOwnersPage(driver);
    }
}
