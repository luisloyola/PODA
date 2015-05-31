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
public class testEnunciado {
    
    private Selenium selenium;
    
    public testEnunciado() {
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
     * Test que valida si se muestra un mensaje tras no tener la etiqueta enunciado
     * @throws Exception 
     */
    @Test
    public void testErrorSinEnunciado() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/enunciado/SinEnunciado.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta enunciado de cierre tiene el slash
     * @throws Exception 
     */
    @Test
    public void testErrorEnunciadoCerradoSinSlash() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/enunciado/EnunciadoCierreSinSlash.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta enunciado tiene la etiqueta de inicio
     * @throws Exception 
     */
    @Test
    public void testErrorEnunciadoSinAbrir() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/enunciado/EnunciadoNoAbierto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta enunciado tiene la etiqueta de cierre
     * @throws Exception 
     */
    @Test
    public void testErrorEnunciadoSinCerrar() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/enunciado/EnunciadoNoCerrado.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta enunciado de apertura tiene el delimitador '<'
     * @throws Exception 
     */
    @Test
    public void testErrorEnunciadoSinLimitadorDer() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/enunciado/EnunciadoSin<.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta enunciado de apertura tiene el delimitador '>'
     * @throws Exception 
     */
    @Test
    public void testErrorEnunciadoSinLimitadorIzq() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/enunciado/EnunciadoSin>.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta enunciados no tiene atributos
     * @throws Exception 
     */
    @Test
    public void testErrorEnunciadoConAtributos() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/enunciado/EnunciadoConAtributo.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    
    /**
     * Test que valida si etiqueta enunciados no tiene atributos
     * @throws Exception 
     */
    @Test
    public void testErrorEnunciadoSinContenido() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/enunciado/EnunciadoSinContenido.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
    }  
    @Test
    public void testErrorEnunciadoConEnunciado() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/enunciado/EnunciadoConEnunciado.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }   
}
