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
"<!DOCTYPE comenzar [\n" +
"<!ELEMENT comenzar (objeto)>\n" +
"<!ELEMENT objeto (escena+)>\n" +
"<!ELEMENT escena (bloque+)>\n" +
"<!ELEMENT bloque (idea*)>\n" +
"<!ELEMENT idea (texto*, voz?)>\n" +
"<!ELEMENT texto (#PCDATA)>\n" +
"<!ELEMENT voz (#PCDATA)>\n" +
"\n" +
"<!ATTLIST objeto titulo CDATA #REQUIRED>\n" +
"<!ATTLIST objeto autor CDATA #REQUIRED>\n" +
"<!ATTLIST objeto tema CDATA #REQUIRED>\n" +
"<!ATTLIST escena titulo CDATA #REQUIRED>\n" +
"<!ATTLIST escena tipo CDATA #REQUIRED>\n" +
"\n" +
"<!ATTLIST idea ordenAparicion CDATA #REQUIRED>\n" +
"<!ATTLIST texto tipo CDATA #REQUIRED>	\n" +
"]>\n" +
"\n" +
"<comenzar>\n" +
"	<objeto titulo=\"Presentación de PODA en la Sprint Review 2\" tema=\"usach\" autor=\"Grupo 1 PINGESO\">\n" +
"		<escena titulo=\"Diseño 1 columna\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">Texto de un primer parrafo de la primera escena</texto>\n" +
"					<texto tipo=\"codigo\">\n" +
"						//Soy texto de un codigo\n" +
"						if(cont==0):\n" +
"							var = cont + 1\n" +
"						else:\n" +
"							var = cont + 2\n" +
"					</texto>\n" +
"					<texto tipo=\"manuscrito\">Soy texto manuscrito</texto>\n" +
"					<voz>Idea 1</voz>\n" +
"				</idea>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo=\"normal\">Soy un texto de una segunda idea con dos pilas de ejemplos</texto>					\n" +
"					<texto tipo=\"normal\">Primer conjunto de ejemplos</texto>					\n" +
"					<texto tipo=\"ejemplo\">Soy el ejemplo 1.1</texto>\n" +
"					<texto tipo=\"ejemplo\">Soy el ejemplo 1.2</texto>\n" +
"					<texto tipo=\"ejemplo\">Soy el ejemplo 1.3</texto>\n" +
"					<texto tipo=\"ejemplo\">Soy el ejemplo 1.4</texto>\n" +
"					<texto tipo=\"normal\">Segundo conjunto de ejemplos</texto>\n" +
"					<texto tipo=\"ejemplo\">Soy el ejemplo 2.1</texto>\n" +
"					<texto tipo=\"ejemplo\">Soy el ejemplo 2.2</texto>\n" +
"					<texto tipo=\"ejemplo\">Soy el ejemplo 2.3</texto>\n" +
"					<texto tipo=\"ejemplo\">Soy el ejemplo 2.4</texto>\n" +
"					<voz>Idea 2 con ejemplos</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"\n" +
"		<escena titulo=\"Diseño 1 fila superior 2 columnas\" tipo=\"1Fil2Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la fila superior de la escena 2 que sale primero</texto>\n" +
"					<texto tipo = \"normal\">/dSoy un texto destacadod/</texto>					\n" +
"					<voz>Idea 1</voz>\n" +
"				</idea>\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo = \"manuscrito\">Soy un texto /dmanuscritod/ de la fila /esuperiore/ que aparece tercero</texto>\n" +
"					<voz>Idea 3</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"4\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la columna inferior izquierda que aparece cuarto</texto>\n" +
"					<texto tipo = \"manuscrito\">/eSoy un texto manuscrito enfatizadoe/</texto>\n" +
"					<voz>Idea 4</voz>\n" +
"				</idea>				\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la columna inferior derecha que aparece segundo</texto>\n" +
"					<voz>Idea 2</voz>\n" +
"				</idea>\n" +
"				<idea ordenAparicion=\"5\">\n" +
"					<texto tipo = \"manuscrito\">Soy un texto manuscrito de la columna inferior izquierda que aparece quinto</texto>\n" +
"					<voz>Idea 5</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"\n" +
"		<escena titulo=\"Diseño 2 columnas\" tipo=\"2Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la fila superior de la escena 2 que sale primero</texto>\n" +
"					<texto tipo = \"normal\">/dSoy un texto destacadod/</texto>					\n" +
"					<voz>Idea 1</voz>\n" +
"				</idea>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo = \"manuscrito\">Soy un texto manuscrito de la fila superior que aparece tercero</texto>\n" +
"					<voz>Idea 2</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la columna inferior izquierda que aparece cuarto</texto>\n" +
"					<texto tipo = \"manuscrito\">/eSoy un texto manuscrito enfatizadoe/</texto>\n" +
"					<voz>Idea 3</voz>\n" +
"				</idea>				\n" +
"			</bloque>			\n" +
"		</escena>\n" +
"\n" +
"		<escena titulo=\"Diseño 3 columnas\" tipo=\"3Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la columna izquierda</texto>\n" +
"					<texto tipo = \"normal\">/dSoy un texto destacadod/</texto>					\n" +
"					<voz>Idea 1</voz>\n" +
"				</idea>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo = \"manuscrito\">Soy un texto manuscrito de la columna izquierda</texto>\n" +
"					<voz>Idea 2</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la columna de al medio</texto>\n" +
"					<texto tipo = \"manuscrito\">/eSoy un texto manuscrito enfatizadoe/</texto>\n" +
"					<voz>Idea 3</voz>\n" +
"				</idea>				\n" +
"			</bloque>			\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"4\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la columna derecha</texto>\n" +
"					<texto tipo = \"manuscrito\">/eSoy un texto manuscrito enfatizadoe/</texto>\n" +
"					<voz>Idea 4</voz>\n" +
"				</idea>				\n" +
"			</bloque>			\n" +
"		</escena>\n" +
"\n" +
"		<escena titulo=\"Diseño 2 filas 2 columnas\" tipo=\"2Fil2Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la fila superior</texto>\n" +
"					<texto tipo = \"normal\">/dSoy un texto destacadod/</texto>					\n" +
"					<voz>Idea 1</voz>\n" +
"				</idea>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo = \"manuscrito\">Soy un texto manuscrito de la fila superior</texto>\n" +
"					<voz>Idea 2</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la columna izquierda</texto>\n" +
"					<texto tipo = \"manuscrito\">/eSoy un texto manuscrito enfatizadoe/</texto>\n" +
"					<voz>Idea 3</voz>\n" +
"				</idea>				\n" +
"			</bloque>			\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"5\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la columna derecha</texto>\n" +
"					<texto tipo = \"manuscrito\">/eSoy un texto manuscrito enfatizadoe/</texto>\n" +
"					<voz>Idea 5</voz>\n" +
"				</idea>				\n" +
"			</bloque>			\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"4\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la fila inferior</texto>\n" +
"					<texto tipo = \"manuscrito\">/eSoy un texto manuscrito enfatizadoe/</texto>\n" +
"					<voz>Idea 4</voz>\n" +
"				</idea>				\n" +
"			</bloque>			\n" +
"		</escena>\n" +
"\n" +
"		<escena titulo=\"Diseño 2 columnas 1 fila\" tipo=\"2Col1Fil\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la columna superior izquierda</texto>\n" +
"					<texto tipo = \"normal\">/dSoy un texto destacadod/</texto>					\n" +
"					<voz>Idea 1</voz>\n" +
"				</idea>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo = \"manuscrito\">Soy un texto manuscrito de la columna superior izquierda</texto>\n" +
"					<voz>Idea 2</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la columna superior derecha</texto>\n" +
"					<texto tipo = \"manuscrito\">/eSoy un texto manuscrito enfatizadoe/</texto>\n" +
"					<voz>Idea 3</voz>\n" +
"				</idea>				\n" +
"			</bloque>			\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"4\">\n" +
"					<texto tipo = \"normal\">Soy un texto de la fila inferior</texto>\n" +
"					<texto tipo = \"manuscrito\">/eSoy un texto manuscrito enfatizadoe/</texto>\n" +
"					<voz>Idea 4</voz>\n" +
"				</idea>				\n" +
"			</bloque>						\n" +
"		</escena>\n" +
"	</objeto>\n" +
"</comenzar>");
        int result = instance.readOA().size();
        
        assertEquals(1, result);
    }

    @Test
    
    public void testPreProcessText(){
        OA_Reader instance = new OA_Reader();
        instance.setFileContent("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<!DOCTYPE comenzar SYSTEM \"validator.dtd\"> \n" +
"\n" +
"<comenzar>\n" +
"	<objeto titulo=\"Prueba v3\" autor=\"Probador\" tema=\"default\">\n" +
"		<escena titulo=\"Escena 1 Columna\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">texto <destacado>destacado</destacado></texto>\n" +
"					<texto tipo=\"codigo\">texto <enfatizado>enfatizado</enfatizado></texto>\n" +
"					<voz>Voz 1Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"	</objeto>\n" +
"</comenzar>");
        
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<!DOCTYPE comenzar SYSTEM \"validator.dtd\"> \n" +
"\n" +
"<comenzar>\n" +
"	<objeto titulo=\"Prueba v3\" autor=\"Probador\" tema=\"default\">\n" +
"		<escena titulo=\"Escena 1 Columna\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">texto /ddestacado/d</texto>\n" +
"					<texto tipo=\"codigo\">texto /eenfatizado/e</texto>\n" +
"					<voz>Voz 1Col</voz>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"	</objeto>\n" +
"</comenzar>";
        
        instance.preProcessText();
        assertEquals(instance.getFileContent(), expected);
    }
}
