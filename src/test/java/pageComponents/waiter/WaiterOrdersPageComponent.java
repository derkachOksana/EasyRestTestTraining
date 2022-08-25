package pageComponents.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WaiterOrdersPageComponent {

    private final WebDriver driver;

    @FindBy(xpath = "//main/div/div")
    private List<WebElement> orders;

    public WaiterOrdersPageComponent(WebDriver driver)  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private WebElement getNeededOrder(int orderNumber) {
        WebElement neededOrder = null;
        String fullOrderNumber = "№" + orderNumber;
        for(WebElement order : orders)  {
            String fullInfo = order.findElement(By.xpath(
                            ".//span[contains(@class, 'subheader')]"))
                    .getAttribute("textContent");
            if(fullInfo.split(" ")[0].equals(fullOrderNumber))    {
                neededOrder = order;
                break;
            }
        }
        return neededOrder;
    }

    public String getOrderStatus(int orderNumber)    {
        return getNeededOrder(orderNumber).findElement(By.xpath(
                ".//*[@role='button']"))
                .getAttribute("textContent");
    }

    public WaiterOrderInfoPageComponent expandOrder(int orderNumber)   {
        getNeededOrder(orderNumber).findElement(By.xpath(
                ".//*[button]"))
                .click();
        return new WaiterOrderInfoPageComponent(driver);
    }
}
