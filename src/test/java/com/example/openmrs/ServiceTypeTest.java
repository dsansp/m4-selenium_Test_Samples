package com.example.openmrs;

import com.example.SeleniumSelectors;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTypeTest extends BaseTest{

    /**
     * Test para probar la funcionalidad de borrar en el listado, se pulsa el botón y se comprueba que se borra una fila
     */
    @Test
    void deleteRowTest() {

        // 1. Navegar hacia la pantalla deseada
        login();
        driver.findElement(By.id("appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension")).click();
        driver.findElement(By.id("appointmentschedulingui-manageAppointmentTypes-app")).click();
        assertEquals(1, driver.findElements(By.id("appointmentTypesTable")).size());

        // 2. Obtener la primera columna de cada fila
        List<WebElement> titleElements = driver.findElements(By.cssSelector("#appointmentTypesTable tbody tr td:first-child"));
        assertEquals(10, titleElements.size());

        // 3. Extraer título de la fila a borrar (queremos borrar la segunda, que está en la posición 1)
        String title = titleElements.get(1).getText();
        assertFalse(title.isEmpty());

        // 3.1 Comprobar el número de elementos en la lista
        String entries = driver.findElement(By.id("appointmentTypesTable_info")).getText();
        String[] palabras = entries.split(" ");
        String penultimo = palabras[palabras.length - 2];
        Integer totalRows = Integer.parseInt(penultimo);

        // 4. Borrar la fila en cuestión
        driver.findElement(By.id("appointmentschedulingui-delete-" + title)).click();
        driver.findElement(By.cssSelector("#delete-appointment-type-dialog:last-child .confirm")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//table[@id='appointmentTypesTable']//tbody//tr//td[text() = '" + title + "']")));



        // 5. Comprobar que ya no aparece el título en las filas
        titleElements = driver.findElements(By.cssSelector("#appointmentTypesTable tbody tr td:first-child"));
        List<String> titles = new ArrayList<>();
        for(WebElement titleElement : titleElements){
            String titulo = titleElement.getText(); // extrae el texto de cada web element
            titles.add(titulo);
        }
        assertFalse(titles.contains(title));

        // 6. Verificar que disminuye el numero total de entries en el listado
        entries = driver.findElement(By.id("appointmentTypesTable_info")).getText();
        palabras = entries.split(" ");
        penultimo = palabras[palabras.length - 2];
        Integer actualRows = Integer.parseInt(penultimo);
        assertEquals(totalRows - 1, actualRows);


    }


    /**
     * Test para probar la funcionalidad de editar, se pulsa el botón editar y se modifica un elemento, se comprueba que
     * el elemento aparece modificado en la lista de elementos.
     */
    @Test
    void editRowTest() {

        // 1. Navegar hacia la pantalla deseada
        login();
        driver.findElement(By.id("appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension")).click();
        driver.findElement(By.id("appointmentschedulingui-manageAppointmentTypes-app")).click();
        assertEquals(1, driver.findElements(By.id("appointmentTypesTable")).size());

        // 2. Obtener la primera columna de cada fila
        List<WebElement> titleElements = driver.findElements(By.cssSelector("#appointmentTypesTable tbody tr td:first-child"));
        assertEquals(10, titleElements.size());

        // 3. Extraer título de la fila a editar (queremos editar la segunda, que está en la posición 1)
        String titleBeforeEdit = titleElements.get(1).getText();
        assertFalse(titleBeforeEdit.isEmpty());

        // 4. Editar la fila en cuestión
        driver.findElement(By.id("appointmentschedulingui-edit-" + titleBeforeEdit)).click();

        // 4.1. Editar el nombre
        WebElement inputName = driver.findElement(By.id("name-field"));
        String name = inputName.getAttribute("value");
        // enviar el texto que se quiere agregar a lo que ya hay
        // inputName.sendKeys(" Editado");
        // borrar el texto y enviar uno nuevo
        inputName.clear();
        inputName.sendKeys(name + " Editado");


        // 4.2. Editar Duration
        WebElement inputDuration = driver.findElement(By.id("duration-field"));
        String duration = inputDuration.getAttribute("value");
        inputDuration.clear();
        inputDuration.sendKeys("99");

        // 4.3. Editar description
        WebElement inputDescription = driver.findElement(By.id("description-field"));
        inputDescription.clear();
        inputDescription.sendKeys("Lorem ipsum dolor dorime ameno");

        // guardar cambios
        driver.findElement(By.id("save-button")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("appointmentTypesTable")));

        // ASERCIONES: sobre columnas de la fila 2:
        List<WebElement> columns = driver.findElements(By.cssSelector("#appointmentTypesTable tbody tr:nth-child(2) td"));

        // 1. titulo td 1
        assertNotEquals(titleBeforeEdit, columns.get(0).getText());

        // 2. duracion td 2
        assertEquals("99", columns.get(1).getText());

        // 3. description td 3
        assertEquals("Lorem ipsum dolor dorime ameno", columns.get(2).getText());


    }

    /**
     * Test para añadir una nueva fila
     */
    @Test
    void addRowTest() {

        // 1. Navegar hacia la pantalla deseada
        login();
        driver.findElement(By.id("appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension")).click();
        driver.findElement(By.id("appointmentschedulingui-manageAppointmentTypes-app")).click();
        assertEquals(1, driver.findElements(By.id("appointmentTypesTable")).size());

        String entries = driver.findElement(By.id("appointmentTypesTable_info")).getText();
        String[] palabras = entries.split(" ");
        String penultimo = palabras[palabras.length - 2];
        Integer rowsBeforeAdd = Integer.parseInt(penultimo);

        // añadir nuevo elemento class="confirm appointment-type-label"
        driver.findElement(By.cssSelector(".confirm.appointment-type-label")).click();

        // rellenar formulario
        WebElement inputName = driver.findElement(By.id("name-field"));
        inputName.clear();
        inputName.sendKeys("Awesome service");

        WebElement inputDuration = driver.findElement(By.id("duration-field"));
        inputDuration.sendKeys("10");

        WebElement inputDescription = driver.findElement(By.id("description-field"));
        inputDescription.sendKeys("Lorem ipsum dolor dorime ameno");

        // guardar cambios
        driver.findElement(By.id("save-button")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("appointmentTypesTable")));

        // ASERCIONES: sobre columnas de la fila 1:
        List<WebElement> columns = driver.findElements(By.cssSelector("#appointmentTypesTable tbody tr:first-child td"));

        // 1. titulo td 1
        assertEquals("Awesome service", columns.get(0).getText());

        // 2. duracion td 2
        assertEquals("10", columns.get(1).getText());

        // 3. description td 3
        assertEquals("Lorem ipsum dolor dorime ameno", columns.get(2).getText());

        entries = driver.findElement(By.id("appointmentTypesTable_info")).getText();
        palabras = entries.split(" ");
        penultimo = palabras[palabras.length - 2];
        Integer actualRows = Integer.parseInt(penultimo);
        assertEquals(rowsBeforeAdd + 1, actualRows);
    }
}
