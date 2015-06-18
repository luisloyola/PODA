
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class SubIdea {
    private List<SubText> subIdeaContent;
    
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
    
    
}
