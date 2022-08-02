package pageComponents.moderator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.waiter.WaiterAssignedWaiterPage;
import pages.waiter.WaiterHistoryPage;
import pages.waiter.WaiterInProgressPage;
import pages.waiter.WaiterMainPage;

public class ModeratorHeaderPageComponent {

    public ModeratorHeaderPageComponent(WebDriver driver)    {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//main//header")
    private WebElement header;

    private final WebElement moderatorAllTab = header.findElement(By.xpath(
            ".//button//*[text()[contains(., 'All')]]"));

    private final WebElement moderatorUnapprovedTab = header.findElement(By.xpath(
            ".//button//*[text()[contains(., 'Unapproved')]]"));

    private final WebElement moderatorApprovedTab = header.findElement(By.xpath(
            ".//button//*[text()[contains(., 'Approved')]]"));

    private final WebElement moderatorArchivedTab = header.findElement(By.xpath(
            ".//button//*[text()[contains(., 'Archived')]]"));

    private final WebElement moderatorActiveTab = header.findElement(By.xpath(
            ".//button//*[text()[contains(., 'Active')]]"));

    private final WebElement moderatorBannedTab = header.findElement(By.xpath(
            ".//button//*[text()[contains(., 'Banned')]]"));

    public void moderatorAllTabAccess()  {
        moderatorAllTab.click();
    }

    public void moderatorUnapprovedTabAccess()  {
        moderatorUnapprovedTab.click();
    }

    public void moderatorApprovedTabAccess()    {
        moderatorApprovedTab.click();
    }

    public void moderatorArchivedTabAccess()    {
        moderatorArchivedTab.click();
    }

    public void moderatorActiveTabAccess()  {
        moderatorArchivedTab.click();
    }

    public void moderatorBannedTabAccess()  {
        moderatorBannedTab.click();
    }
}
