package tests.moderator;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SignInPage;
import pages.moderator.ModeratorBasePage;
import pages.moderator.ModeratorOwnersPage;
import tests.BaseTest;

public class ModeratorOwnerStatusTest extends BaseTest  {

    /*
    ***************************************************

            Fields used without preconditions

    ***************************************************
    */

    private final String moderatorEmail = "petermoderator@test.com";
    private final String moderatorPassword = "1";
    private final String ownerEmail = "jasonbrown@test.com";

    /*
    ************************************************
        End of fields used without preconditions
    ************************************************
    */

    private SignInPage signInPage;
    private ModeratorOwnersPage moderatorOwnersPage;

    private String ownerStatus;

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
        moderatorOwnersPage = moderatorBasePage.ownersPageAccess();
    }

    @Test
    public void moderatorOwnerStatusTest313()  {
        logger = extent.createTest("Moderator owner status test 3.13");

        moderatorOwnersPage = moderatorOwnersPage.header.moderatorActiveOwnersTabAccess();
        moderatorOwnersPage.ownersTable.changeOwnerStatus(ownerEmail);
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorBannedOwnersTabAccess();

        ownerStatus = moderatorOwnersPage.ownersTable.getOwnerStatus(ownerEmail);

        Assert.assertEquals(ownerStatus, "Banned");
    }

    @Test
    public void moderatorOwnerStatusTest314()  {
        logger = extent.createTest("Moderator owner status test 3.14");

        moderatorOwnersPage = moderatorOwnersPage.header.moderatorBannedOwnersTabAccess();
        moderatorOwnersPage.ownersTable.changeOwnerStatus(ownerEmail);
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorActiveOwnersTabAccess();

        ownerStatus = moderatorOwnersPage.ownersTable.getOwnerStatus(ownerEmail);

        Assert.assertEquals(ownerStatus, "Active");
    }

    @Test
    public void moderatorOwnerStatusTest315()  {
        logger = extent.createTest("Moderator owner status test 3.15");

        moderatorOwnersPage = moderatorOwnersPage.header.moderatorAllOwnersTabAccess();
        moderatorOwnersPage.ownersTable.changeOwnerStatus(ownerEmail);
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorBannedOwnersTabAccess();

        ownerStatus = moderatorOwnersPage.ownersTable.getOwnerStatus(ownerEmail);

        Assert.assertEquals(ownerStatus, "Banned");
    }

    @Test
    public void moderatorOwnerStatusTest316()  {
        logger = extent.createTest("Moderator owner status test 3.16");

        moderatorOwnersPage = moderatorOwnersPage.header.moderatorAllOwnersTabAccess();
        moderatorOwnersPage.ownersTable.changeOwnerStatus(ownerEmail);
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorActiveOwnersTabAccess();

        ownerStatus = moderatorOwnersPage.ownersTable.getOwnerStatus(ownerEmail);

        Assert.assertEquals(ownerStatus, "Active");
    }

    @AfterMethod
    public void moderatorLogOut()  {
        signInPage = moderatorOwnersPage
                .headerGlobal
                .userMenu()
                .logOut();
    }

    @AfterClass
    public void postonditions()
    {

    }
}
