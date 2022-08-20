package tests.client;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.MenuPage;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.profile.MyProfilePage;
import tests.BaseTest;
import utility.ConfProperties;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VerifyStatusOrderByClientTest extends BaseTest {
    private final String restaurantName = "Johnson PLC";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Chicken & broccoli pasta bake";
    private SignInPage signInPage;
    private RestaurantsPage restaurantsPage;
    private final Duration duration = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("duration")));

    @BeforeClass
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
        signInPage.setUserEmailInputField(clientEmail);
        signInPage.setUserPasswordInputField(clientPassword);
        signInPage.clickSignInBtn();
        restaurantsPage = new RestaurantsPage(driver);
    }
    /*@Test
    public void changeOrderStatusToDeclinedTest171() {
        logger = extent.createTest("Check posibility change order from status Waiting for confirm to Declined in My Profile/Order History/Declined 1.7.1");
        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName, duration);

        menuPage.menuItems.addToCartByItemName(menuItem1);

        menuPage.submitOrder();

        menuPage.orderConfirmation.submitOrder(duration);

        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();

        String actualOrderId = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderId();

        myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().expandOrder().declineBtnClick();

        myProfilePage.orderHistoryAccess().clientHeader.declinedTabAccess();

        boolean resultId = menuPage.headerGlobal.userMenu().myProfileAccess().orderHistoryAccess()
                .clientHeader.ordersContainer.matchOrderById(actualOrderId);

        Assert.assertTrue(resultId, "The order is not appeared in My Profile/Order History/Declined");
    }

    @Test
    public void changeOrderStatusToDeclinedTest172() {
        logger = extent.createTest("Check posibility change order from status Waiting for confirm to Declined in My Profile/Order History/All 1.7.2");

        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName);

        menuPage.menuItems.addToCartByItemName(menuItem1);

        menuPage.submitOrder();

        menuPage.orderConfirmation.submitOrder(duration);

        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();

        String actualOrderId = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderId();

        myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().expandOrder().declineBtnClick();

        myProfilePage.orderHistoryAccess().clientHeader.allTabAccess();

        boolean resultId = menuPage.headerGlobal.userMenu().myProfileAccess().orderHistoryAccess()
                .clientHeader.ordersContainer.matchOrderById(actualOrderId);

        Assert.assertTrue(resultId, "The order is not appeared in My Profile/Order History/Declined");
    }*/

    @Test
    public void verifyOrderWaitingForConfirmStatus181() {
        logger = extent.createTest("Verify that order is appeared with status Waiting for confirm in My profile/Current orders/Waiting for confirm 1.8.1");

        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName, duration);

        menuPage.menuItems.addToCartByItemName(menuItem1);

        menuPage.submitOrder();

        menuPage.orderConfirmation.submitOrder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.now();
        String currentTime = dtf.format(localTime);

        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu(duration).myProfileAccess();

        String timeCreatedOrder = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().timeCreatedOrder();

        Assert.assertEquals(timeCreatedOrder, currentTime);
    }

    @AfterMethod
    public void goToStartPage()  {
        restaurantsPage.headerGlobal.restaurantsListAccess(driver);

    }
    @AfterClass
    public void clientLogOut() {
        signInPage = restaurantsPage.headerGlobal.
                userMenu().logOut();
    }


}
