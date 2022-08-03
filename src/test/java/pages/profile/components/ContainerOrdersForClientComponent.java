package pages.profile.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContainerOrdersForClientComponent {

    @FindBy (xpath = "//main//header/following-sibling::div")
    private List<WebElement> containerOfOrders;
}
