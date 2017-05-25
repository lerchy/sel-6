package litecart.src.java.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by valeriyagagarina on 4/26/17.
 */
public class AdminLoginTests extends BaseTest{



    @Test (enabled = false)
    public void testLoginAsAdmin() {

        goTo("http://localhost/litecart/admin/login.php");
        login("admin", "admin", "//button[contains(text(),'Login')]");
        app.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));
    }

    @Test(dependsOnMethods = {"testLoginAsAdmin"})
    public void testLeftMenuLinks() {

        // find all menu items at the left side
        List<WebElement> leftMenuItems = app.driver.findElements(By.className("name"));
        // save menu items names as strings in a list
        List<String> leftMenuItemNames = app.getElementNames(leftMenuItems);

        System.out.println("Links number in left menu: " + leftMenuItems.size());

        for(String s : leftMenuItemNames){
            System.out.println("Menu prodContainer name is " + s);
            app.driver.findElement(By.xpath("//span[text()='" + s + "']")).click();
            Assert.assertTrue(app.driver.findElement(By.tagName("h1")).isDisplayed());

            // find all items at the left side in the sub menu
            List<WebElement> menuSubItems = app.driver.findElements(By.cssSelector(("[id^=doc-]")));
            // save sub menu items names as strings in a list
            List<String> leftSubMenuItemNames = app.getElementNames(menuSubItems);

            for(String se : leftSubMenuItemNames){
                System.out.println("    Sub menu prodContainer name is " + se);
                app.driver.findElement(By.xpath("//span[text()='" + se + "']")).click();
                Assert.assertTrue(app.driver.findElement(By.tagName("h1")).isDisplayed());

                logout();
            }
        }
    }
}
