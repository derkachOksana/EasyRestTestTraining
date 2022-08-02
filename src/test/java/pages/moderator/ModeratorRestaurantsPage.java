package pages.moderator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageComponents.moderator.ModeratorRestaurantsPageComponent;

public class ModeratorRestaurantsPage extends ModeratorBasePage {

    public final ModeratorRestaurantsPageComponent restaurants;

    public ModeratorRestaurantsPage(WebDriver driver)   {
        super(driver);
        restaurants = new ModeratorRestaurantsPageComponent(driver);
    }

    @FindBy(xpath = "//a[@href='/moderator/users']")
    private WebElement usersSheet;

    @FindBy(xpath = "//a[@href='/moderator/owners']")
    private WebElement ownersSheet;

    public ModeratorUsersPage usersPageAccess() {
        usersSheet.click();
        return new ModeratorUsersPage(driver);
    }

    public ModeratorOwnersPage ownersPageAccess()   {
        ownersSheet.click();
        return new ModeratorOwnersPage(driver);
    }
}
