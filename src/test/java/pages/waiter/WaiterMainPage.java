package pages.waiter;

import org.openqa.selenium.WebDriver;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.moderator.ModeratorHeaderPageComponent;
import pageComponents.waiter.WaiterHeaderPageComponent;
import pageComponents.waiter.WaiterOrdersPageComponent;
import pages.SignInPage;

public class WaiterMainPage {

    protected WebDriver driver;

    protected final HeaderGeneralPageComponent headerGeneralPageComponent;
    protected final WaiterHeaderPageComponent waiterHeader;
    protected final WaiterOrdersPageComponent orders;

    public void setOrderNumber(String orderNumber)    {
        orders.setOrderNumber(orderNumber);
    }

    public WaiterMainPage(WebDriver driver) {
        this.driver = driver;
        headerGeneralPageComponent = new HeaderGeneralPageComponent(driver);
        waiterHeader = new WaiterHeaderPageComponent(driver);
        orders = new WaiterOrdersPageComponent(driver);
    }

    public WaiterMainPage waiterPanelSheetAccess() {
        return headerGeneralPageComponent.waiterPanelAccess();
    }

    public WaiterAssignedWaiterPage assignedWaiterTabAccess()    {
        return waiterHeader.waiterAssignedWaiterPageAccess();
    }

    public WaiterInProgressPage inProgressTabAccess()   {
        return waiterHeader.waiterInProgressPageAccess();
    }

    public WaiterHistoryPage historyTabAccess()  {
        return waiterHeader.waiterHistoryPageAccess();
    }

    public WaiterMainPage allTabAccess()    {
        return waiterHeader.waiterMainPageAccess();
    }

    public String getOrderStatus()  {
        return orders.getOrderStatus();
    }

    public void closeOrder()    {
        orders.closeOrder();
    }

    public void startOrder()  {
        orders.startOrder();
    }

    public SignInPage logOut()  {
        return headerGeneralPageComponent.logOut();
    }
}
