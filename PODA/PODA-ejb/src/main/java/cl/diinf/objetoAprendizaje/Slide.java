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
 * @author teamPODA
 */
public class Slide {
    private String title;
    private String time;
    private List<Texto> text;
    private List<String> voice;
     
    public Slide(){
        text = new ArrayList<Texto>();
        voice = new ArrayList<String>();
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Texto> getText() {
        return text;
    }

    public void setText(List<Texto> text) {
        this.text = text;
    }

    public List<String> getVoice() {
        return voice;
    }

    public void setVoice(List<String> voice) {
        this.voice = voice;
    }
    
    
    public void addText(Texto text){
        this.text.add(text);
    }
    
    public void addVoice(String voice){
        this.voice.add(voice);
    }
    

}
