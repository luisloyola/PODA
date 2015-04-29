/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.managedBeans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author teban
 */
@Named(value = "fileUpload")
@Dependent
public class FileUpload {

    /**
     * Creates a new instance of FileUpload
     */
    public FileUpload() {
    }
    
}
