package pages.profile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AllForClientPage {

    private final WebDriver driver;

    public AllForClientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
}
