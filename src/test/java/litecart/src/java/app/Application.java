package litecart.src.java.app;

import litecart.src.java.pages.MainPage;
import litecart.src.java.pages.ProductPage;
import litecart.src.java.pages.ShoppingCartPage;
import litecart.src.java.pages.pageBlocks.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeriyagagarina on 5/25/17.
 */
public class Application {

    private WebDriver driver;

    public MainPage mainPage;
    public ProductPage productPage;
    public ShoppingCartPage shoppingCartPage;
    public Header header;


    public Application(){
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        header = new Header(driver);
    }



    public void quite(){
        driver.quit();
    }



    public void pause(long l){
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> getElementNames(List<WebElement> elements){
        List<String> names = new ArrayList<String>();
        for(WebElement e : elements){
            names.add(e.getText());
        }
        return names;
    }
}
