
package cl.diinf.testCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void testCreationCorrect() throws Exception {
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
    
    @Test
    public void testError() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/US006/NoExisteTipo.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
}
