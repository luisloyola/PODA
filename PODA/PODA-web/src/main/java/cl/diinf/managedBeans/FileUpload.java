/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable; 
import cl.diinf.objetoAprendizaje.ObjetoAprendizaje;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import cl.diinf.sessionBeans.OA_Reader;
import cl.diinf.sessionBeans.OA_TranslateHtml;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part; 
/**
 *
 * @author nacho
 */
@Named(value = "fileUpload")
@RequestScoped
public class FileUpload{
   
    private Part file;
    private String fileContent;    
    private String code_html;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getCode_html() {
        return code_html;
    }

    public void setCode_html(String code_html) {
        this.code_html = code_html;
    }
 
    
 
    public void upload() throws IOException {
        try {
            if(!file.equals(null)){            
                fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
            }
            else{
                fileContent = "Error: debe ingresar un archivo";
            }
        } catch (IOException e) {
          // Error handling
            fileContent = null;
        }
        if (fileContent!=null){
           
            OA_Reader nuevoOAR = new OA_Reader();
            
            nuevoOAR.setContenidoFile(fileContent);
            
            List<ObjetoAprendizaje> lista = nuevoOAR.readOA();
            
            if(lista.size()!=0){
                /*Cargara el objeto SÓLO si es válido*/
                OA_TranslateHtml OA_translate = new OA_TranslateHtml();
            
                code_html = OA_translate.writeHtml(lista.get(0));
            }
            else{
                code_html = "Archivo no válido.";
            }
            
                       
        }
        else{
            code_html = "<h2>Error: Intente nuevamente colocando un archivo con la estructura definida<h2>";
        }
    }
        
    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
       
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
       
        Part file = (Part)value;
       
        if (file.equals(null)) {
          msgs.add(new FacesMessage("No hay archivo"));
        }
        if (!msgs.isEmpty()) {
          throw new ValidatorException(msgs);
        }
    }            
    
}
