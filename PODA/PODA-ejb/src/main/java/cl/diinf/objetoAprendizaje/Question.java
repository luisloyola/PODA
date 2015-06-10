
package cl.diinf.objetoAprendizaje;

import java.util.ArrayList;
import java.util.List;


public class Question {
    private List<Form> forms;
    
    public Question(){
        forms = new ArrayList<>();
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }
    
    public void addForm(Form forms){
        this.forms.add(forms);
    }
}
