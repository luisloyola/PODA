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
        instance.setFileContent("Contenido");
        boolean prueba = false;
        if(instance.getFileContent().length() > 0){
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
        instance.setFileContent(contenidoFile);
        boolean prueba = false;
        if(instance.getFileContent()!=null){
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
        instance.setFileContent("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<!DOCTYPE comenzar SYSTEM \"validator.dtd\"> \n" +
"\n" +
"<comenzar>\n" +
"	<objeto titulo=\"Prueba v3\" autor=\"Probador\" tema=\"default\">\n" +
"		<escena titulo=\"Escena 1 Columna\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">texto 1Col: 1</texto>\n" +
"					<texto tipo=\"codigo\">texto 1Col: 2 </texto>\n" +
"					<voz>Voz 1Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"		<escena titulo=\"Escena 1 Fila 2 Columnas\" tipo=\"1Fil2Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"ejemplo\">texto 1Row2Col: 1</texto>\n" +
"					<texto tipo=\"manuscrito\">texto 1Row2Col: 2</texto>\n" +
"					<voz>Voz 1Row2Col</voz>\n" +
"				</idea>\n" +
"			</bloque>	\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo=\"normal\">texto 1Row2Col: 3</texto>\n" +
"					<texto tipo=\"manuscrito\">texto 1Row2Col: 4</texto>\n" +
"					<voz>Voz 1Row2Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo=\"ejemplo\">texto 1Row2Col: 5</texto>\n" +
"					<texto tipo=\"ejemplo\">texto 1Row2Col: 6</texto>\n" +
"					<voz>Voz 1Row2Col</voz>\n" +
"				</idea>\n" +
"			</bloque>		\n" +
"		</escena>\n" +
"		<escena titulo=\"Escena 2 Columnas\" tipo=\"2Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">texto 1Row2Col: 1</texto>\n" +
"					<texto tipo=\"codigo\">texto 1Row2Col: 2</texto>\n" +
"					<voz>Voz 1Row2Col</voz>\n" +
"				</idea>\n" +
"			</bloque>	\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo=\"manuscrito\">texto 1Row2Col: 3</texto>\n" +
"					<texto tipo=\"manuscrito\">texto 1Row2Col: 4</texto>\n" +
"					<voz>Voz 1Row2Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"		<escena titulo=\"Escena 3 Columnas\" tipo=\"3Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"codigo\">texto 3Col: 1</texto>\n" +
"					<texto tipo=\"codigo\">texto 3Col: 2</texto>\n" +
"					<voz>Voz 3Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo=\"manuscrito\">texto 3Col: 3</texto>\n" +
"					<texto tipo=\"codigo\">texto 3Col: 4</texto>\n" +
"					<voz>Voz 3Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo=\"normal\">texto 3Col: 5</texto>\n" +
"					<texto tipo=\"normal\">texto 3Col: 6</texto>\n" +
"					<voz>Voz 3Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"		<escena titulo=\"Escena 1 Fila 3 Columnas\" tipo=\"1Fil3Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">texto 1Row3Col: 1</texto>\n" +
"					<texto tipo=\"codigo\">texto 1Row3Col: 2</texto>\n" +
"					<voz>Voz 1Row3Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo=\"normal\">texto 1Row3Col: 3</texto>\n" +
"					<texto tipo=\"normal\">texto 1Row3Col: 4</texto>\n" +
"					<voz>Voz 1Row3Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo=\"normal\">texto 1Row3Col: 5</texto>\n" +
"					<texto tipo=\"codigo\">texto 1Row3Col: 6</texto>\n" +
"					<voz>Voz 1Row3Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"4\">\n" +
"					<texto tipo=\"normal\">texto 1Row3Col: 7</texto>\n" +
"					<texto tipo=\"normal\">texto 1Row3Col: 8</texto>\n" +
"					<voz>Voz 1Row3Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"		<escena titulo=\"Escena 2 Filas 2 Columnas\" tipo=\"2Fil2Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">texto 2Row2Col: 1</texto>\n" +
"					<texto tipo=\"normal\">texto 2Row2Col: 2</texto>\n" +
"					<voz>Voz 2Row2Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo=\"normal\">texto 2Row2Col: 3</texto>\n" +
"					<texto tipo=\"normal\">texto 2Row2Col: 4</texto>\n" +
"					<voz>Voz 2Row2Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo=\"normal\">texto 2Row2Col: 5</texto>\n" +
"					<texto tipo=\"manuscrito\">texto 2Row2Col: 6</texto>\n" +
"					<voz>Voz 2Row2Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"4\">\n" +
"					<texto tipo=\"normal\">texto 2Row2Col: 7</texto>\n" +
"					<texto tipo=\"normal\">texto 2Row2Col: 8</texto>\n" +
"					<voz>Voz 2Row2Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"	</objeto>\n" +
"</comenzar>");
        int result = instance.readOA().size();
        assertEquals(1, result);
    }

    
}
