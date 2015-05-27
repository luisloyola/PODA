/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.testSelenium;

/*import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
*/
/**
 *
 * @author nacho
 */
public class TestCreationObject {
    /*
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    
    
    public TestCreationObject() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    
    @Test
    public void testPoda() throws Exception {
        driver.get(baseUrl + "/PODA-web/");
        driver.findElement(By.id("form:file")).clear();
        driver.findElement(By.id("form:file")).sendKeys("/home/nacho/Escritorio/EJemplo_review sn manus.xml");
        driver.findElement(By.id("form:file")).clear();
        driver.findElement(By.id("form:file")).sendKeys("/home/nacho/Escritorio/EJemplo_review sn manus.xml");
        driver.findElement(By.name("form:j_idt16")).click();
        driver.findElement(By.name("form:j_idt16")).click();
        driver.findElement(By.cssSelector("input.btn.btn-success")).click();
        driver.findElement(By.cssSelector("input.btn.btn-success")).click();
        driver.findElement(By.cssSelector("button.ajs-button.ajs-ok")).click();
        driver.findElement(By.cssSelector("button.ajs-button.ajs-ok")).click();
        driver.findElement(By.linkText("→")).click();
        driver.findElement(By.linkText("→")).click();
        driver.findElement(By.linkText("→")).click();
        driver.findElement(By.linkText("→")).click();
        driver.findElement(By.linkText("←")).click();
        driver.findElement(By.linkText("←")).click();
        driver.findElement(By.linkText("←")).click();
        driver.findElement(By.linkText("←")).click();
    }
    
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
              alert.accept();
            } else {
              alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }    
    */
}