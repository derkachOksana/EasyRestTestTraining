package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePersonalInfoPage {
    private final WebDriver driver;

    public ProfilePersonalInfoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[text()='My Profile']")
    private WebElement myProfileBtn;

    @FindBy(xpath = "//*[text()='Current Orders']")
    private WebElement currentOrdersBtn;

    @FindBy(xpath = "//*[text()='Order History']")
    private WebElement orderHistoryBtn;

    public void myProfileAccess() {
        myProfileBtn.click();
    }
    public void currentOrdersAccess() {
        currentOrdersBtn.click();
    }
    public void orderHistoryAccess() {
        orderHistoryBtn.click();
    }
}
