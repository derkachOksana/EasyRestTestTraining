package pages.administrator.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderComponent {

    private final WebElement orderDiv;

    private OrderConfirmationComponent orderConfirmationComponent;

    private OrderAcceptedComponent orderAcceptedComponent;

    @FindBy(xpath = "./div[1]/div[@role='button']")
    private WebElement dropDownBtn;
    @FindBy(xpath = "./div[1]/div/div/div/div[1]")
    private WebElement orderId;
    @FindBy(xpath = "./div[1]/div/div/div/div/div")
    private WebElement orderStatus;
    @FindBy(xpath = "./div[2]")
    private WebElement orderExpansion;

    public OrderComponent(WebElement orderDiv) {
        PageFactory.initElements(orderDiv, this);
        this.orderDiv = orderDiv;
    }

    public String getOrderStatus() {
        return orderStatus.getText().trim();
    }

    public String getOrderId() {
        return orderId.getText().replaceAll("\\D", "");
    }

    public void clickDropDownBtn() {
         dropDownBtn.click();
    }

    public void acceptOrder() {
        initConfirmationComponent();
        orderConfirmationComponent.clickAcceptBtn();
    }

    public void assignWaiter() throws InterruptedException {
        initAcceptedComponent();
        orderAcceptedComponent.clickAssignBtn();

        //There is no other way to resolve this action, there is no UI element we can use for waiter.
        Thread.sleep(500);
    }

    public List<WaiterComponent> getWaiterList() {
        initAcceptedComponent();
        return orderAcceptedComponent.getWaiterComponentList();
    }

    public void selectWaiter(int waiterId) {
        initAcceptedComponent();
        orderAcceptedComponent.getWaiterComponentList().get(waiterId).selectWaiter();
    }

    private void initAcceptedComponent() {
        if(orderAcceptedComponent == null) {
            orderAcceptedComponent = new OrderAcceptedComponent(orderExpansion);
        }
    }

    private void initConfirmationComponent() {
        if(orderConfirmationComponent == null) {
            orderConfirmationComponent = new OrderConfirmationComponent(orderExpansion);
        }
    }
}
