/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.testCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nacho
 */
public class testTexto {
    
    private Selenium selenium;
    
    public testTexto() {
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
     * Test que valida si se muestra un mensaje tras no tener la etiqueta objeto
     * @throws Exception 
     */
    @Test
    public void testErrorSinTexto() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/SinTexto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
    }
    /**
     * Test que valida si la etiqueta texto de cierre tiene el slash
     * @throws Exception 
     */
    @Test
    public void testErrorTextoCerradoSinSlash() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/TextoCerrarSinSlash.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta texto tiene la etiqueta de inicio
     * @throws Exception 
     */
    @Test
    public void testErrorTextoSinAbrir() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/TextoNoAbierto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta texto tiene la etiqueta de cierre
     * @throws Exception 
     */
    @Test
    public void testErrorTextoSinCerrar() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/TextoNoCerrado.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta texto de apertura tiene el delimitador '<'
     * @throws Exception 
     */
    @Test
    public void testErrorTextoSinLimitadorDer() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/TextoSin<.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta texto de apertura tiene el delimitador '>'
     * @throws Exception 
     */
    @Test
    public void testErrorTextoSinLimitadorIzq() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/TextoSin>.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta textos no tiene atributos
     * @throws Exception 
     */
    @Test
    public void testErrorTextoSinAtributos() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/TextoSinTipo.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta textos tiene un tipo no valido
     * @throws Exception 
     */
    @Test
    public void testErrorTextoTipoIncorrecto() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/TextoTipoIncorrecto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta textos tiene un atributo sin dato
     * @throws Exception 
     */
    @Test
    public void testErrorTextoTipoSinDato() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/TextoTipoSinDato.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta textos no tiene atributos
     * @throws Exception 
     */
    @Test
    public void testErrorTextoSinContenido() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/TextoSinContenido.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
    }
    @Test
    public void testErrorTextoConTexto() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/texto/TextoConTexto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
}
