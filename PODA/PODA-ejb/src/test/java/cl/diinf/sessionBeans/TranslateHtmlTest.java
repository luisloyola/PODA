/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

import cl.diinf.objetoAprendizaje.Block;
import cl.diinf.objetoAprendizaje.Example;
import cl.diinf.objetoAprendizaje.FeedBack;
import cl.diinf.objetoAprendizaje.Idea;
import cl.diinf.objetoAprendizaje.LearningObject;
import cl.diinf.objetoAprendizaje.Media;
import cl.diinf.objetoAprendizaje.Scene;
import cl.diinf.objetoAprendizaje.TestOA;
import cl.diinf.objetoAprendizaje.Text;
import cl.diinf.sessionBeans.TranslateHtml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class TranslateHtmlTest {
        
    private LearningObject object;
    
    public TranslateHtmlTest() {
                
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {                
                                
        //media
        Media media1 = new Media();
        media1.setType("imagen");
        media1.setContent("http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306");        
        Media media2 = new Media();
        media2.setType("imagen");
        media2.setContent("http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg?1399003306");
        //textos
        Text text1 = new Text();
        text1.setContent("texto normal");
        text1.setType("normal");        
        Text text2 = new Text();
        text2.setContent("texto manuscrito");
        text2.setType("manuscrito");
        Text text3 = new Text();
        text3.setContent("if x == 0");
        text3.setType("codigo");
        Text text4 = new Text();
        text4.setContent("Soy texto <enfatizar>enfatizado</enfatizar> y <destacar>destacado</destacar>");
        text4.setType("normal");
        Text text5 = new Text();
        text5.setContent("while x == 0:");
        text5.setType("codigo");
        
        //Listas
        List<Media> medias1 = new ArrayList<>();
        List<Media> medias2 = new ArrayList<>();
        medias1.add(media1);
        medias2.add(media1);
        medias2.add(media2);
        List<Text> texts1 = new ArrayList<>();
        List<Text> texts2 = new ArrayList<>();
        List<Text> texts3 = new ArrayList<>();
        List<Text> texts4 = new ArrayList<>();
        List<Text> texts5 = new ArrayList<>();
        List<Text> texts6 = new ArrayList<>();
        
        texts1.add(text1);
        texts2.add(text2);
        texts3.add(text3);
        texts4.add(text1);
        texts4.add(text2);
        texts5.add(text2);
        texts5.add(text4);
        texts6.add(text5);
        
        //ejemplos
        Example example1 = new Example();
        example1.setMediaContent(medias1);
        example1.setTextContent(texts3);
        Example example2 = new Example();
        example2.setMediaContent(medias1);
        example2.setTextContent(texts4);
        Example example3 = new Example();
        example3.setTextContent(texts3);        
        Example example4 = new Example();
        example4.setTextContent(texts6);        
        
        //list-ejemplos
        List<Example> list_examples = new ArrayList<>();
        list_examples.add(example1);
        list_examples.add(example2);
        List<Example> list_examples2 = new ArrayList<>();
        list_examples2.add(example3);
        list_examples2.add(example4);
        
        //ideas
        Idea idea1 = new Idea();
        idea1.setAparitionOrder(1);
        idea1.setVoice("voz 1");
        idea1.setExample(list_examples);
        idea1.setMedia(medias1);
        idea1.setText(texts1);
        
        Idea idea2 = new Idea();
        idea2.setAparitionOrder(2);
        idea2.setVoice("voz 2");
        idea2.setText(texts2);
                
        Idea idea3 = new Idea();
        idea3.setAparitionOrder(3);
        idea3.setVoice("voz 3");
        idea3.setMedia(medias2);
        
        Idea idea4 = new Idea();
        idea4.setAparitionOrder(4);        
        idea4.setText(texts3);
        
        Idea idea5 = new Idea();
        idea5.setAparitionOrder(5);
        idea5.setVoice("voz 5");
        idea5.setText(texts4);
                
        Idea idea6 = new Idea();
        idea6.setAparitionOrder(6);        
        idea6.setText(texts5);
        idea6.setExample(list_examples2);
        
        //list_ideas
        List<Idea> ideas1 = new ArrayList<>();
        List<Idea> ideas2 = new ArrayList<>();
        //List<Idea> ideas3 = new ArrayList<>();
        List<Idea> ideas4 = new ArrayList<>();
        ideas1.add(idea4);
        ideas1.add(idea6);
        ideas2.add(idea2);
        ideas2.add(idea3);
        ideas4.add(idea5);
        ideas4.add(idea1);
        //bloques
        Block bloque1 = new Block();        
        bloque1.setIdeas(ideas1);
        List<Block> blocks1 = new ArrayList<>();
        blocks1.add(bloque1);
        
        Block bloque2 = new Block();
        bloque2.setIdeas(ideas2);        
        Block bloque3 = new Block();
        bloque3.setIdeas(ideas4);
        List<Block> blocks2 = new ArrayList<>();
        blocks2.add(bloque2);
        blocks2.add(bloque3);
        //slides
        Scene escena1 = new Scene();
        escena1.setDesign("1Col");
        escena1.setTitle("Primera pagina");
        escena1.setBlocks(blocks1);
        Scene escena2 = new Scene();        
        escena2.setDesign("2Col");
        escena2.setTitle("Segunda pagina");        
        escena2.setBlocks(blocks2);
        
        //list-slides
        List <Scene> scenes = new ArrayList<>();
        scenes.add(escena1);
        scenes.add(escena2);
        
        //object
        object = new LearningObject();
        object.setAuthor("PODA");        
        FeedBack feedback = new FeedBack("https://docs.google.com/a/usach.cl/forms/d/1Zrj3pYt84a9bDFoHQrZvUM4uSiGSEUuX20x-Bco1-b8/viewform");
        object.setFeedback(feedback);
        object.setName_file("PODA");
        object.setTemplate("usach");
        Date date = new Date(215,5,10);
        object.setCreationDate(date);
        object.setTitle("PODA");
        object.setQuizSet(null);
        object.setContent(scenes);
        List<TestOA> quizTest = new ArrayList<>();
        object.setQuizSet(quizTest);
    }
    
    @After
    public void tearDown() {
    }  
        
    @Test
    public void testWrite_headerHtml() throws IOException{
        
        TranslateHtml instance = new TranslateHtml();
        
        String correct = "<metacharset=\"utf-8\"><metahttp-equiv=\"X-UA-Compatible\"content=\"IE=edge,chrome=1\"><metaname=\"viewport\"content=\"width=1024,user-scalable=no\"><title>PODA</title><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/css/botones.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/core/deck.core.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/extensions/goto/deck.goto.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/extensions/menu/deck.menu.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/extensions/navigation/deck.navigation.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/extensions/status/deck.status.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/extensions/scale/deck.scale.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/css/botones.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/css/2columnas.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/css/2columnas-1up.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/css/2columnas-1down.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/css/2columnas-1up-1down.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/css/3columnas.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/css/3columnas-1up.css\"><linkhref=\"resources/extensions/syntaxhighlighter/shCore.css\"rel=\"stylesheet\"type=\"text/css\"/><linkhref=\"resources/extensions/syntaxhighlighter/shThemeDefault.css\"rel=\"stylesheet\"type=\"text/css\"/><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/themes/style/swiss.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/themes/style/usach-colores.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/themes/transition/horizontal-slide.css\"><linkrel=\"stylesheet\"media=\"print\"href=\"resources/core/print.css\"><linkrel=\"stylesheet\"media=\"screen\"href=\"resources/css/font-manuscrita.css\"><scriptsrc=\"resources/modernizr.custom.js\"></script><scriptsrc=\"resources/extensions/deck.syntaxhighlighter.js\"></script><scripttype=\"text/javascript\"src=\"resources/extensions/syntaxhighlighter/shCore.js\"></script><scripttype=\"text/javascript\"src=\"resources/extensions/syntaxhighlighter/shBrushPython.js\"></script><scripttype=\"text/javascript\"src=\"resources/extensions/syntaxhighlighter/shBrushJScript.js\"></script><scriptsrc=\"resources/modernizr.custom.js\"></script><scriptsrc=\"resources/jquery.min.js\"></script><scriptsrc=\"resources/core/deck.core.js\"></script><scriptsrc=\"resources/extensions/menu/deck.menu.js\"></script><scriptsrc=\"resources/extensions/goto/deck.goto.js\"></script><scriptsrc=\"resources/extensions/status/deck.status.js\"></script><scriptsrc=\"resources/extensions/navigation/deck.navigation.js\"></script><scriptsrc=\"resources/extensions/scale/deck.scale.js\"></script><scriptsrc=\"resources/extensions/deck.events/deck.events.js\"></script><scriptsrc=\"resources/extensions/anim/deck.anim.js\"></script><scriptsrc=\"resources/extensions/step/deck.step.js\"></script><scriptsrc=\"resources/audioController.js\"></script><scriptsrc=\"resources/visibilityController.js\"></script><scriptsrc=\"resources/textController.js\"></script><scriptsrc=\"resources/jqBarGraph.1.1.js\"></script><scriptsrc=\"resources/GoogleFormValidator.js\"></script><scriptsrc=\"resources/mano.js\"></script><script>$(function(){$.deck('.slide');});</script><scripttype=\"text/javascript\">SyntaxHighlighter.all()</script><script>functionej_aleatorio(){index=Math.random()*ejemplos.lengthindex=Math.floor(index)document.write(ejemplos[index])}</script>";
        
        String result = instance.write_headerHtml(object);
        result = result.replaceAll(" ","");
        result = result.replaceAll("\n","");
        
        assertEquals("Error en la escritura del header del html", correct, result);
    }
    
    @Test
    public void testWrite_scriptHeaderHtml(){
        
        TranslateHtml instance = new TranslateHtml();
        
        String correct = "<script>$(function(){$(\"#show-slide-1-4\").bind('deck.becameCurrent',function(ev,direction){SectionBecameCurrent(\"slide-1-0-0\",direction);});$(\"#show-slide-1-4\").bind('deck.lostCurrent',function(ev,direction){SectionLostCurrent(\"slide-1-0-0\",direction);});});</script><script>$(function(){$(\"#show-slide-1-6\").bind('deck.becameCurrent',function(ev,direction){SectionBecameCurrent(\"slide-1-0-1\",direction);textBecameCurrent([\"#mano-1-0-1-0\"],direction);manoBecameCurrent(\"#mano-1-0-1\",[\"#mano-1-0-1-0\"],-80);});$(\"#show-slide-1-6\").bind('deck.lostCurrent',function(ev,direction){SectionLostCurrent(\"slide-1-0-1\",direction);textLostCurrent([\"#mano-1-0-1-0\"],direction);manoLostCurrent(\"#mano-1-0-1\",direction,-80);});});</script><script>$(function(){$(\"#show-slide-2-2\").bind('deck.becameCurrent',function(ev,direction){SectionBecameCurrent(\"slide-2-0-0\",direction);AudioBecameCurrent(\"audio-2-0-0\");textBecameCurrent([\"#mano-2-0-0-0\"],direction);manoBecameCurrent(\"#mano-2-0-0\",[\"#mano-2-0-0-0\"],-80);});$(\"#show-slide-2-2\").bind('deck.lostCurrent',function(ev,direction){SectionLostCurrent(\"slide-2-0-0\",direction);AudioLostCurrent(\"audio-2-0-0\");textLostCurrent([\"#mano-2-0-0-0\"],direction);manoLostCurrent(\"#mano-2-0-0\",direction,-80);});});</script><script>$(function(){$(\"#show-slide-2-3\").bind('deck.becameCurrent',function(ev,direction){SectionBecameCurrent(\"slide-2-0-1\",direction);AudioBecameCurrent(\"audio-2-0-1\");});$(\"#show-slide-2-3\").bind('deck.lostCurrent',function(ev,direction){SectionLostCurrent(\"slide-2-0-1\",direction);AudioLostCurrent(\"audio-2-0-1\");});});</script><script>$(function(){$(\"#show-slide-2-5\").bind('deck.becameCurrent',function(ev,direction){SectionBecameCurrent(\"slide-2-1-0\",direction);AudioBecameCurrent(\"audio-2-1-0\");textBecameCurrent([\"#mano-2-1-0-0\"],direction);manoBecameCurrent(\"#mano-2-1-0\",[\"#mano-2-1-0-0\"],+617);});$(\"#show-slide-2-5\").bind('deck.lostCurrent',function(ev,direction){SectionLostCurrent(\"slide-2-1-0\",direction);AudioLostCurrent(\"audio-2-1-0\");textLostCurrent([\"#mano-2-1-0-0\"],direction);manoLostCurrent(\"#mano-2-1-0\",direction,+617);});});</script><script>$(function(){$(\"#show-slide-2-1\").bind('deck.becameCurrent',function(ev,direction){SectionBecameCurrent(\"slide-2-1-1\",direction);AudioBecameCurrent(\"audio-2-1-1\");});$(\"#show-slide-2-1\").bind('deck.lostCurrent',function(ev,direction){SectionLostCurrent(\"slide-2-1-1\",direction);AudioLostCurrent(\"audio-2-1-1\");});});</script><script>$(function(){$(\"#show-slide-2-1\").bind('deck.becameCurrent',function(ev,direction){SectionBecameCurrent(\"slide-2-1-1\",direction);textBecameCurrent([\"#mano-2-1-1-1-0\"],direction);manoBecameCurrent(\"#mano-2-1-1-1\",[\"#mano-2-1-1-1-0\"],+617);});$(\"#show-slide-2-1\").bind('deck.lostCurrent',function(ev,direction){SectionLostCurrent(\"slide-2-1-1\",direction);textLostCurrent([\"#mano-2-1-1-1-0\"],direction);manoLostCurrent(\"#mano-2-1-1-1\",direction,+617);});});</script>";
                
        String result = instance.write_scriptHeaderHtml(object);
        result = result.replaceAll(" ","");
        result = result.replaceAll("\n","");
        
        assertEquals("Error en la escritura de los scripts en el header del html", correct, result);
    }
    
    @Test
    public void testWrite_text(){
        
        TranslateHtml instance = new TranslateHtml();
        
        String correct = "Soy texto <font color=orange>enfatizado</font> y <mark>destacado</mark>";
        String result = instance.write_text(object.getContent().get(0).getBlocks().get(0).getIdeas().get(1).getText().get(1).getContent(), object.getTemplate());
        
        assertEquals("Error en la escritura de un texto normal", correct, result);        
    }
    @Test
    public void testTrozarCadena(){
        
        TranslateHtml instance = new TranslateHtml();
        
        ArrayList<String> correct = new ArrayList();
        correct.add("Soy una cadena muy ");
        correct.add("larga que va a ser ");
        correct.add("trozada en cadenas ");
        correct.add("con un máximo de 10 ");        
        correct.add("     caracteres ");
                
        ArrayList<String> result = instance.trozarCadena("Soy una cadena muy larga que va a ser trozada en cadenas con un máximo de 10      caracteres", 20);
        
        assertEquals("Error en el trozamiento de cadenas", correct, result);
    }
    
    @Test public void testWrite_titleHtml(){
        
        TranslateHtml instance = new TranslateHtml();
        
        String correct = "<sectionclass=\"slide\"id=\"title-slide\"><h1>PODA</h1><divclass=\"autor\"><h3>PODA</h3></div><divclass=\"fecha\"><h3>MonJun102115</h3></div></section>";
        
        String result = instance.write_titleHtml(object);
        result = result.replaceAll(" ","");
        result = result.replaceAll("\n","");
        assertEquals("Error en la traduccion a html del titulo del OA", correct, result);
    }
    
    @Test
    public void testWrite_SlideHtml(){
        
        TranslateHtml instance = new TranslateHtml();
        
        String correct = "<sectionclass=\"slide\"id=\"slide-1\"><h2>Primerapagina</h2><sectionclass=\"slide-1-0-0\"style=\"visibility:hidden\"><preclass=\"brush:js\">ifx==0</pre></section><sectionclass=\"slide-1-0-1\"style=\"visibility:hidden\"><IMGid=\"mano-1-0-1\"SRC=\"resources/manoconmanga.png\"WIDTH=800HEIGHT=800style=\"position:absolute;\"><divid=\"mano-1-0-1-0\"style=\"width:0px;height:50px;white-space:nowrap;overflow:hidden;\"><spanclass=\"manuscrita\">textomanuscrito</span></div><p>Soytexto<fontcolor=orange>enfatizado</font>y<mark>destacado</mark></p><script>varejemplos=newArray(\"<preclass=\\\"brush:js\\\">ifx==0</pre>\",\"<preclass=\\\"brush:js\\\">whilex==0:</pre>\")</script><scriptlanguage=javascript>ej_aleatorio()</script></section><divclass=slideid=\"show-slide-1-4\"></div><divclass=slideid=\"show-slide-1-6\"></div></section>";
        
        String result = instance.write_slideHtml(object.getContent().get(0), 1, "/test", object.getName_file(), object.getTemplate());
        result = result.replaceAll(" ","");
        result = result.replaceAll("\n","");
        
        assertEquals("Error en la traduccion a html de las escenas del OA", correct, result);
    }
    
    @Test
    public void testWrite_FeedBackHtml(){
        
        TranslateHtml instance = new TranslateHtml();
        
        String correct = "<sectionclass=\"slide\"style=\"background-color:#EDFCD0\"><divid=\"frase\"align=\"center\"><br><br><br><br><br><h3>Porfavor,respondelasiguienteencuestaparaseguirmejorandoelobjetodeaprendizajequeacabasdever.</h3><divid=\"boton\"style=\"margin-top:100px;\"><buttonclass=\"btnbtn-lgbtn-default\"type=\"button\"onclick=\"ShowIframe('#encuesta','#boton','#frase','https://docs.google.com/a/usach.cl/forms/d/1Zrj3pYt84a9bDFoHQrZvUM4uSiGSEUuX20x-Bco1-b8/viewform')\"><strong>Responderencuesta</strong></button></div></div><divid=\"encuesta\"></div></section>";
        
        String result = instance.write_feedbackHtml(object);
        result = result.replaceAll(" ","");
        result = result.replaceAll("\n","");
        
        assertEquals("Error en la traduccion a html de la encuesta del OA", correct, result);
    }
    
    @Test
    public void testWrite_librsHtml(){
        
        TranslateHtml instance = new TranslateHtml();
        String correct = instance.write_librsHtml(object);
        correct = correct.replaceAll(" ","");
        correct = correct.replaceAll("\n","");
        
        
        String result = "<div aria-role=\"navigation\">\n" +
                            "    <a href=\"#\" class=\"deck-prev-link\" title=\"Previous\">&#8592;</a>\n" +
                            "    <a href=\"#\" class=\"deck-next-link\" title=\"Next\">&#8594;</a>\n" +
                            "  </div>\n" +
                            "  <p class=\"deck-status\" aria-role=\"status\">\n" +
                            "    <span class=\"deck-status-current\"></span>\n" +
                            "    /\n" +
                            "    <span class=\"deck-status-total\"></span>\n" +
                            "  </p>\n" +
                            "  <form action=\".\" method=\"get\" class=\"goto-form\">\n" +
                            "    <label for=\"goto-slide\">Go to slide:</label>\n" +
                            "    <input type=\"text\" name=\"slidenum\" id=\"goto-slide\" list=\"goto-datalist\">\n" +
                            "    <datalist id=\"goto-datalist\"></datalist>\n" +
                            "    <input type=\"submit\" value=\"Go\">\n" +
                            "  </form>";
        result = result.replaceAll(" ","");
        result = result.replaceAll("\n","");
        
        assertEquals("Error en la escritura de las librerias deck", correct, result);
    }        
}
