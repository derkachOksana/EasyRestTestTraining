package pages.profile.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.waiter.WaiterOrderInfoPageComponent;
import pages.administrator.order.OrderComponent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientOrdersContainerComponent {
    private final WebDriver driver;
    public final ClientOrderInfoComponent orderInfo;

    public ClientOrdersContainerComponent(WebDriver driver) {
        this.driver = driver;
        orderInfo = new ClientOrderInfoComponent(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//main//header/following-sibling::div")
    private List <WebElement> ordersList;


    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    LocalTime localTime = LocalTime.now();
    String currentTime = dtf.format(localTime);

    public void expandOrderClickById(String orderId) {
        driver.findElement(By.xpath("//div/div[@role='button']/div/div/div/p[contains(.,'" +
                orderId + "')]" +
                "//ancestor::div[@role='button']")).click();
    }

    public boolean orderDeclinedIsDisplayedById (String idOrder) {
        if (driver.findElement(By.xpath("//div[@role='button']//p[contains(text(),'"
                + idOrder + "')]")).isDisplayed()) {
            return true;
        }
        else { return false;}

    }


    public void clickNeededOrder(String currentTime) throws InterruptedException {
        for(WebElement order : ordersList)  {
            String fullInfo = order.findElement(By.xpath(
                            "./div/div/div/div/div[3]/p[contains(., 'Created')]"))
                    .getText();
            String rezult = new String(fullInfo.toCharArray(), 20, 5);
            if(rezult.equals(currentTime))    {
               // System.out.println(rezult);
                System.out.println(rezult.equals(currentTime));
                order.findElement(By.xpath("./div/div/div[2]")).click();
                break;
            } else {
                System.out.println("Order not found");

            }
        }
    }

}

