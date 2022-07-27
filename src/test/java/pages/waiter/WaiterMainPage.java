package pages.waiter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.waiter.WaiterOrderInfoPageComponent;
import pageComponents.waiter.WaiterOrdersHeaderPageComponent;
import pageComponents.waiter.WaiterOrdersPageComponent;

public class WaiterMainPage {
    private String orderNumber;

    public void setOrderNumber(String orderNumber)    {
        this.orderNumber = orderNumber;
    }

    @FindBy(xpath = "//div[contains(@class, 'UserMenu')]/*[button]")
    private WebElement userIconBtn;

    @FindBy(xpath = "//a[text()='Waiter panel']")
    private WebElement waiterPanelAccessBtn;

    private HeaderGeneralPageComponent headerGeneralPageComponent = new HeaderGeneralPageComponent();
    private WaiterOrdersHeaderPageComponent headerComponent = new WaiterOrdersHeaderPageComponent();
    private WaiterOrdersPageComponent ordersComponent = new WaiterOrdersPageComponent(orderNumber);
    private WaiterOrderInfoPageComponent orderInfo = ordersComponent.expandOrder();

    public void userMenuAccess()    {
        userIconBtn.click();
    }

    public void waiterPanelSheetAccess() {
        waiterPanelAccessBtn.click();
    }

    public WaiterAssignedWaiterPage assignedWaiterTabAccess()    {
        return headerComponent.waiterAssignedWaiterPageAccess();
    }

    public WaiterInProgressPage inProgressTabAccess()   {
        return headerComponent.waiterInProgressPageAccess();
    }

    public WaiterHistoryPage historyTabAccess()  {
        return headerComponent.waiterHistoryPageAccess();
    }

    public WaiterMainPage allTabAccess()    {
        return headerComponent.waiterMainPageAccess();
    }

    public String getOrderStatus()  {
        return ordersComponent.getOrderStatus();
    }

    public void closeOrder()    {
        orderInfo.closeOrderBtnClick();
    }

}
