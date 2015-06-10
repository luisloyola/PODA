    
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;

public class Choice {
    //Texto de la alternativa.
    private List<Text> textContent;
    private List<Media> mediaContent;
    
    //Solución o distractor.
    private String type;
    
    //Qué tema es la alternativa.
    private String topic;

     public Choice(){
         type = "";
         topic = "";
         textContent = new ArrayList<>();
         mediaContent = new ArrayList<>();
    }
    
    public List<Text> getTextContent() {
        return textContent;
    }

    public void setTextContent(List<Text> textContent) {
        this.textContent = textContent;
    }
    
    public void addTextContent(Text textContent){
        this.textContent.add(textContent);
    }

    public List<Media> getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(List<Media> mediaContent) {
        this.mediaContent = mediaContent;
    }

    public void addMediaContent(Media mediaContent){
        this.mediaContent.add(mediaContent);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }  
    
}
