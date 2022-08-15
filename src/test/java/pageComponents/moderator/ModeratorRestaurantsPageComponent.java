package pageComponents.moderator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ModeratorRestaurantsPageComponent {
    @FindBy(xpath = "//main/div/div")
    private List<WebElement> restaurants;

    public ModeratorRestaurantsPageComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private WebElement getNeededRestaurantName(String desiredRestaurantName)    {
        WebElement neededRestaurant = null;
        for(WebElement restaurant : restaurants)    {
            String currentRestaurantName = restaurant.findElement(By.xpath(
                    ".//span[contains(@class, 'title')]")).getText();
            if(currentRestaurantName.equals(desiredRestaurantName)) {
                neededRestaurant = restaurant;
                break;
            }
        }
        return neededRestaurant;
    }

    public String getRestaurantStatus(String restaurantName) {
        return getNeededRestaurantName(restaurantName).findElement(By.xpath(
                ".//*[@role='button']")).getAttribute("outerText");
    }

    public void approveRestaurant(String restaurantName) {
        getNeededRestaurantName(restaurantName).findElement(By.xpath(
                ".//button[contains(., 'Approve')]"))
                .click();
    }

    public void disapproveRestaurant(String restaurantName)  {
        getNeededRestaurantName(restaurantName).findElement(By.xpath(
                ".//button[contains(., 'Disapprove')]"))
                .click();
    }

    public void deleteRestaurant(String restaurantName)  {
        getNeededRestaurantName(restaurantName).findElement(By.xpath(
                ".//button[contains(., 'Delete')]"))
                .click();
    }

    public void restoreRestaurant(String restaurantName) {
        getNeededRestaurantName(restaurantName).findElement(By.xpath(
                ".//button[contains(., 'Restore')]"))
                .click();
    }
}
