/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;


import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class OA_TranslateHtmlTest {
    
    public OA_TranslateHtmlTest() {
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
     * Test of writeHtml method, of class OA_TranslateHtml.
     */
    @Ignore
    @Test
    public void testWriteHtml() throws Exception {
        /*
          Debido a el cómo trabajan todas estas funciones en conjunto,
          el resultado correcto sólo será obtenido si todas funcionan
          como deben.
        */
        
        OA_Reader test = new OA_Reader();
        test.setFileContent("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
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
        OA_TranslateHtml trans = new OA_TranslateHtml();
        String result = trans.writeHtml(test.readOA().get(0));
        boolean var = false;
        String[] lines = result.split("\r\n|\r|\n");
        if(lines.length == 355){
            var = true;
        }
        assertEquals(true, var);
        //assertEquals(1,1);
    }
}
