package utility;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignUpPage;

import java.time.Duration;

public class RegistrationFacade {
    private final static Faker faker = new Faker();
    private final static RegDataBuilder regDataBuilder = new RegDataBuilder();
    public static RegistrationData registerUserAccount(WebDriver driver) {
        Duration duration = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("duration")));
        WebDriverWait wait = new WebDriverWait(driver, duration);
        RegistrationData user = regDataBuilder
                .name(faker.name().name())
                .email(faker.internet().emailAddress())
                .password(faker.code().isbn13())
                .build();

        driver.get(ConfProperties.getProperty("signUpPage"));

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.inputName(user.getName());
        signUpPage.inputEmail(user.getEmail());
        signUpPage.inputPassword(user.getPassword());
        signUpPage.inputRepeatedPassword(user.getPassword());
        signUpPage.createAccountAccept();
        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("signInPage")));

        return user;
    }
}
