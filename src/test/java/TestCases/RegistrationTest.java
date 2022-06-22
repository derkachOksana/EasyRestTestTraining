package TestCases;

import Pages.SignUpPage;
import Utility.ConfProperties;
import Utility.RegistrationData;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Date;

public class RegistrationTest extends BaseTest{
    public static SignUpPage signUpPage;

    @Test
    public void Register() throws InterruptedException {
        Faker faker = new Faker();
        RegistrationData regData = new RegistrationData.Builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .birthDate(faker.date().birthday(18, 80))
                .password(faker.gameOfThrones().dragon() + faker.pokemon().name())
                .build();

        driver.get(ConfProperties.getProperty("signUpPage"));
        logger = extent.createTest("Registration test");

        signUpPage = new SignUpPage(driver);
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
        logger.pass("Account has been created.");
        Thread.sleep(1000);
    }
}
