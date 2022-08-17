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
    public void verifyWatchMenuButton714() {
        logger = extent.createTest("Verify Watch menu button 7.14");
        String actualRestaurantMenuField = menuPage.menuItems.getRestaurantMenuField();
        Assert.assertEquals(actualRestaurantMenuField, "Gray Group menu:");
    }

    @Test
    public void verifyAddToCartButton715() {
        logger = extent.createTest("Verify Add to cart button 7.15");
        //menuPage.menuItems.getFoodMassByItemName(menuItem1);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        Assert.assertTrue(menuPage.submitOrderEnable(), "The button Submit order is not enabled");
    }

    @Test
    public void verifySubmitOrderBtn716() {
        logger = extent.createTest("Verify Submit Order button 7.16");
        menuPage.submitOrder();
        Assert.assertEquals(menuPage.orderConfirmation.orderConfirmationFieldVisible(), "Order confirmation");
    }

    @AfterClass
    public void clientLogOut() {
        menuPage.orderConfirmation.cancelOrder(duration);
        menuPage.deleteBtnClick(duration);
        signInPage = restaurantsPage.headerGlobal.
                userMenu().logOut();
    }
}
