package pages.moderator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.HeaderPageComponent;
import pageComponents.moderator.ModeratorUsersTablePageComponent;
import pages.SignInPage;

public class ModeratorUsersPage {
    private String userEmail;

    private WebDriver driver = null;

    public void setUserEmail(String userEmail)    {
        this.userEmail = userEmail;
    }

    public ModeratorUsersPage (WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private final HeaderGeneralPageComponent headerGlobal = new HeaderGeneralPageComponent(driver);

    private final HeaderPageComponent header = new HeaderPageComponent(driver);

    private final ModeratorUsersTablePageComponent usersTable = new ModeratorUsersTablePageComponent(driver, userEmail);

    public SignInPage logOut()  {
        return headerGlobal.logOut();
    }

    public void allTabAccess()  {
        header.moderatorAllTabAccess();
    }

    public void activeTabAccess()   {
        header.moderatorActiveTabAccess();
    }

    public void bannedTabAccess()   {
        header.moderatorBannedTabAccess();
    }

    public String getUserStatus()   {
        return usersTable.getUserStatus();
    }

    public void changeUserStatus()   {
        usersTable.changeUserStatus();
    }
}