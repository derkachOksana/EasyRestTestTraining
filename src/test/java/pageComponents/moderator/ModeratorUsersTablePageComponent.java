package pageComponents.moderator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ModeratorUsersTablePageComponent {

    private WebElement neededUser;
    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> users;

    public ModeratorUsersTablePageComponent(String userEmail)   {
        for(WebElement user : users)    {
            String currentUserEmail = user.findElement(By.xpath(
                    "./td")).getText();
            if(currentUserEmail.equals(userEmail))  {
                neededUser = user;
                break;
            }
        }
    }

    public String getUserStatus()   {
        return neededUser.findElement(By.xpath(
                "./td[4]/p")).getText();
    }

    public void changeUserStatus()   {
        neededUser.findElement(By.xpath(
                "/td[5]")).click();
    }
}
