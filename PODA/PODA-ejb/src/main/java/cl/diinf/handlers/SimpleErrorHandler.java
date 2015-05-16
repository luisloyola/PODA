/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.handlers;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author teban
 */
public class SimpleErrorHandler implements ErrorHandler {
    
    String errorMessage;
    
    public SimpleErrorHandler(){
        this.errorMessage = "NO_ERROR";
    }
    
    public void warning(SAXParseException e) throws SAXException {
        //System.out.println(e.getMessage());
        this.errorMessage = e.getMessage();
    }

    public void error(SAXParseException e) throws SAXException {
        //System.out.println(e.getMessage());
        this.errorMessage = e.getMessage();
    }

    public void fatalError(SAXParseException e) throws SAXException {
        //System.out.println(e.getMessage());
        this.errorMessage = e.getMessage();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}