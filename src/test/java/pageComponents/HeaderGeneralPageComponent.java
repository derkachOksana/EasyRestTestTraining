package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderGeneralPageComponent {
    @FindBy(xpath = "//body/div/header")
    private WebElement header;

    private WebElement userIconBtn = header.findElement(By.xpath(
            ".//div[contains(@class, 'UserMenu')]/*[button]"));

    @FindBy(xpath = "")
    private WebElement waiterPanelAccessBtn;

    @FindBy(xpath = "")
    private WebElement logOutBtn;
}
