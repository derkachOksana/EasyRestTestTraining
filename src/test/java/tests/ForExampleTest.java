package tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageComponents.HeaderGeneralPageComponent;
import pages.MenuPage;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.SignUpPage;
import pages.administrator.AcceptedTabPage;
import pages.administrator.AdministratorPage;
import pages.administrator.WaitingForConfirmTabPage;
import pages.administrator.order.OrderComponent;
import pages.profile.ProfileCurrentOrdersPage;
import pages.profile.ProfileOrderHistoryPage;
import utility.ConfProperties;
import utility.RegDataBuilder;
import utility.RegistrationData;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class ForExampleTest extends BaseTest{
    public static SignUpPage signUpPage;
    public static SignInPage logInPage;
    private RegistrationData regData;
    public static ProfileCurrentOrdersPage currentOrdersPage;
    private RegDataBuilder regDataBuilder;

    @Test
    public void onlyForExampleTest() throws InterruptedException {

        String restaurantName = "Johnson PLC";

        driver.get(ConfProperties.getProperty("signInPage"));
        SignInPage signIn = new SignInPage(driver);
        signIn.setUserEmailInputField("nathansmith@test.com");
        signIn.setUserPasswordInputField("1111");
        signIn.clickSignInBtn();

        RestaurantsPage restaurantsPage = new RestaurantsPage(driver);
        System.out.println("Restaurant page");
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
        System.out.println("Add to cart");
        menuPage.submitOrder();
        System.out.println("Submit order");
        Thread.sleep(1000);

        String totalSumExpected = menuPage.orderConfirmation.orderSummary.getTotalOrderPrice();

        menuPage.orderConfirmation.submitOrder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.now();
        String currentTime = dtf.format(localTime);
        menuPage.headerGlobal.userMenu().myProfileAccess()
                .currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().clickNeededOrder(currentTime);
        System.out.println("Wait decline");
        menuPage.headerGlobal.userMenu().myProfileAccess().currentOrdersAccess()
                .clientHeader.ordersContainer.orderInfo.declineBtnClick();
        System.out.println("Decline already click");

        














      /*  boolean matchRestaurant = restaurantsPage.getHeaderGeneralPageComponent().userMenu().myProfileAccess()
                .currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().matchRestaurant(restaurantName, time, totalSum);
        System.out.println("Cood #2!");


        Thread.sleep(300);*/




    }
}
