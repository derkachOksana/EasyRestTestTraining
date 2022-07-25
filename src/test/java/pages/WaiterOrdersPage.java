package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WaiterOrdersPage {
    private final WebDriver driver;
    private String orderNumber;

    public WaiterOrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setOrderNumber(String orderNumber)    {
        this.orderNumber = orderNumber;
    }

    @FindBy(xpath = "//div[contains(@class, 'UserMenu')]/*[button]")
    private WebElement userIconBtn;

    @FindBy(xpath = "//a[text()='Waiter panel']")
    private WebElement waiterPanelAccessBtn;

    @FindBy(xpath = "//a[@href='/waiter/orders/']")
    private WebElement allTab;

    @FindBy(xpath = "//a[@href='/waiter/orders/Assigned waiter']")
    private WebElement assignedWaiterTab;

    @FindBy(xpath = "//a[@href='/waiter/orders/In progress']")
    private WebElement inProgressTab;

    @FindBy(xpath = "//a[@href='/waiter/orders/History']")
    private WebElement historyTab;

    @FindBy(xpath = "//button[contains(., 'Close order')]")
    private WebElement closeOrderBtn;

    @FindBy(xpath = "//button[contains(., 'Start order')]")
    private WebElement startOrderBtn;

    public String getOrderStatus()  {
        return driver.findElement(By.xpath("//div[contains(@class, 'MuiCard')]" +
                "/*[contains(text(), '" + orderNumber + "')]" +
                "/ancestor::div[3]" +
                "//*[@role='button']")).getAttribute("textContent");
    }

    public void orderExpandAccess() {
        driver.findElement(By.xpath("//div[contains(@class, 'MuiCard')]" +
                "/*[contains(text(), '" + orderNumber + "')]" +
                "/ancestor::div[3]" +
                "//*[button]")).click();
    }

    public void userMenuAccess()    {
        userIconBtn.click();
    }

    public void waiterPanelSheetAccess() {
        waiterPanelAccessBtn.click();
    }

    public void assignedWaiterTabAccess()    {
        assignedWaiterTab.click();
    }

    public void inProgressTabAccess()   {
        inProgressTab.click();
    }

    public void allTabAccess()  {
        allTab.click();
    }

    public void historyTabAccess()  {
        historyTab.click();
    }

    public void startOrder()    {
        startOrderBtn.click();
    }

    public void closeOrder()    {
        closeOrderBtn.click();
    }

}
