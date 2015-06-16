
package cl.diinf.managedBeans;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ObjectManagedTest {
    
    public ObjectManagedTest() {
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
    
    @Test
    public void resetMessageTest(){
        ObjectManaged nuevo = new ObjectManaged();
        nuevo.setError_Message("prueba");
        if(nuevo.getError_Message().equals("prueba")){
            nuevo.reset_message();
            assertEquals(nuevo.getError_Message(),null);
        }
        else{
            fail("No se pudo setear el mensaje.");
        }
        
    }
    
    
    
    /**
     * Test of prepareDownload method, of class ObjectManaged.
     */
    @Test
    public void testPrepareDownload() throws IOException {
        
        ObjectManaged instance = new ObjectManaged();
        instance.setCode_html("<html></html>");
        boolean var = false;
        File result = instance.prepareDownload();
        if(result.getAbsolutePath().length() > 0){
            var = true;
        }
        assertEquals(true, var);
    }
    
    @Test
    public void testPrepareScormDownload() throws IOException {
        
        ObjectManaged instance = new ObjectManaged();
        instance.setCode_html("<html></html>");
        boolean var = false;
        File result = instance.prepareScormDownload();
        if(result.getAbsolutePath().length() > 0){
            var = true;
        }
        assertEquals(true, var);
    }

    
}

