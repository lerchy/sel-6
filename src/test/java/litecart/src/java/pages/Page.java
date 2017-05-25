package litecart.src.java.pages;

/**
 * Created by valeriyagagarina on 5/25/17.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    public WebDriver driver;
    public WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);
    }

    public void open(String url){
        driver.navigate().to(url);
    }

    public void clickLink(String linkLocator){
        driver.findElement(By.cssSelector(linkLocator)).click();
    }

    public void clickButton(String buttonLocator){
        driver.findElement(By.cssSelector(buttonLocator)).click();
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

    public void expandPage(){
        // expand the window if it is not full
        try{
            driver.findElement(By.linkText("View full page")).click();
        } catch (Exception e){ }
    }

    public void goToCheckOut(){
        // open the shopping cart for checkout
        clickLink("[id='cart']");
    }

    public void goToHomePage(){
        clickLink("[href='/litecart/']");
    }

    public void areNumbersEqual(int i, int j){
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver){
                //
                if (i == j) return true;
                return null;
            }
        });
    }
}
