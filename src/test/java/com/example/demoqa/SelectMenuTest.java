package com.example.demoqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectMenuTest {

    private static final String URL = "https://demoqa.com/select-menu";
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
    void selectTwoElements() {

        WebElement multiSelect = driver.findElement(By.id("cars"));
        js.executeScript("arguments[0].scrollIntoView();", multiSelect);

        WebElement input = driver.findElement(By.xpath("//option[@value='volvo']"));
        input.click();

        WebElement input2 = driver.findElement(By.xpath("//option[@value='saab']"));

        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).click(input2).perform();

    }

    @Test
    void selectAll() {

        WebElement multiSelect = driver.findElement(By.id("cars"));
        js.executeScript("arguments[0].scrollIntoView();", multiSelect);

        // equivalente xpath: //select[@id='cars']/option
        List<WebElement> options = driver.findElements(By.cssSelector("#cars option"));

        for (WebElement option: options) {
            Actions action = new Actions(driver);
            action.keyDown(Keys.CONTROL).click(option).perform();
        }



    }

}
