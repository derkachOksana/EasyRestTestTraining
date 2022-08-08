package pages.administrator.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WaiterComponent {

    @FindBy(xpath = ".//input[@type='radio']")
    private WebElement ratioBtn;

    @FindBy(xpath = ".//span[contains(., 'Waiter')]")
    private WebElement name;

    public WaiterComponent(WebElement waiterLabel) {
        PageFactory.initElements(waiterLabel, this);
    }

    public void selectWaiter() {
        ratioBtn.click();
    }

    public String getName() {
       return name.getAttribute("innerHTML")
               .replaceAll("(The Waiter \\d+)", "")
               .trim();
    }
}
