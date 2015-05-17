/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.managedBeans;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable; 
import cl.diinf.objetoAprendizaje.ObjetoAprendizaje;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import cl.diinf.sessionBeans.OA_Reader;
import cl.diinf.sessionBeans.OA_TranslateHtml;
import java.util.NoSuchElementException;
import javax.inject.Named;
import javax.servlet.http.Part; 
/**
 *
 * @author teamPODA
 */
@Named(value = "fileUpload")
@SessionScoped
public class FileUpload implements Serializable{
   
    private Part file;
    private String fileContent;    
    private String code_html;
    private String error_Message;

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

    public String getError_Message() {
        return error_Message;
    }

    public void setError_Message(String error_Message) {
        this.error_Message = error_Message;
    }
 
    
    
    /**
     * Cambia el contenido del la variable privada code_html para ser mostrada
     * como objeto de aprendizaje.
     * @throws IOException 
     */
    public void upload() throws IOException {
        try {
            if(!file.equals(null)){            
                Scanner scan = new Scanner(file.getInputStream());
                try{
                    fileContent = scan.useDelimiter("\\A").next();
                    error_Message = null;
                }
                catch(NoSuchElementException e){
                    /*Archivo vacío*/
                    code_html = null;
                    error_Message = "El archivo ingresado se encuentra vacío.";
                }
            }
            else{
                fileContent = "Error: debe ingresar un archivo";
            }
        } catch (IOException e) {
            error_Message = "Archivo inválido";
        }
        if (fileContent != null && !fileContent.isEmpty()){
           
            OA_Reader nuevoOAR = new OA_Reader();
            
            nuevoOAR.setFileContent(fileContent);
            
            List<ObjetoAprendizaje> OA_List = nuevoOAR.readOA();
            
            if(OA_List.size() > 0){
                /*Cargara el objeto SÓLO si es válido*/
                OA_TranslateHtml OA_translate = new OA_TranslateHtml();
            
                code_html = OA_translate.writeHtml(OA_List.get(0));
                error_Message = null;
            }
            else{
                /*Archivo con errores o inválido.*/
                code_html = null;
                error_Message = nuevoOAR.getParsingError();
            }     
        }        
    }
        /**
         * Validador del archivo para el frontend.
         * @param ctx
         * @param comp
         * @param value 
         */
    /*public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
       
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
       
        Part file = (Part)value;
       
        if (file.equals(null)) {
          msgs.add(new FacesMessage("No hay archivo"));
        }
        if (!msgs.isEmpty()) {
          throw new ValidatorException(msgs);
        }
    }     */       
    
}
