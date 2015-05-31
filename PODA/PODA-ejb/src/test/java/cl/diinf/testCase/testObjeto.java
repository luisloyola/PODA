
package cl.diinf.testCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testObjeto {
    
    private Selenium selenium;
    
    public testObjeto() {
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
    public void testErrorSinObjeto() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/SinObjeto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta de cierre de objeto tiene el /
     * @throws Exception 
     */
    @Test
    public void testErrorObjetoCerradoSinSlash() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/ObjetoCerradoSinSlash.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta objeto tiene la etiqueta de inicio
     * @throws Exception 
     */
    @Test
    public void testErrorObjetoSinAbrir() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/ObjetoNoAbierto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
        /**
     * Test que valida si la etiqueta objeto tiene la etiqueta de cierre
     * @throws Exception 
     */
    @Test
    public void testErrorObjetoSinCerrar() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/ObjetoNoCerrado.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta objeto de apertura tiene el delimitador '<'
     * @throws Exception 
     */
    @Test
    public void testErrorObjetoSinLimitadorDer() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/ObjetoSin<.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si la etiqueta objeto de apertura tiene el delimitador '>'
     * @throws Exception 
     */
    @Test
    public void testErrorObjetoSinLimitadorIzq() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/ObjetoSin>.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si etiqueta objetos no tiene atributos
     * @throws Exception 
     */
    @Test
    public void testErrorObjetoSinAtributos() throws Exception {
        
            selenium.open("/PODA-web/");
            selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/ObjetoSinAtributos.xml");
            selenium.click("name=form:j_idt16");
            selenium.waitForPageToLoad("30000");
            selenium.click("css=span.ui-icon.ui-icon-close");
            selenium.click("css=span.ui-icon.ui-icon-close");        
    }
    /**
     * Test que valida si etiqueta objetos tiene un atributo faltante
     * @throws Exception 
     */
    @Test
    public void testErrorObjetoSinCadaAtributo() throws Exception {
        ArrayList <String> nameFiles = new ArrayList();
        nameFiles.add("ObjetoSinTitulo");
        nameFiles.add("ObjetoSinAutor");
        nameFiles.add("ObjetoSinTema");        
        for(int i = 0; i < 3 ; i++){
            selenium.open("/PODA-web/");
            selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/"+nameFiles.get(i)+".xml");
            selenium.click("name=form:j_idt16");
            selenium.waitForPageToLoad("30000");
            selenium.click("css=span.ui-icon.ui-icon-close");
            selenium.click("css=span.ui-icon.ui-icon-close");
        }
    }
    /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta objeto
     * @throws Exception 
     */
    @Test
    public void testErrorObjetoTema() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/ObjetoConTemaNoExistente.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
    }
    /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta objeto
     * @throws Exception 
     */
        @Test
    public void testErrorObjetoAtributoSinDato() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/ObjetoConAtributoSinDato.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
    /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta objeto
     * @throws Exception 
     */
    @Test
    public void testErrorObjetoConContenido() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/ObjetoConContenido.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("css=button.ajs-button.ajs-ok");
        selenium.click("link=→");
        selenium.click("link=→");
    }
        /**
     * Test que valida si se agrega un tema no existente como atributo de la etiqueta objeto
     * @throws Exception 
     */
    @Test
    public void testErrorObjetoConObjeto() throws Exception {
        selenium.open("/PODA-web/");
        selenium.type("id=form:file", "/home/nacho/proyecto-pingeso/Pruebas/objeto/ObjetoConObjeto.xml");
        selenium.click("name=form:j_idt16");
        selenium.waitForPageToLoad("30000");
        selenium.click("css=span.ui-icon.ui-icon-close");
        selenium.click("css=span.ui-icon.ui-icon-close");
    }
}
