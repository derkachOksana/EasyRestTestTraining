package tests.client;

import org.testng.Assert;
import org.testng.annotations.*;
import pageComponents.HeaderGeneralPageComponent;
import pages.HomePage;
import pages.MenuPage;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.profile.MyProfilePage;
import pages.profile.ProfileCurrentOrdersPage;
import pages.profile.components.FacadeAccessToMyProfileForClient;
import pages.profile.components.FacadeCreateOrder;
import tests.BaseTest;
import utility.ConfProperties;
import utility.DataBaseConnection;

import java.time.Duration;

public class VerifyOrderConfirmationWindowTest extends BaseTest {
    private final String restaurantName = "Johnson PLC";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Chicken & broccoli pasta bake";
    private SignInPage signInPage;
    private RestaurantsPage restaurantsPage;
    private ProfileCurrentOrdersPage currentOrdersPage;
    private MenuPage menuPage;
    private final Duration duration = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("durationForClientTests")));

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
        menuPage = new MenuPage(driver);
    }

    @BeforeMethod
    public void startMethod () {
        DataBaseConnection.getInstance().deleteAllOrdersFrom_order_associations();
        DataBaseConnection.getInstance().deleteAllOrdersFrom_orders();
    }
    @Test
    public void verifyNameOfPosition702 () {
        HeaderGeneralPageComponent header = new HeaderGeneralPageComponent(driver);
        header.restaurantsListAccess(driver);
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        String itemNameOrderConfirmation_expected = menuPage.orderConfirmation.orderSummary.itemName(menuItem1);
        menuPage.orderConfirmation.submitOrder(duration);
        String itemNameOrder_actual = menuPage.headerGlobal.userMenu(duration).myProfileAccess(duration).currentOrdersAccess().clientHeader
                .waitingForConfirmTabAccess().expandOrder(duration).itemNameOrder(duration);
        Assert.assertEquals(itemNameOrder_actual, itemNameOrderConfirmation_expected);
    }

    @Test
    public void verifyCancelBtnInOrderConfirmation705 () {
        FacadeCreateOrder createOrder = new FacadeCreateOrder(restaurantsPage, menuPage);
        createOrder.createOrder(restaurantName, menuItem1, duration);
        int orderId_expected = restaurantsPage.headerGlobal.userMenu(duration)
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
        DataBaseConnection.getInstance().deleteAllOrdersFrom_order_associations();
        DataBaseConnection.getInstance().deleteAllOrdersFrom_orders();
        currentOrdersPage.headerGeneralPageComponent.restaurantsListAccess(driver);
    }

    @AfterClass
    public void clientLogOut() {
        signInPage = restaurantsPage.headerGlobal.
                userMenu().logOut();
    }
}
