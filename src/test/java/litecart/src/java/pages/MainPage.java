package litecart.src.java.pages;

import litecart.src.java.pages.pageBlocks.ShoppingCart;
import org.openqa.selenium.WebDriver;

import java.util.List;

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

    public void choosePopularProduct(String name) {

        // add product to the shopping cart
        clickLink("[href='#popular-products']");
        clickLink("[title='" + name + "']");
    }

    public void addItemsFromShoppingListToCart(List<String> shoppingList){

        // add all items from the list to the shopping cart
        for(int i = 0; i < shoppingList.size(); i++) {
            choosePopularProduct(shoppingList.get(i));
            new ProductPage(driver).addProductToCart();
            new ShoppingCart(driver).wasItemAdded(i + 1);
            goToHomePage();
        }

    }

}
