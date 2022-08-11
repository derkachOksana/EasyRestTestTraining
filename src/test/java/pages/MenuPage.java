package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    @FindBy(xpath = "//button[contains(@aria-label, 'Remove')]")
    private List <WebElement> deleteItems;



    public MenuPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        headerGlobal = new HeaderGeneralPageComponent(driver);
        orderConfirmation = new OrderConfirmationPageComponent(driver);
        menuItems = new MenuItemsPageComponent(driver);
    }

    public void submitOrder() {
        submitOrder.click();
    }
    public boolean submitOrderEnable() {
        if (submitOrder.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }
    public void deleteBtnClick() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int i = 0; i < deleteItems.size(); i++) {
            Thread.sleep(1000);
            if(deleteItems.get(i).isEnabled()) {
                deleteItems.get(i).click();
            } else {
                break;
            }
        }
    }
}