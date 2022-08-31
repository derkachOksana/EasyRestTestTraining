package tests.moderator;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SignInPage;
import pages.moderator.ModeratorBasePage;
import pages.moderator.ModeratorOwnersPage;
import tests.BaseTest;
import utility.ConfProperties;
import utility.DataBaseConnection;
import utility.RegistrationData;

import java.time.Duration;

public class ModeratorOwnerStatusTest extends BaseTest  {

    private SignInPage signInPage;
    private ModeratorOwnersPage moderatorOwnersPage;

    private String ownerStatus;

    private RegistrationData moderator;
    private RegistrationData owner;

    private final Duration duration = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("duration")));

    @BeforeClass
    public void preconditions() {
        final int MODERATOR_ROLE_ID = 3;
        final int OWNER_ROLE_ID = 2;
        moderator = DataBaseConnection.getInstance().createAccByRole(driver, MODERATOR_ROLE_ID);
        owner = DataBaseConnection.getInstance().createAccByRole(driver, OWNER_ROLE_ID);

        signInPage = new SignInPage(driver);
    }

    @BeforeMethod
    public void moderatorLogIn()    {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("signInPage")));

        signInPage.setUserEmailInputField(moderator.getEmail());
        signInPage.setUserPasswordInputField(moderator.getPassword());
        signInPage.clickSignInBtn();
        ModeratorBasePage moderatorBasePage = new ModeratorBasePage(driver);
        moderatorOwnersPage = moderatorBasePage.ownersPageAccess();
    }

    @Test
    public void moderatorOwnerStatusTest313()  {
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorActiveOwnersTabAccess();
        moderatorOwnersPage.ownersTable.changeOwnerStatus(owner.getEmail());
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorBannedOwnersTabAccess();

        ownerStatus = moderatorOwnersPage.ownersTable.getOwnerStatus(owner.getEmail());

        Assert.assertEquals(ownerStatus, "Banned");
    }

    @Test
    public void moderatorOwnerStatusTest314()  {
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorBannedOwnersTabAccess();
        moderatorOwnersPage.ownersTable.changeOwnerStatus(owner.getEmail());
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorActiveOwnersTabAccess();

        ownerStatus = moderatorOwnersPage.ownersTable.getOwnerStatus(owner.getEmail());

        Assert.assertEquals(ownerStatus, "Active");
    }

    @Test
    public void moderatorOwnerStatusTest315()  {
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorAllOwnersTabAccess();
        moderatorOwnersPage.ownersTable.changeOwnerStatus(owner.getEmail());
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorBannedOwnersTabAccess();

        ownerStatus = moderatorOwnersPage.ownersTable.getOwnerStatus(owner.getEmail());

        Assert.assertEquals(ownerStatus, "Banned");
    }

    @Test
    public void moderatorOwnerStatusTest316()  {
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorAllOwnersTabAccess();
        moderatorOwnersPage.ownersTable.changeOwnerStatus(owner.getEmail());
        moderatorOwnersPage = moderatorOwnersPage.header.moderatorActiveOwnersTabAccess();

        ownerStatus = moderatorOwnersPage.ownersTable.getOwnerStatus(owner.getEmail());

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
        DataBaseConnection.getInstance().deleteUserByEmail(moderator.getEmail());
        DataBaseConnection.getInstance().deleteUserByEmail(owner.getEmail());
    }
}
