package litecart.src.java.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valeriyagagarina on 5/14/17.
 */
public class BaseTest {
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

    // helper methods
    void goTo(String url){
        driver.navigate().to(url);
    }

    void login(String username, String password, String confirmLocator){
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath(confirmLocator)).click();
        // wait untill the page is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-sign-out")));
    }

    public void logout() {

        driver.findElement(By.className("fa-sign-out")).click();
    }

    List<String> getElementNames(List<WebElement> elements){
        List<String> names = new ArrayList<String>();
        for(WebElement e : elements){
            names.add(e.getText());
        }

        return names;
    }

    public void checkCheckBox(By selector){

        WebElement checkBox = driver.findElement(selector);
        if(checkBox.getAttribute("checked") == null){
            checkBox.click();
        }
    }

    public void uncheckCheckBox(By selector){

        WebElement checkBox = driver.findElement(selector);
        if(checkBox.getAttribute("checked") != null){
            checkBox.click();
        }
    }

    public void fillOutField(String selector, String value){
        driver.findElement(By.cssSelector(selector)).clear();
        driver.findElement(By.cssSelector(selector)).sendKeys(value);
    }

    public void selectFromDropDownList(String selector, String value){
        Select select = new Select(driver.findElement(By.cssSelector(selector)));
        select.selectByVisibleText(value);
    }
}
