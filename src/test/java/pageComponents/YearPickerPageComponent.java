package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class YearPickerPageComponent {

    private WebDriver driver;
    private int desiredYear;
    private JavascriptExecutor js;

    public YearPickerPageComponent(WebDriver driver, int year)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        desiredYear = year;
        js = (JavascriptExecutor) driver;
    }

    public void setYear()   {
        WebElement year = driver.findElement(By.xpath(
                "//div[contains(@class, 'YearSelection')]" +
                        "/*[text()='" + desiredYear + "']"));
        js.executeScript("window.scrollTo(0,0);");
        js.executeScript("arguments[0].scrollIntoView();", year);
        year.click();
    }
}
