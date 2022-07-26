package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderGeneralPage {

   private final WebDriver driver;
   public OrderGeneralPage(WebDriver driver) {
       PageFactory.initElements(driver, this);
       this.driver = driver;
   }

   @FindBy(xpath = "//button[@aria-label='Add to cart']")
   List<WebElement> setOfAddToCartBtn;

   @FindBy(xpath = "//span[contains(text(), 'Submit order')]")
   private WebElement submitOrderTab;
   @FindBy(xpath = "//span[contains(text(), 'Cancel')]")
   private WebElement cancelOrderConfirmationTab;

   @FindBy(xpath = "//*[text()='Submit']")
   private WebElement submitOrderConfirmationTab;

   @FindBy(xpath = "//div//label[contains(text(),'Time picker')]/ancestor::div[1]//input")
   private WebElement timePickerTab;

    @FindBy(xpath = "//div//label[contains(text(),'Date picker')]/ancestor::div[1]//input")
    private WebElement datePickerTab;

    @FindBy(xpath = "//tbody//button[@aria-label = 'Remove item']")
    List <WebElement> removeItemOrderConfirmationBtns;


    public void removeItemOrderBtnAccessByIndex (int index) {
        int i = 0;
        try {
            for (WebElement element : removeItemOrderConfirmationBtns) {
                i++;
                if (index == i) {
                    element.click();                }
            }
        } catch (Exception e) {
            System.out.println("Not expected index");
        }
    }
    public String datePickerGetText() {
        return datePickerTab.getText();
    }

   public String timePickerGetText() {
       return timePickerTab.getText();
   }

   public void submitOrderConfirmationAccessTab() {
       submitOrderTab.click();
   }

   public void setCancelOrderConfirmationAccess() {
       cancelOrderConfirmationTab.click();
   }

   public void submitOrderAccessTab(){
       submitOrderTab.click();
   }

   public void addToCartBtnAccessByIndex (int index) {
       int i = 0;
       try {
           for (WebElement element : setOfAddToCartBtn) {
               i++;
               if (index == i) {
                   element.click();                }
           }
       } catch (Exception e) {
           System.out.println("Not expected index");

       }
   }
}
