package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfileCurrentOrdersPage {
    private final WebDriver driver;

    public ProfileCurrentOrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//a[contains(., 'All')]")
    private WebElement allTab;

    @FindBy(xpath = "//a[@href = '/profile/current_orders/Waiting for confirm']")
    private WebElement waitingForConfirmTab;

    @FindBy(xpath = "//a[@href = '/profile/current_orders/Accepted']")
    private WebElement acceptedTab;

    @FindBy(xpath = "//a[@href = '/profile/current_orders/Assigned waiter']")
    private WebElement assignedWaiterTab;

    @FindBy(xpath = "//a[@href = '/profile/current_orders/In progress']")
    private WebElement inProgressTab;

    @FindBy(xpath = "//header//button/*[@role = 'presentation']")
    List<WebElement> presentationBtns;

    public void presentationBtnsSelectByIndex(int index) {
        int i = 0;
        try {
            for (WebElement element : presentationBtns) {
                i++;
                if (index == i) {
                    element.click();
                }
            }
        } catch (Exception e) {
            System.out.println("Not expected index");
        }

    }
    @FindBy (xpath = "//div[@role = 'button']/div[@role = 'button']")
    List<WebElement> presentationBtnsOrder;



}
