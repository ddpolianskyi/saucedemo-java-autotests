package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod
    void pageSetup(){
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }
    @Test
    void TC1_loginWithEmptyUsernameField(){
        loginPage.login("", loginPage.validPassword);
        loginPage.isRedirected("/");
        loginPage.isElementDisplayed(loginPage.errorMessage);
        Assert.assertEquals(loginPage.readText(loginPage.errorMessage), "Epic sadface: Username is required");
    }
    @Test
    void TC2_loginWithEmptyPasswordField(){
        loginPage.login(loginPage.standardUserUsername, "");
        loginPage.isRedirected("/");
        loginPage.isElementDisplayed(loginPage.errorMessage);
        Assert.assertEquals(loginPage.readText(loginPage.errorMessage), "Epic sadface: Password is required");
    }
    @Test
    void TC3_loginWithStandardCredentials(){
        loginPage.login(loginPage.standardUserUsername, loginPage.validPassword);
        inventoryPage.isRedirectedAndTitleDisplayed();
    }
    @Test
    void TC4_loginWithLockedOutUserCredentials(){
        loginPage.login(loginPage.lockedOutUserUsername, loginPage.validPassword);
        loginPage.isRedirected("/");
        loginPage.isElementDisplayed(loginPage.errorMessage);
        Assert.assertEquals(loginPage.readText(loginPage.errorMessage), "Epic sadface: Sorry, this user has been locked out.");
    }
    @Test
    void TC5_loginWithProblemUserCredentials(){
        loginPage.login(loginPage.problemUserUsername, loginPage.validPassword);
        inventoryPage.isRedirectedAndTitleDisplayed();
        for (int i = 0; i < inventoryPage.countElements(inventoryPage.itemImages); i++) {
            Assert.assertTrue(inventoryPage.readAttribute(inventoryPage.itemImages, "src").endsWith("/static/media/sl-404.168b1cce.jpg"));
        }
    }
    @Test
    void TC6_loginWithPerformanceGlitchUserCredentials(){
        loginPage.login(loginPage.performanceGlitchUserUsername, loginPage.validPassword);
        inventoryPage.isRedirectedAndTitleDisplayed();
    }
    @Test
    void TC7_loginWithErrorUserCredentials(){
        loginPage.login(loginPage.errorUserUsername, loginPage.validPassword);
        inventoryPage.isRedirectedAndTitleDisplayed();
    }
    @Test
    void TC8_loginWithVisualUserCredentials(){
        loginPage.login(loginPage.visualUserUsername, loginPage.validPassword);
        inventoryPage.isRedirectedAndTitleDisplayed();
    }
    @Test
    void TC9_logout(){
        loginPage.login(loginPage.standardUserUsername, loginPage.validPassword);
        inventoryPage.isRedirectedAndTitleDisplayed();
        inventoryPage.clickElement(inventoryPage.burgerMenuButton);
        inventoryPage.isElementDisplayed(inventoryPage.sidebar);
        inventoryPage.clickElement(inventoryPage.sidebarLogoutButton);
        loginPage.isRedirected("/");
        loginPage.isElementDisplayed(loginPage.usernameInput);
    }
}
