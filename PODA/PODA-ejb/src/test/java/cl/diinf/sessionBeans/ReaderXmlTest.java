/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

import cl.diinf.objetoAprendizaje.LearningObject;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author nacho
 */
public class ReaderXmlTest {
    
    List<LearningObject> objects;
    
    public ReaderXmlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
                                
        ReaderXml good_instance = new ReaderXml();
        
        String good_xml =   "<objeto titulo=\"Nombre del Objeto\" tema=\"usach\" autor=\"Autor\">\n" +
                            "	<escena titulo=\"Titulo de la escena\" tipo=\"1Col\">\n" +
                            "		<bloque>\n" +
                            "			<idea orden=\"1\"> \n" +
                            "				<texto tipo=\"normal\">Soy texto normal</texto> \n" +
                            "				<texto tipo=\"codigo\">\n" +
                            "					var = 0\n" +
                            "					while(var++ &lt;= 10)\n" +
                            "						aux = var + 1\n" +
                            "\n" +
                            "				</texto> \n" +
                            "				<texto tipo=\"manuscrito\">Soy texto manuscrito</texto>\n" +
                            "				<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media>\n" +
                            "				<ejemplos>					\n" +
                            "					<ejemplo>\n" +
                            "						<texto_ejemplo tipo=\"normal\">ABCD</texto_ejemplo>\n" +
                            "						<media_ejemplo tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media_ejemplo>	\n" +
                            "					</ejemplo>				\n" +
                            "				</ejemplos>\n" +
                            "				<voz>voz correspondiente a esta idea</voz>				\n" +
                            "			</idea>\n" +
                            "			<idea orden=\"3\">\n" +
                            "				<texto tipo=\"normal\">Soy texto normal 2</texto>\n" +
                            "			</idea>\n" +
                            "		</bloque>		\n" +
                            "	</escena>\n" +
                            "	<escena titulo=\"Titulo de la escena 2\" tipo=\"1Fil2Col\">\n" +
                            "		<bloque>\n" +
                            "			<idea orden=\"1\"> 				\n" +
                            "				<voz>voz correspondiente a esta idea</voz>				\n" +
                            "			</idea>\n" +
                            "			<idea orden=\"2\"> 				\n" +
                            "				<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media>\n" +
                            "			</idea>\n" +
                            "			<idea orden=\"3\">\n" +
                            "				<texto tipo=\"manuscrito\">Soy texto normal 2</texto>\n" +
                            "			</idea>\n" +
                            "		</bloque>\n" +
                            "		<bloque>\n" +
                            "			<idea orden=\"2\">\n" +
                            "				<ejemplos>\n" +
                            "					<!-- dentro de ejemplos estarán las pilas de ejemplos a mostrar, pueden ser varias -->\n" +
                            "					<ejemplo>\n" +
                            "						<texto_ejemplo tipo=\"normal\">ABCD</texto_ejemplo>						\n" +
                            "					</ejemplo>\n" +
                            "					<ejemplo>\n" +
                            "						<media_ejemplo tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media_ejemplo>	\n" +
                            "					</ejemplo>				\n" +
                            "					<ejemplo>\n" +
                            "						<texto_ejemplo tipo=\"normal\">ABCD</texto_ejemplo>\n" +
                            "						<media_ejemplo tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media_ejemplo>	\n" +
                            "					</ejemplo>								\n" +
                            "					<ejemplo>\n" +
                            "						<texto_ejemplo tipo=\"codigo\">ABCD</texto_ejemplo>\n" +
                            "						<media_ejemplo tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media_ejemplo>	\n" +
                            "					</ejemplo>						\n" +
                            "				</ejemplos>\n" +
                            "			</idea>\n" +
                            "		</bloque>	\n" +
                            "		<bloque>\n" +
                            "			<idea orden=\"5\">\n" +
                            "				<voz>HOla como estas</voz>\n" +
                            "			</idea>\n" +
                            "		</bloque>\n" +
                            "\n" +
                            "	</escena>\n" +
                            "\n" +
                            "<evaluacion exigencia_min=\"30\" exigencia_max=\"60\">\n" +
                            "		<pregunta>\n" +
                            "			<forma>\n" +
                            "				<enunciado>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +                            
                            "				</enunciado>\n" +
                            "				<opciones>\n" +
                            "					<alternativa tipo=\"solucion\" tema=\"nuevoTema\"> <!-- o distractor --> \n" +
                            "						<texto tipo=\"normal\">ABC</texto> <!-- 0 o 1 -->\n" +                            
                            "					</alternativa>\n" +
                            "					<alternativa tipo=\"distractor\" tema=\"nuevoTema\"> <!-- o distractor --> \n" +
                            "						<texto tipo=\"normal\">ABCD</texto> <!-- 0 o 1 -->\n" +                            
                            "					</alternativa>\n" +
                            "				</opciones>\n" +
                            "				<solucion>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +                            
                            "				</solucion>\n" +
                            "			</forma>\n" +
                            "		</pregunta>\n" +
                            "		<pregunta>\n" +
                            "			<forma>\n" +
                            "				<enunciado>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +                            
                            "				</enunciado>\n" +
                            "				<opciones>\n" +
                            "					<alternativa tipo=\"solucion\" tema=\"nuevoTema\"> <!-- o distractor --> \n" +
                            "						<texto tipo=\"normal\">ABC</texto> <!-- 0 o 1 -->\n" +                            
                            "					</alternativa>\n" +
                            "					<alternativa tipo=\"distractor\" tema=\"nuevoTema\"> <!-- o distractor --> \n" +
                            "						<texto tipo=\"normal\">ABCD</texto> <!-- 0 o 1 -->\n" +                            
                            "					</alternativa>\n" +
                            "				</opciones>\n" +
                            "				<solucion>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +                            
                            "				</solucion>\n" +
                            "			</forma>\n" +
                            "			<forma>\n" +
                            "				<enunciado>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +                            
                            "				</enunciado>\n" +
                            "				<opciones>\n" +
                            "					<alternativa tipo=\"solucion\" tema=\"nuevoTema\"> <!-- o distractor --> \n" +
                            "						<texto tipo=\"normal\">ABC</texto> <!-- 0 o 1 -->\n" +
                            "					</alternativa>\n" +
                            "					<alternativa tipo=\"distractor\" tema=\"nuevoTema\"> <!-- o distractor --> \n" +
                            "						<texto tipo=\"normal\">ABC</texto> <!-- 0 o 1 -->\n" +
                            "					</alternativa>\n" +
                            "				</opciones>\n" +
                            "				<solucion>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +
                            "				</solucion>\n" +
                            "			</forma>\n" +
                            "		</pregunta>\n" +
                            "	</evaluacion>"+
                            "	<feedback>https://docs.google.com/forms/d/1Zrj3pYt84a9bDFoHQrZvUM4uSiGSEUuX20x-Bco1-b8/viewform</feedback>\n" +
                            "</objeto>";
        
        good_instance.setFileContent(good_xml);
        good_instance.AppendDTD();
        
        objects = good_instance.readOA();
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Probar creacion de objeto
     */ 
    
    @Test
    public void testGoodObject(){
        int size_object = objects.size();
        
        assertEquals("Error en la lectura de objetos desde el xml", 1, size_object);
    }

    /**
     * Probar creacion de objeto a partir de un xml con errores
     */ 
    
    @Test
    public void testErrorObject() throws IOException{
        
        String error_xml =  
                            "   <objeto titulo=\"titulo\" tema=\"default\" autor=\"autor\">\n" +
                            "       <escena titulo=\"escena1\" tipo=\"1Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea ordenAparicion=\"1\">\n" +
                            "                   <texto tipo=\"normal\">texto1</texto>\n" +
                            "                   <texto tipo=\"codigo\"> Soy texto de un codigo</texto>\n" +
                            "                   <texto tipo=\"manuscrito\" >Este es un texto manuscrito</texto>" +
                            "                   <voz>Voz1</voz>\n" +
                            "               </idea>"+
                            "           </bloque>\n" +
                            "        \n" +
                            "   </objeto>";                            
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("Error en la lectura de objetos desde un xml con errores", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de slides
     */
    
    @Test
    public void testSlide(){
        int count_slide = 2;
        if(!objects.isEmpty()){
            assertEquals("Error en la lectura de slides desde el xml", count_slide, objects.get(0).getContent().size());
        }
        else
            fail("Error en la lectura del xml");                    
    }
    /**
     * Probar creacion de bloques
     */
    
    @Test
    public void testBlock(){
        int blocks = 4;
        int count_blocks = 0;
        
        if(!objects.isEmpty()){
            for(int i = 0; i < objects.get(0).getContent().size(); i++)
            {
                count_blocks += objects.get(0).getContent().get(i).getBlocks().size();
            }        
            assertEquals("Error en la lectura de bloques dsde el xml", blocks, count_blocks);
        }
        else
            fail("Error en la lectura del xml");            
    }
    /**
     * Probar creacion de ideas
     */
    
    @Test
    public void testIdea(){
        int ideas = 7;
        int count_ideas = 0;
        
        if(!objects.isEmpty()){
            for(int i = 0; i < objects.get(0).getContent().size(); i++)
            {
                for(int j = 0; j < objects.get(0).getContent().get(i).getBlocks().size(); j++){
                    count_ideas += objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().size();
                }
            }        
            assertEquals("Error en la lectura de ideas dsde el xml", ideas, count_ideas);
        }
        else
            fail("Error en la lectura del xml");            
    }    
    /**
     * Probar creacion de textos
     */
    
    @Test
    public void testText(){
        int texts = 5;
        int count_texts = 0;
        
        if(!objects.isEmpty()){
            for(int i = 0; i < objects.get(0).getContent().size(); i++)
            {
                for(int j = 0; j < objects.get(0).getContent().get(i).getBlocks().size(); j++){

                    for(int k = 0; k < objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().size(); k++){
                        count_texts += objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().get(k).getText().size();
                    }
                }
            }
            assertEquals("Error en la lectura de textos dsde el xml", texts, count_texts);
        }
        else
            fail("Error en la lectura del xml");            
    }
    /**
     * Probar creacion de recursos media
     */
    
    @Test
    public void testMedia(){
        int media = 2;
        int count_media = 0;
        
        if(!objects.isEmpty()){
            for(int i = 0; i < objects.get(0).getContent().size(); i++)
            {
                for(int j = 0; j < objects.get(0).getContent().get(i).getBlocks().size(); j++){

                    for(int k = 0; k < objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().size(); k++){
                        count_media += objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().get(k).getMedia().size();
                    }
                }
            }
            assertEquals("Error en la lectura de recursos media dsde el xml", media, count_media);        
        }
        else
            fail("Error en la lectura del xml");
    }
    
    /**
     * Probar creacion de ejemplos
     */
    
    @Test
    public void testExamples(){
        int list_examples = 2;
        int examples = 5;
        int count_list = 0;
        int count_examples = 0;
        
        if(!objects.isEmpty()){
            for(int i = 0; i < objects.get(0).getContent().size(); i++)
            {
                for(int j = 0; j < objects.get(0).getContent().get(i).getBlocks().size(); j++){

                    for(int k = 0; k < objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().size(); k++){
                                                
                        if(!objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().get(k).getExample().isEmpty()){
                            count_list++;
                            count_examples += objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().get(k).getExample().size();
                        }                                                
                    }
                }
            }
            assertEquals("Error en la lectura de pilas de ejemplos desde el xml", list_examples, count_list);        
            assertEquals("Error en la lectura de ejemplos de una pila desde el xml", examples, count_examples);        
        }
        else
            fail("Error en la lectura del xml");
    }
    
    /**
     * Probar creacion de conjunto de evaluaciones
     */
    
    @Test
    public void testEvaluaciones(){
        int test = 1;
        int question = 2;
        int forms = 3;
        int wording = 3;
        int wording_text = 3;        
        int choice = 6;
        int choice_text = 6;        
        int solution = 3;
        int solution_text = 3;                
        int exigency = 30;
        int count_test = 0;
        int count_questions = 0;
        int count_forms = 0;
        int count_wording = 0;
        int count_wording_text = 0;
        int count_choice = 0;
        int count_choice_text = 0;        
        int count_solution = 0;
        int count_solution_text = 0;
        int count_solution_media = 0;
        int count_exigency = 0;
        
        if(!objects.isEmpty()){
            for(int i = 0; i < objects.get(0).getQuizSet().size(); i++)
            {
                count_test++;
                count_exigency = objects.get(0).getQuizSet().get(i).getExigency();
                
                for(int j = 0; j < objects.get(0).getQuizSet().get(i).getQuestions().size() ; j++){
                    count_questions++;
                    for(int k = 0; k < objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().size(); k++){
                        count_forms++;
                        int voice = 0;
                        
                        count_wording_text += objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getTextContent().size();                        
                        if( (count_wording_text) > 0)
                            count_wording++;
                                                                        
                        count_solution_text  += objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getSolutionTextContent().size();                        
                        
                        if( (count_solution_media + count_solution_text + voice) > 0)
                            count_solution++;
                        
                        for( int l = 0; l < objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().size(); l++ ){
                            count_choice++;                            
                            count_choice_text += objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().get(l).getTextContent().size();
                        }
                    }                    
                }                
            }            
                        
            assertEquals("Error en la lectura del conjunto de evaluaciones en el xml", test, count_test );
            assertEquals("Error en la lectura del conjunto de evaluaciones en el xml", question, count_questions );
            assertEquals("Error en la lectura de conjuntos de formas del conjunto evaluaciones en el xml", forms, count_forms );
            assertEquals("Error en la lectura de los enunciados de cada forma en el xml", wording, count_wording );            
            assertEquals("Error en la lectura de textos en los enunciados de cada forma en el xml", wording_text, count_wording_text );
            assertEquals("Error en la lectura de las alternativas de cada forma en el xml", choice, count_choice );
            assertEquals("Error en la lectura de las textos de las alternativas de cada forma en el xml", choice_text, count_choice_text );            
            assertEquals("Error en la lectura de las soluciones de forma en el xml", solution, count_solution );
            assertEquals("Error en la lectura de los textos de las soluciones de forma en el xml", solution_text, count_solution_text );            
            assertEquals("Error en la lectura de la voz de las soluciones de forma en el xml", exigency, count_exigency );                        
        }
        else
            fail("Error en la lectura del xml");
    }
    /**
    * Probar creacion de slides
    */
    
    @Test
    public void testFeedback(){        
        if(!objects.isEmpty()){
            assertNotNull("Error en la lectura del feedback desde el xml", objects.get(0).getFeedback().getLink());
        }
        else
            fail("Error en la lectura del xml");                    
    }
    /**
     * Probar creacion de objeto a partir de un error en el tipo de escena
     */ 
    
    @Test
    public void testErrorTipoEscena() throws IOException{
        
        String error_xml =  
                            "   <objeto titulo=\"titulo\" tema=\"default\" autor=\"autor\">\n" +
                            "       <escena titulo=\"escena1\" tipo=\"random\">\n" +
                            "           <bloque>\n" +
                            "               <idea orden=\"1\">\n" +
                            "                   <texto tipo=\"normal\">texto1</texto>\n" +
                            "               </idea>"+
                            "           </bloque>\n" +
                            "       </escena> \n" +
                            "   </objeto>\n";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando los tipos de escenas existentes", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con cantidad de bloques inferior al correspondiente 
     */ 
    @Test
    public void testErrorCantidadBloques() throws IOException{
        
        String error_xml =  
                            "   <objeto titulo=\"titulo\" tema=\"default\" autor=\"autor\">\n" +
                            "       <escena titulo=\"escena1\" tipo=\"2Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea orden=\"1\">\n" +
                            "                   <texto tipo=\"normal\">texto1</texto>\n" +
                            "               </idea>"+
                            "           </bloque>\n" +
                            "       </escena> \n" +
                            "   </objeto>\n";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando la cantidad de bloques permitidos segun el tipo de escena", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un ordenAparicion no numerico
     */
    
    @Test
    public void testErrorOrdenNumerico() throws IOException{
        
        String error_xml =  
                            "   <objeto titulo=\"titulo\" tema=\"default\" autor=\"autor\">\n" +
                            "       <escena titulo=\"escena1\" tipo=\"1Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea orden=\"random\">\n" +
                            "                   <texto tipo=\"normal\">texto1</texto>\n" +
                            "               </idea>"+
                            "           </bloque>\n" +
                            "       </escena> \n" +
                            "   </objeto>\n";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada de ordenAparicion sea numerico", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un tipo de texto existente
     */
    @Test
    public void testErrorTipoTexto() throws IOException{
        
        String error_xml =  
                            "   <objeto titulo=\"titulo\" tema=\"default\" autor=\"autor\">\n" +
                            "       <escena titulo=\"escena1\" tipo=\"1Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea orden=\"1\">\n" +
                            "                   <texto tipo=\"randomtipo\">texto1</texto>\n" +
                            "               </idea>"+
                            "           </bloque>\n" +
                            "       </escena> \n" +
                            "   </objeto>\n";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada en tipo texto exista", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un tipo de media no existente
     */
    
    @Test
    public void testErrorTipoMedia() throws IOException{
        
        String error_xml =  
                            "	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
                            "		<escena titulo=\"Ejemplo de multimedia\" tipo=\"1Col\">\n" +
                            "			<bloque>\n" +
                            "				<idea orden=\"1\">\n" +
                            "					<texto tipo=\"normal\">El siguiente es un ejemplo de una imagen</texto>\n" +
                            "					<media tipo=\"randomMediaType\">http://cdn.alltheragefaces.com/img/faces/jpg/fuck-yeah-fuck-yeah-clean.jpg</media>\n" +
                            "				</idea>\n" +				
                            "			</bloque>\n" +
                            "		</escena>\n" +
                            "	</objeto>\n";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada en tipo media exista", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un tipo de evaluacion no existente
     */
    @Test
    public void testErrorTipoAlternativa() throws IOException{
        
        String error_xml =  "<objeto titulo=\"Nombre del Objeto\" tema=\"usach\" autor=\"Autor\">\n" +
"	<escena titulo=\"Titulo de la escena\" tipo=\"1Col\">\n" +
"		<bloque>\n" +
"			<idea orden=\"1\">\n" +
"				<texto tipo=\"normal\"><destacar>A</destacar><enfatizar>B</enfatizar><destacar>C</destacar><esfatizar>D</enfatizar></texto>\n" +
"\n" +
"			</idea>\n" +
"		</bloque>\n" +
"	</escena>\n" +
"	<evaluacion exigencia_min=\"1\" exigencia_max=\"2\"> \n" +
"		<pregunta>\n" +
"			<forma>\n" +
"				<enunciado>\n" +
"					<texto tipo=\"normal\">Enunciado</texto>\n" +
"				</enunciado>\n" +
"				<opciones>\n" +
"					<alternativa tipo=\"random\" tema=\"Pruebas\">\n" +
"						<texto tipo=\"normal\">Alternativa</texto>\n" +
"					</alternativa>\n" +
"				</opciones>\n" +
"				<solucion>\n" +
"					<texto tipo=\"normal\">Solución</texto>\n" +
"				</solucion>\n" +
"			<forma>\n" +
"		</pregunta>\n" +
"	</evaluacion>	\n" +
"</objeto>";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada en tipo media exista", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de codigo DTD
     */
    @Test
    public void testReadOADTDAppend() {
        ReaderXml instance = new ReaderXml();
        String content ="<objeto titulo=\"Nombre del Objeto\" tema=\"usach\" autor=\"Autor\">\n" +
"	<escena titulo=\"Titulo de la escena\" tipo=\"1Col\">\n" +
"		<bloque>\n" +
"			<idea orden=\"1\">\n" +
"				<texto tipo=\"normal\"><destacar>A</destacar><enfatizar>B</enfatizar><destacar>C</destacar><esfatizar>D</enfatizar></texto>\n" +
"\n" +
"			</idea>\n" +
"		</bloque>\n" +
"	</escena>\n" +
"	<evaluacion exigencia_min=\"1\" exigencia_max=\"2\"> \n" +
"		<pregunta>\n" +
"			<forma>\n" +
"				<enunciado>\n" +
"					<texto tipo=\"normal\">Enunciado</texto>\n" +
"				</enunciado>\n" +
"				<opciones>\n" +
"					<alternativa tipo=\"solucion\" tema=\"Pruebas\">\n" +
"						<texto tipo=\"normal\">Alternativa</texto>\n" +
"					</alternativa>\n" +
"				</opciones>\n" +
"				<solucion>\n" +
"					<texto tipo=\"normal\">Solución</texto>\n" +
"				</solucion>\n" +
"			<forma>\n" +
"		</pregunta>\n" +
"	</evaluacion>	\n" +
"</objeto>";
        
        String dtd =    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<!DOCTYPE objeto [\n"
                + "<!ELEMENT objeto (escena*,evaluacion?,feedback?)>\n"
                + "<!ELEMENT escena (bloque+)>\n"
                + "<!ELEMENT bloque (idea+)>\n"
                + "<!ELEMENT idea (texto*, media*,ejemplos?,voz?,subidea*)>\n"
                + "<!ELEMENT texto (#PCDATA)>\n"
                + "<!ELEMENT voz (#PCDATA)>\n"
                + "<!ELEMENT media (#PCDATA)>\n"
                + "<!ELEMENT evaluacion (pregunta+)>\n"
                + "<!ELEMENT pregunta (forma+)>\n"
                + "<!ELEMENT forma (enunciado,opciones,solucion)>\n"
                + "<!ELEMENT enunciado (texto*)>\n"
                + "<!ELEMENT opciones (alternativa*)>\n"
                + "<!ELEMENT alternativa (texto*)>\n"
                + "<!ELEMENT solucion (texto*)>\n"
                + "<!ELEMENT feedback (#PCDATA)>\n"
                + "<!ELEMENT ejemplos (ejemplo*)>\n"
                + "<!ELEMENT ejemplo (texto_ejemplo*,media_ejemplo?)>\n"
                + "<!ELEMENT texto_ejemplo (#PCDATA)>\n"
                + "<!ELEMENT media_ejemplo (#PCDATA)>\n"     
                + "<!ELEMENT subidea (subtexto*)>\n"
                + "<!ELEMENT subtexto (#PCDATA)>\n"   
                + "\n"
                + "\n"
                + "<!ATTLIST objeto titulo CDATA #REQUIRED>\n"
                + "<!ATTLIST objeto autor CDATA #REQUIRED>\n"
                + "<!ATTLIST objeto tema CDATA #REQUIRED>\n"
                + "<!ATTLIST escena titulo CDATA #REQUIRED>\n"
                + "<!ATTLIST escena tipo CDATA #REQUIRED>\n"
                + "\n"
                + "<!ATTLIST idea orden CDATA #REQUIRED>\n"
                + "<!ATTLIST texto tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST media tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST texto_ejemplo tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST media_ejemplo tipo CDATA #REQUIRED>\n"
                + "\n"
                + "<!ATTLIST evaluacion exigencia_min CDATA #REQUIRED>\n"
                + "<!ATTLIST evaluacion exigencia_max CDATA #REQUIRED>\n"
                + "<!ATTLIST alternativa tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST alternativa tema CDATA #REQUIRED>\n"
                + "<!ATTLIST subidea orden CDATA #REQUIRED>\n"
                + "<!ATTLIST subtexto voz CDATA #REQUIRED>\n"
                + "]>";
                
        instance.setFileContent(content);
        instance.AppendDTD();
                
        assertEquals(dtd+content,instance.getFileContent());
    }        
    
    @Ignore  
    public void testPreProcessText(){
        ReaderXml instance = new ReaderXml();
        instance.setFileContent("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                                "<!DOCTYPE comenzar SYSTEM \"validator.dtd\"> \n" +
                                "\n" +
                                "<comenzar>\n" +
                                "   <objeto titulo=\"Prueba v3\" autor=\"Probador\" tema=\"default\">\n" +
                                "       <escena titulo=\"Escena 1 Columna\" tipo=\"1Col\">\n" +
                                "           <bloque>\n" +
                                "               <idea ordenAparicion=\"1\">\n" +
                                "                   <texto tipo=\"normal\">texto <destacar>destacado</destacar></texto>\n" +
                                "                   <texto tipo=\"codigo\">texto <enfatizar>enfatizado</enfatizar></texto>\n" +
                                "                   <voz>Voz 1Col</voz>\n" +
                                "               </idea>\n" +
                                "           </bloque>\n" +
                                "       </escena>\n" +
                                "   </objeto>\n" +
                                "</comenzar>");
        
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<!DOCTYPE comenzar SYSTEM \"validator.dtd\"> \n" +
                            "\n" +
                            "<comenzar>\n" +
                            "   <objeto titulo=\"Prueba v3\" autor=\"Probador\" tema=\"default\">\n" +
                            "       <escena titulo=\"Escena 1 Columna\" tipo=\"1Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea ordenAparicion=\"1\">\n" +
                            "                   <texto tipo=\"normal\">texto <destacar>destacado<destacar></texto>\n" +
                            "                   <texto tipo=\"codigo\">texto <enfatizar>enfatizado<enfatizar></texto>\n" +
                            "                   <voz>Voz 1Col</voz>\n" +
                            "               </idea>\n" +
                            "           </bloque>\n" +
                            "       </escena>\n" +
                            "   </objeto>\n" +
                            "</comenzar>";
        
        
        assertEquals(instance.getFileContent(), expected);
    }
    
    @Test
    public void errorTranslateTest(){
        ReaderXml instance = new ReaderXml();
        
        String prueba1 = instance.errorTranslate("Attribute \"orden\" is required and must be specified for element type \"idea\".");
        String prueba2 = instance.errorTranslate("Attribute \"algo\" must be declared for element type \"voz\".");
        String prueba3 = instance.errorTranslate("Attribute name \"asd\" associated with an element type \"voz\" must be followed by the ' = ' character.");
        String prueba4 = instance.errorTranslate("Content is not allowed in prolog.");
        String prueba5 = instance.errorTranslate("Element type \"sdoot\" must be declared.");
        String prueba6 = instance.errorTranslate("Open quote is expected for attribute \"algo\" associated with an element type \"voz\".");
        String prueba7 = instance.errorTranslate("The content of element type \"escena\" is incomplete, it must match \"(bloque)+\".");
        String prueba8 = instance.errorTranslate("The content of element type \"feedback\" must match \"null\".");
        String prueba9 = instance.errorTranslate("The content of elements must consist of well-formed character data or markup.");
        String prueba10 = instance.errorTranslate("The element type \"voz\" must be terminated by the matching end-tag \"</voz>\".");
        String prueba11 = instance.errorTranslate("The end-tag for element type \"voz\" must end with a '>' delimiter.");
        String prueba12 = instance.errorTranslate("The markup in the document following the root element must be well-formed.");
        String prueba13 = instance.errorTranslate("The value of attribute \"algo\" associated with an element type \"voz\" must not contain the '<' character.");    
        String prueba14 = instance.errorTranslate("XML document structures must start and end within the same entity.");
        String expected1 = "El atributo: \"orden\", es requerido para el elemento: \"idea\"..";
        String expected2 = "El elemento: \"voz\"., no tiene declarado un atributo: \"algo\", revise su sintáxis.";
        String expected3 = "En el elemento: \"voz\", el atributo: \"asd\", debe ser seguido del caracter \"'='\" y luego su contenido.";
        String expected4 = "Por favor, siga la sintaxis XML. Elementos fuera de \"<\" o \">\" no son soportados.";
        String expected5 = "El elemento: \"sdoot\", debe ser declarado. Por ello no es permitido.";
        String expected6 = "En el elemento: \"voz\"., el atributo: \"algo\", debe de tener comillas (\") que contengan su valor.";
        String expected7 = "El contenido del elemento \"escena\", está incompleto, debe ser: \"(bloque)+\"..";
        String expected8 = "El contenido de \"feedback\" debe ser un valor, no una etiqueta.";
        String expected9 = "El contenido de los elementos debe consistir en un caracter o marca bien formada.";
        String expected10 = "Los elementos \"voz\", deben terminar con la etiqueta de cierre: \"</voz>\".";
        String expected11 = "El tag de cierre de: element, debe terminar con: '>'.";
        String expected12 = "El marcador en el documento siguiente a la raíz debe ser un elemento bien formado.";
        String expected13 = "El valor del atriburo: \"algo\", asociado al elemento: \"voz\", no debe contener el caracter \"<\" ó \">\".";
        String expected14 = "Los documentos con estructura XML deben comenzar y finalizar con la misma entidad. Verifique su XML.";
        assertEquals(expected1, prueba1);
        assertEquals(expected2, prueba2);
        assertEquals(expected3, prueba3);
        assertEquals(expected4, prueba4);
        assertEquals(expected5, prueba5);
        assertEquals(expected6, prueba6);
        assertEquals(expected7, prueba7);
        assertEquals(expected8, prueba8);
        assertEquals(expected9, prueba9);
        assertEquals(expected10, prueba10);
        assertEquals(expected11, prueba11);
        assertEquals(expected12, prueba12);
        assertEquals(expected13, prueba13);
        assertEquals(expected14, prueba14);
    }
    
    @Test
    public void subIdeaTest() throws IOException{
        String content = "<objeto titulo=\"Nombre del Objeto\" tema=\"usach\" autor=\"Autor\">\n" +
"	<escena titulo=\"Titulo de la escena\" tipo=\"1Col\">\n" +
"		<bloque>\n" +
"			<idea orden=\"1\">\n" +
"				<subidea orden=\"1\">\n" +
"					<subtexto voz=\"Primero es el if\">If</subtexto>\n" +
"					<subtexto voz=\"luego la condición\">(var = 1)</subtexto>\n" +
"				</subidea>\n" +
"				<subidea orden=\"2\">\n" +
"					<subtexto voz=\"luego tenemos la identación\"><tab/></subtexto>\n" +
"					<subtexto voz=\"y luego continúan las instrucciones\">print(var)</subtexto>\n" +
"				</subidea>\n" +
"			</idea>\n" +
"		</bloque>\n" +
"	</escena>\n" +
"</objeto>";
        ReaderXml newRead = new ReaderXml();
        newRead.setFileContent(newRead.preProcessText(content));
        newRead.AppendDTD();
        assertEquals(newRead.readOA().get(0).getContent().get(0).getBlocks().get(0).getIdeas().get(0).getSubIdea().size(),2);
    }
    
    @Test
    public void subTextTest() throws IOException{
        String content = "<objeto titulo=\"Nombre del Objeto\" tema=\"usach\" autor=\"Autor\">\n" +
"	<escena titulo=\"Titulo de la escena\" tipo=\"1Col\">\n" +
"		<bloque>\n" +
"			<idea orden=\"1\">\n" +
"				<subidea orden=\"1\">\n" +
"					<subtexto voz=\"Primero es el if\">If</subtexto>\n" +
"					<subtexto voz=\"luego la condición\">(var = 1)</subtexto>\n" +
"				</subidea>\n" +
"				<subidea orden=\"2\">\n" +
"					<subtexto voz=\"luego tenemos la identación\"><tab/></subtexto>\n" +
"					<subtexto voz=\"y luego continúan las instrucciones\">print(var)</subtexto>\n" +
"				</subidea>\n" +
"			</idea>\n" +
"		</bloque>\n" +
"	</escena>\n" +
"</objeto>";
        ReaderXml newRead = new ReaderXml();
        newRead.setFileContent(newRead.preProcessText(content));
        newRead.AppendDTD();
        int subTextCounter = 0;
        for(int i = 0; i < newRead.readOA().get(0).getContent().get(0).getBlocks().get(0).getIdeas().get(0).getSubIdea().size(); i++){
            subTextCounter += newRead.readOA().get(0).getContent().get(0).getBlocks().get(0).getIdeas().get(0).getSubIdea().get(i).getSubIdeaContent().size();
        }
        assertEquals(subTextCounter,4);
    }
}
