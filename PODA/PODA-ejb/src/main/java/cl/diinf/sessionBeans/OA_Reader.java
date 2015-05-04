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


/**
 *
 * @author teamPODA
 */
public class OA_Reader {
    
    private String contenidoFile;
    
    public OA_Reader(){
        
    }
    public String getContenidoFile() {
        return contenidoFile;
    }
    public void setContenidoFile(String contenidoFile) {
        this.contenidoFile = contenidoFile;
    }
      
    /**
     * Devuelve una vista generada a partir del string entregado.
     * @return Objects  
     */
    public List<ObjetoAprendizaje> readOA() {
        
        List<ObjetoAprendizaje> Objects = new ArrayList<ObjetoAprendizaje>();
        
        if(this.contenidoFile == null){
            return Objects;
        }
        
        try {
            File OA_XML_File = this.stringToFile(contenidoFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            try{
                doc = dBuilder.parse(OA_XML_File); //Aqui tiro excepcion
            }
            catch(org.xml.sax.SAXException e){
                //e.printStackTrace();
                System.out.println("ARCHIVO NO VÁLIDO");
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
            List<ObjetoAprendizaje> nueva = new ArrayList<ObjetoAprendizaje>();
            
            if(OA.getLength() == 0){
                /*
                Lista vacia en casos de formato inválido o vacío
                */
                return nueva;
            }
                
            for (int i = 0; i < OA.getLength(); i++) {
                /*
                    Recorre todos los hijos de Object (Slides) y las 
                    agrega al objeto que corresponda (OA.item(i))
                */
                ObjetoAprendizaje nuevoOA = new ObjetoAprendizaje();         
                Node ObjectNode = OA.item(i);                
                Element currentNode = (Element) ObjectNode;                      
                nuevoOA.setTitle(currentNode.getAttribute("title"));
                nuevoOA.setAuthor(currentNode.getAttribute("author"));                
                
                //System.out.println("Título Objeto: "+nuevoOA.getTitle()+" Realizado por: "+nuevoOA.getAuthor());
                
                Element slideNode = (Element) currentNode;
                NodeList slides = slideNode.getElementsByTagName("scene");  
                
                List<Slide> currentSlides = new ArrayList<Slide>();
                
                if(slides.getLength() == 0){
                    /*
                        Si no encuentra slides, devuelve un objeto vacío.
                    */
                    nuevoOA.addContent(new Slide());
                    nueva.add(nuevoOA);
                    return nueva;
                }
                
                for(int j = 0; j < slides.getLength(); j++){
                    
                    /*
                        De encontrar, al menos una slide, lee los textos y voces asociados.
                    */
                    
                    Slide nuevaSlide = new Slide();                    
                    Element currentNode2 = (Element) slides.item(j);
                    nuevaSlide.setTitle(currentNode2.getAttribute("sceneTitle"));
                    nuevaSlide.setTime(currentNode2.getAttribute("time"));
         
                    //System.out.println("\tDiapositiva: "+nuevaSlide.getTitle()+" Duración: "+nuevaSlide.getTime());
                    
                    Element contenidoSlide = (Element) currentNode2;
                    NodeList xmlTextos = contenidoSlide.getElementsByTagName("text");
                    NodeList xmlVoices = contenidoSlide.getElementsByTagName("voice");
                    
                    if(xmlTextos.getLength() == 0 && xmlVoices.getLength() == 0){
                        Texto txVacio = new Texto();
                        txVacio.setContent("vacio");
                        txVacio.setFont("default");
                        txVacio.setType("text");
                        nuevaSlide.addText(txVacio);
                        nuevaSlide.addVoice("");
                    }
                    
                    for(int x = 0; x < xmlTextos.getLength(); x++){
                        Element textoFinal = (Element)xmlTextos.item(x);
                                           
                        //System.out.println("\t\tTipo: "+textoFinal.getAttribute("type")+" Fuente: "+textoFinal.getAttribute("font")+" Contenido: "+textoFinal.getTextContent());
                        
                        Texto nuevoTexto = new Texto();                  
                        nuevoTexto.setContent(textoFinal.getTextContent());
                        nuevoTexto.setFont(textoFinal.getAttribute("font"));
                        nuevoTexto.setType(textoFinal.getAttribute("type"));
                        nuevaSlide.addText(nuevoTexto);
                    }
                    
                    for(int y = 0; y < xmlVoices.getLength(); y++){
                        Element vocesFinal = (Element)xmlVoices.item(y);
                        
                        //System.out.println("\t\tVoz: "+vocesFinal.getTextContent());
                        
                       nuevaSlide.addVoice(vocesFinal.getTextContent());
                    }
                    nuevoOA.addContent(nuevaSlide);
                }
                Objects.add(nuevoOA);
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
     * @param str
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
        File nuevo;
        nuevo = new File(chain);
        nuevo.setWritable(true);
        try (FileWriter fw = new FileWriter(nuevo)) {
            fw.write(str);
        }
        return nuevo;
    }
}



