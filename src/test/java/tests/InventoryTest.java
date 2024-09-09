package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends BaseTest {
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    String addToCartButtonText = "Add to cart";
    String addToCartButtonColor = "rgba(19, 35, 34, 1)";
    String removeButtonText = "Remove";
    String removeButtonColor = "rgba(226, 35, 26, 1)";

    @BeforeMethod
    void pageSetup(){
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    void TC10_openTheInventoryItemPage(){
        loginPage.loginStandardUser();
        inventoryPage.isRedirectedAndTitleDisplayed();
        String[] items = {
                inventoryPage.readText(inventoryPage.itemTitle),
                inventoryPage.readText(inventoryPage.itemDescription),
                inventoryPage.readText(inventoryPage.itemPrice)
        };
        inventoryPage.clickElement(inventoryPage.itemTitle);
        inventoryPage.isRedirected("inventory-item.html?id=4");
        By[] itemsDetails = { inventoryPage.itemDetailsTitle, inventoryPage.itemDetailsDescription, inventoryPage.itemDetailsPrice };
        for (int i = 0; i < items.length; i++) {
            inventoryPage.isElementDisplayed(itemsDetails[i]);
            Assert.assertEquals(inventoryPage.readText(itemsDetails[i]), items[i]);
        }
    }
    @Test
    void TC11_addInventoryItemToCart(){
        loginPage.loginStandardUser();
        inventoryPage.isRedirectedAndTitleDisplayed();
        String[] items = {
                inventoryPage.readText(inventoryPage.itemTitle),
                inventoryPage.readText(inventoryPage.itemDescription),
                inventoryPage.readText(inventoryPage.itemPrice)
        };
        inventoryPage.clickElement(inventoryPage.itemButton);
        Assert.assertEquals(inventoryPage.readText(inventoryPage.itemButton), removeButtonText);
        Assert.assertEquals(inventoryPage.readCssValue(inventoryPage.itemButton, "color"), removeButtonColor);
        inventoryPage.clickElement(inventoryPage.cartIcon);
        cartPage.isRedirectedAndTitleDisplayed();
        By[] cartItems = { cartPage.itemTitle, cartPage.itemDescription, cartPage.itemPrice };
        for (int i = 0; i < items.length; i++) {
            inventoryPage.isElementDisplayed(cartItems[i]);
            Assert.assertEquals(inventoryPage.readText(cartItems[i]), items[i]);
        }
    }
    @Test
    void TC12_removeInventoryItemFromCart(){
        loginPage.loginStandardUser();
        inventoryPage.isRedirectedAndTitleDisplayed();
        inventoryPage.clickElement(inventoryPage.itemButton);
        inventoryPage.clickElement(inventoryPage.itemButton);
        Assert.assertEquals(inventoryPage.readText(inventoryPage.itemButton), addToCartButtonText);
        Assert.assertEquals(inventoryPage.readCssValue(inventoryPage.itemButton, "color"), addToCartButtonColor);
        inventoryPage.clickElement(inventoryPage.cartIcon);
        cartPage.isRedirectedAndTitleDisplayed();
        Assert.assertTrue(cartPage.countElements(cartPage.cartItem) == 0);
    }
}
