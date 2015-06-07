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
 * @author teban
 */
public class Test {
    
    private int exigency;
    private List<Form> question;
    
    public Test(){
        question = new ArrayList<>();
    }    
    
    public int getExigency() {
        return exigency;
    }

    public void setExigency(int exigency) {
        this.exigency = exigency;
    }

    public List<Form> getQuestions() {
        return question;
    }

    public void setQuestions(List<Form> question) {
        this.question = question;
    }
    
    public void addQuestions(Form question) {
        this.question.add(question);
    }
    
}
