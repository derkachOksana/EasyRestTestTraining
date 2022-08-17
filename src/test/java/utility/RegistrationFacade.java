package utility;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignInPage;
import pages.SignUpPage;

import java.time.Duration;

public class RegistrationFacade {

    static RegDataBuilder regDataBuilder = new RegDataBuilder();
    static Faker faker = new Faker();

    public static RegistrationData registerNewClient(WebDriver driver, Duration duration)  {
        WebDriverWait wait = new WebDriverWait(driver, duration);

        RegistrationData clientRegistrationData = regDataBuilder
                .name(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.superhero().name() + faker.superhero().power())
                .build();

        driver.get(ConfProperties.getProperty("registrationPage"));

        SignUpPage signUpPage = new SignUpPage(driver);

        signUpPage.inputName(clientRegistrationData.getName());
        signUpPage.inputEmail(clientRegistrationData.getEmail());
        signUpPage.inputPassword(clientRegistrationData.getPassword());
        signUpPage.inputRepeatedPassword(clientRegistrationData.getPassword());
        signUpPage.createAccountAccept();

        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("logInPage")));

        driver.get(ConfProperties.getProperty("mainPage"));
        return clientRegistrationData;
    }

    public static RegistrationData registerNewModerator(WebDriver driver, Duration duration)   {
        WebDriverWait wait = new WebDriverWait(driver, duration);

        RegistrationData moderatorRegistrationData = regDataBuilder
                .name(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.code().isbn13())
                .build();

        driver.get(ConfProperties.getProperty("logInPage"));

        SignInPage signInPage = new SignInPage(driver);

        signInPage.setUserEmailInputField(ConfProperties.getProperty("adminLogin"));
        signInPage.setUserPasswordInputField(ConfProperties.getProperty("adminPassword"));
        signInPage.clickSignInBtn();

        wait.until(ExpectedConditions.urlContains(ConfProperties.getProperty("adminNodeUrl")));

        driver.get(ConfProperties.getProperty("adminModeratorsCreatePage"));

        SignUpPage signUpPage = new SignUpPage(driver);

        signUpPage.inputName(moderatorRegistrationData.getName());
        signUpPage.inputEmail(moderatorRegistrationData.getEmail());
        signUpPage.inputPassword(moderatorRegistrationData.getPassword());
        signUpPage.inputRepeatedPassword(moderatorRegistrationData.getPassword());
        signUpPage.createAccountAccept();

        driver.get(ConfProperties.getProperty("mainPage"));

        return moderatorRegistrationData;
    }
}
