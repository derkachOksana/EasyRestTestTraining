package tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageComponents.HeaderGeneralPageComponent;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.SignUpPage;
import pages.administrator.AcceptedTabPage;
import pages.administrator.AdministratorPage;
import pages.administrator.WaitingForConfirmTabPage;
import pages.administrator.order.OrderComponent;
import pages.profile.ProfileCurrentOrdersPage;
import utility.ConfProperties;
import utility.RegDataBuilder;
import utility.RegistrationData;


public class ForExampleTest extends BaseTest{
    public static SignUpPage signUpPage;
    public static SignInPage logInPage;
    private RegistrationData regData;
    public static ProfileCurrentOrdersPage currentOrdersPage;
    private RegDataBuilder regDataBuilder;

    @Test
    public void onlyForExampleTest() throws InterruptedException {

        String restaurantName = "Johnson PLC";
        String time = "Booked for: 28.07.2022 21:12";
        String totalSum = "54.20";

        driver.get(ConfProperties.getProperty("signInPage"));
        SignInPage signIn = new SignInPage(driver);
        signIn.setUserEmailInputField("nathansmith@test.com");
        signIn.setUserPasswordInputField("1111");
        signIn.clickSignInBtn();
        System.out.println("Cood!");


        RestaurantsPage restaurantsPage = new RestaurantsPage(driver);

        restaurantsPage.clickByRestaurantName("Johnson PLC");

        System.out.println("Restaurant page");
        boolean matchRestaurant = restaurantsPage.getHeaderGeneralPageComponent().userMenu().myProfileAccess()
                .currentOrdersAccess().clientHeader.waitingForConfirmTabAccess().matchRestaurant(restaurantName, time, totalSum);
        System.out.println("Cood #2!");


        Thread.sleep(300);




    }
}
