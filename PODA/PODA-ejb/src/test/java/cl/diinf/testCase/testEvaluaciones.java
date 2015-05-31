
package cl.diinf.testCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testEvaluaciones {
    
    private Selenium selenium;
    
    public testEvaluaciones() {
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
     * Test que valida si se muestra un mensaje tras no tener la etiqueta evaluaciones
     * @throws Exception 
     */
    @Test
    public void testErrorSinEvaluaciones() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/evaluaciones/SinEvaluaciones.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta de cierre de evaluaciones tiene el /
     * @throws Exception 
     */
    @Test
    public void testErrorEvaluacionesCerradoSinSlash() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/evaluaciones/EvaluacionesCierreSinSlash.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta evaluaciones tiene la etiqueta de inicio
     * @throws Exception 
     */
    @Test
    public void testErrorEvaluacionesSinAbrir() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/evaluaciones/EvaluacionesNoAbierto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta evaluaciones tiene la etiqueta de cierre
     * @throws Exception 
     */
    @Test
    public void testErrorEvaluacionesSinCerrar() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/evaluaciones/EvaluacionesNoCerrado.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta evaluaciones de apertura tiene el delimitador '<'
     * @throws Exception 
     */
    @Test
    public void testErrorEvaluacionesSinLimitadorDer() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/evaluaciones/EvaluacionesSin<.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta evaluaciones de apertura tiene el delimitador '>'
     * @throws Exception 
     */
    @Test
    public void testErrorEvaluacionesSinLimitadorIzq() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/evaluaciones/EvaluacionesSin>.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta evaluacioness no tiene atributos
     * @throws Exception 
     */
    @Test
    public void testErrorEvaluacionesConAtributos() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/evaluaciones/EvaluacionesConAtributo.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    
        /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta evaluaciones
     * @throws Exception 
     */
    @Test
    public void testErrorEvaluacionesConContenido() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/evaluaciones/EvaluacionesConContenido.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");   
        selenium.click("link=→");
    }
        /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta evaluaciones
     * @throws Exception 
     */
    @Test
    public void testErrorEvaluacionesConEvaluaciones() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/evaluaciones/EvaluacionesConEvaluaciones");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si se agrega un una evaluacion vacia
     * @throws Exception 
     */
    @Test
    public void testErrorEvaluacionesVacias() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/evaluaciones/EvaluacionesVacias.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
    }
}
