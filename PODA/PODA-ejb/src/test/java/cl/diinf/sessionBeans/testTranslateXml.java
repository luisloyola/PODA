/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;


import cl.diinf.objetoAprendizaje.LearningObject;
import java.io.IOException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class testTranslateXml {
        private String correctResult;
        private String entrada;
        private LearningObject object;
        
    public testTranslateXml() {
        correctResult = "";
        entrada = "<comenzar>\n" +
"	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
"		<escena titulo=\"Ejemplo de textos\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">Este es un texto</texto>\n" +
"				</idea>\n" +
"				<idea ordenAparicion=\"2\">\n" +
"					<texto tipo=\"normal\">Este texto tiene audio</texto>\n" +
"				</idea>\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo=\"manuscrito\">Este es un texto manuscrito</texto>\n" +
"				</idea>\n" +
"				<idea ordenAparicion=\"4\">\n" +
"					<texto tipo=\"ejemplo\">Este es el ejemplo 1</texto>\n" +
"					<texto tipo=\"ejemplo\">Este es el ejemplo 2</texto>\n" +
"					<texto tipo=\"ejemplo\">Este es el ejemplo 3</texto>\n" +
"					<texto tipo=\"ejemplo\">Este es el ejemplo 4</texto>\n" +
"					<texto tipo=\"ejemplo\">Este es el ejemplo 5</texto>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"		<escena titulo=\"Ejemplo de multimedia\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"normal\">El siguiente es un ejemplo de una imagen</texto>\n" +
"				</idea>				\n" +
"				<idea ordenAparicion=\"3\">\n" +
"					<texto tipo=\"normal\">soy un texto</texto>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"		<escena titulo=\"Ejemplo de mano\" tipo=\"1Col\">\n" +
"			<bloque>\n" +
"				<idea ordenAparicion=\"1\">\n" +
"					<texto tipo=\"manuscrito\">Este texto será escrito a MANO.</texto>\n" +
"				</idea>\n" +
"			</bloque>\n" +
"		</escena>\n" +
"		<evaluaciones>\n" +
"			<evaluacion>\n" +
"				<enunciado>Enunciado Evaluacion </enunciado>\n" +
"				<opciones>\n" +
"					<alternativa tipo=\"solucion\" tema=\"prueba\">Si</alternativa>\n" +
"					<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
"					<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
"					<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>						\n" +
"				</opciones>					\n" +
"			</evaluacion>\n" +
"		</evaluaciones>\n" +
"	</objeto>\n" +
"</comenzar>";
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        readerXml test = new readerXml();
        test.setFileContent(entrada);
        test.AppendDTD();
        TranslateHtml trans = new TranslateHtml();
        object = test.readOA().get(0);
        String result = trans.writeHtml(object);
        correctResult = result;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of writeHtml method, of class TranslateHtml.
     * @throws java.lang.Exception
     */
    
    @Test
    public void testWriteHtml() throws Exception {
        TranslateHtml testTranslate = new TranslateHtml();
        assertEquals(testTranslate.writeHtml(object),this.correctResult);
        
    }
    
    @Test
    public void testWriteContent(){
        TranslateHtml testTranslate = new TranslateHtml();
        boolean var = false;
        if(this.correctResult.contains(testTranslate.write_contentHtml(object, object.getTitle()))){
            var = true;
        }
        assertTrue(var);        
    }
    /*@Test
    public void testWriteEvaluacion() throws IOException{
        TranslateHtml testTranslate = new TranslateHtml();
        boolean var = false;
        if(this.correctResult.contains(testTranslate.write_evaluacionHtml(object))){
            var = true;
        }
        assertTrue(var);  
    }*/
    @Test
    public void testWriteDate() throws IOException{
        TranslateHtml testTranslate = new TranslateHtml();
        boolean var = false;
        if(this.correctResult.contains(testTranslate.write_date(new Date()))){
            var = true;
        }
        assertTrue(var);
    }
    
    @Test
    public void testWriteHeader() throws IOException{
        TranslateHtml testTranslate = new TranslateHtml();
        boolean var = false;
        if(this.correctResult.contains(testTranslate.write_headerHtml(object))){
            var = true;
        }
        assertTrue(var);
        
    }
    
    @Test
    public void testWriteLibrsHtml() throws IOException{
        TranslateHtml testTranslate = new TranslateHtml();
        boolean var = false;
        if(this.correctResult.contains(testTranslate.write_librsHtml(object))){
            var = true;
        }
        assertTrue(var);
        
    }
    
    @Test
    public void testWriteTitleHtml() throws IOException{
        TranslateHtml testTranslate = new TranslateHtml();
        boolean var = false;
        if(this.correctResult.contains(testTranslate.write_titleHtml(object))){
            var = true;
        }
        assertTrue(var);
        
    }
        
        
    
    
}
