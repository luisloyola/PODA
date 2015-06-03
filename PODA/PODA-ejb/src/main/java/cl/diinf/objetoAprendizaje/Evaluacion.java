
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class Evaluacion {
    //Enunciado
    private String header;
    
    //Lista de alternativas
    private List<Alternativa> choices;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Alternativa> getChoices() {
        return choices;
    }

    public void setChoices(List<Alternativa> choices) {
        this.choices = choices;
    }
    
    public void addChoices(Alternativa choice){
        this.choices.add(choice);
    }
    
    
    public Evaluacion(){
        choices = new ArrayList<Alternativa>();
    }
}
