package pages.administrator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TabPanelPage {

    private final WebElement tabDiv;

    @FindBy(xpath = ".//button[contains(.,'Waiting for confirm')]")
    private WebElement waitingForConfirmTab;

    @FindBy(xpath = ".//button[contains(.,'Accepted')]")
    private WebElement acceptedTab;

    public TabPanelPage(WebElement tabDiv ) {
        this.tabDiv = tabDiv;
        PageFactory.initElements(this.tabDiv, this);
    }

    public void waitingForConfirmAccess() {
        waitingForConfirmTab.click();
    }

    public void clickAcceptedTab() {
        acceptedTab.click();
    }

}
