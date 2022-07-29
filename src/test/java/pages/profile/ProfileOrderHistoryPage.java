package pages.profile;

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

    @FindBy(xpath = "//a[@href = '/profile/order_history/History']")
    private WebElement historyTab;

    @FindBy (xpath = "//a[@href = '/profile/order_history/Declined']")
    private WebElement declinedTab;

    @FindBy(xpath = "//span[contains(text(), 'Reorder')]")
    private List<WebElement> setOfReordersBtns;

    public void allTabAccess() {
        allTab.click();
    }
    public void historyTabAccess() {
        historyTab.click();
    }
    public void declinedTabTabAccess() {
        declinedTab.click();
    }
    public void reorderBtnAccessByIndex(int index) throws IllegalArgumentException {

        int size = setOfReordersBtns.size();

        if(index >= size) {
            throw new IllegalArgumentException("Unexpected index [" + index + "]. Expect index should not be grater then " + size);
        }

        setOfReordersBtns.get(index).click();
    }
}
