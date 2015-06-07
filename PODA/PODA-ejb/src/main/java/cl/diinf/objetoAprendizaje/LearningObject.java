/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author teamPODA
 */
public class LearningObject {
    private String title;
    private String author;
    private String template;
    private String name_file;
    private Date creationDate;
    private List<Scene> content;
    private List<Test> quizSet;
    private FeedBack feedback;
    
    public LearningObject(){
        content = new ArrayList<>();
        quizSet = new ArrayList<>();
    };

    /**
     * Devuelve el valor del atributo privado.
     * @return String title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Asigna el valor title al atributo privado.
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

        /**
     * Devuelve el valor del atributo privado.
     * @return String Author
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Asigna el valor title al atributo privado.
     * @param author 
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * Agrega una slide a la lista de slides de la presentación.
     * @param slide 
     */
    public void addContent(Scene slide){
        this.content.add(slide);
    }
    
        /**
     * Devuelve el valor del atributo privado.
     * @return Scene[] title
     */
    public List<Scene> getContent(){
        return this.content;
    }

    public String getName_file() {
        return name_file;
    }

    public void setName_file(String name_file) {
        this.name_file = name_file;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public List<Test> getQuizSet() {
        return quizSet;
    }

    public void setQuizSet(List<Test> quizSet) {
        this.quizSet = quizSet;
    }

    public void addQuiz(Test quizSet){
        this.quizSet.add(quizSet);
    }

    public FeedBack getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedBack feedback) {
        this.feedback = feedback;
    }
    
    
}
