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
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
        signInPage.setUserEmailInputField(clientEmail);
        signInPage.setUserPasswordInputField(clientPassword);
        signInPage.clickSignInBtn();
        restaurantsPage = new RestaurantsPage(driver);
    }

    @Test
    public void verifyAddToCartButton714() {
        logger = extent.createTest("Verify Add to cart button 7.14");
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.getFoodMassByItemName(menuItem1);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        Assert.assertTrue(menuPage.submitOrderEnable(), "The button Submit order is not enable");
    }

    @Test
    public void verifySubmitOrderBtn716() {
        logger = extent.createTest("Verify Submit Order button 7.16");
        menuPage.submitOrder();
        Assert.assertEquals(menuPage.orderConfirmation.orderConfirmationFieldVisible(), "Order confirmation");
    }

    @AfterClass
    public void clientLogOut() {
        menuPage.orderConfirmation.cancelOrder();
        /*We need to write method active/unactive button Cart*/
        //menuPage.showCartBtnClick();
        menuPage.deleteBtnClick();
        signInPage = restaurantsPage.headerGlobal.
                userMenu().logOut();
    }
}
