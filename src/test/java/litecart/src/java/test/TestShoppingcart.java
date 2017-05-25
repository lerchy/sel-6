package litecart.src.java.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

/**
 * Created by valeriyagagarina on 5/22/17.
 */
public class TestShoppingcart extends BaseTest{

    // test data items to buy
    List<String> itemsToBuy = new ArrayList<String>(Arrays.asList("Blue Duck", "Purple Duck", "Green Duck"));

    @Test
    public void testAddindProductsToCart() {

        // open main page
        app.mainPage.open();

        // add all items from the list to the shopping cart
        for(int i = 0; i < itemsToBuy.size(); i++) {
            app.mainPage.addProductToCart("#popular-products", itemsToBuy.get(i));
            // wait till the item is added to the shopping cart
            app.shoppingCartPage.areNumbersEqual(app.shoppingCartPage.getItemsCount(), (i + 1));
            app.productPage.goToHomePage();
        }

        app.shoppingCartPage.goToCheckOut();
        app.shoppingCartPage.deleteAllItemsFromCart();

        Assert.assertTrue(app.mainPage.driver.findElement(By.tagName("p")).getText().contains("There are no items in your cart."));
    }
}
