package com.example.phptravels;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TravelTest {

    private static final String URL = "https://www.phptravels.net/";
    WebDriver driver;
    JavascriptExecutor js;


    @BeforeEach
    void setUp() {
        String dir = System.getProperty("user.dir"); // ruta del proyecto
        String driverUrl = "/drivers/chromedriver.exe";
        String url = dir + driverUrl;
        System.setProperty("webdriver.chrome.driver", url);
        driver = new ChromeDriver(); // Google Chrome
        js = (JavascriptExecutor) driver;
        driver.get(URL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    void travelTest() throws InterruptedException {


        driver.findElement(By.id("select2-hotels_city-container")).click();

        driver.findElement(By.className("select2-search__field")).sendKeys("Dubai");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='select2-hotels_city-results']//li[text()='Dubai,United Arab Emirates']")));

        // Thread.sleep(5000);

        //System.out.println(driver.findElement(By.cssSelector("#select2-hotels_city-results li")).getAttribute("innerHTML"));
        driver.findElement(By.cssSelector("#select2-hotels_city-results li")).click();

        driver.findElement(By.id("submit")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("data")));

        // hotels_amenities_ Hay 6 hoteles
        // By.className("hotels_amenities_")
        // By.cssSelector(".hotels_amenities_")
        List<WebElement> hotels = driver.findElements(By.className("hotels_amenities_"));
        assertEquals(6, hotels.size());


        // testear filtro 5 estrellas
        //  rating__text
        // custom-control-label
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.xpath("//label[@for='stars_5']"));
        Actions action = new Actions(driver);

        action.moveToElement(element).click().perform();
        Thread.sleep(5000);

        List<WebElement> hotelsafter = driver.findElements(By.className("stars_5"));
        assertEquals(1, hotelsafter.size());

   

        // testear pantalla Details: pulsar en el primer hotel en el botón Details y comprobar la pantalla que aparece, que sea de Dubai

        driver.findElement(By.xpath("//a[@href='https://www.phptravels.net/en/usd/hotel/dubai/jumeirah-beach-hotel/32/11-11-2021/12-11-2021/1/2/0/1//0/0/']")).click();
//xpath ("//li[text() =’ Dubai ’]"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
        WebElement check = driver.findElement(By.id("description"));

        assertTrue(check.getText().contains("Dubai"));

    }
}
