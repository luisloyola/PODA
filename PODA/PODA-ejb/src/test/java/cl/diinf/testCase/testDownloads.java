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
import org.junit.Ignore;

/**
 *
 * @author nacho
 */
public class testDownloads {
    
    private Selenium selenium;
    
    public testDownloads() {
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
     * Test que evalua la descarga del archivo base de un ejemplo xml
     * @throws Exception 
     */
    @Test
    public void testDescargaPlantilla() throws Exception {
        selenium.open("/PODA-web/");
        selenium.click("link=Ver Detalles »");
        selenium.click("link=Ver Detalles »");
        selenium.waitForPageToLoad("30000");
        selenium.click("name=j_idt21:j_idt23");
        selenium.click("name=j_idt21:j_idt23");
        selenium.waitForPageToLoad("30000");
    }
    /**
     * Test que evalua la descarga del objeto como zip (html + recursos)
     * @throws Exception 
     */
    @Ignore
    public void testDescargaZipObjeto() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Template.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("name=j_idt15:j_idt16");
        selenium.click("name=j_idt15:j_idt16");
        selenium.waitForPageToLoad("30000");
    }
    /**
     * Test que evalua la descarga del objeto como paquete scorm
     * @throws Exception 
     */
    @Ignore
    public void testDescargaScormObjeto() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Template.xml");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Template.xml");
        selenium.click("name=form:j_idt16");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("name=j_idt13:j_idt14");
        selenium.click("name=j_idt13:j_idt14");
        selenium.waitForPageToLoad("30000");
    }
}
