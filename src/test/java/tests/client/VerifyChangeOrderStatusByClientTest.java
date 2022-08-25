package tests.client;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.MenuPage;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.profile.MyProfilePage;
import pages.profile.components.FacadeAccessToMyProfileForClient;
import pages.profile.components.FacadeCreateOrder;
import tests.BaseTest;
import utility.ConfProperties;
import utility.DataBaseConnection;
import java.time.Duration;

public class VerifyChangeOrderStatusByClientTest extends BaseTest {
    private final String restaurantName = "Johnson PLC";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Chicken & broccoli pasta bake";
    private SignInPage signInPage;
    private RestaurantsPage restaurantsPage;
    private MyProfilePage myProfilePage;
    private MenuPage menuPage;
    private final Duration duration = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("duration")));
    private FacadeAccessToMyProfileForClient facadeAccessToMyProfile;

    @BeforeClass
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
        signInPage.setUserEmailInputField(clientEmail);
        signInPage.setUserPasswordInputField(clientPassword);
        signInPage.clickSignInBtn();
    }

    @BeforeMethod
    public void startTest () {
        DataBaseConnection.getInstance().deleteAllOrdersFrom_order_associations();
        DataBaseConnection.getInstance().deleteAllOrdersFrom_orders();
        restaurantsPage = new RestaurantsPage(driver);
        menuPage = new MenuPage(driver);
        FacadeCreateOrder facadeCreateOrder = new FacadeCreateOrder(restaurantsPage,menuPage);
        facadeCreateOrder.createOrder(restaurantName,menuItem1, duration);
        facadeAccessToMyProfile = new FacadeAccessToMyProfileForClient(menuPage);
        myProfilePage = facadeAccessToMyProfile.accessToMyProfile();
    }

    @Test
    public void changeOrderStatusToDeclinedTest171() {
        logger = extent.createTest("Check possibility change order from status Waiting for " +
                "confirm to Declined in My Profile/Order History/Declined 1.7.1");
        myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().expandOrder(duration).declineBtnClick(duration);
        String orderStatus_actual = myProfilePage.orderHistoryAccess().clientHeader.declinedTabAccess().getStatusOrder();
        Assert.assertEquals(orderStatus_actual, "Declined");
    }

    @Test
    public void changeOrderStatusToDeclinedTest172() {
        logger = extent.createTest("Check possibility change order from status Waiting for " +
                "confirm to Declined in My Profile/Order History/All 1.7.2");
        myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().expandOrder(duration).declineBtnClick(duration);
        String orderStatus_actual = myProfilePage.orderHistoryAccess().clientHeader.allTabAccess().getStatusOrder();
        Assert.assertEquals(orderStatus_actual, "Declined");

    }
    @AfterMethod
    public void goToStartPage()  {
        restaurantsPage.headerGlobal.restaurantsListAccess(driver);
    }

    @AfterClass
    public void clientLogOut() {
        DataBaseConnection.getInstance().deleteAllOrdersFrom_order_associations();
        DataBaseConnection.getInstance().deleteAllOrdersFrom_orders();
        signInPage = restaurantsPage.headerGlobal.
                userMenu().logOut();
    }
}
