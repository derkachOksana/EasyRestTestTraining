package tests.waiter;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.SignInPage;
import pages.waiter.WaiterAssignedWaiterPage;
import pages.waiter.WaiterHistoryPage;
import pages.waiter.WaiterInProgressPage;
import pages.waiter.WaiterMainPage;
import tests.BaseTest;
import utility.ConfProperties;
import utility.DataBaseConnection;
import utility.RegistrationData;

import java.time.Duration;

public class WaiterOrderStatusTest extends BaseTest    {

    private SignInPage signInPage;
    private WaiterMainPage waiterMainPage;

    private RegistrationData waiter;
    private int firstOrderNumber;
    private int secondOrderNumber;

    private final Duration DURATION = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("duration")));

    @BeforeClass
    public void preconditions() {
        final int WAITER_ROLE_ID = 6;
        waiter = DataBaseConnection.getInstance().createAccByRole(driver, WAITER_ROLE_ID);
        firstOrderNumber = DataBaseConnection.getInstance().createOrderID(waiter);
        secondOrderNumber = DataBaseConnection.getInstance().createOrderID(waiter);
        signInPage = new SignInPage(driver);
    }

    @BeforeMethod
    public void waiterLogIn()    {
        signInPage.setUserEmailInputField(waiter.getEmail());
        signInPage.setUserPasswordInputField(waiter.getPassword());
        signInPage.clickSignInBtn();
        waiterMainPage = new WaiterMainPage(driver);
    }

    @Test
    public void waiterOrderStatusTest601()  {
        String orderStatus;

        WaiterAssignedWaiterPage waiterAssignedWaiter = waiterMainPage
                .waiterHeader
                .waiterAssignedWaiterPageAccess(DURATION);

        waiterAssignedWaiter.orders.expandOrder(firstOrderNumber).startOrderBtnClick();

        WaiterInProgressPage waiterInProgressPage = waiterAssignedWaiter
                .waiterHeader
                .waiterInProgressPageAccess(DURATION);

        orderStatus = waiterInProgressPage.orders.getOrderStatus(firstOrderNumber);

        Assert.assertEquals(orderStatus, "In progress");
    }

    @Test
    public void waiterOrderStatusTest602()  {
        String orderStatus;

        WaiterInProgressPage waiterInProgressPage = waiterMainPage
                .waiterHeader
                .waiterInProgressPageAccess(DURATION);

        waiterInProgressPage.orders.expandOrder(firstOrderNumber).closeOrderBtnClick();

        WaiterHistoryPage waiterHistoryPage = waiterInProgressPage
                .waiterHeader
                .waiterHistoryPageAccess(DURATION);

        orderStatus = waiterHistoryPage.orders.getOrderStatus(firstOrderNumber);

        Assert.assertEquals(orderStatus, "History");
    }

    @Test
    public void waiterOrderStatusTest603()  {
        String orderStatus;

        waiterMainPage = waiterMainPage.waiterHeader.waiterMainPageAccess(DURATION);

        waiterMainPage.orders.expandOrder(secondOrderNumber).startOrderBtnClick();

        WaiterInProgressPage waiterInProgressPage = waiterMainPage
                .waiterHeader
                .waiterInProgressPageAccess(DURATION);

        orderStatus = waiterInProgressPage.orders.getOrderStatus(secondOrderNumber);

        Assert.assertEquals(orderStatus, "In progress");
    }

    @Test
    public void waiterOrderStatusTest604()  {
        String orderStatus;

        waiterMainPage = waiterMainPage.waiterHeader.waiterMainPageAccess(DURATION);

        waiterMainPage.orders.expandOrder(secondOrderNumber).closeOrderBtnClick();

        WaiterHistoryPage waiterHistoryPage = waiterMainPage
                .waiterHeader
                .waiterHistoryPageAccess(DURATION);

        orderStatus = waiterHistoryPage.orders.getOrderStatus(secondOrderNumber);

        Assert.assertEquals(orderStatus, "History");
    }

    @AfterMethod
    public void waiterLogOut()  {
        signInPage = waiterMainPage
                .headerGeneralPageComponent
                .userMenu()
                .logOut();
    }

    @AfterClass
    public void postconditions()    {
        DataBaseConnection.getInstance().deleteOrderByID(firstOrderNumber);
        DataBaseConnection.getInstance().deleteOrderByID(secondOrderNumber);
        DataBaseConnection.getInstance().deleteUserByEmail(waiter.getEmail());
    }
}