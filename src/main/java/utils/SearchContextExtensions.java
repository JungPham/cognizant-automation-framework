package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static utils.LocatorExtensions.toBy;

public class SearchContextExtensions {

    public static WebElement getElement(WebDriver driver, WebElementLocator locator){
        return driver.findElement(toBy(locator));
    }

    public static List<WebElement> getElements(WebDriver driver, WebElementLocator locator){
        return driver.findElements(toBy(locator));
    }

    public static void waitForClickableWebElement(WebDriver driver, WebElementLocator element, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(toBy(element)));
    }

    public static void waitForVisibleWebElement(WebDriver driver, WebElementLocator element, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(toBy(element)));
    }
}
