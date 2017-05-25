package litecart.src.java.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeriyagagarina on 5/24/17.
 */
public class TestBrowserLogMessages extends BaseTest{

    @Test
    public void testBrowserLogMessages(){

        // login to product page as admin
        goTo("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        login("admin", "admin", "//button[contains(text(),'Login')]");
        app.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));

        // save product names in a list
        List<WebElement> products = app.driver.findElements(By.cssSelector("a[href*='product_id']"));
        List<String> productNames = new ArrayList<String>();
        for(int i = 0; i < products.size(); i+=2){
            productNames.add(products.get(i).getText());
        }

        // open each product and return to the previous page
        for(String productName : productNames){
            app.driver.findElement(By.linkText(productName)).click();
            app.driver.navigate().back();
        }

        // check if any browser logs are present
        app.driver.manage().logs().get("browser").forEach(l -> {
            System.out.println(l);
            Assert.assertTrue(l.equals(""));});
    }

}
