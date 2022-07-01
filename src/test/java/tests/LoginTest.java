package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignInPage;
import utility.ConfProperties;


public class LoginTest extends BaseTest{
    public static SignInPage signInPage;

    @Test
    public void enterEmptyEmailTest () throws InterruptedException {
        driver.get(ConfProperties.getProperty("homePage"));
        logger = extent.createTest("enterEmptyEmail test");
        signInPage = new SignInPage(driver);

        //Thread.sleep(500);
        String exceptedText = "Email is required";

        Assert.assertEquals(signInPage.warningTextEnterEmptyEmail(), exceptedText);
    }
    @Test
    public void enterToAccountTest () throws InterruptedException {
        driver.get(ConfProperties.getProperty("homePage"));
        logger = extent.createTest("ButtonSignInForRegisteredUser");
        signInPage = new SignInPage(driver);
        logger.info("Test is running!");

        Assert.assertFalse(signInPage.enterToAccount(), "Account exists");
    }


}
