package litecart.src.java.test;

import litecart.src.java.app.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;

/**
 * Created by valeriyagagarina on 5/14/17.
 */
public class BaseTest {

    Application app = new Application();

    @AfterClass
    public void finish(){
        app.quite();
    }



    void login(String username, String password, String confirmLocator){
        app.driver.findElement(By.name("username")).sendKeys(username);
        app.driver.findElement(By.name("password")).sendKeys(password);
        app.driver.findElement(By.xpath(confirmLocator)).click();
        // wait untill the page is loaded
        app.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-sign-out")));
    }

    public void logout() {

        app.driver.findElement(By.className("fa-sign-out")).click();
    }



}
