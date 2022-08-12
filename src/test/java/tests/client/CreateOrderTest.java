package tests.client;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.MenuPage;
import pages.RestaurantsPage;
import pages.SignInPage;
import tests.BaseTest;

public class CreateOrderTest extends BaseTest {
    private final String restaurantName = "Gray Group";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Mustard-stuffed chicken";
    private SignInPage signInPage;
    private RestaurantsPage restaurantsPage;
    private MenuPage menuPage;


    @BeforeClass
    public void preconditions() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
        Thread.sleep(1000);
        signInPage.setUserEmailInputField(clientEmail);
        signInPage.setUserPasswordInputField(clientPassword);
        signInPage.clickSignInBtn();
        Thread.sleep(1000);
        restaurantsPage = new RestaurantsPage(driver);
    }

    @Test
    public void verifyAddToCartButton714() throws InterruptedException {
        logger = extent.createTest("Verify Add to cart button 7.14");
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.getFoodMassByItemName(menuItem1);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        Assert.assertTrue(menuPage.submitOrderEnable(), "The button Submit order is not enable");
    }

    @Test
    public void verifySubmitOrderBtn716() throws InterruptedException {
        logger = extent.createTest("Verify Submit Order button 7.16");
        menuPage.submitOrder();
        Assert.assertEquals(menuPage.orderConfirmation.orderConfirmationFieldVisible(), "Order confirmation");
    }

    @AfterClass
    public void clientLogOut() throws InterruptedException {
        menuPage.orderConfirmation.cancelOrder();
        menuPage.deleteBtnClick();
        signInPage = restaurantsPage.getHeaderGeneralPageComponent().
                userMenu().logOut();
    }
}
