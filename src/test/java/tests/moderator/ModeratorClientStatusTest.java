package tests.moderator;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.AdminPage;
import pages.SignInPage;
import pages.moderator.ModeratorBasePage;
import pages.moderator.ModeratorUsersPage;
import tests.BaseTest;
import utility.ConfProperties;
import utility.RegistrationData;
import utility.RegistrationFacade;

import java.time.Duration;

public class ModeratorClientStatusTest extends BaseTest {
    private SignInPage signInPage;
    private ModeratorUsersPage moderatorUsersPage;
    private RegistrationData clientRegistrationData;
    private RegistrationData moderatorRegistrationData;
    private String userStatus;

    @BeforeClass
    public void preconditions() {
        Duration duration = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("duration")));

        logger = extent.createTest("Setup preconditions to moderator client tests.");

        clientRegistrationData = RegistrationFacade.registerNewClient(driver, duration);

        logger.info("Client registered.");

        moderatorRegistrationData = RegistrationFacade.registerNewModerator(driver, duration);

        logger.info("Moderator registered.");

        driver.get(ConfProperties.getProperty("logInPage"));
        signInPage = new SignInPage(driver);
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
