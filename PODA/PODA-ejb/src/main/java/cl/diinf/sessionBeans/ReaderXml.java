package cl.diinf.sessionBeans;

import cl.diinf.handlers.SimpleErrorHandler;
import cl.diinf.objetoAprendizaje.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;

public class ReaderXml {

    private String parsingError;
    private String fileContent;

    public ReaderXml() {
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getParsingError() {
        return parsingError;
    }

    public void setParsingError(String parsingError) {
        this.parsingError = parsingError;

    }        
      
    /**
     * Devuelve una vista generada a partir del string entregado.
     *
     * @return Objects como lista de objetos de aprendizaje.
     */
    public List<LearningObject> readOA() {

        List<LearningObject> Objects = new ArrayList<>();
        
        if (this.fileContent == null) {
            return Objects;
        }
        
        /*
            Agrega el DTD al archivo.
        */
        this.preProcessText();
        
        
        try {
            File OA_XML_File = this.stringToFile(fileContent);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setValidating(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            SimpleErrorHandler errorHandler = new SimpleErrorHandler();
            dBuilder.setErrorHandler(errorHandler);
            Document doc = dBuilder.newDocument();
            try {
                doc = dBuilder.parse(OA_XML_File);
                if (!errorHandler.getErrorMessage().equals("NO_ERROR")) {
                        /*Error en el documento*/
                        this.parsingError = errorHandler.getErrorMessage();
                        System.out.println(errorHandler.getErrorMessage());
                        OA_XML_File.delete();
                        return Objects;
                    }
            } catch (org.xml.sax.SAXException e) {
                /*Error en el parser*/
                this.parsingError = e.getLocalizedMessage();
                OA_XML_File.delete();
                System.out.println(e.getLocalizedMessage());
                return Objects;
            }
            
            doc.getDocumentElement().normalize();

            NodeList OA = doc.getElementsByTagName("objeto");
            
            /*
             Recorre todos los objetos encontrados en el archivo de entrada para
             devolver tantos LearningObject como hayan sido especificados.
             */
            /*
             En esta lista se guardar los objetos de aprendizaje que lean del archivo
             */
            List<LearningObject> newOAList = new ArrayList<>();

            if (OA.getLength() == 0) {
                /*
                 Lista vacia en casos de formato inválido o vacío
                 */
                return newOAList;
            }

            for (int i = 0; i < OA.getLength(); i++) {
                /*
                 Recorre todos los hijos de Object (Slides) y las 
                 agrega al objeto que corresponda (OA.item(i))
                 */
                LearningObject newOA = new LearningObject();
                Node ObjectNode = OA.item(i);
                Element currentNode = (Element) ObjectNode;

                newOA.setTitle(currentNode.getAttribute("titulo"));
                newOA.setAuthor(currentNode.getAttribute("autor"));
                newOA.setTemplate(currentNode.getAttribute("tema"));
                newOA.setCreationDate(new Date());
                String OA_nameFile = UUID.randomUUID().toString();
                OA_nameFile += ("" + System.currentTimeMillis() + "_OA");
                newOA.setName_file(OA_nameFile);

                Element objectNode = (Element) currentNode;
                NodeList slides = objectNode.getElementsByTagName("escena");
                
                for (int j = 0; j < slides.getLength(); j++) {
                    Scene newSlide = new Scene();
                    Element currentSlide = (Element) slides.item(j);
                    newSlide.setTitle(currentSlide.getAttribute("titulo"));
                    newSlide.setDesign(currentSlide.getAttribute("tipo"));
                    if (newSlide.getDesign().equals("")) {
                        this.setParsingError("No se especificó diseño de escena: " + newSlide.getTitle() + ".");

                        newSlide.setDesign("1Col");
                        return new ArrayList<>();
                    }

                    NodeList readedBlocks = currentSlide.getElementsByTagName("bloque");

                    for (int k = 0; k < readedBlocks.getLength(); k++) {

                        switch (newSlide.getDesign()) {
                            case "1Col":
                                if (readedBlocks.getLength() != 1) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    return new ArrayList<>();
                                }
                                break;
                            case "1Fil2Col":
                                if (readedBlocks.getLength() != 3) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    return new ArrayList<>();
                                }
                                break;
                            case "2Col":
                                if (readedBlocks.getLength() != 2) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    return new ArrayList<>();
                                }
                                break;
                            case "3Col":
                                if (readedBlocks.getLength() != 3) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    return new ArrayList<>();
                                }
                                break;
                            case "1Fil3Col":
                                if (readedBlocks.getLength() != 4) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    return new ArrayList<>();
                                }
                                break;
                            case "2Fil2Col":
                                if (readedBlocks.getLength() != 4) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    return new ArrayList<>();
                                }
                                break;
                            case "2Col1Fil":
                                if (readedBlocks.getLength() != 3) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    return new ArrayList<>();
                                }
                                break;
                            default:
                                this.parsingError = "\nTipo de diseño inválido.";
                                return new ArrayList<>();

                        }

                        Element currentBlock = (Element) readedBlocks.item(k);

                        NodeList readedIdea = currentBlock.getElementsByTagName("idea");

                        Block newBlock = new Block();
                        
                        for (int l = 0; l < readedIdea.getLength(); l++) {

                            Idea newIdea = new Idea();

                            Element currentIdea = (Element) readedIdea.item(l);

                            int aparitionOrder = 0;

                            try {
                                aparitionOrder = Math.abs(Integer.parseInt(currentIdea.getAttribute("orden")));
                                if (aparitionOrder < 0) {
                                    this.parsingError = "\nOrden de apareción inválido en: " + newSlide.getTitle() + ".";
                                    return new ArrayList<>();
                                }
                            } catch (Exception e) {

                                this.parsingError = "\nOrden de apareción inválido en: " + newSlide.getTitle() + ".";
                                return new ArrayList<>();
                            }

                            newIdea.setAparitionOrder(aparitionOrder);

                            NodeList textNode = currentIdea.getElementsByTagName("texto");
                            
                            int handwriteIdeaCounter = 0;
                            for (int m = 0; m < textNode.getLength(); m++) {

                                Element currentText = (Element) textNode.item(m);

                                Text newText = new Text();
                                
                                String tempText = currentText.getTextContent();
                                
                                tempText = tempText.replaceAll("ltdestacargt", "<destacar>");
                                tempText = tempText.replaceAll("lt/destacargt", "</destacar>");
                                tempText = tempText.replaceAll("ltenfatizargt", "<enfatizar>");
                                tempText = tempText.replaceAll("lt/enfatizargt", "</enfatizar>");
                                
                                newText.setContent(tempText);

                                newText.setType(currentText.getAttribute("tipo"));

                                switch (newText.getType()) {
                                    case "normal":
                                        newText.setHand(false);
                                        break;
                                    case "manuscrito":
                                        handwriteIdeaCounter+=1;
                                        /*switch(currentText.getAttribute("mano")){
                                         case "mostrar":
                                         newText.setHand(true);
                                         break;
                                         case "ocultar":
                                         newText.setHand(false);
                                         default:
                                         this.parsingError = "\nEn textos manuscritos se ha de especificar si deben o no ser escritos a mano.";
                                         return new ArrayList<ObjetoAprendizaje>();
                                         }*/
                                        newText.setHand(true);
                                        break;
                                    case "codigo":
                                        newText.setHand(false);
                                        break;
                                    case "ejemplo":
                                        newText.setHand(false);
                                        break;
                                    default:
                                        this.parsingError = "\nTipo de texto no soportado.";
                                        return new ArrayList<>();
                                }
                                
                                if(handwriteIdeaCounter>1){
                                    this.parsingError = "Sólo puede haber un texto manuscrito por Idea.";
                                    return new ArrayList<>();
                                }
                                
                                newIdea.addText(newText);
                            }                                                    
                            
                            NodeList mediaNode = currentIdea.getElementsByTagName("media");

                            for (int mediaCount = 0; mediaCount < mediaNode.getLength(); mediaCount++) {

                                Element currentMedia = (Element) mediaNode.item(mediaCount);

                                Media newMedia = new Media();

                                switch (currentMedia.getAttribute("tipo")) {
                                    case "imagen":
                                        newMedia.setType("imagen");
                                        break;
                                    /*case "audio":
                                     newMedia.setType("audio");
                                     break;*/
                                    //CASE VIDEO
                                    default:
                                        this.parsingError = "\nTipo multimedia no soportado.";
                                        return new ArrayList<>();
                                }

                                newMedia.setContent(currentMedia.getTextContent());
                                newIdea.addMedia(newMedia);
                            }
                            
                            NodeList examplesNode = currentIdea.getElementsByTagName("ejemplos");
                            
                            List<Example> newExamples = new ArrayList<>();
                                                       
                            for(int examplesNodeCount = 0; examplesNodeCount < examplesNode.getLength(); examplesNodeCount++){
                                Element currentExamplesNode = (Element) examplesNode.item(examplesNodeCount);
                                
                                NodeList exampleNode = currentExamplesNode.getElementsByTagName("ejemplo");

                                for(int exampleNodeCount = 0; exampleNodeCount < exampleNode.getLength(); exampleNodeCount++){
                                    
                                    Element currentExampleNode = (Element) exampleNode.item(exampleNodeCount);
                                    
                                    //ADD TEXT
                                    
                                    Example newExample = new Example();
                                    
                                    NodeList exampleTextNode = currentExampleNode.getElementsByTagName("texto_ejemplo");
                                    int handwritedExampleCounter = 0;
                                    for(int eTN = 0; eTN < exampleTextNode.getLength(); eTN++){
                                        Text newText2 = new Text();
                                        
                                        Element currentExampleText = (Element) exampleTextNode.item(eTN);
                                        
                                        newText2.setContent(currentExampleText.getTextContent());
                                        
                                        String currentExampleTextType = currentExampleText.getAttribute("tipo");
                                        
                                        newText2.setHand(false);
                                        
                                        switch(currentExampleTextType){
                                            case "normal":
                                                break;
                                            case "codigo":
                                                break;
                                            case "manuscrito":
                                                if(handwritedExampleCounter>1){
                                                    this.parsingError = "Sólo se admite un texto manuscrito por ejemplo.";
                                                    return new ArrayList<>();
                                                }
                                                newText2.setHand(true);
                                                handwritedExampleCounter++;
                                                break;
                                            default:
                                                this.parsingError = "Tipo de texto no soportado para ejemplos.";
                                                return new ArrayList<>();
                                        }
                                        
                                        newText2.setType(currentExampleTextType);
                                        
                                        
                                        newExample.addTextContent(newText2);
                                    }    
                                    
                                    //ADD MEDIA
                                    NodeList exampleMediaNode = currentExampleNode.getElementsByTagName("media_ejemplo");
                                    
                                    for(int eMN = 0; eMN < exampleMediaNode.getLength(); eMN++){
                                        
                                        Element currentExampleMedia = (Element) exampleMediaNode.item(eMN);
                                        
                                        Media newMedia2 = new Media();
                                        
                                        if(!currentExampleMedia.getAttribute("tipo").equals("imagen")){
                                            this.parsingError = "Tipo multimedia para ejemplo no soportado";
                                            return new ArrayList<>();
                                        }
                                        
                                        newMedia2.setType("imagen");
                                        newMedia2.setContent(currentExampleMedia.getTextContent());
                                        newExample.addMediaContent(newMedia2);
                                    }
                                    
                                    if(newExample.getMediaContent().isEmpty() && newExample.getTextContent().isEmpty()){
                                        this.parsingError = "Debe, al menos, existir un texto o un media en la etiqueta ejemplos.";
                                        return new ArrayList<>();
                                    }
                                    
                                    newExamples.add(newExample);
                                }                               
                            }
                            
                            
                            
                            newIdea.setExample(newExamples);
                                                       
                            NodeList voiceNode = currentIdea.getElementsByTagName("voz");

                            if (voiceNode.getLength() > 0) {
                                newIdea.setVoice(voiceNode.item(0).getTextContent());
                            } else if (voiceNode.getLength() >= 1) {

                                this.parsingError = "\n Sólo se permite una voz por idea.";
                                return new ArrayList<>();
                            }

                            newBlock.addIdeas(newIdea);
                        }
                        newSlide.addBlocks(newBlock);
                    }

                    newOA.addContent(newSlide);
                }
                
                //EVALUACIONES
                
                NodeList quizSetNode = objectNode.getElementsByTagName("evaluacion");
                
                for(int tests = 0; tests < quizSetNode.getLength(); tests++){
                    Test newTest = new Test();
                    
                    Element currentTestNode = (Element) quizSetNode.item(tests);
                    
                    newTest.setExigency(Math.abs(Integer.parseInt(currentTestNode.getAttribute("exigencia"))));
                    
                    if(newTest.getExigency() > 100){
                        this.parsingError = "La exigencia de una evaluación no debe ser superior a 100%";
                        return new ArrayList<>();
                    }
                    
                    NodeList choiceNode = currentTestNode.getElementsByTagName("pregunta");
                    
                    for(int choices = 0; choices < choiceNode.getLength(); choices++){
                        Question newForm = new Question();
                        
                        Element currentChoiceNode = (Element) choiceNode.item(choices);
                        
                        NodeList formNode = currentChoiceNode.getElementsByTagName("forma");
                        
                        for(int forms = 0; forms < formNode.getLength(); forms++){
                             
                            Form newQuestion = new Form();
                            
                            Element currentFormNode = (Element) formNode.item(forms);
                            
                            //Enunciado
                            NodeList enunNode = currentFormNode.getElementsByTagName("enunciado");
                            
                            for(int enunCount = 0; enunCount < enunNode.getLength(); enunCount++){                             
                                
                                Element currentEnun = (Element) enunNode.item(enunCount);
                                
                                NodeList enunTextNode = currentEnun.getElementsByTagName("texto");
                                
                                for(int enunTextCount = 0; enunTextCount < enunTextNode.getLength(); enunTextCount++){
                                    Text newEnunText = new Text();
                                    
                                    Element currentEnunText = (Element) enunTextNode.item(enunTextCount);
                                    
                                    newEnunText.setContent(currentEnunText.getTextContent());
                                    newEnunText.setHand(false);
                                    newEnunText.setType("normal");
                                    
                                    newQuestion.addTextContent(newEnunText);
                                }                 
                                
                                NodeList enunMediaNode = currentEnun.getElementsByTagName("media");
                                
                                for(int enunMediaCount = 0; enunMediaCount< enunMediaNode.getLength(); enunMediaCount++){
                                    Media newEnunMedia = new Media();
                                    
                                    Element currentEnunMedia = (Element) enunMediaNode.item(enunMediaCount);
                                    
                                    newEnunMedia.setType("imagen");
                                    newEnunMedia.setContent(currentEnunMedia.getTextContent());
                                    
                                    newQuestion.addMediaContent(newEnunMedia);
                                }
                                
                            }
                 
                            //Alternativas
                            NodeList optionNode = currentFormNode.getElementsByTagName("opciones");
                            
                            for(int optionCount = 0; optionCount < optionNode.getLength(); optionCount++){
                                
                                Element currentOptionNode = (Element) optionNode.item(optionCount);
                                
                                NodeList choiceNode2 = currentOptionNode.getElementsByTagName("alternativa");
                                
                                for(int choiceNodeCount = 0; choiceNodeCount < choiceNode2.getLength(); choiceNodeCount++){
                                    Choice newChoice = new Choice();
                                    
                                    Element currentChoice = (Element)choiceNode2.item(choiceNodeCount);
                                    
                                    NodeList choiceTextNode = currentChoice.getElementsByTagName("texto");
                                    
                                    for(int choiceTextCount = 0; choiceTextCount < choiceTextNode.getLength();choiceTextCount++){
                                        Text newChoiceText = new Text();
                                        
                                        Element currentChoiceText = (Element) choiceTextNode.item(choiceTextCount);

                                        newChoiceText.setHand(false);
                                        newChoiceText.setType("normal");
                                        newChoiceText.setContent(currentChoiceText.getTextContent());
                                        
                                        
                                        
                                        newChoice.addTextContent(newChoiceText);
                                    }
                                    
                                    NodeList choiceMediaNode = currentChoice.getElementsByTagName("media");
                                    
                                    for(int choiceTextCount = 0; choiceTextCount < choiceTextNode.getLength();choiceTextCount++){
                                        Media newChoiceMedia = new Media();
                                        
                                        Element currentChoiceText = (Element) choiceTextNode.item(choiceTextCount);
                                        
                                        newChoiceMedia.setType("imagen");
                                        newChoiceMedia.setContent(currentChoiceText.getTextContent());
                                        
                                        newChoice.addMediaContent(newChoiceMedia);
                                    }
                                    newChoice.setTopic(currentChoice.getAttribute("tema"));
                                    newChoice.setType(currentChoice.getAttribute("tipo"));
                                    newQuestion.addChoices(newChoice);
                                }
                                
                            }
                            
                            //Solucionario
                            NodeList solutionNode = currentFormNode.getElementsByTagName("solucion");
                            
                            for(int solutionCount = 0; solutionCount < solutionNode.getLength(); solutionCount++){
                                
                                Element currentSolutionNode = (Element) solutionNode.item(solutionCount);
                                
                                NodeList solutionTextNode = currentSolutionNode.getElementsByTagName("texto");
                                
                                for(int solutionTextCount = 0; solutionTextCount < solutionTextNode.getLength(); solutionTextCount++){
                                    
                                    Text newText = new Text();
                                    
                                    Element currentSolutionTextNode = (Element) solutionTextNode.item(solutionTextCount);
                                    
                                    newText.setHand(false);
                                    newText.setType("normal");
                                    newText.setContent(currentSolutionTextNode.getTextContent());
                                    
                                    newQuestion.addSolutionTextContent(newText);
                                }
                                
                                NodeList solutionMediaNode = currentSolutionNode.getElementsByTagName("media");
                                
                                for(int solutionMediaCount = 0; solutionMediaCount < solutionMediaNode.getLength(); solutionMediaCount++){
                                    
                                    Media newMedia = new Media();
                                    
                                    Element currentSolutionMediaNode = (Element) solutionTextNode.item(solutionMediaCount);
                                    
                                    newMedia.setType("imagen");
                                    newMedia.setContent(currentSolutionMediaNode.getTextContent());
                                    
                                    newQuestion.addSolutionMediaContent(newMedia);
                                }
                                                            
                                NodeList solutionVoiceNode = currentSolutionNode.getElementsByTagName("voz");
                                if(solutionVoiceNode.getLength()>0){
                                    newQuestion.setVoice(solutionVoiceNode.item(0).getTextContent());
                                }
                            }
                            
                            newForm.addForm(newQuestion);
                        }
                        newTest.addQuestion(newForm);
                    }                    
                    newOA.addQuiz(newTest);
                }    
                
                NodeList feedBackNode = objectNode.getElementsByTagName("feedback");
                
                if(feedBackNode.getLength() > 1){
                    this.fileContent = "Sólo puede haber un elemento de feedback.";
                    return new ArrayList<>();
                }                       
                               
                FeedBack newFeedBack = new FeedBack();
                       
                for(int fbi = 0; fbi < feedBackNode.getLength(); fbi++){
                    Element currentFeedBack = (Element) feedBackNode.item(0);
                    newFeedBack.setLink(currentFeedBack.getTextContent());
                }
                               
                newOA.setFeedback(newFeedBack);
                
                Objects.add(newOA);
            }
        } catch (IOException | ParserConfigurationException | DOMException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return Objects;
    }

    /**
     * Toma el string recibido y lo lleva a un archivo temporal para poder ser
     * dado como parámetro a la función que realiza el parser del .xml
     *
     * @param str como string contenido del archivo.
     * @return String como Archivo xml
     * @throws IOException
     */
    public File stringToFile(String str) throws IOException {
        /*
         Recibe un string y lo procesa para que sea un archivo.
         Este archivo estará momentaneamente en el directorio del 
         programa, por ello, se le asigna el nombre que, probablemente,
         no genere conflictos con otro que se esté creando a la vez.
         */
        String chain = (Math.random() * 100000 + 1) + ".xml";
        File newFile;
        newFile = new File(chain);
        newFile.setWritable(true);
        try (FileWriter fw = new FileWriter(newFile)) {
            fw.write(str);
        }
        return newFile;
    }

    public void preProcessText() {
        /*
         Inicio: Reemplazar <destacado> y </destacado> por /d
         */
        String destacado[] = this.fileContent.split("<destacar>");
        String temp = destacado[0];
        for (int i = 1; i == destacado.length - 1; i++) {
            temp += "ltdestacargt" + destacado[i];
        }
        this.fileContent = temp;
        temp = "";
        String destacado2[] = this.fileContent.split("</destacar>");
        temp += destacado2[0];
        for (int i = 1; i == destacado2.length - 1; i++) {
            temp += "lt/destacargt" + destacado2[i];
        }
        this.fileContent = temp;
        temp = ""; 

        /*
         FIN: reemplazo de destacado
         Inicio: Reemplazar <enfatizado> y </enfatizado> por /d
         */
        String enfatizado[] = this.fileContent.split("<enfatizar>");
        temp += enfatizado[0];
        for (int i = 1; i == enfatizado.length - 1; i++) {
            temp += "ltenfatizargt" + enfatizado[i];
        }
        this.fileContent = temp;
        temp = "";

        String enfatizado2[] = this.fileContent.split("</enfatizar>");
        temp += enfatizado2[0];
        for (int i = 1; i == enfatizado2.length - 1; i++) {
            temp += "lt/enfatizargt" + enfatizado2[i];
        }
        this.fileContent = temp;
        temp = "";
    }

    public void AppendDTD() {
        this.fileContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<!DOCTYPE objeto [\n"
                + "<!ELEMENT objeto (escena*,evaluacion*,feedback?)>\n"
                + "<!ELEMENT escena (bloque+)>\n"
                + "<!ELEMENT bloque (idea+)>\n"
                + "<!ELEMENT idea (texto*, media*,ejemplos?,voz?)>\n"
                + "<!ELEMENT texto (#PCDATA)>\n"
                + "<!ELEMENT voz (#PCDATA)>\n"
                + "<!ELEMENT media (#PCDATA)>\n"
                + "<!ELEMENT evaluacion (pregunta+)>\n"
                + "<!ELEMENT pregunta (forma+)>\n"
                + "<!ELEMENT forma (enunciado,opciones,solucion?)>\n"
                + "<!ELEMENT enunciado (texto?,media?)>\n"
                + "<!ELEMENT opciones (alternativa*)>\n"
                + "<!ELEMENT alternativa (texto?,media?)>\n"
                + "<!ELEMENT solucion (texto?,media?,voz?)>\n"
                + "<!ELEMENT feedback (#PCDATA)>\n"
                + "<!ELEMENT ejemplos (ejemplo*)>\n"
                + "<!ELEMENT ejemplo (texto_ejemplo*,media_ejemplo?)>\n"
                + "<!ELEMENT texto_ejemplo (#PCDATA)>\n"
                + "<!ELEMENT media_ejemplo (#PCDATA)>\n"             
                + "\n"
                + "\n"
                + "<!ATTLIST objeto titulo CDATA #REQUIRED>\n"
                + "<!ATTLIST objeto autor CDATA #REQUIRED>\n"
                + "<!ATTLIST objeto tema CDATA #REQUIRED>\n"
                + "<!ATTLIST escena titulo CDATA #REQUIRED>\n"
                + "<!ATTLIST escena tipo CDATA #REQUIRED>\n"
                + "\n"
                + "<!ATTLIST idea orden CDATA #REQUIRED>\n"
                + "<!ATTLIST texto tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST media tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST texto_ejemplo tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST media_ejemplo tipo CDATA #REQUIRED>\n"
                + "\n"
                + "<!ATTLIST evaluacion exigencia CDATA #REQUIRED>\n"
                + "<!ATTLIST alternativa tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST alternativa tema CDATA #REQUIRED>\n"
                + "]>"
                + this.fileContent;
    }
}
