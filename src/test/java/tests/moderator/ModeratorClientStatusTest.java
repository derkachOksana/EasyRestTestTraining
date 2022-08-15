package tests.moderator;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SignInPage;
import pages.moderator.ModeratorBasePage;
import pages.moderator.ModeratorUsersPage;
import tests.BaseTest;

public class ModeratorClientStatusTest extends BaseTest {

    /*
    ***************************************************

            Fields used without preconditions

    ***************************************************
    */

    private final String moderatorEmail = "petermoderator@test.com";
    private final String moderatorPassword = "1";
    private final String clientEmail = "angelabrewer@test.com";

    /*
    ************************************************
        End of fields used without preconditions
    ************************************************
    */

    private SignInPage signInPage;
    private ModeratorUsersPage moderatorUsersPage;

    private String userStatus;

    @BeforeClass
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
    }

    @BeforeMethod
    public void moderatorLogIn()    {
        signInPage.setUserEmailInputField(moderatorEmail);
        signInPage.setUserPasswordInputField(moderatorPassword);
        signInPage.clickSignInBtn();
        ModeratorBasePage moderatorBasePage = new ModeratorBasePage(driver);
        moderatorUsersPage = moderatorBasePage.usersPageAccess();
    }

    @Test
    public void moderatorClientStatusTest309()  {
        logger = extent.createTest("Moderator client status test 3.09");

        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(clientEmail);
        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(clientEmail);

        Assert.assertEquals(userStatus, "Banned");
    }

    @Test
    public void moderatorClientStatusTest310()  {
        logger = extent.createTest("Moderator client status test 3.10");

        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(clientEmail);
        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(clientEmail);

        Assert.assertEquals(userStatus, "Active");
    }

    @Test
    public void moderatorClientStatusTest311()  {
        logger = extent.createTest("Moderator client status test 3.11");

        moderatorUsersPage = moderatorUsersPage.header.moderatorAllUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(clientEmail);
        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(clientEmail);

        Assert.assertEquals(userStatus, "Banned");
    }

    @Test
    public void moderatorClientStatusTest312()  {
        logger = extent.createTest("Moderator client status test 3.12");

        moderatorUsersPage = moderatorUsersPage.header.moderatorAllUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(clientEmail);
        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(clientEmail);

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
