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
    String timeCreatedOrder;

    public ClientOrdersContainerComponent(WebDriver driver) {
        this.driver = driver;
        orderInfo = new ClientOrderInfoComponent(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//main//header/following-sibling::div/div")
    private List <WebElement> ordersList;

    private WebElement neededOrder() {
        return ordersList.get(0);
    }

    public String getOrderId () {
        return neededOrder().findElement(By.xpath(
                        ".//div/div/div/div[1]/p")).getText();
    }

    public String getTotalPrice() {
        String fullInfo = neededOrder().findElement(By.xpath(".//div/div/div/div[5]/p")).getText();
        String totalPriceOrder = new String(fullInfo.toCharArray(), 13, 5);
        return totalPriceOrder;
    }

    public ClientOrderInfoComponent expandOrder() {
        neededOrder().click();
        return new ClientOrderInfoComponent(driver);
    }

    public boolean matchOrderById (String orderId) {
        for(WebElement order : ordersList)  {
            if (order.findElement(By.xpath(".//div/div/div/div[1]/p")).getText().equals(orderId)) {
                return true;
            }
        }
        return false;
    }

    public String timeCreatedOrder() {
        ;
        for (WebElement order : ordersList) {
            String fullInfo = order.findElement(By.xpath(
                            "./div/div/div/div[3]/p[contains(., 'Created')]"))
                    .getText();
            timeCreatedOrder = new String(fullInfo.toCharArray(), 20, 5);
        }
        return timeCreatedOrder;
    }

    /*public String orderId(String currentTime) throws InterruptedException {
        for(WebElement order : ordersList)  {
            String fullInfo = order.findElement(By.xpath(
                            "./div/div/div/div/div[3]/p[contains(., 'Created')]"))
                    .getText();
            String rezult = new String(fullInfo.toCharArray(), 20, 5);
            if(rezult.equals(currentTime)) {
                System.out.println(rezult.equals(currentTime));

                return order.findElement(By.xpath("./div/div/div/div/div[1]/p")).getText();
            } else {
                System.out.println("Order not found");
            }
        }
        return null;
    }*/







}

