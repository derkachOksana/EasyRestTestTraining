package pageComponents.menu;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderSummaryPageComponent {
    private WebDriverWait wait;
    private WebDriver driver;
    public OrderSummaryPageComponent(WebDriver driver)  {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table//tbody//tr[1]")
    private List<WebElement> itemsInOrder;

    //@FindBy(xpath = "//table//*[text()='Total:']/../td[3]")
    @FindBy(xpath = "//table//tbody/tr[2]/td[3]")
    private WebElement totalOrderPrice;

    @FindBy(xpath = "//table//tbody/tr[1]/td[2]")
    private WebElement itemNameFirstPosition;

    private WebElement getItem(String itemName)   {
        WebElement itemInfo = null;
        for(WebElement item : itemsInOrder) {
            if (itemName.equals(item.findElement(By.xpath("//td[2]")).getText()))   {
                itemInfo = item;
            }
        }
        return itemInfo;
    }

    public void removeItemByName(String itemName, Duration duration)    {
        wait = new WebDriverWait(driver, duration);
        for (int i = 0; i <= 2; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table//tbody//tr/td[1]/button")));
                break;
            } catch (Exception e) {

            }
        }
        getItem(itemName)
                .findElement(By.xpath(
                        "./td[1]/button"))
                .click();
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
                        "./td[5]//input"))
                .getAttribute("value");
    }

    public String getTotalPriceByItemName(String itemName)  {
        return getItem(itemName)
                .findElement(By.xpath(
                        "./td[6]"))
                .getText();
    }

    public String getTotalOrderPrice() {
        String totalPrice = totalOrderPrice.getText();
        return totalPrice;
    }

    public String itemName (String itemName) {
        try {
            return getItem(itemName)
                    .findElement(By.xpath(
                            "./td[2]")).getText();
        } catch (Exception e){
            return  null;
        }
    }
    public boolean displayedItemName (Duration duration) {
        wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOf(itemNameFirstPosition));
        boolean displayedItemName = itemNameFirstPosition.isDisplayed();
        return displayedItemName;
    }
}
