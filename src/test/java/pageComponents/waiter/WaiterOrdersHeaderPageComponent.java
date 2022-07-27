package pageComponents.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.waiter.WaiterAssignedWaiterPage;
import pages.waiter.WaiterHistoryPage;
import pages.waiter.WaiterInProgressPage;
import pages.waiter.WaiterMainPage;

public class WaiterOrdersHeaderPageComponent {
    @FindBy(xpath = "//main/header")
    private WebElement header;

    private WebElement assignedWaiterLink = header.findElement(By.xpath(
            "./a[@href='/waiter/orders/Assigned waiter']"));

    private WebElement inProgressLink = header.findElement(By.xpath(
            "./a[@href='/waiter/orders/In progress']"));

    private WebElement historyLink = header.findElement(By.xpath(
            "./a[@href='/waiter/orders/History']"));

    private WebElement allLink = header.findElement(By.xpath(
            "./a[@href='/waiter/orders/']"));

    public WaiterAssignedWaiterPage waiterAssignedWaiterPageAccess()  {
        assignedWaiterLink.click();
        return new WaiterAssignedWaiterPage();
    }

    public WaiterHistoryPage waiterHistoryPageAccess()    {
        historyLink.click();
        return new WaiterHistoryPage();
    }

    public WaiterInProgressPage waiterInProgressPageAccess()  {
        inProgressLink.click();
        return new WaiterInProgressPage();
    }

    public WaiterMainPage waiterMainPageAccess()  {
        allLink.click();
        return new WaiterMainPage();
    }
}
