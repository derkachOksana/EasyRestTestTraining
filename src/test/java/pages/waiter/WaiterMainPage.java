package pages.waiter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.HeaderPageComponent;
import pageComponents.waiter.WaiterOrdersPageComponent;
import pages.SignInPage;

public class WaiterMainPage {
    protected String orderNumber;

    protected WebDriver driver;

    public void setOrderNumber(String orderNumber)    {
        this.orderNumber = orderNumber;
    }

    public WaiterMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected final HeaderGeneralPageComponent headerGeneralPageComponent = new HeaderGeneralPageComponent(driver);
    protected final HeaderPageComponent waiterHeader = new HeaderPageComponent(driver);
    protected final WaiterOrdersPageComponent orders = new WaiterOrdersPageComponent(driver, orderNumber);

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
