package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by valeriyagagarina on 4/22/17.
 */
public class One {

    WebDriver driver;

    @BeforeTest
    public  void setup(){

        System.setProperty("webdriver.gecko.driver", "geckodriver");
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void finish(){

        driver.quit();
    }

    @Test
    public void testOne() throws InterruptedException {

        driver.get("http://www.seleniumhq.org/");
        Assert.assertTrue(driver.getTitle().equals("Selenium - Web Browser Automation"));

    }
}


