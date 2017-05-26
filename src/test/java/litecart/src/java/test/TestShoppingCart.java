package litecart.src.java.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by valeriyagagarina on 5/22/17.
 */
public class TestShoppingCart extends BaseTest{

    // test data items to buy
    List<String> shoppingList = new ArrayList<String>(Arrays.asList("Blue Duck", "Purple Duck", "Green Duck"));

    @Test
    public void testAddindProductsToCart() {
        app.mainPage.open();
        app.mainPage.addItemsFromShoppingListToCart(shoppingList);
        app.productPage.goToCheckOut();
        app.shoppingCartPage.deleteAllItemsFromCart();
        Assert.assertTrue(app.shoppingCartPage.isCartEmpty());
    }
}
