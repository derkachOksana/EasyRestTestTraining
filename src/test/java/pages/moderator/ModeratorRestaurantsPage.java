package pages.moderator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageComponents.moderator.ModeratorRestaurantsPageComponent;

public class ModeratorRestaurantsPage extends ModeratorBasePage {

    private final ModeratorRestaurantsPageComponent restaurants;

    public void setRestaurantName(String restaurantName)    {
        restaurants.setDesiredRestaurantName(restaurantName);
    }

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

    public void archivedTabAccess()   {
        header.moderatorArchivedTabAccess();
    }

    public void unapprovedTabAccess()   {
        header.moderatorUnapprovedTabAccess();
    }

    public void approvedTabAccess()   {
        header.moderatorApprovedTabAccess();
    }

    public void activeTabAccess()   {
        header.moderatorActiveTabAccess();
    }
}
