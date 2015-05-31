
package cl.diinf.testCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testGenerales {
    
    private Selenium selenium;
    
    public testGenerales() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
        selenium.start();
    }
    
    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
    /**
     * Prueba para evaluar creacion correcta de un objeto
     * @throws Exception 
     */
    @Test
    public void testCreationCorrect() throws Exception {
        selenium.open("PODA-web/");        
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Template.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");

    }    
    /**
     * Prueba para validar el ingreso de un archivo que incluye reglas DTD
     * @throws Exception 
     */
    @Test
    public void testErrorConDTD() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Generales/ConDTD.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Prueba para validar el ingreso de un archivo que incluye reglas DTD con errores
     * @throws Exception 
     */
    @Test
    public void testErrorConDTDIncorrecto() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Generales/ConDTDIncorrecto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Prueba para validar el ingreso de un archivo con etiquetas de bloque cruzadas
     * @throws Exception 
     */
    @Test
    public void testErrorCruzamientoBloques() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Generales/CruzamientoDeBloques.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Prueba para validar el ingreso de un archivo con etiquetas de escenas cruzadas
     * @throws Exception 
     */
    @Test
    public void testErrorCruzamientoEscenas() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Generales/CruzamientoDeEscenas.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Prueba para validar el ingreso de un archivo con etiquetas de textos cruzadas
     * @throws Exception 
     */
    @Test
    public void testErrorCruzamientoTextos() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Generales/CruzamientoDeTextos.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Prueba para validar el ingreso de un archivo con etiqueta no existente
     * @throws Exception 
     */
    @Test
    public void testErrorEtiquetaNoExistente() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Generales/EtiquetaNoExistente.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
    }
}
