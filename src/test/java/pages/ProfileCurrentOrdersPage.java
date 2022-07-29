package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfileCurrentOrdersPage {
    private final WebDriver driver;

    String nameRestaurant;
    String timeOrder;
    String totalSum;


    public ProfileCurrentOrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //div[@role='button']//p[contains(text(),'Johnson PLC')]
    @FindBy(xpath = "//a[contains(., 'All')]")
    private WebElement allTab;

    @FindBy(xpath = "//a[@href = '/profile/current_orders/Waiting for confirm']")
    private WebElement waitingForConfirmTab;

    @FindBy(xpath = "//a[@href = '/profile/current_orders/Accepted']")
    private WebElement acceptedTab;

    @FindBy(xpath = "//a[@href = '/profile/current_orders/Assigned waiter']")
    private WebElement assignedWaiterTab;

    @FindBy(xpath = "//a[@href = '/profile/current_orders/In progress']")
    private WebElement inProgressTab;

    //left or right arrow of the order`s status
    @FindBy(xpath = "//header//button/*[@role = 'presentation']")
    private List<WebElement> presentationStatusBtns;

    // arrow on the right next to each order
    @FindBy(xpath = "//div[@role = 'button']/div[@role = 'button']")
    private List<WebElement> presentationOrderBtn;

    @FindBy(xpath = "//span[contains(text(), 'Decline')]")
    private List<WebElement> setOfDeclineBtns;

    public void allTabAccess() {
        allTab.click();
    }

    public void waitingForConfirmTabAccess() {
        waitingForConfirmTab.click();
    }

    public void acceptedTabAccess() {
        acceptedTab.click();
    }

    public void assignedWaiterTabAccess() {
        assignedWaiterTab.click();
    }

    public void inProgressTabAccess() {
        inProgressTab.click();
    }

    public void presentationStatusBtnAccessByIndex(int index) throws IllegalArgumentException {

        int size = presentationStatusBtns.size();

        if (index >= size) {
            throw new IllegalArgumentException("Unexpected index [" + index + "]. Expect index should not be grater then " + size);
        }

        presentationStatusBtns.get(index).click();
    }

    public void presentationOrderBtnAccessByIndex(int index) throws IllegalArgumentException {

        int size = presentationOrderBtn.size();

        if (index >= size) {
            throw new IllegalArgumentException("Unexpected index [" + index + "]. Expect index should not be grater then " + size);
        }

        presentationOrderBtn.get(index).click();
    }

    public void declineBtnAccessByIndex(int index) throws IllegalArgumentException {

        int size = setOfDeclineBtns.size();

        if (index >= size) {
            throw new IllegalArgumentException("Unexpected index [" + index + "]. Expect index should not be grater then " + size);
        }

        setOfDeclineBtns.get(index).click();
    }

    public boolean matchRestaurant(String nameRestaurant, String timeOrder, String totalSum) {
        /**
         * method checks if there is an order with such parameters
         *
         */
        boolean orderStatusOne = driver.findElement(By.xpath("//div[@role='button']//p[contains(text(),'" + nameRestaurant + "')]")).isDisplayed();
        boolean orderStatusTwo = driver.findElement(By.xpath("//div[@role='button']//p[contains(.,'" + timeOrder + "')]")).isDisplayed();
        boolean orderStatusThree = driver.findElement(By.xpath("//div[@role='button']//p[contains(.,'" + totalSum + "')]")).isDisplayed();

        if (orderStatusOne == true &&
                orderStatusTwo == true &&
                orderStatusThree == true) {
            return true;
        } else {
            return false;

        }
    }

    public String getOrderID(String nameRestaurant, String timeOrder, String totalSum) {
        /**
         * method return order id
         *
         */
        try {
            String id = driver.findElement(By.xpath("//div/div[@role='button']" +
                    "/div/div/div/p[contains(text(),'" + nameRestaurant + "')]" +
                    "//ancestor::div/div[@role='button']/div/div/div/p[contains(.,'" + timeOrder + "')]" +
                    "//ancestor::div[@role='button']/div/div/div/p[contains(.,'" + totalSum + "')]")).getText();
            return id;
        }catch (Exception e){
            System.out.println("Order not found");
            return null;
        }

        }
        public void clickOrderById(String orderId){
        try {
            driver.findElement(By.xpath("//div/div[@role='button']/div/div/div/p[contains(.,'"+ orderId + "')]" +
                    "//ancestor::div[@role='button']")).click();
        }catch (Exception e){
            System.out.println("Can`t click button order");
        }
        }


}

