package pages.profile;

import org.openqa.selenium.WebDriver;
import pageComponents.HeaderGeneralPageComponent;
import pages.profile.components.ClientOrderInfoComponent;
import pages.profile.components.ClientHeaderPageComponent;
import pages.profile.components.ClientOrdersContainerComponent;

public class ProfileCurrentOrdersPage {

    protected WebDriver driver;
    public final HeaderGeneralPageComponent headerGeneralPageComponent;
    public final ClientHeaderPageComponent clientHeader;


    public ProfileCurrentOrdersPage(WebDriver driver) {
        this.driver = driver;
        headerGeneralPageComponent = new HeaderGeneralPageComponent(driver);
        clientHeader = new ClientHeaderPageComponent(driver);
    }
   /* public void getId (String nameRestaurant, String timeOrder, String totalSum) {
        orders.getOrderID(nameRestaurant, timeOrder, totalSum);
    }*/
}

