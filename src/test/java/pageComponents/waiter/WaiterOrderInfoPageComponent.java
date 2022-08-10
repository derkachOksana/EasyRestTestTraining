package pageComponents.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaiterOrderInfoPageComponent {

    private final WebDriver driver;

    private final WebDriverWait wait;

    public WaiterOrderInfoPageComponent(WebDriver driver)   {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void startOrderBtnClick()    {
        WebElement startOrderBtn = driver.findElement(By.xpath(
                ".//button[contains(., 'Start order')]"));
        wait.until(ExpectedConditions.elementToBeClickable(startOrderBtn));
        startOrderBtn.click();
    }

    public void closeOrderBtnClick()    {
        WebElement closeOrderBtn = driver.findElement(By.xpath(
                ".//button[contains(., 'Close order')]"));
        wait.until(ExpectedConditions.elementToBeClickable(closeOrderBtn));
        closeOrderBtn.click();
    }
}
