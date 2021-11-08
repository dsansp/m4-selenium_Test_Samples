## Índice

1. Crear proyecto maven con dependencias: selenium y junit 
2. Descarga de Drivers: chrome, geckodriver...
3. Crear setUp y tearDown para configurar WebDriver
4. Selectores de elementos web (WebElement)
5. Interactuar con los elementos de una web: `sendKeys()`, `submit()`, `click()`
6. Crear suite de test sobre la página demoqa.com para practicar



## Configuración Selenium 4

### 1. Agregar dependencias maven: 

* junit-jupiter 
* selenium-java

### 2. Descargar Driver

Descargar driver versión correspondiente a nuestro navegador y sistema operativo.

* Chrome: https://chromedriver.storage.googleapis.com/index.html
* Firefox: https://github.com/mozilla/geckodriver/releases

## 3. Configurar setUp y tearDown

@BeforeEach
setUp()

```
String dir = System.getProperty("user.dir");
String driverUrl = "/drivers/chromedriver.exe";
String url = dir + driverUrl;
System.setProperty("webdriver.chrome.driver", url);
driver = new ChromeDriver(); // Google Chrome
```

@AfterEach 
tearDown() 

```
driver.quit();
```

## 4. Seleccionar elementos web 

Seleccionar elementos mediante los métodos:

* findElement
* findElements

y la clase `By`.

* id
* tag name
* name
* class name
* css selector
* xpath: https://www.w3schools.com/xml/xpath_intro.asp 

## 5. Interacción con elementos html desde selenium

Rellenar un campo de un formulario: 

`sendKeys()`

Hacer click sobre un elemento: 

`click()`

Enviar un formulario:

`submit()`

## Páginas web para testear con selenium 

* https://seleniumbase.io/demo_page
* https://www.phptravels.net/
* https://demoqa.com
* https://testpages.herokuapp.com/styled/index.html
* https://demo.openmrs.org/openmrs/
    * User: Admin
    * Password: Admin123

## Recursos 

https://www.toolsqa.com/selenium-webdriver/selenium-tutorial/

## Selenium 3 vs Selenium 4

https://www.selenium.dev/documentation/getting_started/how_to_upgrade_to_selenium_4/


## Curso testing

1. Java
2. Spring Boot
3. Testing (Testing unitario y de integración)
4. Selenium (Testing interfaz de usuario)
5. Automatización

## Excepciones comunes en Selenium

org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document

## Selectores css:

1. por id
#idname

2. Por clase
   .classname

3. Por más de una clase
   .classname1.classname2.classname3

4. Combinar id más clase:
#idname.classname
#idname.classname1.classname2

5. Por nombre de etiqueta html más clase
   div.classname
   div#idname.classname

## Conceptos selenium: 

1. Cargar driver de navegador: Chrome, Firefox, ...
2. WebDriver y WebElement
3. Abrir página web: `driver.get()`
4. Comprobar título: `driver.getTitle()`
5. Selectores:
   1. `By.tagName`
   2. `By.name`
   3. `By.id `
   4. `By.cssSelector`
   5. `By.xpath`
   6. `By.className`
6. Enviar datos / teclas: `sendKeys()`
7. Scroll hacia un elemento: `js.executeScript`
8. Actions
   1. `doubleClick()`
   2. `contextClick()`
   3. `keyDown(Keys.CONTROL).click(option)`
   4. `moveToElement()`
9. WebDriverWait y ExpectedConditions para realizar esperas controladas (no utilizar `Thread.sleep()`)
10. BaseTest con herencia para reutilizar setUp() en los tests
11. Crear funciones propias para tener atajos a ciertas acciones comunes como hacer clic
12. `getAttribute("innerHTML")` permite recuperar el HTML de un elemento para ver su contenido
13. `isDisplayed()` permite comprobar si un elemento está mostrado (display:block, etc.)
14. `getText()` permite obtener el texto de un elemento

## Problemas frecuentes con selenium

Es posible que ejecutando un test este falle porque selecciona un elemento y no es lo que esperábamos
lo cual impide que la pantalla siga avanzando. 

En esos casos se recomienda investigar paso a paso los elementos que se estén seleccionando con 
ayuda de `innerHTML`. 

Se recomienda ajustar bien los selectores, ver ejemplo PHPTravels selector Dubai se arregló con xpath.

```
#select2-hotels_city-results li

//ul[@id='select2-hotels_city-results']//li[text()='Dubai,United Arab Emirates']
```
El segundo es más específico, impide que se seleccione por error otro elemento de la lista.


## Overview M5

1. Apache Maven: https://maven.apache.org/install.html
2. GitHub Actions
3. SonarCloud
4. Jenkins: https://www.jenkins.io/doc/book/installing/
5. SonarQube: https://www.sonarqube.org/downloads/
6. Lambda Test:
   1. https://www.lambdatest.com/support/docs/getting-started-with-lambdatest-automation/
