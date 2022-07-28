package pages.administrator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdministratorPage {

    private final WebDriver driver;

    @FindBy(xpath = "//main/div/header")
    private WebElement tabDiv;

    public AdministratorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public TabPanelPage getTabPanelPage() {
        return new TabPanelPage(tabDiv);
    }

    public WaitingForConfirmTabPage getWaitingForConfirmTabPage() {
        return new WaitingForConfirmTabPage(driver);
    }

    public AcceptedTabPage getAcceptedTabPage() {
        return new AcceptedTabPage(driver);
    }
}