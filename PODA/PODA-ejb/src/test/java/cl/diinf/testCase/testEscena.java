
package cl.diinf.testCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testEscena {
    
    private Selenium selenium;
    
    public testEscena() {
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
     * Test que valida si se muestra un mensaje tras no tener la etiqueta escena
     * @throws Exception 
     */
    @Test
    public void testErrorSinEscena() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/SinEscena.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta de cierre de escena tiene el /
     * @throws Exception 
     */
    @Test
    public void testErrorEscenaCerradoSinSlash() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/EscenaCerradaSinSlash.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta escena tiene la etiqueta de inicio
     * @throws Exception 
     */
    @Test
    public void testErrorEscenaSinAbrir() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/EscenaNoAbierta.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta escena tiene la etiqueta de cierre
     * @throws Exception 
     */
    @Test
    public void testErrorEscenaSinCerrar() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/EscenaNoCerrada.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta escena de apertura tiene el delimitador '<'
     * @throws Exception 
     */
    @Test
    public void testErrorEscenaSinLimitadorDer() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/EscenaSin<.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta escena de apertura tiene el delimitador '>'
     * @throws Exception 
     */
    @Test
    public void testErrorEscenaSinLimitadorIzq() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/EscenaSin>.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta escenas no tiene atributos
     * @throws Exception 
     */
    @Test
    public void testErrorEscenaSinAtributos() throws Exception {
        
            selenium.open("/PODA-web/");
            selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/EscenaSinAtributos.xml");
            selenium.click("name=form:j_idt16");
            selenium.waitForPageToLoad("30000");
            selenium.click("css=span.ui-icon.ui-icon-close");
            selenium.click("css=span.ui-icon.ui-icon-close");        
    }
    /**
     * Test que valida si etiqueta escenas tiene un atributo faltante
     * @throws Exception 
     */
    @Test
    public void testErrorEscenaSinCadaAtributo() throws Exception {
        ArrayList <String> nameFiles = new ArrayList();
        nameFiles.add("EscenaSinTitulo");
        nameFiles.add("EscenaSinTipo");        
        for(int i = 0; i < 3 ; i++){
            selenium.open("/PODA-web/");
            selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/"+nameFiles.get(i)+".xml");
            selenium.click("name=form:j_idt16");
            selenium.waitForPageToLoad("30000");
            selenium.click("css=span.ui-icon.ui-icon-close");
            selenium.click("css=span.ui-icon.ui-icon-close");
        }
    }
    /**
     * Test que valida si se agrega un tipo no existente como atributo de la etiqueta escena
     * @throws Exception 
     */
    @Test
    public void testErrorEscenaTipo() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/EscenaConTipoIncorrecto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta escena
     * @throws Exception 
     */
        @Test
    public void testErrorEscenaTipoSinDato() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/EscenaConTipoSinDato.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta escena
     * @throws Exception 
     */
    @Test
    public void testErrorEscenaConContenido() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/EscenaConContenido.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
    }
        /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta escena
     * @throws Exception 
     */
    @Test
    public void testErrorEscenaConEscena() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/escena/EscenaConEscena.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
}
