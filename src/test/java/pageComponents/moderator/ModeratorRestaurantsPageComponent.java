package pageComponents.moderator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ModeratorRestaurantsPageComponent {


    private String desiredRestaurantName;

    @FindBy(xpath = "//main/div/div")
    private List<WebElement> restaurants;

    public ModeratorRestaurantsPageComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private WebElement getNeededRestaurantName()    {
        WebElement neededRestaurant = restaurants.get(0);
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

    public void setDesiredRestaurantName(String restaurantName)  {
        desiredRestaurantName = restaurantName;
    }

    public String getRestaurantStatus() {
        return getNeededRestaurantName().findElement(By.xpath(
                ".//*[@role='button']")).getAttribute("outerText");
    }

    public void approveRestaurant() {
        getNeededRestaurantName().findElement(By.xpath(
                ".//button[contains(., 'Approve')]"))
                .click();
    }

    public void disapproveRestaurant()  {
        getNeededRestaurantName().findElement(By.xpath(
                ".//button[contains(., 'Disapprove')]"))
                .click();
    }

    public void deleteRestaurant()  {
        getNeededRestaurantName().findElement(By.xpath(
                ".//button[contains(., 'Delete')]"))
                .click();
    }

    public void restoreRestaurant() {
        getNeededRestaurantName().findElement(By.xpath(
                ".//button[contains(., 'Restore')]"))
                .click();
    }
}
