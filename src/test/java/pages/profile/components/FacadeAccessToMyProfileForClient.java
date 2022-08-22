package pages.profile.components;

import pages.MenuPage;
import pages.profile.MyProfilePage;

public class FacadeAccessToMyProfileForClient {
    private MenuPage menuPage;

    public FacadeAccessToMyProfileForClient(MenuPage menuPage) {
        this.menuPage = menuPage;
    }

    public MyProfilePage accessToMyProfile () {
        return menuPage.headerGlobal.userMenu().myProfileAccess();
    }
}
