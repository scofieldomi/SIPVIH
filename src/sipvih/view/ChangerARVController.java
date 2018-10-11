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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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
public class ChangerARVController {
    
    @FXML
    public TextField chargeViralePlasmatique;
    @FXML
    public TextField chargeViraleDBS;
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
    private CheckBox anemie_grave;
    @FXML
    private CheckBox neutropenie_grave;
    @FXML
    private CheckBox acidose_lactique;
    @FXML
    private CheckBox lipodystrophie;
    @FXML
    private CheckBox toxicite_renale;
    @FXML
    private CheckBox toxicite_systeme_nerveux;
    @FXML
    private CheckBox hepatite;
    @FXML
    private CheckBox rash_modere;
    @FXML
    private CheckBox rash_grave;
    @FXML
    private CheckBox intolerance_gastro_intestinale_severe;
    @FXML
    private CheckBox osteoporose;
    @FXML
    private CheckBox diarrhee_vomissement_naussee;
    @FXML
    private CheckBox pancreatite;
    @FXML
    private CheckBox reaction_hypersensibilite;
    
       

    public Main main ;
    
    @FXML
    public void traitementVirologie() throws IOException{
        
        String serologieR = (String) serologie.getValue();

        String typeTraitementR = (String) typeTraitement.getValue();
        
        if (schemaTherapeutique.getText().compareTo("")==0) {
            boiteDialogue("Veuillez saisir le schéma suivi!");
        }
        else
        {
            
             if (chargeViralePlasmatique.getText().compareTo("")==0) {
            
            if(chargeViraleDBS.getText().compareTo("")==0){
                boiteDialogue("Veuillez saisir la charge virale");
            }
            else{
                Integer cvdbsR = Integer.parseInt(chargeViraleDBS.getText());
                if (cvdbsR>3000) {
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
        }
        else if(chargeViraleDBS.getText().compareTo("")==0){
            if (chargeViralePlasmatique.getText().compareTo("")==0) {
                boiteDialogue("Veuillez saisir la charge virale");
            }
            else
            {   
                Integer cvpR = Integer.parseInt(chargeViralePlasmatique.getText());
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
            
        }
        else{
            Integer cvpR = Integer.parseInt(chargeViralePlasmatique.getText());
            Integer cvdbsR = Integer.parseInt(chargeViraleDBS.getText());
            if ((cvpR>1000)||(cvdbsR>3000)) {
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
            
        }
       
        
    }
    
    
    private void boiteDialogue(String info){
        Alert dialog = new Alert(AlertType.INFORMATION);
        dialog.setTitle("Information");
        dialog.setHeaderText(""+info);
        dialog.setContentText(null);
        dialog.showAndWait();
    }
    
    
     public void boiteDialogue2() throws IOException{
        Alert dialog = new Alert(AlertType.INFORMATION);
        dialog.setTitle("Changement de traitement");
        dialog.setHeaderText("Changer de ligne thérapeutique!");
        dialog.setContentText("Voulez-vous continuer?");
        
        ButtonType btnoui = new ButtonType("Oui");
        ButtonType btnnon = new ButtonType("Non", ButtonData.CANCEL_CLOSE);
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
     
    @FXML
    public void btnRetour() throws IOException{
        main.afficheARV();
    }
    
    @FXML
    public void btnRetour2() throws IOException, Exception{
        main.MainView();
    }
     
    @FXML
     public void traitementEffet(){
         
       CheckBox effet[]={anemie_grave,neutropenie_grave,acidose_lactique,lipodystrophie,toxicite_renale,toxicite_systeme_nerveux,hepatite,rash_modere,rash_grave,intolerance_gastro_intestinale_severe,osteoporose,diarrhee_vomissement_naussee,pancreatite,reaction_hypersensibilite};
 
        if(schemaTherapeutique.getText().compareTo("")==0){
            boiteDialogue("Veuillez saisir le schéma suivi!!!");
        }
        else{
            String schemaR = (String) schemaTherapeutique.getText();
            String schemaSource=schemaR;
            for (int i = 0; i < effet.length; i++) {
                if (effet[i].isSelected()) {
                    String arvsource=getARVeffetSource(effet[i].getId());
                    String arvsubstitut=getARVeffetSubstitut(effet[i].getId());
                    if (schemaR.contains(arvsource)) {
                        schemaR=schemaR.replace(arvsource, arvsubstitut);
                       
                    }
                    
                }
            }
            if(schemaSource.compareTo(schemaR)==0){
                boiteDialogue("Continuez le même schéma de traitement : \n"+schemaR);
            }
            else{
                boiteDialogue("Modifier le schéma de traitement par ce schéma: \n"+schemaR);
            }
            
        }
    }
    public String getARVeffetSource(String effet){
       String arvsource="";
        ResultSet resultat=ARV.getARVeffet(effet);
          if (resultat != null) {
              
              while (resultat.hasNext()) {
                    QuerySolution qsol = resultat.nextSolution();
                    Literal nom = qsol.getLiteral("nom"); 
                    arvsource=""+nom;
            }
              
          }    
       return arvsource;
    }
    public String getARVeffetSubstitut(String effet){
       String arvsubstitut="";
        ResultSet resultat=ARV.getARVSubstitue(effet);
          if (resultat != null) {
              
              while (resultat.hasNext()) {
                    QuerySolution qsol = resultat.nextSolution();
                    Literal nom = qsol.getLiteral("nom"); 
                    arvsubstitut=""+nom;
            }
              
          }    
       return arvsubstitut;
    }
     
      public void setMain(Main main){
        this.main = main;
    }
     
}
