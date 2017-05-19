package litecart.src.java.test;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by valeriyagagarina on 5/19/17.
 */

public class TestNewUserRegistration extends BaseTest{
    String email = System.currentTimeMillis() / 1000L + "@testmail.com";
    User newUser = new User("Max", "Mustermann", "Fake Address", email, "password");

    public void logout(){
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void testNewUserCreation(){

        goTo("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logotype")));

        driver.findElement(By.linkText("New customers click here")).click();
        WebElement loginRegistrationForm = driver.findElement(By.id("content"));

        loginRegistrationForm.findElement(By.cssSelector("[name='firstname']")).sendKeys(newUser.firstName);
        loginRegistrationForm.findElement(By.cssSelector("[name='lastname']")).sendKeys(newUser.lastName);
        loginRegistrationForm.findElement(By.cssSelector("[name='address1']")).sendKeys(newUser.address1);
        loginRegistrationForm.findElement(By.cssSelector("[name='email']")).sendKeys(newUser.email);
        loginRegistrationForm.findElement(By.cssSelector("[name='password']")).sendKeys(newUser.password);
        loginRegistrationForm.findElement(By.cssSelector("[name='confirmed_password']")).sendKeys(newUser.password);

        // submit login form
        loginRegistrationForm.findElement(By.cssSelector("[name='create_account']")).click();
        Assert.assertTrue(driver.findElement(By.className("alert")).getText().contains("Your customer account has been created."));

        // logout
        logout();
        Assert.assertTrue(driver.findElement(By.className("alert")).
                getText().contains("You are now logged out."));
    }

    @Test(dependsOnMethods = {"testNewUserCreation"})
    public void testUserLogin(){

        WebElement loginForm = driver.findElement(By.id("sidebar"));

        loginForm.findElement(By.cssSelector("[name='email']")).sendKeys(newUser.email);
        loginForm.findElement(By.cssSelector("[name='password']")).sendKeys(newUser.password);

        // submit login form
        loginForm.findElement(By.cssSelector("[name='login']")).click();
        Assert.assertTrue(driver.findElement(By.className("alert")).
                getText().contains("You are now logged in as " + newUser.firstName + " " + newUser.lastName));

        // logout
        logout();
        Assert.assertTrue(driver.findElement(By.className("alert")).
                getText().contains("You are now logged out."));
    }
}
