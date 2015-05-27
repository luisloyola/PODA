/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

/**
 *
 * @author teban
 */
public class Idea implements Comparable<Idea>{
    int aparitionOrder;
    String voice;
    List<Texto> text;
    List<Media> media;
    List<Prueba> quizSet;
    
    public Idea(){
        text = new ArrayList<Texto>();
        media = new ArrayList<Media>();
        quizSet = new ArrayList<Prueba>();
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

    public List<Texto> getText() {
        return text;
    }

    public void setText(List<Texto> text) {
        this.text = text;
    }
    
    public void addText(Texto text) {
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

    public List<Prueba> getQuizSet() {
        return quizSet;
    }

    public void setQuizSet(List<Prueba> quizSet) {
        this.quizSet = quizSet;
    }

       
    public void addQuiz(Prueba quizSet){
        this.quizSet.add(quizSet);
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


