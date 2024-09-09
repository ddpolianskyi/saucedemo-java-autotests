package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InventoryPage extends BasePage {
    WebDriver driver;

    public InventoryPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public By burgerMenuButton = By.id("react-burger-menu-btn");
    public By sidebar = By.className("bm-menu");
    public By sidebarLogoutButton = By.id("logout_sidebar_link");
    public By title = By.className("title");
    public By cartIcon = By.className("shopping_cart_link");
    public By itemTitle = By.cssSelector(".inventory_item:first-child a[id$=\"title_link\"]");
    public By itemDescription = By.cssSelector(".inventory_item:first-child .inventory_item_desc");
    public By itemPrice = By.cssSelector(".inventory_item:first-child div[class$=\"price\"]");
    public By itemButton = By.cssSelector(".inventory_item:first-child button");
    public By itemImages = By.cssSelector(".inventory_item img");
    public By itemDetailsTitle = By.className("inventory_details_name");
    public By itemDetailsDescription = By.className("inventory_details_desc");
    public By itemDetailsPrice = By.className("inventory_details_price");

    public void isRedirectedAndTitleDisplayed(){
        Assert.assertTrue(driver.getCurrentUrl().endsWith("inventory.html"));
        Assert.assertTrue(isElementDisplayed(title));
    }
}
