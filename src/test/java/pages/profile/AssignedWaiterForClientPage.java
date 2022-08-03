package pages.profile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AssignedWaiterForClientPage {

    private final WebDriver driver;

    public AssignedWaiterForClientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
