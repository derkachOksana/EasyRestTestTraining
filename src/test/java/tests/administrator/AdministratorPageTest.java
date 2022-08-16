package tests.administrator;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;
import pages.administrator.AdministratorPage;
import pages.administrator.order.OrderComponent;
import tests.BaseTest;

import java.time.Duration;

public class AdministratorPageTest extends BaseTest {

    private final String administratorEmail = "eringonzales@test.com";
    private final String administratorPassword = "1";
    private final String orderId = "177";
    private final String ADMIN_URL = "http://miniserver:8880/administrator-panel";

    private SignInPage signInPage;
    private AdministratorPage administratorPage;
    private WebDriverWait wait;

    @BeforeClass
    public void precondition() {
        HomePage homePage = new HomePage(driver);
        driver.get("http://miniserver:8880/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
    }

    @BeforeMethod
    public void administratorSignIn() {
        signInPage.setUserEmailInputField(administratorEmail);
        signInPage.setUserPasswordInputField(administratorPassword);
        signInPage.clickSignInBtn();
        administratorPage = new AdministratorPage(driver);
        wait.until(ExpectedConditions.urlContains(ADMIN_URL));
    }

    @Test
    public void checkAccessToAdministratorPageTest501() {
        logger = extent.createTest("Administrator page test 5.01");

        Assert.assertEquals(driver.getCurrentUrl(), ADMIN_URL);

    }

    @Test
    public void checkOrderConfirmationByAdministrator502() {
        logger = extent.createTest("Administrator page test 5.02");
        OrderComponent orderToConfirm = administratorPage.getOrderById(orderId);
        orderToConfirm.clickDropDownBtn();
        orderToConfirm.acceptOrder();
        administratorPage.getTabPanelPage().switchToAcceptedTab();
        OrderComponent acceptedOrder = administratorPage.getOrderById(orderId);
        wait.until(ExpectedConditions.invisibilityOfAllElements());
        Assert.assertEquals(orderToConfirm, acceptedOrder);









    }

    @AfterMethod
    public void administratorLogOut() {
       signInPage = administratorPage
               .getHeaderGeneralPageComponent()
               .userMenu()
               .logOut();
    }

}
