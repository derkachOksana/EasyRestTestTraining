package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignInPage;
import utility.ConfProperties;


public class LoginTest extends BaseTest{
    public static SignInPage signInPage;

    @Test (groups = {"smokeTest", "regression"})
    public void enterEmptyEmailTest () throws InterruptedException {
        driver.get(ConfProperties.getProperty("homePage"));
        logger = extent.createTest("enterEmptyEmail test");
        signInPage = new SignInPage(driver);

        Thread.sleep(500);
        String exceptedText = "Email is required";

            Assert.assertEquals(signInPage.warningTextEnterEmptyEmail(), exceptedText);
    }
    @Test(groups = {"regression"})
    public void enterToAccountTest () throws InterruptedException {
        driver.get(ConfProperties.getProperty("homePage"));
        logger = extent.createTest("buttonSignInForRegisteredUser");
        signInPage = new SignInPage(driver);
        logger.info("Test is running!");

        Assert.assertFalse(signInPage.enterToAccount(), "Account exists");
        logger.pass("fvfvfvf");
        Thread.sleep(1000);
    }


}
