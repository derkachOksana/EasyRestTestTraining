package pages.moderator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageComponents.HeaderGeneralPageComponent;
import pageComponents.moderator.ModeratorHeaderPageComponent;

public class ModeratorBasePage {
    protected WebDriver driver;

    public final HeaderGeneralPageComponent headerGlobal;
    public final ModeratorHeaderPageComponent header;

    public ModeratorBasePage (WebDriver driver)    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        headerGlobal = new HeaderGeneralPageComponent(driver);
        header = new ModeratorHeaderPageComponent(driver);
    }
}
