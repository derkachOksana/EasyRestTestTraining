package pageComponents.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WaiterOrdersPageComponent {

    private String orderNumber;

    public void setOrderNumber(String orderNumber)  {
        this.orderNumber = orderNumber;
    }

    public final WaiterOrderInfoPageComponent orderInfo = expandOrder();
    @FindBy(xpath = "//main/div/div")
    private List<WebElement> orders;

    public WaiterOrdersPageComponent(WebDriver driver)  {
        PageFactory.initElements(driver, this);
    }

    private WebElement getNeededOrder() {
        WebElement neededOrder = orders.get(0);
        for(WebElement order : orders)  {
            String fullInfo = order.findElement(By.xpath(
                            ".//*[contains(text(), '" + orderNumber + "')]"))
                    .getAttribute("textContent");
            if(fullInfo.substring(0, 4).equals(orderNumber))    {
                neededOrder = order;
                break;
            }
        }
        return neededOrder;
    }

    public String getOrderStatus()    {
        return getNeededOrder().findElement(By.xpath(
                ".//*[@role='button']"))
                .getAttribute("textContent");
    }

    private WaiterOrderInfoPageComponent expandOrder()   {
        getNeededOrder().findElement(By.xpath(
                ".//*[button]"))
                .click();
        return new WaiterOrderInfoPageComponent(getNeededOrder()
                .findElement(By.xpath(
                        "./div/div[2]")));
    }
}
