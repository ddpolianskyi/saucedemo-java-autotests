package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitVisibility(By elementLocator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementLocator));
    }
    public void isRedirected(String url){
        Assert.assertTrue(driver.getCurrentUrl().endsWith(url));
    }
    public void clickElement(By elementLocator){
        waitVisibility(elementLocator);
        driver.findElement(elementLocator).click();
    }
    public void sendKeys(By elementLocator, String text){
        waitVisibility(elementLocator);
        driver.findElement(elementLocator).sendKeys(text);
    }
    public int countElements(By elementLocator){
        return (int) driver.findElements(elementLocator).stream().count();
    }
    public String readText(By elementLocator){
        return driver.findElement(elementLocator).getText();
    }
    public String readAttribute(By elementLocator, String attributeName){
        waitVisibility(elementLocator);
        return driver.findElement(elementLocator).getAttribute(attributeName);
    }
    public String readCssValue(By elementLocator, String cssValueName){
        waitVisibility(elementLocator);
        return driver.findElement(elementLocator).getCssValue(cssValueName);
    }
    public boolean isElementDisplayed(By elementLocator){
        return driver.findElement(elementLocator).isDisplayed();
    }
}
