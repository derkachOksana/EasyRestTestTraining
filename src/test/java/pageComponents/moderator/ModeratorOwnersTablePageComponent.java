package pageComponents.moderator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ModeratorOwnersTablePageComponent {

    private WebDriver driver;

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> owners;

    private WebElement neededOwner;
    public ModeratorOwnersTablePageComponent(WebDriver driver, String ownerEmail)   {
        PageFactory.initElements(driver, this);
        this.driver = driver;
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
