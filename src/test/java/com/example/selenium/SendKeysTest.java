package com.example.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendKeysTest {

    // Navegador
    WebDriver driver;

    @BeforeEach
    void setUp() {
        String dir = System.getProperty("user.dir"); // ruta del proyecto
        String driverUrl = "/drivers/chromedriver.exe";
        String url = dir + driverUrl;
        System.setProperty("webdriver.chrome.driver", url);
        driver = new ChromeDriver(); // Google Chrome
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testInputForm(){
        driver.get("https://seleniumbase.io/demo_page");

        WebElement input = driver.findElement(By.id("myTextInput"));
        // Enviar información al campo del formulario
        input.sendKeys("Texto desde selenium");

        sleep();

        // comprobar que el input tiene el texto introducido
        input = driver.findElement(By.id("myTextInput"));
        String inputValue = input.getAttribute("value");
        assertEquals("Texto desde selenium", inputValue);

    }

    @Test
    void testSearchDuckDuckGo(){
        driver.get("https://duckduckgo.com/");
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Selenium java examples" + Keys.ENTER);
        sleep();
    }

    @Test
    void testSearchDuckDuckGo2(){
        driver.get("https://duckduckgo.com/");
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Selenium java examples" + Keys.ENTER);

        // Prueba a limpiar el campo de búsqueda para realizar otra búsqueda:
        input = driver.findElement(By.name("q"));
        input.clear();
        input.sendKeys("JUnit 5" + Keys.ENTER);

    }

    @Test
    void testSearchGoogle(){
        driver.get("https://www.google.es");
        // 1. aceptar terminos y condiciones
        //sleep();
        WebElement acceptButton = driver.findElement(By.xpath("//div[text() = 'Acepto']"));
        assertEquals("jyfHyd", acceptButton.getAttribute("class"));
        acceptButton.click();
        //sleep();
        // 2. realizar busqueda
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Selenium java examples");
        input.submit();
        System.out.println("fin");
    }

    // Selector de formulario
    @Test
    void testDropDown(){
        driver.get("https://seleniumbase.io/demo_page");

        // abrir el selector
        WebElement selector = driver.findElement(By.id("mySelect"));
        selector.click();

        // Opción 1: seleccionar utilizando selector css
//        List<WebElement> options = driver.findElements(By.cssSelector("#mySelect option"));
//        assertEquals(4, options.size());
//        options.get(3).click();

        // Opcion 2: seleccionar utilizando selector xpath (opcional)
        WebElement option = driver.findElement(By.xpath("//select[@id='mySelect']/option[@value='100%']"));
        option.click();

        System.out.println("fin");
    }



    private void sleep() {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}






