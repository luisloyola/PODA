
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class Form {
        
    //Enunciado
    private List<Text> textContent;
    
    //Lista de alternativas
    private List<Choice> choices;
    //Solucionario
    private List<Text> solutionTextContent;
    
    
    public Form(){
        textContent = new ArrayList<>();
        solutionTextContent = new ArrayList<>();
        choices = new ArrayList<>();
    }
   
    public List<Text> getTextContent() {
        return textContent;
    }

    public void setTextContent(List<Text> textContent) {
        this.textContent = textContent;
    } 

    public void addTextContent(Text textContent) {
        this.textContent.add(textContent);
    }


    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
    
    public void addChoices(Choice choice){
        this.choices.add(choice);
    }

    public List<Text> getSolutionTextContent() {
        return solutionTextContent;
    }

    public void setSolutionTextContent(List<Text> solutionTextContent) {
        this.solutionTextContent = solutionTextContent;
    }
    
    public void addSolutionTextContent(Text solutionTextContent) {
        this.solutionTextContent.add(solutionTextContent);
    }   
   
}
