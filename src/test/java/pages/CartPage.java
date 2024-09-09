package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage extends BasePage {
    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public By title = By.className("title");
    public By cartTitle = By.className("title");
    public By cartList = By.className("cart_list");
    public By cartItem = By.className("cart_item");
    public By itemTitle = By.cssSelector(".cart_item:nth-child(3) a[id$=\"title_link\"]");
    public By itemDescription = By.cssSelector(".cart_item:nth-child(3) .inventory_item_desc");
    public By itemPrice = By.cssSelector(".cart_item:nth-child(3) div[class$=\"price\"]");
    public By checkoutButton = By.className("checkout_button");
    public By checkoutFirstName = By.id("first-name");
    public By checkoutLastName = By.id("last-name");
    public By checkoutPostalCode = By.id("postal-code");
    public By checkoutContinueButton = By.id("continue");
    public By checkoutPaymentInformation = By.cssSelector("[data-test=\"payment-info-value\"]");
    public By checkoutShippingInformation = By.cssSelector("[data-test=\"shipping-info-value\"]");
    public By checkoutPriceTotalItemTotal = By.cssSelector("[data-test=\"subtotal-label\"]");
    public By checkoutPriceTotalTax = By.cssSelector("[data-test=\"tax-label\"]");
    public By checkoutPriceTotal = By.cssSelector("[data-test=\"total-label\"]");
    public By checkoutFinishButton = By.id("finish");
    public By checkoutCompleteTitle = By.cssSelector("[data-test=\"complete-header\"]");

    public void isRedirectedAndTitleDisplayed(){
        Assert.assertTrue(driver.getCurrentUrl().endsWith("cart.html"));
        Assert.assertTrue(isElementDisplayed(title));
    }
    public void fillCheckoutForm(String firstName, String lastName, String postalCode){
        sendKeys(checkoutFirstName, firstName);
        sendKeys(checkoutLastName, lastName);
        sendKeys(checkoutPostalCode, postalCode);
        Assert.assertEquals(readAttribute(checkoutFirstName, "value"), firstName);
        Assert.assertEquals(readAttribute(checkoutLastName, "value"), lastName);
        Assert.assertEquals(readAttribute(checkoutPostalCode, "value"), postalCode);
        clickElement(checkoutContinueButton);
    }
}
