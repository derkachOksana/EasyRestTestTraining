package pages.profile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;

public class MyProfilePage {

    protected WebDriver driver;
    public final HeaderGeneralPageComponent headerGeneralPageComponent;
    public MyProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        headerGeneralPageComponent = new HeaderGeneralPageComponent(driver);
    }
    @FindBy(xpath = "//*[text()='Current Orders']")
    private WebElement currentOrdersBtn;

    @FindBy(xpath = "//*[text()='Order History']")
    private WebElement orderHistoryBtn;

    public ProfileCurrentOrdersPage currentOrdersAccess() {

        ProfileCurrentOrdersPage currentOrdersPage = new ProfileCurrentOrdersPage(driver);
        currentOrdersBtn.click();
        return currentOrdersPage;
    }
    public ProfileOrderHistoryPage orderHistoryAccess() {

        ProfileOrderHistoryPage orderHistoryPage = new ProfileOrderHistoryPage(driver);
        orderHistoryBtn.click();
        return orderHistoryPage;
    }
}
