package litecart.src.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by valeriyagagarina on 5/25/17.
 */
public class ShoppingCartPage extends Page{

    private int itemsCount;

    public ShoppingCartPage(WebDriver driver){
        super(driver);
    }

    public int getItemsCount(){
        return Integer.valueOf(driver.findElement(By.className("quantity")).getText());
    }

    public void deleteAllItemsFromCart(){

        WebElement buttonDeleteItem;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name='remove_cart_item']")));
        while(driver.findElements(By.cssSelector("[name='remove_cart_item']")).size() != 0){
            buttonDeleteItem = driver.findElement(By.cssSelector("[name='remove_cart_item']"));
            buttonDeleteItem.click();
            wait.until(ExpectedConditions.stalenessOf(buttonDeleteItem));
        }
    }
}
