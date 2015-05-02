/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.managedBeans;


import cl.diinf.objetoAprendizaje.ObjetoAprendizaje;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import cl.diinf.sessionBeans.OA_Reader;
import cl.diinf.sessionBeans.OA_TranslateHtml;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author teban
 */
@Named(value = "fileUpload")
@RequestScoped
public class FileUpload {
   
    private Part file;
    private String fileContent;
    private boolean fileUp;
 
    public void upload() {
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
            OA_TranslateHtml nuevoTrans = new OA_TranslateHtml();
            nuevoOAR.setContenidoFile(fileContent);
            List<ObjetoAprendizaje> objetos = nuevoOAR.readOA();
            for(int i = 0; i < objetos.size(); i++){
                try{
                    nuevoTrans.writeHtml(objetos.get(i));
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

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

    public boolean isFileUp() {
        return fileUp;
    }

    public void setFileUp(boolean fileUp) {
        this.fileUp = fileUp;
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
