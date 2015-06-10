/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author teamPODA
 */
public class Scene {
    private String title;
    private String design;
    private List<Block> blocks;
     
    public Scene(){
        title = design = "";
        blocks = new ArrayList<Block>();
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
