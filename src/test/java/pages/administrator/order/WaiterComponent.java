package pages.administrator.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WaiterComponent {

    private WebElement waiterLabel;

    @FindBy(xpath = ".//input[@type='radio']")
    private WebElement ratioBtn;

    @FindBy(xpath = ".//span[contains(., 'Waiter')]")
    private WebElement name;


    public WaiterComponent(WebElement waiterLabel) {
        PageFactory.initElements(waiterLabel, this);
        this.waiterLabel = waiterLabel;
    }

    public void selectWaiter() {
        ratioBtn.click();
    }

    public WebElement getName() {
        return name;
    }
}
