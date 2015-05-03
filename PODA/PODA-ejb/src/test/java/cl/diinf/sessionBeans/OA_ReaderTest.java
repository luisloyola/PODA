/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

import cl.diinf.objetoAprendizaje.ObjetoAprendizaje;
import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teban
 */
public class OA_ReaderTest {
    
    public OA_ReaderTest() {
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
     * Test of getContenidoFile method, of class OA_Reader.
     */
    @Test
    public void testGetContenidoFile() {
        //System.out.println("getContenidoFile");
        OA_Reader instance = new OA_Reader();
        instance.setContenidoFile("Contenido");
        boolean prueba = false;
        if(instance.getContenidoFile().length() > 0){
            prueba = true;
        }
        assertEquals(true,prueba);
    }

    /**
     * Test of setContenidoFile method, of class OA_Reader.
     */
    @Test
    public void testSetContenidoFile() {
        String contenidoFile = "Contenido";
        OA_Reader instance = new OA_Reader();
        instance.setContenidoFile(contenidoFile);
        boolean prueba = false;
        if(instance.getContenidoFile()!=null){
            prueba = true;
        }
        assertEquals(true,prueba);
    }

    /**
     * Test of readOA method, of class OA_Reader.
     */
    @Test
    public void testReadOA() {
        OA_Reader instance = new OA_Reader();
        instance.setContenidoFile("<?xml version=\"1.0\"?>\n" +
"\n" +
"<begin>\n" +
"	<object title=\"QWERTY\" author=\"ASDFG\">\n" +
"		<scene sceneTitle=\"1\" time=\"default\">\n" +
"			<text type=\"text\" font=\"default\">Text1.1</text>	\n" +
"			<voice>Voice1.1</voice>	\n" +
"		</scene>\n" +
"		<scene sceneTitle=\"2\" time=\"default\">\n" +
"			<text type=\"example\" font=\"default\">Text1.2</text>\n" +
"			<text type=\"example\" font=\"default\">Text1.3</text>\n" +
"			<text type=\"example\" font=\"default\">Text1.4</text>	\n" +
"			<voice>Voice1.2</voice>	\n" +
"		</scene>\n" +
"	</object>\n" +
"	<object Title=\"QWERTY2\" author=\"ASDFG2\">\n" +
"		<scene sceneTitle=\"1\" time=\"default\">\n" +
"			<text type=\"example\" font=\"default\">text2.1</text>\n" +
"			<text type=\"example\" font=\"default\">text2.2</text>	\n" +
"			<voice>Voice2.1</voice>	\n" +
"		</scene>\n" +
"	</object>\n" +
"</begin>");
        int result = instance.readOA().size();
        assertEquals(2, result);
    }

    
}
