package litecart.src.java.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by valeriyagagarina on 5/22/17.
 */
public class TestShoppingcart extends BaseTest{

    // test data items to buy
    List<String> itemsToBuy = new ArrayList<String>(Arrays.asList("Blue Duck", "Purple Duck", "Green Duck"));

    @Test
    public void testAddindProductsToCart(){

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        goTo("http://localhost/litecart");

        for(int i = 0; i <3; i++) {

            // add product to the shopping cart
            clickLink("[href='#popular-products']");
            clickLink("[title='" + itemsToBuy.get(i) + "']");
            clickButton("[name='add_cart_product']");

            // expand the window if it is not full
            try{
                driver.findElement(By.linkText("View full page")).click();
            } catch (Exception e){ }

            // wait till the item is added to the shopping cart
            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className("quantity")), ""+(i+1)));

            // return to home page
            clickLink("[href='/litecart/']");
        }

        // open the shopping cart for checkout
        clickLink("[id='cart']");

        // delete items from the shopping cart
        WebElement buttonDeleteItem;
        while(driver.findElements(By.cssSelector("[name='remove_cart_item']")).size() != 0){
            buttonDeleteItem = driver.findElement(By.cssSelector("[name='remove_cart_item']"));
            buttonDeleteItem.click();
            wait.until(ExpectedConditions.stalenessOf(buttonDeleteItem));
        }

        Assert.assertTrue(driver.findElement(By.tagName("p")).getText().contains("There are no items in your cart."));
    }

}
