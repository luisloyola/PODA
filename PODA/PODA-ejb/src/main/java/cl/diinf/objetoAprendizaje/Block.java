
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class Block {
    List<Idea> Ideas;
    
    public Block(){
        Ideas = new ArrayList<>();
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

