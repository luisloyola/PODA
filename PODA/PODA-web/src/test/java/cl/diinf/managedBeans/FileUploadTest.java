/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.managedBeans;

import java.io.File;
import javax.servlet.http.Part;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author teban
 */
public class FileUploadTest {
    
    public FileUploadTest() {
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
     * Test of prepareDownload method, of class FileUpload.
     */
    @Test
    public void testPrepareDownload() {
        
        FileUpload instance = new FileUpload();
        instance.setCode_html("<html></html>");
        boolean var = false;
        File result = instance.prepareDownload();
        if(result.getAbsolutePath().length() > 0){
            var = true;
        }
        assertEquals(true, var);
    }

    
}
