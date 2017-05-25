package litecart.src.java.pages.pageBlocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by valeriyagagarina on 5/25/17.
 */
public class PageBlock {
    public WebDriver driver;
    public WebDriverWait wait;

    public PageBlock(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

}
