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
public class TranslateHtml {    
    
    private String translateError;    
    
    public TranslateHtml(){
        translateError = "NO_ERROR";
    }
    
    /**
     * Funcion que obtiene el atributo translateError
     * @return el valor de translateError
     */
    public String getTranslateError() {
        return translateError;
    }
    /**
     * Funcion que setea el valor del atributo translateError
     * @param translateError valor con cual setear el atributo translateError
     */
    public void setTranslateError(String translateError) {
        this.translateError = translateError;
    }   
    
    /**
     * Funcion que realiza la traduccion a html completa del objeto a ser creado
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @return String codeHtml, resultado que estará como presentación del OA
     * @throws IOException 
     */
    public String writeHtml(LearningObject object) throws IOException{
                    
        String codeHtml;       
        String OAName = object.getName_file();
        
        codeHtml =  "<!DOCTYPE html>\n" +"<html>\n" + "<head>\n";
        codeHtml += write_headerHtml(object);
        codeHtml += write_scriptHeaderHtml(object);        
        codeHtml += "</head>\n"+"<body>\n";
        codeHtml += "   <div class=\"deck-container\">\n";
        codeHtml += write_contentHtml(object, OAName);
        codeHtml += write_feedbackHtml(object);
        codeHtml += write_librsHtml(object);
        //codeHtml += write_scriptHand(object);
        codeHtml += "   </div>\n";
        codeHtml += "</body></html>";
        return codeHtml;        
    }
    
    /**
     * Realiza la traduccion a html del OA, en su seccion del head, agregando los recursos necesarios
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @return string con el header incorporado.
     */
    public String write_headerHtml(LearningObject object) throws IOException {
        //Head del HTML
        
        String templateHtml;
        
        String scriptEvaluacion = "";
        
        if(!object.getQuizSet().isEmpty()){
            scriptEvaluacion = write_evaluacionHtml(object);
        }
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
        
        String htmlHeader = "  <meta charset=\"utf-8\">\n" +
                            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                            "  <meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0\"> " +
                            "  <title>" + object.getTitle() + "</title>"+
                            " <link rel=\"stylesheet\" href=\"resources/extensions/style-chunks/core.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/core/deck.core.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/style/usach-colores.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/botones.css\"> \n" +
                            "  \n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/status/deck.status.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/scale/deck.scale.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/botones.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1up.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1down.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1up-1down.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/3columnas.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/3columnas-1up.css\">\n" +
                            "    \n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/transition/horizontal-slide.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"print\" href=\"resources/core/print.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/font-manuscrita.css\">\n" +
                            "  \n" +
                            "  <script src=\"resources/modernizr.custom.js\"></script>\n" +
                            "  <script src=\"resources/jquery.min.js\"></script>\n" +
                            "  <script src=\"resources/core/deck.core.js\"></script>\n" +
                            "\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/fit/deck.fit-fs.css\">\n" +
                            "  <script src=\"resources/extensions/fit/deck.fit.js\"></script>\n" +
                            "\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/goto/deck.goto.css\">\n" +
                            "  <script src=\"resources/extensions/goto/deck.goto.js\"></script>\n" +
                            "\n" +
                            "\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/navigation/deck.navigation.css\">\n" +
                            "  <script src=\"resources/extensions/navigation/deck.navigation.js\"></script>\n" +
                            "\n" +
                            "\n" +
                            "  <script src=\"resources/extensions/menu/deck.menu.js\"></script>\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/menu/deck.menu.css\">\n" +
                            "\n" +
                            "  <script src=\"resources/extensions/status/deck.status.js\"></script>\n" +
                            "\n" +
                            "  <script src=\"resources/extensions/step/deck.step.js\"></script>\n" +
                            "  <script src=\"resources/extensions/deck.events/deck.events.js\"></script>\n" +
                            "  <script src=\"resources/extensions/anim/deck.anim.js\"></script>\n" +
                            "  \n" +
                            "  <link href=\"resources/extensions/syntaxhighlighter/shCore.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                            "  <link href=\"resources/extensions/syntaxhighlighter/shThemeDefault.css\" rel=\"stylesheet\" type=\"text/css\" /> \n" +
                            "  <script src=\"resources/extensions/deck.syntaxhighlighter.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shCore.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shBrushPython.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shBrushJScript.js\"></script>\n" +
                            "  \n" +
                            "  <script src=\"resources/audioController.js\"></script>\n" +
                            "  <script src=\"resources/visibilityController.js\"></script>\n" +
                            "  <script src=\"resources/textController.js\"></script>\n" +
                            "  <script src=\"resources/jqBarGraph.1.1.js\"></script>\n" +
                            "  <script src=\"resources/GoogleFormValidator.js\"></script>\n" +
                            "  <script src=\"resources/mano.js\"></script> \n" +
                            "  <script type=\"text/javascript\" src=\"SCORM_API_wrapper.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"SCOFunctions.js\"></script>\n"+
                            "  <script type=\"text/javascript\" src=\"resources/SCORM_API_wrapper.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"resources/SCOFunctions.js\"></script>\n"+
                            "  <script src=\"resources/jqBarGraph.1.1.js\"></script>\n" + 
                            "  <script src=\"resources/GoogleFormValidator.js\"></script>\n"+
                            "  <script src=\"resources/mano.js\"></script>\n"+
                            "\n" +
                            "\n" +
                            "  <script>\n" +
                            "    $(function() {$.deck({\n" +
                            "      // fitMarginX:100, fitMarginY:100, // uncomment to tune margin\n" +
                            "      // fitMode: \"stretched\", // uncomment to strech\n" +
                            "      dummy:\"\"\n" +
                            "    });});\n" +
                            "  </script>" +
                            "\n" +
                            "  <script type=\"text/javascript\">\n" +
                            "    SyntaxHighlighter.all()\n" +
                            "  </script>" +
                
                            "<script>\n"+
                            "   function ej_aleatorio(){\n" +
                            "       index = Math.random() * ejemplos.length\n" +
                            "       index = Math.floor(index)\n" +
                            "       document.write(ejemplos[index])\n" +
                            "   }\n" +
                            "</script> ";                                            
        htmlHeader = htmlHeader + scriptEvaluacion;        
        return htmlHeader;
    }
    /**
     * Funcion que determina la cantidad maxima de caracteres que pueden ir en una linea dentro de un bloque ubicado en cierto tipo de diseño de slide
     * @param object Objeto que contiene al objeto de aprendizaje con todo su contenido
     * @param numberSlide Numero de la slide donde se desea escribir el texto
     * @param numberBlock Numero del bloque donde se desea escribir el texto
     * @return Un entero con la cantidad de caracteres maximo por linea
     */
    public int getCharMax(LearningObject object, int numberSlide, int numberBlock){
        
        int lim_line = 0;
        
        switch (object.getContent().get(numberSlide).getDesign()){
            
            case "1Col":
                lim_line = 110;
                break;
                
            case "1Fil2Col":
                switch (numberBlock){
                    case 0:
                        lim_line = 100;
                        break;
                    case 1:
                        lim_line = 45;
                        break;
                    case 2:
                        lim_line = 45;
                        break;
                    default:
                        break;
                }
                break;
                
            case "2Col":
                switch (numberBlock){
                    case 0:
                        lim_line = 45;
                        break;
                    case 1:
                        lim_line = 45;
                        break;                   
                    default:
                        break;
                }
                break;
                
            case "3Col":
                switch (numberBlock){
                    case 0:
                        lim_line = 30;
                        break;
                    case 1:
                        lim_line = 30;
                        break;
                    case 2:
                        lim_line = 30;
                        break;
                    default:
                        break;
                }
                break;
                
            case "1Fil3Col":
                switch (numberBlock){
                    case 0:
                        lim_line = 110;
                        break;
                    case 1:
                        lim_line = 30;
                        break;
                    case 2:
                        lim_line = 30;
                        break;
                    case 3:
                        lim_line = 30;
                        break;
                    default:
                        break;
                }
                break;
                
            case "2Fil2Col":
                switch (numberBlock){
                    case 0:
                        lim_line = 100;
                        break;
                    case 1:
                        lim_line = 45;
                        break;
                    case 2:
                        lim_line = 45;
                        break;
                    case 3:
                        lim_line = 100;
                        break;
                    default:
                        break;
                }
                break;
            case "2Col1Fil":
                switch (numberBlock){
                    case 0:
                        lim_line = 45;
                        break;
                    case 1:
                        lim_line = 45;
                        break;
                    case 2:
                        lim_line = 100;
                        break;                    
                    default:
                        break;
                }
                break;
                
            default:
                lim_line = 1;
                break;            
        }        
        return lim_line;
    }
    /**
     * Función que realiza la traduccion a html, en la seccion de los script que controlan el flujo de las slide, el audio y la animacion mano.texto
     * @param object Objeto que contiene al objeto de aprendizaje con todo su contenido
     * @return Cadena con el codigo html de la seccion de script
     */
    public String write_scriptHeaderHtml(LearningObject object){
        
        String script_header = "";
        ArrayList<String> trozos;
        for(int i = 0; i < object.getContent().size(); i++){
            
            Scene slide = object.getContent().get(i);
            
            for(int j = 0; j < slide.getBlocks().size(); j++){

                Block bloque = slide.getBlocks().get(j);
                
                for(int k = 0; k < bloque.getIdeas().size(); k++){
                    
                    Idea idea = bloque.getIdeas().get(k);
                    int id_hand = -1;                    
                    String atr_left = write_confHand(slide.getDesign(), j);
                    String script_voice_became = "";
                    String script_voice_lost = "";
                    String script_text_became = "";
                    String script_text_lost = "";
                    String script_hand_became = "";
                    String script_hand_lost = "";
                    String script_text_became_example = "";
                    String script_text_lost_example = "";
                    String script_hand_became_example = "";
                    String script_hand_lost_example = "";                    
                    
                    if(!idea.getVoice().isEmpty()){
                        
                        script_voice_became = "AudioBecameCurrent(\"audio-"+(i+1)+"-"+j+"-"+k+"\");";
                        script_voice_lost = "AudioLostCurrent(\"audio-"+(i+1)+"-"+j+"-"+k+"\");";
                    }
                    
                    for(int l = 0; l < idea.getText().size(); l++){
                        
                        if(idea.getText().get(l).getType().equals("manuscrito")){
                        
                            id_hand = l;
                            break;
                        }
                    }                    
                    if(id_hand > -1){

                        int caracter_max = getCharMax(object, i, j );
                        
                        trozos = trozarCadena(idea.getText().get(id_hand).getContent(), caracter_max);
                        
                        String text_ids = "";
                        
                        for(int m = 0; m < trozos.size(); m++){
                            text_ids += "\"#mano-"+(i+1)+"-"+j+"-"+k+"-"+ m + "\"";
                                                    
                            if(m+1 < trozos.size()){
                                text_ids +=",";
                            }
                        }
                        
                        script_text_became = "textBecameCurrent(["+ text_ids + "], direction);";
                        script_hand_became = "manoBecameCurrent(\"#mano-"+(i+1)+"-"+j+"-"+k+"\""+", ["+text_ids+"], "+ atr_left +");";
                        script_text_lost = "textLostCurrent(["+ text_ids +"], direction);";                        
                        script_hand_lost = "manoLostCurrent(\"#mano-"+(i+1)+"-"+j+"-"+k+"\""+", direction,"+ atr_left +");";
                    }
                                                                                
                    script_header +=    "<script>\n" +
                                        "    $(function(){\n" +
                                        "      $(\"#show-slide-"+(i+1)+"-"+idea.getAparitionOrder()+"\").bind('deck.becameCurrent', function(ev, direction) {\n" +                                        
                                        "        SectionBecameCurrent(\"slide-"+(i+1)+"-"+j+"-"+k+"\", direction);\n" +
                                                    script_voice_became +"\n"+
                                                    script_text_became +"\n"+
                                                    script_hand_became +"\n"+                                                    
                                        "      });\n" +
                                        "      $(\"#show-slide-"+(i+1)+"-"+idea.getAparitionOrder()+"\").bind('deck.lostCurrent', function(ev, direction) {\n" +
                                        "        SectionLostCurrent(\"slide-"+(i+1)+"-"+j+"-"+k+"\", direction);\n" +
                                                    script_voice_lost +"\n"+
                                                    script_text_lost +"\n"+
                                                    script_hand_lost +"\n"+                                                    
                                        "      });\n" +
                                        "    });\n" +
                                        "  </script>";
                    
                    for(int l = 0; l < idea.getExample().size(); l++){
                        
                        int id_hand_example = -1;
                        for(int m = 0; m < idea.getExample().get(l).getTextContent().size(); m++){
                            if(idea.getExample().get(l).getTextContent().get(m).getType().equals("manuscrito")){
                                id_hand_example = m;
                                
                                break;                                
                            }
                        }
                        if(id_hand_example > -1){
                            int caracter_max = getCharMax(object, i, j );
                            trozos = trozarCadena(idea.getExample().get(l).getTextContent().get(id_hand_example).getContent(), caracter_max);
                            String text_ids = "";

                            for(int m = 0; m < trozos.size(); m++){
                                text_ids += "\"#mano-"+(i+1)+"-"+j+"-"+k+"-"+ l + "-"+ m + "\"";
                                

                                if(m+1 < trozos.size()){
                                    text_ids +=",";
                                }
                            }

                            script_text_became_example = "textBecameCurrent(["+ text_ids + "], direction);";
                            script_text_lost_example = "textLostCurrent(["+ text_ids +"], direction);";
                            script_hand_became_example = "manoBecameCurrent(\"#mano-"+(i+1)+"-"+j+"-"+k+"-"+ l +"\""+", ["+text_ids+"], "+ atr_left +");";
                            script_hand_lost_example = "manoLostCurrent(\"#mano-"+(i+1)+"-"+j+"-"+k+"-"+ l +"\""+", direction,"+ atr_left +");";
                        
                            script_header +=    "<script>\n" +
                                            "    $(function(){\n" +
                                            "      $(\"#show-slide-"+(i+1)+"-"+idea.getAparitionOrder()+"\").bind('deck.becameCurrent', function(ev, direction) {\n" +
                                            "        SectionBecameCurrent(\"slide-"+(i+1)+"-"+j+"-"+k+"\", direction);\n" +
                                                        //script_voice_became +
                                                        script_text_became_example +
                                                        script_hand_became_example +
                                            "      });\n" +
                                            "      $(\"#show-slide-"+(i+1)+"-"+idea.getAparitionOrder()+"\").bind('deck.lostCurrent', function(ev, direction) {\n" +
                                            "        SectionLostCurrent(\"slide-"+(i+1)+"-"+j+"-"+k+"\", direction);\n" +
                                                        //script_voice_lost +
                                                        script_text_lost_example +
                                                        script_hand_lost_example +
                                            "      });\n" +
                                            "    });\n" +
                                            "  </script>";
                        }
                    }
                    for(int l = 0 ; l < idea.getSubIdea().size(); l++){
                        
                        String script_span_text_became;
                        String script_span_text_lost;
                        String script_span_voice_became = "";
                        String script_span_voice_lost = "";
                        
                        String text_ids = "";
                        for(int m = 0 ; m < idea.getSubIdea().get(l).getSubIdeaContent().size(); m++){
                            text_ids += "\"#span"+(i+1)+"-"+j+"-"+k+"-"+l+"-"+m+"\"";
                            if(m+1 < idea.getSubIdea().get(l).getSubIdeaContent().size()){
                                text_ids +=",";
                            }
                        }
                        for(int m = 0 ; m < idea.getSubIdea().get(l).getSubIdeaContent().size(); m++){
                            script_span_text_became = "spanBecameCurrent(\"#mano-"+(i+1)+"-"+j+"-"+k+"-"+l+"\",\"#manoSpan-"+(i+1)+"-"+j+"-"+k+"-"+l+"\", ["+text_ids+"],"+m+",direction);";
                            script_span_text_lost = "spanLostCurrent(\"#mano-"+(i+1)+"-"+j+"-"+k+"-"+l+"\",\"#manoSpan-"+(i+1)+"-"+j+"-"+k+"-"+l+"\", ["+text_ids+"],"+m+",direction);";
                            
                            if(!idea.getSubIdea().get(l).getSubIdeaContent().get(m).getVoice().isEmpty()){
                                script_span_voice_became = "AudioBecameCurrent(\"audio-"+(i+1)+"-"+j+"-"+k+"-"+l+"-"+m+"\");";
                                script_span_voice_lost = "AudioLostCurrent(\"audio-"+(i+1)+"-"+j+"-"+k+"-"+l+"-"+m+"\");";
                            }

                            script_header +=    "<script>\n" +
                                                "    $(function(){\n" +
                                                "      $(\"#show-slide-"+(i+1)+"-"+idea.getAparitionOrder()+"-"+l+"-"+m+"\").bind('deck.becameCurrent', function(ev, direction) {\n" +
                                                //"        SectionBecameCurrent(\"slide-"+(i+1)+"-"+j+"-"+k+"\", direction);\n" +
                                                            script_span_voice_became +
                                                            script_span_text_became +
                                                "      });\n" +
                                                "      $(\"#show-slide-"+(i+1)+"-"+idea.getAparitionOrder()+"-"+l+"-"+m+"\").bind('deck.lostCurrent', function(ev, direction) {\n" +
                                                //"        SectionLostCurrent(\"slide-"+(i+1)+"-"+j+"-"+k+"\", direction);\n" +
                                                            script_span_voice_lost +
                                                            script_span_text_lost +
                                                "      });\n" +
                                                "    });\n" +
                                                "  </script>";
                        }
                    }
                }
            }
        }
        return script_header;
    }

    
    /**
     * Funcion que realiza la traduccion a html de un texto del tipo normal, agregado el codigo para enfatizar y destacar
     * @param text Texto a ser escrito
     * @param template Tipo de template del OA donde se escribe el texto
     * @return La cadena de texto traducida a codigo html
     */
    public String write_text(String text, String template){
        String color;
        switch (template){
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
    /**
     * Funcion que troza una cadena de manera que cada trozo esta completo y no supera la cantidad maxima de caracteres que se permite 
     * en una linea de cierto bloque de cierto tipo de slide
     * @param content_text Texto a ser escrito y que se necesita trozar
     * @param max_chars Cantidad maxima de caracteres de una cadena
     * @return Un arreglo de cadenas, donde cada cadena en el arreglo es entera y no supera la cantidad máxima de caracteres
     */
    public ArrayList<String> trozarCadena(String content_text,int max_chars){
               
        ArrayList<String> piece_string = new ArrayList();
        
        int limite = max_chars;        
        int count_char = 0;
        String words_line = "";
        String[] words_content = content_text.split(" ");
        
        for( int i = 0; i < words_content.length; i++){            
            
            String word_aux = words_content[i] + " ";
            
            count_char += word_aux.length();            
            
            if( count_char > limite ){                                 
                
                if(!words_line.equals("")){
                    piece_string.add(words_line);  
                    words_line = "";
                }                
                while(word_aux.length() > limite){
                    
                    piece_string.add(word_aux.substring(0, limite));
                    word_aux=word_aux.substring(limite);
                }   
                if(word_aux.length() != 0)
                    words_line = word_aux;
                
                count_char = words_line.length();
            }
            else{                    
                words_line += word_aux;                
            }
            
            if( i+1 == words_content.length ){
                piece_string.add(words_line);
            }
        }                 
        return piece_string;        
    }
    /**
     * Funcion que realiza la traduccion a html de la animación mano-texto
     * @param trozos Trozos de cadenas que tienen que ser escritas por la animacion mano-texto
     * @param numberSlide Posicion de la slide, donde se va a encontrar la animacion, en el arreglo de slides del objeto
     * @param numberBlock Posicion del bloque, donde se va a encontrar la animacion, en el arreglo de bloques de la slide correspondiente
     * @param numberIdea Posicion de la idea, donde se va a encontrar la animacion, en el arreglo de ideas del bloque correspondiente
     * @param numberExample Si la animación es escrita como un ejemplo, se indica la posicion del ejemplo, donde se va a encontrar la animacion,
     * en el arreglo de ejemplos de la idea correspondiente
     * @return Cadena con el codigo html que representa a la animacion mano-texto
     */
    public String write_hand(ArrayList<String> trozos, int numberSlide, int numberBlock, int numberIdea, int numberExample){
        
        String add_example = "";
        String charE = "";
        if(numberExample != -1 ){
            add_example = "-"+numberExample;
            charE = "\\";
        }
                
        String id_handImage="\"mano-"+numberSlide+"-"+numberBlock+"-"+numberIdea+ add_example +charE+"\"";        
        String text = "<IMG id="+charE+ id_handImage+" SRC="+charE+"\"resources/manoconmanga.png"+charE+"\" WIDTH=800 HEIGHT=800 style="+charE+"\"position:absolute; display: none;"+charE+"\">";
                                        
        for(int i = 0; i < trozos.size(); i++){
        
            text +=     "<div id="+charE+"\"mano-"+numberSlide+"-"+numberBlock+"-"+numberIdea+ add_example +"-"+ i +charE+"\""+" style="+charE+"\"width: 0px; height: 32px; white-space: nowrap; overflow: hidden;"+charE+"\">" +
                        "<span class="+charE+"\"manuscrita"+charE+"\">"+ trozos.get(i) +"</span>" +
                        "</div>";
        }
        return text;
    }
    /**
     * Funcion que realiza la traduccion a html de la animación mano-texto     
     * @param idea Onbjeto del tipo Idea con todo el contenido necesario que tiene una idea
     * @param numberSlide Posicion de la slide, donde se va a encontrar la animacion, en el arreglo de slides del objeto
     * @param numberBlock Posicion del bloque, donde se va a encontrar la animacion, en el arreglo de bloques de la slide correspondiente
     * @param numberIdea Posicion de la idea, donde se va a encontrar la animacion, en el arreglo de ideas del bloque correspondiente     
     * @return Cadena con el codigo html que representa a la animacion mano-texto
     */
    public String write_subIdea(Idea idea, int numberSlide, int numberBlock, int numberIdea, String OAName, String OAPath){
        
        String text = "";
        String id_span;
        String id_handImage;
        String code_audio = "";
        
        
        for(int i = 0 ; i < idea.getSubIdea().size(); i++){
                        
            id_handImage="\"mano-"+numberSlide+"-"+numberBlock+"-"+numberIdea+"-"+i+"\"";
            text += "<IMG id="+ id_handImage+" SRC="+"\"resources/manoconmanga.png"+"\" WIDTH=800 HEIGHT=800 style="+"\"position:absolute; display: none;"+"\">";
            text +=     "<div id="+"\"manoSpan-"+numberSlide+"-"+numberBlock+"-"+numberIdea+"-"+ i +"\""+" style="+"\"width: 0px; height: 32px; white-space: nowrap; overflow: hidden;"+"\">";
            for(int j = 0; j < idea.getSubIdea().get(i).getSubIdeaContent().size(); j++){

                id_span = "\"span"+ numberSlide +"-"+numberBlock + "-"+ numberIdea +"-"+ i + "-" + j + "\"";
                text += "<span id="+id_span+"class="+"\"manuscrita"+"\">"+ idea.getSubIdea().get(i).getSubIdeaContent().get(j).getContent() +"</span>";
                
                if(!idea.getSubIdea().get(i).getSubIdeaContent().get(j).getVoice().isEmpty()){
                    
                    String audioFileName;
                    try {
                        audioFileName = ResourcesDownloader.downloadFromGoogleTTS(idea.getSubIdea().get(i).getSubIdeaContent().get(j).getVoice(), OAPath);

                        code_audio += ("<audio id=\"audio-" + numberSlide + "-" + numberBlock + "-" + numberIdea +"-"+ i +"-"+j+ "\" ");
                        code_audio += ("source src=\"resources/medias/" + OAName+ "/"+audioFileName +"\" ");
                        code_audio += ("type=\"audio/ogg\"></audio>");

                    } catch (IOException ex) {
                        this.translateError = "NO_AUDIO";
                    }
                }
            }
            text +="</div>"+ code_audio;                        
        }
        return text;
    }
    public String write_evaluacionHtml(LearningObject object) throws IOException{
                    
        String templateCss = "";
        String templateEvaluacion1 = "";
        String templateRescatarTemas = "";
        String templateFuncionesFijas = "";
        String templateFuncionesFijas2 = "";
        String total = null;
        String templateSolucionario = "";
        
        templateCss = "<style>\n" +
                      "div#evaluacion{ padding:10px 40px 40px 40px; }\n" +
                      "table, th, td {\n" +
                      "border: 1px solid black;\n" +
                      "border-collapse: collapse;\n" +
                      "}\n" +
                      "th, td {\n" +
                      "padding: 5px;\n" +
                      "}\n" +
                      "</style>";
        
        
        templateEvaluacion1= "<script>\n" +
                             "var posicion = 0, evaluacion, pregunta, seleccion, opciones, correcta = 0;\n" +
                             "var puntajeTotal        = 0;\n" +
                             "var puntajePorPregunta  = 0;\n" +
                             "var temasCorrectos      = new Array();\n" +
                             "var porcentajeExigenciaMin = " +object.getQuizSet().get(0).getExigency() + "\n" +
                             "var porcentajeExigenciaMax = " +object.getQuizSet().get(0).getSecondExigency() + "\n" +
                             "var temas     = [];\n" +
                             "var temaAgregar;\n" +
                             "var temasActuales    = [];\n" +
                             "var correctas              = 0;\n" +
                             "var puntajeTotalOA         = 0;\n" +
                             "var puntajeTotalOAObtenido = 0;\n";
                             
        String tempEval = "";
        List<String> pilas = new ArrayList<>();
        List<String> cantidadEnunciadosList = new ArrayList<>();
        
        int contador=0;
        for (int i=0; i<object.getQuizSet().size(); i++) {
            String varPila = "";
            int cantidadEnunciados = 0;
            
            for(int j = 0; j < object.getQuizSet().get(i).getQuestions().size(); j++) {
                
                for(int k = 0; k < object.getQuizSet().get(i).getQuestions().get(j).getForms().size(); k++){    
                    contador++;
                    cantidadEnunciados++;
                    tempEval+= "var enunciado"+(contador)+" = {\n" +
                               "enunciado: \"";
                    for(int l = 0; l < object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getTextContent().size(); l++){                        
                        if(object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getTextContent().size()-1 == l){
                            tempEval+= object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getTextContent().get(l).getContent()+"\",";
                        }

                        else{
                            tempEval+= object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getTextContent().get(l).getContent(); 
                        }
                    }
                    tempEval+="solucion: '";
                    for(int l = 0; l < object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().size(); l++){
                        
                        if(object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().get(l).getType().equals("solucion")){
                            
                            switch(l){
                            case 0:
                                tempEval+="A',";
                                break;

                            case 1:
                                tempEval+="B',";
                                break;

                            case 2:
                                tempEval+="C',";
                                break;

                            case 3:
                                tempEval+="D',";
                                break;

                            case 4:
                                tempEval+="E',";
                                break;    

                            case 5:
                                tempEval+="F',";
                                break;

                            case 6:
                                tempEval+="G',";
                                break;    
                            default:
                                break;
                        }


                        }
                    }
                    tempEval+="alternativas: [\n";
                    for(int l = 0; l < object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().size(); l++){
                            switch(l){
                            case 0:
                                tempEval+="{alternativa: 'A";
                                break;

                            case 1:
                                tempEval+="{alternativa: 'B";
                                break;

                            case 2:
                                tempEval+="{alternativa: 'C";
                                break;

                            case 3:
                                tempEval+="{alternativa: 'D";
                                break;

                            case 4:
                                tempEval+="{alternativa: 'E";
                                break;    

                            case 5:
                                tempEval+="{alternativa: 'F";
                                break;

                            case 6:
                                tempEval+="{alternativa: 'G";
                                break;    
                            default:
                                break;
                        } // fin switch

                        if(object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().size()-1 == l) {
                            tempEval+="', pregunta:\"";
                            for(int m = 0; m < object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().get(l).getTextContent().size(); m++){
                                 tempEval+= object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().get(l).getTextContent().get(m).getContent();
                            }
                            tempEval+= "\", tema:\""+object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().get(l).getTopic()+"\"}],";

                        }
                        else{
                            tempEval+="', pregunta:\"";
                            for(int m = 0; m < object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().get(l).getTextContent().size(); m++){
                                tempEval+= object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().get(l).getTextContent().get(m).getContent();
                            }
                            tempEval+= "\", tema:\""+object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getChoices().get(l).getTopic()+"\"},";
                        }
                    }                    
                    tempEval+="solucionario:\"";
                    
                    for(int l = 0; l < object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getSolutionTextContent().size(); l++){
                        tempEval+= object.getQuizSet().get(i).getQuestions().get(j).getForms().get(k).getSolutionTextContent().get(l).getContent();
                    }
                    tempEval+= "\"};\n";
                } // fin for k    
                                     
            } // fin for j
        cantidadEnunciadosList.add(String.valueOf(cantidadEnunciados));        
        } // fin for i
        
          
        String randomPila = ""; 
        int contador2 = 1;
        for(int i = 0; i < object.getQuizSet().size(); i++){ //Recorro todas las evaluaciones
            for(int j = 0; j < object.getQuizSet().get(i).getQuestions().size(); j++){ //Recorro cuantas preguntas hay en la evaluacion
                tempEval+="var pila"+(j+1)+" = [";
                //contador2++;
                for(int k = 0; k < object.getQuizSet().get(i).getQuestions().get(j).getForms().size(); k++){   //Recorro formas
                    if(k == object.getQuizSet().get(i).getQuestions().get(j).getForms().size()-1){
                        tempEval+="enunciado"+contador2+"];\n";
                        tempEval+="var elementoPila"+(j+1)+"Random= pila"+(j+1)+"[Math.floor(Math.random()*pila"+(j+1)+".length)];\n";
                        
                    }
                    else{
                         tempEval+="enunciado"+contador2+",";
                    }
                    contador2++;
                }
                
            }
            tempEval+="var preguntas=[";
            for(int j = 0; j < object.getQuizSet().get(i).getQuestions().size(); j++){
                if(j == object.getQuizSet().get(i).getQuestions().size()-1){
                    tempEval+="elementoPila"+(j+1)+"Random];\n" +
                       "var temas     = [];\n" +
                       "var temaAgregar;\n" +
                       "var temasActuales    = [];\n" +
                       "var correctas              = 0;\n" +
                       "var puntajeTotalOA         = 0;\n" +
                       "var puntajeTotalOAObtenido = 0;\n";
                }
                else{
                    tempEval+="elementoPila"+(j+1)+"Random,";
                }
            }
        }
                              
        
        templateRescatarTemas= "for (var contadorPreguntas=0; contadorPreguntas<preguntas.length; contadorPreguntas++){\n" +
                               "for (var contadorAlternativa=0; contadorAlternativa<preguntas[contadorPreguntas].alternativas.length; contadorAlternativa++){\n" +
                               "temaAgregar=preguntas[contadorPreguntas].alternativas[contadorAlternativa].tema;\n" +
                               "temas.push({ tema:temaAgregar, puntaje:0, puntajeTotal:0, porcentajeLogrado:0, color: null });                                                                    \n" +
                               "}\n" +
                               "}";

	templateFuncionesFijas = "function _(x){\n" +
                                 "return document.getElementById(x);\n" +
                                 "}\n" +
                    "\n" +
                    "        function cleanup(arr, prop) {\n" +
                    "          var new_arr = [];\n" +
                    "          var lookup  = {};\n" +
                    " \n" +
                    "          for (var i in arr) {\n" +
                    "            lookup[arr[i][prop]] = arr[i];\n" +
                    "          }\n" +
                    " \n" +
                    "          for (i in lookup) {\n" +
                    "            new_arr.push(lookup[i]);\n" +
                    "          }\n" +
                    " \n" +
                    "          return new_arr;\n" +
                    "        }\n" +
                    "\n" +
                    "        function porcentaje(puntajeTotal, puntajeObtenido){\n" +
                    "          var porcentajeObtenido = (puntajeTotal/puntajeObtenido) * 100;\n" +
                    "          return Math.floor(porcentajeObtenido);\n" +
                    "        }\n" +
                    "\n" +
                    "        function porcentajeNivelExigencia(porcentajeLogrado, porcentajeExigenciaMin, porcentajeExigenciaMax) {\n" +
                    "          if (porcentajeLogrado >= porcentajeExigenciaMax) { // Aprobado verde\n" +
                    "            return \"green\"\n" +
                    "          } else if (porcentajeLogrado >= porcentajeExigenciaMin && porcentajeLogrado <porcentajeExigenciaMax) {\n" +
                    "            return \"yellow\"\n" +
                    "          } else if (porcentajeLogrado < porcentajeExigenciaMin) {\n" +
                    "            return \"red\"\n" +
                    "          }\n" +
                    "        }\n" +
                    "\n" +
                     "function porcentajeMinimo(temas) {\n" +
                    " var minimo = Number.POSITIVE_INFINITY; \n"+
                    " var maximo = Number.NEGATIVE_INFINITY; \n " +
                    " var tmp; \n" +
                    " for (var i=temas.length-1; i>=0; i--) { " +
                    " tmp = temas[i].porcentajeLogrado; if (tmp < minimo) minimo =tmp; if(tmp>maximo) maximo = tmp;} return minimo;}" +                
                    "				function mostrarPregunta() {\n" +
                    "         evaluacion        = _(\"evaluacion\");\n" +
                    "         var str           = '';\n" +
                    "         var strTemas      = '';\n" +
                    "         var strPorcentaje = '';\n" +
                    "         puntajeTotalOA         = 0;\n" +
                    "         puntajeTotalOAObtenido = 0; var minimo=0\n" +
                    "          var mensaje=\"\";"+
                    "var strTemasFallados = '';"+
                    "\n" +
                    "          if(posicion >= preguntas.length){\n" +
                    "\n" +
                    "            temas=cleanup(temas,'tema'); //borrar temas repetidos\n" +
                    "\n" +
                    "            for (var c3=0; c3<temas.length; c3++) {\n" +
                    "              puntajeTotalOA         = puntajeTotalOA + temas[c3].puntajeTotal;\n" +
                    "              puntajeTotalOAObtenido = puntajeTotalOAObtenido + temas[c3].puntaje;\n" +
                    "            }\n" +
                    "            \n" +
                    "            if (puntajeTotalOAObtenido < 0) {\n" +
                    "              puntajeTotalOAObtenido = 0;\n" +
                    "            }\n" +
                    "\n" +
                    "            for (var l=0; l<temas.length; l++) {\n" +
                    "              temas[l].porcentajeLogrado = porcentaje(temas[l].puntaje, temas[l].puntajeTotal);\n" +
                    "            }\n" +
                    "\n" +
                    "            for (var j=0; j<temas.length; j++) { \n" +
                    "              if (temas[j].puntaje <=0) { \n" +
                    "                temas[j].puntaje=0;\n" +
                    "              }\n" +
                    "            }\n" +
                    "\n" +
                    "            for (var n=0; n<temas.length; n++) {\n" +
                    "              if (temas[n].puntajeTotal == 0) {\n" +
                    "                temas.splice(n,1);\n" +
                    "              }\n" +
                    "\n" +
                    "            } \n" +
                    "            \n" +
                    "            for(var i=0; i<temas.length ; i++){\n" +
                    "              \n" +
                    "              if (temas[i].puntaje == 0 && temas[i].puntajeTotal == 0) {\n" +
                    "                str = str\n" +
                    "              } else {\n" +
                    "                str = str + \"<tr><td> T\"+(i+1)+\": \"+temas[i].tema +\"</td></tr>\";\n" +
                    "              }\n" +
                    "            }\n" +
                    "\n" +                
                    "             for (var k1=0; k1< temas.length; k1++) {\n" +
                    "              temas[k1].color = porcentajeNivelExigencia (temas[k1].porcentajeLogrado, porcentajeExigenciaMin, porcentajeExigenciaMax);\n" +
                    "              if(temas[k1].color == \"yellow\" || temas[k1].color == \"red\" ) {\n" +
                    "                strTemasFallados = strTemasFallados + temas[k1].tema + \" \";\n" +
                    "              }\n" +
                    "              if(temas[k1].puntajeObtenido == 0) {\n" +
                    "                strTemasFallados = strTemasFallados + temas[k].tema + \" \";\n" +
                    "              }\n" +
                    "            }" + 
                    "minimo=porcentajeMinimo(temas);\n " +
                    "var evalSuccess1 = pipwerks.SCORM.init();"+
                    "var evalSuccess2 = pipwerks.SCORM.set(\"cmi.score.min\",1);\n" +
                    "var evalSuccess3 = pipwerks.SCORM.set(\"cmi.score.max\",100);\n" +
                    "var evalSuccess4 = pipwerks.SCORM.set(\"cmi.score.raw\", minimo);\n"+
                    "var evalSuccess7 = pipwerks.SCORM.SetCompletionStatus(\"completed\"); "+
                    "var evalSuccess5 = pipwerks.SCORM.save(); \n" +
                    "var evalSuccess6 = pipwerks.SCORM.quit();"+
                    "            _(\"evaluacion_status\").innerHTML = \"Evaluación completa\";\n" +
                    "\n" +
                    "            \n" +
                    "            if (puntajeTotalOAObtenido == 0) {\n" +
                    "              \n" +
                    "              evaluacion.innerHTML = \"<div class=\\\"left-2columnas\\\"><p style=\\\"color: #444; text-align: center\\\"><strong>Nombre del tema</strong></p><div style=\\\"width:530px; height:280px; overflow:auto;\\\"><table class=\\\"table table-striped table-bordered table-condensed\\\">\"+str+\"</table></div><p></p><p>Puntaje total obtenido de este objeto de aprendizaje: \"+ puntajeTotalOAObtenido + \" de \" + puntajeTotalOA+ \"&nbsp;&nbsp;&nbsp;<button class=\\\"btn btn-primary\\\"  onclick='mostrarSolucion(0)'>Ver soluciones</button></p>\";" +
                    "\n" +
                    "mensaje=\"<p style=\\\"text-align: center\\\"><strong>Te recomendamos ver el objeto de aprendizaje de nuevo</strong></p>\";"+
                    "            } else if (puntajeTotalOAObtenido == puntajeTotalOA) {\n" +
                    "              \n" +
                    "              evaluacion.innerHTML = \"<div class=\\\"left-2columnas\\\"><p style=\\\"color: #444; text-align: center\\\"><strong>Nombre del tema</strong></p><div style=\\\"width:530px; height:280px; overflow:auto;\\\"><table class=\\\"table table-striped table-bordered table-condensed\\\">\"+str+\"</table></div><p></p><p>Puntaje total obtenido de este objeto de aprendizaje: \"+ puntajeTotalOAObtenido + \" de \" + puntajeTotalOA+ \"&nbsp;&nbsp;&nbsp;<button class=\\\"btn btn-primary\\\"  onclick='mostrarSolucion(0)'>Ver soluciones</button></p>\";" +
                    "\n" +
                    "mensaje=\"<p style=\\\"text-align: center\\\"><strong>¡Felicidades, has aprobado este objeto de aprendizaje!</strong></p>\";"+
                    "            } else {\n" +
                    "              \n" +
                    "              evaluacion.innerHTML =\"<div class=\\\"left-2columnas\\\"><p style=\\\"color: #444; text-align: center\\\"><strong>Nombre del tema</strong></p><div style=\\\"width:530px; height:280px; overflow:auto;\\\"><table class=\\\"table table-striped table-bordered table-condensed\\\">\"+str+\"</table></div><p></p><p>Puntaje total obtenido de este objeto de aprendizaje: \"+ puntajeTotalOAObtenido + \" de \" + puntajeTotalOA+ \"&nbsp;&nbsp;&nbsp;<button class=\\\"btn btn-primary\\\"  onclick='mostrarSolucion(0)'>Ver soluciones</button></p>\";" +
                    "\n" +
                    "mensaje=\"<p style=\\\"text-align: center\\\"><strong>Deberias repasar estos temas: \"+strTemasFallados+\"</strong></p>\";"+
                    "            }\n" +
                    "\n" +
                    "\n" +
                    "evaluacion.innerHTML+=\"<div class=\\\"right-2columnas\\\" ><div id=\\\"divForGraph\\\" style=\\\"width:100%; height:100%;\\\"></div>\"+mensaje+\"</div>\";"+
                    "grafico();\n" +
                    "posicion = 0;\n" +
                    "correcta = 0;\n" +
                    "return false;\n" +
                    "}\n" +
                    "pregunta = preguntas[posicion].enunciado;\n" +
                    "\n" +
                    "_(\"evaluacion_status\").innerHTML = \"Preguntas \"+(posicion+1)+\" de \"+preguntas.length;\n" +
                    "evaluacion.innerHTML = \"<h3>\"+pregunta+\"</h3>\";\n" +
                    "\n" +
                    "var rangoOpciones =\"ABCDEFG\";\n" +
                    "var rangoArray    = rangoOpciones.split('');\n" +
                    "\n" +
                    "for (var c1=0; c1<preguntas[posicion].alternativas.length; c1++) {\n" +
                    "var alternativa = preguntas[posicion].alternativas[c1].pregunta;\n" +
                    "evaluacion.innerHTML += \"<input type='radio' name='opciones' value=\"+rangoArray[c1]+ \" onclick='mostrar()'> \"+alternativa+\"<br>\";\n" +
                    "}\n" +
                    "evaluacion.innerHTML += \"<br><button class=\\\"btn btn-default btnEnviar\\\" disabled onclick='evaluar()'>Enviar respuesta</button>\";\n" +
                    "}";
        
        templateFuncionesFijas2 = "function evaluar() {\n" +
                                "				opciones = document.getElementsByName(\"opciones\");\n" +
                                "				for(var i=0; i<opciones.length; i++){\n" +
                                "					if(opciones[i].checked){\n" +
                                "						seleccion = opciones[i].value;\n" +
                                "					}\n" +
                                "				}\n" +
                                "\n" +
                                "        // Algoritmo de evaluacion\n" +
                                "        // Pregunta respondida de forma correcta\n" +
                                "				if(seleccion == preguntas[posicion].solucion) {\n" +
                                "					correcta++;\n" +
                                "					// Si esta buena, le sumo +1 al puntaje total de cada tema y al puntaje obtenido por cada tema\n" +
                                "					for (var i = 0; i<temas.length; i++) {\n" +
                                "						for(var j=0; j<preguntas[posicion].alternativas.length; j++){\n" +
                                "							if (temas[i].tema == preguntas[posicion].alternativas[j].tema) {\n" +
                                "                temas[i].puntaje      = temas[i].puntaje + 1;\n" +
                                "                temas[i].puntajeTotal = temas[i].puntajeTotal + 1;\n" +
                                "							}\n" +
                                "						}\n" +
                                "					}\n" +
                                "				} // fin if\n" +
                                "\n" +
                                "        // Pregunta respondida de forma incorrecta\n" +
                                "				else {\n" +
                                "					// Si esta mala, le sumo +1 al puntaje total de la que selecciono\n" +
                                "          // Busco los temas\n" +
                                "					for (var i = 0; i<temas.length; i++) { \n" +
                                "            for (var j=0; j<preguntas[posicion].alternativas.length; j++){\n" +
                                "							if (seleccion == preguntas[posicion].alternativas[j].alternativa) {\n" +
                                "								if (temas[i].tema == preguntas[posicion].alternativas[j].tema) {\n" +
                                "                   temas[i].puntajeTotal = temas[i].puntajeTotal + 1;\n" +
                                "                } \n" +
                                "							}\n" +
                                "						}\n" +
                                "					} // fin for\n" +
                                "\n" +
                                "            //Si esta mala, le sumo +1 al puntaje total de la alternativa correcta\n" +
                                "            for (var i=0; i<preguntas[posicion].alternativas.length; i++){\n" +
                                "              if (preguntas[posicion].solucion == preguntas[posicion].alternativas[i].alternativa){\n" +
                                "                //recorro lista de temas para restar al tema correspondiente\n" +
                                "                for(var j=0; j<temas.length; j++){\n" +
                                "                  if (preguntas[posicion].alternativas[i].tema == temas[j].tema){\n" +
                                "                    temas[j].puntajeTotal = temas[j].puntajeTotal + 1;                  \n" +
                                "                  }\n" +
                                "                }\n" +
                                "              }//fin if\n" +
                                "            }//fin for\n" +
                                "\n" +
                                "					} // fin else\n" +
                                "					\n" +
                                "				posicion++;\n" +
                                "				mostrarPregunta();\n" +
                                "			} // fin funcion\n" +
                                "			\n" ;
                
                
        templateSolucionario = write_solutionHtml();        
        total = templateCss + templateEvaluacion1 + tempEval + randomPila + templateRescatarTemas+ templateFuncionesFijas+ templateFuncionesFijas2 + templateSolucionario;
        return total;        
    }
    
    public String write_solutionHtml(){
        String mostrarSolucion="";
        
        mostrarSolucion+="function mostrarSolucion(posicion) {\n" +
                    "         evaluacion        = _(\"evaluacion\");\n" +
                    "         var str           = '';\n" +
                    "         var strTemas      = '';\n" +
                    "         var strPorcentaje = '';\n" +
                    "         puntajeTotalOA         = 0;\n" +
                    "         puntajeTotalOAObtenido = 0; var minimo=0\n" +
                    "var strTemasFallados = '';"+
                    "\n" +
                    "          if(posicion >= preguntas.length){\n" +
                    "\n" +
                    "            temas=cleanup(temas,'tema'); //borrar temas repetidos\n" +
                    "\n" +
                    "            for (var c3=0; c3<temas.length; c3++) {\n" +
                    "              puntajeTotalOA         = puntajeTotalOA + temas[c3].puntajeTotal;\n" +
                    "              puntajeTotalOAObtenido = puntajeTotalOAObtenido + temas[c3].puntaje;\n" +
                    "            }\n" +
                    "            \n" +
                    "            if (puntajeTotalOAObtenido < 0) {\n" +
                    "              puntajeTotalOAObtenido = 0;\n" +
                    "            }\n" +
                    "\n" +
                    "            for (var l=0; l<temas.length; l++) {\n" +
                    "              temas[l].porcentajeLogrado = porcentaje(temas[l].puntaje, temas[l].puntajeTotal);\n" +
                    "            }\n" +
                    "\n" +
                    "            for (var j=0; j<temas.length; j++) { \n" +
                    "              if (temas[j].puntaje <=0) { \n" +
                    "                temas[j].puntaje=0;\n" +
                    "              }\n" +
                    "            }\n" +
                    "\n" +
                    "            for (var n=0; n<temas.length; n++) {\n" +
                    "              if (temas[n].puntajeTotal == 0) {\n" +
                    "                temas.splice(n,1);\n" +
                    "              }\n" +
                    "\n" +
                    "            } \n" +
                    "            \n" +
                    "            for(var i=0; i<temas.length ; i++){\n" +
                    "              \n" +
                    "              if (temas[i].puntaje == 0 && temas[i].puntajeTotal == 0) {\n" +
                    "                str = str\n" +
                    "              } else {\n" +
                    "                   str = str + \"<tr><td> T\"+(i+1)+\": \"+temas[i].tema +\"</td></tr>\";" +
                    "              }\n" +
                    "            }\n" +
                    "\n" +
                    "             for (var k1=0; k1< temas.length; k1++) {\n" +
                    "              temas[k1].color = porcentajeNivelExigencia (temas[k1].porcentajeLogrado, porcentajeExigenciaMin, porcentajeExigenciaMax);\n" +
                    "              if(temas[k1].color == \"yellow\" || temas[k1].color == \"red\" ) {\n" +
                    "                strTemasFallados = strTemasFallados + temas[k1].tema + \" \";\n" +
                    "              }\n" +
                    "              if(temas[k1].puntajeObtenido == 0) {\n" +
                    "                strTemasFallados = strTemasFallados + temas[k].tema + \" \";\n" +
                    "              }\n" +
                    "            }" + "minimo=porcentajeMinimo(temas); \n" +
                    "\n" +
                    "            _(\"evaluacion_status\").innerHTML = \"Evaluación completa\";\n" +
                    "\n" +
                    "            \n" +
                    "            if (puntajeTotalOAObtenido == 0) {\n" +
                    "              \n" +
                    "              evaluacion.innerHTML = \"<div class=\\\"left-2columnas\\\"><p style=\\\"color: #444; text-align: center\\\"><strong>Nombre del tema</strong></p><div style=\\\"width:530px; height:280px; overflow:auto;\\\"><table class=\\\"table table-striped table-bordered table-condensed\\\">\"+str+\"</table></div><p></p><p>Puntaje total obtenido de este objeto de aprendizaje: \"+ puntajeTotalOAObtenido + \" de \" + puntajeTotalOA+ \"&nbsp;&nbsp;&nbsp;<button class=\\\"btn btn-primary\\\"  onclick='mostrarSolucion(0)'>Ver soluciones</button></p>\";" +
                    "\n" +
                "              mensaje=\"<p style=\\\"text-align: center\\\"><strong>Te recomendamos ver el objeto de aprendizaje de nuevo</strong></p>\";"+
                    "            } else if (puntajeTotalOAObtenido == puntajeTotalOA) {\n" +
                    "              \n" +
                    "              evaluacion.innerHTML = \"<div class=\\\"left-2columnas\\\"><p style=\\\"color: #444; text-align: center\\\"><strong>Nombre del tema</strong></p><div style=\\\"width:530px; height:280px; overflow:auto;\\\"><table class=\\\"table table-striped table-bordered table-condensed\\\">\"+str+\"</table></div><p></p><p>Puntaje total obtenido de este objeto de aprendizaje: \"+ puntajeTotalOAObtenido + \" de \" + puntajeTotalOA+ \"&nbsp;&nbsp;&nbsp;<button class=\\\"btn btn-primary\\\"  onclick='mostrarSolucion(0)'>Ver soluciones</button></p>\";" +
                    "\n" +
                "                mensaje=\"<p style=\\\"text-align: center\\\"><strong>¡Felicidades, has aprobado este objeto de aprendizaje!</strong></p>\";"+
                    "            } else {\n" +
                    "              \n" +
                    "              evaluacion.innerHTML =\"<div class=\\\"left-2columnas\\\"><p style=\\\"color: #444; text-align: center\\\"><strong>Nombre del tema</strong></p><div style=\\\"width:530px; height:280px; overflow:auto;\\\"><table class=\\\"table table-striped table-bordered table-condensed\\\">\"+str+\"</table></div><p></p><p>Puntaje total obtenido de este objeto de aprendizaje: \"+ puntajeTotalOAObtenido + \" de \" + puntajeTotalOA+ \"&nbsp;&nbsp;&nbsp;<button class=\\\"btn btn-primary\\\"  onclick='mostrarSolucion(0)'>Ver soluciones</button></p>\";" +
                    "\n" +
                "                mensaje=\"<p style=\\\"text-align: center\\\"><strong>Deberias repasar estos temas: \"+strTemasFallados+\"</strong></p>\";"+
                    "            }\n" +
                "            evaluacion.innerHTML+=\"<div class=\\\"right-2columnas\\\" ><div id=\\\"divForGraph\\\" style=\\\"width:100%; height:100%;\\\"></div>\"+mensaje+\"</div>\";"+
                    "            grafico();\n" +
                    "            posicion = 0;\n" +
                    "            correcta = 0;\n" +
                    "            return false;\n" +
                    "          }\n" +
                    "          \n" +
                    "          var valor =[];\n" +
                    "          pregunta  =preguntas[posicion].enunciado;\n" +
                    "          sol       = preguntas[posicion].solucionario;\n" +
                    "          var cantidad_alternativas=preguntas[posicion].alternativas.length;\n" +
                    "          \n" +
                    "          for(var j=0;j<cantidad_alternativas;j++){\n" +
                    "            if(preguntas[posicion].solucion==preguntas[posicion].alternativas[j].alternativa){\n" +
                    "                  valor[j]=\" <strong style=\\\"color:green\\\">\"+preguntas[posicion].alternativas[j].alternativa+\") \"+preguntas[posicion].alternativas[j].pregunta+\"</strong><br>\";\n" +
                    "            } else{\n" +
                    "              valor[j]=\"<b style=\\\"color:grey\\\">\"+preguntas[posicion].alternativas[j].alternativa+\") \"+preguntas[posicion].alternativas[j].pregunta+\"</b><br>\";\n" +
                    "            }\n" +
                    "          \n" +
                    "          }\n" +
                    "          \n" +
                    "          _(\"evaluacion_status\").innerHTML = \"Preguntas \"+(posicion+1)+\" de \"+preguntas.length;\n" +
                    "          \n" +
                    "          evaluacion.innerHTML = \"<h3 style=\\\"color:#514D4D\\\">\"+pregunta+\"</h3>\";" +
                    "          evaluacion.innerHTML += \"<div class=\\\"right-2columnas\\\" align=\\\"center\\\"><p>\"+sol+\"</p></div>\";\n" +
                    "          \n" +
                    "          for(var j=0;j<cantidad_alternativas;j++) {\n" +
                    "            evaluacion.innerHTML += valor[j];\n" +
                    "          }\n" +
                    "\n" +
                    "          if(posicion==preguntas.length-1) {\n" +
                    "            evaluacion.innerHTML += \"<br><button class=\\\"btn btn-default\\\"onclick='mostrarSolucion(\"+(posicion+1)+\")'>Ir a estadísticas</button>\";\n" +
                    "          } else {\n" +
                    "            evaluacion.innerHTML += \"<br><button class=\\\"btn btn-default\\\"onclick='mostrarSolucion(\"+(posicion+1)+\")'>Siguiente</button>\";\n" +
                    "          }\n" +
                    "      }function grafico() {\n" +
                    "        arrayOfData = [];\n" +
                    "        for (var g=0; g<temas.length; g++) {\n" +
                    "          arrayOfData.push([temas[g].porcentajeLogrado, \"T\"+(g+1), temas[g].color]);\n" +
                    "        }\n" +
                    "        $('#divForGraph').jqBarGraph({ data: arrayOfData, title: \"<strong>Porcentaje logrado por tema (%)</strong>\" });\n" +
                    "      }\n" +
                    "\n" +
                    "      function mostrar() {  \n" +
                    "        $(\".btnEnviar\").attr(\"disabled\",false);\n" +
                    "      }"+
                    "window.addEventListener(\"load\", mostrarPregunta, false);\n" +
                    "</script>"+
                    "<script>\n" +
                    "$(function(){\n" +
                    " $(\"#slide-evaluacion\").bind('deck.becameCurrent', function(ev, direction) {\n" +
                    "  console.log(\"estoy en la slide evaluacion\");  \n" +
                    "  $( \"#slide-1\" ).remove();\n" +
                    "\n" +
                    "  });\n" +
                    "\n" +
                    "});\n" +
                    "</script>";
        
        return mostrarSolucion;
    }
    
    /**
     * Funcion que realiza la traduccion a html de una pila de ejemplos que se quieran agregar a un OA.
     * @param list_examples arreglo de objetos de la clase Example, que representa a una pila de ejemplos.
     * @param OAName Nombre del objeto (usado para la descarga de imagenes)
     * @param OAPath Nombre de la ruta donde se ubica el objeto (para la descarga de imagenes)
     * @param design Tipo de template del OA
     * @param lim_line Limite maximo de caracteres por linea segun el bloque y slide encontrado (usado para el texto manuscrito)
     * @param numberSlide Posicion de la slide, donde se va a encontrar la animacion, en el arreglo de slides del objeto
     * @param numberBlock Posicion del bloque, donde se va a encontrar la animacion, en el arreglo de bloques de la slide correspondiente
     * @param numberIdea Posicion de la idea, donde se va a encontrar la animacion, en el arreglo de ideas del bloque correspondiente
     * @return 
     */
    public String write_examples(List<Example> list_examples, String OAName, String OAPath, String design, int lim_line, int numberSlide, int numberBlock, int numberIdea){
        
        String examples = "<script>\nvar ejemplos = new Array(";
        ArrayList<String> trozos;
        
        for( int i = 0 ; i < list_examples.size(); i++){
                                    
            if(i > 0)
                examples += ", ";
            
            examples += "\"";
            
            for(int j = 0; j < list_examples.get(i).getTextContent().size(); j++){

                examples += " ";

                String content = list_examples.get(i).getTextContent().get(j).getContent();

                switch ( list_examples.get(i).getTextContent().get(j).getType()){

                    case "normal":
                        content = content.replaceAll("\n","\\\\n");
                        examples += "<p>"+ write_text(content, design) +"</p>";
                        break;
                    case "codigo":
                        content = content.replaceAll("\n","\\\\n");
                        examples += "<pre class=\\\"brush: js\\\">"+ content +"</pre>";
                        break;
                    case "manuscrito":
                        content = content.replaceAll("\n","\\\\n");
                        trozos=trozarCadena(content, lim_line);
                        examples += write_hand(trozos, numberSlide, numberBlock, numberIdea, i);
                        break;
                                    
                    default:
                        break;
                }
            }

            for(int j = 0; j < list_examples.get(i).getMediaContent().size(); j++){
                                
                examples += write_media(list_examples.get(i).getMediaContent().get(j), OAName, OAPath, true);
            }
            
            examples += "\"";
        }
        examples += ")</script>";
        examples += "<script language=javascript>ej_aleatorio()</script>";
        
        return examples;
    }    
    /**
     * Funcion que realiza la traduccion a html de los recursos multimedias que se ingresen al OA
     * @param media Objeto del tipo media que representa al recurso a ser ingresado
     * @param OAName Nombre del objeto (usado para la descarga de imagenes)
     * @param OAPath Nombre de la ruta donde se ubica el objeto (para la descarga de imagenes)
     * @param isExample Booleano que indica si este recurso es ingresado como un ejemplo o no.
     * @return Cadena con el codigo html que representa al recurso multimedia en el OA
     */
    public String write_media(Media media, String OAName, String OAPath, boolean isExample){
        
        String code_media = "";
        String mediaName = "";
        String char_example = "";
        
        if(isExample)
            char_example = "\\";
        
        try{
            mediaName = ResourcesDownloader.DownloadFromURLAsMozilla(media.getContent(), OAPath, ".img");            
        }catch (IOException ex) {
            
            this.translateError = "NO_MEDIA";
        }        
        code_media += "<img src="+char_example+"\"resources/medias/"+ OAName+ "/"+mediaName+ char_example +"\">";
        return code_media;
    }
    /**
     * Funcion que realiza la traduccion a html de la representacion de un bloque
     * @param block Objeto del tipo Block que va a ser traducido
     * @param numberSlide Posicion de la Scene donde se encuentra el Block, en el arreglo de Scene de un OA
     * @param numberBlock Posicion del Block, en el arreglo de Block de la Scene
     * @param template Tipo de template que se esta usando en el OA
     * @param OAName Nombre del objeto (usado para la descarga de imagenes)
     * @param OAPath Nombre de la ruta donde se ubica el objeto (para la descarga de imagenes)
     * @param lim_line Numero maximo de caracteres que se permiten por linea dentro del bloque
     * @return Cadena de caracteres con el codigo html que corresponde a un bloque
     */
    public String write_block(Block block, int numberSlide, int numberBlock, String template, String OAName, String OAPath, int lim_line){
                        
        String codeHtml = "";                
        ArrayList<String> trozos;
                        
        for(int i = 0; i < block.getIdeas().size(); i++){
                                        
            Idea idea = block.getIdeas().get(i);            
                                                
            String start_label = "<section class=\"slide-" + numberSlide +"-" + numberBlock+"-"+ i +"\" style=\"visibility:hidden\">";
            codeHtml += start_label;            
            
            for(int j = 0; j < idea.getText().size(); j++){
                                
                switch(idea.getText().get(j).getType()){

                    case "normal":
                        codeHtml += "<p>";
                        codeHtml += write_text(idea.getText().get(j).getContent(), template);
                        codeHtml += "</p>";
                        break;
                        
                    case "manuscrito":                        
                        trozos=trozarCadena(idea.getText().get(j).getContent(), lim_line);
                        codeHtml += write_hand(trozos, numberSlide, numberBlock, i, -1);                        
                        break;
                        
                    case "codigo":
                        codeHtml += "<pre class=\"brush: js\">";
                        codeHtml += idea.getText().get(j).getContent();
                        codeHtml += "</pre>";
                        break;                    
                        
                    default:
                        break;
                };
            }
            
            if(!idea.getSubIdea().isEmpty()){
                codeHtml += write_subIdea(idea, numberSlide, numberBlock, i, OAName, OAPath);
            }                        
            if( !idea.getExample().isEmpty() ){
                
                codeHtml += write_examples(idea.getExample(), OAName, OAPath, template, lim_line, numberSlide, numberBlock, i);
            }                        
            
            for(int j = 0; j < idea.getMedia().size(); j++){
                codeHtml += write_media(idea.getMedia().get(j), OAName, OAPath, false);
                
            }
            
            if(!idea.getVoice().isEmpty()){
                String audioFileName;            
                try {
                    audioFileName = ResourcesDownloader.downloadFromGoogleTTS(idea.getVoice(), OAPath);

                    codeHtml += ("<audio id=\"audio-" + numberSlide + "-" + numberBlock + "-" + i + "\" ");
                    codeHtml += ("source src=\"resources/medias/" + OAName+ "/"+audioFileName +"\" ");
                    codeHtml += ("type=\"audio/ogg\"></audio>");

                } catch (IOException ex) {
                    this.translateError = "NO_AUDIO";
                }
            }
            codeHtml += "</section>";
        }
        return codeHtml;
    }
    /**
     * Funcion que rescata dia, mes y año de una objeto del tipo date
     * @param date Objeto del tipo Date con la fecha que se desea simplificar
     * @return Una cadena con los datos del dia de la semana, dia , mes y año. Ejemplo: Mon 10 Jun 2015
     */
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
    /**
     * Funcion que realiza la traduccion a html de la portada de un OA
     * @param object Objeto que contiene al objeto de aprendizaje con todo su contenido
     * @return Cadena de caracteres con el cogido html de la portada de un objeto
     */
    public String write_titleHtml(LearningObject object){
                
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
    /**
     * Funcion que realiza la traduccion a html de los section que llaman a los scripts correspondientes para dar el flujo dentro del OA
     * @param scene Objeto del tipo Scene que representa a la escena donde se escriben los section
     * @param number_slide Posicion de la Scene en el arreglo que tiene a todas las Scene de un OA     
     * @return Cadena de caracteres con el codigo html que representa a los section que dan la animacion al OA
     */
    public String write_animationHtml(Scene scene, int number_slide){
        
        String codeHtml = "";
        ArrayList<Idea> orderedIdea = new ArrayList<>();
        
        ArrayList<ArrayList<Idea>> blockIdea = new ArrayList<>();
        
        for(int i=0 ; i < scene.getBlocks().size(); i++){
                
            blockIdea.add(new ArrayList<Idea>());
                
            for(int j=0 ; j < scene.getBlocks().get(i).getIdeas().size(); j++){
                
                orderedIdea.add(scene.getBlocks().get(i).getIdeas().get(j));
                blockIdea.get(i).add(scene.getBlocks().get(i).getIdeas().get(j));
            }
        }
        
        Collections.sort(orderedIdea);
        int end_orden = -1;
        
        for (int i = 0; i < orderedIdea.size(); i++){
            
            //int number_idea = -1, number_block = -1;
            
            /*for(int j = 0; j < blockIdea.size(); j++){
                                
                int aux = blockIdea.get(j).indexOf(orderedIdea.get(i));

                if( aux != -1){
                    number_idea= aux;
                    number_block= j;
                    break;
                }
            }*/
            
            if(end_orden != orderedIdea.get(i).getAparitionOrder()){
                codeHtml += "<div class=slide id=\"show-slide-"+ number_slide + "-" + orderedIdea.get(i).getAparitionOrder() + "\"></div>\n";
                for(int j = 0 ; j < orderedIdea.get(i).getSubIdea().size(); j++){
                    for(int k = 0 ; k < orderedIdea.get(i).getSubIdea().get(j).getSubIdeaContent().size();k++){
                        codeHtml += "<div class=slide id=\"show-slide-"+ number_slide + "-" + orderedIdea.get(i).getAparitionOrder()+ 
                                    "-"+ j + "-" + k +"\"></div>";
                    }
                }
                end_orden = orderedIdea.get(i).getAparitionOrder();
            }
        }
        return codeHtml;
    }      
    
    /**
     * Funcion que realiza la traduccion a html de una Escena completa de un OA
     * @param scene Contenido de una Scene
     * @param nro_slide Posicion de la scene dentro del OA
     * @param OAPath Cadena de caracteres con el nombre de la ruta donde se almacena los recursos de un OA (Para la descarga de recursos)
     * @param OAName Cadena de caracteres con el nombre con el que se guarda el OA (Para la descarga de recursos)
     * @param template Tipo de template que se esta usando en el OA
     * @return Cadena de caracteres con el codigo html que representa a una scene
     */
    public String write_slideHtml(Scene scene, int nro_slide, String OAPath, String OAName, String template){        
        
        int tam_fila = 110;
        int tam_2Col = 45;
        int tam_3Col = 30;        
        
        String codeHtml = "";        
        
        codeHtml += "<section class=\"slide\" id=\"slide-"+nro_slide+"\">\n" +
                            "<h2>"+scene.getTitle()+"</h2>\n";          
        
        switch (scene.getDesign()){
            case "1Col":
                //escribir contenido del primer bloque                                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, template, OAName, OAPath, tam_fila);
                break;
                
            case "1Fil2Col":
                //bloque 1
                codeHtml += "<div class=\"up-2columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, template, OAName, OAPath, tam_fila); 
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"left-2columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, template, OAName, OAPath, tam_2Col);
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"right-2columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, template, OAName, OAPath, tam_2Col);
                codeHtml += "</div>";
                        
                break;
                
            case "2Col":
                //escribir contenido del primer bloque
                codeHtml += "<div class=\"left-2columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, template, OAName, OAPath, tam_2Col);                                                
                codeHtml += "</div>";
                
                //escribir contenido del segundo bloque
                codeHtml += "<div class=\"right-2columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, template , OAName, OAPath, tam_2Col);
                codeHtml += "</div>";
                
                break;
                
            case "3Col":
                
                //bloque 1
                codeHtml += "<div class=\"left-3columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, template , OAName, OAPath, tam_3Col);                                            
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"center-3columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, template , OAName, OAPath, tam_3Col);
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"right-3columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, template, OAName, OAPath, tam_3Col);
                codeHtml += "</div>";
                
                break;
                
            case "1Fil3Col":
                //bloque 1
                codeHtml += "<div class=\"up-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, template , OAName, OAPath, tam_fila);
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"left-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, template , OAName, OAPath, tam_3Col);                                           
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"center-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, template, OAName, OAPath, tam_3Col);                                          
                codeHtml += "</div>";
                
                //bloque 4
                codeHtml += "<div class=\"right-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(3),nro_slide, 3, template, OAName, OAPath, tam_3Col);
                codeHtml += "</div>";
                
                break;
                
            case "2Fil2Col":
                
                //bloque 1
                codeHtml += "<div class=\"up-2columnas-1up-1down\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, template , OAName, OAPath, tam_fila);
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"left-2columnas-1up-1down\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, template , OAName, OAPath, tam_fila);                                           
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"right-2columnas-1up-1down\">";
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, template, OAName, OAPath, tam_2Col);  
                codeHtml += "</div>";
                
                //bloque 4
                codeHtml += "<div class=\"down-2columnas-1up-1down\">";                
                codeHtml += write_block(scene.getBlocks().get(3),nro_slide, 3, template, OAName, OAPath, tam_2Col);                                            
                codeHtml += "</div>";
                
                break;
            case "2Col1Fil":
                
                //bloque 1
                codeHtml += "<div class=\"left-2columnas-1down\">";
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, template , OAName, OAPath, tam_2Col);                                                
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"right-2columnas-1down \">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, template , OAName, OAPath, tam_2Col);
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"down-2columnas-1down \">";
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, template, OAName, OAPath, tam_fila);                                          
                codeHtml += "</div>";
                
                break;
                
            default:
                break;            
        }        
        codeHtml += write_animationHtml(scene, nro_slide);
        codeHtml += "</section>";        
                
        return codeHtml;
    }
    
    /**
     * Escribe todas las escenas o "slides" del objeto en la página.
     * @param object objeto de aprendizaje construido por la clase de reader.     
     * @param OAName Nombre con que se descarga el OA
     * @return string con todas las slides incorporadas.
     */
    public String write_contentHtml(LearningObject object, String OAName){

        /* Obtener la ruta donde guardar el OA*/
        String OAPath = ResourcesDownloader.generatePathForOA(); // Wildfly/standalone/deplayments/...ear/...web.war
        
        OAPath = OAPath.concat("/resources/medias/" + OAName +"/");

        //Codigo de las escenas
        String codeHtml = "";        
        codeHtml += write_titleHtml(object);
        
        for (int i = 0; i < object.getContent().size(); i++){
                        
            codeHtml += write_slideHtml(object.getContent().get(i), i+1, OAPath, OAName, object.getTemplate());                                    
        }
        
        //write_slideEvaluaciones();
        
         // Se incluyen slide de evaluaciones
        
        // Si hay evaluaciones se inserta el codigo html de evaluaciones
        if (!object.getQuizSet().isEmpty()){
            codeHtml += "<section id=\"slide-evaluacion\" class=\"slide\">\n" +
                "<h2 id=\"evaluacion_status\"></h2>\n" +
                "<div id=\"evaluacion\"></div>\n" +
                "</section>\n";
        }
        
        return codeHtml;
    }     
    /**
     * Funcion que realiza la traduccion a html de la seccion de encuesta del OA
     * @param object Objeto de la clase LearningObject que representa al Objeto a ser escrito con todo su contenido
     * @return Cadena con el codigo html de la seccion de encuestas del OA
     */
    public String write_feedbackHtml(LearningObject object){
        
        String codeHtml = "";
                
            if(!object.getFeedback().getLink().isEmpty()){
                
                if(object.getFeedback().isLinkValid()){
                    codeHtml+=  "<section class=\"slide\" style=\"background-color: #EDFCD0\">\n" +
                                "   <div id=\"frase\" align=\"center\"><br><br><br><br><br>\n" +
                                "       <h3 >Por favor, responde la siguiente encuesta para seguir mejorando el objeto de aprendizaje que acabas de ver.</h3>\n" +
                                "       <div id=\"boton\" style=\"margin-top: 100px;\">\n" +
                                "              <button class=\"btn btn-lg btn-default\" type=\"button\" onclick=\"ShowIframe('#encuesta','#boton','#frase','";
                    codeHtml += object.getFeedback().getLink();
                    codeHtml += "')\" >\n" +"<strong>Responder encuesta</strong>\n </button>\n </div>\n </div>\n <div id=\"encuesta\">\n </div>\n </section>";
                }
                else
                    this.translateError = "NO_FORM";
            }        
        return codeHtml;
    }
        
    /**
     * Escribe liberías necesarias para utilizar el framework en el que está
     * construido el objeto.
     * @param object objeto de aprendizaje construido por la clase de reader.     
     * @return string con las librerías incorporadas.
     */
    public String write_librsHtml(LearningObject object){

        String codeHtml =   "\n<div aria-role=\"navigation\">\n" +
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

        return codeHtml;
    }   
    /**
     * Funcion que determina la posicion donde comienza un texto en la animacion mano-texto, segun el bloque y slide en que se encuentre
     * @param design Tipo de diseño de la slide
     * @param numberBLock Posicion del bloque en el arreglo de bloques de la slide correspondiente
     * @return cadena que representa el valor de la posicion donde comienza la animacion
     */
    public String write_confHand(String design, int numberBLock){
        
        String left = "";
        
        switch (design){
            case "1Col":
                //escribir contenido del primer bloque 
                left="-80";
                break;

            case "1Fil2Col":
                //bloque 1
                if(numberBLock==0){
                  left="-80";
                }
                //bloque 2
                else if(numberBLock==1){
                    left="-80";
                }
                //bloque 3
                else{
                    left="+617";
                }

                break;

            case "2Col":
                //bloque 1

                if(numberBLock==0){
                  left="-80";
                }
                else{
                   left="+617";
                }

                break;

            case "3Col":
                //bloque 1
                if(numberBLock==0){
                  left="-80";
                }
                //bloque 2
                else if(numberBLock==1){
                    left="+335";    //ARREGLAR, HAY QUE SABER CUANTO VALE PARA EL CENTRO
                }
                //bloque 3
                else{
                    left="+760";
                }

                break;

            case "1Fil3Col":
                //bloque 1
                if(numberBLock==0){
                  left="-80";
                }
                //bloque 2
                else if(numberBLock==1){
                    left="-80";    //ARREGLAR, HAY QUE SABER CUANTO VALE PARA EL CENTRO
                }
                //bloque 3
                else if(numberBLock==2){
                    left="+335";    //ARREGLAR, HAY QUE SABER CUANTO VALE PARA EL CENTRO
                }
                else{
                    left="+760"; 
                }
                break;

            case "2Fil2Col":

                //bloque 1
                if(numberBLock==0){
                  left="-80";
                }
                //bloque 2
                else if(numberBLock==1){
                    left="-80";   
                }
                //bloque 3
                else if(numberBLock==2){
                    left="+617"; 
                }
                else{
                    left="-80"; 
                }


                break;
            case "2Col1Fil":
               //bloque 1
                if(numberBLock==0){
                  left="-80";
                }
                //bloque 2
                else if(numberBLock==1){
                    left="+617";  
                }
                //bloque 3
                else if(numberBLock==2){
                    left="-80"; 
                }
                else{
                    left="-80"; 
                }

                break;

            default:
                break;            
        }
        return left;
    }
}
