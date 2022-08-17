package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.menu.MenuItemsPageComponent;
import pageComponents.menu.OrderConfirmationPageComponent;

import java.time.Duration;

public class MenuPage {

    public final HeaderGeneralPageComponent headerGlobal;
    public final OrderConfirmationPageComponent orderConfirmation;
    public final MenuItemsPageComponent menuItems;
    private final WebDriver driver;
    private final Actions action;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[contains(text(), 'Submit order')]")
    private WebElement submitOrder;

    @FindBy(xpath = "//button[contains(@aria-label, 'Remove')]")
    private WebElement deleteItem;

    @FindBy(xpath = "//button[contains(@aria-label, 'Show cart')]")
    private WebElement showCartBtn;

    public MenuPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        headerGlobal = new HeaderGeneralPageComponent(driver);
        orderConfirmation = new OrderConfirmationPageComponent(driver);
        menuItems = new MenuItemsPageComponent(driver);
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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

    public void showCartBtnClick() {
        showCartBtn.click();
    }

    public void deleteBtnClick() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        for (int i = 0; i <= 2; i++) {
            try {
                action.moveToElement(deleteItem).perform();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@aria-label, 'Remove')]")));
                break;
            } catch (Exception e) {

            }
            deleteItem.click();
        }
    }
}
