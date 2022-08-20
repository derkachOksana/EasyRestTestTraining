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

public class VerifyCreateOrderByClientTest extends BaseTest {
    private final String restaurantName = "Gray Group";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Mustard-stuffed chicken";
    private SignInPage signInPage;
    private RestaurantsPage restaurantsPage;
    private MenuPage menuPage;
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
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName, duration);
    }

  /*  @Test
    public void verifyTotalPrice701() {
        logger = extent.createTest("Verify that total price of order in Order Confirmation window " +
                "is matched with Order sum in Current orders/ Waiting for confirm/# order 7.01");

        menuPage.menuItems.addToCartByItemName(menuItem1);

        menuPage.submitOrder();
        String totalPriceOrderConfirmationWindow = menuPage.orderConfirmation.orderSummary.getTotalOrderPrice();
        System.out.println("Total price " + totalPriceOrderConfirmationWindow);
        menuPage.orderConfirmation.submitOrder(duration);

        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu(duration).myProfileAccess();

        String totalPriceOrderInWaitingForConfirm = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getTotalPrice();
        System.out.println(totalPriceOrderInWaitingForConfirm);
        Assert.assertEquals(totalPriceOrderConfirmationWindow, totalPriceOrderInWaitingForConfirm);
    }*/

  /*  @Test
    public void verifyNameOfPosition702 () {
        logger = extent.createTest("Verify that name of position in Order Confirmation window " +
                "is matched with name of position in Current orders/ Waiting for confirm/# order 7.02");

        menuPage.menuItems.addToCartByItemName(menuItem1);

        menuPage.submitOrder();
        String itemNameOrderConfirmation = menuPage.orderConfirmation.orderSummary.itemName();
        menuPage.orderConfirmation.submitOrder(duration);
        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();
        String itemNameOrder = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().expandOrder().itemNameOrder();
        Assert.assertEquals(itemNameOrder, itemNameOrderConfirmation);
    }*/

  /*  @Test
    public void verifyPriceForOnePosition703 () {
        logger = extent.createTest("Verify that price for one position in page Watch menu " +
                "is appeared in column Total price for one position of window Order confirmation 7.03");
        String itemPriceInWatchMenuPage_expected = menuPage.menuItems.getPriceByItemName(menuItem1);
        System.out.println(itemPriceInWatchMenuPage_expected);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        String itemPriceInOrderConfirmation_actual = menuPage.orderConfirmation.orderSummary.getPriceByItemName(menuItem1, duration);
        System.out.println(itemPriceInOrderConfirmation_actual);
        //Assert.assertEquals(itemPriceInOrderConfirmation_actual, itemPriceInWatchMenuPage_expected);
    }*/

   /* @Test
    public void verifyDeleteForOnePosition704 () {
        logger = extent.createTest("Verify that user can delete position from Action column in Order Confirmation window 7.04");
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        menuPage.orderConfirmation.orderSummary.removeItemByName(menuItem1, duration);

        Assert.assertTrue(menuPage.orderConfirmation.orderSummary.itemName().isEmpty(), "Delete button for separate item is not working in Order Confirmation window");
    }*/
    /*@Test
    public void verifyCancelBtnInOrderConfirmation705 () {
        logger = extent.createTest("Verify that user can Cancel order in window Order Confirmation 7.05");
        int expectedOrderId = restaurantsPage.headerGlobal.userMenu(duration)
                .myProfileAccess().currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderIdInt();

        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName, duration);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        menuPage.orderConfirmation.cancelOrder();
        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();
        int lastOrderIdAfterTest = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderIdInt();
        Assert.assertEquals(lastOrderIdAfterTest, expectedOrderId);
    }*/

    /*@Test
    public void verifyChangeDateOrder706 () {
        logger = extent.createTest("Verify that user can change date (Date picker) of order in window Order confirmation 7.06");

        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName, duration);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        //menuPage.orderConfirmation.setDate(2022.08.13);
        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();
        String orderDate_actual = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderCreatedDate();

    }*/

     /*@Test
    public void verifyChangeTimeOrder707 () {
        logger = extent.createTest("Verify that user can change time of order (Time picker) in window Order confirmation 7.07");
        }
      */

    @Test
    public void verifyItemNameInWatchMenuPage709 () {
        logger = extent.createTest("Verify that chosen position in page Watch menu is appeared in column Item name of window Order confirmation 7.09");
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        String itemNameInOrderConfirmation_actual = menuPage.orderConfirmation.orderSummary.itemName();
        menuPage.orderConfirmation.cancelOrder(duration);
        Assert.assertEquals(itemNameInOrderConfirmation_actual, menuItem1);
    }




    @AfterClass
    public void clientLogOut() {
        signInPage = restaurantsPage.headerGlobal.
                userMenu().logOut();
    }
}
