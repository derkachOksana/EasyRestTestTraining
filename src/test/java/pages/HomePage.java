package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;

public class HomePage {

    private final WebDriver driver;

    @FindBy(xpath = "//a[contains(.,'View All')]")
    private WebElement viewAllTag;

    public HomePage(WebDriver driver)   {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public HeaderGeneralPageComponent getHeaderGeneralPageComponent() {
        return new HeaderGeneralPageComponent(driver);
    }

    public void clickViewAllTag() {
        viewAllTag.click();
    }
}
