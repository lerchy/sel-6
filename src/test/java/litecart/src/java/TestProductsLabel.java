package litecart.src.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by valeriyagagarina on 5/14/17.
 */
public class TestProductsLabel extends BaseTest{

    @Test
    public void testProductLabelPresence(){

        // open home page
        goTo("http://localhost/litecart/en/");

        // find all products on the home page and save them to a list
        List<WebElement> products = driver.findElements(By.className("image-wrapper"));

        // verify that each product has exactly one sticker
        for(WebElement e : products){
            System.out.println(e.findElement(By.className("sticker")).getAttribute("textContent"));
            Assert.assertEquals( e.findElements(By.className("sticker")).size(), 1);
        }
    }
}
