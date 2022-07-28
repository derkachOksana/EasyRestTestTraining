package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import pages.LogInPage;
import pages.RestaurantPage;
import pages.SignUpPage;
import utility.ConfProperties;
import utility.RegDataBuilder;
import utility.RegistrationData;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {
    public static SignUpPage signUpPage;
    public static LogInPage logInPage;
    private RegistrationData regData;
    private RegDataBuilder regDataBuilder;

    @Test
    public void registerNewUserTest() throws InterruptedException {
        faker = new Faker();
        regDataBuilder = new RegDataBuilder();
        regData = regDataBuilder
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .birthDate(faker.date().birthday(2, 5))
                .password(faker.gameOfThrones().dragon() + faker.pokemon().name())
                .build();

        driver.get(ConfProperties.getProperty("signUpPage"));
        logger = extent.createTest("Registration new user test");

        signUpPage = new SignUpPage(driver);
        logInPage = new LogInPage(driver);

        signUpPage.inputName(regData.getName());
        logger.info("Name has been entered.");
        signUpPage.inputEmail(regData.getEmail());
        logger.info("Email has been entered.");
        signUpPage.inputPhoneNumber(regData.getPhoneNumber());
        logger.info("Phone number has been entered");
        signUpPage.calendarAccess();
        signUpPage.setYearCalendar(regData.getBirthDate());
        logger.info("Year has been set.");
        signUpPage.setMonthCalendar(regData.getBirthDate());
        logger.info("Month has been set.");
        signUpPage.setDayCalendar(regData.getBirthDate());
        logger.info("Day has been set.");
        signUpPage.calendarAcceptChanges();
        logger.info("Date has been set.");
        signUpPage.inputPassword(regData.getPassword());
        logger.info("Password has been set.");
        signUpPage.inputRepeatedPassword(regData.getPassword());
        logger.info("Password has been repeated.");
        signUpPage.createAccountAccept();
        Assert.assertEquals(logInPage.getSignInText(), "to continue E-Restaurant");
    }
}
