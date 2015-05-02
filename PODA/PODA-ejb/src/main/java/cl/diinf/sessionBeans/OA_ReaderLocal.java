/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

import cl.diinf.objetoAprendizaje.ObjetoAprendizaje;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author teban
 */
@Local
public interface OA_ReaderLocal {
    
    
    public String getContenidoFile();
    public void setContenidoFile(String contenidoFile);
    public List<ObjetoAprendizaje> readOA();
    public File stringToFile(String str) throws IOException;
    
}
