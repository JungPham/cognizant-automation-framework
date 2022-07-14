package utils;

import lombok.Setter;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Setter
@Getter
public class TestContext {
    private WebDriver webDriver;
}
