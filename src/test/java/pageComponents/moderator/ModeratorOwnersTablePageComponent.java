package pageComponents.moderator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ModeratorOwnersTablePageComponent {

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> owners;

    public ModeratorOwnersTablePageComponent(WebDriver driver)   {
        PageFactory.initElements(driver, this);
    }

    private WebElement getNeededOwner(String ownerEmail) {
        WebElement neededOwner = null;
        for(WebElement owner : owners)    {
            String currentOwnerEmail = owner.findElement(By.xpath(
                    "./td")).getText();
            if(currentOwnerEmail.equals(ownerEmail))  {
                neededOwner = owner;
                break;
            }
        }
        return neededOwner;
    }

    public String getOwnerStatus(String ownerEmail)  {
        return getNeededOwner(ownerEmail).findElement(By.xpath(
                "./td[5]/p")).getText();
    }

    public void changeOwnerStatus(String ownerEmail) {
        getNeededOwner(ownerEmail).findElement(By.xpath(
                "./td[6]")).click();
    }
}
