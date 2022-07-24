package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfileOrderHistoryPage {
    private final WebDriver driver;

    public ProfileOrderHistoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[contains(., 'All')]")
    private WebElement allTab;

    @FindBy(xpath = "//a[@href = '/profile/order_history/History'])")
    private WebElement historyTab;

    @FindBy (xpath = "//a[@href = '/profile/order_history/Declined']")
    private WebElement declinedTab;

    @FindBy(xpath = "//span[contains(text(), 'Reorder')]")
    List<WebElement> setOfReordersBtns;

    public void allTabAccess() {
        allTab.click();
    }
    public void historyTabAccess() {
        historyTab.click();
    }
    public void declinedTabTabAccess() {
        declinedTab.click();
    }
    public void reorderBtnAccessByIndex(int index) {
        int i = 0;
        try {
            for (WebElement element : setOfReordersBtns) {
                i++;
                if (index == i) {
                    element.click();
                }
            }
        } catch (Exception e) {
            System.out.println("Not expected index");
        }
    }
}
