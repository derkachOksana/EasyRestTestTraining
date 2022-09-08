package tests.signin;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageComponents.HeaderGeneralPageComponent;
import pages.HomePage;
import pages.RestaurantsPage;
import pages.SignInPage;
import pages.administrator.AdministratorPage;
import pages.moderator.ModeratorBasePage;
import pages.waiter.WaiterMainPage;
import tests.BaseTest;

import java.time.Duration;

public class SignInAsRegisteredUserTest extends BaseTest {


    private final String BASE_URL = "http://ec2-13-42-10-104.eu-west-2.compute.amazonaws.com:3000";
    private SignInPage signInPage;
    private WebDriverWait wait;


    @BeforeMethod
    public void precondition() {
        HomePage homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
    }

    @Test
    public void shouldLoginAsAdministratorTest() {
        String administratorUrl = BASE_URL + "/administrator-panel";
        logIn("eringonzales@test.com", "1");
        AdministratorPage administratorPage = new AdministratorPage(driver);
        wait.until(ExpectedConditions.urlToBe(administratorUrl));

        Assert.assertEquals(driver.getCurrentUrl(), administratorUrl);
    }

    @Test
    public void shouldLoginAsClientTest() {
        String clientUrl = BASE_URL + "/restaurants";
        logIn("kellymeza@test.com", "1111");
        RestaurantsPage restaurantsPage = new RestaurantsPage(driver);
        wait.until(ExpectedConditions.urlToBe(clientUrl));

        Assert.assertEquals(driver.getCurrentUrl(), clientUrl);
    }

    @Test
    public void shouldLoginAsModeratorTest() {
        String moderatorUrl = BASE_URL + "/moderator";
        logIn("petermoderator@test.com", "1");
        ModeratorBasePage moderatorPage = new ModeratorBasePage(driver);
        wait.until(ExpectedConditions.urlToBe(moderatorUrl));

        Assert.assertEquals(driver.getCurrentUrl(), moderatorUrl);
    }

    @Test
    public void shouldLoginAsWaiterTest() {
        String waiterUrl = BASE_URL + "/waiter/orders";
        logIn("alexandriawright@test.com", "1");
        WaiterMainPage waiterPage = new WaiterMainPage(driver);
        wait.until(ExpectedConditions.urlToBe(waiterUrl));

        Assert.assertEquals(driver.getCurrentUrl(), waiterUrl);
    }

    @Test
    public void shouldLoginAsOwnerTest() {
        String ownerUrl = BASE_URL + "/profile/restaurants";
        logIn("jasonbrown@test.com", "1111");
        WaiterMainPage waiterPage = new WaiterMainPage(driver);
        wait.until(ExpectedConditions.urlToBe(ownerUrl));

        Assert.assertEquals(driver.getCurrentUrl(), ownerUrl);
    }

    private void logIn(String email, String password) {
        signInPage.setUserEmailInputField(email);
        signInPage.setUserPasswordInputField(password);
        signInPage.clickSignInBtn();
    }

    @AfterMethod
    public void postCondition() {
        HeaderGeneralPageComponent header = new HeaderGeneralPageComponent(driver);
        header.userMenu().logOut();
        try {
            header.homeAccess();
        } catch (Exception e) {
            e.printStackTrace();
            header.clickBackToHomePageBtn();
        }
    }
}
