/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Abdramane
 */
public class modeleARV {
    private final StringProperty nomARV;
    private final StringProperty nomAbrege;
    
    public modeleARV(){
        this.nomARV=new SimpleStringProperty();
        this.nomAbrege=new SimpleStringProperty();
    }
    public String getNomARV(){
        return nomARV.get();
    }
    public void setNomARV(String nomARV){
        this.nomARV.set(nomARV);
    }
    public StringProperty nomARVProperty(){
        return nomARV;
    }
    
    public String getNomAbrege(){
        return nomAbrege.get();
    }
    public void setNomAbrege(String nomAbregre){
        this.nomAbrege.set(nomAbregre);
    }
    public StringProperty nomAbregeProperty(){
        return nomAbrege;
    }
}
