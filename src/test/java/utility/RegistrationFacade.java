package utility;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import pages.SignUpPage;

public class RegistrationFacade {
    public static RegistrationData registerUserAccount(WebDriver driver) {
        Faker faker = new Faker();

        RegDataBuilder regDataBuilder = new RegDataBuilder();
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

        return user;
    }
}
