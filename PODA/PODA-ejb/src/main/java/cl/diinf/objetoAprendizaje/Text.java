/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.objetoAprendizaje;

/**
 *
 * @author teamPODA
 */
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
