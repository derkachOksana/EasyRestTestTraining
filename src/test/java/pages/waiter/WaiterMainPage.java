package pages.waiter;

import pageComponents.HeaderGeneralPageComponent;
import pageComponents.HeaderPageComponent;
import pageComponents.waiter.WaiterOrdersPageComponent;
import pages.SignInPage;

public class WaiterMainPage {
    private String orderNumber;

    public void setOrderNumber(String orderNumber)    {
        this.orderNumber = orderNumber;
    }

    private final HeaderGeneralPageComponent headerGeneralPageComponent = new HeaderGeneralPageComponent();
    private final HeaderPageComponent waiterHeader = new HeaderPageComponent();
    private final WaiterOrdersPageComponent orders = new WaiterOrdersPageComponent(orderNumber);

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
