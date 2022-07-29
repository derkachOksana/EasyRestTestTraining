package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RestaurantsPage {
    private final WebDriver driver;

    public RestaurantsPage(WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//main/div/div/div/div")
    private List<WebElement> restaurantContainers;

    public void clickByRestaurantName(String name) throws IllegalArgumentException {

        try {
            driver.findElement(By.xpath("//main/div/div/div//div[@title='" + name +
                    "']/ancestor::div[1]//div/a/span[contains(text(), 'Watch Menu')]")).click();
        }catch (Exception e){
            throw new IllegalArgumentException("A restaurant with the name [" + name + "] does not exist ");
        }
    }
}
