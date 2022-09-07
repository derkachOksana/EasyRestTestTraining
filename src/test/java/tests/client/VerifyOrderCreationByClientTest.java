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
import utility.DataBaseConnection;
import java.time.Duration;

public class VerifyOrderCreationByClientTest extends BaseTest {
    private final String restaurantName = "Ball-Logan";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Avocado & strawberry smoothie";
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
        DataBaseConnection.getInstance().deleteAllOrdersFrom_order_associations();
        DataBaseConnection.getInstance().deleteAllOrdersFrom_orders();
    }

    @Test
    public void verifyOrderWaitingForConfirmStatus181() {
        /*logger = extent.createTest("Verify that order is appeared with status Waiting for " +
                "confirm in My profile/Current orders/Waiting for confirm 1.8.1");*/
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        menuPage.orderConfirmation.submitOrder(duration);
        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu(duration).myProfileAccess();
        String statusOrder_actual = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getStatusOrder(duration);
        Assert.assertEquals(statusOrder_actual, "Waiting for confirm");
    }

    @Test
    public void verifyOrderWaitingForConfirmStatus182() {
        /*logger = extent.createTest("Verify that order is appeared with status Waiting for " +
                "confirm in My profile/Current orders/All 1.8.2");*/
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        menuPage.orderConfirmation.submitOrder(duration);
        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu(duration).myProfileAccess();
        String statusOrder_actual = myProfilePage.currentOrdersAccess().clientHeader.allTabAccess().getStatusOrder(duration);
        Assert.assertEquals(statusOrder_actual, "Waiting for confirm");
    }

    @AfterMethod
    public void goToStartPage() {
        DataBaseConnection.getInstance().deleteAllOrdersFrom_order_associations();
        DataBaseConnection.getInstance().deleteAllOrdersFrom_orders();
        restaurantsPage.headerGlobal.restaurantsListAccess(driver);
    }

    @AfterClass
    public void clientLogOut() {
        signInPage = restaurantsPage.headerGlobal.
                userMenu().logOut();
    }
}
