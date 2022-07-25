package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModeratorRestaurantsPage {
    private final WebDriver driver;
    private String restaurantName;

    public ModeratorRestaurantsPage(WebDriver driver)   {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setRestaurantName(String restaurantName)    {
        this.restaurantName = restaurantName;
    }

    @FindBy(xpath = "//div[contains(@class, 'UserMenu')]/*[button]")
    private WebElement userIconBtn;

    @FindBy(xpath = "//a[text()='Moderator panel']")
    private WebElement moderatorPanelAccessBtn;

    @FindBy(xpath = "//a[@href='/moderator/restaurants']")
    private WebElement restaurantsSheet;

    @FindBy(xpath = "//button[contains(., 'All')]")
    private WebElement allTab;

    @FindBy(xpath = "//button[contains(., 'Unapproved')]")
    private WebElement unapprovedTab;

    @FindBy(xpath = "//button[contains(., 'Approved')]")
    private WebElement approvedTab;

    @FindBy(xpath = "//button[contains(., 'Archived')]")
    private WebElement archivedTab;

    public void allTabAccess()  {
        allTab.click();
    }

    public void unapprovedTabAccess()   {
        unapprovedTab.click();
    }

    public void approvedTabAccess() {
        approvedTab.click();
    }

    public void archivedTabAccess() {
        archivedTab.click();
    }

    public String getRestaurantStatus() {
        return driver.findElement(By.xpath("//*[contains(text(), '" + restaurantName + "')]" +
                "/ancestor::div[3]" +
                "//*[@role='button']")).getAttribute("outerText");
    }

    public void approveRestaurant() {
        driver.findElement(By.xpath("//*[contains(text(), '" + restaurantName + "')]" +
                "/ancestor::div[3]" +
                "button[contains(., 'Approve')]")).click();
    }

    public void disapproveRestaurant()  {
        driver.findElement(By.xpath("//*[contains(text(), '" + restaurantName + "')]" +
                "/ancestor::div[3]" +
                "button[contains(., 'Disapprove')]")).click();
    }

    public void deleteRestaurant()  {
        driver.findElement(By.xpath("//*[contains(text(), '" + restaurantName + "')]" +
                "/ancestor::div[3]" +
                "button[contains(., 'Delete')]")).click();
    }

    public void restoreRestaurant() {
        driver.findElement(By.xpath("//*[contains(text(), '" + restaurantName + "')]" +
                "/ancestor::div[3]" +
                "button[contains(., 'Restore')]")).click();
    }
}
