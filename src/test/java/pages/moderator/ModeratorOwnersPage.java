package pages.moderator;

import org.openqa.selenium.WebDriver;
import pageComponents.moderator.ModeratorOwnersTablePageComponent;

public class ModeratorOwnersPage extends ModeratorBasePage {
    public final ModeratorOwnersTablePageComponent ownersTable;

    public ModeratorOwnersPage(WebDriver driver)    {
        super(driver);
        ownersTable = new ModeratorOwnersTablePageComponent(driver);
    }
}
