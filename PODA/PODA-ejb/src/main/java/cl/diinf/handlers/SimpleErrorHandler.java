/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.handlers;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class SimpleErrorHandler implements ErrorHandler {
    
    String errorMessage;
    
    /**
     * Inicializa el manejador de errores.
     */
    public SimpleErrorHandler(){
        this.errorMessage = "NO_ERROR";
    }
    
    /**
     * Retorna el contenido del mensaje producido por el error de parseo.
     * @param e excelpción obtenida desde el parser.
     * @throws SAXException 
     */
    @Override
    public void warning(SAXParseException e) throws SAXException {
        this.errorMessage = e.getMessage() ;//+ " (linea: "+ (e.getLineNumber()-38) + ", Columna: "+ (1+e.getColumnNumber())+").";
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        this.errorMessage = e.getMessage() ;//+ " (linea: "+ (e.getLineNumber()-38) + ", Columna: "+ (e.getColumnNumber()+1)+").";
    
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        this.errorMessage = e.getMessage() ;//+ " (linea: "+ (e.getLineNumber()-38) + ", Columna: "+ (e.getColumnNumber()+1)+").";
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}