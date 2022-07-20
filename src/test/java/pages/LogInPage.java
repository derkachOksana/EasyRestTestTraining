package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
    private final WebDriver driver;

    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[text() = 'to continue E-Restaurant']")
    public WebElement signInTextElement;

    public String getSignInText()   {
        return signInTextElement.getText();
    }
}
