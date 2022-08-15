package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.moderator.ModeratorUsersTablePageComponent;

import java.util.List;

public class AdminPage {

    private final WebDriver driver;

    public final HeaderGeneralPageComponent headerGlobal;

    @FindBy(xpath = "//a[@href='/admin/moderators']")
    private WebElement moderatorSheetAccess;

    @FindBy(xpath = "//a[@href='/admin/users']")
    private WebElement userSheetAccess;

    @FindBy(xpath = "//*[text()='Add moderator']")
    private WebElement moderatorAddBtn;

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> moderators;

    public AdminPage(WebDriver driver)  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        headerGlobal = new HeaderGeneralPageComponent(driver);
    }

    public SignUpPage createModeratorAccount() {
        moderatorAddBtn.click();
        return new SignUpPage(driver);
    }

    public AdminPage moderatorSheetAccess() {
        moderatorSheetAccess.click();
        return new AdminPage(driver);
    }

    public ModeratorUsersTablePageComponent usersTable()    {
        userSheetAccess.click();
        return new ModeratorUsersTablePageComponent(driver);
    }

    private WebElement getNeededModerator(String userEmail)  {
        WebElement neededModerator = null;
        for(WebElement moderator : moderators)    {
            String currentUserEmail = moderator.findElement(By.xpath(
                    "./td")).getText();
            if(currentUserEmail.equals(userEmail))  {
                neededModerator = moderator;
                break;
            }
        }
        return neededModerator;
    }

    public void changeModeratorStatus(String userEmail)   {
        getNeededModerator(userEmail).findElement(By.xpath(
                "./td[5]")).click();
    }
}
