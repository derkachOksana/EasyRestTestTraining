package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MenuPage {
    private final WebDriver driver;

    @FindBy(xpath = "//main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[1]")
    private WebElement foodName;

    @FindBy(xpath = "//main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]")
    private WebElement foodMass;

    @FindBy(xpath = "//main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[3]")
    private WebElement foodPrice;

    @FindBy(xpath = "//main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[4]/div/div/input")
    private WebElement foodQuantity;

    @FindBy(xpath = "//main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[5]")
    private WebElement foodFinalPrice;

    @FindBy(xpath = "//button[@aria-label='Add to cart']")
    private List<WebElement> setOfAddToCartBtn;

    @FindBy(xpath = "//span[contains(text(), 'Submit order')]")
    private WebElement submitOrderTab;


    public MenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFoodName() {
        return foodName.getText();
    }

    public String getFoodMass() {
        return foodMass.getText();
    }

    public String getFoodPrice() {
        return foodPrice.getText();
    }

    public String getFoodQuantity() {
        return foodQuantity.getAttribute("value");
    }

    public String getFoodFinalPrice() {
        return foodFinalPrice.getText();
    }

    public void submitOrderAccessTab() {
        submitOrderTab.click();
    }

    public void addToCartBtnAccessByIndex(int index) {

        int size = setOfAddToCartBtn.size();

        if (index >= size) {
            throw new IllegalArgumentException("Unexpected index [" + index + "]. Expect index should not be grater then " + size);
        }
        setOfAddToCartBtn.get(index).click();
    }
}