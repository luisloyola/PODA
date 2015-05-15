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
public class Block {
    List<Idea> Ideas;
    
    public Block(){
        Ideas = new ArrayList<Idea>();
    };

    public List<Idea> getIdeas() {
        return Ideas;
    }

    public void setIdeas(List<Idea> Ideas) {
        this.Ideas = Ideas;
    }
    
    public void addIdeas(Idea Ideas) {
        this.Ideas.add(Ideas);
    }

      
}

