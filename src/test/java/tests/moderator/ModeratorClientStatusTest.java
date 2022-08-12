package tests.moderator;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SignInPage;
import pages.SignUpPage;
import pages.moderator.ModeratorBasePage;
import pages.moderator.ModeratorUsersPage;
import tests.BaseTest;
import utility.ConfProperties;
import utility.RegDataBuilder;
import utility.RegistrationData;

import java.time.Duration;

public class ModeratorClientStatusTest extends BaseTest {

    /*
    ***************************************************

            Fields used without preconditions

    ***************************************************
    */

    private final String moderatorEmail = "petermoderator@test.com";
    private final String moderatorPassword = "1";

    /*
    ************************************************
        End of fields used without preconditions
    ************************************************
    */

    private SignInPage signInPage;
    private ModeratorUsersPage moderatorUsersPage;
    private RegistrationData registrationData;
    private Duration duration = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("duration")));

    private String userStatus;

    @BeforeClass
    public void preconditions() {
        logger = extent.createTest("Setup preconditions to moderator client tests.");

        RegDataBuilder regDataBuilder = new RegDataBuilder();
        registrationData = regDataBuilder
                .name(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.superhero().name() + faker.superhero().power())
                .build();

        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = homePage.getHeaderGeneralPageComponent().signUpAccess();

        signUpPage.inputName(registrationData.getName());
        signUpPage.inputEmail(registrationData.getEmail());
        signUpPage.inputPassword(registrationData.getPassword());
        signUpPage.inputRepeatedPassword(registrationData.getPassword());

        logger.info("Client registered.");

        signInPage = signUpPage.createAccountAccept();
    }

    @BeforeMethod
    public void moderatorLogIn() {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("logInPage")));

        signInPage.setUserEmailInputField(moderatorEmail);
        signInPage.setUserPasswordInputField(moderatorPassword);
        signInPage.clickSignInBtn();

        ModeratorBasePage moderatorBasePage = new ModeratorBasePage(driver);
        moderatorUsersPage = moderatorBasePage.usersPageAccess();

        Assert.assertTrue(true, "hi");
    }

    @Test
    public void moderatorClientStatusTest309()  {
        logger = extent.createTest("Moderator client status test 3.09");

        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(registrationData.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(registrationData.getEmail());

        Assert.assertEquals(userStatus, "Banned");
    }

    @Test
    public void moderatorClientStatusTest310()  {
        logger = extent.createTest("Moderator client status test 3.10");

        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(registrationData.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(registrationData.getEmail());

        Assert.assertEquals(userStatus, "Active");
    }

    @Test
    public void moderatorClientStatusTest311()  {
        logger = extent.createTest("Moderator client status test 3.11");

        moderatorUsersPage = moderatorUsersPage.header.moderatorAllUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(registrationData.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(registrationData.getEmail());

        Assert.assertEquals(userStatus, "Banned");
    }

    @Test
    public void moderatorClientStatusTest312()  {
        logger = extent.createTest("Moderator client status test 3.12");

        moderatorUsersPage = moderatorUsersPage.header.moderatorAllUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(registrationData.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(registrationData.getEmail());

        Assert.assertEquals(userStatus, "Active");
    }

    @AfterMethod
    public void moderatorLogOut()  {
        signInPage = moderatorUsersPage
                .headerGlobal
                .userMenu()
                .logOut();
    }

    @AfterClass
    public void postonditions()
    {

    }
}
