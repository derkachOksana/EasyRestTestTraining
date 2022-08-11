package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import utility.ConfProperties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import utility.DateHandler;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest logger;

    Faker faker;

    @BeforeSuite
    public void setUpReport()   {
        ExtentSparkReporter spark = new ExtentSparkReporter(ConfProperties.getProperty("reportsFolder")
                + DateHandler.getCurrentDateTime() + ".html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeTest
    public void setUp()    {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("mainPage"));
    }

    @AfterTest
    public void closeDriver()  {
        driver.close();
    }

    @AfterSuite
    public void shutDown()  {
        extent.flush();
        driver.quit();
    }
}
