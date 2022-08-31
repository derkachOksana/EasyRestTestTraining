package tests.moderator;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SignInPage;
import pages.SignUpPage;
import pages.moderator.ModeratorBasePage;
import pages.moderator.ModeratorUsersPage;
import tests.BaseTest;
import utility.*;

import java.time.Duration;

public class ModeratorClientStatusTest extends BaseTest {

    private SignInPage signInPage;
    private ModeratorUsersPage moderatorUsersPage;

    private String userStatus;

    private RegistrationData moderator;
    private RegistrationData client;

    private final Duration duration = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("duration")));

    @BeforeClass
    public void preconditions() {
        RegistrationFacade registrationFacade = new RegistrationFacade(new Faker(), new RegDataBuilder(), new SignUpPage(driver));
        final int MODERATOR_ROLE_ID = 3;
        moderator = DataBaseConnection.getInstance().createAccByRole(driver, MODERATOR_ROLE_ID);
        client = registrationFacade.registerUserAccount(driver);

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
        moderatorUsersPage = moderatorBasePage.usersPageAccess();
    }

    @Test
    public void moderatorClientStatusTest309()  {
        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(client.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(client.getEmail());

        Assert.assertEquals(userStatus, "Banned");
    }

    @Test
    public void moderatorClientStatusTest310()  {
        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(client.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(client.getEmail());

        Assert.assertEquals(userStatus, "Active");
    }

    @Test
    public void moderatorClientStatusTest311()  {
        moderatorUsersPage = moderatorUsersPage.header.moderatorAllUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(client.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorBannedUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(client.getEmail());

        Assert.assertEquals(userStatus, "Banned");
    }

    @Test
    public void moderatorClientStatusTest312()  {
        moderatorUsersPage = moderatorUsersPage.header.moderatorAllUsersTabAccess();
        moderatorUsersPage.usersTable.changeUserStatus(client.getEmail());
        moderatorUsersPage = moderatorUsersPage.header.moderatorActiveUsersTabAccess();

        userStatus = moderatorUsersPage.usersTable.getUserStatus(client.getEmail());

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
        DataBaseConnection.getInstance().deleteUserByEmail(moderator.getEmail());
        DataBaseConnection.getInstance().deleteUserByEmail(client.getEmail());
    }
}
