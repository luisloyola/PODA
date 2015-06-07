
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class Form {
    private List<Question> questions;
    
    public Form(){
        questions = new ArrayList<>();
    }

    public List<Question> getQuestion() {
        return questions;
    }

    public void setQuestion(List<Question> question) {
        this.questions = question;
    }
    
    public void addQuestion(Question question){
        this.questions.add(question);
    }
}
