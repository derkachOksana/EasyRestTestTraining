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

    @FindBy(xpath = "//*[@id=\"root\"]/main/div/div[1]/div/span/h6")
    public WebElement signInTextElement;

    public String getSignInText()   {
        return signInTextElement.getText();
    }
}
