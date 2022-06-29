package Tests;

import Utility.ConfProperties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public WebDriver driver;
    public ExtentReports extent;
    public ExtentTest logger;
    public ExtentSparkReporter spark;
    Faker faker;

    @BeforeClass
    public void setUp() {
        faker = new Faker();
        spark = new ExtentSparkReporter(ConfProperties.getProperty("reportsFolder") + ConfProperties.getCurrentDateTime() + ".html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        //System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = WebDriverManager.chromedriver().create();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void shutDown()  {
       // driver.quit();
        extent.flush();
    }
}
