package pages.administrator.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationComponent {

    private WebElement orderSummaryDiv;

    @FindBy(xpath = "./div/div/div/div/div/div/button")
    private WebElement acceptBtn;

    public OrderConfirmationComponent(WebElement orderSummaryDiv) {
        PageFactory.initElements(orderSummaryDiv, this);
        this.orderSummaryDiv = orderSummaryDiv;
    }

    public void clickAcceptBtn() {
        acceptBtn.click();
    }


}
