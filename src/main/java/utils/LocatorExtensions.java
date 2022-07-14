package utils;

import org.openqa.selenium.By;

public class LocatorExtensions {

    public static By toBy(WebElementLocator locator){
        By by;
        switch (locator.getKind()) {
            case Id:
                by = By.id(locator.getValue());
                break;
            case ClassName:
                by = By.className(locator.getValue());
                break;
            case CssSelector:
                by = By.cssSelector(locator.getValue());
                break;
            case LinkText:
                by = By.linkText(locator.getValue());
                break;
            case PartialLinkText:
                by = By.partialLinkText(locator.getValue());
                break;
            case TagName:
                by = By.tagName(locator.getValue());
                break;
            case Xpath:
                by = By.xpath(locator.getValue());
                break;
            case Name:
                by = By.name(locator.getValue());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + locator.getKind());
        }
        return by;
    }
}
