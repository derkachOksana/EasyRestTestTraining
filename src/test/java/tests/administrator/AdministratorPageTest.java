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
import utility.DataBaseConnection;

import java.time.Duration;

public class AdministratorPageTest extends BaseTest {

    private final String administratorEmail = "eringonzales@test.com";
    private final String administratorPassword = "1";
    private final String orderId = "1";
    private final String ADMINISTRATOR_URL = "http://ec2-13-42-10-104.eu-west-2.compute.amazonaws.com:3000/administrator-panel";
    private final String SQL_FILE = "src/test/resources/datasource/administrator/administrator_datasource.sql";

    private SignInPage signInPage;
    private AdministratorPage administratorPage;
    private WebDriverWait wait;
    private String orderStatus;

    @BeforeClass
    public void precondition() {
        DataBaseConnection.getInstance().execute(SQL_FILE);
        HomePage homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        wait.until(ExpectedConditions.urlContains(ADMINISTRATOR_URL));
    }

    @Test
    public void checkAccessToAdministratorPageTest501() {
        /*logger = extent.createTest("Administrator page test 5.01");*/

        Assert.assertEquals(driver.getCurrentUrl(), ADMINISTRATOR_URL);
    }

    @Test
    public void checkOrderConfirmationByAdministratorTest502() {
        /*logger = extent.createTest("Administrator page test 5.02");*/
        OrderComponent orderToConfirm = administratorPage.getOrderById(orderId);
        orderToConfirm.clickDropDownBtn();
        orderToConfirm.acceptOrder();
        administratorPage.getTabPanelPage().switchToAcceptedTab();
        AdministratorPage acceptedTab = new AdministratorPage(driver);
        OrderComponent acceptedOrder = acceptedTab.getOrderById(orderId);
        orderStatus = acceptedOrder.getOrderStatus();
        Assert.assertEquals(orderStatus, "Accepted");
    }

    @Test
    public void checkPossibilityToAssignWaiterTest503() {
        /*logger = extent.createTest("Administrator page Test 5.03");*/
        AdministratorPage acceptedTab = new AdministratorPage(driver);
        acceptedTab.getTabPanelPage().switchToAcceptedTab();
        OrderComponent acceptedOrder = administratorPage.getOrderById(orderId);
        acceptedOrder.clickDropDownBtn();
        acceptedOrder.selectWaiter(1);
        acceptedOrder.assignWaiter();
        wait.until(ExpectedConditions.invisibilityOfAllElements());
        acceptedTab.getTabPanelPage().switchToAssignedWaiterTab();
        AdministratorPage assignedWaiterTab = new AdministratorPage(driver);
        OrderComponent assignedWaiterOrder = assignedWaiterTab.getOrderById(orderId);
        orderStatus = assignedWaiterOrder.getOrderStatus();
        Assert.assertEquals(orderStatus, "Assigned waiter");
    }

    @AfterMethod
    public void administratorLogOut() {
       signInPage = administratorPage
               .getHeaderGeneralPageComponent()
               .userMenu()
               .logOut();
    }
}
