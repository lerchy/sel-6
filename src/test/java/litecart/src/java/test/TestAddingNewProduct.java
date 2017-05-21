package litecart.src.java.test;

import model.NewProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
        Assert.assertTrue(driver.findElement(By.className("alert-success")).getText().contains("You are now logged in as admin"));
    }

    @Test(dependsOnMethods = {"testLogin"})
    public void initNewProductTest(){

        // prepair testdata
        createPirateDuckProduct();
        // open menu "Catalog"
        driver.findElement(By.cssSelector("a[href*='catalog']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().equals("Catalog"));

        // press button "Add New Product"
        driver.findElement(By.linkText("Add New Product")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().equals("Add New Product"));

    }

    @Test(dependsOnMethods = {"initNewProductTest"})
    public void testAddingNewProduct(){

        // fill out information on tab "General"
        // uncheck category "Root"
        uncheckCheckBox(By.cssSelector("[data-name='Root']"));

        // check category "Rubber Ducks"
        checkCheckBox(By.cssSelector("[data-name='Rubber Ducks']"));

        // fill out input fields code' and 'SKU'
        fillOutField("[name='code']", pirateDuck.code);
        fillOutField("[name='sku']", pirateDuck.code);

        // fill out input field 'name'
        fillOutField("[name='name[en]']", pirateDuck.name);

        // fill out input field 'quantity'
        fillOutField("[name='quantity']", pirateDuck.quantity);

        // fill out input field 'weight'
        fillOutField("[name='weight']", pirateDuck.weight);
        selectFromDropDownList("[name='weight_class']", "g");

        // fill out input fields width, height, length
        fillOutField("[name='dim_x']", pirateDuck.width);
        fillOutField("[name='dim_y']", pirateDuck.height);
        fillOutField("[name='dim_z']", pirateDuck.length);
        selectFromDropDownList("[name='dim_class']", "cm");

        // add picture new_images[]
        driver.findElement(By.cssSelector("[name='new_images[]']")).sendKeys(pirateDuck.picture);

        // fill out ""Information tab
        driver.findElement(By.linkText("Information")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='manufacturer_id']")));

        selectFromDropDownList("[name='manufacturer_id']", "ACME Corp.");

        // filled out 'Short description
        fillOutField("[name='short_description[en]']", pirateDuck.shortDescription);

        // fill out 'Long description'
        fillOutField("[name='description[en]']", pirateDuck.shortDescription);

        // adding info in "Price" tab
        driver.findElement(By.linkText("Prices")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("prices")));

        fillOutField("[name='purchase_price']", pirateDuck.price);
        selectFromDropDownList("[name='purchase_price_currency_code'", "Euros");

        // save changes
        driver.findElement(By.cssSelector("[name='save']")).click();
        // confirm changes
        Assert.assertTrue(driver.findElement(By.className("alert-success")).getText().contains("Changes were successfully saved."));
    }


    @Test(dependsOnMethods = {"testAddingNewProduct"})
    public void testProductWasAdded(){
        driver.findElement(By.cssSelector("a[href*='catalog']")).click();
        driver.findElement(By.linkText("Rubber Ducks")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Pirate Duck")).isDisplayed());
    }


    // helpers
    void createPirateDuckProduct(){
        String pathToPicFile = new File("src/test/java/resources/Pirate Duck.png")
                .getAbsolutePath();
        pirateDuck.withName("Pirate Duck").withCode("RD00x").withHeadTitle("Pirate Duck").withHeight(10.00)
                .withLength(10.00).withWidth(6.00).withWeight(200.00).withQuantity(25).withPrice(12.00)
                .withPicture(pathToPicFile);

    }

    void pause(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
