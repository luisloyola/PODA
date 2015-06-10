/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class Example {
    List<Text> textContent;
    List<Media> mediaContent;
    
    public Example(){
        textContent = new ArrayList<>();
        mediaContent = new ArrayList<>();
    };

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
    
    
}
