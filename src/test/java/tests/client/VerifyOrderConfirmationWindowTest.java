package tests.client;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.MenuPage;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.profile.MyProfilePage;
import pages.profile.ProfileCurrentOrdersPage;
import tests.BaseTest;
import utility.ConfProperties;
import java.time.Duration;

public class VerifyOrderConfirmationWindowTest extends BaseTest {
    private final String restaurantName = "Gray Group";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Mustard-stuffed chicken";
    private SignInPage signInPage;
    private RestaurantsPage restaurantsPage;
    private ProfileCurrentOrdersPage currentOrdersPage;
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
        currentOrdersPage = new ProfileCurrentOrdersPage(driver);
    }
    @Test
    public void verifyNameOfPosition702 () {
        logger = extent.createTest("Verify that name of position in Order Confirmation window " +
                "is matched with name of position in Current orders/ Waiting for confirm/# order 7.02");
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);

        menuPage.menuItems.addToCartByItemName(menuItem1);

        menuPage.submitOrder();
        String itemNameOrderConfirmation = menuPage.orderConfirmation.orderSummary.itemName(menuItem1);
        menuPage.orderConfirmation.submitOrder(duration);
        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();
        String itemNameOrder = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().expandOrder().itemNameOrder();
        Assert.assertEquals(itemNameOrder, itemNameOrderConfirmation);
    }

    @Test
    public void verifyCancelBtnInOrderConfirmation705 () {
        logger = extent.createTest("Verify that user can Cancel order in window Order Confirmation 7.05");
        int orderId_expected = restaurantsPage.headerGlobal.userMenu()
                .myProfileAccess().currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderIdInt();
        currentOrdersPage.headerGeneralPageComponent.restaurantsListAccess(driver);
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        menuPage.orderConfirmation.cancelOrder(duration);
        menuPage.deleteBtnClick(duration);
        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();
        int orderIdAfterTest_actual = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderIdInt();
        Assert.assertEquals(orderIdAfterTest_actual, orderId_expected);
    }

    @Test
    public void verifyItemNameInWatchMenuPage709 () {
        logger = extent.createTest("Verify that chosen position in page Watch menu is appeared in column Item name of window Order confirmation 7.09");
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        String itemNameInOrderConfirmation_actual = menuPage.orderConfirmation.orderSummary.itemName(menuItem1);
        menuPage.orderConfirmation.cancelOrder(duration);
        menuPage.deleteBtnClick(duration);
        Assert.assertEquals(itemNameInOrderConfirmation_actual, menuItem1);
    }

    @Test
    public void verifyVolumeItemInWatchMenu710 () {
        logger = extent.createTest("Verify that volume position in page Watch menu is appeared in column Volume of window Order confirmation 7.10");
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);

        menuPage.menuItems.addToCartByItemName(menuItem1);
        String volumeItemInWatchMenuPage = menuPage.menuItems.getFoodMassByItemName(menuItem1);
        menuPage.submitOrder();
        String volumeItemInOrderConfirmation = menuPage.orderConfirmation.orderSummary.getVolumeByItemName(menuItem1);
        menuPage.orderConfirmation.cancelOrder(duration);
        menuPage.deleteBtnClick(duration);
        Assert.assertEquals(volumeItemInOrderConfirmation, volumeItemInWatchMenuPage);
    }

    @Test
    public void verifyPricePerItemInWatchMenu711 () {
        logger = extent.createTest("Verify that price per item position in page Watch menu is appeared in column Price per item of window Order confirmation 7.11");
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        String pricePerItemInWatchMenuPage = menuPage.menuItems.getPriceByItemName(menuItem1);
        menuPage.submitOrder();
        String pricePerItemInOrderConfirmation = menuPage.orderConfirmation.orderSummary.getPriceByItemName(menuItem1);
        menuPage.orderConfirmation.cancelOrder(duration);
        menuPage.deleteBtnClick(duration);
        Assert.assertEquals(pricePerItemInOrderConfirmation, pricePerItemInWatchMenuPage);
    }

    @Test
    public void verifyQuantityPerItemInWatchMenu712 () {
        logger = extent.createTest("Verify that quantity of chosen position in page Watch menu is appeared in column Quantity of window Order confirmation 7.12");
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        String quantityPerItemInWatchMenuPage = menuPage.menuItems.getQuantityByItemName(menuItem1);
        menuPage.submitOrder();
        String quantityPerItemInOrderConfirmation = menuPage.orderConfirmation.orderSummary.getQuantityByItemName(menuItem1);
        menuPage.orderConfirmation.cancelOrder(duration);
        menuPage.deleteBtnClick(duration);
        Assert.assertEquals(quantityPerItemInOrderConfirmation, quantityPerItemInWatchMenuPage);
    }
    @AfterMethod
    public void goToStartPage () {
        currentOrdersPage.headerGeneralPageComponent.restaurantsListAccess(driver);
    }
    @AfterClass
    public void clientLogOut() {
        signInPage = restaurantsPage.headerGlobal.
                userMenu().logOut();
    }
}
