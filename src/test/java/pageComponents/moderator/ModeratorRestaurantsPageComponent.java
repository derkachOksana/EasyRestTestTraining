package pageComponents.moderator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ModeratorRestaurantsPageComponent {

    private WebElement neededRestaurant;

    @FindBy(xpath = "//main/div/div")
    private List<WebElement> restaurants;

    public ModeratorRestaurantsPageComponent(String desiredRestaurantName) {
        for(WebElement restaurant : restaurants)    {
            String currentRestaurantName = restaurant.findElement(By.xpath(
                    ".//span[contains(@class, 'title')]")).getText();
            if(currentRestaurantName.equals(desiredRestaurantName)) {
                neededRestaurant = restaurant;
                break;
            }
        }
    }

    public String getRestaurantStatus() {
        return neededRestaurant.findElement(By.xpath(
                ".//*[@role='button']")).getAttribute("outerText");
    }

    public void approveRestaurant() {
        neededRestaurant.findElement(By.xpath(
                ".//button[contains(., 'Approve')]"))
                .click();
    }

    public void disapproveRestaurant()  {
        neededRestaurant.findElement(By.xpath(
                ".//button[contains(., 'Disapprove')]"))
                .click();
    }

    public void deleteRestaurant()  {
        neededRestaurant.findElement(By.xpath(
                ".//button[contains(., 'Delete')]"))
                .click();
    }

    public void restoreRestaurant() {
        neededRestaurant.findElement(By.xpath(
                ".//button[contains(., 'Restore')]"))
                .click();
    }
}