
package cl.diinf.testCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testOpciones {
    
    private Selenium selenium;
    
    public testOpciones() {
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
     * Test que valida si se muestra un mensaje tras no tener la etiqueta opciones
     * @throws Exception 
     */
    @Test
    public void testErrorSinOpciones() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/SinOpciones.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta de cierre de opciones tiene el /
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesCerradoSinSlash() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesCierreSinSlash.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta opciones tiene la etiqueta de inicio
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesSinAbrir() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesNoAbierto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta opciones tiene la etiqueta de cierre
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesSinCerrar() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesNoCerrado.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta opciones de apertura tiene el delimitador '<'
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesSinLimitadorDer() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesSin<.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta opciones de apertura tiene el delimitador '>'
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesSinLimitadorIzq() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesSin>.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta opcioness no tiene atributos
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesConAtributos() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesConAtributo.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    
        /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta opciones
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesConContenido() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesConContenido.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
    }
    /**
     * Test que valida si se agrega una etiqueta opciones vacia
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesVacio() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesVacio.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");        
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si se agrega una etiqueta opciones donde ninguna de sus alternativas es solucion
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesSinSolucion() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesSinSolucion.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si se agrega una opcion con una etiqueta de texto, el cual no es permitido
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesConTexto() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesConTexto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    } 
    /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta opciones
     * @throws Exception 
     */
    @Test
    public void testErrorOpcionesConOpciones() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/opciones/OpcionesConOpciones.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
}
