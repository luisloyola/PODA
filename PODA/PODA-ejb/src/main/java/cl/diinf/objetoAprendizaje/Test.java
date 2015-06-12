
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;

public class Test {
    
    private int exigency;
    private int secondExigency;
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

    public int getSecondExigency() {
        return secondExigency;
    }

    public void setSecondExigency(int secondExigency) {
        this.secondExigency = secondExigency;
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
