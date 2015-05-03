/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

import javax.ejb.Stateless;
import cl.diinf.objetoAprendizaje.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nacho
 */
@Stateless
public class OA_TranslateHtml implements OA_TranslateHtmlLocal {    
    
    public OA_TranslateHtml(){
    
    }

    public String writeHtml(ObjetoAprendizaje object) throws IOException{
                    
        String codeHtml;
        
        codeHtml = write_headerHtml(object);
        codeHtml = write_slidesHtml(object, codeHtml);
        codeHtml = write_librsHtml(object, codeHtml);
        codeHtml = write_scriptHtml(object, codeHtml);
        codeHtml = write_voiceHtml(object, codeHtml);
        
        return codeHtml;
    }
    
    public String write_headerHtml(ObjetoAprendizaje object){
        //Head del HTML
        String htmlHeader = "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "  <meta charset=\"utf-8\">\n" +
                            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                            "  <meta name=\"viewport\" content=\"width=1024, user-scalable=no\">\n" +
                            "\n" + "  <title>" + object.getTitle() + "</title>"+                                
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/core/deck.core.css\">\n" +"\n" +                                                                
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/goto/deck.goto.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/menu/deck.menu.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/navigation/deck.navigation.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/status/deck.status.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/scale/deck.scale.css\">\n" + "\n" +                                                                
                            "  <script src=\"resources/voice/AJAX_GOOGLE.js\"></script>\n" +
                            "  <script src=\"resources/voice/voice.js\"></script>\n" + "\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/style/swiss.css\">\n" + "\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/transition/horizontal-slide.css\">\n" +"\n" +
                            "  <link rel=\"stylesheet\" media=\"print\" href=\"resources/core/print.css\">\n" +"\n" +                                       
                            "  <script src=\"resources/modernizr.custom.js\"></script>\n" +
                            "</head>\n" +"\n" + "<body>" +
                            "<div class=\"deck-container\">\n";
                
        return htmlHeader;
    }

    public String write_slidesHtml(ObjetoAprendizaje object, String codeHtml){
        //Codigo de las escenas
        Slide scene;
        String string_num;
        String title_scene;
        String texts;
        String finish_scene;            

        for (int i = 0; i < object.getContent().size(); i++){

            scene = object.getContent().get(i);            

            //Escritura del head y titulo de una escena
            string_num = Integer.toString(i+1);
            title_scene =   "<section class=\"slide\" id=\"slide-"+string_num+"\">\n" +
                            "<h2>"+scene.getTitle()+"</h2>\n";

            codeHtml = codeHtml + "\n" + title_scene;

            ArrayList<String> list_exam = new ArrayList<String>();
            Random rand = new Random();

            for(int j = 0; j < scene.getText().size(); j++){

                if( scene.getText().get(j).getType().equals("text") ){

                    if(!list_exam.isEmpty()){
                        int index_exa = rand.nextInt(list_exam.size());
                        texts = "<p>"+list_exam.get(index_exa)+"</p>\n";
                        codeHtml = codeHtml + "\n" + texts;
                        list_exam.clear();
                    }

                    texts = "<p>"+scene.getText().get(j).getContent()+"</p>\n";
                    codeHtml = codeHtml + "\n" + texts;
                }
                else if( scene.getText().get(j).getType().equals("example") ){

                    list_exam.add(scene.getText().get(j).getContent());
                }
                else{
                    codeHtml = codeHtml + "\n" + "<p>ERROR</p>\n";
                }

                if( j+1 >= scene.getText().size() && !list_exam.isEmpty()){                    

                    int index_exa = rand.nextInt(list_exam.size());
                    texts = "<p>"+list_exam.get(index_exa)+"</p>\n";
                    list_exam.clear();
                    codeHtml = codeHtml + "\n" + texts;
                }
            }

            finish_scene = "</section>\n";
            codeHtml = codeHtml + "\n" + finish_scene;
        }
        return codeHtml;
    }

    public String write_librsHtml(ObjetoAprendizaje object, String codeHtml){

        //Importe de las librerias necesarias

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
                            "<script src=\"resources/extensions/deck.events/deck.events.js\"></script>\n";

        //pWriter.append(htmlLibrs);
        codeHtml = codeHtml + "\n" + htmlLibrs;

        return codeHtml;
    }

    public String write_scriptHtml(ObjetoAprendizaje object, String codeHtml){

        String htmlScriptBase = "<script>\n" +
                            "  $(function() {\n" +
                            "    $.deck('.slide');\n" +
                            "  });\n" +
                            "</script>\n";

        codeHtml = codeHtml + "\n" + htmlScriptBase;
        return codeHtml;
    }

    public String write_voiceHtml(ObjetoAprendizaje object, String codeHtml){
        //Procesamiento de la voz por cada escena
        String htmlScriptVoice;            

        for (int i = 0; i < object.getContent().size(); i++){

            Slide scene = object.getContent().get(i);                        
            String string_num = Integer.toString(i+1);                
            String txt_voice = "";

            for( int j = 0; j < scene.getVoice().size(); j++){
                txt_voice = txt_voice + scene.getVoice().get(j);
            }

            htmlScriptVoice = "";

            if(i == 0){

                htmlScriptVoice =   "<script>\n" + "  document.addEventListener(\"TTS_LOADED\", function(event) {\n" +                                    
                                    "    $(\"#slide-"+string_num +"\").trigger('deck.becameCurrent');\n" +
                                    "  });\n" + "</script>";

            }

            htmlScriptVoice =   htmlScriptVoice +
                                "<script>\n" +
                                "  $(\"#slide-"+string_num+"\").bind('deck.becameCurrent', function(ev, direction) {\n" +
                                "    var texto_a_reproducir = '"+txt_voice+"';\n" +
                                "    responsiveVoice.cancel();\n" +
                                "    if(texto_a_reproducir != ''){\n" +
                                "      responsiveVoice.speak(texto_a_reproducir,'Spanish Female');\n" +
                                "    }\n" +
                                "  });\n" +
                                "</script>\n";            

            codeHtml = codeHtml + "\n" + htmlScriptVoice;
        }

        //Fin del HTML
        String htmlEnd = "</body>\n" + "</html>";
        //pWriter.append(htmlEnd);
        codeHtml = codeHtml + "\n" + htmlEnd;

        return codeHtml;
    }                                                               
}