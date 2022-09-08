package tests.signin;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;
import tests.BaseTest;

public class SignInPageTest extends BaseTest {

    private final String USER_EMAIL = "eringonzales@test.com";
    private final Faker FAKER = new Faker();
    private final String ALERT_MESSAGE = "Email or password is invalid";
    private final String EMAIL_ERROR_MESSAGE = "Email is not valid";
    private final String PASSWORD_REQUIRED_MESSAGE = "Password is required";
    private final String EMAIL_REQUIRED_MESSAGE = "Email is required";
    private SignInPage signInPage;


    @BeforeMethod
    public void precondition() {
        HomePage homePage = new HomePage(driver);
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
    }

    @Test
    public void loginNotRegisteredUserWithValidLoginTest801() {
        signInPage.setUserEmailInputField(FAKER.internet().emailAddress());
        signInPage.setUserPasswordInputField(FAKER.code().ean8());
        signInPage.clickSignInBtn();

        Assert.assertEquals(signInPage.getAlertMessage(), ALERT_MESSAGE);
    }

    @Test
    public void loginWithInvalidEmailTest802() {
        signInPage.setUserEmailInputField(FAKER.superhero().name());

        Assert.assertEquals(signInPage.getEmailErrorMessage(), EMAIL_ERROR_MESSAGE);
    }

    @Test
    public void loginWithoutEmailAndPasswordTest803() {
        signInPage.clickSignInBtn();

        Assert.assertEquals(signInPage.getEmailErrorMessage(), EMAIL_REQUIRED_MESSAGE);
        Assert.assertEquals(signInPage.getPasswordErrorMessage(), PASSWORD_REQUIRED_MESSAGE);
    }

    @Test
    public void loginWithRegisteredEmailAndWrongPasswordTest804() {
        signInPage.setUserEmailInputField(USER_EMAIL);
        signInPage.setUserPasswordInputField(FAKER.code().ean8());
        signInPage.clickSignInBtn();

        Assert.assertEquals(signInPage.getAlertMessage(), ALERT_MESSAGE);
    }

    @AfterMethod
    public void backToHomePage() {
        signInPage.getHeaderGeneralPageComponent().clickBackToHomePageBtn();
    }
}
