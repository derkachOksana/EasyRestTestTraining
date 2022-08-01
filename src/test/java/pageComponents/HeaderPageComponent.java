package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.waiter.WaiterAssignedWaiterPage;
import pages.waiter.WaiterHistoryPage;
import pages.waiter.WaiterInProgressPage;
import pages.waiter.WaiterMainPage;

public class HeaderPageComponent {

    private WebDriver driver;

    public HeaderPageComponent(WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//main//header")
    private WebElement header;

    private final WebElement waiterAssignedLink = header.findElement(By.xpath(
            "./a[@href='/waiter/orders/Assigned waiter']"));

    private final WebElement waiterInProgressLink = header.findElement(By.xpath(
            "./a[@href='/waiter/orders/In progress']"));

    private final WebElement waiterHistoryLink = header.findElement(By.xpath(
            "./a[@href='/waiter/orders/History']"));

    private final WebElement waiterAllLink = header.findElement(By.xpath(
            "./a[@href='/waiter/orders/']"));

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

    public WaiterAssignedWaiterPage waiterAssignedWaiterPageAccess()  {
        waiterAssignedLink.click();
        return new WaiterAssignedWaiterPage(driver);
    }

    public WaiterHistoryPage waiterHistoryPageAccess()    {
        waiterHistoryLink.click();
        return new WaiterHistoryPage(driver);
    }

    public WaiterInProgressPage waiterInProgressPageAccess()  {
        waiterInProgressLink.click();
        return new WaiterInProgressPage(driver);
    }

    public WaiterMainPage waiterMainPageAccess()  {
        waiterAllLink.click();
        return new WaiterMainPage(driver);
    }

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
