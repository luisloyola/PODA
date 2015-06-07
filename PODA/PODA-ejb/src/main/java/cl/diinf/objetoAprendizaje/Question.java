
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class Question {
    
    
    
    //Enunciado
    private List<Text> textContent;
    private List<Media> mediaContent;
    //Lista de alternativas
    private List<Choice> choices;
    //Solucionario
    private List<Text> solutionTextContent;
    private List<Media> solutionMediaContent;
    private String voice;
    
    public Question(){
        textContent = new ArrayList<>();
        mediaContent = new ArrayList<>();
        solutionTextContent = new ArrayList<>();
        solutionMediaContent = new ArrayList<>();
        voice = "";
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
    
    public List<Media> getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(List<Media> mediaContent) {
        this.mediaContent = mediaContent;
    }

    public void addMediaContent(Media mediaContent) {
        this.mediaContent.add(mediaContent);
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

    public List<Media> getSolutionMediaContent() {
        return solutionMediaContent;
    }

    public void setSolutionMediaContent(List<Media> solutionMediaContent) {
        this.solutionMediaContent = solutionMediaContent;
    }
    
    public void addSolutionMediaContent(Media solutionMediaContent) {
        this.solutionMediaContent.add(solutionMediaContent);
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
    
    
   
}
