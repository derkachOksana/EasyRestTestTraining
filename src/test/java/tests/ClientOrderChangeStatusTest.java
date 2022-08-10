package tests;

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
import pages.waiter.WaiterMainPage;
import utility.ConfProperties;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class ClientOrderChangeStatusTest extends BaseTest{
    private final String restaurantName = "Johnson PLC";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem1 = "Chicken & broccoli pasta bake";
    private final String menuItem2 = "Chicken & chorizo jambalaya";
    private SignInPage signIn;
    private RestaurantsPage restaurantsPage;


    @BeforeClass
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        signIn = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
    }

    @BeforeMethod
    public void clientLogIn()    {
        signIn.setUserEmailInputField(clientEmail);
        signIn.setUserPasswordInputField(clientPassword);
        signIn.clickSignInBtn();
        restaurantsPage = new RestaurantsPage(driver);
    }

    @Test
    public void changeOrderStatusToDeclineTest171 () throws InterruptedException {
        logger = extent.createTest("Client order status test 1.7.1");

        Thread.sleep(500);
        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        Thread.sleep(1000);
        menuPage.menuItems.getFoodMassByItemName(menuItem1);
        Thread.sleep(1000);

        menuPage.menuItems.addToCartByItemName(menuItem1);
        Thread.sleep(1000);

       /* menuPage.menuItems.getFoodMassByItemName(menuItem2);
        Thread.sleep(2000);

        menuPage.menuItems.addToCartByItemName(menuItem2);
        Thread.sleep(1000);*/

        menuPage.submitOrder();
        Thread.sleep(1000);
        menuPage.orderConfirmation.submitOrder();


        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Thread.sleep(1000);
        //System.out.println(menuPage.headerGlobal.userMenu().myProfileAccess().currentOrdersAccess().clientHeader.ordersContainer.neededOrder());


        Thread.sleep(1000);

        myProfilePage = menuPage.headerGlobal.userMenu().myProfileAccess();
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
}
