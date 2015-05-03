/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
//package org.primefaces.showcase.view.file;
 
import java.io.InputStream;
import java.io.Serializable;
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
@RequestScoped
public class FileDownload implements Serializable{

    private StreamedContent file;

    public void setFile(StreamedContent file) {
        this.file = file;
    }
 
    public StreamedContent getFile() {
        
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/plantilla_oa.xml");
        file = new DefaultStreamedContent(stream, "text/plain", "plantilla_objeto.xml");
        
        return file;
    }   
}
