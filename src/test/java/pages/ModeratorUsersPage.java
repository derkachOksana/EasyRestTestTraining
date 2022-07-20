package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModeratorUsersPage {
    private final WebDriver driver;
    private String userEmail;

    public ModeratorUsersPage(WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setUserEmail(String userEmail)    {
        this.userEmail = userEmail;
    }

    @FindBy(xpath = "//div[contains(@class, 'UserMenu')]/*[button]")
    private WebElement userIconBtn;

    @FindBy(xpath = "//a[text()='Moderator panel']")
    private WebElement moderatorPanelAccessBtn;

    @FindBy(xpath = "//a[@href='/moderator/users']")
    private WebElement usersSheet;

    @FindBy(xpath = "//button[contains(., 'All')]")
    private WebElement allTab;

    @FindBy(xpath = "//button[contains(., 'Active')]")
    private WebElement activeTab;

    @FindBy(xpath = "//button[contains(., 'Banned')]")
    private WebElement bannedTab;

    public void allTabAccess()  {
        allTab.click();
    }

    public void activeTabAccess()   {
        activeTab.click();
    }

    public void bannedTabAccess()   {
        bannedTab.click();
    }

    public String getUserStatus()   {
        return driver.findElement(By.xpath("//*[contains(text(), '" + userEmail + "')]" +
                "/..//td[4]")).getAttribute("innerText");
    }

    public void banUnbanUser()   {
        driver.findElement(By.xpath("//*[contains(text(), '" + userEmail + "')]" +
                "/..//button")).click();
    }
}
