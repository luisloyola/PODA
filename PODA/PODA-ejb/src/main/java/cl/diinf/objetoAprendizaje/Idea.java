
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;

public class Idea implements Comparable<Idea>{
    int aparitionOrder;
    String voice;
    List<Text> text;
    List<Media> media;
    List<Example> example;
    List<SubIdea> subIdea;
    
    public Idea(){
        text = new ArrayList<>();
        media = new ArrayList<>();
        example = new ArrayList<>();
        subIdea = new ArrayList<>();
        voice = "";
    }

    public int getAparitionOrder() {
        return aparitionOrder;
    }

    public void setAparitionOrder(int aparitionOrder) {
        this.aparitionOrder = aparitionOrder;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public List<Text> getText() {
        return text;
    }

    public void setText(List<Text> text) {
        this.text = text;
    }
    
    public void addText(Text text) {
        this.text.add(text);
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }
    
    public void addMedia(Media media) {
        this.media.add(media);
    }

    public List<Example> getExample() {
        return example;
    }

    public void setExample(List<Example> example) {
        this.example = example;
    }
    
    public void addExample(Example example) {
        this.example.add(example);
    }

    public List<SubIdea> getSubIdea() {
        return subIdea;
    }

    public void setSubIdea(List<SubIdea> subIdea) {
        this.subIdea = subIdea;
    }
    
    public void addSubIdea(SubIdea subIdea) {
        this.subIdea.add(subIdea);
    }
   
    @Override     
    public int compareTo( Idea idea ) {
        if(aparitionOrder < idea.aparitionOrder){
            return -1;
        }
        else if(aparitionOrder > idea.aparitionOrder){
            return 1;
        }
        return 0;
    }
}


