package pageComponents.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.waiter.WaiterAssignedWaiterPage;
import pages.waiter.WaiterHistoryPage;
import pages.waiter.WaiterInProgressPage;
import pages.waiter.WaiterMainPage;

public class WaiterHeaderPageComponent {

    private final WebDriver driver;

    public WaiterHeaderPageComponent(WebDriver driver)    {
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
}
