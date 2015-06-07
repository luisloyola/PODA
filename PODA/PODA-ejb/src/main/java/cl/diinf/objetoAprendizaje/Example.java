/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class Example {
    List<String> textContent;
    List<String> mediaContent;
    
    public Example(){
        textContent = new ArrayList<>();
        mediaContent = new ArrayList<>();
    };

    public List<String> getTextContent() {
        return textContent;
    }

    public void setTextContent(List<String> textContent) {
        this.textContent = textContent;
    }
    
    public void addTextContent(String textContent) {
        this.textContent.add(textContent);
    }

    public List<String> getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(List<String> mediaContent) {
        this.mediaContent = mediaContent;
    }
    
    public void addMediaContent(String mediaContent) {
        this.mediaContent.add(mediaContent);
    }
    
    
}
