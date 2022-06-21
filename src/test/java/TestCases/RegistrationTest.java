package TestCases;

import Pages.SignUpPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest{
    public static WebDriver driver;
    public static SignUpPage signUpPage;

    @Test
    public void Register() throws InterruptedException {
        Faker faker = new Faker();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        String password = faker.gameOfThrones().dragon() + faker.pokemon().name();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/sign-up");
        signUpPage = new SignUpPage(driver);
        signUpPage.Register(faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(),
                faker.date().birthday(4, 10),
                password);
        Thread.sleep(1000);
        driver.close();
    }
}
