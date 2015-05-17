/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
                    String[] tempErrorHandler = errorHandler.getErrorMessage().split(" ");
                    if(tempErrorHandler[0].equals("Attribute")){
                        this.parsingError = "En el elemento: "+tempErrorHandler[11]+" debe añadirse el atributo: "+tempErrorHandler[1];
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
                
                //System.out.println("Título Objeto: "+newOA.getTitle()+" Realizado por: "+newOA.getAuthor()+" Template: "+newOA.getTemplate() + " Fecha creación: "+newOA.getCreationDate());
                
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
                        //System.out.println("\nEscena: "+(j+1)+"\n\tTítulo: "+newSlide.getTitle()+"\n\tTipo: "+newSlide.getDesign());
                        
                        NodeList readedBlocks  = currentSlide.getElementsByTagName("bloque");
                            
                        for(int k = 0; k < readedBlocks.getLength(); k++){
                            int breaker = -1;
                            switch(newSlide.getDesign()){
                                case "1Col":
                                    if(readedBlocks.getLength()  != 1){
                                        breaker = 1;
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                    }
                                    break;
                                case "1Fil2Col":
                                    if(readedBlocks.getLength()  != 3){
                                        breaker = 3;
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                    }
                                    break;
                                case "2Col":
                                    if(readedBlocks.getLength()  != 2){
                                        breaker = 2;
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                    }
                                    break;
                                case "3Col":
                                    if(readedBlocks.getLength()  != 3){
                                        breaker = 3;
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                    }
                                    break;
                                case "1Fil3Col":
                                    if(readedBlocks.getLength()  != 4){
                                        breaker = 4;
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                    }
                                    break;
                                case "2Fil2Col":
                                    if(readedBlocks.getLength()  != 4){
                                        breaker = 4;
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                    }
                                    break;
                                case "2Col1Fil":
                                    if(readedBlocks.getLength()  != 3){
                                        breaker = 3;
                                        this.parsingError = "\nCantidad de bloques inválido.";
                                    }
                                    break;    
                                default:
                                    breaker = 1;
                                    this.parsingError = "\nTipo de diseño inválido.";
                                    newSlide.setDesign("1Col");
                                    break;
                            }
                            
                            if(k == breaker-1){
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
                                            break;
                                        case "manuscrito":
                                            break;
                                        case "codigo":
                                            break;
                                        case "ejemplo":
                                            break;
                                        default:

                                            newText.setType("normal");
                                            this.parsingError = "\nTipo de texto no soportado.";
                                            return new ArrayList<ObjetoAprendizaje>();
                                    }
                                    
                                    newIdea.addText(newText);
                                    /*System.out.println("\t\tTexto: "+newText.getContent());
                                    if(newText.getType()!=""){
                                        System.out.println("\t\tTipo texto: "+newText.getType());
                                    }*/
                                    
                                }
                                
                                NodeList voiceNode = currentIdea.getElementsByTagName("voz");
                                
                                if(voiceNode.getLength() > 0){
                                    newIdea.setVoice(voiceNode.item(0).getTextContent());
                                    //System.out.println("\t\tVoz: "+newIdea.getVoice()+"\n   ");
                                }
                                else if(voiceNode.getLength() >= 1){
                                    
                                    this.parsingError = "\n Sólo se permite una voz por idea.";
                                    new ArrayList<ObjetoAprendizaje>();
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
}



