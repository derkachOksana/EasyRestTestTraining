package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MenuPage;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.SignUpPage;
import pages.profile.ProfileCurrentOrdersPage;
import utility.ConfProperties;
import utility.RegDataBuilder;
import utility.RegistrationData;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class CurrentOrderFunctionalityTests extends BaseTest{
   // public static SignUpPage signUpPage;
   // public static SignInPage logInPage;
   // private RegistrationData regData;
    //public static ProfileCurrentOrdersPage currentOrdersPage;
    //private RegDataBuilder regDataBuilder;

    @Test
    public void changeOrderStatusToDeclineTest_1_7_1 () throws InterruptedException {

        String restaurantName = "Johnson PLC";

        driver.get(ConfProperties.getProperty("signInPage"));
        SignInPage signIn = new SignInPage(driver);
        signIn.setUserEmailInputField("nathansmith@test.com");
        signIn.setUserPasswordInputField("1111");
        signIn.clickSignInBtn();

        RestaurantsPage restaurantsPage = new RestaurantsPage(driver);
        Thread.sleep(500);

        MenuPage menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        Thread.sleep(1000);
        menuPage.menuItems.getFoodMassByItemName("Chicken & broccoli pasta bake");
        Thread.sleep(1000);

        menuPage.menuItems.addToCartByItemName("Chicken & broccoli pasta bake");
        Thread.sleep(1000);

        menuPage.menuItems.getFoodMassByItemName("Chicken & chorizo jambalaya");
        Thread.sleep(2000);

        menuPage.menuItems.addToCartByItemName("Chicken & chorizo jambalaya");
        Thread.sleep(1000);

        menuPage.submitOrder();
        Thread.sleep(1000);
        menuPage.orderConfirmation.submitOrder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
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

        Assert.assertTrue(resultId, "Something went wrong");
    }
}
