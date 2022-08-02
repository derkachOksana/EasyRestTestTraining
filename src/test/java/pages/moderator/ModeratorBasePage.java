package pages.moderator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.moderator.ModeratorHeaderPageComponent;
import pages.SignInPage;

public class ModeratorBasePage {
    protected WebDriver driver;

    protected final HeaderGeneralPageComponent headerGlobal;
    protected final ModeratorHeaderPageComponent header;

    public ModeratorBasePage (WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        headerGlobal = new HeaderGeneralPageComponent(driver);
        header = new ModeratorHeaderPageComponent(driver);
    }

    public SignInPage logOut()  {
        return headerGlobal.logOut();
    }

    public void allTabAccess()  {
        header.moderatorAllTabAccess();
    }
}
