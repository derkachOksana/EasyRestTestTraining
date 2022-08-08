package pageComponents.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MenuItemsPageComponent {

    private WebElement neededItemBlock;
    private List<WebElement> itemAttributeList;

    public MenuItemsPageComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'direction')]/div/div")
    private List<WebElement> itemList;


    private void getBlockByItemName(String itemName)  {
        for(WebElement item : itemList) {
            if(item.findElement(By.xpath(
                            ".//div[contains(@class, 'align-items')]//span"))
                    .getText()
                    .equals(itemName))  {
                neededItemBlock = item;
                itemAttributeList = item.findElements(By.xpath(".//p"));
            }
        }
    }


    public String getFoodMassByItemName(String itemName) {
        getBlockByItemName(itemName);
        return itemAttributeList.get(0).getText();
    }

    public String getPriceByItemName(String itemName) {
        getBlockByItemName(itemName);
        return itemAttributeList.get(1).getText();
    }

    public String getQuantityByItemName(String itemName) {
        getBlockByItemName(itemName);
        return neededItemBlock.findElement(By.xpath(
                ".//input")).getAttribute("value");
    }

    public String getFinalPriceByItemName(String itemName) {
        getBlockByItemName(itemName);
        return itemAttributeList.get(2).getText();
    }

    public void addToCartByItemName(String itemName) {
        getBlockByItemName(itemName);
        neededItemBlock.findElement(By.xpath(
                        ".//button[contains(@class, 'addButton')]"))
                .click();
    }
}
