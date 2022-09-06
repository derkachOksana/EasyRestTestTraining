package tests.client;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.MenuPage;
import pages.RestaurantsPage;
import pages.SignInPage;
import tests.BaseTest;
import utility.ConfProperties;
import java.time.Duration;

public class VerifyButtonsForStartOrderByClientTest extends BaseTest {
    private final String restaurantName = "Ball-Logan";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Avocado & strawberry smoothie";
    private SignInPage signInPage;
    private RestaurantsPage restaurantsPage;
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
        menuPage = new MenuPage(driver);
    }
    @Test
    public void verifyWatchMenuButton714() {
        boolean watchMenuBtnIsDisplayed = restaurantsPage.watchMenuByRestNameDisplayed(restaurantName, duration);
        Assert.assertEquals(watchMenuBtnIsDisplayed, true);
    }

    @Test
    public void verifyAddToCartButton715() {
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName, duration);
        boolean addToCartBtnIsDisplayed = menuPage.menuItems.addToCartBtnDisplayed(menuItem1);
        Assert.assertEquals(addToCartBtnIsDisplayed, true);
    }

    @Test
    public void verifySubmitOrderBtn716() {
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName, duration);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        boolean submitOrderEnable_actual = menuPage.submitOrderEnable();
        menuPage.deleteBtnClick(duration);
        Assert.assertTrue(submitOrderEnable_actual, "The button Submit order is not enable");
    }
    @AfterMethod
    public void goToStart () {
        menuPage.headerGlobal.restaurantsListAccess(driver);
    }

    @AfterClass
    public void clientLogOut() {
        signInPage = restaurantsPage.headerGlobal.
                userMenu().logOut();
    }
}
