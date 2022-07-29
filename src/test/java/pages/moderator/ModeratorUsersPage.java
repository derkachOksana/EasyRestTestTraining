package pages.moderator;

import pageComponents.HeaderGeneralPageComponent;
import pageComponents.HeaderPageComponent;
import pageComponents.moderator.ModeratorUsersTablePageComponent;
import pages.SignInPage;

public class ModeratorUsersPage {
    private String userEmail;

    public void setUserEmail(String userEmail)    {
        this.userEmail = userEmail;
    }

    private final HeaderGeneralPageComponent headerGlobal = new HeaderGeneralPageComponent();

    private final HeaderPageComponent header = new HeaderPageComponent();

    private final ModeratorUsersTablePageComponent usersTable = new ModeratorUsersTablePageComponent(userEmail);

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