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
    private List<Question> questions;
    
    public Test(){
        questions = new ArrayList<>();
    }    
    
    public int getExigency() {
        return exigency;
    }

    public void setExigency(int exigency) {
        this.exigency = exigency;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    public void addQuestion(Question questions) {
        this.questions.add(questions);
    }
    
}
