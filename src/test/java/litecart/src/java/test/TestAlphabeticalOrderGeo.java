package litecart.src.java.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by valeriyagagarina on 5/14/17.
 */
public class TestAlphabeticalOrderGeo extends BaseTest {

    private WebElement table;
    private List<WebElement> rows;

    @Test
    public void testAlphabeticalOrderCountries(){

        goTo("http://localhost/litecart/admin/?app=countries&doc=countries");
        login("admin", "admin", "//button[contains(text(),'Login')]");

        int countryColNum = 4;
        int zoneColNum = 5;
        String currentCountryName = "";
        String nextCountryName = "";
        String currentZoneName = "";
        String nextZoneName = "";

        initTable();

        // check if countries are in alphabetical order
        for(int i = 0; i < rows.size()-1; i++){

            currentCountryName = rows.get(i).findElements(By.tagName("td")).get(countryColNum).getText();
            nextCountryName  = rows.get(i+1).findElements(By.tagName("td")).get(countryColNum).getText();
            System.out.println("Country name is " + currentCountryName);
            Assert.assertTrue(StringUtils.stripAccents(nextCountryName).compareTo(StringUtils.stripAccents(currentCountryName)) > 0);

            // if there are more then 1 zone for a country click the link with country name
            int zonesCount = Integer.parseInt(rows.get(i).findElements(By.tagName("td")).get(zoneColNum).getText());
            if (zonesCount > 1){
                driver.findElement(By.linkText(currentCountryName)).click();

                // check if zones are in alphabetical order (for countries with more then one zone)
                initTable();
                List<WebElement> allZones = table.findElements(By.cssSelector(("[name$='][name]']")));
                for(int j = 0; j < rows.size()-1; j++){

                    currentZoneName = StringUtils.stripAccents((allZones.get(j).getAttribute("value")));
                    nextZoneName  = StringUtils.stripAccents(allZones.get(j+1).getAttribute("value"));

                    System.out.println("    Zone name is " + currentZoneName);
                    Assert.assertTrue(nextZoneName.compareTo(currentZoneName) > 0);
                }

                driver.navigate().back();
                initTable();
            }
        }

        logout();
    }

    @Test
    public void testAlphabeticalOrderOfZones(){
        goTo("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        login("admin", "admin", "//button[contains(text(),'Login')]");

        initTable();

        int countryColNum = 2;
        int zoneColNum = 2;
        String countryName = "";
        String currentZoneName = "";
        String nextZoneName = "";

        // check if countries are in alphabetical order
        for(int i = 0; i < rows.size(); i++){

            countryName = rows.get(i).findElements(By.tagName("td")).get(countryColNum).getText();
            System.out.println("Country name is " + countryName);

            // click the link with country name
            driver.findElement(By.linkText(countryName)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("td")));

            // check if zones are in alphabetical order
            initTable();
            for(int j = 0; j < rows.size()-1; j++){

                currentZoneName = StringUtils.stripAccents(rows.get(j).findElements(By.tagName("td")).
                        get(zoneColNum).getText());
                nextZoneName  = StringUtils.stripAccents(rows.get(j+1).findElements(By.tagName("td")).
                        get(zoneColNum).getText());
                System.out.println("    Zone name is " + currentZoneName);
                Assert.assertTrue(nextZoneName.compareTo(currentZoneName) > 0);
                }

                driver.navigate().back();
                initTable();

        }
        logout();
    }

    private void initTable(){
        table = driver.findElement(By.tagName("tbody"));
        rows = table.findElements(By.tagName("tr"));
    }

}
