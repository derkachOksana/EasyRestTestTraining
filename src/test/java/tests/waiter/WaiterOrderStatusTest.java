package tests.waiter;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SignInPage;
import pages.waiter.WaiterAssignedWaiterPage;
import pages.waiter.WaiterHistoryPage;
import pages.waiter.WaiterInProgressPage;
import pages.waiter.WaiterMainPage;
import tests.BaseTest;

public class WaiterOrderStatusTest extends BaseTest    {

    /*
    ***************************************************

            Fields used without preconditions

    ***************************************************
    */

    private final String waiterEmail = "alexandriawright@test.com";
    private final String waiterPassword = "1";

    private final String firstOrderNumber = "№138";
    private final String secondOrderNumber = "№123";

    /*
    ************************************************
        End of fields used without preconditions
    ************************************************
    */

    private SignInPage signInPage;
    private WaiterMainPage waiterMainPage;

    @BeforeClass
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
    }

    @BeforeMethod
    public void waiterLogIn()    {
        signInPage.setUserEmailInputField(waiterEmail);
        signInPage.setUserPasswordInputField(waiterPassword);
        signInPage.clickSignInBtn();
        waiterMainPage = new WaiterMainPage(driver);
    }

    @Test
    public void waiterOrderStatusTest601()  {
        logger = extent.createTest("Waiter order status test 6.01");
        String orderStatus;

        WaiterAssignedWaiterPage waiterAssignedWaiter = waiterMainPage
                .waiterHeader
                .waiterAssignedWaiterPageAccess();

        waiterAssignedWaiter.orders.expandOrder(firstOrderNumber).startOrderBtnClick();

        WaiterInProgressPage waiterInProgressPage = waiterAssignedWaiter
                .waiterHeader
                .waiterInProgressPageAccess();

        orderStatus = waiterInProgressPage.orders.getOrderStatus(firstOrderNumber);

        Assert.assertEquals(orderStatus, "In progress");
    }

    @Test
    public void waiterOrderStatusTest602()  {
        logger = extent.createTest("Waiter order status test 6.02");
        String orderStatus;

        WaiterInProgressPage waiterInProgressPage = waiterMainPage
                .waiterHeader
                .waiterInProgressPageAccess();

        waiterInProgressPage.orders.expandOrder(firstOrderNumber).closeOrderBtnClick();

        WaiterHistoryPage waiterHistoryPage = waiterInProgressPage
                .waiterHeader
                .waiterHistoryPageAccess();

        orderStatus = waiterHistoryPage.orders.getOrderStatus(firstOrderNumber);

        Assert.assertEquals(orderStatus, "History");
    }

    @Test
    public void waiterOrderStatusTest603()  {
        logger = extent.createTest("Waiter order status test 6.03");
        String orderStatus;

        waiterMainPage = waiterMainPage.waiterHeader.waiterMainPageAccess();

        waiterMainPage.orders.expandOrder(secondOrderNumber).startOrderBtnClick();

        WaiterInProgressPage waiterInProgressPage = waiterMainPage
                .waiterHeader
                .waiterInProgressPageAccess();

        orderStatus = waiterInProgressPage.orders.getOrderStatus(secondOrderNumber);

        Assert.assertEquals(orderStatus, "In progress");
    }

    @Test
    public void waiterOrderStatusTest604()  {
        logger = extent.createTest("Waiter order status test 6.04");
        String orderStatus;

        waiterMainPage = waiterMainPage.waiterHeader.waiterMainPageAccess();

        waiterMainPage.orders.expandOrder(secondOrderNumber).closeOrderBtnClick();

        WaiterHistoryPage waiterHistoryPage = waiterMainPage
                .waiterHeader
                .waiterHistoryPageAccess();

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

    }
}
