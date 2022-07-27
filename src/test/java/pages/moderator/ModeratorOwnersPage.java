package pages.moderator;

import pageComponents.HeaderGeneralPageComponent;
import pageComponents.HeaderPageComponent;
import pageComponents.moderator.ModeratorOwnersTablePageComponent;
import pages.SignInPage;

public class ModeratorOwnersPage {
    private String ownerEmail;

    public void setOwnerEmail(String ownerEmail)    {
        this.ownerEmail = ownerEmail;
    }

    private final HeaderGeneralPageComponent headerGlobal = new HeaderGeneralPageComponent();

    private final HeaderPageComponent header = new HeaderPageComponent();

    private final ModeratorOwnersTablePageComponent ownersTable = new ModeratorOwnersTablePageComponent(ownerEmail);

    public SignInPage logout()  {
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
        return ownersTable.getOwnerStatus();
    }

    public void banUnbanUser()   {
        ownersTable.changeOwnerStatus();
    }
}
