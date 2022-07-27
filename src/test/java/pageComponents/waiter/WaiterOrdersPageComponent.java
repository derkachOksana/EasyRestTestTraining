package pageComponents.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WaiterOrdersPageComponent {
    private WebElement neededOrder;
    @FindBy(xpath = "//main/div/div")
    private List<WebElement> orders;

    public WaiterOrdersPageComponent(String orderNumber)  {
        for(WebElement order : orders)  {
            String fullInfo = order.findElement(By.xpath(
                    ".//*[contains(text(), '" + orderNumber + "')]"))
                    .getAttribute("textContent");
            if(fullInfo.substring(0, 4).equals(orderNumber))    {
                neededOrder = order;
                break;
            }
        }
    }

    public String getOrderStatus()    {
        return neededOrder.findElement(By.xpath(
                ".//*[@role='button']"))
                .getAttribute("textContent");
    }

    public WaiterOrderInfoPageComponent expandOrder()   {
        neededOrder.findElement(By.xpath(
                ".//*[button]"))
                .click();
        return new WaiterOrderInfoPageComponent(neededOrder.findElement(By.xpath("./div/div[2]")));
    }
}
