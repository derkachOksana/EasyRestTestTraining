package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.menu.MenuItemsPageComponent;
import pageComponents.menu.OrderConfirmationPageComponent;

import java.util.List;

public class MenuPage {

    public final HeaderGeneralPageComponent headerGlobal;
    public final OrderConfirmationPageComponent orderConfirmation;
    public final MenuItemsPageComponent menuItems;

    @FindBy(xpath = "//span[contains(text(), 'Submit order')]")
    private WebElement submitOrder;


    public MenuPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        headerGlobal = new HeaderGeneralPageComponent(driver);
        orderConfirmation = new OrderConfirmationPageComponent(driver);
        menuItems = new MenuItemsPageComponent(driver);
    }

    public void submitOrder() {
        submitOrder.click();
    }
}