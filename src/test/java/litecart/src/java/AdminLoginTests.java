package litecart.src.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeriyagagarina on 4/26/17.
 */
public class AdminLoginTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void finish(){

        driver.quit();
    }

    @Test
    public void testLoginAsAdmin() throws InterruptedException {

        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
    }

    @Test(dependsOnMethods = {"testLoginAsAdmin"})
    public void testLeftMenuLinks() throws InterruptedException {
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
            }
        }
    }

    @Test(dependsOnMethods = {"testLeftMenuLinks"})
    public void testLogoutAsAdmin() throws InterruptedException {

        driver.findElement(By.className("fa-sign-out")).click();
    }

    // helper methods
    private List<String> getElementNames(List<WebElement> elements){
        List<String> names = new ArrayList<String>();
        for(WebElement e : elements){
            names.add(e.getText());
        }

        return names;
    }
}
