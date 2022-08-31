package tests.moderator;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SignInPage;
import pages.moderator.ModeratorBasePage;
import pages.moderator.ModeratorRestaurantsPage;
import tests.BaseTest;
import utility.ConfProperties;
import utility.DataBaseConnection;
import utility.RegistrationData;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ModeratorRestaurantStatusTest extends BaseTest {

    private SignInPage signInPage;
    private ModeratorRestaurantsPage moderatorRestaurantsPage;

    private String restaurantStatus;

    private RegistrationData moderator;
    private final List<RegistrationData> restaurants = new ArrayList<>();

    private final Duration duration = Duration.ofSeconds(Integer.parseInt(ConfProperties.getProperty("duration")));

    @BeforeClass
    public void preconditions() {
        final int MODERATOR_ROLE_ID = 3;
        moderator = DataBaseConnection.getInstance().createAccByRole(driver, MODERATOR_ROLE_ID);
        for(int i = 0; i < 4; i++)  {
            restaurants.add(DataBaseConnection.getInstance().createRestaurant());
        }

        signInPage = new SignInPage(driver);
    }

    @BeforeMethod
    public void moderatorLogIn()    {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("signInPage")));

        signInPage.setUserEmailInputField(moderator.getEmail());
        signInPage.setUserPasswordInputField(moderator.getPassword());
        signInPage.clickSignInBtn();

        ModeratorBasePage moderatorBasePage = new ModeratorBasePage(driver);
        moderatorRestaurantsPage = moderatorBasePage.restaurantsPageAccess();
    }

    @Test
    public void moderatorRestaurantStatusTest301()  {
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorUnapprovedRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.approveRestaurant(restaurants.get(0).getName());
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorApprovedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(restaurants.get(0).getName());

        Assert.assertEquals(restaurantStatus, "Approved");
    }

    @Test
    public void moderatorRestaurantStatusTest302()  {
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorApprovedRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.deleteRestaurant(restaurants.get(0).getName());
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorArchivedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(restaurants.get(0).getName());

        Assert.assertEquals(restaurantStatus, "Archived");
    }

    @Test
    public void moderatorRestaurantStatusTest303()  {
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorUnapprovedRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.disapproveRestaurant(restaurants.get(1).getName());
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorArchivedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(restaurants.get(1).getName());

        Assert.assertEquals(restaurantStatus, "Archived");
    }

    @Test
    public void moderatorRestaurantStatusTest304()  {
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorArchivedRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.restoreRestaurant(restaurants.get(1).getName());
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorApprovedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(restaurants.get(1).getName());

        Assert.assertEquals(restaurantStatus, "Approved");
    }

    @Test
    public void moderatorRestaurantStatusTest305()  {
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorAllRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.approveRestaurant(restaurants.get(2).getName());
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorApprovedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(restaurants.get(2).getName());

        Assert.assertEquals(restaurantStatus, "Approved");
    }

    @Test
    public void moderatorRestaurantStatusTest306()  {
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorAllRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.deleteRestaurant(restaurants.get(2).getName());
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorArchivedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(restaurants.get(2).getName());

        Assert.assertEquals(restaurantStatus, "Archived");
    }

    @Test
    public void moderatorRestaurantStatusTest307()  {
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorAllRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.disapproveRestaurant(restaurants.get(3).getName());
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorArchivedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(restaurants.get(3).getName());

        Assert.assertEquals(restaurantStatus, "Archived");
    }

    @Test
    public void moderatorRestaurantStatusTest308()  {
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorAllRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.restoreRestaurant(restaurants.get(3).getName());
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorApprovedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(restaurants.get(3).getName());

        Assert.assertEquals(restaurantStatus, "Approved");
    }

    @AfterMethod
    public void moderatorLogOut()  {
        signInPage = moderatorRestaurantsPage
                .headerGlobal
                .userMenu()
                .logOut();
    }

    @AfterClass
    public void postonditions()
    {
        DataBaseConnection.getInstance().deleteUserByEmail(moderator.getEmail());
        for (int i = 0; i < 4; i++) {
            DataBaseConnection.getInstance().deleteRestaurantByName(restaurants.get(i).getName());
        }
    }
}