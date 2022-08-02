package pages.moderator;

import org.openqa.selenium.WebDriver;
import pageComponents.moderator.ModeratorOwnersTablePageComponent;

public class ModeratorOwnersPage extends ModeratorBasePage {
    private final ModeratorOwnersTablePageComponent ownersTable;

    public void setOwnerEmail(String ownerEmail)    {
        ownersTable.setOwnerEmail(ownerEmail);
    }

    public ModeratorOwnersPage(WebDriver driver)    {
        super(driver);
        ownersTable = new ModeratorOwnersTablePageComponent(driver);
    }

    public void activeTabAccess()   {
        header.moderatorActiveTabAccess();
    }

    public void bannedTabAccess()   {
        header.moderatorBannedTabAccess();
    }
    public String getOwnerStatusStatus()   {
        return ownersTable.getOwnerStatus();
    }

    public void changeOwnerStatusUser()   {
        ownersTable.changeOwnerStatus();
    }
}
