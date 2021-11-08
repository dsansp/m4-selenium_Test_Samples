package com.example.demoqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FormTest {

    private static final String URL = "https://demoqa.com/automation-practice-form";
    private static final String DROP_DOWN_MATHS = "//div[text()='Maths' and contains(@class, 'subjects-auto-complete__option')]";
    private static final String SELECTED_MATHS = "//div[text()='Maths' and contains(@class, 'subjects-auto-complete__multi-value__label')]";
    private static final String GENDER_MALE = "//label[@for='gender-radio-1']";
    private static final String HOBBIES_SPORTS = "//label[@for='hobbies-checkbox-1']";
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
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    void formTest() {

        driver.findElement(By.id("firstName")).sendKeys("Ioan");
        driver.findElement(By.id("lastName")).sendKeys("Stirbu");
        driver.findElement(By.id("userEmail")).sendKeys("example@example.com");
        driver.findElement(By.xpath(GENDER_MALE)).click();
        driver.findElement(By.id("userNumber")).sendKeys("6665554441");

        driver.findElement(By.id("dateOfBirthInput")).click();
        driver.findElement(By.cssSelector(".react-datepicker__day--016")).click();

        WebElement input = driver.findElement(By.id("subjectsInput"));
        js.executeScript("arguments[0].scrollIntoView();", input);
        input.sendKeys("M");
        driver.findElement(By.xpath(DROP_DOWN_MATHS)).click();
        assertEquals(1, driver.findElements(By.xpath(SELECTED_MATHS)).size());
        // IMPORTANTE: hemos extraido el HTML de un elemento para conocer su contenido
        //WebElement menu = driver.findElement(By.cssSelector(".subjects-auto-complete__menu"));
        // System.out.println(menu.getAttribute("innerHTML"));

        driver.findElement(By.xpath(HOBBIES_SPORTS)).click();

        // Subir imagen:
        String img = "C:\\Users\\alanj\\Desktop\\Selenium_Logo.png";
        WebElement uploadPicture = driver.findElement(By.id("uploadPicture"));
        uploadPicture.sendKeys(img);

        driver.findElement(By.id("currentAddress")).sendKeys("Lorem ipsum dolor");

        WebElement state = driver.findElement(By.id("react-select-3-input"));
        assertNull(state.getAttribute("disabled")); // null

        WebElement city = driver.findElement(By.id("react-select-4-input"));
        assertEquals("true", city.getAttribute("disabled")); // true

        // Seleccionar State
        driver.findElement(By.id("react-select-3-input")).sendKeys("N");
        WebElement stateMenu = driver.findElement(By.xpath("//div[@id='stateCity-wrapper']//div[contains(@class, 'css-') and contains(@class, '-menu')]"));
        System.out.println(stateMenu.getAttribute("innerHTML"));

        driver.findElement(By.id("react-select-3-option-0")).click();

        city = driver.findElement(By.id("react-select-4-input"));
        assertNull(city.getAttribute("disabled")); // null

        // seleccionar city
        driver.findElement(By.id("react-select-4-input")).sendKeys("D");
        WebElement cityMenu = driver.findElement(By.xpath("//div[@id='city']//div[contains(@class, 'css-') and contains(@class, '-menu')]"));
        System.out.println(cityMenu.getAttribute("innerHTML"));

        driver.findElement(By.id("react-select-4-option-0")).click();
        driver.findElement(By.id("submit")).click();


        assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());

        WebElement closeButton = driver.findElement(By.id("closeLargeModal"));

        js.executeScript("arguments[0].scrollIntoView();", closeButton);
        closeButton.sendKeys(Keys.ENTER);

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));

        assertEquals(0, driver.findElements(By.className("modal-content")).size());

        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("modal-content")));

    }

private void deleteAds(){
        // TODO
        /*
        WebElement yourElement = …
        JavaScriptExecutor jsExecutor = (JavaScriptExecutor) webDriver;
        jsExecutor.executeScript(
            "arguments[0].parentNode.removeChild(arguments[0])", yourElement);
         */
}

}
