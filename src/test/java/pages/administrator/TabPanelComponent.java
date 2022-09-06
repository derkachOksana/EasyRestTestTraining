package pages.administrator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TabPanelComponent {

    private final WebElement tabDiv;

    @FindBy(xpath = ".//button[contains(.,'Waiting for confirm')]")
    private WebElement waitingForConfirmTab;

    @FindBy(xpath = ".//button[contains(.,'Accepted')]")
    private WebElement acceptedTab;

    @FindBy(xpath = ".//button[contains(.,'Assigned waiter')]")
    private WebElement assignedWaiterTab;

    public TabPanelComponent(WebElement tabDiv ) {
        this.tabDiv = tabDiv;
        PageFactory.initElements(this.tabDiv, this);
    }

    public void switchToWaitingForConfirmTab() {
        waitingForConfirmTab.click();
    }

    public void switchToAcceptedTab() {
        acceptedTab.click();
    }

    public void switchToAssignedWaiterTab() {
        assignedWaiterTab.click();
    }

}
