package pages.profile.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.profile.*;

import java.util.List;

public class PanelOrderStatusComponent {

    @FindBy (xpath = "//main//header")
    private WebElement panelOrderStatus;

    public PanelOrderStatusComponent(WebElement panelOrderStatus) {
        this.panelOrderStatus = panelOrderStatus;
        PageFactory.initElements(this.panelOrderStatus, this);
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
    public void waitingForConfirmTabAccess() {
        waitingForConfirmTab.click();
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

    public AllForClientPage getAllForClientPage (WebDriver driver) {
        allTabAccess();
        return new AllForClientPage(driver);
    }
    public WaitingForConfirmForClientPage getWaitingForConfirmForClientPage (WebDriver driver) {
        waitingForConfirmTabAccess();
        return new WaitingForConfirmForClientPage(driver);
    }
    public AcceptedForClientPage getAcceptedForClientPage (WebDriver driver) {
        acceptedTabAccess();
        return new AcceptedForClientPage(driver);
    }
    public AssignedWaiterForClientPage getAssignedWaiterForClientPage (WebDriver driver) {
        assignedWaiterTabAccess();
        return new AssignedWaiterForClientPage(driver);
    }
    public InProgressForClientPage getInProgressForClientPage (WebDriver driver) {
        inProgressTabAccess();
        return new InProgressForClientPage(driver);
    }

}
