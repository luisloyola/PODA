
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class SubIdea {
    private List<SubText> subIdeaContent;
    private int aparitionOrder;
    
    public SubIdea(){
        subIdeaContent = new ArrayList<>();
    }

    public List<SubText> getSubIdeaContent() {
        return subIdeaContent;
    }

    public void setSubIdeaContent(List<SubText> subIdeaContent) {
        this.subIdeaContent = subIdeaContent;
    }
    
    public void addSubIdeaContent(SubText subIdeaContent) {
        this.subIdeaContent.add(subIdeaContent);
    }

    public int getAparitionOrder() {
        return aparitionOrder;
    }

    public void setAparitionOrder(int aparitionOrder) {
        this.aparitionOrder = aparitionOrder;
    }
    
    
}
