package pages.profile.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ClientOrderInfoComponent {
    private final WebDriver driver;
    private WebDriverWait wait;

    public ClientOrderInfoComponent(WebDriver driver)   {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//span[contains(text(), 'Decline')]")
    private List<WebElement> setOfDeclineBtns;

    @FindBy(xpath = "//span[contains(text(), 'Reorder')]")
    private List<WebElement> setOfReordersBtns;

    @FindBy(xpath = "//th/p[1]")
    private WebElement itemName;

    public void declineBtnClick(Duration duration) {
        wait = new WebDriverWait(driver, duration);
        for(WebElement declineBtn : setOfDeclineBtns) {
            wait.until(ExpectedConditions.elementToBeClickable(declineBtn));
                if (declineBtn.isDisplayed()) {
                    declineBtn.click();
                    break;
                }
        }
    }

    public void reorderBtnClick() {
        for(WebElement reorderBtn : setOfReordersBtns) {
                if (reorderBtn.isDisplayed()) {
                    reorderBtn.click();
                    break;
                }
        }
    }
    public String itemNameOrder (Duration duration) {
        wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOf(itemName));
        return itemName.getText();
    }
}
