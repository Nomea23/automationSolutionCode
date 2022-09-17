package features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WebLauncher {

    WebDriver driver;

    public WebLauncher(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
