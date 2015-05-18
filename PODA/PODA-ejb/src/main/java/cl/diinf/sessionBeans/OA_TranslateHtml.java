/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

import javax.ejb.Stateless;
import cl.diinf.objetoAprendizaje.*;
import cl.diinf.util.CreateDirectory;
import cl.diinf.util.TTSDownloader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author teamPODA
 */
@Stateless
public class OA_TranslateHtml implements OA_TranslateHtmlLocal {    
    
    public OA_TranslateHtml(){
    
    }

    /**
     * Devuelve una página web completa para ser instroducida en el contenedor
     * en la capa de vista.
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @return String codeHtml, resultado que estará como presentación del OA
     * @throws IOException 
     */
    public String writeHtml(ObjetoAprendizaje object) throws IOException{
                    
        String codeHtml;
        
        //CreateDirectory create = new CreateDirectory();
        
        /* Crear un nombre unico para el OA*/
        String OATitle= object.getTitle();
        String OAName = object.getName_file();
        
        codeHtml = write_headerHtml(object);
        codeHtml += write_contentHtml(object, OAName);
        codeHtml += write_librsHtml(object);
        codeHtml += write_scriptHtml(object);

        codeHtml += write_voiceHtml(object);
        //create.createDirectory(codeHtml,OAName, OATitle);   //crea un directorio con el archivo html generado
        //String destino = "../standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war/OADownloads/"+OAName+"/resources"; 
        //String origen = "../standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war/resources";
        
        //File file = new File(destino);
        //File file2 = new File(origen);
        //CreateDirectory.copyFolder(file2,file);
        //String destinoAudio = "../standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war/OADownloads/"+OAName+"/resources/audios/"+OAName; 
        //String origenAudio = "../standalone/deployments/PODA-ear-1.0.ear/PODA-web-1.0.war/resources/audios/"+OAName;
        
        //File fileAudio = new File(destinoAudio);
        //File fileAudio2 = new File(origenAudio);
        //CreateDirectory.copyFolder(fileAudio2,fileAudio);      
        System.out.println(codeHtml);
        return codeHtml;
        
    }
    
    /**
     * Escribe el header de la página web.
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @return string con el header incorporado.
     */
    public String write_headerHtml(ObjetoAprendizaje object){
        //Head del HTML
        String htmlHeader = "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "  <meta charset=\"utf-8\">\n" +
                            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                            "  <meta name=\"viewport\" content=\"width=1024, user-scalable=no\">\n" +
                            "\n" + "  <title>" + object.getTitle() + "</title>"+                                
                            "<link rel=\"stylesheet\" media=\"screen\" href=\"resources/core/deck.core.css\">\n" + "\n" +
                            "  <!-- Extensiones deckjs -->\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/goto/deck.goto.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/menu/deck.menu.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/navigation/deck.navigation.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/status/deck.status.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/scale/deck.scale.css\">\n" + "\n" +
                            "  <!-- CSS bloques -->\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1up.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1down.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1up-1down.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/3columnas.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/3columnas-1up.css\">\n" + "\n" + "\n" +
                            "  <!-- Archivos necesarios para voz realtime. -->\n" +
                            "  <script src=\"resources/voice/AJAX_GOOGLE.js\"></script>\n" +
                            "  <script src=\"resources/voice/voice.js\"></script>\n" + "\n" +
                            "  <!-- Estilos -->\n" +
                            "  <!-- Estilo por defecto -->\n" +
                            "  <!--<link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/style/swiss.css\"> -->\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/style/swiss.css\">\n" + "\n" +
                            "  <!-- Estilo colores usach -->\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/style/usach-colores.css\">\n" + "\n" +
                            "  <!-- Tema transicion -->\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/transition/horizontal-slide.css\">\n" + "\n" +
                            "  <link rel=\"stylesheet\" media=\"print\" href=\"resources/core/print.css\">\n" + "\n" +
                            "  <!-- Archivo modernizr necesario -->\n" +
                            "  <script src=\"resources/modernizr.custom.js\"></script>\n" + " \n" +
                            "  <!-- Falta hacerlo online -->\n" +
                            "  <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>\n" + "  \n" +
                            "  <!-- CSS letra manuscrita -->\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/font-manuscrita.css\">"+
                            "<script>\n"+
                            "   function ej_aleatorio(){\n" +
                            "       index = Math.random() * ejemplos.length\n" +
                            "       index = Math.floor(index)\n" +
                            "       document.write(ejemplos[index])\n" +
                            "   }\n" +
                            "</script> " +
                            "<script>\n" +
                            "  function play(idaudio){\n" +
                            "  var audio = document.getElementById(idaudio);\n" +
                            "  audio.play();\n" +
                            "}\n" +
                            "\n" +
                            "function pause(idaudio){\n" +
                            "  var audio = document.getElementById(idaudio);\n" +
                            "  audio.pause();\n" +
                            "  audio.currentTime = 0;\n" +
                            "}\n" +
                            "</script>\n"+
                            "</head>\n" +"\n" + "<body>" +
                            "<div class=\"deck-container\" >\n";
                
        return htmlHeader;
    }
    
    public String write_text(String text){
                        
        text = text.replaceAll("/d", "<mark>");
        text = text.replaceAll("d/", "</mark>");
        text = text.replaceAll("/e", "<font color=green>");
        text = text.replaceAll("e/", "</font>"); 
        
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
    
    public String write_block(Block block, String OAPath, String OAName, int numberSlide, int numberBlock){
        
        String codeHtml = "";
        
        ArrayList<String> list_examples = new ArrayList<>();
        
        for(int i = 0; i < block.getIdeas().size(); i++){
                                        
            Idea idea = block.getIdeas().get(i);
            String order = Integer.toString(idea.getAparitionOrder());
            String start_label = "", end_label = "";
            
            if(idea.getAparitionOrder() != 1){
                start_label = "<span class = \"order-" + order + "\">";
                end_label = "</span>";
            }
            
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
                        codeHtml += write_text(idea.getText().get(j).getContent());
                        codeHtml += end_label+"</p>";
                        break;
                    case "manuscrito":
                        codeHtml += "<p id=\"manuscrita\">"+ start_label;
                        codeHtml += write_text(idea.getText().get(j).getContent());
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
        return codeHtml;
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
            
            if(orderedIdea.get(i).getAparitionOrder() != 1)
                codeHtml += "<div class=\"anim-show slide\" data-what=\".order-" + orderedIdea.get(i).getAparitionOrder() + "\" id=\"animacion-" + number_slide + "-" + number_block + "-" + number_idea + "\"></div>";

            if(!orderedIdea.get(i).getVoice().equals("")){
                String audioFileName;            
                try {
                    audioFileName = TTSDownloader.downloadFromGoogleTTS(orderedIdea.get(i).getVoice(), OAPath);

                    //<audio id="audio-numberSlide-numberBlock-numberIdea" source src="resources/audios/audioFileName.mp3" type="audio/ogg"></audio> 
                    codeHtml += ("<audio id=\"audio-" + number_slide + "-" + number_block + "-" + number_idea + "\" ");
                    codeHtml += ("source src=\"resources/audios/" + OAName+ "/"+audioFileName +"\" ");
                    codeHtml += ("type=\"audio/ogg\"></audio>");

                } catch (IOException ex) {
                    Logger.getLogger(OA_TranslateHtml.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return codeHtml;
    }
    
    public String write_slideHtml(Slide scene, int nro_slide, String OAPath, String OAName){
        
        String codeHtml = "";
        
        String string_num = Integer.toString(nro_slide);
        codeHtml += "<section class=\"slide\" id=\"slide-"+string_num+"\">\n" +
                            "<h2>"+scene.getTitle()+"</h2>\n";          
        
        switch (scene.getDesign()){
            case "1Col":
                //escribir contenido del primer bloque
                
                codeHtml += write_block(scene.getBlocks().get(0), OAPath, OAName, nro_slide, 0);                
                break;
                
            case "1Fil2Col":
                
                codeHtml += "<div class=\"up-2columnas-1up\">";
                //bloque 1
                codeHtml += write_block(scene.getBlocks().get(0), OAPath, OAName, nro_slide, 0);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"left-2columnas-1up\">";
                //bloque 2
                codeHtml += write_block(scene.getBlocks().get(1), OAPath, OAName, nro_slide, 1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-2columnas-1up\">";
                //bloque 3
                codeHtml += write_block(scene.getBlocks().get(2), OAPath, OAName, nro_slide, 2);
                codeHtml += "</div>";
                        
                break;
                
            case "2Col":
                                
                codeHtml += "<div class=\"left-2columnas\">";
                //escribir contenido del primer bloque
                codeHtml += write_block(scene.getBlocks().get(0), OAPath, OAName, nro_slide, 0);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-2columnas\">";
                //escribir contenido del segundo bloque
                codeHtml += write_block(scene.getBlocks().get(1), OAPath, OAName, nro_slide, 1);
                codeHtml += "</div>";
                
                break;
            case "3Col":
                
                codeHtml += "<div class=\"left-3columnas\">";
                //bloque 1
                codeHtml += write_block(scene.getBlocks().get(0), OAPath, OAName, nro_slide, 0);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"center-3columnas\">";
                //bloque 2
                codeHtml += write_block(scene.getBlocks().get(1), OAPath, OAName, nro_slide, 1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-3columnas\">";
                //bloque 3
                codeHtml += write_block(scene.getBlocks().get(2), OAPath, OAName, nro_slide, 2);
                codeHtml += "</div>";
                
                break;
            case "1Fil3Col":
                
                codeHtml += "<div class=\"up-3columnas-1up\">";
                //bloque 1
                codeHtml += write_block(scene.getBlocks().get(0), OAPath, OAName, nro_slide, 0);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"left-3columnas-1up\">";
                //bloque 2
                codeHtml += write_block(scene.getBlocks().get(1), OAPath, OAName, nro_slide, 1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"center-3columnas-1up\">";
                //bloque 3
                codeHtml += write_block(scene.getBlocks().get(2), OAPath, OAName, nro_slide, 2);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-3columnas-1up\">";
                //bloque 4
                codeHtml += write_block(scene.getBlocks().get(3), OAPath, OAName, nro_slide, 3);
                codeHtml += "</div>";
                
                break;
            case "2Fil2Col":
                
                codeHtml += "<div class=\"up-2columnas-1up-1down\">";
                //bloque 1
                codeHtml += write_block(scene.getBlocks().get(0), OAPath, OAName, nro_slide, 0);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"left-2columnas-1up-1down\">";
                //bloque 2
                codeHtml += write_block(scene.getBlocks().get(1), OAPath, OAName, nro_slide, 1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-2columnas-1up-1down\">";
                //bloque 3
                codeHtml += write_block(scene.getBlocks().get(2), OAPath, OAName, nro_slide, 2);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"down-2columnas-1up-1down\">";
                //bloque 4
                codeHtml += write_block(scene.getBlocks().get(3), OAPath, OAName, nro_slide, 3);
                codeHtml += "</div>";
                
                break;
            case "2Col1Fil":
                
                 codeHtml += "<div class=\"left-2columnas-1down\">";
                //bloque 1
                 codeHtml += write_block(scene.getBlocks().get(0), OAPath, OAName, nro_slide, 0);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"right-2columnas-1down \">";
                //bloque 2
                codeHtml += write_block(scene.getBlocks().get(1), OAPath, OAName, nro_slide, 1);
                codeHtml += "</div>";
                
                codeHtml += "<div class=\"down-2columnas-1down \">";
                //bloque 3
                codeHtml += write_block(scene.getBlocks().get(2), OAPath, OAName, nro_slide, 2);
                codeHtml += "</div>";
                
                break;
                
            default:
                break;            
        }
        
        codeHtml += write_animationHtml(scene, nro_slide, OAPath, OAName);
        codeHtml += "</section>";        
        
        return codeHtml;
    }
    
    /**
     * Escribe todas las escenas o "slides" del objeto en la página.
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @param codeHtml 
     * @return string con todas las slides incorporadas.
     */
    public String write_contentHtml(ObjetoAprendizaje object, String OAName){

        /* Obtener la ruta donde guardar el OA*/
        String OAPath = TTSDownloader.generatePathForOA(); // Wildfly/standalone/deplayments/...ear/...web.war
        
        OAPath = OAPath.concat("/resources/audios/" + OAName +"/");

        //Codigo de las escenas
        String codeHtml = "";
        codeHtml += write_titleHtml(object);
        
        for (int i = 0; i < object.getContent().size(); i++){

            codeHtml += write_slideHtml(object.getContent().get(i), i+1, OAPath, OAName);
                                    
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
        String htmlLibrs=  "    <!-- deck.navigation snippet -->\n" +
                            "    <div aria-role=\"navigation\">\n" +
                            "      <a href=\"#\" class=\"deck-prev-link\" title=\"Previous\">&#8592;</a>\n" +
                            "      <a href=\"#\" class=\"deck-next-link\" title=\"Next\">&#8594;</a>\n" +
                            "    </div>\n" + "\n" +
                            "    <!-- deck.status snippet -->\n" +
                            "    <p class=\"deck-status\" aria-role=\"status\">\n" +
                            "      <span class=\"deck-status-current\"></span>\n" + "/\n" +
                            "      <span class=\"deck-status-total\"></span>\n" + "    </p>\n" + "\n" +
                            "    <!-- deck.goto snippet -->\n" +
                            "    <form action=\".\" method=\"get\" class=\"goto-form\">\n" +
                            "      <label for=\"goto-slide\">Go to slide:</label>\n" +
                            "      <input type=\"text\" name=\"slidenum\" id=\"goto-slide\" list=\"goto-datalist\">\n" +
                            "      <datalist id=\"goto-datalist\"></datalist>\n" +
                            "      <input type=\"submit\" value=\"Go\">\n" +
                            "    </form>\n" + "\n" +
                            "    <!-- End extension snippets. -->\n" +
                            "  </div>\n" + "\n" +
                            "<!-- Archivos JS necesarios -->\n" +
                            "<script src=\"resources/jquery.min.js\"></script>\n" +
                            "<script src=\"resources/core/deck.core.js\"></script>\n" + "\n" +
                            "<!-- Archivos JS de extensiones deckjs -->\n" +
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
     * Escribe la función que hace referencia al framework.
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @param codeHtml 
     * @return strig con el los script incorporados.
     */
    public String write_scriptHtml(ObjetoAprendizaje object){

        String codeHtml = "";
        String htmlScriptBase = "<script>\n" +
                            "  $(function() {\n" +
                            "    $.deck('.slide');\n" +
                            "  });\n" +
                            "</script>\n";

        codeHtml = codeHtml + "\n" + htmlScriptBase;
        
         //Fin del HTML
                
        codeHtml += "\n" + "</body>\n" + "</html>";
        
        return codeHtml;
    }

    /**
     * Escribe en la página el código para reproducir voz.
     * @param object
     * @param codeHtml
     * @return 
     */
    
    public String write_voiceHtml(ObjetoAprendizaje object){
        //Procesamiento de la voz por cada escena
        String codeHtml = "";
        String htmlScriptVoice = "";            

        for (int i = 0; i < object.getContent().size(); i++){

            Slide scene = object.getContent().get(i);                                    

            for(int j=0 ; j < scene.getBlocks().size(); j++){
                for( int k=0 ; k < scene.getBlocks().get(j).getIdeas().size(); k++){
                
                    if(!scene.getBlocks().get(j).getIdeas().get(k).getVoice().equals("")){
                        
                        if(scene.getBlocks().get(j).getIdeas().get(k).getAparitionOrder() != 1){
                            htmlScriptVoice = "<script>\n" +
                                                "  $(\"#animacion-"+(i+1) +"-"+ j +"-"+ k +"\").bind('deck.becameCurrent', function(ev, direction) {\n" +
                                                "    pause(\"audio-" + (i+1) +"-"+ j +"-"+ k +"\");\n" +
                                                "    play(\"audio-" + (i+1) +"-"+ j +"-"+ k +"\");\n" +
                                                "  });\n" +
                                                "  $(\"#animacion-"+(i+1) +"-"+ j +"-"+ k +"\").bind('deck.lostCurrent', function(ev, direction) {\n" +
                                                "    pause(\"audio-" + (i+1) +"-"+ j +"-"+ k +"\");\n" +
                                                "  });\n" +
                                                "</script>\n";                            
                        }
                        else if(scene.getBlocks().get(j).getIdeas().get(k).getAparitionOrder() == 1){
                            htmlScriptVoice = "<script>\n" +
                                                "  $(\"#slide-"+(i+1)+"\").bind('deck.becameCurrent', function(ev, direction) {\n" +
                                                "    pause(\"audio-" + (i+1) +"-"+ j +"-"+ k +"\");\n" +
                                                "    play(\"audio-" + (i+1) +"-"+ j +"-"+ k +"\");\n" +
                                                "  });\n" +
                                                "  $(\"#slide-"+(i+1)+"\").bind('deck.lostCurrent', function(ev, direction) {\n" +
                                                "    pause(\"audio-" + (i+1) +"-"+ j +"-"+ k +"\");\n" +
                                                "  });\n" +
                                                "</script>\n";                                                    
                        }
                        else{
                            
                        }
                        codeHtml += "\n" + htmlScriptVoice;
                    }
                }
            }
        }    
        //Fin del HTML
        String htmlEnd = "</body>\n" + "</html>";
        //pWriter.append(htmlEnd);
        codeHtml = codeHtml + "\n" + htmlEnd;

        return codeHtml;
    }
}