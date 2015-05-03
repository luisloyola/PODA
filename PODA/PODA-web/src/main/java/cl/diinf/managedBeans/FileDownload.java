/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.managedBeans;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
//package org.primefaces.showcase.view.file;
 
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
/**
 *
 * @author nacho
 */
@Named(value = "FileDownload")
@ApplicationScoped
public class FileDownload {

    private StreamedContent file;

    public void setFile(StreamedContent file) {
        this.file = file;
    }
                 
    public FileDownload() {        
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/plantilla_oa.xml");
        file = new DefaultStreamedContent(stream, "text/plain", "plantilla_objeto.xml");
    }
 
    public StreamedContent getFile() {
        return file;
    }
    
}
