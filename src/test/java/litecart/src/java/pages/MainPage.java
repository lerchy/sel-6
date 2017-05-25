package litecart.src.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by valeriyagagarina on 5/25/17.
 */
public class MainPage extends Page{

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void open() {
        super.open("http://localhost/litecart");
    }

    public void addProductToCart(String tab, String name) {

        // add product to the shopping cart
        clickLink("[href='" + tab + "']");
        clickLink("[title='" + name + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='add_cart_product']"))).click();
        expandPage();

    }


}
