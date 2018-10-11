/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih.ontologie;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Abdramane
 */
public class modeleArv {
     //Declare ARV Table Columns
    
    private StringProperty nomARV;
    private StringProperty nomAbrege;
    private StringProperty posologie;
    private StringProperty effetIndesirable;
   
    //Constructor
    public modeleArv() {
        this.nomARV = new SimpleStringProperty();
        this.nomAbrege= new SimpleStringProperty();
        this.posologie= new SimpleStringProperty();
        this.effetIndesirable= new SimpleStringProperty();
    }

    //nomARV
    public String getNomARV () {
        return nomARV.get();
    }

    public void setNomARV(String nomARVR){
        this.nomARV.set(nomARVR);
    }

    public StringProperty NomARVProperty() {
        return nomARV;
    }

    //nom_abrege
    public String getNomAbrege () {
        return nomARV.get();
    }

    public void setNomAbrege(String nomAbregeR){
        this.nomAbrege.set(nomAbregeR);
    }

    public StringProperty nomAbregeProperty() {
        return nomAbrege;
    }
    
     //posologie
    public String getPosologie () {
        return posologie.get();
    }

    public void setPosologie(String posologieR){
        this.posologie.set(posologieR);
    }

    public StringProperty posologieProperty() {
        return posologie;
    }
    
     //effet indesirables
    public String getEffetIndesirable () {
        return effetIndesirable.get();
    }

    public void setEffetIndesirable(String effetIndesirableR){
        this.effetIndesirable.set(effetIndesirableR);
    }

    public StringProperty effetIndesirableProperty() {
        return effetIndesirable;
    }
   
}
