package pageComponents.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class WaiterOrderInfoPageComponent {
    private final WebElement orderInfo;

    public WaiterOrderInfoPageComponent(WebElement orderInfo)   {
        PageFactory.initElements(orderInfo, this);
        this.orderInfo = orderInfo;
    }

    public void startOrderBtnClick()    {
        orderInfo.findElement(By.xpath(
                "./button[contains(., 'Start order')]"))
                .click();
    }

    public void closeOrderBtnClick()    {
        orderInfo.findElement(By.xpath(
                "./button[contains(., 'Close order')]"))
                .click();
    }
}
