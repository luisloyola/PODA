
package cl.diinf.objetoAprendizaje;

public class Text {
    private String type;
    private String content;
    private boolean hand;
    
    public Text(){
        type = content = "";
        hand = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }  

    public boolean isHand() {
        return hand;
    }

    public void setHand(boolean hand) {
        this.hand = hand;
    }
    
    
}
