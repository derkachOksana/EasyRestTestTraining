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

    protected WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest logger;

    Faker faker;

    @BeforeSuite
    public void setUpReport()   {
        ExtentSparkReporter spark = new ExtentSparkReporter(ConfProperties.getProperty("reports.dir")
                + DateHandler.getCurrentDateTime() + ".html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

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

    @AfterSuite
    public void shutDown()  {
        extent.flush();
    }
}
