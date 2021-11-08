package com.example.demoqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AccordianTest {

    private static final String URL = "https://demoqa.com/accordian";
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
    void section1Test() {
        assertEquals(1,
                driver.findElements(By.cssSelector("#accordianContainer .collapse.show")).size());

        WebElement heading1 = driver.findElement(By.id("section1Heading"));
        heading1.click();

        assertEquals(0,
                driver.findElements(By.cssSelector("#accordianContainer .collapse.show")).size());
    }

    @Test
    void section2Test() throws InterruptedException {
        assertEquals(1,
                driver.findElements(By.id("section2Content")).size());

        WebElement section2 = driver.findElement(By.id("section2Content"));
        assertFalse(section2.isDisplayed()); // comprueba que section2 no se ve
        assertTrue(driver.findElement(By.id("section1Content")).isDisplayed()); // comprueba que section 1 si se ve

        WebElement heading2 = driver.findElement(By.id("section2Heading"));
        js.executeScript("arguments[0].scrollIntoView();", heading2);
        heading2.click();

        section2 = driver.findElement(By.id("section2Content"));
        assertTrue(section2.isDisplayed()); // comprueba que section2 sí se ve

        // IMPORTANTE: esperar a que se produzca el ocultamiento de la section1 para poder comprobar que ya no es visible

        // 8,5 s
//        Thread.sleep(3000L);
//        assertFalse(driver.findElement(By.id("section1Content")).isDisplayed());

        // 6,1 s
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("section1Content")));
        assertFalse(driver.findElement(By.id("section1Content")).isDisplayed()); // comprueba que section 1 si se ve
    }

}
