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
import pages.waiter.WaiterMainPage;
import utility.ConfProperties;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class ClientOrderChangeStatusTest extends BaseTest{
    private final String restaurantName = "Johnson PLC";
    private final String clientEmail = "nathansmith@test.com";
    private final String clientPassword = "1111";
    private final String menuItem = "Chicken & broccoli pasta bake";
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



       /* driver.get(ConfProperties.getProperty("signInPage"));
        SignInPage signIn = new SignInPage(driver);
        signIn.setUserEmailInputField("nathansmith@test.com");
        signIn.setUserPasswordInputField("1111");
        signIn.clickSignInBtn();

        RestaurantsPage restaurantsPage = new RestaurantsPage(driver);*/
        Thread.sleep(500);
        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        Thread.sleep(1000);
        menuPage.menuItems.getFoodMassByItemName(menuItem);
        Thread.sleep(1000);

        menuPage.menuItems.addToCartByItemName(menuItem);
        Thread.sleep(1000);

        /*menuPage.menuItems.getFoodMassByItemName("Chicken & chorizo jambalaya");
        Thread.sleep(2000);

        menuPage.menuItems.addToCartByItemName("Chicken & chorizo jambalaya");
        Thread.sleep(1000);*/

        menuPage.submitOrder();
        Thread.sleep(1000);
        menuPage.orderConfirmation.submitOrder();


        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.now();
        String currentTime = dtf.format(localTime);


        Thread.sleep(1000);
        menuPage.headerGlobal.userMenu().myProfileAccess()
                .currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().clickNeededOrder(currentTime);
        Thread.sleep(1000);

        menuPage.headerGlobal.userMenu().myProfileAccess().orderHistoryAccess().clientHeader.declinedTabAccess();

        String orderId = menuPage.headerGlobal.userMenu().myProfileAccess().orderHistoryAccess()
                .clientHeader.ordersContainer.orderId(currentTime);
        boolean resultId = menuPage.headerGlobal.userMenu().myProfileAccess().orderHistoryAccess()
                .clientHeader.ordersContainer.matchOrderById(orderId);

        Assert.assertTrue(resultId, "Something went wrong");*/
    }



}
