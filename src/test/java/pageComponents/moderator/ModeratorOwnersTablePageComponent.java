package pageComponents.moderator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ModeratorOwnersTablePageComponent {

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> owners;

    private WebElement neededOwner;
    public ModeratorOwnersTablePageComponent(String ownerEmail)   {
        for(WebElement owner : owners)    {
            String currentOwnerEmail = owner.findElement(By.xpath(
                    "./td")).getText();
            if(currentOwnerEmail.equals(ownerEmail))  {
                neededOwner = owner;
                break;
            }
        }
    }

    public String getOwnerStatus()  {
        return neededOwner.findElement(By.xpath(
                "./td[5]/p")).getText();
    }

    public void changeOwnerStatus() {
        neededOwner.findElement(By.xpath(
                "./td[6]")).click();
    }
}
