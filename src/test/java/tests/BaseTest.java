package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import utility.ConfProperties;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

    protected WebDriver driver;

    //THIS CODE IS ONLY FOR PARALLEL TESTING AT BROWSERSTACK CLOUD GK
    //DO NOT UNCOMMENT IT (ONLY 100 FREE MINUTES)

    /*public static final String USERNAME = "grygoriikyslenko_ti0gaC";
    public static final String AUTOMATE_KEY = "iDxzRqr4dzfqdjcTpApM";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Parameters({"os", "osVersion", "browserName", "browserVersion", "sessionName"})
    @BeforeClass
    public void setUp(String os, String osVersion, String browserName, String browserVersion, String sessionName)   {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        capabilities.setCapability("sessionName", sessionName);

        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("os", os);
        browserstackOptions.put("osVersion", osVersion);

        capabilities.setCapability("bstack:options", browserstackOptions);
        capabilities.setCapability("browserstack.networkLogs", true);

        HashMap<String, Boolean> networkLogsOptions = new HashMap<>();
        networkLogsOptions.put("captureContent", true);
        capabilities.setCapability("browserstack.networkLogsOptions", networkLogsOptions);

        try {
            driver = new RemoteWebDriver(new URL(URL), capabilities);
        }   catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("mainPage"));
    }*/

    @BeforeClass
    public void setUp()   {
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
