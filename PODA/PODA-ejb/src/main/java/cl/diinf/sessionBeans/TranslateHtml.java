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
    public String writeHtml(LearningObject object) throws IOException{
                    
        String codeHtml;       
        String OAName = object.getName_file();
        
        codeHtml =  "<!DOCTYPE html>\n" +"<html>\n" + "<head>\n";
        codeHtml += write_headerHtml(object);
        codeHtml += write_scriptHeaderHtml(object);        
        codeHtml += "</head>\n"+"<body>\n";
        codeHtml += "   <div class=\"deck-container\">\n";
        codeHtml += write_contentHtml(object, OAName);
        codeHtml += write_librsHtml(object);
        codeHtml += write_scriptHand(object);
        codeHtml += "   </div>\n";
        codeHtml += "</body></html>";
        return codeHtml;        
    }
    
    /**
     * Escribe el header de la página web.
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @return string con el header incorporado.
     */
    public String write_headerHtml(LearningObject object) throws IOException{
        //Head del HTML
        
        String templateHtml;
        //String scriptEvaluacion = write_evaluacionHtml(object);
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
        //htmlHeader = htmlHeader + scriptEvaluacion;        
        return htmlHeader;
    }
    
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
                break;            
        }        
        return lim_line;
    }
    
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
                    int id_hand_example = -1;
                    String script_voice_became = "";
                    String script_voice_lost = "";
                    String script_hand_became = "";
                    String script_hand_lost = "";
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
                        
                        script_hand_became = "textBecameCurrent(["+ text_ids + "], direction);";
                        script_hand_lost = "textLostCurrent(["+ text_ids +"], direction);";                        
                    }
                    
                    for(int l = 0; l < idea.getExample().size(); l++){
                        for(int m = 0; m < idea.getExample().get(l).getTextContent().size(); m++){
                            if(idea.getExample().get(l).getTextContent().get(m).getType().equals("manuscrito")){
                                id_hand_example = m;
                                break;
                            }
                        }
                        if(id_hand_example > -1){
                            int caracter_max = getCharMax(object, i, j );                                               
                        trozos = trozarCadena(idea.getText().get(id_hand).getContent(), caracter_max);
                        
                        String text_ids = "";
                        
                        for(int m = 0; m < trozos.size(); m++){
                            text_ids += "\"#manoExample-"+(i+1)+"-"+j+"-"+k+"-"+ m + "\"";
                            
                            if(m+1 < trozos.size()){
                                text_ids +=",";
                            }                                                            
                        }
                        
                        script_hand_became_example = "textBecameCurrent(["+ text_ids + "], direction);";
                        script_hand_lost_example = "textLostCurrent(["+ text_ids +"], direction);"; 
                        }
                    }
                    
                    script_header +=    "<script>\n" +
                                        "    $(function(){\n" +
                                        "      $(\"#show-slide-"+(i+1)+"-"+idea.getAparitionOrder()+"\").bind('deck.becameCurrent', function(ev, direction) {\n" +                                        
                                        "        SectionBecameCurrent(\"slide-"+(i+1)+"-"+j+"-"+k+"\", direction);\n" +
                                                    script_voice_became +
                                                    script_hand_became +
                                        "      });\n" +
                                        "      $(\"#show-slide-"+(i+1)+"-"+idea.getAparitionOrder()+"\").bind('deck.lostCurrent', function(ev, direction) {\n" +
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
               
        ArrayList<String> piece_string = new ArrayList();
        
        int limite = filOrCol;        
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
    
    public String write_hand(ArrayList<String> trozos, int numberSlide, int numberBlock, int numberIdea){
        
        String id_handImage="\"mano-"+numberSlide+"-"+numberBlock+"-"+numberIdea+"\"";        
        String text = "\n<IMG id="+ id_handImage+" SRC=\"resources/manoconmanga.png\" WIDTH=800 HEIGHT=800 style=\"position:absolute;\">\n";
                                        
        for(int i = 0; i < trozos.size(); i++){
        
            text +=     "<div id=\"mano-"+numberSlide+"-"+numberBlock+"-"+numberIdea+"-"+ i +"\""+" style=\"width: 0px; height: 50px; white-space: nowrap; overflow: hidden;\">\n" +
                        "   <span class=\"manuscrita\">"+ trozos.get(i) +"</span>\n" +
                        "</div>\n";        
        }
        return text;
    }
    
    /*public String write_evaluacionHtml(LearningObject object) throws IOException{
                    
        String templateCss = "";
        String templateEvaluacion1 = "";
        String templateRescatarTemas = "";
        String templateFuncionesFijas = "";
        String total = null;
        templateCss = "<style>div#evaluacion{ border:#000 1px solid; padding:10px 40px 40px 40px; }" +
                      "table, th, td {border: 1px solid black; border-collapse: collapse;}"+
                      "th, td {padding: 5px;}</style>";
        
        templateEvaluacion1= "<script>"+
                             "var posicion = 0, evaluacion, pregunta, seleccion, opciones, correcta = 0;\n" +
                             "var puntajeTotal       = 0;\n" +
                             "var puntajePorPregunta = 0;\n" +
                             "var temasCorrectos     = new Array();\n" +
                             "var alternativaA, alternativaB, alternativaC, alternativaD = 0;"+
                             "var temas= [];"+
                            "var temasTemp  = [];"+
                            "var objetoTema = {};"+
                            "var temaAgregar;";
        
        String tempEval = "";
        List<String> pilas = new ArrayList<>();
        List<String> cantidadEnunciadosList = new ArrayList<>();
        
        int contador=0;
        for (int i=0; i<object.getQuizSet().size(); i++){
            String varPila = "";
            int cantidadEnunciados = 0;
            
            for(int j = 0; j < object.getQuizSet().get(i).getQuiz().size(); j++){
                contador++;
                cantidadEnunciados++;
                tempEval+= "var enunciado"+(contador)+" = {	\n" +
                           "enunciado: \""+
                object.getQuizSet().get(i).getQuiz().get(j).getHeader()+"\",\n"; //Enunciado
                if(j == object.getQuizSet().get(i).getQuiz().size()-1){
                    varPila+=object.getQuizSet().get(i).getQuiz().get(j).getHeader();
                }
                else{
                    varPila+=object.getQuizSet().get(i).getQuiz().get(j).getHeader()+",";
                }
                for(int k = 0; k < object.getQuizSet().get(i).getQuiz().get(j).getChoices().size();k++){
                    String solutionAlt = "";
                    switch(object.getQuizSet().get(i).getQuiz().get(j).getChoices().get(k).getType()){
                        case "solucion":
                            switch(k){
                                case 0:
                                    solutionAlt="A";
                                    tempEval+="solucion: '"+solutionAlt+"',\n"+"alternativas: [\n";
                                    break;
                                case 1:
                                    solutionAlt="B";
                                    tempEval+="solucion: '"+solutionAlt+"',\n"+"alternativas: [\n";
                                    break;
                                case 2:
                                    solutionAlt="C";
                                    tempEval+="solucion: '"+solutionAlt+"',\n"+"alternativas: [\n";
                                    break;
                                case 3:
                                    solutionAlt="D";
                                    tempEval+="solucion: '"+solutionAlt+"',\n"+"alternativas: [\n";
                                    break;
                            }
                            break;
                        case "distractor":
                            break;
                    }
                    String solutionAlt2 = "";
                    switch(k){
                                case 0:
                                    solutionAlt2="A";
                                    break;
                                case 1:
                                    solutionAlt2="B";
                                    break;
                                case 2:
                                    solutionAlt2="C";
                                    break;
                                case 3:
                                    solutionAlt2="D";
                                    break;
                            }
                    tempEval+="{alternativa: '"+solutionAlt2+"', pregunta:\""+object.getQuizSet().get(i).getQuiz().get(j).getChoices().get(k).getContent()+"\", tema:\""+object.getQuizSet().get(i).getQuiz().get(j).getChoices().get(k).getTopic()+"\"},\n";
                    
                     //cada alternativa tema
                     //cada alternativa tipo solucion o distractor
                     //cada alternativa contenido
                }
                tempEval+= "]};\n";
            
            }//fin for
            pilas.add(varPila);
            cantidadEnunciadosList.add(String.valueOf(cantidadEnunciados));
          
        }//fin for
        String randomPila = ""; 
        int contador2 = 0;
        for(int i = 0; i < object.getQuizSet().size(); i++){
            
            randomPila += "var pila"+(i+1)+" = [";
            for(int j = 0; j < object.getQuizSet().get(i).getQuiz().size(); j++){
                if(j == object.getQuizSet().get(i).getQuiz().size()-1){
                    randomPila += "enunciado"+(contador2+1);
                    contador2++;
                }
                else{
                    randomPila += "enunciado"+(contador2+1)+",";
                    contador2++;
                }
            }
            randomPila+="];\n";
            randomPila += "var elementoPila"+(i+1)+"Random= pila"+(i+1)+"[Math.floor(Math.random()*pila"+(i+1)+".length)];";
                          
        }
        String mParameters = "var preguntas = [";
        for(int i = 0; i < cantidadEnunciadosList.size(); i++){
            if(i == cantidadEnunciadosList.size()-1){
                mParameters+="elementoPila"+(i+1)+"Random";
            }
            else{
                mParameters+="elementoPila"+(i+1)+"Random,";
            }
        }
        mParameters += "];\n" +
                        "var temas      = [];\n" +
                        "var temasTemp  = [];\n" +
                        "var objetoTema = {};\n" +
                        "var temaAgregar; \n" +
                        "var temasIncorrectos = [];";
                        
        
        templateRescatarTemas=  mParameters+ "for (var contadorPreguntas=0; contadorPreguntas<preguntas.length; contadorPreguntas++){\n" +
                             "for (var contadorAlternativa=0; contadorAlternativa<preguntas[contadorPreguntas].alternativas.length; contadorAlternativa++){\n"+
                             "temaAgregar=preguntas[contadorPreguntas].alternativas[contadorAlternativa].tema;\n" +
                             "temas.push({ tema:temaAgregar, puntaje:0 });\n"+
                             "}\n"+
                             "}\n";

	templateFuncionesFijas = "function _(x){\n" +
				 "return document.getElementById(x);\n" +
                                  "}\n" +
                                 "\n" +
                                "function cleanup(arr, prop) {\n" +
                                "var new_arr = [];\n" +
                                "var lookup  = {};\n" +
                                " \n" + 
                                "for (var i in arr) {\n" +
                                "lookup[arr[i][prop]] = arr[i];\n" +
                                "}\n" +
                                "\n" +
                                "for (i in lookup) {\n" +
                                "new_arr.push(lookup[i]);\n" +
                                "}\n" +
                                " \n" +
                                "return new_arr;\n" +
                                "}\n"+
				"function mostrarPregunta() {\n" +
				"evaluacion = _(\"evaluacion\");\n" +
                                "var str = '';\n" +
				"var strTemas='';\n"+
                                "temasIncorrectos=cleanup(temasIncorrectos,'tema');\n"+
                                "if(posicion >= preguntas.length){\n" +
                                "\n" +
                                "temas=cleanup(temas,'tema');\n" +
                                "for (var j=0; j<temas.length; j++){\n" +
                                "if (temas[j].puntaje <=0){\n" +
                                "temas[j].puntaje=0;\n" +
                                "}\n" +
                                "}"+
                                "for(var i=0; i<temas.length ; i++){\n" +
                                " str = str + \"<tr><td>\"+temas[i].tema +\"</td>\"+\"<td>\"+temas[i].puntaje +\"</td></tr>\";\n" +
                                "}\n" +
                                "for(var k=0; k<temasIncorrectos.length; k++){\n" +
                                "strTemas = strTemas + temasIncorrectos[k].tema + \" \";\n" +
                                "}"+
                                "\n" +
                                "\n" +
                                "\n" +
                                "evaluacion.innerHTML = \n" +
                                "\"<table align=\\\"center\\\"><tr><th>Tema</th><th>Puntaje obtenido</th></tr>\"+str+\"</tr></table><p>Deberias repasar estos temas: \"+strTemas+\"</p>\";" +
                                "\n" +
                                "\n" +
                                "_(\"evaluacion_status\").innerHTML = \"Evaluación completa\";\n" +
                                "posicion = 0;\n" +
                                "correcta = 0;\n" +
                                "return false;\n" +
                                "}\n" +
				"\n" +
				"pregunta     =preguntas[posicion].enunciado;\n" +
				"alternativaA =preguntas[posicion].alternativas[0].pregunta;\n" +
				"alternativaB =preguntas[posicion].alternativas[1].pregunta;\n" +
				"alternativaC =preguntas[posicion].alternativas[2].pregunta;\n" +
				"alternativaD =preguntas[posicion].alternativas[3].pregunta;\n" +
				"_(\"evaluacion_status\").innerHTML = \"Preguntas \"+(posicion+1)+\" de \"+preguntas.length;\n" +
				"evaluacion.innerHTML = \"<h3>\"+pregunta+\"</h3>\";\n" +
				"evaluacion.innerHTML += \"<input type='radio' name='opciones' value='A'> \"+alternativaA+\"<br>\";\n" +
				"evaluacion.innerHTML += \"<input type='radio' name='opciones' value='B'> \"+alternativaB+\"<br>\";\n" +
				"evaluacion.innerHTML += \"<input type='radio' name='opciones' value='C'> \"+alternativaB+\"<br>\";\n" +
				"evaluacion.innerHTML += \"<input type='radio' name='opciones' value='D'> \"+alternativaD+\"<br><br>\";\n" +
				"evaluacion.innerHTML += \"<button onclick='evaluar()'>Enviar respuesta</button>\";\n" +
				"}\n" +
				"\n" +
				"function evaluar() {\n" +
				"opciones = document.getElementsByName(\"opciones\");\n" +
				"for(var i=0; i<opciones.length; i++){\n" +
				"if(opciones[i].checked){\n" +
				"seleccion = opciones[i].value;\n" +
				"}\n" +
				"}\n" +
				"\n" +
				"if(seleccion == preguntas[posicion].solucion) {\n" +
				"correcta++;\n" +
				"// Si esta buena, le sumo +1 al tema de la alternativa y al resto de los temas de las otras alternativas de la pregunta\n" +
				"for (var i = 0; i<temas.length; i++) {\n" +
				"for(var j=0; j<preguntas[posicion].alternativas.length; j++){\n" +
				"if (temas[i].tema == preguntas[posicion].alternativas[j].tema) {\n" +
				"temas[i].puntaje = temas[i].puntaje + 1; \n" +
				"}\n" +
				"}\n" +
				"}\n" +
				"} // fin if\n" +
				"else {\n" +
				"// Si esta mala, le resto -1 al tema de la alternativa escogida y al tema de la alternativa correcta\n" +
				"for (var i = 0; i<temas.length; i++) {\n" +
				"for (var j=0; j<preguntas[posicion].alternativas.length; j++){\n" +
				"//resto al tema de la incorrecta\n" +
				"if (seleccion == preguntas[posicion].alternativas[j].alternativa) {\n" +
				"if (temas[i].tema == preguntas[posicion].alternativas[j].tema){\n" +
				"temas[i].puntaje = temas[i].puntaje - 1;\n" +
                                "temasIncorrectos.push({ tema:preguntas[posicion].alternativas[j].tema });"+
				"if (temas[i].puntaje < 0){\n" +
				"temas[i].puntaje = 0;\n" +
				"}\n" +
				"}\n" +
				"}\n" +
				"}\n" +
				"}//fin for\n" +
				"//resto al tema de la correcta\n" +
				"for (var i=0; i<preguntas[posicion].alternativas.length; i++){\n" +
				"if (preguntas[posicion].solucion == preguntas[posicion].alternativas[i].alternativa){\n" +
				"   //recorro lista de temas para restar al tema correspondiente\n" +
				"   for(var j=0; j<temas.length; j++){\n" +
				"       if (preguntas[posicion].alternativas[i].tema == temas[j].tema){\n" +
				"           temas[j].puntaje = temas[j].puntaje - 1;\n" +
				"       if (temas[i].puntaje < 0){\n" +
				"           temas[i].puntaje = 0;\n" +
				"   }\n" +
				"}\n" +
				"}\n" +
				"}\n" +
				"}				\n" +
				"}//fin else\n" +
				"posicion++;\n" +
				"mostrarPregunta();\n" +
				"}\n" +
				"\n" +
				"window.addEventListener(\"load\", mostrarPregunta, false);\n" +
				"</script>";
        total = templateCss +templateEvaluacion1+tempEval+randomPila+templateRescatarTemas+templateFuncionesFijas;
        return total;        
    }*/
    
    public String write_examples(List<Example> list_examples, String OAName, String OAPath){
        
        String examples = "<script>\nvar ejemplos = new Array(";
        
        for( int i = 0 ; i < list_examples.size(); i++){
            
            
            
            if(i > 0)
                examples += ", ";
            
            examples += "\"";
            
            for(int j = 0; j < list_examples.get(i).getTextContent().size(); j++){

                examples += " ";

                String content = list_examples.get(i).getTextContent().get(j).getContent();

                switch ( list_examples.get(i).getTextContent().get(j).getType()){

                    case "normal":
                        examples += "<p>"+ content +"</p>";
                        break;
                    case "codigo":
                        examples += "<pre class=\\\"brush: js\\\">"+ content +"</pre>";
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
    
    public String write_block(Block block, int numberSlide, int numberBlock, String design, String OAName, String OAPath, int lim_line){
                        
        String codeHtml = "";                
        ArrayList<String> trozos = new ArrayList<>();                    
                        
        for(int i = 0; i < block.getIdeas().size(); i++){
                                        
            Idea idea = block.getIdeas().get(i);            
                                                
            String start_label = "<section class=\"slide-" + numberSlide +"-" + numberBlock+"-"+ i +"\" style=\"visibility:hidden\">";
            codeHtml += start_label;            
            
            for(int j = 0; j < idea.getText().size(); j++){
                                
                switch(idea.getText().get(j).getType()){

                    case "normal":
                        codeHtml += "<p>";
                        codeHtml += write_text(idea.getText().get(j).getContent(), design);
                        codeHtml += "</p>";
                        break;
                        
                    case "manuscrito":                        
                        trozos=trozarCadena(idea.getText().get(j).getContent(), lim_line);
                        codeHtml += write_hand(trozos, numberSlide, numberBlock, i);                        
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
            
            if( !idea.getExample().isEmpty() ){
                
                codeHtml += write_examples(idea.getExample(), OAName, OAPath);
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

    public String write_animationHtml(Scene scene, int number_slide, String OAPath, String OAName ){
        
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
                        
            codeHtml += "<div class=slide id=\"show-slide-"+ number_slide + "-" + orderedIdea.get(i).getAparitionOrder() + "\"></div>";            
        }
        return codeHtml;
    }      
    
    public String write_slideHtml(Scene scene, int nro_slide, String OAPath, String OAName, String design){        
        
        int tam_fila = 110;
        int tam_2Col = 45;
        int tam_3Col = 30;
        
        
        String codeHtml = "";        
        
        codeHtml += "<section class=\"slide\" id=\"slide-"+nro_slide+"\">\n" +
                            "<h2>"+scene.getTitle()+"</h2>\n";          
        
        switch (scene.getDesign()){
            case "1Col":
                //escribir contenido del primer bloque                                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design, OAName, OAPath, tam_fila);
                break;
                
            case "1Fil2Col":
                //bloque 1
                codeHtml += "<div class=\"up-2columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design, OAName, OAPath, tam_fila); 
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"left-2columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design, OAName, OAPath, tam_2Col);
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"right-2columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, design, OAName, OAPath, tam_2Col);
                codeHtml += "</div>";
                        
                break;
                
            case "2Col":
                //escribir contenido del primer bloque
                codeHtml += "<div class=\"left-2columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design, OAName, OAPath, tam_2Col);                                                
                codeHtml += "</div>";
                
                //escribir contenido del segundo bloque
                codeHtml += "<div class=\"right-2columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design , OAName, OAPath, tam_2Col);
                codeHtml += "</div>";
                
                break;
                
            case "3Col":
                
                //bloque 1
                codeHtml += "<div class=\"left-3columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design , OAName, OAPath, tam_3Col);                                            
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"center-3columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design , OAName, OAPath, tam_3Col);
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"right-3columnas\">";                
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, design, OAName, OAPath, tam_3Col);
                codeHtml += "</div>";
                
                break;
                
            case "1Fil3Col":
                //bloque 1
                codeHtml += "<div class=\"up-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design , OAName, OAPath, tam_fila);
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"left-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design , OAName, OAPath, tam_3Col);                                           
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"center-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, design, OAName, OAPath, tam_3Col);                                          
                codeHtml += "</div>";
                
                //bloque 4
                codeHtml += "<div class=\"right-3columnas-1up\">";                
                codeHtml += write_block(scene.getBlocks().get(3),nro_slide, 3, design, OAName, OAPath, tam_3Col);
                codeHtml += "</div>";
                
                break;
                
            case "2Fil2Col":
                
                //bloque 1
                codeHtml += "<div class=\"up-2columnas-1up-1down\">";                
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design , OAName, OAPath, tam_fila);
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"left-2columnas-1up-1down\">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design , OAName, OAPath, tam_fila);                                           
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"right-2columnas-1up-1down\">";
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, design, OAName, OAPath, tam_2Col);  
                codeHtml += "</div>";
                
                //bloque 4
                codeHtml += "<div class=\"down-2columnas-1up-1down\">";                
                codeHtml += write_block(scene.getBlocks().get(3),nro_slide, 3, design, OAName, OAPath, tam_2Col);                                            
                codeHtml += "</div>";
                
                break;
            case "2Col1Fil":
                
                //bloque 1
                codeHtml += "<div class=\"left-2columnas-1down\">";
                codeHtml += write_block(scene.getBlocks().get(0), nro_slide, 0, design , OAName, OAPath, tam_2Col);                                                
                codeHtml += "</div>";
                
                //bloque 2
                codeHtml += "<div class=\"right-2columnas-1down \">";                
                codeHtml += write_block(scene.getBlocks().get(1), nro_slide, 1, design , OAName, OAPath, tam_2Col);
                codeHtml += "</div>";
                
                //bloque 3
                codeHtml += "<div class=\"down-2columnas-1down \">";
                codeHtml += write_block(scene.getBlocks().get(2),nro_slide, 2, design, OAName, OAPath, tam_fila);                                          
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
        codeHtml += "<section class=\"slide\">\n" +
                    "<h2 id=\"evaluacion_status\"></h2>\n" +
                    "<div id=\"evaluacion\"></div>\n" +
                    "</section>\n";
        
        return codeHtml;
    }     
    /**
     * Escribe liberías necesarias para utilizar el framework en el que está
     * construido el objeto.
     * @param object objeto de aprendizaje construido por la clase de reader.
     * @param codeHtml 
     * @return string con las librerías incorporadas.
     */
    public String write_librsHtml(LearningObject object){

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
    public String write_scriptHand(LearningObject object){
        String codeHtml = "";
        String scriptHand= "" ;

            for(int i = 0; i < object.getContent().size(); i++){
            Scene slide = object.getContent().get(i);
            
            for(int j = 0; j < slide.getBlocks().size(); j++){
                Block bloque = slide.getBlocks().get(j);
                
                for(int k = 0; k < bloque.getIdeas().size(); k++){
                    
                    Idea idea = bloque.getIdeas().get(k);
                    int id_hand = -1;
                    String array_textManuscrito = "";                    
                    String left="";

                    for(int l = 0; l < idea.getText().size(); l++){
                        if(idea.getText().get(l).getType().equals("manuscrito")){
                            id_hand = l;
                            break;
                        }
                    }                    
                    if(id_hand > -1){
                                                
                        int caracter_max = getCharMax(object, i, j );                        
                        ArrayList <String >trozos= trozarCadena(idea.getText().get(id_hand).getContent(), caracter_max);
                        
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
                                left="-80";
                                break;

                            case "1Fil2Col":
                                //bloque 1
                                if(j==0){
                                  left="-80";
                                }
                                //bloque 2
                                else if(j==1){
                                    left="-80";
                                }
                                //bloque 3
                                else{
                                    left="+617";
                                }

                                break;

                            case "2Col":
                                //bloque 1
                               
                                if(j==0){
                                  left="-80";
                                }
                                else{
                                   left="+617";
                                }

                                break;

                            case "3Col":
                                //bloque 1
                                if(j==0){
                                  left="-80";
                                }
                                //bloque 2
                                else if(j==1){
                                    left="+340";    //ARREGLAR, HAY QUE SABER CUANTO VALE PARA EL CENTRO
                                }
                                //bloque 3
                                else{
                                    left="+760";
                                }

                                break;

                            case "1Fil3Col":
                                //bloque 1
                                if(j==0){
                                  left="-80";
                                }
                                //bloque 2
                                else if(j==1){
                                    left="-80";    //ARREGLAR, HAY QUE SABER CUANTO VALE PARA EL CENTRO
                                }
                                //bloque 3
                                else if(j==2){
                                    left="+340";    //ARREGLAR, HAY QUE SABER CUANTO VALE PARA EL CENTRO
                                }
                                else{
                                    left="+760"; 
                                }
                                break;

                            case "2Fil2Col":

                                //bloque 1
                                if(j==0){
                                  left="-80";
                                }
                                //bloque 2
                                else if(j==1){
                                    left="-80";   
                                }
                                //bloque 3
                                else if(j==2){
                                    left="+617"; 
                                }
                                else{
                                    left="-80"; 
                                }


                                break;
                            case "2Col1Fil":
                               //bloque 1
                                if(j==0){
                                  left="-80";
                                }
                                //bloque 2
                                else if(j==1){
                                    left="+617";  
                                }
                                //bloque 3
                                else if(j==2){
                                    left="-80"; 
                                }
                                else{
                                    left="-80"; 
                                }

                                break;

                            default:
                                break;            
                        }
                        array_textManuscrito= "move_down(["+ text_ids+"],"+left+",dist,up,down);\n";                        
                    
                    
                    scriptHand +=    "<script>\n" +
                                        "      $(\"#show-slide-"+(i+1)+"-"+ idea.getAparitionOrder() +"\").bind('deck.becameCurrent', function(ev, direction) {\n" +                                        
                                        "           var dist;\n"+
                                        "           $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").show();\n" +
                                        "           var up=-80;\n" +
                                        "           var down=-40;" +
                                                    array_textManuscrito + 
                                        "            function move_down(array,num,dist,up,down) {\n" +
                                        "        var n = array.length;\n" +
                                        "        \n" +
                                        "        if(n!=0){\n" +
                                        "          var linea = array[0];\n" +
                                        "          var spanWidth = $(linea+' span').width();\n" +
                                        "          dist=num;\n" +
                                        "           if(n==1){\n" +
                                        "          if(dist<=spanWidth"+left+"+20 ){\n" +
                                        "          $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").animate({\n" +
                                        "            \"margin-top\": down+\"px\"\n" +
                                        "            ,left: dist}, 200, \"linear\", function () {\n" +
                                        "              num+=11.8;\n" +
                                        "              move_up(array,num,dist,up,down);\n" +
                                        "            });\n" +
                                        "          }else{\n" +
                                        "              var new_array = [];\n" +
                                        "              for(i=1; i<n; i++){\n" +
                                        "                new_array.push(array[i]);   \n" +
                                        "              }\n" +
                                        "           $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").animate({left: "+left+"},0);\n" +
                                        "            move_down(new_array,"+left+",dist,up+50,down+50);\n" +
                                        "          }\n" +
                                        "         }else{\n"+
                                        "          if(dist<=spanWidth"+left+"){\n" +
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
                                        "           $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").animate({left: "+left+"},0);\n"+
                                        "            move_down(new_array,"+left+",dist,up+50,down+50);\n" +
                                        "          }\n" +
                                        "         }\n"+
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
                                        "           if(n==1){\n" +
                                        "          if(dist<=spanWidth"+left+"+20 ){\n" +
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
                                        "           $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").animate({left: "+left+"},0);\n" +
                                        "            move_down(new_array,"+left+",dist,up+50,down+50);\n" +
                                        "          }\n" +
                                        "         }else{\n"+
                                        "          if(dist<=spanWidth"+left+"){\n" +
                                        "            $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").animate({\n" +
                                        "              \"margin-top\": up+\"px\"\n" +
                                        "              ,left: dist}, 200, \"linear\", function () {\n" +
                                        "                num+=11.8;\n" +
                                        "                move_down(array,num,dist,up,down);\n" +
                                        "            });\n" +
                                        "          }else{\n" +
                                        "              var new_array = [];\n" +
                                        "              for(i=1; i<n; i++){\n" +
                                        "                new_array.push(array[i]);   \n" +
                                        "              }\n" +
                                        "           $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").animate({left: "+left+"},0);\n"+
                                        "            move_down(new_array,"+left+",dist,up+50,down+50);\n" +
                                        "          }\n" +
                                        "         }\n"+
                                        "        }else{\n" +
                                        "          $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").hide();\n" +
                                        "        }\n" +
                                        "      }\n" +
                                        "    });\n" +
                                        "\n" +
                                        "    $(\"#show-slide-"+(i+1)+"-"+idea.getAparitionOrder()+"\").bind('deck.lostCurrent', function(ev, direction) {\n" +
                                        "      if(direction==\"reverse\"){\n" +
                                        "        $( \"#mano-"+(i+1)+"-"+j+"-"+k+"\" ).stop();\n" +
                                        "        $(\"#mano-"+(i+1)+"-"+j+"-"+k+"\").animate({left: "+left+"},0);\n" +
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