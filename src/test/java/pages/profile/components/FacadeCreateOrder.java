package pages.profile.components;

import pages.MenuPage;
import pages.RestaurantsPage;
import pages.profile.MyProfilePage;
import pages.profile.ProfileCurrentOrdersPage;

import java.time.Duration;

public class FacadeCreateOrder {
    private RestaurantsPage restaurantsPage;
    private MenuPage menuPage;


    public FacadeCreateOrder(RestaurantsPage restaurantsPage, MenuPage menuPage) {
        this.restaurantsPage = restaurantsPage;
        this.menuPage = menuPage;
    }

    public void createOrder (String restaurantName, String menuItem1, Duration duration) {
        menuPage = restaurantsPage.watchMenuByRestName(restaurantName);
        menuPage.menuItems.addToCartByItemName(menuItem1);
        menuPage.submitOrder();
        menuPage.orderConfirmation.submitOrder(duration);
    }
}
