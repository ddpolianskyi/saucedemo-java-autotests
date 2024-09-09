package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver){
        super(driver);
    }

    public String validPassword = "secret_sauce";
    public String standardUserUsername = "standard_user";
    public String lockedOutUserUsername = "locked_out_user";
    public String problemUserUsername = "problem_user";
    public String performanceGlitchUserUsername = "performance_glitch_user";
    public String errorUserUsername = "error_user";
    public String visualUserUsername = "visual_user";

    public By usernameInput = By.id("user-name");
    public By passwordInput = By.id("password");
    public By loginButton = By.id("login-button");
    public By errorMessage = By.cssSelector("[data-test=\"error\"]");

    public void login(String username, String password){
        sendKeys(usernameInput, username);
        sendKeys(passwordInput, password);
        Assert.assertEquals(readAttribute(usernameInput, "value"), username);
        Assert.assertEquals(readAttribute(passwordInput, "value"), password);
        clickElement(loginButton);
    }

    public void loginStandardUser(){
        sendKeys(usernameInput, standardUserUsername);
        sendKeys(passwordInput, validPassword);
        Assert.assertEquals(readAttribute(usernameInput, "value"), standardUserUsername);
        Assert.assertEquals(readAttribute(passwordInput, "value"), validPassword);
        clickElement(loginButton);
    }
}
