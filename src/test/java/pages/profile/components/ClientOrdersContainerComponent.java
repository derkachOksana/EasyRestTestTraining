package pages.profile.components;

import freemarker.template.utility.DateUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageComponents.waiter.WaiterOrderInfoPageComponent;
import pages.administrator.order.OrderComponent;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientOrdersContainerComponent {
    private final WebDriver driver;
    public final ClientOrderInfoComponent orderInfo;
    private WebDriverWait wait;


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

    private WebElement neededOrder(Duration duration) {
        wait = new WebDriverWait(driver, duration);
        for (int i = 0; i <= 2; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(ordersList.get(0)));
                break;
            } catch (Exception e) {

            }
        }
        return ordersList.get(0);
    }

    public String getTotalPrice() {
        String fullInfo = neededOrder().findElement(By.xpath(".//div/div/div/div[5]/p")).getText();
        String totalPriceOrder = new String(fullInfo.toCharArray(), 13, 5);
        return totalPriceOrder;
    }

    public String getOrderCreatedDate() {
        String fullInfo = neededOrder().findElement(By.xpath(
                        "./div/div/div/div[3]/p[contains(., 'Created')]")).getText();
        return new String(fullInfo.toCharArray(), 20, 5);
    }

    public ClientOrderInfoComponent expandOrder(Duration duration) {
        wait = new WebDriverWait(driver, duration);
        for (int i = 0; i <= 2; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(ordersList.get(0)));
                break;
            } catch (Exception e) {

            }
        }
        neededOrder(duration).click();
        return new ClientOrderInfoComponent(driver);
    }

    public int getOrderIdInt () {
        String id = neededOrder().findElement(By.xpath(
                ".//div/div/div/div[1]/p")).getText();
        String result = new String(id.toCharArray(),1, 3);
        int idOnlyNumber = Integer.parseInt (result);
        return idOnlyNumber;
    }

    public String getStatusOrder (Duration duration) {
        return neededOrder(duration).findElement(By.xpath(
                ".//div/div/div/div[6]/p")).getText();
    }

    public String getNameRestaurantByOrder () {
        return neededOrder().findElement(By.xpath(
                ".//div/div/div/div[2]/p")).getText();
    }
}

