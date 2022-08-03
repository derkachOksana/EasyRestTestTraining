package pageComponents.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderSummaryPageComponent {

    public OrderSummaryPageComponent(WebDriver driver)  {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table//tbody//tr")
    private List<WebElement> itemsInOrder;

    @FindBy(xpath = "//table//*[text()='Total:']/../td[3]")
    private WebElement totalOrderPrice;

    private WebElement getItem(String itemName)   {
        WebElement itemInfo = null;
        for(WebElement item : itemsInOrder) {
            if (itemName.equals(item.findElement(By.xpath("//td[2]")).getText()))   {
                itemInfo = item;
            }
        }
        return itemInfo;
    }

    public void removeItemByName(String itemName)    {
        getItem(itemName)
                .findElement(By.xpath(
                        "./td[1]/button"))
                .click();
    }

    public String getVolumeByItemName(String itemName)   {
        return getItem(itemName)
                .findElement(By.xpath(
                        "./td[3]"))
                .getText();
    }

    public String getPriceByItemName(String itemName)   {
        return getItem(itemName)
                .findElement(By.xpath(
                        "./td[4]"))
                .getText();
    }

    public String getQuantityByItemName(String itemName)    {
        return getItem(itemName)
                .findElement(By.xpath(
                        "./td[5]"))
                .getText();
    }

    public String getTotalPriceByItemName(String itemName)  {
        return getItem(itemName)
                .findElement(By.xpath(
                        "./td[6]"))
                .getText();
    }

    public String getTotalOrderPrice()  {
        return totalOrderPrice.getText();
    }
}
