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
public class OA_ReaderTest {

    List<ObjetoAprendizaje> objects;
    
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
                                
        OA_Reader good_instance = new OA_Reader();
        
        String good_xml =   "<comenzar>\n" +
                            "   <objeto titulo=\"Objeto de prueba de desarrollo\" tema=\"default\" autor=\"Teban\">\n" +
                            "       <escena titulo=\"Ejemplo de textos\" tipo=\"1Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea ordenAparicion=\"1\">\n" +
                            "                   <texto tipo=\"normal\">Este texto tiene audio</texto>\n" +
                            "                   <voz>Buenos Dias, tardes, noches</voz>\n" +
                            "               </idea>\n" +
                            "               <idea ordenAparicion=\"3\">\n" +
                            "                   <texto tipo=\"manuscrito\" >Este es un texto manuscrito</texto>\n" +
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
                            "               <idea ordenAparicion=\"3\">\n" +
                            "                   <texto tipo=\"normal\">El siguiente es un ejemplo de audio</texto>\n" +
                            "                   <!--<media tipo=\"audio\">https://www.youtube.com/watch?v=_4IRMYuE1hI</media>\n" +
                            "               --></idea>\n" +
                            "           </bloque>\n" +
                            "       </escena>\n" +
                            "       <escena titulo=\"Ejemplo de evaluación\" tipo=\"1Col\">\n" +
                            "           <bloque>\n" +
                            "               <idea ordenAparicion=\"1\">\n" +
                            "                   <evaluaciones>\n" +
                            "                       <evaluacion>\n" +
                            "                           <enunciado>Enunciado Evaluacion </enunciado>\n" +
                            "                           <opciones>\n" +
                            "                               <alternativa tipo=\"solucion\" tema=\"prueba\">Si</alternativa>\n" +
                            "                               <alternativa tipo=\"solucion\" tema=\"prueba\">No</alternativa>\n" +
                            "                           </opciones>                 \n" +
                            "                       </evaluacion>\n" +
                            "                   </evaluaciones>\n" +
                            "               </idea>\n" +
                            "           </bloque>\n" +
                            "       </escena>\n" +
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
        OA_Reader instance = new OA_Reader();
        instance.setFileContent(error_xml);  
        instance.AppendDTD();
        
        assertEquals("Error en la lectura de objetos desde un xml con errores", 0 ,instance.readOA().size());
    }
    /**
     * Probar creacion de slides
     */
    @Test
    public void testSlide(){
        int count_slide = 3;
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
        int blocks = 3;
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
        int ideas = 6;
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
        int texts = 9;
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
        int media = 1;
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
    
    
    @Ignore
    public void testEvaluaciones(){
        
    }
    
    @Ignore    
    public void testPreProcessText(){
        OA_Reader instance = new OA_Reader();
        instance.setFileContent("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<!DOCTYPE comenzar SYSTEM \"validator.dtd\"> \n" +
"\n" +
"<comenzar>\n" +
"   <objeto titulo=\"Prueba v3\" autor=\"Probador\" tema=\"default\">\n" +
"       <escena titulo=\"Escena 1 Columna\" tipo=\"1Col\">\n" +
"           <bloque>\n" +
"               <idea ordenAparicion=\"1\">\n" +
"                   <texto tipo=\"normal\">texto <destacado>destacado</destacado></texto>\n" +
"                   <texto tipo=\"codigo\">texto <enfatizado>enfatizado</enfatizado></texto>\n" +
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
"                   <texto tipo=\"normal\">texto /ddestacado/d</texto>\n" +
"                   <texto tipo=\"codigo\">texto /eenfatizado/e</texto>\n" +
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
