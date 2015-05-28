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

/**
 *
 * @author nacho
 */
public class testSelenium {
    
    private Selenium selenium;
    
    public testSelenium() {
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

    @Test
    public void testPoda() throws Exception {
        selenium.open("PODA-web/");        
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/Template.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
        selenium.click("link=→");
    }    
}
