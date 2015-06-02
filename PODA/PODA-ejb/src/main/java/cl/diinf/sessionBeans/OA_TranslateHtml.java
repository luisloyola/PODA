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
        codeHtml += write_scriptHeaderHtml(object);
        //codeHtml += write_scriptevaluaciones(object);
        codeHtml += "</script>\n</head>";        
        codeHtml += write_contentHtml(object, OAName);        
        codeHtml += write_librsHtml(object);
        codeHtml += write_scriptHand(object);
        codeHtml+="</body></html>";
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
                            "  <meta name=\"viewport\" content=\"width=1024, user-scalable=no\">\n" +
                            "  <title>" + object.getTitle() + "</title>"+                                
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/core/deck.core.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/goto/deck.goto.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/menu/deck.menu.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/navigation/deck.navigation.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/status/deck.status.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/scale/deck.scale.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1up.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1down.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/2columnas-1up-1down.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/3columnas.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/3columnas-1up.css\">\n" +
                            "  <link href=\"resources/extensions/syntaxhighlighter/shCore.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                            "  <link href=\"resources/extensions/syntaxhighlighter/shThemeDefault.css\" rel=\"stylesheet\" type=\"text/css\" />" +
                            "<link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/style/swiss.css\">"+
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/style/" + templateHtml +"\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/transition/horizontal-slide.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"print\" href=\"resources/core/print.css\">\n" +
                            "  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/css/font-manuscrita.css\">\n" +
                            "<script src=\"resources/modernizr.custom.js\"></script>"+
                            "  \n" +
                            "<script src=\"resources/extensions/deck.syntaxhighlighter.js\"></script>\n"+
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shCore.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shBrushPython.js\"></script>\n" +
                            "  <script type=\"text/javascript\" src=\"resources/extensions/syntaxhighlighter/shBrushJScript.js\"></script>\n" +
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
                            "  <script src=\"resources/textController.js\"></script>\n" +
                            "\n" +
                            "\n" +
                            "  <script>\n" +
                            "    $(function() {\n" +
                            "      $.deck('.slide');\n" +
                            "    });\n" +
                            "  </script>\n" +
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
                
        return htmlHeader;
    }
    
    public int getCharMax(ObjetoAprendizaje object, int numberSlide, int numberBlock){
        
        int lim_line = 0;
        
        switch (object.getContent().get(numberSlide).getDesign()){
            
            case "1Col":
                lim_line = 100;
                break;
                
            case "1Fil2Col":
                switch (numberBlock){
                    case 0:
                        lim_line = 100;
                        break;
                    case 1:
                        lim_line = 40;
                        break;
                    case 2:
                        lim_line = 40;
                        break;
                    default:
                        break;
                }
                break;
                
            case "2Col":
                switch (numberBlock){
                    case 0:
                        lim_line = 40;
                        break;
                    case 1:
                        lim_line = 40;
                        break;                   
                    default:
                        break;
                }
                break;
                
            case "3Col":
                switch (numberBlock){
                    case 0:
                        lim_line = 40;
                        break;
                    case 1:
                        lim_line = 40;
                        break;
                    case 2:
                        lim_line = 40;
                        break;
                    default:
                        break;
                }
                break;
                
            case "1Fil3Col":
                switch (numberBlock){
                    case 0:
                        lim_line = 100;
                        break;
                    case 1:
                        lim_line = 40;
                        break;
                    case 2:
                        lim_line = 40;
                        break;
                    case 3:
                        lim_line = 40;
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
                        lim_line = 100;
                        break;
                    case 2:
                        lim_line = 40;
                        break;
                    case 3:
                        lim_line = 40;
                        break;
                    default:
                        break;
                }
                break;
            case "2Col1Fil":
                switch (numberBlock){
                    case 0:
                        lim_line = 40;
                        break;
                    case 1:
                        lim_line = 40;
                        break;
                    case 2:
                        lim_line = 100;
                        break;                    
                    default:
                        break;
                }
                break;
                
            default:
                break;            
        }        
        return lim_line;
    }
    
    public String write_scriptHeaderHtml(ObjetoAprendizaje object){
        
        String script_header = "";
        ArrayList<String> trozos= new ArrayList();
        for(int i = 0; i < object.getContent().size(); i++){
            Slide slide = object.getContent().get(i);
            
            for(int j = 0; j < slide.getBlocks().size(); j++){
                Block bloque = slide.getBlocks().get(j);
                
                for(int k = 0; k < bloque.getIdeas().size(); k++){
                    
                    Idea idea = bloque.getIdeas().get(k);
                    int id_hand = -1;
                    String script_voice_became = "";
                    String script_voice_lost = "";
                    String script_hand_became = "";
                    String script_hand_lost = "";
                    
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
                        /*int lenght_manus = idea.getText().get(id_hand).getContent().length() / caracter_max;
                        
                        if(lenght_manus % caracter_max != 0)
                            lenght_manus += 1;
                        */
                        trozos= trozarCadena(idea.getText().get(id_hand).getContent(), caracter_max);
                        
                        String text_ids = "";
                        for(int m = 0; m < trozos.size(); m++){
                            text_ids += "\"#mano-"+(i+1)+"-"+j+"-"+k+"-"+ m + "\"";
                            
                            if(m+1 < trozos.size()){
                                text_ids +=",";
                            }                                                            
                        }
                        
                        script_hand_became = "textBecameCurrent(["+ text_ids + "], direction);";
                        script_hand_lost = "textLostCurrent(["+ text_ids +"], direction);";                        
                    }
                    
                    script_header +=    "<script>\n" +
                                        "    $(function(){\n" +
                                        "      $(\"#show-slide-"+(i+1)+"-"+j+"-"+k+"\").bind('deck.becameCurrent', function(ev, direction) {\n" +                                        
                                        "        SectionBecameCurrent(\"slide-"+(i+1)+"-"+j+"-"+k+"\", direction);\n" +
                                                    script_voice_became +
                                                    script_hand_became +
                                        "      });\n" +
                                        "      $(\"#show-slide-"+(i+1)+"-"+j+"-"+k+"\").bind('deck.lostCurrent', function(ev, direction) {\n" +
                                        "        SectionLostCurrent(\"slide-"+(i+1)+"-"+j+"-"+k+"\", direction);\n" +
                                                    script_voice_lost +
                                                    script_hand_lost +
                                        "      });\n" +
                                        "    });\n" +
                                        "  </script>";
                }
            }
        }        
        return script_header;
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
    
    public ArrayList<String> trozarCadena(String content_text,int filOrCol){
               
        ArrayList<String> text_manus_split = new ArrayList();
        int lim_fil = 100;
        int lim_col = 40;
        
        if(filOrCol==lim_col){
            int cont = lim_col;
                    int aux_ind = 0;
        int index = 0;
            while(cont < content_text.length()){
                                
                aux_ind = content_text.indexOf(" ", cont)+1;
                text_manus_split.add(content_text.substring(index, aux_ind));
                cont = lim_col+aux_ind;
                index = aux_ind;
            }            
            text_manus_split.add(content_text.substring(index, content_text.length()));
        }        
        
        else if(filOrCol==lim_fil){
                    int aux_ind = 0;
        int index = 0;
            int cont = lim_fil;
            
            while(cont < content_text.length()){
                                
                aux_ind = content_text.indexOf(" ", cont)+1;
                text_manus_split.add(content_text.substring(index, aux_ind));
                cont = lim_fil+aux_ind;
                index = aux_ind;
            }            
            text_manus_split.add(content_text.substring(index, content_text.length()));
        }
        return text_manus_split;
        
    }
    
    public String write_hand(ArrayList<String> trozos, String design, int numberSlide, int numberBlock, int numberIdea, String designBlock){
         String id_handImage="";       
       /* ArrayList<String> text_manus_split = new ArrayList();
        int lim_fil = 100;
        int lim_col = 40;
        
        if(designBlock.equals("col")){
            int cont = lim_col;
            int aux_ind = 0;
            int index = 0;
            
            while(index < text_manus.length()){
                                
                aux_ind = text_manus.indexOf(" ", index);
                text_manus_split.add(text_manus.substring(index, aux_ind));
                cont = lim_col+aux_ind;
                index = aux_ind;
            }            
            text_manus_split.add(text_manus.substring(index, text_manus.length()));
        }        
        
        else if(designBlock.equals("fila")){
            int cont = lim_fil;
            int aux_ind = 0;
            int index = 0;
            
            while(cont < text_manus.length()){
                                
                aux_ind = text_manus.indexOf(" ", cont);
                text_manus_split.add(text_manus.substring(index, aux_ind));
                cont = lim_fil+aux_ind;
                index = aux_ind;
            }            
            text_manus_split.add(text_manus.substring(index, text_manus.length()));
        }*/
        String text = "";
        String image_hand="";
        id_handImage= "\"mano-"+numberSlide+"-"+numberBlock+"-"+numberIdea+"\"";
        image_hand+="\n<IMG id="+ id_handImage+" SRC=\"resources/manoconmanga.png\" WIDTH=700 HEIGHT=700 style=\"position:absolute;\">\n";
        text+=image_hand;
        for(int i = 0; i < trozos.size(); i++){
        
            text +=  "<div id=\"mano-"+numberSlide+"-"+numberBlock+"-"+numberIdea+"-"+ i +"\""+" style=\"width: 0px; height: 50px; white-space: nowrap; overflow: hidden;\">\n" +
                        "   <span class=\"manuscrita\">"+ trozos.get(i) +"</span>\n" +
                        "</div>\n";        
        }
        return text;
    }
    
    public String write_examples(ArrayList<String> list_examples/*, String start, String end*/){
        
        String examples = "<script>\nvar ejemplos = new Array(";
        
        for( int i = 0 ; i < list_examples.size(); i++){
            
            if(i == 0)
                examples += "\""+ list_examples.get(i) +"\"";
            else
                examples += ", \""+ list_examples.get(i) +"\"";
        }
        examples += ")</script>";
        examples += "<p>"+"<script language=javascript>ej_aleatorio()</script>"+"</p>";
        
        return examples;
    }    
    
    public String write_media(Media media, String OAName, String OAPath){
        
        String code_media = "";
        String mediaName = "";
        try{
            mediaName = ResourcesDownloader.DownloadFromURLAsMozilla(media.getContent(), OAPath, ".img");            
        }catch (IOException ex) {
            
            this.translateError = "NO_MEDIA";
        }        
        code_media += "<img src=\"resources/medias/"+ OAName+ "/"+mediaName+"\">";            
        return code_media;
    }
    
    public String write_block(Block block, int numberSlide, int numberBlock, String design, String OAName, String OAPath, String designBlock){
                        
        String codeHtml = "";        
        ArrayList<String>list_examples = new ArrayList();
        ArrayList<String> trozos = new ArrayList<>();
        int lim = 0;
        int idHand=0;
        if(designBlock.equals("fila")){
            lim = 100;
        }
        else{
            lim = 40;
        }
        for(int i = 0; i < block.getIdeas().size(); i++){
                                        
            Idea idea = block.getIdeas().get(i);            
                                                
            String start_label = "<section class=\"slide-" + numberSlide +"-" + numberBlock+"-"+ i +"\" style=\"visibility:hidden\">";
            codeHtml += start_label;            
            
            for(int j = 0; j < idea.getText().size(); j++){
                
                if(!idea.getText().get(j).getType().equals("ejemplo")){
                    
                    if(!list_examples.isEmpty()){
                        codeHtml += write_examples(list_examples);                        
                        list_examples.clear();
                    }
                }

                switch(idea.getText().get(j).getType()){

                    case "normal":
                        codeHtml += "<p>";
                        codeHtml += write_text(idea.getText().get(j).getContent(), design);
                        codeHtml += "</p>";
                        break;
                        
                    case "manuscrito":
                        trozos=trozarCadena(idea.getText().get(j).getContent(), lim);
                        codeHtml += write_hand(trozos, design, numberSlide, numberBlock, i, designBlock);                        
                        break;
                        
                    case "codigo":
                        codeHtml += "<pre class=\"brush: js\">";
                        codeHtml += idea.getText().get(j).getContent();
                        codeHtml += "</pre>";
                        break;
                        
                    case "ejemplo":
                        list_examples.add(idea.getText().get(j).getContent());
                        break;
                        
                    default:
                        break;
                };
            }
            
            if(!list_examples.isEmpty()){
                        
                codeHtml += write_examples(list_examples);
                list_examples.clear();
            }
            
            for(int j = 0; j < idea.getMedia().size(); j++){
                codeHtml += write_media(idea.getMedia().get(j), OAName, OAPath);
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
                
        String codeHtml =   "<body>\n" +
                            "  <div class=\"deck-container\">"+
                            "<section class=\"slide\" id=\"title-slide\">\n" +
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
                        
            codeHtml += "<div class=slide id=\"show-slide-"+ number_slide + "-" + number_block + "-" + number_idea + "\"></div>";            
        }
        return codeHtml;
    }      
    
    public String write_slideHtml(Slide scene, int nro_slide, String OAPath, String OAName, String design){        
        
        String codeHtml = "";               
        
        codeHtml += "<section class=\"slide\" id=\"slide-"+nro_slide+"\">\n" +
                            "<h2>"+scene.getTitle()+"</h2>\n";          
        
        switch (scene.getDesign()){
            case "1Col":
                //escribir contenido del primer bloque                                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design, OAName, OAPath, "fila");
                break;
                
            case "1Fil2Col":
                //bloque 1
                codeHtml += "<div class=\"up-2columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design, OAName, OAPath, "fila"); 
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"left-2columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design, OAName, OAPath, "col");
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"right-2columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, design, OAName, OAPath, "col");
                codeHtml += "</div>";
                        
                break;
                
            case "2Col":
                //escribir contenido del primer bloque
                codeHtml += "<div class=\"left-2columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design, OAName, OAPath, "col");                                                
                codeHtml += "</div>";
                
                //escribir contenido del segundo bloque
                codeHtml += "<div class=\"right-2columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design , OAName, OAPath, "col");
                codeHtml += "</div>";
                
                break;
                
            case "3Col":
                
                //bloque 1
                codeHtml += "<div class=\"left-3columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design , OAName, OAPath, "col");                                            
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"center-3columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design , OAName, OAPath, "col");
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"right-3columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, design, OAName, OAPath, "col");
                codeHtml += "</div>";
                
                break;
                
            case "1Fil3Col":
                //bloque 1
                codeHtml += "<div class=\"up-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design , OAName, OAPath, "fila");
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"left-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design , OAName, OAPath, "col");                                           
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"center-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, design, OAName, OAPath, "col");                                          
                codeHtml += "</div>";
                
                //bloque 4
                codeHtml += "<div class=\"right-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(3),nro_slide, 3, design, OAName, OAPath, "col");                                            
                codeHtml += "</div>";
                
                break;
                
            case "2Fil2Col":
                
                //bloque 1
                codeHtml += "<div class=\"up-2columnas-1up-1down\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design , OAName, OAPath, "fila");
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"left-2columnas-1up-1down\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design , OAName, OAPath, "fila");                                           
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"right-2columnas-1up-1down\">";
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, design, OAName, OAPath, "col");                                         
                codeHtml += "</div>";
                
                //bloque 4
                codeHtml += "<div class=\"down-2columnas-1up-1down\">";                
                codeHtml += write_block(scene.getBlocks().get(3),nro_slide, 3, design, OAName, OAPath, "col");                                            
                codeHtml += "</div>";
                
                break;
            case "2Col1Fil":
                
                //bloque 1
                codeHtml += "<div class=\"left-2columnas-1down\">";
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design , OAName, OAPath, "col");                                                
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"right-2columnas-1down \">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design , OAName, OAPath, "col");
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"down-2columnas-1down \">";
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, design, OAName, OAPath, "fila");                                          
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
     * @param OAName 
     * @return string con todas las slides incorporadas.
     */
    public String write_contentHtml(ObjetoAprendizaje object, String OAName){

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
        String htmlLibrs=   "<div aria-role=\"navigation\">\n" +
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

        codeHtml = "\n" + htmlLibrs;

        return codeHtml;
    }   
    public String write_scriptHand(ObjetoAprendizaje object){
        String codeHtml = "";
        String scriptHand= "" ;
        ArrayList<String> trozos= new ArrayList();

            for(int i = 0; i < object.getContent().size(); i++){
            Slide slide = object.getContent().get(i);
            
            for(int j = 0; j < slide.getBlocks().size(); j++){
                Block bloque = slide.getBlocks().get(j);
                
                for(int k = 0; k < bloque.getIdeas().size(); k++){
                    
                    Idea idea = bloque.getIdeas().get(k);
                    int id_hand = -1;
                    String array_textManuscrito = "";
                    String id_handImage="";
                    String left="";

                    for(int l = 0; l < idea.getText().size(); l++){
                        if(idea.getText().get(l).getType().equals("manuscrito")){
                            id_hand = l;
                            break;
                        }
                    }                    
                    if(id_hand > -1){
                                                
                        int caracter_max = getCharMax(object, i, j );

                        trozos= trozarCadena(idea.getText().get(id_hand).getContent(), caracter_max);
                        
                        String text_ids = "";
                        for(int m = 0; m < trozos.size(); m++){
                            text_ids += "\"#mano-"+(i+1)+"-"+j+"-"+k+"-"+ m + "\"";
                            
                            if(m+1 < trozos.size()){
                                text_ids +=",";
                            }                                                            
                        }

                        switch (slide.getDesign()){
                            case "1Col":
                                //escribir contenido del primer bloque 
                                left="-50";
                                break;

                            case "1Fil2Col":
                                //bloque 1
                                if(j==0){
                                  left="-50";
                                }
                                //bloque 2
                                else if(j==1){
                                    left="-50";
                                }
                                //bloque 3
                                else{
                                    left="+647";
                                }

                                break;

                            case "2Col":
                                //bloque 1
                               
                                if(j==0){
                                  left="-50";
                                }
                                else{
                                   left="+647";
                                }

                                break;

                            case "3Col":
                                //bloque 1
                                if(j==0){
                                  left="-50";
                                }
                                //bloque 2
                                else if(j==1){
                                    left="+400";    //ARREGLAR, HAY QUE SABER CUANTO VALE PARA EL CENTRO
                                }
                                //bloque 3
                                else{
                                    left="+647";
                                }

                                break;

                            case "1Fil3Col":
                                //bloque 1
                                if(j==0){
                                  left="-50";
                                }
                                //bloque 2
                                else if(j==1){
                                    left="+300";    //ARREGLAR, HAY QUE SABER CUANTO VALE PARA EL CENTRO
                                }
                                //bloque 3
                                else if(j==2){
                                    left="+600";    //ARREGLAR, HAY QUE SABER CUANTO VALE PARA EL CENTRO
                                }
                                else{
                                    left="+800"; 
                                }
                                break;

                            case "2Fil2Col":

                                //bloque 1
                                if(j==0){
                                  left="-50";
                                }
                                //bloque 2
                                else if(j==1){
                                    left="-50";   
                                }
                                //bloque 3
                                else if(j==2){
                                    left="+647"; 
                                }
                                else{
                                    left="-50"; 
                                }


                                break;
                            case "2Col1Fil":
                               //bloque 1
                                if(j==0){
                                  left="-50";
                                }
                                //bloque 2
                                else if(j==1){
                                    left="+647";  
                                }
                                //bloque 3
                                else if(j==2){
                                    left="-50"; 
                                }
                                else{
                                    left="-50"; 
                                }

                                break;

                            default:
                                break;            
                        }
                        array_textManuscrito= "move_down(["+ text_ids+"],"+left+",dist,up,down);\n";                        
                    
                    
                    scriptHand +=    "<script>\n" +
                                        "      $(\"#show-slide-"+(i+1)+"-"+j+"-"+k+"\").bind('deck.becameCurrent', function(ev, direction) {\n" +                                        
                                        "           var dist;\n"+
                                        "           $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").show();\n" +
                                        "           $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").addClass(\"moviendo\");\n" +
                                        "           var up=-70;\n" +
                                        "           var down=-30;" +
                                                    array_textManuscrito + 
                                        "            function move_down(array,num,dist,up,down) {\n" +
                                        "        var n = array.length;\n" +
                                        "        \n" +
                                        "        if(n!=0){\n" +
                                        "          var linea = array[0];\n" +
                                        "          var spanWidth = $(linea+' span').width();\n" +
                                        "          dist=num;\n" +
                                        "          if(dist<=spanWidth"+left+" && $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").hasClass(\"moviendo\")){\n" +
                                        "            $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").animate({\n" +
                                        "              \"margin-top\": down+\"px\"\n" +
                                        "              ,left: dist}, 200, \"linear\", function () {\n" +
                                        "                num+=11.8;\n" +
                                        "                move_up(array,num,dist,up,down);\n" +
                                        "            });\n" +
                                        "          }else{\n" +
                                        "              var new_array = [];\n" +
                                        "              for(i=1; i<n; i++){\n" +
                                        "                new_array.push(array[i]);   \n" +
                                        "              }\n" +
                                        "            move_down(new_array,"+left+",dist,up+50,down+50);\n" +
                                        "          }\n" +
                                        "        }else{\n" +
                                        "          $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").hide();\n" +
                                        "        }\n" +
                                        "      }\n" +
                                        "\n" +
                                        "      function move_up(array,num,dist,up,down) {\n" +
                                        "        var n = array.length;\n" +
                                        "        \n" +
                                        "        if(n!=0){\n" +
                                        "        var linea = array[0];\n" +
                                        "         var spanWidth = $(linea+' span').width();\n" +
                                        "         dist=num;\n" +
                                        "         if(dist<=spanWidth"+left+" && $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").hasClass(\"moviendo\")){\n" +
                                        "          $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").animate({\n" +
                                        "            \"margin-top\": up+\"px\"\n" +
                                        "            ,left: dist}, 200, \"linear\", function () {\n" +
                                        "              num+=11.8;\n" +
                                        "              move_down(array,num,dist,up,down);\n" +
                                        "            });\n" +
                                        "          }else{\n" +
                                        "              var new_array = [];\n" +
                                        "              for(i=1; i<n; i++){\n" +
                                        "                new_array.push(array[i]);   \n" +
                                        "              }\n" +
                                        "            move_down(new_array,"+left+",dist,up+50,down+50);\n" +
                                        "          }\n" +
                                        "        }else{\n" +
                                        "          $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").hide();\n" +
                                        "        }\n" +
                                        "      }\n" +
                                        "    });\n" +
                                        "\n" +
                                        "    $(\"#show-slide-"+(i+1)+"-"+j+"-"+k+"\").bind('deck.lostCurrent', function(ev, direction) {\n" +
                                        "      if(direction==\"reverse\"){\n" +
                                        "        $( \"#mano-"+(i+1)+"-"+j+"-"+k+"\" ).stop();\n" +
                                        "        $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").animate({left: "+left+"});\n" +
                                        "        $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").removeClass(\"moviendo\");\n" +
                                        "        $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").fadeIn();\n" +
                                        "      } else {\n" +
                                        "        $( \"#mano-"+(i+1)+"-"+j+"-"+k+"\" ).finish();\n" +
                                        "        $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").hide();\n" +
                                        "      }\n" +
                                        "    });\n" +
                                        "</script>" ;
                    }
                }
            }
        }        
        codeHtml = "\n" + scriptHand;

        return codeHtml;
    }
}