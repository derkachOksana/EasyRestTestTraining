package pages.administrator.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class OrderAcceptedComponent {

    private final WebElement orderSummaryDiv;
    private WebDriverWait wait;

    @FindBy(xpath = "./div/div/div/div/div/div/button")
    private WebElement assignBtn;

    @FindBy(xpath = "./div/div/div/div/div/div/fieldset/div/label")
    private List<WebElement> waiterList;


    public OrderAcceptedComponent(WebElement orderSummaryDiv, WebDriverWait wait) {
        PageFactory.initElements(orderSummaryDiv, this);
        this.orderSummaryDiv = orderSummaryDiv;
        this.wait = wait;
    }

    public void clickAssignBtn() {
        wait.until(ExpectedConditions.visibilityOf(assignBtn));
        assignBtn.click();
    }

    public List<WaiterComponent> getWaiterComponentList() {
        return waiterList.stream()
                .map(waiter -> new WaiterComponent(waiter))
                .collect(Collectors.toList());
    }
}
