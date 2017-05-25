package litecart.src.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by valeriyagagarina on 5/25/17.
 */
public class ProductPage extends Page{

    public ProductPage(WebDriver driver){
        super(driver);
    }

    public void addProductToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='add_cart_product']"))).click();
        expandPage();
    }

}
