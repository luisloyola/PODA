
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class Prueba {
    private List<Evaluacion> quiz;
    
    public Prueba(){
        quiz = new ArrayList<Evaluacion>();
    }

    public List<Evaluacion> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Evaluacion> quiz) {
        this.quiz = quiz;
    }
    
    public void addQuiz(Evaluacion quiz){
        this.quiz.add(quiz);
    }
}
