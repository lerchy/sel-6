package litecart.src.java.test;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import litecart.src.java.model.ProductItem;

/**
 * Created by valeriyagagarina on 5/17/17.
 */
public class ProductPageTests extends BaseTest{

    ProductItem productMP; // prodContainer on main page
    ProductItem productPP; // prodContainer on prodContainer page

    @Test
    void initTestData(){

        // load the main page
        goTo("http://localhost/litecart/en/");
        app.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logotype")));

        // init the product from main page
        productMP = new ProductItem(app.driver, By.id("campaign-products"));

        // go to campain page
        app.driver.findElement(By.cssSelector("[title='Yellow Duck']")).click();

        // init the product from the campain page
        productPP = new ProductItem(app.driver);
    }

    //  на главной странице и на странице товара совпадает текст названия товара
    @Test(dependsOnMethods = {"initTestData"})
    public void testProductNamesAreSame(){
        System.out.println("MP name: " + productMP.name + ", PP name: " + productPP.name);
        Assert.assertTrue(productMP.name.equals(productPP.name));
    }

    //  на главной странице и на странице товара совпадаeт обычная цена
    @Test(dependsOnMethods = {"initTestData"})
    public void testRegularPricesAreSame(){
        Assert.assertTrue(productMP.regularPrice == productPP.regularPrice);
    }

    //  на главной странице и на странице товара совпадаeт акционная цена
    @Test(dependsOnMethods = {"initTestData"})
    public void testSpecialPricesAreSame(){
        System.out.println("MP spec Price: " + productMP.specialPrice + ", PP spec Price: " + productPP.specialPrice);
        Assert.assertTrue(productMP.specialPrice == productPP.specialPrice);
    }

    //  обычная цена зачёркнутая (главная страница)
    @Test(dependsOnMethods = {"initTestData"})
    public void testRegularPriceIsLignedThroughMP(){
        Assert.assertTrue(productMP.regularPriceTextDecoration.contains("line-through"));
    }

    //  обычная цена серая (главная страница)
    @Test(dependsOnMethods = {"initTestData"})
    public void testRegularPriceIsGrayMP(){
        Assert.assertTrue(productMP.regularPriceColor.getBlue() == productMP.regularPriceColor.getGreen() &&
                productMP.regularPriceColor.getGreen() == productMP.regularPriceColor.getRed());
    }

    //  акционная цена жирная (главная страница)
    @Test(dependsOnMethods = {"initTestData"})
    public void testSpecialPriceIsStrongMP(){
        Assert.assertTrue(productMP.specialPriceTextDecoration.contains("strong"));
    }

    //  акционная цена красная (главная страница)
    @Test(dependsOnMethods = {"initTestData"})
    public void testSpecialrPriceIsRedMP(){
        Assert.assertTrue(productMP.specialPriceColor.getBlue() == 0 &&
                productMP.specialPriceColor.getGreen() == 0);
    }

    //  акционная цена крупнее, чем обычная (главная страница)
    @Test(dependsOnMethods = {"initTestData"})
    public void testSpecialPriceFontGreaterThanRegularMP(){
        Assert.assertTrue(productMP.specialPriceFontSize > productMP.regularPriceFontSize);
    }

    //  обычная цена зачёркнутая (акционная страница)
    @Test(dependsOnMethods = {"initTestData"})
    public void testRegularPriceIsLignedThroughPP(){
        Assert.assertTrue(productMP.regularPriceTextDecoration.contains("line-through"));
    }

    //  обычная цена серая (акционная страница)
    @Test(dependsOnMethods = {"initTestData"})
    public void testRegularPriceIsGrayPP(){
        Assert.assertTrue(productMP.regularPriceColor.getBlue() == productMP.regularPriceColor.getGreen() &&
                productMP.regularPriceColor.getGreen() == productMP.regularPriceColor.getRed());
    }

    //  акционная цена жирная (акционная страница)
    @Test(dependsOnMethods = {"initTestData"})
    public void testSpecialPriceIsStrongPP(){
        Assert.assertTrue(productMP.specialPriceTextDecoration.contains("strong"));
    }

    //  акционная цена красная (акционная страница)
    @Test(dependsOnMethods = {"initTestData"})
    public void testSpecialrPriceIsRedPP(){
        Assert.assertTrue(productMP.specialPriceColor.getBlue() == 0 &&
                productMP.specialPriceColor.getGreen() == 0);
    }

    //  акционная цена крупнее, чем обычная (акционная страница)
    @Test(dependsOnMethods = {"initTestData"})
    public void testSpecialPriceFontGreaterThanRegularPP(){
        Assert.assertTrue(productMP.specialPriceFontSize > productMP.regularPriceFontSize);
    }
}