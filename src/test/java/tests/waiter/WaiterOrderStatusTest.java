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
import utility.ConfProperties;

import java.time.Duration;

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

    private final Duration duration = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("duration")));

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
                .waiterAssignedWaiterPageAccess(duration);

        waiterAssignedWaiter.orders.expandOrder(firstOrderNumber).startOrderBtnClick();

        WaiterInProgressPage waiterInProgressPage = waiterAssignedWaiter
                .waiterHeader
                .waiterInProgressPageAccess(duration);

        orderStatus = waiterInProgressPage.orders.getOrderStatus(firstOrderNumber);

        Assert.assertEquals(orderStatus, "In progress");
    }

    @Test
    public void waiterOrderStatusTest602()  {
        logger = extent.createTest("Waiter order status test 6.02");
        String orderStatus;

        WaiterInProgressPage waiterInProgressPage = waiterMainPage
                .waiterHeader
                .waiterInProgressPageAccess(duration);

        waiterInProgressPage.orders.expandOrder(firstOrderNumber).closeOrderBtnClick();

        WaiterHistoryPage waiterHistoryPage = waiterInProgressPage
                .waiterHeader
                .waiterHistoryPageAccess(duration);

        orderStatus = waiterHistoryPage.orders.getOrderStatus(firstOrderNumber);

        Assert.assertEquals(orderStatus, "History");
    }

    @Test
    public void waiterOrderStatusTest603()  {
        logger = extent.createTest("Waiter order status test 6.03");
        String orderStatus;

        waiterMainPage = waiterMainPage.waiterHeader.waiterMainPageAccess(duration);

        waiterMainPage.orders.expandOrder(secondOrderNumber).startOrderBtnClick();

        WaiterInProgressPage waiterInProgressPage = waiterMainPage
                .waiterHeader
                .waiterInProgressPageAccess(duration);

        orderStatus = waiterInProgressPage.orders.getOrderStatus(secondOrderNumber);

        Assert.assertEquals(orderStatus, "In progress");
    }

    @Test
    public void waiterOrderStatusTest604()  {
        logger = extent.createTest("Waiter order status test 6.04");
        String orderStatus;

        waiterMainPage = waiterMainPage.waiterHeader.waiterMainPageAccess(duration);

        waiterMainPage.orders.expandOrder(secondOrderNumber).closeOrderBtnClick();

        WaiterHistoryPage waiterHistoryPage = waiterMainPage
                .waiterHeader
                .waiterHistoryPageAccess(duration);

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
