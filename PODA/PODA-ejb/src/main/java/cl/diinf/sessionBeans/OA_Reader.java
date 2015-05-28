
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


/**
 *
 * @author teamPODA
 */
public class OA_Reader {
    private String parsingError;
    private String fileContent;
    
    public OA_Reader(){
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
     * @return Objects como lista de objetos de aprendizaje.  
     */
    public List<ObjetoAprendizaje> readOA() {
        
        List<ObjetoAprendizaje> Objects = new ArrayList<ObjetoAprendizaje>();
        
        if(this.fileContent == null){
            return Objects;
        }
        
        try {
            File OA_XML_File = this.stringToFile(fileContent);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setValidating(true);
            
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            SimpleErrorHandler errorHandler = new SimpleErrorHandler();
            
            dBuilder.setErrorHandler(errorHandler);
            Document doc = dBuilder.newDocument();
            
            try{
                
                doc = dBuilder.parse(OA_XML_File);
                if(!errorHandler.getErrorMessage().equals("NO_ERROR")){
                    System.out.println(errorHandler.getErrorMessage());
                    String[] tempErrorHandler = errorHandler.getErrorMessage().split(" ");
                    if(tempErrorHandler[0].equals("Attribute")){
                        this.parsingError = "En el elemento: "+tempErrorHandler[8]+" debe añadirse el atributo: "+tempErrorHandler[1];
                    }
                    else if(tempErrorHandler[0].equals("The") && tempErrorHandler[1].equals("content") && !tempErrorHandler[7].equals("incomplete,")){
                        this.parsingError = "El elemento: "+tempErrorHandler[5] +" debe cumplir el patrón: "+tempErrorHandler[8];
                    }
                    else if(tempErrorHandler[7].equals("incomplete,")){
                        this.parsingError = "El elemento: "+tempErrorHandler[5]+" Está incompleto, debe cumplir el patrón: "+tempErrorHandler[11];
                    }
                    else{
                        this.parsingError = errorHandler.getErrorMessage();
                    }
                    OA_XML_File.delete();
                    return Objects;
                }
                else{
                    OA_XML_File.delete();
                }
            }
            catch(org.xml.sax.SAXException e){
                /*Error en el parser*/
                OA_XML_File.delete();
                if(e.getLocalizedMessage().equals("Content is not allowed in prolog.")){
                    this.parsingError = "Error: Archivo no soportado.";
                }
                else{
                    String[] translate = e.getLocalizedMessage().split(" ");
                    this.parsingError = "Error: El elemento "+translate[3]+" debe terminar con "+translate[11];
                }
                
                return Objects;
            }
            
            doc.getDocumentElement().normalize();

            NodeList OA = doc.getElementsByTagName("objeto");

            /*
                Recorre todos los objetos encontrados en el archivo de entrada para
                devolver tantos ObjetoAprendizaje como hayan sido especificados.
            */
            
            /*
                En esta lista se guardar los objetos de aprendizaje que lean del archivo
            */
            List<ObjetoAprendizaje> newOAList = new ArrayList<ObjetoAprendizaje>();
            
            if(OA.getLength() == 0){
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
                ObjetoAprendizaje newOA = new ObjetoAprendizaje();         
                Node ObjectNode = OA.item(i);                
                Element currentNode = (Element) ObjectNode;     
                
                newOA.setTitle(currentNode.getAttribute("titulo"));
                newOA.setAuthor(currentNode.getAttribute("autor"));  
                newOA.setTemplate(currentNode.getAttribute("tema"));
                newOA.setCreationDate(new Date());
                String OA_nameFile = UUID.randomUUID().toString();
                OA_nameFile += ("" + System.currentTimeMillis() + "_OA");
                newOA.setName_file(OA_nameFile);
                
                Element slideNode = (Element) currentNode;
                NodeList slides = slideNode.getElementsByTagName("escena");  
                
                for(int j = 0; j < slides.getLength(); j++ ){
                        Slide newSlide = new Slide();
                        Element currentSlide = (Element) slides.item(j);
                        newSlide.setTitle(currentSlide.getAttribute("titulo"));
                        newSlide.setDesign(currentSlide.getAttribute("tipo"));
                        if(newSlide.getDesign().equals("")){
                            this.setParsingError("No se especificó diseño de escena: "+newSlide.getTitle()+".");
                            
                            newSlide.setDesign("1Col");
                            return new ArrayList<ObjetoAprendizaje>();
                        }
                        
                        NodeList readedBlocks  = currentSlide.getElementsByTagName("bloque");
                            
                        for(int k = 0; k < readedBlocks.getLength(); k++){
                            
                            switch(newSlide.getDesign()){
                                case "1Col":
                                    if(readedBlocks.getLength()  != 1){
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                        return new ArrayList<ObjetoAprendizaje>();
                                    }
                                    break;
                                case "1Fil2Col":
                                    if(readedBlocks.getLength()  != 3){
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                        return new ArrayList<ObjetoAprendizaje>();
                                    }
                                    break;
                                case "2Col":
                                    if(readedBlocks.getLength()  != 2){
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                        return new ArrayList<ObjetoAprendizaje>();
                                    }
                                    break;
                                case "3Col":
                                    if(readedBlocks.getLength()  != 3){
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                        return new ArrayList<ObjetoAprendizaje>();
                                    }
                                    break;
                                case "1Fil3Col":
                                    if(readedBlocks.getLength()  != 4){
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                        return new ArrayList<ObjetoAprendizaje>();
                                    }
                                    break;
                                case "2Fil2Col":
                                    if(readedBlocks.getLength()  != 4){
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                        return new ArrayList<ObjetoAprendizaje>();
                                    }
                                    break;
                                case "2Col1Fil":
                                    if(readedBlocks.getLength()  != 3){
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                        return new ArrayList<ObjetoAprendizaje>();
                                    }
                                    break;    
                                default:
                                    this.parsingError = "\nTipo de diseño inválido.";
                                    return new ArrayList<ObjetoAprendizaje>();
                                    
                            }
                            
                            
                            Element currentBlock = (Element) readedBlocks.item(k);
                            
                            NodeList readedIdea = currentBlock.getElementsByTagName("idea");

                            Block newBlock = new Block();
                            
                            for(int l = 0; l < readedIdea.getLength(); l++){

                                
                                Idea newIdea = new Idea();
                                
                                Element currentIdea = (Element) readedIdea.item(l);                                                     
                                
                                int aparitionOrder = 0  ;
                                
                                try{
                                    aparitionOrder = Integer.parseInt(currentIdea.getAttribute("ordenAparicion"));
                                    if(aparitionOrder < 0){
                                        this.parsingError = "\nOrden de apareción inválido en: "+newSlide.getTitle()+".";
                                        return new ArrayList<ObjetoAprendizaje>();
                                    }
                                }
                                catch(Exception e){
                                    
                                    this.parsingError = "\nOrden de apareción inválido en: "+newSlide.getTitle()+".";
                                    return new ArrayList<ObjetoAprendizaje>();
                                }
                                
                                newIdea.setAparitionOrder(aparitionOrder);
                                
                                NodeList textNode = currentIdea.getElementsByTagName("texto");
                                
                                for(int m = 0; m < textNode.getLength(); m++){
                                    
                                    Element currentText = (Element) textNode.item(m);
                                    
                                    Texto newText = new Texto();
                                    
                                    newText.setContent(currentText.getTextContent());
                                    
                                    newText.setType(currentText.getAttribute("tipo"));
                                    
                                    switch(newText.getType()){
                                        case "normal":
                                            newText.setHand(false);
                                            break;
                                        case "manuscrito":
                                            switch(currentText.getAttribute("mano")){
                                                case "mostrar":
                                                    newText.setHand(true);
                                                    break;
                                                case "ocultar":
                                                    newText.setHand(false);
                                                default:
                                                    this.parsingError = "\nEn textos manuscritos se ha de especificar si deben o no ser escritos a mano.";
                                                    return new ArrayList<ObjetoAprendizaje>();
                                            }
                                            break;
                                        case "codigo":
                                            newText.setHand(false);
                                            break;
                                        case "ejemplo":
                                            newText.setHand(false);
                                            break;
                                        default:
                                            this.parsingError = "\nTipo de texto no soportado.";
                                            return new ArrayList<ObjetoAprendizaje>();
                                    }
                                    
                                    newIdea.addText(newText);
                                    
                                }
                                
                                NodeList mediaNode = currentIdea.getElementsByTagName("media");
                                
                                for(int mediaCount = 0; mediaCount < mediaNode.getLength(); mediaCount++){
                                    
                                    Element currentMedia = (Element) mediaNode.item(mediaCount);
                                    
                                    Media newMedia = new Media();
                                    
                                    switch(currentMedia.getAttribute("tipo")){
                                        case "imagen":
                                            newMedia.setType("imagen");
                                            break;
                                        case "audio":
                                            newMedia.setType("audio");
                                            break;
                                        //CASE VIDEO
                                        default:
                                            this.parsingError = "\nTipo multimedia no soportado.";
                                            return new ArrayList<ObjetoAprendizaje>();                                 
                                    }
                                    
                                    newMedia.setContent(currentMedia.getTextContent());
                                    newIdea.addMedia(newMedia);
                                }
                                
                                NodeList voiceNode = currentIdea.getElementsByTagName("voz");
                                
                                if(voiceNode.getLength() > 0){
                                    newIdea.setVoice(voiceNode.item(0).getTextContent());
                                }
                                else if(voiceNode.getLength() >= 1){
                                    
                                    this.parsingError = "\n Sólo se permite una voz por idea.";
                                    new ArrayList<ObjetoAprendizaje>();
                                }
                                
                                NodeList quizSetNode = currentIdea.getElementsByTagName("evaluaciones");
                                
                                for(int qSN = 0; qSN < quizSetNode.getLength(); qSN++){
                                    Element currentQuizSet = (Element) quizSetNode.item(qSN);
                                    
                                    Prueba newQuizSet = new Prueba();
                                    
                                    NodeList quizNode = currentQuizSet.getElementsByTagName("evaluacion");
                                
                                    for(int n = 0; n < quizNode.getLength(); n++){

                                        Element currentQuiz = (Element)quizNode.item(n);

                                        Evaluacion newQuiz = new Evaluacion();

                                        NodeList currentHeader = currentQuiz.getElementsByTagName("enunciado");

                                        String header = "";

                                        for(int n1 = 0; n1 < currentHeader.getLength(); n1++){
                                            header+=currentHeader.item(n1).getTextContent();
                                        }

                                        newQuiz.setHeader(header);

                                        NodeList currentChoiceHead = currentQuiz.getElementsByTagName("opciones");

                                        if(currentChoiceHead.getLength() > 1){
                                            this.parsingError = "Sólo se admite un bloque \"opciones\" por evaluación";
                                            return new ArrayList<ObjetoAprendizaje>();
                                        }

                                        NodeList currentChoices = ((Element)currentChoiceHead.item(0)).getElementsByTagName("alternativa");

                                        for(int n2 = 0; n2 < currentChoices.getLength(); n2++){
                                            Alternativa newChoice = new Alternativa();
                                            newChoice.setContent(currentChoices.item(n2).getTextContent());
                                            newChoice.setTopic(((Element)currentChoices.item(n2)).getAttribute("tema"));
                                            String choiceType = ((Element)currentChoices.item(n2)).getAttribute("tipo");
                                            switch(choiceType){
                                                case "solucion":
                                                    break;
                                                case "distractor":
                                                    break;
                                                default:
                                                    this.parsingError = "Tipo de evaluación no soportado";
                                                    return new ArrayList<ObjetoAprendizaje>();                                               
                                            }

                                            newChoice.setType(choiceType);
                                            newQuiz.addChoices(newChoice);
                                        }

                                        newQuizSet.addQuiz(newQuiz);
                                    }
                                    
                                    newIdea.addQuiz(newQuizSet);
                                }
                                
                                newBlock.addIdeas(newIdea);
                            }
                            newSlide.addBlocks(newBlock);
                        }
                        
                        newOA.addContent(newSlide);
                }
                Objects.add(newOA);
            }    
        }
        catch (Exception e) {
            e.printStackTrace();
        }      
        
        
    return Objects;
}
    /**
     * Toma el string recibido y lo lleva a un archivo temporal para poder ser
     * dado como parámetro a la función que realiza el parser del .xml
     * @param str como string contenido del archivo.
     * @return String como Archivo xml
     * @throws IOException 
     */
    public File stringToFile(String str) throws IOException{
        /*
            Recibe un string y lo procesa para que sea un archivo.
            Este archivo estará momentaneamente en el directorio del 
            programa, por ello, se le asigna el nombre que, probablemente,
            no genere conflictos con otro que se esté creando a la vez.
        */
        String chain = (Math.random()*100000+1)+".xml";
        File newFile;
        newFile = new File(chain);
        newFile.setWritable(true);
        try (FileWriter fw = new FileWriter(newFile)) {
            fw.write(str);
        }
        return newFile;
    }
    
    public void preProcessText(){
        /*
        Inicio: Reemplazar <destacado> y </destacado> por /d
        */
        String destacado[] = this.fileContent.split("<destacado>");
        String temp = destacado[0];
        for(int i = 1; i == destacado.length-1; i++){
            temp+="/d"+destacado[i];
        }
        this.fileContent = temp;
        temp = "";
        String destacado2[] = this.fileContent.split("</destacado>");
        temp += destacado2[0];
        for(int i = 1; i == destacado2.length-1; i++){
            temp+="/d"+destacado2[i];
        }
        this.fileContent = temp;
        temp = "";
        
        /*
        FIN: reemplazo de destacado
        Inicio: Reemplazar <enfatizado> y </enfatizado> por /d
        */
        
        
        String enfatizado[] = this.fileContent.split("<enfatizado>");
        temp += enfatizado[0];
        for(int i = 1; i == enfatizado.length-1; i++){
            temp+="/e"+enfatizado[i];
        }
        this.fileContent = temp;
        temp = "";
       
        
        String enfatizado2[] = this.fileContent.split("</enfatizado>");
        temp += enfatizado2[0];
        for(int i = 1; i == enfatizado2.length-1; i++){
            temp+="/e"+enfatizado2[i];
        }
        this.fileContent = temp;        
        temp = "";        
    }
    
    public void AppendDTD(){
        this.fileContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<!DOCTYPE comenzar [\n" +
"<!ELEMENT comenzar (objeto)>\n" +
"<!ELEMENT objeto (escena+)>\n" +
"<!ELEMENT escena (bloque+)>\n" +
"<!ELEMENT bloque (idea*)>\n" +
"<!ELEMENT idea (texto*, voz?, media*, evaluaciones*)>\n" +
"<!ELEMENT texto (#PCDATA)>\n" +
"<!ELEMENT voz (#PCDATA)>\n" +
"<!ELEMENT media (#PCDATA)>\n" +
"<!ELEMENT evaluaciones (evaluacion*)>\n" +
"<!ELEMENT evaluacion (enunciado,opciones)>\n" +
"<!ELEMENT enunciado (#PCDATA)>\n" +
"<!ELEMENT opciones (alternativa*)>\n" +
"<!ELEMENT alternativa (#PCDATA)>\n" +
"\n" +
"\n" +
"<!ATTLIST objeto titulo CDATA #REQUIRED>\n" +
"<!ATTLIST objeto autor CDATA #REQUIRED>\n" +
"<!ATTLIST objeto tema CDATA #REQUIRED>\n" +
"<!ATTLIST escena titulo CDATA #REQUIRED>\n" +
"<!ATTLIST escena tipo CDATA #REQUIRED>\n" +
"\n" +
"<!ATTLIST idea ordenAparicion CDATA #REQUIRED>\n" +
"<!ATTLIST texto tipo CDATA #REQUIRED>\n" +
"<!ATTLIST texto mano CDATA #IMPLIED>\n" +
"<!ATTLIST media tipo CDATA #REQUIRED>	\n" +
"\n" +
"<!ATTLIST alternativa tipo CDATA #REQUIRED>\n" +
"<!ATTLIST alternativa tema CDATA #REQUIRED>\n" +
"]>" + this.fileContent;
    }
}



