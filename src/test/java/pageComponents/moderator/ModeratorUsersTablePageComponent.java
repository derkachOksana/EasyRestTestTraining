package pageComponents.moderator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ModeratorUsersTablePageComponent {

    private String userEmail;
    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> users;

    public ModeratorUsersTablePageComponent(WebDriver driver)   {
        PageFactory.initElements(driver, this);
    }

    public void setUserEmail(String userEmail)  {
        this.userEmail = userEmail;
    }

    private WebElement getNeededUser()  {
        WebElement neededUser = users.get(0);
        for(WebElement user : users)    {
            String currentUserEmail = user.findElement(By.xpath(
                    "./td")).getText();
            if(currentUserEmail.equals(userEmail))  {
                neededUser = user;
                break;
            }
        }
        return neededUser;
    }

    public String getUserStatus()   {
        return getNeededUser().findElement(By.xpath(
                "./td[4]/p")).getText();
    }

    public void changeUserStatus()   {
        getNeededUser().findElement(By.xpath(
                "/td[5]")).click();
    }
}
