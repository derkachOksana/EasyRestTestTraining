package tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
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

        SignInPage signIn = new SignInPage(driver);
        signIn.setUserEmailInputField("nathansmith@test.com");
        signIn.setUserPasswordInputField("1111");
        signIn.clickSignInBtn();
        driver.get(ConfProperties.getProperty("currentOrders"));
       // logger = extent.createTest("Registration new user test");

        //signUpPage = new SignUpPage(driver);
     //   logInPage = new SignInPage(driver);
        currentOrdersPage = new ProfileCurrentOrdersPage(driver);

        Thread.sleep(300);
        String restaurantName = "Johnson PLC";
        String time = "Booked for: 28.07.2022 21:12";
        String totalSum = "54.20";
    }
}
