package pages.profile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AcceptedForClientPage {
    private final WebDriver driver;

    public AcceptedForClientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);

    }
}
