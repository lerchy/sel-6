package litecart.src.java.test;

import litecart.src.java.model.NewProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by valeriyagagarina on 5/21/17.
 */
public class TestAddingNewProduct extends BaseTest{

    NewProduct pirateDuck = new NewProduct();

    @Test
    public void testLogin(){

        // login as admin
        goTo("http://localhost/litecart/admin/login.php");
        login("admin", "admin", "//button[contains(text(),'Login')]");
        app.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
        Assert.assertTrue(app.driver.findElement(By.className("alert-success")).getText().contains("You are now logged in as admin"));
    }

    @Test(dependsOnMethods = {"testLogin"})
    public void initNewProductTest(){

        // prepair testdata
        createPirateDuckProduct();
        // open menu "Catalog"
        app.driver.findElement(By.cssSelector("a[href*='catalog']")).click();
        app.wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Assert.assertTrue(app.driver.findElement(By.tagName("h1")).getText().equals("Catalog"));

        // press button "Add New Product"
        app.driver.findElement(By.linkText("Add New Product")).click();
        app.wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Assert.assertTrue(app.driver.findElement(By.tagName("h1")).getText().equals("Add New Product"));

    }

    @Test(dependsOnMethods = {"initNewProductTest"})
    public void testAddingNewProduct(){

        // fill out information on tab "General"
        // uncheck category "Root"
        app.uncheckCheckBox(By.cssSelector("[data-name='Root']"));

        // check category "Rubber Ducks"
        app.checkCheckBox(By.cssSelector("[data-name='Rubber Ducks']"));

        // fill out input fields code' and 'SKU'
        app.fillOutField("[name='code']", pirateDuck.code);
        app.fillOutField("[name='sku']", pirateDuck.code);

        // fill out input field 'name'
        app.fillOutField("[name='name[en]']", pirateDuck.name);

        // fill out input field 'quantity'
        app.fillOutField("[name='quantity']", pirateDuck.quantity);

        // fill out input field 'weight'
        app.fillOutField("[name='weight']", pirateDuck.weight);
        app.selectFromDropDownList("[name='weight_class']", "g");

        // fill out input fields width, height, length
        app.fillOutField("[name='dim_x']", pirateDuck.width);
        app.fillOutField("[name='dim_y']", pirateDuck.height);
        app.fillOutField("[name='dim_z']", pirateDuck.length);
        app.selectFromDropDownList("[name='dim_class']", "cm");

        // add picture new_images[]
        app.driver.findElement(By.cssSelector("[name='new_images[]']")).sendKeys(pirateDuck.picture);

        // fill out ""Information tab
        app.driver.findElement(By.linkText("Information")).click();
        app.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='manufacturer_id']")));

        app.selectFromDropDownList("[name='manufacturer_id']", "ACME Corp.");

        // filled out 'Short description
        app.fillOutField("[name='short_description[en]']", pirateDuck.shortDescription);

        // fill out 'Long description'
        app.fillOutField("[name='description[en]']", pirateDuck.shortDescription);

        // adding info in "Price" tab
        app.driver.findElement(By.linkText("Prices")).click();
        app.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("prices")));

        app.fillOutField("[name='purchase_price']", pirateDuck.price);
        app.selectFromDropDownList("[name='purchase_price_currency_code'", "Euros");

        // save changes
        app.driver.findElement(By.cssSelector("[name='save']")).click();
        // confirm changes
        Assert.assertTrue(app.driver.findElement(By.className("alert-success")).getText().contains("Changes were successfully saved."));
    }


    @Test(dependsOnMethods = {"testAddingNewProduct"})
    public void testProductWasAdded(){
        app.driver.findElement(By.cssSelector("a[href*='catalog']")).click();
        app.driver.findElement(By.linkText("Rubber Ducks")).click();
        Assert.assertTrue(app.driver.findElement(By.linkText("Pirate Duck")).isDisplayed());
    }


    // helpers
    void createPirateDuckProduct(){
        String pathToPicFile = new File("src/test/java/resources/Pirate Duck.png")
                .getAbsolutePath();
        pirateDuck.withName("Pirate Duck").withCode("RD00x").withHeadTitle("Pirate Duck").withHeight(10.00)
                .withLength(10.00).withWidth(6.00).withWeight(200.00).withQuantity(25).withPrice(12.00)
                .withPicture(pathToPicFile);

    }

}
