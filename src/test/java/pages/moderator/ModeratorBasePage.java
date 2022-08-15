package pages.moderator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.moderator.ModeratorHeaderPageComponent;

public class ModeratorBasePage {
    protected WebDriver driver;

    public final HeaderGeneralPageComponent headerGlobal;
    public final ModeratorHeaderPageComponent header;


    @FindBy(xpath = "//a[@href='/moderator/users']")
    private WebElement usersSheet;

    @FindBy(xpath = "//a[@href='/moderator/owners']")
    private WebElement ownersSheet;

    @FindBy(xpath = "//a[@href='/moderator/restaurants']")
    private WebElement restaurantsSheet;

    public ModeratorBasePage (WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        headerGlobal = new HeaderGeneralPageComponent(driver);
        header = new ModeratorHeaderPageComponent(driver);
    }

    public ModeratorUsersPage usersPageAccess() {
        usersSheet.click();
        return new ModeratorUsersPage(driver);
    }

    public ModeratorOwnersPage ownersPageAccess()   {
        ownersSheet.click();
        return new ModeratorOwnersPage(driver);
    }

    public ModeratorRestaurantsPage restaurantsPageAccess() {
        restaurantsSheet.click();
        return new ModeratorRestaurantsPage(driver);
    }
}
