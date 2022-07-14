package abstracts;

import org.openqa.selenium.WebDriver;
import utils.DriverConfiguration;

public abstract class BaseInitPage {

    public final WebDriver driver;

    public BaseInitPage(){
        this.driver = DriverConfiguration.driver;
    }
}
