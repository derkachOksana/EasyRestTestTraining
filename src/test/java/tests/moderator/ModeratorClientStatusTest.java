package tests.moderator;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AdminPage;
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
    private SignInPage signInPage;
    private ModeratorUsersPage moderatorUsersPage;
    private RegistrationData clientRegistrationData;
    private RegistrationData moderatorRegistrationData;
    private final Duration duration = Duration.ofSeconds
            (Integer.parseInt(ConfProperties.getProperty("duration")));

    private String userStatus;

    @BeforeClass
    public void preconditions() {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        logger = extent.createTest("Setup preconditions to moderator client tests.");

        RegDataBuilder regDataBuilder = new RegDataBuilder();
        clientRegistrationData = regDataBuilder
                .name(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.superhero().name() + faker.superhero().power())
                .build();

        moderatorRegistrationData = regDataBuilder
                .name(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.superhero().name() + faker.superhero().power())
                .build();

        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = homePage.getHeaderGeneralPageComponent().signUpAccess();

        signUpPage.inputName(clientRegistrationData.getName());
        signUpPage.inputEmail(clientRegistrationData.getEmail());
        signUpPage.inputPassword(clientRegistrationData.getPassword());
        signUpPage.inputRepeatedPassword(clientRegistrationData.getPassword());

        logger.info("Client registered.");

        signInPage = signUpPage.createAccountAccept();

        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("logInPage")));

        signInPage.setUserEmailInputField(ConfProperties.getProperty("adminLogin"));
        signInPage.setUserPasswordInputField(ConfProperties.getProperty("adminPassword"));
        signInPage.clickSignInBtn();

        AdminPage adminPage = new AdminPage(driver);

        adminPage = adminPage.moderatorSheetAccess();
        signUpPage = adminPage.createModeratorAccount();

        signUpPage.inputName(moderatorRegistrationData.getName());
        signUpPage.inputEmail(moderatorRegistrationData.getEmail());
        signUpPage.inputPassword(moderatorRegistrationData.getPassword());
        signUpPage.inputRepeatedPassword(moderatorRegistrationData.getPassword());
        signUpPage.createAccountAccept();

        logger.info("Moderator registered.");

        signInPage = adminPage.headerGlobal.userMenu().logOut();
    }

    @BeforeMethod
    public void moderatorLogIn() {
        signInPage.setUserEmailInputField(moderatorRegistrationData.getEmail());
        signInPage.setUserPasswordInputField(moderatorRegistrationData.getPassword());
        signInPage.clickSignInBtn();

        ModeratorBasePage moderatorBasePage = new ModeratorBasePage(driver).headerGlobal.userMenu().moderatorPanelAccess();
        moderatorUsersPage = moderatorBasePage.usersPageAccess();
    }

    @Test
    public void moderatorClientStatusTest309()  {
        logger = extent.createTest("Moderator client status test 3.09");

        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(clientRegistrationData.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(clientRegistrationData.getEmail());

        Assert.assertEquals(userStatus, "Banned");
    }

    @Test
    public void moderatorClientStatusTest310()  {
        logger = extent.createTest("Moderator client status test 3.10");

        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(clientRegistrationData.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(clientRegistrationData.getEmail());

        Assert.assertEquals(userStatus, "Active");
    }

    @Test
    public void moderatorClientStatusTest311()  {
        logger = extent.createTest("Moderator client status test 3.11");

        moderatorUsersPage = moderatorUsersPage.header.moderatorAllUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(clientRegistrationData.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(clientRegistrationData.getEmail());

        Assert.assertEquals(userStatus, "Banned");
    }

    @Test
    public void moderatorClientStatusTest312()  {
        logger = extent.createTest("Moderator client status test 3.12");

        moderatorUsersPage = moderatorUsersPage.header.moderatorAllUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(clientRegistrationData.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(clientRegistrationData.getEmail());

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
        logger = extent.createTest("Banning tested client and moderator");
        signInPage.setUserEmailInputField(ConfProperties.getProperty("adminLogin"));
        signInPage.setUserPasswordInputField(ConfProperties.getProperty("adminPassword"));
        signInPage.clickSignInBtn();
        AdminPage adminPage = new AdminPage(driver).headerGlobal.userMenu().adminPanelAccess().moderatorSheetAccess();
        adminPage.changeModeratorStatus(moderatorRegistrationData.getEmail());
        logger.info("Moderator banned.");
        adminPage.usersTable().changeUserStatus(clientRegistrationData.getEmail());
        logger.info("Client banned.");
        adminPage.headerGlobal.userMenu().logOut();
        driver.get(ConfProperties.getProperty("mainPage"));
    }
}
