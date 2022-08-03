package pages.profile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WaitingForConfirmForClientPage {

    private final WebDriver driver;

    public WaitingForConfirmForClientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
