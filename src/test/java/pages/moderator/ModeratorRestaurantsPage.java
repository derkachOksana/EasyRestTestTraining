package pages.moderator;

import org.openqa.selenium.WebDriver;
import pageComponents.moderator.ModeratorRestaurantsPageComponent;

public class ModeratorRestaurantsPage extends ModeratorBasePage {

    public final ModeratorRestaurantsPageComponent restaurants;

    public ModeratorRestaurantsPage(WebDriver driver)   {
        super(driver);
        restaurants = new ModeratorRestaurantsPageComponent(driver);
    }
}