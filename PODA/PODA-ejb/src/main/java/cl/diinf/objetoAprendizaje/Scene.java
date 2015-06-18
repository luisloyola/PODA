
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private String title;
    private String design;
    private List<Block> blocks;
     
    public Scene(){
        title = design = "";
        blocks = new ArrayList<>();
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
    
    public void addBlocks(Block blocks) {
        this.blocks.add(blocks);
    }
    
}
