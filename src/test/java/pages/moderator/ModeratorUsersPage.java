package pages.moderator;

import org.openqa.selenium.WebDriver;
import pageComponents.moderator.ModeratorUsersTablePageComponent;

public class ModeratorUsersPage extends ModeratorBasePage {

    private final ModeratorUsersTablePageComponent usersTable;

    public void setUserEmail(String userEmail)    {
        usersTable.setUserEmail(userEmail);
    }

    public ModeratorUsersPage (WebDriver driver)    {
        super(driver);
        usersTable = new ModeratorUsersTablePageComponent(driver);
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