package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTest extends BaseTest {
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    String firstName = "John";
    String lastName = "Doe";
    String postalCode = "12345";

    @BeforeMethod
    void pageSetup(){
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
    }
    @Test
    void TC13_Checkout(){
        loginPage.loginStandardUser();
        inventoryPage.isRedirectedAndTitleDisplayed();
        inventoryPage.clickElement(inventoryPage.itemButton);
        String[] itemDetails = {
            inventoryPage.readText(inventoryPage.itemTitle),
            inventoryPage.readText(inventoryPage.itemDescription),
            inventoryPage.readText(inventoryPage.itemPrice),
        };
        inventoryPage.clickElement(inventoryPage.cartIcon);
        cartPage.isRedirectedAndTitleDisplayed();
        cartPage.clickElement(cartPage.checkoutButton);
        cartPage.isRedirected("checkout-step-one.html");
        Assert.assertEquals(cartPage.readText(cartPage.title), "Checkout: Your Information");
        cartPage.fillCheckoutForm(firstName, lastName, postalCode);
        cartPage.isRedirected("checkout-step-two.html");
        Assert.assertEquals(cartPage.readText(cartPage.title), "Checkout: Overview");
        By[] overviewItemDetails = { cartPage.itemTitle, cartPage.itemDescription, cartPage.itemPrice };
        for (int i = 0; i < itemDetails.length; i++) {
            Assert.assertEquals(cartPage.readText(overviewItemDetails[i]), itemDetails[i]);
        }
        Assert.assertNotEquals(cartPage.readText(cartPage.checkoutPaymentInformation), "");
        Assert.assertNotEquals(cartPage.readText(cartPage.checkoutShippingInformation), "");
        String priceTotalItemTotal = cartPage.readText(cartPage.checkoutPriceTotalItemTotal).replace("Item total: ", "");
        Assert.assertEquals(priceTotalItemTotal, itemDetails[2]);
        Assert.assertNotEquals(cartPage.readText(cartPage.checkoutPriceTotalTax), "");
        double itemTotal = Double.parseDouble(cartPage.readText(cartPage.checkoutPriceTotalItemTotal).replace("Item total: $", ""));
        double tax = Double.parseDouble(cartPage.readText(cartPage.checkoutPriceTotalTax).replace("Tax: $", ""));
        Assert.assertEquals(cartPage.readText(cartPage.checkoutPriceTotal), "Total: $" + (itemTotal + tax));
        cartPage.clickElement(cartPage.checkoutFinishButton);
        cartPage.isRedirected("checkout-complete.html");
        Assert.assertEquals(cartPage.readText(cartPage.title), "Checkout: Complete!");
        cartPage.isElementDisplayed(cartPage.checkoutCompleteTitle);
    }
}
