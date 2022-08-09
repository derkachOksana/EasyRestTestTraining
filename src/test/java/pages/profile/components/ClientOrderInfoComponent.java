package pages.profile.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ClientOrderInfoComponent {
    private final WebDriver driver;

    public ClientOrderInfoComponent(WebDriver driver)   {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//span[contains(text(), 'Decline')]")
    private List<WebElement> setOfDeclineBtns;

    @FindBy(xpath = "//span[contains(text(), 'Reorder')]")
    private List<WebElement> setOfReordersBtns;

    public void declineBtnClick() {
        for(WebElement declineBtn : setOfDeclineBtns) {
            if (declineBtn.isDisplayed()) {
                declineBtn.click();
            }
        }
    }
    public void reorderBtnClick() {
        for(WebElement reorderBtn : setOfReordersBtns) {
            if (reorderBtn.isDisplayed()) {
                reorderBtn.click();
            }
        }
    }
}
