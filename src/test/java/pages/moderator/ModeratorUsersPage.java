package pages.moderator;

import org.openqa.selenium.WebDriver;
import pageComponents.moderator.ModeratorUsersTablePageComponent;

public class ModeratorUsersPage extends ModeratorBasePage {

    public final ModeratorUsersTablePageComponent usersTable;

    public ModeratorUsersPage (WebDriver driver)    {
        super(driver);
        usersTable = new ModeratorUsersTablePageComponent(driver);
    }
}