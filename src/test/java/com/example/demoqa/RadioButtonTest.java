package com.example.demoqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://demoqa.com/radio-button
 *
 * Test:
 * submitEmpyFormTest()
 * submitIncorrectEmailTest()
 * submitOkTest()
 */
public class RadioButtonTest {

    private static final String URL = "https://demoqa.com/radio-button";
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
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    void yesRadioTest() {
        driver.get(URL);
        assertEquals(0,  driver.findElements(By.className("text-success")).size());

        WebElement radioLabel = driver.findElement(By.xpath("//label[@for='yesRadio']"));
        radioLabel.click();

        assertEquals(1,  driver.findElements(By.className("text-success")).size());
        WebElement textSuccess =  driver.findElement(By.className("text-success"));
        assertEquals("Yes", textSuccess.getText());
    }

    @Test
    void impressiveRadioTest() {
        driver.get(URL);
        assertEquals(0,  driver.findElements(By.className("text-success")).size());

        WebElement radioLabel = driver.findElement(By.xpath("//label[@for='impressiveRadio']"));
        radioLabel.click();

        assertEquals(1,  driver.findElements(By.className("text-success")).size());
        WebElement textSuccess =  driver.findElement(By.className("text-success"));
        assertEquals("Impressive", textSuccess.getText());
    }

    @Test
    void noRadioTest() {
        driver.get(URL);
        assertEquals(0,  driver.findElements(By.className("text-success")).size());

        WebElement radioLabel = driver.findElement(By.xpath("//label[@for='noRadio']"));
        radioLabel.click();

        assertEquals(0,  driver.findElements(By.className("text-success")).size());
    }

}
