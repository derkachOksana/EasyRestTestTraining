package pages.profile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class InProgressForClientPage {

    private final WebDriver driver;

    public InProgressForClientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
