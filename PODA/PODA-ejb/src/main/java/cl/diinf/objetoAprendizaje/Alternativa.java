    
package cl.diinf.objetoAprendizaje;

public class Alternativa {
    //Texto de la alternativa.
    private String content;
    
    //Solución o distractor.
    private String type;
    
    //Qué tema es la alternativa.
    private String topic;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    
    
    
    public Alternativa(){
    
    }
    
    
}
