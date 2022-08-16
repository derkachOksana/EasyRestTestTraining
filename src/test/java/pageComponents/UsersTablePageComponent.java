package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UsersTablePageComponent {

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> users;

    public UsersTablePageComponent(WebDriver driver)   {
        PageFactory.initElements(driver, this);
    }

    private WebElement getNeededUser(String userEmail)  {
        WebElement neededUser = null;
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

    public String getUserStatus(String userEmail)   {
        return getNeededUser(userEmail).findElement(By.xpath(
                "./td[4]/p")).getText();
    }

    public void changeUserStatus(String userEmail)   {
        getNeededUser(userEmail).findElement(By.xpath(
                "./td[5]")).click();
    }
}
