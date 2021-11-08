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

import static org.junit.jupiter.api.Assertions.*;

/**
 * https://demoqa.com/webtables
 *
 * Test:
 * submitEmpyFormTest()
 * submitIncorrectEmailTest()
 * submitOkTest()
 */
public class WebtablesTest {

    private static final String URL = "https://demoqa.com/webtables";
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
        driver.get(URL); // navega hacia la pantalla a testear
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    void yesRadioTest() {
        assertEquals(3,  driver.findElements(By.className("action-buttons")).size());
//        List<WebElement> deleteButtons = driver.findElements(By.xpath("//span[@title='Delete']"));
//        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("span[id*='delete-record-']"));

        // Alternativa 1
//        List<WebElement> deleteButtons = driver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
//        deleteButtons.get(0).click();
//        assertEquals(2,  driver.findElements(By.className("action-buttons")).size());
//
//        deleteButtons = driver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
//        deleteButtons.get(0).click();
//        assertEquals(1,  driver.findElements(By.className("action-buttons")).size());
//
//        deleteButtons = driver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
//        deleteButtons.get(0).click();
//        assertEquals(0,  driver.findElements(By.className("action-buttons")).size());

        // Alternativa 2: Utilizar un bucle for
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
        for(int i = deleteButtons.size(); i > 0; i--){ // for(int i = 0; i < deleteButtons.size(); i++){
            deleteButtons = driver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
            deleteButtons.get(0).click();
            assertEquals(i - 1,  driver.findElements(By.className("action-buttons")).size());
        }

        // Alternativa 3: utilizar un bucle do while
//        List<WebElement> deleteButtons = driver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
//        int count = deleteButtons.size();
//        do{
//            deleteButtons.get(0).click();
//            assertEquals(count - 1,  driver.findElements(By.className("action-buttons")).size());
//            deleteButtons = driver.findElements(By.xpath("//span[contains(@id, 'delete-record-')]"));
//            count--;
//        }while(deleteButtons.size() != 0);

        // Alternativa 4: bucle while (TODO)

    }

    @Test
    void searchOkTest(){
        assertEquals(3,  driver.findElements(By.className("action-buttons")).size());

        WebElement search = driver.findElement(By.id("searchBox"));
        search.sendKeys("Legal");

        assertEquals(1,  driver.findElements(By.className("action-buttons")).size());
    }

    @Test
    void searchNotFoundTest(){
        assertEquals(3,  driver.findElements(By.className("action-buttons")).size());

        WebElement search = driver.findElement(By.id("searchBox"));
        search.sendKeys("Test");

        assertEquals(0,  driver.findElements(By.className("action-buttons")).size());
    }

    @Test
    void searchTest(){
        assertEquals(3,  driver.findElements(By.className("action-buttons")).size());

        WebElement search = driver.findElement(By.id("searchBox"));
        search.sendKeys("Legal");

        assertEquals(1,  driver.findElements(By.className("action-buttons")).size());
    }

    @Test
    void submitEmptyFormTest() {

        driver.findElement(By.id("addNewRecordButton")).click();

        assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());

        WebElement button = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].scrollIntoView();", button);
        button.click();

        assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());

    }

    @Test
    void nameLengthTest() {

        driver.findElement(By.id("addNewRecordButton")).click();

        driver.findElement(By.id("firstName")).sendKeys("wertygfdrtwertygfdrtwertygfdrt"); // 30 letras

        assertEquals(25, driver.findElement(By.id("firstName")).getAttribute("value").length()); // límite 25 letras
        assertEquals("wertygfdrtwertygfdrtwerty", driver.findElement(By.id("firstName")).getAttribute("value")); // límite 25 letras

    }

    @Test
    void addOkTest() {

        assertEquals(3,  driver.findElements(By.className("action-buttons")).size());
        driver.findElement(By.id("addNewRecordButton")).click();
        driver.findElement(By.id("firstName")).sendKeys("Name 1");
        driver.findElement(By.id("lastName")).sendKeys("LastName 1");
        driver.findElement(By.id("userEmail")).sendKeys("valid@valid.com");
        driver.findElement(By.id("age")).sendKeys("44");
        driver.findElement(By.id("salary")).sendKeys("44000");
        driver.findElement(By.id("department")).sendKeys("Legal");

        WebElement button = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].scrollIntoView();", button);
        button.click();

        assertEquals(4,  driver.findElements(By.className("action-buttons")).size());


    }

    @Test
    void editRowTest() {

        List<WebElement> editButtons = driver.findElements(By.xpath("//span[contains(@id, 'edit-record-')]"));
        editButtons.get(0).click();
        assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
        assertEquals("Cierra", driver.findElement(By.id("firstName")).getAttribute("value"));
        assertEquals("Vega", driver.findElement(By.id("lastName")).getAttribute("value"));
        assertEquals("cierra@example.com", driver.findElement(By.id("userEmail")).getAttribute("value"));
        assertEquals("39", driver.findElement(By.id("age")).getAttribute("value"));
        assertEquals("10000", driver.findElement(By.id("salary")).getAttribute("value"));
        assertEquals("Insurance", driver.findElement(By.id("department")).getAttribute("value"));

    }

    @Test
    void closeModal() throws InterruptedException {

        driver.findElement(By.id("addNewRecordButton")).click();
        assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
        driver.findElement(By.className("close")).click();
        // TODO replace Thread with Wait https://www.selenium.dev/documentation/webdriver/waits/
        Thread.sleep(5000L);
        assertEquals(0,  driver.findElements(By.className("modal-content")).size());

    }

    @Test
    void paginationTest(){

        // .pagination-bottom select
        // //div[@class='pagination-bottom']//select

        assertEquals(10, driver.findElements(By.className("rt-tr-group")).size());

        WebElement selector = driver.findElement(By.cssSelector(".pagination-bottom select"));
        js.executeScript("arguments[0].scrollIntoView();", selector);
        selector.click();

        // seleccionar el option de 20 páginas
        // .pagination-bottom select option[value='20']
        //div[@class='pagination-bottom']//select//option[@value='20']
        WebElement option20 = driver.findElement(By.cssSelector(".pagination-bottom select option[value='20']"));
        option20.click();

        assertEquals(20, driver.findElements(By.className("rt-tr-group")).size());

    }

}
