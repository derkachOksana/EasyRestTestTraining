package pages.profile;

import org.openqa.selenium.WebDriver;
import pageComponents.HeaderGeneralPageComponent;
import pages.profile.components.ClientHeaderPageComponent;
import pages.profile.components.ClientOrderInfoComponent;
import pages.profile.components.ClientOrdersContainerComponent;

public class ProfileOrderHistoryPage {

    protected WebDriver driver;
    public final HeaderGeneralPageComponent headerGeneralPageComponent;
    public final ClientHeaderPageComponent clientHeader;
   // public final ProfileCurrentOrdersPage currentOrdersPage;


    public ProfileOrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        headerGeneralPageComponent = new HeaderGeneralPageComponent(driver);
        clientHeader = new ClientHeaderPageComponent(driver);
       // currentOrdersPage = new ProfileCurrentOrdersPage(driver);
    }
}
