package pages.moderator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.HeaderPageComponent;
import pageComponents.moderator.ModeratorOwnersTablePageComponent;
import pages.SignInPage;

public class ModeratorOwnersPage {
    private String ownerEmail;

    private WebDriver driver = null;

    public void setOwnerEmail(String ownerEmail)    {
        this.ownerEmail = ownerEmail;
    }

    public ModeratorOwnersPage(WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private final HeaderGeneralPageComponent headerGlobal = new HeaderGeneralPageComponent(driver);

    private final HeaderPageComponent header = new HeaderPageComponent(driver);

    private final ModeratorOwnersTablePageComponent ownersTable = new ModeratorOwnersTablePageComponent(driver, ownerEmail);

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
