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

public class VerifyOrderCreationByClientTest extends BaseTest {
    private final String restaurantName = "Johnson PLC";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Chicken & broccoli pasta bake";
    private SignInPage signInPage;
    private RestaurantsPage restaurantsPage;
    private ProfileCurrentOrdersPage currentOrdersPage;
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
    public void verifyOrderWaitingForConfirmStatus181() {
        logger = extent.createTest("Verify that order is appeared with status Waiting for " +
                "confirm in My profile/Current orders/Waiting for confirm 1.8.1");
        int expectedOrderId = restaurantsPage.headerGlobal.userMenu(duration)
                .myProfileAccess().currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderIdInt() + 1;
        currentOrdersPage.headerGeneralPageComponent.restaurantsListAccess(driver);

        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        menuPage.orderConfirmation.submitOrder(duration);
        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();
        int lastOrderIdAfterTest = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderIdInt();
        System.out.println(lastOrderIdAfterTest + "Order 181");
        System.out.println(expectedOrderId + "Order 181");
        Assert.assertEquals(lastOrderIdAfterTest, expectedOrderId);
    }

    @Test
    public void verifyOrderWaitingForConfirmStatus182() {
        logger = extent.createTest("Verify that order is appeared with status Waiting for " +
                "confirm in My profile/Current orders/All 1.8.2");
        int expectedOrderId = restaurantsPage.headerGlobal.userMenu(duration)
                .myProfileAccess().currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderIdInt() + 1;
        currentOrdersPage.headerGeneralPageComponent.restaurantsListAccess(driver);
        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        menuPage.orderConfirmation.submitOrder(duration);
        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();
        int lastOrderIdAfterTest = myProfilePage.currentOrdersAccess().clientHeader.allTabAccess().getOrderIdInt();
        System.out.println(lastOrderIdAfterTest + "Order 182");
        System.out.println(expectedOrderId + "Order 182");
        Assert.assertEquals(lastOrderIdAfterTest, expectedOrderId);
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
