/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

import javax.ejb.Stateless;
import cl.diinf.objetoAprendizaje.*;
import cl.diinf.util.ResourcesDownloader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author teamPODA
 */
@Stateless
public class OA_TranslateHtml implements OA_TranslateHtmlLocal {    
    
    private String translateError;
    
    public OA_TranslateHtml(){
        translateError = "NO_ERROR";
    }

    public String getTranslateError() {
        return translateError;
    }

    public void setTranslateError(String translateError) {
        this.translateError = translateError;
    }

    
    
    /**
     * Devuelve una página web completa para ser introducida en el contenedor
     * en la capa de vista.
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @return String codeHtml, resultado que estará como presentación del OA
     * @throws IOException 
     */
    public String writeHtml(ObjetoAprendizaje object) throws IOException{
                    
        String codeHtml;

        String OATitle= object.getTitle();
        String OAName = object.getName_file();
        
        codeHtml = write_headerHtml(object);
        codeHtml += write_contentHtml(object, OAName);
        codeHtml += write_librsHtml(object);
        codeHtml += write_scriptHtml(object);

        codeHtml += write_scriptsHtml(object);

        return codeHtml;        
    }
    
    /**
     * Escribe el header de la página web.
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @return string con el header incorporado.
     */
    public String write_headerHtml(ObjetoAprendizaje object){
        //Head del HTML
        
        String templateHtml;
        switch (object.getTemplate()){
            case "dafault":
                templateHtml = "default.css";
                break;
            case "usach":
                templateHtml = "usach-colores.css";
                break;
            default:
                templateHtml = "default.css";
                break;
        }
        
        String htmlHeader = "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "  <meta charset=\"utf-8\">\n" +
                            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                            "  <meta name=\"viewport\" content=\"width=1024, user-scalable=no\">\n\n" +
                            
                            "  <title>" + object.getTitle() + "</title>"+                                
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/core/deck.core.css\">\n\n" +
                            
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/goto/deck.goto.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/menu/deck.menu.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/navigation/deck.navigation.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/status/deck.status.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/scale/deck.scale.css\">\n" + "\n" +                            
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1up.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1down.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1up-1down.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/3columnas.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/3columnas-1up.css\">\n" + "\n" + "\n" +                              
                            "  <link href=\"resources/extensions/syntaxhighlighter/shCore.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                            "  <link href=\"resources/extensions/syntaxhighlighter/shThemeDefault.css\" rel=\"stylesheet\" type=\"text/css\" /> \n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/style/" + templateHtml +"\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/transition/horizontal-slide.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"print\" href=\"resources/core/print.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/font-manuscrita.css\"> \n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shCore.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shBrushPython.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shBrushJScript.js\"></script> \n" +
                            "  <script src=\"resources/modernizr.custom.js\"></script>\n" +
                            "  <script src=\"resources/jquery.min.js\"></script>\n" +
                            "  <script src=\"resources/core/deck.core.js\"></script>\n" +
                            "  <script src=\"resources/extensions/menu/deck.menu.js\"></script>\n" +
                            "  <script src=\"resources/extensions/goto/deck.goto.js\"></script>\n" +
                            "  <script src=\"resources/extensions/status/deck.status.js\"></script>\n" +
                            "  <script src=\"resources/extensions/navigation/deck.navigation.js\"></script>\n" +
                            "  <script src=\"resources/extensions/scale/deck.scale.js\"></script>\n" +
                            "  <script src=\"resources/extensions/deck.events/deck.events.js\"></script>\n" +
                            "  <script src=\"resources/extensions/anim/deck.anim.js\"></script>\n" +
                            "  <script src=\"resources/extensions/step/deck.step.js\"></script> \n" +
                            "  <script src=\"resources/audioController.js\"></script>\n" +
                            "  <script src=\"resources/visibilityController.js\"></script>\n" +
                            "  <script src=\"resources/textController.js\"></script> \n\n" +
                            
                            " <script>\n" +
                            "    $(function() {\n" +
                            "      $.deck('.slide');\n" +
                            "    });\n" +
                            "  </script> \n\n" +         
                            
                            " <script type=\"text/javascript\">\n" +
                            "    SyntaxHighlighter.all()\n" +
                            "  </script> \n\n" +
                
                            "<script>\n"+
                            "   function ej_aleatorio(){\n" +
                            "       index = Math.random() * ejemplos.length\n" +
                            "       index = Math.floor(index)\n" +
                            "       document.write(ejemplos[index])\n" +
                            "   }\n" +
                            "</script> " +
                
                            //write_scriptHtml(object) +
                            "</head>\n\n" + "<body>\n" +
                            "<div class=\"deck-container\" >\n";
                
        return htmlHeader;
    }
    
    public String write_text(String text, String desing){
        String color;
        switch (desing){
            case "usach":
                color = "orange";
                break;
            default:
                color = "green";
                break;                    
        }
        
        text = text.replaceAll("<destacar>", "<mark>");
        text = text.replaceAll("</destacar>", "</mark>");
        text = text.replaceAll("<enfatizar>", "<font color="+color+">");
        text = text.replaceAll("</enfatizar>", "</font>");         
        
        return text;
    }
    
    public String write_examples(ArrayList<String> list_examples, String start, String end){
        
        String examples = "<script>\nvar ejemplos = new Array(";
        
        for( int i = 0 ; i < list_examples.size(); i++){
            
            if(i == 0)
                examples += "\""+ list_examples.get(i) +"\"";
            else
                examples += ", \""+ list_examples.get(i) +"\"";
        }
        examples += ")</script>";
        examples += "<p>"+start+"<script language=javascript>ej_aleatorio()</script>"+ end +"</p>";
        
        return examples;
    }    
    
    public ArrayList<String> write_block(Block block, int numberSlide, int numberBlock, String design){
        
        ArrayList<String> slides_scripts = new ArrayList<>();
        
        String codeHtml = "";
        String scripts = "";
        
        ArrayList<String> list_examples = new ArrayList<>();
        
        //write idea
        for(int i = 0; i < block.getIdeas().size(); i++){
                                        
            Idea idea = block.getIdeas().get(i);
            String order = Integer.toString(idea.getAparitionOrder());
            String start_label = "", end_label = "";
            
            //código para una idea
            
            start_label = "<section class=\"slide-" + numberSlide +"-" + order+"\" style=\"visibility:hidden\">";
            end_label = "</section>";
            
            
            for(int j = 0; j < idea.getText().size(); j++){
                
                if(!idea.getText().get(j).getType().equals("ejemplo")){
                    
                    if(!list_examples.isEmpty()){
                        codeHtml += write_examples(list_examples, start_label, end_label);                        
                        list_examples.clear();
                    }
                }

                switch(idea.getText().get(j).getType()){

                    case "normal":
                        codeHtml += "<p>"+ start_label;
                        codeHtml += write_text(idea.getText().get(j).getContent(), design);
                        codeHtml += end_label+"</p>";
                        break;
                    case "manuscrito":
                        "<div id=\"mano-1-1\" style=\"width: 0px; height: 50px; white-space: nowrap; overflow: hidden;\">\n" +
                        "        <span class=\"manuscrita\" >texto escrito a mano uno</span>\n" +
                        "      </div>"
                        codeHtml += "<p class=\"manuscrita\">"+ start_label;
                        codeHtml += write_text(idea.getText().get(j).getContent(), design);
                        codeHtml += end_label+"</p>";
                        break;
                    case "codigo":
                        codeHtml += start_label+"<pre class=\"brush: js\">";
                        codeHtml += idea.getText().get(j).getContent();
                        codeHtml += "</pre>"+ end_label;
                        break;
                    case "ejemplo":
                        list_examples.add(idea.getText().get(j).getContent());
                        break;    
                    default:
                        break;
                };
            }
            
            if(!list_examples.isEmpty()){
                        
                codeHtml += write_examples(list_examples, start_label, end_label);
                list_examples.clear();
            }            
            
        }        
        slides_scripts.add(codeHtml);
        slides_scripts.add(scripts);
        return slides_scripts;
    }
    
    public String write_date(Date date){
        
        String date_str = date.toString();
        String [] date_split = date_str.split(" ");
        String fecha = "";
        for( int i = 0 ; i < date_split.length ; i++){
            
            if(i != 3 && i!=4){
                fecha += date_split[i] + " ";
            }
        }
        return fecha;
    }
        
    public String write_titleHtml(ObjetoAprendizaje object){
                
        String codeHtml =   "<section class=\"slide\" id=\"title-slide\">\n" +
                            "<h1>" + object.getTitle() + "</h1>"+
                            "   <div class=\"autor\"> \n" +
                            "<h3>" + object.getAuthor() + "</h3> \n" +
                            "</div>\n" +
                            "<div class=\"fecha\"> \n" +
                            "<h3>" + write_date(object.getCreationDate()) + "</h3> \n" +
                            "</div>" +
                            "</section>";
        
        return codeHtml;
    }

    public String write_animationHtml(Slide scene, int number_slide, String OAPath, String OAName ){
        
        String codeHtml = "";
        ArrayList<Idea> orderedIdea = new ArrayList<>();
        
        ArrayList<ArrayList<Idea>> blockIdea = new ArrayList<ArrayList<Idea>>();
        
        for(int i=0 ; i < scene.getBlocks().size(); i++){
                
            blockIdea.add(new ArrayList<Idea>());
                
            for(int j=0 ; j < scene.getBlocks().get(i).getIdeas().size(); j++){
                
                orderedIdea.add(scene.getBlocks().get(i).getIdeas().get(j));
                blockIdea.get(i).add(scene.getBlocks().get(i).getIdeas().get(j));
            }
        }
        
        Collections.sort(orderedIdea);
        
        for (int i = 0; i < orderedIdea.size(); i++){
            
            int number_idea = -1, number_block = -1;
            
            for(int j = 0; j < blockIdea.size(); j++){
                                
                int aux = blockIdea.get(j).indexOf(orderedIdea.get(i));

                if( aux != -1){
                    number_idea= aux;
                    number_block= j;
                    break;
                }
            }                        
            
            //if(orderedIdea.get(i).getAparitionOrder() != 1)
            codeHtml += "<div class=\"anim-show slide\" data-what=\".order-" + orderedIdea.get(i).getAparitionOrder() + "\" id=\"animacion-" + number_slide + "-" + number_block + "-" + number_idea + "\"></div>";

            if(!orderedIdea.get(i).getVoice().equals("")){
                String audioFileName;            
                try {
                    audioFileName = ResourcesDownloader.downloadFromGoogleTTS(orderedIdea.get(i).getVoice(), OAPath);

                    //<audio id="audio-numberSlide-numberBlock-numberIdea" source src="resources/audios/audioFileName.mp3" type="audio/ogg"></audio> 
                    codeHtml += ("<audio id=\"audio-" + number_slide + "-" + number_block + "-" + number_idea + "\" ");
                    codeHtml += ("source src=\"resources/audios/" + OAName+ "/"+audioFileName +"\" ");
                    codeHtml += ("type=\"audio/ogg\"></audio>");

                } catch (IOException ex) {
                    this.translateError = "NO_AUDIO";
                    
                }
            }
        }
        return codeHtml;
    }
    
    public ArrayList<String> write_slideHtml(Slide scene, int nro_slide, String OAPath, String OAName, String design){
        ArrayList<String> slides_scripts = new ArrayList<>();
        
        String codeHtml = "";
        String scriptsHtml = "";
        
        String string_num = Integer.toString(nro_slide);
        codeHtml += "<section class=\"slide\" id=\"slide-"+string_num+"\">\n" +
                            "<h2>"+scene.getTitle()+"</h2>\n";          
        
        ArrayList<String> block_return0;
        ArrayList<String> block_return1;
        ArrayList<String> block_return2;
        ArrayList<String> block_return3;
        ArrayList<String> block_return4;
        switch (scene.getDesign()){
            case "1Col":
                //escribir contenido del primer bloque
                
                
                ArrayList<String> block_return = write_block(scene.getBlocks().get(0), nro_slide, 0, design);                
                codeHtml += block_return.get(0);
                scriptsHtml += block_return.get(1);
                break;
                
            case "1Fil2Col":
                
                codeHtml += "<div class=\"up-2columnas-1up\">";
                //bloque 1
                block_return0 = write_block(scene.getBlocks().get(0), nro_slide, 0, design );
                codeHtml += block_return0.get(0);
                scriptsHtml += block_return0.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"left-2columnas-1up\">";
                //bloque 2
                block_return1 = write_block(scene.getBlocks().get(1), nro_slide, 1, design);
                codeHtml += block_return1.get(0);
                scriptsHtml += block_return1.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-2columnas-1up\">";
                //bloque 3
                block_return2 = write_block(scene.getBlocks().get(2),nro_slide, 2, design);
                codeHtml += block_return2.get(0);
                scriptsHtml += block_return2.get(1);
                codeHtml += "</div>";
                        
                break;
                
            case "2Col":
                                
                codeHtml += "<div class=\"left-2columnas\">";
                //escribir contenido del primer bloque
                block_return0 = write_block(scene.getBlocks().get(0), nro_slide, 0, design );
                codeHtml += block_return0.get(0);
                scriptsHtml += block_return0.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-2columnas\">";
                //escribir contenido del segundo bloque
                block_return1 = write_block(scene.getBlocks().get(1), nro_slide, 1, design );
                codeHtml += block_return1.get(0);
                scriptsHtml += block_return1.get(1);
                codeHtml += "</div>";
                
                break;
            case "3Col":
                
                codeHtml += "<div class=\"left-3columnas\">";
                //bloque 1
                block_return0 = write_block(scene.getBlocks().get(0), nro_slide, 0, design );
                codeHtml += block_return0.get(0);
                scriptsHtml += block_return0.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"center-3columnas\">";
                //bloque 2
                block_return1 = write_block(scene.getBlocks().get(1), nro_slide, 1, design );
                codeHtml += block_return1.get(0);
                scriptsHtml += block_return1.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-3columnas\">";
                //bloque 3
                block_return2 = write_block(scene.getBlocks().get(2),nro_slide, 2, design);
                codeHtml += block_return2.get(0);
                scriptsHtml += block_return2.get(1);
                codeHtml += "</div>";
                
                break;
            case "1Fil3Col":
                
                codeHtml += "<div class=\"up-3columnas-1up\">";
                //bloque 1
                block_return0 = write_block(scene.getBlocks().get(0), nro_slide, 0, design );
                codeHtml += block_return0.get(0);
                scriptsHtml += block_return0.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"left-3columnas-1up\">";
                //bloque 2
                block_return1 = write_block(scene.getBlocks().get(1), nro_slide, 1, design );
                codeHtml += block_return1.get(0);
                scriptsHtml += block_return1.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"center-3columnas-1up\">";
                //bloque 3
                block_return2 = write_block(scene.getBlocks().get(2),nro_slide, 2, design);
                codeHtml += block_return2.get(0);
                scriptsHtml += block_return2.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-3columnas-1up\">";
                //bloque 4
                block_return3 = write_block(scene.getBlocks().get(3),nro_slide, 3, design);
                codeHtml += block_return3.get(0);
                scriptsHtml += block_return3.get(1);
                codeHtml += "</div>";
                
                break;
            case "2Fil2Col":
                
                codeHtml += "<div class=\"up-2columnas-1up-1down\">";
                //bloque 1
                block_return0 = write_block(scene.getBlocks().get(0), nro_slide, 0, design );
                codeHtml += block_return0.get(0);
                scriptsHtml += block_return0.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"left-2columnas-1up-1down\">";
                //bloque 2
                block_return1 = write_block(scene.getBlocks().get(1), nro_slide, 1, design );
                codeHtml += block_return1.get(0);
                scriptsHtml += block_return1.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-2columnas-1up-1down\">";
                //bloque 3
                block_return2 = write_block(scene.getBlocks().get(2),nro_slide, 2, design);
                codeHtml += block_return2.get(0);
                scriptsHtml += block_return2.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"down-2columnas-1up-1down\">";
                //bloque 4
                block_return3 = write_block(scene.getBlocks().get(3),nro_slide, 3, design);
                codeHtml += block_return3.get(0);
                scriptsHtml += block_return3.get(1);
                codeHtml += "</div>";
                
                break;
            case "2Col1Fil":
                
                 codeHtml += "<div class=\"left-2columnas-1down\">";
                //bloque 1
                block_return0 = write_block(scene.getBlocks().get(0), nro_slide, 0, design );
                codeHtml += block_return0.get(0);
                scriptsHtml += block_return0.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-2columnas-1down \">";
                //bloque 2
                block_return1 = write_block(scene.getBlocks().get(1), nro_slide, 1, design );
                codeHtml += block_return1.get(0);
                scriptsHtml += block_return1.get(1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"down-2columnas-1down \">";
                //bloque 3
                block_return2 = write_block(scene.getBlocks().get(2),nro_slide, 2, design);
                codeHtml += block_return2.get(0);
                scriptsHtml += block_return2.get(1);
                codeHtml += "</div>";
                
                break;
                
            default:
                break;            
        }
        
        codeHtml += write_animationHtml(scene, nro_slide, OAPath, OAName);
        codeHtml += "</section>";        
        
        slides_scripts.add(codeHtml);
        slides_scripts.add(scriptsHtml);
        return slides_scripts;
    }
    
    /**
     * Escribe todas las escenas o "slides" del objeto en la página.
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @param codeHtml 
     * @param OAName 
     * @return string con todas las slides incorporadas.
     */
    public String write_contentHtml(ObjetoAprendizaje object, String OAName){

        /* Obtener la ruta donde guardar el OA*/
        String OAPath = ResourcesDownloader.generatePathForOA(); // Wildfly/standalone/deplayments/...ear/...web.war
        
        OAPath = OAPath.concat("/resources/audios/" + OAName +"/");

        //Codigo de las escenas
        String codeHtml = "";
        String slidesHtml = "";
        String scriptsHtml = "";
        codeHtml += write_titleHtml(object);
        
        for (int i = 0; i < object.getContent().size(); i++){
            ArrayList<String> slides_scripts;
            slides_scripts = write_slideHtml(object.getContent().get(i), i+1, OAPath, OAName, object.getTemplate());
            slidesHtml += slides_scripts.get(0);
            scriptsHtml += slides_scripts.get(1);
            
        }
        return codeHtml;
    }
    
    /**
     * Escribe liberías necesarias para utilizar el framework en el que está
     * construido el objeto.
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @param codeHtml 
     * @return string con las librerías incorporadas.
     */
    public String write_librsHtml(ObjetoAprendizaje object){

        String codeHtml = "";
        String htmlLibrs=   "    <div aria-role=\"navigation\">\n" +
                            "      <a href=\"#\" class=\"deck-prev-link\" title=\"Previous\">&#8592;</a>\n" +
                            "      <a href=\"#\" class=\"deck-next-link\" title=\"Next\">&#8594;</a>\n" +
                            "    </div>\n" + "\n" +
                            "    <p class=\"deck-status\" aria-role=\"status\">\n" +
                            "      <span class=\"deck-status-current\"></span>\n" + "/\n" +
                            "      <span class=\"deck-status-total\"></span>\n" + "    </p>\n" + "\n" +
                            "    <form action=\".\" method=\"get\" class=\"goto-form\">\n" +
                            "      <label for=\"goto-slide\">Go to slide:</label>\n" +
                            "      <input type=\"text\" name=\"slidenum\" id=\"goto-slide\" list=\"goto-datalist\">\n" +
                            "      <datalist id=\"goto-datalist\"></datalist>\n" +
                            "      <input type=\"submit\" value=\"Go\">\n" +
                            "    </form>\n" + "\n" +
                            "  </div>\n" + "\n" +
                            "<script src=\"resources/jquery.min.js\"></script>\n" +
                            "<script src=\"resources/core/deck.core.js\"></script>\n" + "\n" +
                            "<script src=\"resources/extensions/menu/deck.menu.js\"></script>\n" +
                            "<script src=\"resources/extensions/goto/deck.goto.js\"></script>\n" +
                            "<script src=\"resources/extensions/status/deck.status.js\"></script>\n" +
                            "<script src=\"resources/extensions/navigation/deck.navigation.js\"></script>\n" +
                            "<script src=\"resources/extensions/scale/deck.scale.js\"></script>\n" +
                            "<script src=\"resources/extensions/deck.events/deck.events.js\"></script>\n" +
                            "<script src=\"resources/extensions/anim/deck.anim.js\"></script>\n" +
                            "<script src=\"resources/extensions/step/deck.step.js\"></script>";

        codeHtml = codeHtml + "\n" + htmlLibrs;

        return codeHtml;
    }


    /**
     * Escribe en la página el código para reproducir voz.
     * @param object
     * @param codeHtml
     * @return 
     */
    
    public String write_scriptsHtml(ObjetoAprendizaje object){
        //Procesamiento de la voz por cada escena
        String codeHtml = "";
        String htmlScriptVoice = "";            

        for (int i = 0; i < object.getContent().size(); i++){

            Slide scene = object.getContent().get(i);                                    

            for(int j=0 ; j < scene.getBlocks().size(); j++){
                for( int k=0 ; k < scene.getBlocks().get(j).getIdeas().size(); k++){
                
                    if(!scene.getBlocks().get(j).getIdeas().get(k).getVoice().equals("")){
                        

                        htmlScriptVoice = "<script>\n" +
                                           "  $(\"#animacion-"+(i+1) +"-"+ j +"-"+ k +"\").bind('deck.becameCurrent', function(ev, direction) {\n" +
                                           "    pause(\"audio-" + (i+1) +"-"+ j +"-"+ k +"\");\n" +
                                           "    play(\"audio-" + (i+1) +"-"+ j +"-"+ k +"\");\n" +
                                           "  });\n" +
                                           "  $(\"#animacion-"+(i+1) +"-"+ j +"-"+ k +"\").bind('deck.lostCurrent', function(ev, direction) {\n" +
                                           "    pause(\"audio-" + (i+1) +"-"+ j +"-"+ k +"\");\n" +
                                           "  });\n" +
                                           "</script>\n";                            

                        codeHtml += "\n" + htmlScriptVoice;
                    }
                }
            }
        }    
        //Fin del HTML
        String htmlEnd = "</body>\n" + "</html>";
        codeHtml = codeHtml + "\n" + htmlEnd;

        return codeHtml;
    }
    
    
    
    
    
    
    
    
    
    
    


    public String write_html2(ObjetoAprendizaje object){
        String head_start = write_headStartHtml2(object);
        String head_end = write_headEndHtml2();
        
        ArrayList<String> slide_script = new ArrayList<>();
        slide_script = write_contentHTML2(object);
        String slides = slide_script.get(0);
        String scripts = slide_script.get(1);
        
        
        String html_end = write_htmlendHtml2();
        
        return head_start +
                scripts +
                head_end +
                slides +
                html_end;
    }

    public String write_headStartHtml2(ObjetoAprendizaje object){
        //Head del HTML
        
        String templateHtml;
        switch (object.getTemplate()){
            case "dafault":
                templateHtml = "default.css";
                break;
            case "usach":
                templateHtml = "usach-colores.css";
                break;
            default:
                templateHtml = "default.css";
                break;
        }
        
        String htmlHeader = "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "  <meta charset=\"utf-8\">\n" +
                            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                            "  <meta name=\"viewport\" content=\"width=1024, user-scalable=no\">\n\n" +
                            
                            "  <title>" + object.getTitle() + "</title>"+                                
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/core/deck.core.css\">\n\n" +
                            
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/goto/deck.goto.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/menu/deck.menu.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/navigation/deck.navigation.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/status/deck.status.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/scale/deck.scale.css\">\n" + "\n" +                            
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1up.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1down.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1up-1down.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/3columnas.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/3columnas-1up.css\">\n" + "\n" + "\n" +                              
                            "  <link href=\"resources/extensions/syntaxhighlighter/shCore.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                            "  <link href=\"resources/extensions/syntaxhighlighter/shThemeDefault.css\" rel=\"stylesheet\" type=\"text/css\" /> \n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/style/" + templateHtml +"\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/transition/horizontal-slide.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"print\" href=\"resources/core/print.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/font-manuscrita.css\"> \n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shCore.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shBrushPython.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shBrushJScript.js\"></script> \n" +
                            "  <script src=\"resources/modernizr.custom.js\"></script>\n" +
                            "  <script src=\"resources/jquery.min.js\"></script>\n" +
                            "  <script src=\"resources/core/deck.core.js\"></script>\n" +
                            "  <script src=\"resources/extensions/menu/deck.menu.js\"></script>\n" +
                            "  <script src=\"resources/extensions/goto/deck.goto.js\"></script>\n" +
                            "  <script src=\"resources/extensions/status/deck.status.js\"></script>\n" +
                            "  <script src=\"resources/extensions/navigation/deck.navigation.js\"></script>\n" +
                            "  <script src=\"resources/extensions/scale/deck.scale.js\"></script>\n" +
                            "  <script src=\"resources/extensions/deck.events/deck.events.js\"></script>\n" +
                            "  <script src=\"resources/extensions/anim/deck.anim.js\"></script>\n" +
                            "  <script src=\"resources/extensions/step/deck.step.js\"></script> \n" +
                            "  <script src=\"resources/audioController.js\"></script>\n" +
                            "  <script src=\"resources/visibilityController.js\"></script>\n" +
                            "  <script src=\"resources/textController.js\"></script> \n\n" +
                            
                            " <script>\n" +
                            "    $(function() {\n" +
                            "      $.deck('.slide');\n" +
                            "    });\n" +
                            "  </script> \n\n" +         
                            
                            " <script type=\"text/javascript\">\n" +
                            "    SyntaxHighlighter.all()\n" +
                            "  </script> \n\n" +
                
                            "<script>\n"+
                            "   function ej_aleatorio(){\n" +
                            "       index = Math.random() * ejemplos.length\n" +
                            "       index = Math.floor(index)\n" +
                            "       document.write(ejemplos[index])\n" +
                            "   }\n" +
                            "</script> ";
        return htmlHeader;
    }
    
    public String write_headEndHtml2(){
        return  "</head>\n\n" + "<body>\n" +
                "<div class=\"deck-container\" >\n";
    }
                               
    public String write_htmlendHtml2(){
        String out =    "<div aria-role=\"navigation\">\n" +
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
                        "  </form>\n\n" +
                        "</body>\n" +
                        "</html>";
        return out;
    }

    public ArrayList<String> write_contentHTML2(ObjetoAprendizaje object){
        ArrayList<String> out = new ArrayList<>();
        
        String slide_code = "";
        String script_code = "";
        
        List<Slide> slide_list = object.getContent();
        
        //para cada slide
        for(int i=0, n=slide_list.size(); i<n; i++){
            int slide_number = (i+1);
            ArrayList<String> temp = new ArrayList<>();
            temp = write_slideHTML2(slide_list.get(i), slide_number);
            slide_code += temp.get(0);
            script_code += temp.get(1);
        }
        
        
        out.add(slide_code);
        out.add(script_code);
        return out;
    }

    public ArrayList<String> write_slideHTML2(Slide slide, int slide_number){
        ArrayList<String> out = new ArrayList<>();
        
        String slide_code = "";
        String script_code = "";
        
        String code_start = "<section class=\"slide\" id=\"slide-" + slide_number + "\">";
        String code_end = "</section>";
        
        slide_code += code_start;
        slide_code += "<h2>" + slide.getTitle() + "</h2>";
        
        
        List<Block> block_list = slide.getBlocks();
        

        //diseño de la slide
        ArrayList<String> block_return0;
        ArrayList<String> block_return1;
        ArrayList<String> block_return2;
        ArrayList<String> block_return3;
        switch (slide.getDesign()){
            case "1Col":
                //escribir contenido del primer bloque
                block_return0 = write_block(block_list.get(0), slide_number, 0, design);                
                break;
                
            case "1Fil2Col":
                
                slide_code += "<div class=\"up-2columnas-1up\">";
                //bloque 1
                block_return0 = write_blockHtml2(block_list.get(0), slide_number, 0, design );
                slide_code += block_return0.get(0);
                script_code += block_return0.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"left-2columnas-1up\">";
                //bloque 2
                block_return1 = write_blockHtml2(block_list.get(1), slide_number, 1, design );
                slide_code += block_return1.get(0);
                script_code += block_return1.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"right-2columnas-1up\">";
                //bloque 1
                block_return2 = write_blockHtml2(block_list.get(2), slide_number, 2, design );
                slide_code += block_return2.get(0);
                script_code += block_return2.get(1);
                slide_code += "</div>";
                        
                break;
                
            case "2Col":
                                
                slide_code += "<div class=\"left-2columnas\">";
                //escribir contenido del primer bloque
                block_return0 = write_blockHtml2(block_list.get(0), slide_number, 0, design );
                slide_code += block_return0.get(0);
                script_code += block_return0.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"right-2columnas\">";
                //escribir contenido del segundo bloque
                block_return1 = write_blockHtml2(block_list.get(1), slide_number, 1, design );
                slide_code += block_return1.get(0);
                script_code += block_return1.get(1);
                slide_code += "</div>";
                
                break;
            case "3Col":
                
                slide_code += "<div class=\"left-3columnas\">";
                //bloque 1
                block_return0 = write_blockHtml2(block_list.get(0), slide_number, 0, design );
                slide_code += block_return0.get(0);
                script_code += block_return0.get(1);
                slide_code += "</div>";
                
                
                slide_code += "<div class=\"center-3columnas\">";
                //bloque 2
                block_return1 = write_blockHtml2(block_list.get(1), slide_number, 1, design );
                slide_code += block_return1.get(0);
                script_code += block_return1.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"right-3columnas\">";
                //bloque 3
                block_return2 = write_blockHtml2(block_list.get(2), slide_number, 2, design );
                slide_code += block_return2.get(0);
                script_code += block_return2.get(1);
                slide_code += "</div>";
                
                break;
            case "1Fil3Col":
                
                slide_code += "<div class=\"up-3columnas-1up\">";
                //bloque 1
                block_return0 = write_blockHtml2(block_list.get(0), slide_number, 0, design );
                slide_code += block_return0.get(0);
                script_code += block_return0.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"left-3columnas-1up\">";
                //bloque 2
                block_return1 = write_blockHtml2(block_list.get(1), slide_number, 1, design );
                slide_code += block_return1.get(0);
                script_code += block_return1.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"center-3columnas-1up\">";
                //bloque 3
                block_return2 = write_blockHtml2(block_list.get(2), slide_number, 2, design );
                slide_code += block_return2.get(0);
                script_code += block_return2.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"right-3columnas-1up\">";
                //bloque 4
                block_return3 = write_blockHtml2(block_list.get(3), slide_number, 3, design );
                slide_code += block_return3.get(0);
                script_code += block_return3.get(1);
                slide_code += "</div>";
                
                break;
            case "2Fil2Col":
                
                slide_code += "<div class=\"up-2columnas-1up-1down\">";
                //bloque 1
                block_return0 = write_blockHtml2(block_list.get(0), slide_number, 0, design );
                slide_code += block_return0.get(0);
                script_code += block_return0.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"left-2columnas-1up-1down\">";
                //bloque 2
                block_return1 = write_blockHtml2(block_list.get(1), slide_number, 1, design );
                slide_code += block_return1.get(0);
                script_code += block_return1.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"right-2columnas-1up-1down\">";
                //bloque 3
                block_return2 = write_blockHtml2(block_list.get(2), slide_number, 2, design );
                slide_code += block_return2.get(0);
                script_code += block_return2.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"down-2columnas-1up-1down\">";
                //bloque 4
                block_return3 = write_blockHtml2(block_list.get(3), slide_number, 3, design );
                slide_code += block_return3.get(0);
                script_code += block_return3.get(1);
                slide_code += "</div>";
                
                break;
            case "2Col1Fil":
                
                 slide_code += "<div class=\"left-2columnas-1down\">";
                //bloque 1
                block_return0 = write_blockHtml2(block_list.get(0), slide_number, 0, design );
                slide_code += block_return0.get(0);
                script_code += block_return0.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"right-2columnas-1down \">";
                //bloque 2
                block_return1 = write_blockHtml2(block_list.get(1), slide_number, 1, design );
                slide_code += block_return1.get(0);
                script_code += block_return1.get(1);
                slide_code += "</div>";
                
                slide_code += "<div class=\"down-2columnas-1down \">";
                //bloque 3
                block_return2 = write_blockHtml2(block_list.get(2), slide_number, 2, design );
                slide_code += block_return2.get(0);
                script_code += block_return2.get(1);
                slide_code += "</div>";
                
                break;
                
            default:
                break;            
        }
        codeHtml += write_animationHtml(scene, nro_slide, OAPath, OAName);
        codeHtml += "</section>";        
        
        slides_scripts.add(codeHtml);
        slides_scripts.add(scriptsHtml);
        return slides_scripts;
        
        
        List<Block> block_list = slide.getBlocks();    
        //para cada bloque
        for(int i=0, n=block_list.size(); i<n; i++){
            ArrayList<String> temp = new ArrayList<>();
            temp = write_blockHtml2(block_list.get(i), slide_number);
            slide_code += temp.get(0);
            script_code += temp.get(1);
        }
            
        slide_code += code_end;
        
        out.add(slide_code);
        out.add(script_code);
        return out;
    }
    
    public ArrayList<String> write_blockHtml2(Block bloque, int slide_number){
        List<Idea> idea_list = bloque.getIdeas();
        
        String slide_code = "";
        String script_code = "";
        
        String code_start = "";
        String code_end = "";
        
        
        
    }



















}
