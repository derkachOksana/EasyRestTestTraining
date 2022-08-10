package pages.profile.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ClientHeaderPageComponent {
    private final WebDriver driver;
    public final ClientOrdersContainerComponent ordersContainer;

    public ClientHeaderPageComponent(WebDriver driver) {
        this.driver = driver;
        ordersContainer = new ClientOrdersContainerComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/profile/current_orders/Waiting for confirm']")
    public WebElement waitingForConfirmTab;
    @FindBy(xpath = "//a[contains(., 'All')]")
    public WebElement allTab;
    @FindBy(xpath = "//a[@href = '/profile/current_orders/Accepted']")
    public WebElement acceptedTab;
    @FindBy(xpath = "//a[@href = '/profile/current_orders/Assigned waiter']")
    public WebElement assignedWaiterTab;
    @FindBy(xpath = "//a[@href = '/profile/current_orders/In progress']")
    public WebElement inProgressTab;
    @FindBy(xpath = "//a[@href = '/profile/order_history/History']")
    public WebElement historyTab;
    @FindBy(xpath = "//a[@href = '/profile/order_history/Declined']")
    public WebElement declinedTab;

    //left or right arrow of the order`s status
    @FindBy(xpath = "//header//button/*[@role = 'presentation']")
    private List<WebElement> presentationStatusBtns;

    public void presentationStatusBtnAccessByIndex(int index) throws IllegalArgumentException{
        int size = presentationStatusBtns.size();
        if(index >= size) {
            throw new IllegalArgumentException("Unexpected index [" + index + "]. Expect index should not be grater then " + size);
        }
        presentationStatusBtns.get(index).click();
    }

    public void allTabAccess() {
        allTab.click();
    }
    public ClientOrdersContainerComponent waitingForConfirmTabAccess() {

        ClientOrdersContainerComponent ordersContainer= new ClientOrdersContainerComponent(driver);
        waitingForConfirmTab.click();
        return ordersContainer;
    }
    public void acceptedTabAccess() {
        acceptedTab.click();
    }
    public void assignedWaiterTabAccess() {
        assignedWaiterTab.click();
    }
    public void inProgressTabAccess() {
        inProgressTab.click();
    }
    public void historyTabAccess() {
        historyTab.click();
    }
    public void declinedTabAccess() { declinedTab.click(); }
}
