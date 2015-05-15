/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author teamPODA
 */
public class ObjetoAprendizaje {
    private String title;
    private String author;
    private String template;
    private Date creationDate;
    private List<Slide> content;
    
    public ObjetoAprendizaje(){
        content = new ArrayList<Slide>();
    };

    /**
     * Devuelve el valor del atributo privado.
     * @return String title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Asigna el valor title al atributo privado.
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

        /**
     * Devuelve el valor del atributo privado.
     * @return String Author
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Asigna el valor title al atributo privado.
     * @param author 
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * Agrega una slide a la lista de slides de la presentación.
     * @param slide 
     */
    public void addContent(Slide slide){
        this.content.add(slide);
    }
    
        /**
     * Devuelve el valor del atributo privado.
     * @return Slide[] title
     */
    public List<Slide> getContent(){
        return this.content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    
    
}
