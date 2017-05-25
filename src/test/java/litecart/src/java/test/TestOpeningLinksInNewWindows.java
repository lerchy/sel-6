package litecart.src.java.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * Created by valeriyagagarina on 5/23/17.
 */
public class TestOpeningLinksInNewWindows extends BaseTest {
// 3) открыть на редактирование какую-нибудь страну или начать создание новой
//4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние
//    страницы и открываются в новом окне, именно это и нужно проверить.

    @Test
    public void testOpeningLinkInNewWindow(){

        // open page 'Countries'
        goTo("http://localhost/litecart/admin/?app=countries&doc=countries");
        login("admin", "admin", "//button[contains(text(),'Login')]");
        app.wait.until(ExpectedConditions.visibilityOf(app.driver.findElement(By.cssSelector("[name='countries_form']"))));

        app.driver.findElement(By.cssSelector("[title='Edit']")).click();
        app.wait.until(ExpectedConditions.visibilityOf(app.driver.findElement(By.xpath("//h1[contains(text(), " +
                "'Edit Country')]"))));

        String existingWindow = app.driver.getWindowHandle();
        System.out.println(existingWindow);

        List<WebElement> links = app.driver.findElements(By.className("fa-external-link"));
        int numberOflinks = links.size();
        for(int i = 0; i < numberOflinks; i++){
            links.get(i).click();
            app.wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> windows = app.driver.getWindowHandles();
            windows.remove(existingWindow);
            String newWindow = windows.iterator().next();
            app.driver.switchTo().window(newWindow);
            app.driver.close();
            app.driver.switchTo().window(existingWindow);
        }
    }
}
