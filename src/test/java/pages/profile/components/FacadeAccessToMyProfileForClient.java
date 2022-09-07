package pages.profile.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MenuPage;
import pages.profile.MyProfilePage;
import utility.ConfProperties;

import java.time.Duration;

public class FacadeAccessToMyProfileForClient {
    private MenuPage menuPage;
    public FacadeAccessToMyProfileForClient(MenuPage menuPage) {
        this.menuPage = menuPage;
    }
    public MyProfilePage accessToMyProfile (Duration duration) {
        return menuPage.headerGlobal.userMenu(duration).myProfileAccess();
    }
}
