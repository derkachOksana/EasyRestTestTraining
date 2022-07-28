package pages.administrator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.administrator.order.OrderComponent;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class AcceptedTabPage {

    private final WebDriver driver;

    public AcceptedTabPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
       // initOrderList();
    }

    @FindBy(xpath = "//main/div/div/div/div/div")
    private List<WebElement> orderDivList;


    public OrderComponent getOrderById(String orderId) {
        List<OrderComponent> orders = getOrderList();

        return orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No order was found for order id: " + orderId));
    }

    public List<OrderComponent> getOrderList() {
        return orderDivList.stream()
            .map(el -> new OrderComponent(el))
            .collect(Collectors.toList());
    }
}
