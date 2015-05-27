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
        instance.setFileContent("<comenzar>\n" +
"	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
"		<escena titulo=\"Ejemplo de textos\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">Este es un texto</texto>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"			\n" +
"	</objeto>\n" +
"</comenzar>");
        instance.AppendDTD();
        int result = instance.readOA().size();
        
        assertEquals(1, result);
    }
    
    @Test
    public void testReadOADTDAppend() {
        OA_Reader instance = new OA_Reader();
        String content = "<comenzar>\n" +
"	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
"		<escena titulo=\"Ejemplo de textos\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"manuscrito\" mano=\"randomText\">Este es un texto</texto>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"			\n" +
"	</objeto>\n" +
"</comenzar>";
        String dtd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<!DOCTYPE comenzar [\n" +
"<!ELEMENT comenzar (objeto)>\n" +
"<!ELEMENT objeto (escena+)>\n" +
"<!ELEMENT escena (bloque+)>\n" +
"<!ELEMENT bloque (idea*)>\n" +
"<!ELEMENT idea (texto*, voz?, media*, prueba*)>\n" +
"<!ELEMENT texto (#PCDATA)>\n" +
"<!ELEMENT voz (#PCDATA)>\n" +
"<!ELEMENT media (#PCDATA)>\n" +
"<!ELEMENT prueba (evaluacion*)>\n" +
"<!ELEMENT evaluacion (enunciado,opciones)>\n" +
"<!ELEMENT enunciado (#PCDATA)>\n" +
"<!ELEMENT opciones (alternativa*)>\n" +
"<!ELEMENT alternativa (#PCDATA)>\n" +
"\n" +
"\n" +
"<!ATTLIST objeto titulo CDATA #REQUIRED>\n" +
"<!ATTLIST objeto autor CDATA #REQUIRED>\n" +
"<!ATTLIST objeto tema CDATA #REQUIRED>\n" +
"<!ATTLIST escena titulo CDATA #REQUIRED>\n" +
"<!ATTLIST escena tipo CDATA #REQUIRED>\n" +
"\n" +
"<!ATTLIST idea ordenAparicion CDATA #REQUIRED>\n" +
"<!ATTLIST texto tipo CDATA #REQUIRED>\n" +
"<!ATTLIST texto mano CDATA #IMPLIED>\n" +
"<!ATTLIST media tipo CDATA #REQUIRED>	\n" +
"\n" +
"<!ATTLIST alternativa tipo CDATA #REQUIRED>\n" +
"<!ATTLIST alternativa tema CDATA #REQUIRED>\n" +
"]>";
                
        instance.setFileContent(content);
        instance.AppendDTD();
        
        
        assertEquals(dtd+content,instance.getFileContent());
    }
    
    
    @Test
    public void testReadOAForHandTrue() {
        OA_Reader instance = new OA_Reader();
        instance.setFileContent("<comenzar>\n" +
"	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
"		<escena titulo=\"Ejemplo de textos\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"manuscrito\" mano=\"mostrar\">Este es un texto</texto>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"			\n" +
"	</objeto>\n" +
"</comenzar>");
        instance.AppendDTD();
        int result = instance.readOA().size();
        
        assertEquals(1, result);
    }
    
    @Test
    public void testReadOAForHandFalse() {
        OA_Reader instance = new OA_Reader();
        instance.setFileContent("<comenzar>\n" +
"	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
"		<escena titulo=\"Ejemplo de textos\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"manuscrito\" mano=\"randomText\">Este es un texto</texto>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"			\n" +
"	</objeto>\n" +
"</comenzar>");
        instance.AppendDTD();
        int result = instance.readOA().size();
        
        assertEquals(0, result);
    }
    
    @Test
    public void testReadOAForMedia() {
        OA_Reader instance = new OA_Reader();
        instance.setFileContent("<comenzar>\n" +
"	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
"		<escena titulo=\"Ejemplo de multimedia\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">El siguiente es un ejemplo de una imagen</texto>\n" +
"					<media tipo=\"imagen\">http://cdn.alltheragefaces.com/img/faces/jpg/fuck-yeah-fuck-yeah-clean.jpg</media>\n" +
"				</idea>\n" +
"				<!--<idea ordenAparicion=\"2\">\n" +
"					<texto tipo=\"normal\">El siguiente es un ejemplo de un video</texto>\n" +
"					<media tipo=\"video\">https://www.youtube.com/watch?v=sTSA_sWGM44</media>\n" +
"				</idea>-->\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo=\"normal\">El siguiente es un ejemplo de audio</texto>\n" +
"					<media tipo=\"audio\">https://www.youtube.com/watch?v=_4IRMYuE1hI</media>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"	</objeto>\n" +
"</comenzar>");
        instance.AppendDTD();
        int result = instance.readOA().size();
        
        assertEquals(1, result);
    }
    
    @Test
    public void testReadOAForMediaForNoMediaType() {
        OA_Reader instance = new OA_Reader();
        instance.setFileContent("<comenzar>\n" +
"	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
"		<escena titulo=\"Ejemplo de multimedia\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">El siguiente es un ejemplo de una imagen</texto>\n" +
"					<media >http://cdn.alltheragefaces.com/img/faces/jpg/fuck-yeah-fuck-yeah-clean.jpg</media>\n" +
"				</idea>\n" +
"				<!--<idea ordenAparicion=\"2\">\n" +
"					<texto tipo=\"normal\">El siguiente es un ejemplo de un video</texto>\n" +
"					<media tipo=\"video\">https://www.youtube.com/watch?v=sTSA_sWGM44</media>\n" +
"				</idea>-->\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo=\"normal\">El siguiente es un ejemplo de audio</texto>\n" +
"					<media tipo=\"audio\">https://www.youtube.com/watch?v=_4IRMYuE1hI</media>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"	</objeto>\n" +
"</comenzar>");
        instance.AppendDTD();
        int result = instance.readOA().size();
        
        assertEquals(0, result);
    }
    
    @Test
    public void testReadOAForMediaForRandomMediaType() {
        OA_Reader instance = new OA_Reader();
        instance.setFileContent("<comenzar>\n" +
"	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
"		<escena titulo=\"Ejemplo de multimedia\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">El siguiente es un ejemplo de una imagen</texto>\n" +
"					<media tipo=\"randomMediaType\">http://cdn.alltheragefaces.com/img/faces/jpg/fuck-yeah-fuck-yeah-clean.jpg</media>\n" +
"				</idea>\n" +
"				<!--<idea ordenAparicion=\"2\">\n" +
"					<texto tipo=\"normal\">El siguiente es un ejemplo de un video</texto>\n" +
"					<media tipo=\"video\">https://www.youtube.com/watch?v=sTSA_sWGM44</media>\n" +
"				</idea>-->\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo=\"normal\">El siguiente es un ejemplo de audio</texto>\n" +
"					<media tipo=\"audio\">https://www.youtube.com/watch?v=_4IRMYuE1hI</media>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"	</objeto>\n" +
"</comenzar>");
        instance.AppendDTD();
        int result = instance.readOA().size();
        
        assertEquals(0, result);
    }
    
    @Test
    public void testReadOAForQuiz() {
        OA_Reader instance = new OA_Reader();
        instance.setFileContent("<comenzar>\n" +
"	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
"		<escena titulo=\"Ejemplo de evaluacion\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<prueba>\n" +
"						<evaluacion>\n" +
"							<enunciado>Enunciado Evaluacion </enunciado>\n" +
"							<opciones>\n" +
"								<alternativa tipo=\"solucion\" tema=\"prueba\">Si</alternativa>\n" +
"								<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
"							</opciones>				\n" +
"						</evaluacion>\n" +
"					</prueba>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"	</objeto>\n" +
"</comenzar>");
        instance.AppendDTD();
        int result = instance.readOA().size();
        
        assertEquals(1, result);
    }
    
    @Test
    public void testReadOAForQuizForRandomType() {
        OA_Reader instance = new OA_Reader();
        instance.setFileContent("<comenzar>\n" +
"	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
"		<escena titulo=\"Ejemplo de evaluacion\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<prueba>\n" +
"						<evaluacion>\n" +
"							<enunciado>Enunciado Evaluacion </enunciado>\n" +
"							<opciones>\n" +
"								<alternativa tipo=\"randomType\" tema=\"prueba\">Si</alternativa>\n" +
"								<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
"							</opciones>				\n" +
"						</evaluacion>\n" +
"					</prueba>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"	</objeto>\n" +
"</comenzar>");
        instance.AppendDTD();
        int result = instance.readOA().size();
        
        assertEquals(0, result);
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
