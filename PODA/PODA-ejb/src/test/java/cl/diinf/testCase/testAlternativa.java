/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.testCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import java.util.ArrayList;
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
public class testAlternativa {
    
    private Selenium selenium;
    
    public testAlternativa() {
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
    public void testErrorSinAlternativa() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/SinAlternativa.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta alternativa de cierre tiene el slash
     * @throws Exception 
     */
    @Test
    public void testErrorAlternativaCerradoSinSlash() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/AlternativaCierreSinSlash.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta alternativa tiene la etiqueta de inicio
     * @throws Exception 
     */
    @Test
    public void testErrorAlternativaSinAbrir() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/AlternativaNoAbierta.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta alternativa tiene la etiqueta de cierre
     * @throws Exception 
     */
    @Test
    public void testErrorAlternativaSinCerrar() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/AlternativaNoCerrada.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta alternativa de apertura tiene el delimitador '<'
     * @throws Exception 
     */
    @Test
    public void testErrorAlternativaSinLimitadorDer() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/AlternativaSin<.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta alternativa de apertura tiene el delimitador '>'
     * @throws Exception 
     */
    @Test
    public void testErrorAlternativaSinLimitadorIzq() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/AlternativaSin>.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta alternativas no tiene atributos
     * @throws Exception 
     */
    @Test
    public void testErrorAlternativaSinAtributos() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/AlternativaSinAtributos.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta alternativa tiene un atributo faltante
     * @throws Exception 
     */
    @Test
    public void testErrorAlternativaSinCadaAtributo() throws Exception {
        ArrayList <String> nameFiles = new ArrayList();
        nameFiles.add("AlternativaSinTema");
        nameFiles.add("AlternativaSinTipo");        
        for(int i = 0; i < 3 ; i++){
            selenium.open("/PODA-web/");
            selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/"+nameFiles.get(i)+".xml");
            selenium.click("name=form:j_idt16");
            selenium.waitForPageToLoad("30000");
            selenium.click("css=span.ui-icon.ui-icon-close");
            selenium.click("css=span.ui-icon.ui-icon-close");
        }
    }
    /**
     * Test que valida si etiqueta alternativas tiene un tipo no valido
     * @throws Exception 
     */
    @Test
    public void testErrorAlternativaTipoIncorrecto() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/AlternativaTipoIncorrecto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta alternativas tiene un atributo sin dato
     * @throws Exception 
     */
    @Test
    public void testErrorAlternativaTipoSinDato() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/AlternativaTipoSinDato.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta alternativas no tiene atributos
     * @throws Exception 
     */
    @Test
    public void testErrorAlternativaSinContenido() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/AlternativaSinContenido.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
    }
    @Test
    public void testErrorAlternativaConEtiquetaInterior() throws Exception {

        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/alternativa/AlternativaConTexto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
}
