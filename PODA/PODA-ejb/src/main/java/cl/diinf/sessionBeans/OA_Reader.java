/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

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
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            try{
                doc = dBuilder.parse(OA_XML_File);
            }
            catch(org.xml.sax.SAXException e){
                /*Error en el parser*/
                parsingError = e.getLocalizedMessage();
                return Objects;
            }
            OA_XML_File.delete();
            doc.getDocumentElement().normalize();

            NodeList OA = doc.getElementsByTagName("object");

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
                newOA.setTitle(currentNode.getAttribute("title"));
                newOA.setAuthor(currentNode.getAttribute("author"));  
                newOA.setTemplate(currentNode.getAttribute("template"));
                newOA.setCreationDate(new Date());
                
                //System.out.println("Título Objeto: "+newOA.getTitle()+" Realizado por: "+newOA.getAuthor()+" Template: "+newOA.getTemplate());
                
                Element slideNode = (Element) currentNode;
                NodeList slides = slideNode.getElementsByTagName("scene");  
                
                List<Slide> currentSlides = new ArrayList<Slide>();
                
                if(slides.getLength() == 0){
                    /*
                        Si no encuentra slides, devuelve un objeto vacío.
                    */
                    newOA.addContent(new Slide());
                    newOAList.add(newOA);
                    return newOAList;
                }
                
                for(int j = 0; j < slides.getLength(); j++){
                    
                    /*
                        De encontrar, al menos una slide, lee los textos y voces asociados.
                    */
                    
                    Slide newSlide = new Slide();                    
                    Element currentNode2 = (Element) slides.item(j);
                    newSlide.setTitle(currentNode2.getAttribute("sceneTitle"));
                    newSlide.setTime(currentNode2.getAttribute("time"));
                    newSlide.setDesign(currentNode2.getAttribute("design"));
         
                    //System.out.println("\tDiapositiva: "+newSlide.getTitle()+" Duración: "+newSlide.getTime() + " Diseño: "+newSlide.getDesign());
                    
                    Element contenidoSlide = (Element) currentNode2;
                    NodeList xmlText = contenidoSlide.getElementsByTagName("text");
                    NodeList xmlVoices = contenidoSlide.getElementsByTagName("voice");
                    NodeList xmlMedia = contenidoSlide.getElementsByTagName("media");
                    
                    if(xmlText.getLength() == 0 && xmlVoices.getLength() == 0){
                        Texto emptyTxt = new Texto();
                        emptyTxt.setContent("vacio");
                        emptyTxt.setFont("default");
                        emptyTxt.setType("text");
                        newSlide.addText(emptyTxt);
                        newSlide.addVoice("");
                    }
                    
                    for(int x = 0; x < xmlText.getLength(); x++){
                        Element textoFinal = (Element)xmlText.item(x);
                                           
                        //System.out.println("\t\tTipo: "+textoFinal.getAttribute("type")+" Fuente: "+textoFinal.getAttribute("font")+" Contenido: "+textoFinal.getTextContent());
                        
                        Texto nuevoTexto = new Texto();                  
                        nuevoTexto.setContent(textoFinal.getTextContent());
                        nuevoTexto.setFont(textoFinal.getAttribute("font"));
                        nuevoTexto.setType(textoFinal.getAttribute("type"));
                        newSlide.addText(nuevoTexto);
                    }
                    
                    for(int y = 0; y < xmlVoices.getLength(); y++){
                        Element vocesFinal = (Element)xmlVoices.item(y);
                        
                        //System.out.println("\t\tVoz: "+vocesFinal.getTextContent());
                        
                       newSlide.addVoice(vocesFinal.getTextContent());
                    }
                    
                    for(int w = 0; w < xmlMedia.getLength(); w++){
                        Element mediaFinal = (Element)xmlMedia.item(w);
                        Media media = new Media();
                        media.setPosition(mediaFinal.getAttribute("position"));
                        media.setType(mediaFinal.getAttribute("type"));
                        media.setContent(mediaFinal.getTextContent());
                        newSlide.addMedia(media);
                        
                        //System.out.println("\t\tPosicion: "+media.getPosition()+" tipo: "+media.getType()+" Contenido: "+media.getContent());

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



