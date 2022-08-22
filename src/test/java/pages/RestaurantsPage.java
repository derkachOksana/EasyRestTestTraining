package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageComponents.HeaderGeneralPageComponent;

import java.time.Duration;
import java.util.List;

public class RestaurantsPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    public final HeaderGeneralPageComponent headerGlobal;

    public RestaurantsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        headerGlobal = new HeaderGeneralPageComponent(driver);
    }

    @FindBy(xpath = "//main/div/div/div")
    private List<WebElement> restaurants;

    private WebElement neededRestaurant(String restName) {
        WebElement neededRestaurant = restaurants.get(0);
        for (WebElement restaurant : restaurants) {
            if (restaurant.findElement(By.xpath(
                            ".//div/div"))
                    .getAttribute("title").equals(restName)) {
                neededRestaurant = restaurant;
            }
        }
        return neededRestaurant;
    }

    private WebElement neededRestaurant(String restName, Duration duration) {
        wait = new WebDriverWait(driver, duration);
        // Using for loop, it tries for 3 times.
        // If the element is located for the first time then it breaks from the for loop nad comeout of the loop
        for (int i = 0; i <= 2; i++) {
            try {
                wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(restaurants.get(0))));
                break;
            } catch (Exception e) {

            }
        }
        WebElement neededRestaurant = restaurants.get(0);
        for (WebElement restaurant : restaurants) {
            if (restaurant.findElement(By.xpath(
                            ".//div/div"))
                    .getAttribute("title").equals(restName)) {
                neededRestaurant = restaurant;
            }
        }
        return neededRestaurant;
    }

    public MenuPage watchMenuByRestName(String restName) {
        neededRestaurant(restName).findElement(By.xpath(
                    ".//*[text()='Watch Menu']")).click();
        return new MenuPage(driver);
    }

    public MenuPage watchMenuByRestName(String restName, Duration duration) {
        neededRestaurant(restName, duration).findElement(By.xpath(
                ".//*[text()='Watch Menu']")).click();
        return new MenuPage(driver);
    }
}



