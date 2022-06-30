package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ConfProperties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utility.DateHandler;

public class BaseTest {

    public WebDriver driver;
    public ExtentReports extent;
    public static ExtentTest logger;
    public ExtentSparkReporter spark;
    Faker faker;

    @BeforeClass
    public void setUp() {
        faker = new Faker();
        spark = new ExtentSparkReporter(ConfProperties.getProperty("reportsFolder")
                + DateHandler.getCurrentDateTime() + ".html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void shutDown()  {
        driver.quit();
        extent.flush();
    }
}
