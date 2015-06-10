/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

import cl.diinf.objetoAprendizaje.LearningObject;
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
    public void setUp() {
                                
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
                            "<evaluacion exigencia=\"60\">\n" +
                            "		<pregunta>\n" +
                            "			<forma>\n" +
                            "				<enunciado>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +
                            "					<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media>	\n" +
                            "				</enunciado>\n" +
                            "				<opciones>\n" +
                            "					<alternativa tipo=\"solucion\" tema=\"nuevoTema\"> <!-- o distractor --> \n" +
                            "						<texto tipo=\"normal\">ABC</texto> <!-- 0 o 1 -->\n" +
                            "						<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media> <!-- 0 o 1? -->\n" +
                            "					</alternativa>\n" +
                            "					<alternativa tipo=\"distractor\" tema=\"nuevoTema\"> <!-- o distractor --> \n" +
                            "						<texto tipo=\"normal\">ABCD</texto> <!-- 0 o 1 -->\n" +
                            "						<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media> <!-- 0 o 1? -->\n" +
                            "					</alternativa>\n" +
                            "				</opciones>\n" +
                            "				<solucion>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +
                            "					<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media>\n" +
                            "					<voz>Voz correspondiente a la solucion</voz>\n" +
                            "				</solucion>\n" +
                            "			</forma>\n" +
                            "		</pregunta>\n" +
                            "		<pregunta>\n" +
                            "			<forma>\n" +
                            "				<enunciado>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +
                            "					<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media>	\n" +
                            "				</enunciado>\n" +
                            "				<opciones>\n" +
                            "					<alternativa tipo=\"solucion\" tema=\"nuevoTema\"> <!-- o distractor --> \n" +
                            "						<texto tipo=\"normal\">ABC</texto> <!-- 0 o 1 -->\n" +
                            "						<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media> <!-- 0 o 1? -->\n" +
                            "					</alternativa>\n" +
                            "					<alternativa tipo=\"distractor\" tema=\"nuevoTema\"> <!-- o distractor --> \n" +
                            "						<texto tipo=\"normal\">ABCD</texto> <!-- 0 o 1 -->\n" +
                            "						<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media> <!-- 0 o 1? -->\n" +
                            "					</alternativa>\n" +
                            "				</opciones>\n" +
                            "				<solucion>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +
                            "					<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media>\n" +
                            "					<voz>Voz correspondiente a la solucion</voz>\n" +
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
                            "						<media tipo=\"imagen\">http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306</media> <!-- 0 o 1? -->\n" +
                            "					</alternativa>\n" +
                            "				</opciones>\n" +
                            "				<solucion>\n" +
                            "					<texto tipo=\"normal\">ABC</texto>\n" +
                            "				</solucion>\n" +
                            "			</forma>\n" +
                            "		</pregunta>\n" +
                            "	</evaluacion>"+
                            "	<feedback>ABC</feedback>\n" +
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
    public void testErrorObject(){
        
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
        int wording_media = 2;
        int choice = 6;
        int choice_text = 5;
        int choice_media = 5;
        int solution = 3;
        int solution_text = 3;
        int solution_media = 2;
        int solution_voice = 2;
        int exigency = 60;
        int count_test = 0;
        int count_questions = 0;
        int count_forms = 0;
        int count_wording = 0;
        int count_wording_text = 0;
        int count_wording_media = 0;
        int count_choice = 0;
        int count_choice_text = 0;
        int count_choice_media = 0;        
        int count_solution = 0;
        int count_solution_text = 0;
        int count_solution_media = 0;
        int count_solution_voice = 0;
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
                        //count_wording_media += objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getMediaContent().size();
                        if( (count_wording_text) > 0)
                            count_wording++;
                                                
                        //count_solution_media += objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getSolutionMediaContent().size();
                        count_solution_text  += objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getSolutionTextContent().size();
                        
                        /*if(!objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getVoice().isEmpty()){
                            count_solution_voice++;
                            voice = 1;
                        }*/
                        
                        if( (count_solution_media + count_solution_text + voice) > 0)
                            count_solution++;
                        
                        for( int l = 0; l < objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().size(); l++ ){
                            count_choice++;
                            //count_choice_media += objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().get(l).getMediaContent().size();
                            count_choice_text += objects.get(0).getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().get(l).getTextContent().size();
                        }
                    }                    
                }                
            }            
                        
            assertEquals("Error en la lectura del conjunto de evaluaciones en el xml", test, count_test );
            assertEquals("Error en la lectura del conjunto de evaluaciones en el xml", question, count_questions );
            assertEquals("Error en la lectura de conjuntos de formas del conjunto evaluaciones en el xml", forms, count_forms );
            assertEquals("Error en la lectura de los enunciados de cada forma en el xml", wording, count_wording );
            //assertEquals("Error en la lectura de imagenes en los enunciados de cada forma en el xml", wording_media, count_wording_media );
            assertEquals("Error en la lectura de textos en los enunciados de cada forma en el xml", wording_text, count_wording_text );
            assertEquals("Error en la lectura de las alternativas de cada forma en el xml", choice, count_choice );
            assertEquals("Error en la lectura de las textos de las alternativas de cada forma en el xml", choice_text, count_choice_text );
            //assertEquals("Error en la lectura de las imagenes de las alternativas de cada forma en el xml", choice_media, count_choice_media );
            assertEquals("Error en la lectura de las soluciones de forma en el xml", solution, count_solution );
            assertEquals("Error en la lectura de los textos de las soluciones de forma en el xml", solution_text, count_solution_text );
            //assertEquals("Error en la lectura de las imagenes las soluciones de forma en el xml", solution_media, count_solution_media );
            //assertEquals("Error en la lectura de la voz de las soluciones de forma en el xml", solution_voice, count_solution_voice );
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
        int count_feedback = 1;
        if(!objects.isEmpty()){
            assertNotNull("Error en la lectura del feedback desde el xml", objects.get(0).getFeedback().getLink());
        }
        else
            fail("Error en la lectura del xml");                    
    }
    /**
     * Probar creacion de objeto a partir de un error en el tipo de escena
     */ 
    @Ignore
    @Test
    public void testErrorTipoEscena(){
        
        String error_xml =  "<comenzar>\n" +
                            "   <objeto titulo=\"titulo\" tema=\"default\" autor=\"autor\">\n" +
                            "       <escena titulo=\"escena1\" tipo=\"random\">\n" +
                            "           <bloque>\n" +
                            "               <idea ordenAparicion=\"1\">\n" +
                            "                   <texto tipo=\"normal\">texto1</texto>\n" +
                            "               </idea>"+
                            "           </bloque>\n" +
                            "       </escena> \n" +
                            "   </objeto>\n" +
                            "</comenzar>";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando los tipos de escenas existentes", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con cantidad de bloques inferior al correspondiente 
     */ 
    @Ignore
    @Test
    public void testErrorCantidadBloques(){
        
        String error_xml =  "<comenzar>\n" +
                            "   <objeto titulo=\"titulo\" tema=\"default\" autor=\"autor\">\n" +
                            "       <escena titulo=\"escena1\" tipo=\"2Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea ordenAparicion=\"1\">\n" +
                            "                   <texto tipo=\"normal\">texto1</texto>\n" +
                            "               </idea>"+
                            "           </bloque>\n" +
                            "       </escena> \n" +
                            "   </objeto>\n" +
                            "</comenzar>";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando la cantidad de bloques permitidos segun el tipo de escena", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un ordenAparicion no numerico
     */
    @Ignore
    @Test
    public void testErrorOrdenNumerico(){
        
        String error_xml =  "<comenzar>\n" +
                            "   <objeto titulo=\"titulo\" tema=\"default\" autor=\"autor\">\n" +
                            "       <escena titulo=\"escena1\" tipo=\"1Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea ordenAparicion=\"random\">\n" +
                            "                   <texto tipo=\"normal\">texto1</texto>\n" +
                            "               </idea>"+
                            "           </bloque>\n" +
                            "       </escena> \n" +
                            "   </objeto>\n" +
                            "</comenzar>";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada de ordenAparicion sea numerico", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un tipo de texto existente
     */
    @Ignore
    @Test
    public void testErrorTipoTexto(){
        
        String error_xml =  "<comenzar>\n" +
                            "   <objeto titulo=\"titulo\" tema=\"default\" autor=\"autor\">\n" +
                            "       <escena titulo=\"escena1\" tipo=\"1Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea ordenAparicion=\"1\">\n" +
                            "                   <texto tipo=\"randomtipo\">texto1</texto>\n" +
                            "               </idea>"+
                            "           </bloque>\n" +
                            "       </escena> \n" +
                            "   </objeto>\n" +
                            "</comenzar>";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada en tipo texto exista", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un tipo de media no existente
     */
    @Ignore
    @Test
    public void testErrorTipoMedia(){
        
        String error_xml =  "<comenzar>\n" +
                            "	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
                            "		<escena titulo=\"Ejemplo de multimedia\" tipo=\"1Col\">\n" +
                            "			<bloque>\n" +
                            "				<idea ordenAparicion=\"1\">\n" +
                            "					<texto tipo=\"normal\">El siguiente es un ejemplo de una imagen</texto>\n" +
                            "					<media tipo=\"randomMediaType\">http://cdn.alltheragefaces.com/img/faces/jpg/fuck-yeah-fuck-yeah-clean.jpg</media>\n" +
                            "				</idea>\n" +				
                            "			</bloque>\n" +
                            "		</escena>\n" +
                            "	</objeto>\n" +
                            "</comenzar>";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada en tipo media exista", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un tipo de evaluacion no existente
     */
    @Ignore
    @Test
    public void testErrorTipoAlternativa(){
        
        String error_xml =  "<comenzar>\n" +
                            "	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
                            "		<escena titulo=\"Ejemplo de evaluacion\" tipo=\"1Col\">\n" +
                            "			<bloque>\n" +
                            "				<idea ordenAparicion=\"1\">\n" +
                            "					<evaluaciones>\n" +
                            "						<evaluacion>\n" +
                            "							<enunciado>Enunciado Evaluacion </enunciado>\n" +
                            "							<opciones>\n" +
                            "								<alternativa tipo=\"randomType\" tema=\"prueba\">Si</alternativa>\n" +
                            "								<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "                                                           <alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "                                                           <alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "							</opciones>\n" +
                            "						</evaluacion>\n" +
                            "					</evaluaciones>\n" +
                            "				</idea>\n" +
                            "			</bloque>\n" +
                            "		</escena>\n" +
                            "	</objeto>\n" +
                            "</comenzar>";
        ReaderXml instance = new ReaderXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada en tipo media exista", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de codigo DTD
     */
    @Ignore
    public void testReadOADTDAppend() {
        ReaderXml instance = new ReaderXml();
        String content = "<comenzar>\n" +
                        "	<objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
                        "		<escena titulo=\"Ejemplo de textos\" tipo=\"1Col\">\n" +
                        "			<bloque>\n" +
                        "				<idea ordenAparicion=\"1\">\n" +
                        "					<texto tipo=\"manuscrito\">Este es un texto</texto>\n" +
                        "				</idea>\n" +
                        "			</bloque>\n" +
                        "		</escena>\n" +
                        "			\n" +
                        "	</objeto>\n" +
                        "</comenzar>";
        
        String dtd =    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<!DOCTYPE comenzar [\n"
                + "<!ELEMENT comenzar (objeto)>\n"
                + "<!ELEMENT objeto (escena*,evaluaciones*)>\n"
                + "<!ELEMENT escena (bloque+)>\n"
                + "<!ELEMENT bloque (idea*)>\n"
                + "<!ELEMENT idea (texto*, voz?, media*)>\n"
                + "<!ELEMENT texto (#PCDATA)>\n"
                + "<!ELEMENT voz (#PCDATA)>\n"
                + "<!ELEMENT media (#PCDATA)>\n"
                + "<!ELEMENT evaluaciones (evaluacion*)>\n"
                + "<!ELEMENT evaluacion (enunciado,opciones)>\n"
                + "<!ELEMENT enunciado (#PCDATA)>\n"
                + "<!ELEMENT opciones (alternativa*)>\n"
                + "<!ELEMENT alternativa (#PCDATA)>\n"
                + "\n"
                + "\n"
                + "<!ATTLIST objeto titulo CDATA #REQUIRED>\n"
                + "<!ATTLIST objeto autor CDATA #REQUIRED>\n"
                + "<!ATTLIST objeto tema CDATA #REQUIRED>\n"
                + "<!ATTLIST escena titulo CDATA #REQUIRED>\n"
                + "<!ATTLIST escena tipo CDATA #REQUIRED>\n"
                + "\n"
                + "<!ATTLIST idea ordenAparicion CDATA #REQUIRED>\n"
                + "<!ATTLIST texto tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST media tipo CDATA #REQUIRED>	\n"
                + "\n"
                + "<!ATTLIST alternativa tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST alternativa tema CDATA #REQUIRED>\n"
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
        
        instance.preProcessText();
        assertEquals(instance.getFileContent(), expected);
    }
}
