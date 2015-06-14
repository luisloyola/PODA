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
        this.fileContent = this.parsingError = "";
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
     * @throws java.io.IOException
     */
    public List<LearningObject> readOA() throws IOException {

        File errorLog = new File("log.txt","");
        FileWriter logfw;
        logfw = new FileWriter(errorLog,true);
        
        List<LearningObject> Objects = new ArrayList<>();
        
        if (this.fileContent == null) {
            return Objects;
        }
        
        /*
            Agrega el DTD al archivo.
        */        
        
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
                        this.parsingError = this.errorTranslate(this.parsingError);
                        System.out.println(errorHandler.getErrorMessage());
                        logfw.write(parsingError+"\n");
                        logfw.close();
                        OA_XML_File.delete();
                        return Objects;
                    }
                else{
                    OA_XML_File.delete();
                }
            } catch (org.xml.sax.SAXException e) {
                /*Error en el parser*/
                this.parsingError = e.getLocalizedMessage();
                this.parsingError = this.errorTranslate(this.parsingError);
                logfw.write(parsingError+"\n");
                logfw.close();
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
                                    logfw.write(parsingError+"\n");
                                    logfw.close();
                                    return new ArrayList<>();
                                }
                                break;
                            case "1Fil2Col":
                                if (readedBlocks.getLength() != 3) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    logfw.write(parsingError+"\n");
                                    logfw.close();
                                    return new ArrayList<>();
                                }
                                break;
                            case "2Col":
                                if (readedBlocks.getLength() != 2) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    logfw.write(parsingError+"\n");
                                    logfw.close();
                                    return new ArrayList<>();
                                }
                                break;
                            case "3Col":
                                if (readedBlocks.getLength() != 3) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    logfw.write(parsingError+"\n");
                                    logfw.close();
                                    return new ArrayList<>();
                                }
                                break;
                            case "1Fil3Col":
                                if (readedBlocks.getLength() != 4) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    logfw.write(parsingError+"\n");
                                    logfw.close();
                                    return new ArrayList<>();
                                }
                                break;
                            case "2Fil2Col":
                                if (readedBlocks.getLength() != 4) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    logfw.write(parsingError+"\n");
                                    logfw.close();
                                    return new ArrayList<>();
                                }
                                break;
                            case "2Col1Fil":
                                if (readedBlocks.getLength() != 3) {
                                    this.parsingError = "\nCantidad de bloques inválido.";
                                    logfw.write(parsingError+"\n");
                                    logfw.close();
                                    return new ArrayList<>();
                                }
                                break;
                            default:
                                this.parsingError = "\nTipo de diseño inválido.";
                                logfw.write(parsingError+"\n");
                                logfw.close();
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
                                    logfw.write(parsingError+"\n");
                                    logfw.close();
                                    return new ArrayList<>();
                                }
                            } catch (Exception e) {

                                this.parsingError = "\nOrden de apareción inválido en: " + newSlide.getTitle() + ".";
                                logfw.write(parsingError+"\n");
                                logfw.close();
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
                                        logfw.write(parsingError+"\n");
                                        logfw.close();
                                        return new ArrayList<>();
                                }
                                
                                if(handwriteIdeaCounter>1){
                                    this.parsingError = "Sólo puede haber un texto manuscrito por Idea.";
                                    logfw.write(parsingError+"\n");
                                    logfw.close();
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
                                        logfw.write(parsingError+"\n");
                                        logfw.close();
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
                                                    logfw.write(parsingError+"\n");
                                                    logfw.close();
                                                    return new ArrayList<>();
                                                }
                                                newText2.setHand(true);
                                                handwritedExampleCounter++;
                                                break;
                                            default:
                                                this.parsingError = "Tipo de texto no soportado para ejemplos.";
                                                logfw.write(parsingError+"\n");
                                                logfw.close();
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
                                            logfw.write(parsingError+"\n");
                                            logfw.close();
                                            return new ArrayList<>();
                                        }
                                        
                                        newMedia2.setType("imagen");
                                        newMedia2.setContent(currentExampleMedia.getTextContent());
                                        newExample.addMediaContent(newMedia2);
                                    }
                                    
                                    if(newExample.getMediaContent().isEmpty() && newExample.getTextContent().isEmpty()){
                                        this.parsingError = "Debe, al menos, existir un texto o un media en la etiqueta ejemplos.";
                                        logfw.write(parsingError+"\n");
                                        logfw.close();
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
                                logfw.write(parsingError+"\n");
                                logfw.close();
                                return new ArrayList<>();
                            }

                            NodeList subIdeas = currentIdea.getElementsByTagName("subidea");
                            
                            for(int sI = 0; sI < subIdeas.getLength(); sI++){
                                Element currentSubIdea = (Element) subIdeas.item(sI);
                                SubIdea newSubIdea = new SubIdea();
                                
                                try{
                                    newSubIdea.setAparitionOrder(Math.abs(Integer.parseInt(currentSubIdea.getAttribute("orden"))));
                                }
                                catch(Exception e){
                                    this.parsingError = "El orden de una subidea debe ser un número entero positivo.";
                                    return new ArrayList<>();
                                }
                                
                                NodeList subTexts = currentSubIdea.getElementsByTagName("subtexto");
                                
                                for(int sT = 0; sT < subTexts.getLength(); sT++){
                                    Element currentSubText = (Element) subTexts.item(sT);
                                    
                                    SubText newSubText = new SubText();
                                    newSubText.setVoice(currentSubText.getAttribute("voz"));
                                    newSubText.setContent(currentSubText.getTextContent());
                                    
                                    newSubIdea.addSubIdeaContent(newSubText);
                                }
                                
                                newIdea.addSubIdea(newSubIdea);
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
                    
                    newTest.setExigency(Math.abs(Integer.parseInt(currentTestNode.getAttribute("exigencia_min"))));
                    
                    newTest.setSecondExigency(Math.abs(Integer.parseInt(currentTestNode.getAttribute("exigencia_max"))));
                    
                    if(newTest.getExigency() > 100 || newTest.getSecondExigency() > 100){
                        this.parsingError = "Las exigencias de una evaluación no debe ser superior a 100%";
                        logfw.write(parsingError+"\n");
                        logfw.close();
                        return new ArrayList<>();
                    }
                    
                    if(newTest.getExigency() > newTest.getSecondExigency()){
                        this.parsingError = "exigencia_min debe ser menor a exigencia_max.";
                        logfw.write(parsingError+"\n");
                        logfw.close();
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
                                
                                int enunManusCount = 0;
                                for(int enunTextCount = 0; enunTextCount < enunTextNode.getLength(); enunTextCount++){
                                    Text newEnunText = new Text();
                                    
                                    Element currentEnunText = (Element) enunTextNode.item(enunTextCount);
                                    
                                    newEnunText.setContent(currentEnunText.getTextContent());
                                    newEnunText.setHand(false);
                                    newEnunText.setType(currentEnunText.getAttribute("tipo"));
                                    if(newEnunText.getType().equals("manuscrito")){
                                        enunManusCount++;
                                    }
                                    if(enunManusCount>1){
                                        this.parsingError = "sólo puede existir un texto manuscrito por enunciado.";
                                        logfw.write(parsingError+"\n");
                                        logfw.close();
                                        return new ArrayList<>();
                                    }
                                    
                                    newQuestion.addTextContent(newEnunText);
                                }                 
                                                                
                            }
                 
                            //Alternativas
                            NodeList optionNode = currentFormNode.getElementsByTagName("opciones");
                            
                            for(int optionCount = 0; optionCount < optionNode.getLength(); optionCount++){
                                
                                Element currentOptionNode = (Element) optionNode.item(optionCount);
                                
                                NodeList choiceNode2 = currentOptionNode.getElementsByTagName("alternativa");
                                
                                if(choiceNode2.getLength() > 7){
                                    this.parsingError = "Sólo es posible agregar hasta 7 alternativas.";
                                    logfw.write(parsingError+"\n");
                                    logfw.close();
                                    return new ArrayList<>();
                                }
                                
                                for(int choiceNodeCount = 0; choiceNodeCount < choiceNode2.getLength(); choiceNodeCount++){
                                    Choice newChoice = new Choice();
                                    
                                    Element currentChoice = (Element)choiceNode2.item(choiceNodeCount);
                                    
                                    NodeList choiceTextNode = currentChoice.getElementsByTagName("texto");
                                    
                                    int choiceManusCount = 0;
                                    for(int choiceTextCount = 0; choiceTextCount < choiceTextNode.getLength();choiceTextCount++){
                                        Text newChoiceText = new Text();
                                        
                                        Element currentChoiceText = (Element) choiceTextNode.item(choiceTextCount);

                                        newChoiceText.setHand(false);
                                        newChoiceText.setType(currentChoiceText.getAttribute("tipo"));
                                        newChoiceText.setContent(currentChoiceText.getTextContent());
                                        if(newChoiceText.getType().equals("manuscrito")){
                                            choiceManusCount++;
                                        }
                                        if(choiceManusCount>1){
                                            this.parsingError = "sólo puede existir un texto manuscrito en una alternativa.";
                                            logfw.write(parsingError+"\n");
                                            logfw.close();
                                            return new ArrayList<>();
                                        }
                                        
                                        
                                        newChoice.addTextContent(newChoiceText);
                                    }
                                    
                                    newChoice.setTopic(currentChoice.getAttribute("tema"));
                                    
                                    newChoice.setType(currentChoice.getAttribute("tipo"));
                                    
                                    switch(newChoice.getType()){
                                        case "solucion":
                                            break;
                                        case "distractor":
                                            break;
                                        default:
                                            this.parsingError = "Sólo es posible asignar \"solución\" ó \"distractor\" como tipo de alternativa.";
                                            logfw.write(parsingError+"\n");
                                            logfw.close();
                                            return new ArrayList<>();
                                    }
                                    
                                    newQuestion.addChoices(newChoice);
                                }
                                
                            }
                            
                            //Solucionario
                            NodeList solutionNode = currentFormNode.getElementsByTagName("solucion");
                            
                            for(int solutionCount = 0; solutionCount < solutionNode.getLength(); solutionCount++){
                                
                                Element currentSolutionNode = (Element) solutionNode.item(solutionCount);
                                
                                NodeList solutionTextNode = currentSolutionNode.getElementsByTagName("texto");
                                
                                int soluManusCount = 0;
                                for(int solutionTextCount = 0; solutionTextCount < solutionTextNode.getLength(); solutionTextCount++){
                                    
                                    Text newText = new Text();
                                    
                                    Element currentSolutionTextNode = (Element) solutionTextNode.item(solutionTextCount);
                                    
                                    newText.setHand(false);
                                    newText.setType(currentSolutionTextNode.getAttribute("tipo"));
                                    newText.setContent(currentSolutionTextNode.getTextContent());
                                    if(newText.getType().equals("manuscrito")){
                                        soluManusCount++;
                                    }
                                    if(soluManusCount>1){
                                        this.parsingError = "sólo puede existir un texto manuscrito en una alternativa.";
                                        logfw.write(parsingError+"\n");
                                        logfw.close();
                                        return new ArrayList<>();
                                    }
                                    newQuestion.addSolutionTextContent(newText);
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
                    logfw.write(parsingError+"\n");
                    logfw.close();
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
            logfw.write(e.getLocalizedMessage()+"\n");
            logfw.close();
            return new ArrayList<>();
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

    public String preProcessText(String xml) {
        xml = xml.replaceAll("<destacar>","&lt;destacar&gt;");
        xml = xml.replaceAll("</destacar>","&lt;/destacar&gt;");
        xml = xml.replaceAll("<enfatizar>","&lt;enfatizar&gt;");
        xml = xml.replaceAll("</enfatizar>","&lt;/enfatizar&gt;");
        xml = xml.replaceAll("<tab/>","&lt;tab/&gt;");
        return xml;
    }

    public void AppendDTD() {
        this.fileContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<!DOCTYPE objeto [\n"
                + "<!ELEMENT objeto (escena*,evaluacion?,feedback?)>\n"
                + "<!ELEMENT escena (bloque+)>\n"
                + "<!ELEMENT bloque (idea+)>\n"
                + "<!ELEMENT idea (texto*, media*,ejemplos?,voz?,subidea*)>\n"
                + "<!ELEMENT texto (#PCDATA)>\n"
                + "<!ELEMENT voz (#PCDATA)>\n"
                + "<!ELEMENT media (#PCDATA)>\n"
                + "<!ELEMENT evaluacion (pregunta+)>\n"
                + "<!ELEMENT pregunta (forma+)>\n"
                + "<!ELEMENT forma (enunciado,opciones,solucion)>\n"
                + "<!ELEMENT enunciado (texto*)>\n"
                + "<!ELEMENT opciones (alternativa*)>\n"
                + "<!ELEMENT alternativa (texto*)>\n"
                + "<!ELEMENT solucion (texto*)>\n"
                + "<!ELEMENT feedback (#PCDATA)>\n"
                + "<!ELEMENT ejemplos (ejemplo*)>\n"
                + "<!ELEMENT ejemplo (texto_ejemplo*,media_ejemplo?)>\n"
                + "<!ELEMENT texto_ejemplo (#PCDATA)>\n"
                + "<!ELEMENT media_ejemplo (#PCDATA)>\n"     
                + "<!ELEMENT subidea (subtexto*)>\n"
                + "<!ELEMENT subtexto (#PCDATA)>\n"   
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
                + "<!ATTLIST evaluacion exigencia_min CDATA #REQUIRED>\n"
                + "<!ATTLIST evaluacion exigencia_max CDATA #REQUIRED>\n"
                + "<!ATTLIST alternativa tipo CDATA #REQUIRED>\n"
                + "<!ATTLIST alternativa tema CDATA #REQUIRED>\n"
                + "<!ATTLIST subidea orden CDATA #REQUIRED>\n"
                + "<!ATTLIST subtexto voz CDATA #REQUIRED>\n"
                + "]>"
                + this.fileContent;
    }
    
    public String errorTranslate(String error){
        if(error.equals("XML document structures must start and end within the same entity.")){
            error = "Los documentos con estructura XML deben comenzar y finalizar con la misma entidad. Verifique su XML.";
        }
        else if(error.equals("Content is not allowed in prolog.")){
            error = "Por favor, siga la sintaxis XML. Elementos fuera de \"<\" o \">\" no son soportados.";
        }
        else{
            String[] err = error.split(" ");
            /*for(int i = 0; i < err.length; i++){
                System.out.println("Item["+i+"]: "+err[i]);
            }*/
            
            switch(err.length){
                case 6:
                    return "El elemento: "+err[2]+", debe ser declarado. Por ello no es permitido.";
                case 9:{
                         
                         if(err[0].equals("Attribute")
                                 && err[2].equals("must")
                                 && err[3].equals("be")
                                 && err[4].equals("declared")
                                 && err[5].equals("for")
                                 && err[6].equals("element")
                                 && err[7].equals("type")
                                 ){
                             return "El elemento: "+err[8]+", no tiene declarado un atributo: "+err[1]+", revise su sintáxis.";
                         }
                         else if(err[0].equals("The")
                                 && err[1].equals("content")
                                 && err[2].equals("of")
                                 && err[3].equals("element")
                                 && err[4].equals("type")
                                 && err[6].equals("must")
                                 && err[7].equals("match")
                                 ){
                                 if(err[8].equals("\"null\".")){
                                     return "El contenido de "+err[5]+" debe ser un valor, no una etiqueta.";
                                 }
                                 else{
                                     return "El contenido de "+err[5]+" debe ser: "+err[8]+".";
                                 }
                         }
                         else{
                             return error;
                         }
                    }
                case 12:{
                        if(
                           err[0].equals("Attribute")
                        && err[2].equals("is")
                        && err[3].equals("required")
                        && err[4].equals("and")
                        && err[5].equals("must")
                        && err[6].equals("be")
                        && err[7].equals("specified")
                        && err[8].equals("for")
                        && err[9].equals("element")
                        && err[10].equals("type")                                
                                ){
                            return "El atributo: "+err[1]+", es requerido para el elemento: "+err[11]+".";
                        }
                        else if(
                                err[0].equals("The")
                             && err[1].equals("content")
                             && err[2].equals("of")
                             && err[3].equals("element")
                             && err[4].equals("type")
                             && err[6].equals("is")
                             && err[7].equals("incomplete,")
                             && err[8].equals("it")
                             && err[9].equals("must")
                             && err[10].equals("match")                             
                                ){
                                return "El contenido del elemento "+err[5]+", está incompleto, debe ser: "+err[11]+".";
                        }
                        else if(error.equals("The content of elements must consist of well-formed character data or markup.")                                ){
                            return "El contenido de los elementos debe consistir en un caracter o marca bien formada.";
                        }
                        else if(
                                err[0].equals("The")
                             && err[1].equals("element")
                             && err[2].equals("type")
                             && err[4].equals("must")
                             && err[5].equals("be")
                             && err[6].equals("terminated")
                             && err[7].equals("by")
                             && err[8].equals("the")
                             && err[9].equals("matching")
                             && err[10].equals("end-tag")                              
                                ){
                            return "Los elementos "+err[3]+", deben terminar con la etiqueta de cierre: "+err[11];
                        }
                        else if(
                                err[0].equals("The")
                             && err[1].equals("end-tag")
                             && err[2].equals("for")
                             && err[3].equals("element")
                             && err[4].equals("type")
                             && err[6].equals("must")
                             && err[7].equals("end")
                             && err[8].equals("with")
                             && err[9].equals("a")
                             && err[11].equals("delimiter.")                              
                                ){
                            return "El tag de cierre de: "+err[3]+", debe terminar con: "+err[10]+".";
                        }
                        else if(error.equals("The markup in the document following the root element must be well-formed.")){
                            return "El marcador en el documento siguiente a la raíz debe ser un elemento bien formado.";
                        }
                        else{
                            return error;
                        }
                    }
                case 13:
                    return "En el elemento: "+err[12]+", el atributo: "+err[6]+", debe de tener comillas (\") que contengan su valor.";
                case 18:
                    return "En el elemento: "+err[8]+", el atributo: "+err[2]+", debe ser seguido del caracter \"'='\" y luego su contenido.";
                case 17:
                    return "El valor del atriburo: "+err[4]+", asociado al elemento: "+err[10]+", no debe contener el caracter \"<\" ó \">\".";
                default:
                    return error;
            }
        }
        return error;
    }
}
