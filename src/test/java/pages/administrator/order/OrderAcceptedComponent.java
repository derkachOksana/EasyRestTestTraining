package pages.administrator.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OrderAcceptedComponent {

    private WebElement orderSummaryDiv;
    //private List<WaiterComponent> waiterComponentList;

    @FindBy(xpath = "./div/div/div/div/div/div/button")
    private WebElement assignBtn;

    @FindBy(xpath = "./div/div/div/div/div/div/fieldset/div/label")
    private List<WebElement> waiterList;


    public OrderAcceptedComponent(WebElement orderSummaryDiv) {
        PageFactory.initElements(orderSummaryDiv, this);
        this.orderSummaryDiv = orderSummaryDiv;
    }

    public void clickAssignBtn() {
        assignBtn.click();
    }

    public List<WaiterComponent> getWaiterComponentList() {
        return waiterList.stream()
                .map(waiter -> new WaiterComponent(waiter))
                .collect(Collectors.toList());
    }
}
