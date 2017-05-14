package litecart.src.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeriyagagarina on 5/14/17.
 */
public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void finish(){

        driver.quit();
    }

    // helper methods
    protected void goTo(String url){
        driver.navigate().to(url);
    }

    protected List<String> getElementNames(List<WebElement> elements){
        List<String> names = new ArrayList<String>();
        for(WebElement e : elements){
            names.add(e.getText());
        }

        return names;
    }
}
