package Tests;

import Pages.SignUpPage;
import Pages.StartPage;
import Utility.ConfProperties;

import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest{
    public static StartPage startPage;

    @Test (groups = {"smoke", "regression"})
    public void enterEmptyEmailTest () throws InterruptedException {
        driver.get(ConfProperties.getProperty("startPage"));
        logger = extent.createTest("enterEmptyEmail test");
        startPage = new StartPage(driver);

        Thread.sleep(500);
        String exceptedText = "Email is required";

            Assert.assertEquals(startPage.warningTextEnterEmptyEmail(), exceptedText);
            logger.pass("The correct text appears");

            //logger.fail("Doesn`t correct text appear");

    }
    @Test(groups = {"smoke"})
    public void enterToAccountTest () throws InterruptedException {
        driver.get(ConfProperties.getProperty("startPage"));
        logger = extent.createTest("buttonSignInForRegisteredUser");
        startPage = new StartPage(driver);
        logger.info("Test is running!");

        Assert.assertTrue(startPage.enterToAccount(), "Account exists");
        logger.pass("Account exists");


            logger.fail("Something went wrong, cann`t enter to own account");











    }


}
