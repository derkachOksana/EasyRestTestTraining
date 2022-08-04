package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.menu.OrderConfirmationPageComponent;

import java.util.List;

public class MenuPage {
    private WebElement neededItemBlock;
    private List<WebElement> itemAttributeList;

    public final HeaderGeneralPageComponent headerGlobal;

    public final OrderConfirmationPageComponent orderConfirmation;

    @FindBy(xpath = "//div[contains(@class, 'direction')]/div")
    private List<WebElement> itemList;

    @FindBy(xpath = "//button[@aria-label='Add to cart']")
    private List<WebElement> setOfAddToCartBtn;

    @FindBy(xpath = "//span[contains(text(), 'Submit order')]")
    private WebElement submitOrder;


    public MenuPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        headerGlobal = new HeaderGeneralPageComponent(driver);
        orderConfirmation = new OrderConfirmationPageComponent(driver);
    }

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
        return itemAttributeList.get(3).getText();
    }

    public void submitOrder() {
        submitOrder.click();
    }

    public void addToCartByItemName(String itemName) {
        getBlockByItemName(itemName);
        neededItemBlock.findElement(By.xpath(
                ".//button[contains(@class, 'addButton')]"))
                .click();
    }
}