package tests.client;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MenuPage;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.profile.MyProfilePage;
import tests.BaseTest;



public class ClientOrderStatusTest extends BaseTest {
    private final String restaurantName = "Johnson PLC";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Chicken & broccoli pasta bake";
    private SignInPage signInPage;
    private RestaurantsPage restaurantsPage;


    @BeforeClass
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
    }

    @BeforeMethod
    public void clientLogIn()    {
        signInPage.setUserEmailInputField(clientEmail);
        signInPage.setUserPasswordInputField(clientPassword);
        signInPage.clickSignInBtn();
        restaurantsPage = new RestaurantsPage(driver);
    }

    @Test
    public void changeOrderStatusToDeclineTest171 () throws InterruptedException {
        logger = extent.createTest("Client order status test 1.7.1");
        Thread.sleep(1000);
        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName);

        menuPage.menuItems.getFoodMassByItemName(menuItem1);

        menuPage.menuItems.addToCartByItemName(menuItem1);

        menuPage.submitOrder();
        Thread.sleep(1000);
        menuPage.orderConfirmation.submitOrder();
        Thread.sleep(1000);


        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();

        Thread.sleep(1000);
        String actualOrderId = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderId();
        Thread.sleep(1000);
        myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().expandOrder().declineBtnClick();

        Thread.sleep(1000);

        myProfilePage.orderHistoryAccess().clientHeader.declinedTabAccess();

        boolean resultId = menuPage.headerGlobal.userMenu().myProfileAccess().orderHistoryAccess()
                .clientHeader.ordersContainer.matchOrderById(actualOrderId);

        Assert.assertTrue(resultId, "Something went wrong");
    }

    @Test
    public void changeOrderStatusToDeclineTest172() throws InterruptedException {
        logger = extent.createTest("Client order status test 1.7.2");
        Thread.sleep(1000);
        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName);

        menuPage.menuItems.getFoodMassByItemName(menuItem1);

        menuPage.menuItems.addToCartByItemName(menuItem1);

        menuPage.submitOrder();
        Thread.sleep(1000);
        menuPage.orderConfirmation.submitOrder();
        Thread.sleep(1000);


        MyProfilePage myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();

        Thread.sleep(1000);
        String actualOrderId = myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().getOrderId();
        Thread.sleep(1000);
        myProfilePage.currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().expandOrder().declineBtnClick();

        Thread.sleep(1000);

        myProfilePage.orderHistoryAccess().clientHeader.allTabAccess();

        boolean resultId = menuPage.headerGlobal.userMenu().myProfileAccess().orderHistoryAccess()
                .clientHeader.ordersContainer.matchOrderById(actualOrderId);

        Assert.assertTrue(resultId, "Something went wrong");

    }


    @AfterMethod
    public void clientLogOut()  {
        signInPage = restaurantsPage.getHeaderGeneralPageComponent()
                .userMenu().logOut();

    }
}
