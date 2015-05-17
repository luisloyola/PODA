/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.managedBeans;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
 
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
/**
 *
 * @author teamPODA
 */
@Named(value = "FileDownload")
@ApplicationScoped
public class FileDownload {

    private StreamedContent file;

    public void setFile(StreamedContent file) {
        this.file = file;
    }        
 
    /**
     * Devuelve un stream para ser capaz de ser descargado.
     * @return StreamedContent file
     */
    public StreamedContent getFile() {
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/plantilla_oa.xml");
        file = new DefaultStreamedContent(stream, "text/plain", "plantilla_objeto.xml");
        
        return file;
    }
    
    public StreamedContent getZipFile(String path){
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(path);
        file = new DefaultStreamedContent(stream, "application/zip","ObjetoAprendizaje.zip");
        
        return file;
    }
    
}
