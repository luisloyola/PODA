/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

import cl.diinf.objetoAprendizaje.ObjetoAprendizaje;
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
 * @author teban
 */
public class testReaderXml {

    List<ObjetoAprendizaje> objects;
    
    public testReaderXml() {
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
                                
        readerXml good_instance = new readerXml();
        
        String good_xml =   "<comenzar>\n" +
                            "   <objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
                            "       <escena titulo=\"Ejemplo de textos\" tipo=\"1Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea ordenAparicion=\"1\">\n" +
                            "                   <texto tipo=\"normal\">Este texto tiene audio</texto>\n" +
                            "                   <voz>Buenos Dias, tardes, noches</voz>\n" +
                            "               </idea>\n" +
                            "               <idea ordenAparicion=\"3\">\n" +
                            "                   <texto tipo=\"manuscrito\">Este es un texto manuscrito</texto>\n" +
                            "                   <media tipo=\"imagen\">http://cdn.alltheragefaces.com/img/faces/jpg/fuck-yeah-fuck-yeah-clean.jpg</media>\n" +
                            "               </idea>\n" +
                            "               <idea ordenAparicion=\"4\">\n" +
                            "                   <texto tipo=\"ejemplo\">Este es el ejemplo 1</texto>\n" +
                            "                   <texto tipo=\"ejemplo\">Este es el ejemplo 2</texto>\n" +
                            "                   <texto tipo=\"ejemplo\">Este es el ejemplo 3</texto>\n" +
                            "                   <texto tipo=\"ejemplo\">Este es el ejemplo 4</texto>\n" +
                            "                   <texto tipo=\"ejemplo\">Este es el ejemplo 5</texto>\n" +
                            "               </idea>\n" +
                            "           </bloque>\n" +
                            "       </escena>\n" +
                            "       <escena titulo=\"Ejemplo de multimedia\" tipo=\"1Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea ordenAparicion=\"1\">\n" +
                            "                   <texto tipo=\"normal\">El siguiente es un ejemplo de una imagen</texto>\n" +
                            "                   <media tipo=\"imagen\">http://cdn.alltheragefaces.com/img/faces/jpg/fuck-yeah-fuck-yeah-clean.jpg</media>\n" +
                            "               </idea>\n" +
                            "           </bloque>\n" +
                            "       </escena>\n" +
                            "			<evaluaciones>\n" +
                            "				<evaluacion>\n" +
                            "					<enunciado>Enunciado Evaluacion </enunciado>\n" +
                            "					<opciones>\n" +
                            "						<alternativa tipo=\"solucion\" tema=\"prueba\">Si</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "					</opciones>\n" +
                            "				</evaluacion>\n" +
                            "				<evaluacion>\n" +
                            "					<enunciado>Enunciado Evaluacion 2</enunciado>\n" +
                            "					<opciones>\n" +
                            "						<alternativa tipo=\"solucion\" tema=\"prueba\">Alternativa a</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">Alternativa b</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "					</opciones>\n" +
                            "				</evaluacion>\n" +
                            "			</evaluaciones>" +
                            "			<evaluaciones>\n" +
                            "				<evaluacion>\n" +
                            "					<enunciado>Enunciado Evaluacion 3 </enunciado>\n" +
                            "					<opciones>\n" +
                            "						<alternativa tipo=\"solucion\" tema=\"prueba\">Si</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "					</opciones>\n" +
                            "				</evaluacion>\n" +
                            "				<evaluacion>\n" +
                            "					<enunciado>Enunciado Evaluacion 4</enunciado>\n" +
                            "					<opciones>\n" +
                            "						<alternativa tipo=\"solucion\" tema=\"prueba\">Alternativa a</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">Alternativa b</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "						<alternativa tipo=\"distractor\" tema=\"prueba\">No</alternativa>\n" +
                            "					</opciones>\n" +
                            "				</evaluacion>\n" +
                            "			</evaluaciones>" +
                            "   </objeto>\n" +
                            "</comenzar>";
        
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
        
        String error_xml =  "<comenzar>\n" +
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
                            "   </objeto>\n" +
                            "</comenzar>";
        readerXml instance = new readerXml();
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
        int blocks = 2;
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
        int ideas = 4;
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
        int texts = 8;
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
     * Probar creacion de conjunto de evaluaciones
     */
    
    @Test
    public void testPruebas(){
        int pruebas = 2;
        int count_pruebas = 0;
        
        if(!objects.isEmpty()){
            /*for(int i = 0; i < objects.get(0).getContent().size(); i++)
            {
                for(int j = 0; j < objects.get(0).getContent().get(i).getBlocks().size(); j++){

                    for(int k = 0; k < objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().size(); k++){
                        
                        count_pruebas += objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().get(k).getQuizSet().size();
                    }
                }
            }*/
            count_pruebas = objects.get(0).getQuizSet().size();
            assertEquals("Error en la lectura de conjunto de evaluaciones en el xml", pruebas, count_pruebas);
        }
        else
            fail("Error en la lectura del xml");
    }
    /**
     * Probar creacion de evaluaciones
     */
    
    @Test
    public void testEvaluaciones(){
        int evaluaciones = 4;
        int count_evaluaciones = 0;
        
        if(!objects.isEmpty()){
            /*for(int i = 0; i < objects.get(0).getContent().size(); i++)
            {
                for(int j = 0; j < objects.get(0).getContent().get(i).getBlocks().size(); j++){

                    for(int k = 0; k < objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().size(); k++){
                        
                        for(int l = 0; l < objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().get(k).getQuizSet().size(); l++){
                            
                            count_evaluaciones += objects.get(0).getContent().get(i).getBlocks().get(j).getIdeas().get(k).getQuizSet().get(l).getQuiz().size();
                    }
                }
            }*/
            for(int i = 0; i < objects.get(0).getQuizSet().size(); i++){
                count_evaluaciones+=objects.get(0).getQuizSet().get(i).getQuiz().size();
            }
            assertEquals("Error en la lectura de evaluaciones en el xml", evaluaciones, count_evaluaciones);
        }
        else
            fail("Error en la lectura del xml");
    }
    /**
     * Probar creacion de alternativas de las evaluaciones
     */
    
    @Test
    public void testAlternativas(){
        int alternativas = 16;
        int enunciado = 4;
        int count_alternativas = 0;
        int count_enunciado = 0;
        
        if(!objects.isEmpty()){
            for(int i = 0; i < objects.get(0).getQuizSet().size(); i++)
            {
                
                for(int j = 0; j < objects.get(0).getQuizSet().get(i).getQuiz().size(); j++){
                    count_enunciado += 1;
                    count_alternativas+=objects.get(0).getQuizSet().get(i).getQuiz().get(j).getChoices().size();
                }
            }
            assertEquals("Error en la lectura de evaluaciones en el xml", alternativas, count_alternativas);
            assertEquals("Error en la lectura de enunciados de evaluaciones en el xml", enunciado, count_enunciado);
        }
        else
            fail("Error en la lectura del xml");
    }
    
    /**
     * Probar creacion de objeto a partir de un error en el tipo de escena
     */ 
    
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
        readerXml instance = new readerXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando los tipos de escenas existentes", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con cantidad de bloques inferior al correspondiente 
     */ 
    
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
        readerXml instance = new readerXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando la cantidad de bloques permitidos segun el tipo de escena", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un ordenAparicion no numerico
     */
    
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
        readerXml instance = new readerXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada de ordenAparicion sea numerico", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un tipo de texto existente
     */
    
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
        readerXml instance = new readerXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada en tipo texto exista", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un tipo de media no existente
     */
    
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
        readerXml instance = new readerXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada en tipo media exista", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de objeto a partir de un xml con un tipo de evaluacion no existente
     */
    
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
        readerXml instance = new readerXml();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("No se esta validando que la entrada en tipo media exista", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de codigo DTD
     */
    
    public void testReadOADTDAppend() {
        readerXml instance = new readerXml();
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
        readerXml instance = new readerXml();
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
