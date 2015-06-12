/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.objetoAprendizaje;

import cl.diinf.objetoAprendizaje.FeedBack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luis
 */
public class FeedBackTest {
    
    public FeedBackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLink method, of class FeedBack.
     */
    @Test
    public void testGetLink() {
        System.out.println("getLink");
        FeedBack instance = new FeedBack();
        instance.setLink("url_test");
        String expResult = "url_test";
        String result = instance.getLink();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLink method, of class FeedBack.
     */
    @Test
    public void testSetLink() {
        System.out.println("setLink");
        FeedBack instance = new FeedBack();
        String link = "url_test";
        instance.setLink(link);
        assertEquals(link, instance.getLink());
    }

    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "";
        boolean expResult = false;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid2() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "asdf";
        boolean expResult = false;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid3() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "https://docs.google.com/forms";
        boolean expResult = false;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid4() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "https://docs.google.com/forms/d/";
        boolean expResult = false;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid5() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "https://docs.google.com/forms/d/asdfasdfasdf";
        boolean expResult = false;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid6() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "https://docs.google.com/forms/d/asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfa";
        boolean expResult = false;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid7() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "https://docs.google.com/forms/d/asdfasdfasdf/viewform";
        boolean expResult = true;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid8() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "https://docs.google.com/forms/d/asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfa/viewform";
        boolean expResult = true;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid9() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "https://docs.google.com/forms/d/1cC167MA3BRnIPbfCQHxUnCqb7_ChrzNEI3NbAzNXAqY/viewform";
        boolean expResult = true;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid10() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "https://docs.google.com/forms/d/1cC167MA3BRnIPbfCQHxUnCqb7_ChrzNEI3NbAzNXAqY/viewform?usp=send_form";
        boolean expResult = true;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid11() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "https://docs.google.com/forms/d/1cC167MA3BRnIPbfCQHxUnCqb7_ChrzNEI3NbAzNXAqY/viewform?usp=send_form/edit";
        boolean expResult = true;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isLinkValid method, of class FeedBack.
     */
    @Test
    public void testIsLinkValid12() {
        System.out.println("isLinkValid");
        FeedBack instance = new FeedBack();
        String link = "https://docs.google.com/a/usach.cl/forms/d/1uttl2s55PN1s1tny07AaYjKlmg6l75eyshlnIglCzcg/viewform?usp=send_form";
        boolean expResult = true;
        instance.setLink(link);
        boolean result = instance.isLinkValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of repairLink method, of class FeedBack.
     */
    @Test
    public void testRepairLink() {
        System.out.println("repairLink");
        FeedBack instance = new FeedBack();
        String link = "url_test";
        String expResult = "url_test";
        instance.setLink(link);
        instance.repairLink();
        assertEquals(instance.getLink(), expResult);
    }
    
    /**
     * Test of repairLink method, of class FeedBack.
     */
    @Test
    public void testRepairLink2() {
        System.out.println("repairLink");
        FeedBack instance = new FeedBack();
        String link = "url_test/edit";
        String expResult = "url_test";
        instance.setLink(link);
        instance.repairLink();
        assertEquals(instance.getLink(), expResult);
    }
    
    /**
     * Test of repairLink method, of class FeedBack.
     */
    @Test
    public void testRepairLink3() {
        System.out.println("repairLink");
        FeedBack instance = new FeedBack();
        String link = "url_test?usp=send_form";
        String expResult = "url_test";
        instance.setLink(link);
        instance.repairLink();
        assertEquals(instance.getLink(), expResult);
    }
    
    /**
     * Test of repairLink method, of class FeedBack.
     */
    @Test
    public void testRepairLink4() {
        System.out.println("repairLink");
        FeedBack instance = new FeedBack();
        String link = "url_test/edit?usp=send_form";
        String expResult = "url_test";
        instance.setLink(link);
        instance.repairLink();
        assertEquals(instance.getLink(), expResult);
    }
}
