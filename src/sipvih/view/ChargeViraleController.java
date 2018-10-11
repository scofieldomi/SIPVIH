/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih.view;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import sipvih.Main;
import sipvih.ontologie.ARV;

/**
 *
 * @author Abdramane
 */
public class ChargeViraleController {
    
    public Main main ;
    @FXML
    public TextField chargeViralePlasmatique;
    @FXML
    public TextField chargeViraleDBS;
    @FXML
    public TextField chargeViralePlasmatiqueControle;
    @FXML
    public TextField schemaTherapeutique;
    
    @FXML
    public ComboBox serologie;
    @FXML
    public ComboBox categoriePatient;
    @FXML
    public ComboBox typeTraitement;

    @FXML
    private Label prop1;  
    @FXML
    private Label prop2;
    @FXML
    private Label prop3;
    @FXML
    private Label prop4;
    @FXML
    private Label prop5;
    @FXML
    private Label prop6;
    @FXML
    private Label prop7;
    @FXML
    private Label prop8;
    
    @FXML
    private Label cvPLabel; 
    @FXML
    private Label cvPbLabel; 
    @FXML
    private Label visible1; 
    @FXML
    private Label visible2; 
    @FXML
    private Label visible3; 
    @FXML
    private Label visible4; 
    @FXML
    private Label visible5; 
    @FXML
    private Label visible6; 
    @FXML
    private Label visible7; 
    @FXML
    private Label visible8;
    
   
    
    ChangerARVController changerARVControl= new ChangerARVController();
    
    
    @FXML
    public void traitementVirologie() throws IOException{
        
     
      if (chargeViralePlasmatiqueControle.isVisible()==false) {
       if (chargeViralePlasmatique.getText().compareTo("")==0) {
            
            if(chargeViraleDBS.getText().compareTo("")==0){
                boiteDialogue("Veuillez saisir la charge virale");
            }
            else{
                Integer cvdbsR = Integer.parseInt(chargeViraleDBS.getText());
                if (cvdbsR>3000) {
                        boiteDialogueConfirmation("Renforcer l'observance et contrôle dans 3 mois");
                }
                else{
                    boiteDialogue("Continuer le traitement");
                }
            }
        }
        else if(chargeViraleDBS.getText().compareTo("")==0){
            if (chargeViralePlasmatique.getText().compareTo("")==0) {
                boiteDialogue("Veuillez saisir la charge virale");
            }
            else
            {   
                Integer cvpR = Integer.parseInt(chargeViralePlasmatique.getText());
                if (cvpR>1000) {
                             boiteDialogueConfirmation("Renforcer l'observance et contrôle dans 3 mois");
                }
                else{
                    boiteDialogue("Continuer le traitement");
                }
            }
        }
        else{
            Integer cvpR = Integer.parseInt(chargeViralePlasmatique.getText());
            Integer cvdbsR = Integer.parseInt(chargeViraleDBS.getText());
            if ((cvpR>1000)||(cvdbsR>3000)) {
                 boiteDialogueConfirmation("Renforcer l'observance et contrôle dans 3 mois");
            }
            else{
                    boiteDialogue("Continuer le traitement");
            }
        }
      }
      else
      {
          if ((chargeViralePlasmatiqueControle.getText().compareTo("")==0)||(schemaTherapeutique.getText().compareTo("")==0)) {
              boiteDialogue("Veuillez bien renseigner les informations");
          }
          else{
              changerTraitement();
          }
          
      }
    }
    
    private void changerTraitement() throws IOException{
            Integer cvpR = Integer.parseInt(chargeViralePlasmatiqueControle.getText());
            String typeTraitementR = (String) typeTraitement.getValue();
            if (cvpR>1000) {
                if (typeTraitementR.compareTo("Première ligne")==0) {
                        boiteDialogue2();
                    }
                      else{
                           boiteDialogue("Le patient doit être référé dans un centre de référence national!");
                      }     
            }
            else{
                 boiteDialogue("Continuer le traitement");
            }
    }
    
    
    public void boiteDialogue2() throws IOException{
        Alert dialog = new Alert(AlertType.INFORMATION);
        dialog.setTitle("Changement de traitement");
        dialog.setHeaderText("Changer de ligne thérapeutique!");
        dialog.setContentText("Voulez-vous continuer?");
        
        ButtonType btnoui = new ButtonType("Oui");
        ButtonType btnnon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getButtonTypes().setAll(btnoui, btnnon);
        Optional<ButtonType> answer = dialog.showAndWait();
        
        if (answer.get() == btnoui) {
              
               changerTraitementARV();
                
                
        }
        else {
                System.out.println("Quitter");
        }
    }
    
    
     @FXML
    public void changerTraitementARV() throws IOException{
    
        String serologieR = (String) serologie.getValue();
        String patientR = (String) categoriePatient.getValue();
        
        //Float hemoglobineR =  Float.parseFloat(hemoglobine.getText());
        //Float clairanceR =  Float.parseFloat(clairance.getText());
        
        ResultSet resultat=null;
        ResultSet result=null;
        String ligne="DeuxiemeLigne";
        
        if ((patientR.compareTo("Adolescent")==0) || (patientR.compareTo("Adulte")==0)) {
            String patient="Adulte";
            resultat=ARV.propositionARVRecommende(serologieR, patient,ligne);
            result=ARV.propositionARVAlternatif(serologieR, patient,ligne);
        }
        else if(patientR.compareTo("Enfant moins de 3 ans")==0)
        {
            boiteDialogue("Pas de changement, à adapter aux résultats du test de résistance au VIH...");
        }
        else
        {
            String patient="EnfantPlus3Ans";
            resultat=ARV.propositionARVRecommende(serologieR, patient,ligne);
            result=ARV.propositionARVAlternatif(serologieR, patient,ligne);
        }
       affichesSchema(resultat,result);
    }
    
     public String diviserSchema(){
         String schemaDivise="";
          String shemaR = schemaTherapeutique.getText();
         
         String schemaR1[]= shemaR.split("\\+");
         if (schemaR1.length==1) {
             String schemR2[]=schemaR1[0].split("/");
             for (int i = 0; i < schemR2.length; i++) {
                 schemaDivise=schemaDivise+schemR2[i]+" ";
             }
         }else if(schemaR1.length==2){
             String schemR2[]=schemaR1[0].split("/");
             for (int i = 0; i < schemR2.length; i++) {
                 schemaDivise=schemaDivise+schemR2[i]+" ";
             }
             schemaDivise=schemaDivise+schemaR1[1];
         }else{
             for (int i = 0; i < schemaR1.length; i++) {
                 schemaDivise=schemaDivise+schemaR1[i]+" ";
             }
         }
         return schemaDivise;
     } 
     public void affichesSchema(ResultSet resultat,ResultSet result){
            String schemaDiv=diviserSchema();
            String schemaDiv1[]=schemaDiv.split(" ");
            
            
            String schemaChaine="";
            String schemaChaine1="";
            Label propa[]={prop1,prop2,prop3};
            Label propb[]={prop4,prop5,prop6,prop7};
            for (int t = 0; t < propa.length; t++) {
                propa[t].setText("");
            }
            for (int v = 0; v < propb.length; v++) {
                propb[v].setText("");
            }
            
          while (resultat.hasNext()) {
             QuerySolution qsol = resultat.nextSolution();
             Literal nomarv = qsol.getLiteral("nom"); 
             schemaChaine1=schemaChaine1+nomarv+" ";
            }
          String schema1[]=schemaChaine1.split(" ");
          for (int i = 0; i <propa.length; i++) {
              if (i<schema1.length) {
                        if ((schema1[i].contains(schemaDiv1[0]))|| ((schema1[i].contains(schemaDiv1[1]))&&(schema1[i].contains(schemaDiv1[2])))) {
                 
                  }else
                       propa[i].setText(""+schema1[i]);
              }
              else
                   propa[i].setText("");
        }
          
          while (result.hasNext()) {
             QuerySolution qsol = result.nextSolution();
             Literal nomarv = qsol.getLiteral("nom"); 
            schemaChaine=schemaChaine+nomarv+" ";
            }
          String schema[]=schemaChaine.split(" ");
          for (int i = 0; i <propb.length; i++) {
              if (i<schema.length) {
                    if ((schema[i].contains(schemaDiv1[0]))|| ((schema[i].contains(schemaDiv1[1]))&&(schema[i].contains(schemaDiv1[2])))) {
                      
                  }else
                        propb[i].setText(""+schema[i]);
              }
              else
                   propb[i].setText("");
        }
          
         
    }
    
    private void boiteDialogue(String info){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Information");
        dialog.setHeaderText(""+info);
        dialog.setContentText(null);
        dialog.showAndWait();
    }
    
    private void boiteDialogueConfirmation(String info){
        
        Label visible[]={visible1,visible2,visible3,visible4,visible5,visible6,visible7,visible8};
        ComboBox combo[]={serologie,typeTraitement,categoriePatient};
      
        
        Alert dialogC = new Alert(AlertType.CONFIRMATION);
        dialogC.setTitle("Information");
        dialogC.setHeaderText(info);
        dialogC.setContentText("Avez-vous la charge virale de contrôle?");
        
        ButtonType btnOui = new ButtonType("Oui");
        ButtonType btnNon = new ButtonType("Non");
            
        dialogC.getButtonTypes().setAll(btnOui,btnNon);
        Optional<ButtonType> answer = dialogC.showAndWait();
        
        if (answer.get() == btnOui) {
                    cvPbLabel.setVisible(false);
                    cvPLabel.setVisible(false);
                    chargeViraleDBS.setVisible(false);
                    chargeViralePlasmatique.setVisible(false);
                    schemaTherapeutique.setVisible(true);
                    chargeViralePlasmatiqueControle.setVisible(true);
                    for (int i = 0; i < visible.length; i++) {
                            visible[i].setVisible(true);
                    }
                    for (int i = 0; i < combo.length; i++) {
                            combo[i].setVisible(true);
                    }
        }
        else {
                
        }
    }
    
    
     @FXML
    public void btnRetour() throws IOException, Exception{
        main.MainView();
    }
    
    
    
     public void setMain(Main main){
        this.main = main;
    }
}
