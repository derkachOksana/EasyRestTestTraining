package pages.administrator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;
import pages.administrator.order.OrderComponent;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class AdministratorPage {

    private final WebDriver driver;

    @FindBy(xpath = "//main/div/header")
    private WebElement tabDiv;
    @FindBy(xpath = "//main/div/div/div/div/div")
    private List<WebElement> orderDivList;

    public AdministratorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public HeaderGeneralPageComponent getHeaderGeneralPageComponent() {
        return new HeaderGeneralPageComponent(driver);
    }

    public TabPanelComponent getTabPanelPage() {
        return new TabPanelComponent(tabDiv);
    }

    public OrderComponent getOrderById(String orderId) {
        List<OrderComponent> orders = getOrderList();

        return orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No order was found for order id: " + orderId));
    }

    public List<OrderComponent> getOrderList() {
        return orderDivList.stream()
                .map(el -> new OrderComponent(el, driver))
                .collect(Collectors.toList());
    }
}
