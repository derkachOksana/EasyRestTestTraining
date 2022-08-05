package pages.administrator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;

public class AdministratorPage {

    private final WebDriver driver;
    private HeaderGeneralPageComponent headerGeneralPageComponent;
    private TabPanelPage tabPanelPage;
    private WaitingForConfirmTabPage waitingForConfirmTabPage;
    private AcceptedTabPage acceptedTabPage;

    @FindBy(xpath = "//main/div/header")
    private WebElement tabDiv;

    public AdministratorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    private void initHeaderGeneralPageComponent() {
        if(headerGeneralPageComponent == null) {
            headerGeneralPageComponent = new HeaderGeneralPageComponent(driver);
        }
    }
    private void initTabPanelPage() {
        if(tabPanelPage == null) {
            tabPanelPage = new TabPanelPage(tabDiv);
        }
    }
    private void initWaitingForConfirmTabPage() {
        if(waitingForConfirmTabPage == null) {
            waitingForConfirmTabPage = new WaitingForConfirmTabPage(driver);
        }
    }
    private void initAcceptedTabPage() {
        if(acceptedTabPage == null) {
            acceptedTabPage = new AcceptedTabPage(driver);
        }
    }
}
