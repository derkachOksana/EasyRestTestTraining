package pages.administrator.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationComponent {

    private WebElement orderSummaryDiv;
    private WebDriverWait wait;

    @FindBy(xpath = "./div/div/div/div/div/div/button")
    private WebElement acceptBtn;

    public OrderConfirmationComponent(WebElement orderSummaryDiv, WebDriverWait wait) {
        PageFactory.initElements(orderSummaryDiv, this);
        this.orderSummaryDiv = orderSummaryDiv;
        this.wait = wait;
    }

    public void clickAcceptBtn() {
        wait.until(ExpectedConditions.visibilityOf(acceptBtn));
        acceptBtn.click();
    }


}
