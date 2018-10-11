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
public class modelePatient {
    
    
      //Declare ARV Table Columns
    
    private StringProperty code;
    private StringProperty serologie;
    private StringProperty dateNaissance;
     private StringProperty traitement;
    
   
    //Constructor
    public modelePatient() {
        this.code = new SimpleStringProperty();
        this.serologie= new SimpleStringProperty();
        this.dateNaissance= new SimpleStringProperty();
         this.traitement= new SimpleStringProperty();
    }

    //code
    public String getCode () {
        return code.get();
    }

    public void setCode(String codeR){
        this.code.set(codeR);
    }

    public StringProperty codeProperty() {
        return code;
    }

    //serologie
    public String getSerologie () {
        return serologie.get();
    }

    public void setSerologie(String serologieR){
        this.serologie.set(serologieR);
    }

    public StringProperty serologieProperty() {
        return serologie;
    }
    
    //date_naissancs
    public String getDateNaissance () {
        return dateNaissance.get();
    }

    public void setDateNaissance(String dateNaissanceR){
        this.dateNaissance.set(dateNaissanceR);
    }

    public StringProperty dateNaissanceProperty() {
        return dateNaissance;
    }
    
     //date_naissancs
    public String getTraitement () {
        return traitement.get();
    }

    public void setTraitement(String traitementR){
        this.traitement.set(traitementR);
    }

    public StringProperty traitementProperty() {
        return traitement;
    }
    
}
