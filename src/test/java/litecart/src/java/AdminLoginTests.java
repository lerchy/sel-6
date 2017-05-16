package litecart.src.java;

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



    @Test
    public void testLoginAsAdmin() {

        goTo("http://localhost/litecart/admin/login.php");
        login("admin", "admin", "//button[contains(text(),'Login')]");
    }

    @Test(dependsOnMethods = {"testLoginAsAdmin"})
    public void testLeftMenuLinks() {
        // wait untill the page is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));

        // find all menu items at the left side
        List<WebElement> leftMenuItems = driver.findElements(By.className("name"));
        // save menu items names as strings in a list
        List<String> leftMenuItemNames = getElementNames(leftMenuItems);

        System.out.println("Links number in left menu: " + leftMenuItems.size());

        for(String s : leftMenuItemNames){
            System.out.println("Menu item name is " + s);
            driver.findElement(By.xpath("//span[text()='" + s + "']")).click();
            Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());

            // find all items at the left side in the sub menu
            List<WebElement> menuSubItems = driver.findElements(By.cssSelector(("[id^=doc-]")));
            // save sub menu items names as strings in a list
            List<String> leftSubMenuItemNames = getElementNames(menuSubItems);

            for(String se : leftSubMenuItemNames){
                System.out.println("    Sub menu item name is " + se);
                driver.findElement(By.xpath("//span[text()='" + se + "']")).click();
                Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());

                logout();
            }
        }
    }
}
