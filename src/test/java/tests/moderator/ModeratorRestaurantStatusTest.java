package tests.moderator;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SignInPage;
import pages.moderator.ModeratorBasePage;
import pages.moderator.ModeratorRestaurantsPage;
import tests.BaseTest;

public class ModeratorRestaurantStatusTest extends BaseTest {

    /*
    ***************************************************

            Fields used without preconditions

    ***************************************************
    */

    private final String moderatorEmail = "petermoderator@test.com";
    private final String moderatorPassword = "1";

    private final String firstRestaurant = "Edwards-Silva";
    private final String secondRestaurant = "Gray Group";
    private final String thirdRestaurant = "Hardy Group";
    private final String fourthRestaurant = "Chapman PLC";

    /*
    ************************************************
        End of fields used without preconditions
    ************************************************
    */

    private SignInPage signInPage;
    private ModeratorRestaurantsPage moderatorRestaurantsPage;

    private String restaurantStatus;

    @BeforeClass
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        signInPage = homePage
                .getHeaderGeneralPageComponent()
                .signInAccess();
    }

    @BeforeMethod
    public void moderatorLogIn()    {
        signInPage.setUserEmailInputField(moderatorEmail);
        signInPage.setUserPasswordInputField(moderatorPassword);
        signInPage.clickSignInBtn();
        ModeratorBasePage moderatorBasePage = new ModeratorBasePage(driver);
        moderatorRestaurantsPage = moderatorBasePage.restaurantsPageAccess();
    }

    @Test
    public void moderatorRestaurantStatusTest301()  {
        logger = extent.createTest("Moderator restaurant status test 3.01");

        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorUnapprovedRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.approveRestaurant(firstRestaurant);
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorApprovedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(firstRestaurant);

        Assert.assertEquals(restaurantStatus, "Approved");
    }

    @Test
    public void moderatorRestaurantStatusTest302()  {
        logger = extent.createTest("Moderator restaurant status test 3.02");

        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorApprovedRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.deleteRestaurant(firstRestaurant);
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorArchivedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(firstRestaurant);

        Assert.assertEquals(restaurantStatus, "Archived");
    }

    @Test
    public void moderatorRestaurantStatusTest303()  {
        logger = extent.createTest("Moderator restaurant status test 3.03");

        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorUnapprovedRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.disapproveRestaurant(secondRestaurant);
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorArchivedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(secondRestaurant);

        Assert.assertEquals(restaurantStatus, "Archived");
    }

    @Test
    public void moderatorRestaurantStatusTest304()  {
        logger = extent.createTest("Moderator restaurant status test 3.04");

        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorArchivedRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.restoreRestaurant(secondRestaurant);
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorApprovedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(secondRestaurant);

        Assert.assertEquals(restaurantStatus, "Approved");
    }

    @Test
    public void moderatorRestaurantStatusTest305()  {
        logger = extent.createTest("Moderator restaurant status test 3.05");

        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorAllRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.approveRestaurant(thirdRestaurant);
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorApprovedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(thirdRestaurant);

        Assert.assertEquals(restaurantStatus, "Approved");
    }

    @Test
    public void moderatorRestaurantStatusTest306()  {
        logger = extent.createTest("Moderator restaurant status test 3.06");

        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorAllRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.deleteRestaurant(thirdRestaurant);
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorArchivedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(thirdRestaurant);

        Assert.assertEquals(restaurantStatus, "Archived");
    }

    @Test
    public void moderatorRestaurantStatusTest307()  {
        logger = extent.createTest("Moderator restaurant status test 3.07");

        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorAllRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.disapproveRestaurant(fourthRestaurant);
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorArchivedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(thirdRestaurant);

        Assert.assertEquals(restaurantStatus, "Archived");
    }

    @Test
    public void moderatorRestaurantStatusTest308()  {
        logger = extent.createTest("Moderator restaurant status test 3.08");

        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorAllRestaurantsTabAccess();
        moderatorRestaurantsPage.restaurants.restoreRestaurant(fourthRestaurant);
        moderatorRestaurantsPage = moderatorRestaurantsPage.header.moderatorApprovedRestaurantsTabAccess();

        restaurantStatus = moderatorRestaurantsPage.restaurants.getRestaurantStatus(fourthRestaurant);

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

    }
}