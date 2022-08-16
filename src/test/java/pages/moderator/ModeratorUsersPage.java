package pages.moderator;

import org.openqa.selenium.WebDriver;
import pageComponents.UsersTablePageComponent;

public class ModeratorUsersPage extends ModeratorBasePage {

    public final UsersTablePageComponent usersTable;

    public ModeratorUsersPage (WebDriver driver)    {
        super(driver);
        usersTable = new UsersTablePageComponent(driver);
    }
}