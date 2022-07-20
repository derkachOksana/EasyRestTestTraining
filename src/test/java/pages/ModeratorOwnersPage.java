package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModeratorOwnersPage {
    private final WebDriver driver;
    private String ownerEmail;

    public ModeratorOwnersPage(WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setOwnerEmail(String ownerEmail)    {
        this.ownerEmail = ownerEmail;
    }

    @FindBy(xpath = "//div[contains(@class, 'UserMenu')]/*[button]")
    private WebElement userIconBtn;

    @FindBy(xpath = "//a[text()='Moderator panel']")
    private WebElement moderatorPanelAccessBtn;

    @FindBy(xpath = "//a[@href='/moderator/owners']")
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
        return driver.findElement(By.xpath("//*[contains(text(), '" + ownerEmail + "')]" +
                "/..//td[5]")).getAttribute("innerText");
    }

    public void banUnbanUser()   {
        driver.findElement(By.xpath("//*[contains(text(), '" + ownerEmail + "')]" +
                "/..//button")).click();
    }
}
