package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;

import java.util.List;

public class RestaurantsPage {
    private final WebDriver driver;
    public HeaderGeneralPageComponent getHeaderGeneralPageComponent() {
        return new HeaderGeneralPageComponent(driver);
    }

    public final HeaderGeneralPageComponent headerGlobal;

    public RestaurantsPage(WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        headerGlobal = new HeaderGeneralPageComponent(driver);
    }
    @FindBy(xpath = "//main/div/div/div")
    private List<WebElement> restaurants;

    private WebElement neededRestaurant(String restName)   {
        WebElement neededRestaurant = restaurants.get(0);
        for(WebElement restaurant : restaurants)    {
            if(restaurant.findElement(By.xpath(
                            ".//div/div"))
                    .getAttribute("title").equals(restName))   {
                neededRestaurant = restaurant;
            }
        }
        return neededRestaurant;
    }

    public MenuPage watchMenuByRestName(String restName)    {
        neededRestaurant(restName).findElement(By.xpath(
                ".//*[text()='Watch Menu']")).click();
        return new MenuPage(driver);
    }
}
