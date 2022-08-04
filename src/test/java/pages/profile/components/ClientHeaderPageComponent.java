package pages.profile.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ClientHeaderPageComponent {
    private final WebDriver driver;

    public ClientHeaderPageComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//main//header")
    private WebElement header;

    private final WebElement waitingForConfirmTab = header.findElement(By.xpath(
            ".//a[@href='/profile/current_orders/Waiting for confirm']"));
    private final WebElement allTab = header.findElement(By.xpath(".//a[contains(., 'All')]"));
    private final WebElement acceptedTab = header
            .findElement (By.xpath(".//a[@href = '/profile/current_orders/Accepted']"));
    private final WebElement assignedWaiterTab = header
            .findElement(By.xpath(".//a[@href = '/profile/current_orders/Assigned waiter']"));
    private final WebElement inProgressTab = header
            .findElement(By.xpath(".//a[@href = '/profile/current_orders/In progress']"));
    private final WebElement historyTab = header
            .findElement(By.xpath(".//a[@href = '/profile/order_history/History']"));
    private final WebElement declinedTab = header
            .findElement(By.xpath(".//a[@href = '/profile/order_history/Declined']"));

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
    public void historyTabAccess() {
        historyTab.click();
    }
    public void declinedTabTabAccess() {
        declinedTab.click();
    }
}
