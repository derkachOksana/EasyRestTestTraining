package pages.profile.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.waiter.WaiterOrderInfoPageComponent;

import java.util.List;

public class ClientOrdersContainerComponent {
    private final WebDriver driver;
    public final ClientOrderInfoComponent orderInfo;

   /* @FindBy(xpath = "//main//header/following-sibling::div")
    private List<WebElement> ordersContainer;*/

    public ClientOrdersContainerComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        orderInfo = new ClientOrderInfoComponent(driver);
    }

    public String getOrderID(String nameRestaurant, String timeOrder, String totalSum) {
        try {
            String id = driver.findElement(By.xpath("//div/div[@role='button']" +
                    "/div/div/div/p[contains(text(),'" + nameRestaurant + "')]" +
                    "//ancestor::div/div[@role='button']/div/div/div/p[contains(.,'" + timeOrder + "')]" +
                    "//ancestor::div[@role='button']/div/div/div/p[contains(.,'" + totalSum + "')]")).getText();
            System.out.println(id);
           // expandOrderClickById(id);
            return id;
        } catch (Exception e) {
            System.out.println("Order not found");
            return null;
        }
    }
    public boolean matchRestaurant(String nameRestaurant, String timeOrder, String totalSum) {

        // method checks if there is an order with such parameters


        boolean orderStatusOne = driver.findElement(By.xpath("//div[@role='button']//p[contains(text(),'" + nameRestaurant + "')]")).isDisplayed();
        boolean orderStatusTwo = driver.findElement(By.xpath("//div[@role='button']//p[contains(.,'" + timeOrder + "')]")).isDisplayed();
        boolean orderStatusThree = driver.findElement(By.xpath("//div[@role='button']//p[contains(.,'" + totalSum + "')]")).isDisplayed();

        if (orderStatusOne == true &&
                orderStatusTwo == true &&
                orderStatusThree == true) {
            return true;
        } else {
            return false;

        }}

        public void expandOrderClickById (String orderId) {
                driver.findElement(By.xpath("//div/div[@role='button']/div/div/div/p[contains(.,'" +
                        orderId + "')]" +
                        "//ancestor::div[@role='button']")).click();
            }
}

