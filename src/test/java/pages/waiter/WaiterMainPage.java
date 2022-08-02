package pages.waiter;

import org.openqa.selenium.WebDriver;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.waiter.WaiterHeaderPageComponent;
import pageComponents.waiter.WaiterOrdersPageComponent;

public class WaiterMainPage {

    protected WebDriver driver;

    public final HeaderGeneralPageComponent headerGeneralPageComponent;
    public final WaiterHeaderPageComponent waiterHeader;
    public final WaiterOrdersPageComponent orders;

    public WaiterMainPage(WebDriver driver) {
        this.driver = driver;
        headerGeneralPageComponent = new HeaderGeneralPageComponent(driver);
        waiterHeader = new WaiterHeaderPageComponent(driver);
        orders = new WaiterOrdersPageComponent(driver);
    }
}
