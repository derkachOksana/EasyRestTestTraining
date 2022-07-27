package pages.moderator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.HeaderPageComponent;
import pageComponents.moderator.ModeratorRestaurantsPageComponent;
import pages.SignInPage;

public class ModeratorRestaurantsPage {
    private String restaurantName;

    public void setRestaurantName(String restaurantName)    {
        this.restaurantName = restaurantName;
    }

    @FindBy(xpath = "//a[@href='/moderator/users']")
    private WebElement usersSheet;

    @FindBy(xpath = "//a[@href='/moderator/owners']")
    private WebElement ownersSheet;

    private final HeaderGeneralPageComponent headerGlobal = new HeaderGeneralPageComponent();
    private final HeaderPageComponent header = new HeaderPageComponent();

    private final ModeratorRestaurantsPageComponent restaurants = new ModeratorRestaurantsPageComponent(restaurantName);

    public void allTabAccess()  {
        header.moderatorAllTabAccess();
    }

    public void unapprovedTabAccess()   {
        header.moderatorUnapprovedTabAccess();
    }

    public void approvedTabAccess() {
        header.moderatorApprovedTabAccess();
    }

    public void archivedTabAccess() {
        header.moderatorArchivedTabAccess();
    }

    public SignInPage logOut()  {
        return headerGlobal.logOut();
    }

    public ModeratorUsersPage usersPageAccess() {
        usersSheet.click();
        return new ModeratorUsersPage();
    }

    public ModeratorOwnersPage ownersPageAccess()   {
        ownersSheet.click();
        return new ModeratorOwnersPage();
    }

    public String getRestaurantStatus() {
        return restaurants.getRestaurantStatus();
    }

    public void approveRestaurant() {
        restaurants.approveRestaurant();
    }

    public void disapproveRestaurant()  {
        restaurants.disapproveRestaurant();
    }

    public void deleteRestaurant()  {
        restaurants.deleteRestaurant();
    }

    public void restoreRestaurant() {
        restaurants.restoreRestaurant();
    }
}
