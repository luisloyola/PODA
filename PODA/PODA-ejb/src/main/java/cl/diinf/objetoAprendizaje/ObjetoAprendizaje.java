/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author teban
 */
public class ObjetoAprendizaje {
    private String title;
    private String author;
    private List<Slide> content;
    
    public ObjetoAprendizaje(){
        content = new ArrayList<Slide>();
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void addContent(Slide slide){
        this.content.add(slide);
    }
    
    public List<Slide> getContent(){
        return this.content;
    }
    
}
