package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import utility.ConfProperties;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp()    {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("mainPage"));
    }

    @AfterClass
    public void closeDriver()  {
        driver.quit();
    }
}
