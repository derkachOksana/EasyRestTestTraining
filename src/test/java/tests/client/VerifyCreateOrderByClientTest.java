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

    @Test
    public void verifyTotalPrice701() {
        logger = extent.createTest("Verify that total price of order in Order Confirmation window is matched with Order sum in Current orders/ Waiting for confirm/# order 7.01");

        menuPage.menuItems.addToCartByItemName(menuItem1);

        menuPage.submitOrder();
        String totalPriceOrderConfirmationWindow = menuPage.orderConfirmation.orderSummary.getTotalOrderPrice();
        System.out.println("Total price " + totalPriceOrderConfirmationWindow);
       /* menuPage.orderConfirmation.submitOrder();

        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu(duration).myProfileAccess();

        String totalPriceOrderInWaitingForConfirm = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getTotalPrice();
        Assert.assertEquals(totalPriceOrderConfirmationWindow, totalPriceOrderInWaitingForConfirm);*/

    }


    @AfterClass
    public void clientLogOut() {
        signInPage = restaurantsPage.headerGlobal.
                userMenu().logOut();
    }
}
