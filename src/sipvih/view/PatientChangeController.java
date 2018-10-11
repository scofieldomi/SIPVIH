/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;
import sipvih.Main;
import sipvih.ontologie.ARV;
import sipvih.ontologie.Patient;
import static sipvih.ontologie.Patient.insertionTraitement;


/**
 *
 * @author Abdramane
 */
public class PatientChangeController {
    public Main main ;
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
    
    @FXML
    Label codeR;
     @FXML
    Label serologieR;
    @FXML
    Label dateNaissanceR;
    @FXML
    Label traitementR;
    
    
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
    private Label prop9;
    @FXML
    private Label prop10;
    @FXML
    private Label prop11;
    @FXML
    private Label prop12;
    @FXML
    private Label prop13;
    @FXML
    private Label prop14;
    @FXML
    private Label prop15;
    
     @FXML
    public TextField chargeViralePlasmatique;
    @FXML
    public TextField chargeViraleDBS;
    @FXML
    public ComboBox typeTraitement;
    
     public void initialize(String code,String serologie,String dateNaissance,String traitement){
        codeR.setText(code);
        serologieR.setText(serologie);        
        dateNaissanceR.setText(dateNaissance);
        traitementR.setText(traitement);
    }
    
    @FXML
     public void traitementEffet() throws IOException{
         
            CheckBox effet[]={anemie_grave,neutropenie_grave,acidose_lactique,lipodystrophie,toxicite_renale,toxicite_systeme_nerveux,hepatite,rash_modere,rash_grave,intolerance_gastro_intestinale_severe,osteoporose,diarrhee_vomissement_naussee,pancreatite,reaction_hypersensibilite};
            
            String code=codeR.getText();
            
            String schemaR = traitementR.getText();
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
                boiteDialogue("Continuez le même schéma de traitement : \n");
            }
            else{
                        ResultSet result=Patient.getRessourceTraitement(schemaR);
                        Resource traitement=null;
                        while (result.hasNext()) {
                                QuerySolution qsol = result.nextSolution();
                                traitement=qsol.getResource("traitement");
                        }
                        String resourceTr=""+traitement;
                        if (traitement==null) {
                            String traite="<http://www.medecine.fr/maladies#"+schemaR+">";
                                String traiteAnc="<http://www.medecine.fr/maladies#"+schemaSource+">";
                                String datenaissanc=dateNaissanceR.getText();
                                String serolog=serologieR.getText();
                                insertionTraitement(schemaR);
                                boiteDialogue2(schemaR,traiteAnc, code,traite,datenaissanc,serolog);
                                main.affichePagePatient();
                        }
                        else{
                                String traite="<"+traitement+">";
                                String traiteAnc="<http://www.medecine.fr/maladies#"+schemaSource+">";
                                String datenaissanc=dateNaissanceR.getText();
                                String serolog=serologieR.getText();
                                boiteDialogue2(schemaR,traiteAnc, code,traite,datenaissanc,serolog);
                                main.affichePagePatient();
                                
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
    
    public void annulerChangement() throws IOException{
        main.affichePagePatient();
    }
    
     private void boiteDialogue(String info){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Information");
        dialog.setHeaderText(""+info);
        dialog.setContentText(null);
        dialog.showAndWait();
    }
      private void boiteDialogue2(String schema,String traiteAnc,String code,String traite, String datenaissanc,String serolog){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Information");
        dialog.setHeaderText("Modifier le schéma de traitement par ce schéma:\n"+schema);
        dialog.setContentText("Voulez-vous enregistrer le changement de traitement?");
        
        ButtonType btnOui = new ButtonType("Oui");
        ButtonType btnNon = new ButtonType("Non");
        dialog.getButtonTypes().setAll(btnOui, btnNon);
        Optional<ButtonType> answer = dialog.showAndWait();
        if (answer.get() == btnOui) {
                Patient.modificationPatientTraitement(traiteAnc,code, traite, datenaissanc, serolog);
                boiteDialogue("Le changement de traitement a été effectué!");
        }
    }
    
    @FXML
    public void traitementVirologie() throws IOException{
        
        String serologie = serologieR.getText();

        String typeTraitementR = (String) typeTraitement.getValue();
       
            
             if (chargeViralePlasmatique.getText().compareTo("")==0) {
            
            if(chargeViraleDBS.getText().compareTo("")==0){
                boiteDialogue("Veuillez saisir la charge virale");
            }
            else{
                Integer cvdbsR = Integer.parseInt(chargeViraleDBS.getText());
                if (cvdbsR>3000) {
                      if (typeTraitementR.compareTo("Première ligne")==0) {
                        boiteDialogue3();
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
                        boiteDialogue3();
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
                        boiteDialogue3();
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
    
     public void boiteDialogue3() throws IOException{
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
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
    
     public int getAge(String dateNaissance){
        int age;
        Date date=new Date();
        LocalDate dateJour = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(date) );
        LocalDate dateR=LocalDate.parse(dateNaissance);
       
        int anneeJ=dateJour.getYear();
        int anneeN=dateR.getYear();
         age=anneeJ-anneeN;
        return age;
    }
    
    public String getCategoriePatient(int age){
        String categoriePatient="";
        
        if(age<=3){
            categoriePatient="EnfantMoins3ans";
        }
        else if (age<=10) {
            categoriePatient="EnfantPlus3Ans";
        }
        else{
           categoriePatient="Adulte";
        }
        return categoriePatient;
    }
     @FXML
    public void changerTraitementARV() throws IOException{
    
        String serologie = serologieR.getText();
        String patientR = getCategoriePatient(getAge(dateNaissanceR.getText()));
        
        //Float hemoglobineR =  Float.parseFloat(hemoglobine.getText());
        //Float clairanceR =  Float.parseFloat(clairance.getText());
        
        ResultSet resultat=null;
        ResultSet result=null;
        String ligne="DeuxiemeLigne";
        
        
        if(patientR.compareTo("EnfantMoins3ans")==0)
        {
            boiteDialogue("Pas de changement, à adapter aux résultats du test de résistance au VIH...");
        }
        else
        {
            resultat= Patient.propositionARVRecommende(serologie, patientR,ligne);
            result = Patient.propositionARVAlternatif(serologie, patientR,ligne);
            affichesSchema(resultat, result);
        }
    }
     
     public String diviserSchema(){
         String schemaDivise="";
          String shemaR = traitementR.getText();
         
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
            String resourceSchema1="";
            String resourceSchema2="";
            
            Label propa[]={prop1,prop2,prop3};
            Label propb[]={prop4,prop5,prop6,prop7};
            Label propc[]={prop8,prop9,prop10};
            Label propd[]={prop11,prop12,prop13,prop14};
            for (int t = 0; t < propa.length; t++) {
                propa[t].setText("");
            }
            for (int v = 0; v < propb.length; v++) {
                propb[v].setText("");
            }
            for (int t = 0; t < propc.length; t++) {
                propc[t].setText("");
            }
            for (int v = 0; v < propd.length; v++) {
                propd[v].setText("");
            }
            
          while (resultat.hasNext()) {
             QuerySolution qsol = resultat.nextSolution();
             Literal nomarv = qsol.getLiteral("nom");
             Resource traitement=qsol.getResource("traitement");
             schemaChaine1=schemaChaine1+nomarv+" ";
             resourceSchema1=resourceSchema1+traitement+" ";
            }
          String schema1[]=schemaChaine1.split(" ");
          String resource1[]=resourceSchema1.split(" ");
          for (int i = 0; i <propa.length; i++) {
              if (i<schema1.length) {
                        if ((schema1[i].contains(schemaDiv1[0]))|| ((schema1[i].contains(schemaDiv1[1]))&&(schema1[i].contains(schemaDiv1[2])))) {
                 
                  }else{
                         propa[i].setText(""+schema1[i]);
                         propc[i].setText(""+resource1[i]);   
                        }
              }
              else{
                    propa[i].setText("");
                    propc[i].setText("");
              }
                   
        }
          
          while (result.hasNext()) {
             QuerySolution qsol = result.nextSolution();
             Literal nomarv = qsol.getLiteral("nom"); 
            schemaChaine=schemaChaine+nomarv+" ";
            Resource traitement=qsol.getResource("traitement");
            resourceSchema2=resourceSchema2+traitement+" ";
            }
          String schema[]=schemaChaine.split(" ");
          String resource2[]=resourceSchema2.split(" ");
          for (int i = 0; i <propb.length; i++) {
              if (i<schema.length) {
                    if ((schema[i].contains(schemaDiv1[0]))|| ((schema[i].contains(schemaDiv1[1]))&&(schema[i].contains(schemaDiv1[2])))) {
                      
                  }else{
                        propb[i].setText(""+schema[i]);
                        propd[i].setText(""+resource2[i]);
                    }
              }
              else{
                  propb[i].setText("");
                  propd[i].setText("");
              }
                   
        }
          
         
    }
     
     
     @FXML
    public void choisirSchema(String schema,String traitement){
                String code=codeR.getText();
                String traite="<"+traitement+">";
                String datenaissanc=dateNaissanceR.getText();
                String serolog=serologieR.getText();
                Patient.modificationPatient(code, traite, datenaissanc, serolog);
                boiteDialogue("Patient mis sous traitement ARV !");
        try {
            main.affichePagePatient();
        } catch (IOException ex) {
            Logger.getLogger(PatientSuiteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    public void choisirSchema1(){
         if (prop1.getText().compareTo("")!=0) {
             choisirSchema(prop1.getText(),prop8.getText());
         }
    }
    
     @FXML
    public void choisirSchema2(){
        if (prop2.getText().compareTo("")!=0) {
             choisirSchema(prop2.getText(),prop9.getText());
         }
    }
    
     @FXML
    public void choisirSchema3(){
        if (prop3.getText().compareTo("")!=0) {
             choisirSchema(prop3.getText(),prop10.getText());
         }
    }
     @FXML
    public void choisirSchema4(){
        if (prop4.getText().compareTo("")!=0) {
             choisirSchema(prop4.getText(),prop11.getText());
         }
    }
     @FXML
    public void choisirSchema5(){
        if (prop5.getText().compareTo("")!=0) {
             choisirSchema(prop5.getText(),prop12.getText());
         }
    }
     @FXML
    public void choisirSchema6(){
        if (prop6.getText().compareTo("")!=0) {
             choisirSchema(prop6.getText(),prop13.getText());
         }
    }
     @FXML
    public void choisirSchema7(){
        if (prop7.getText().compareTo("")!=0) {
             choisirSchema(prop7.getText(),prop14.getText());
         }
    }
    public void setMain(Main main){
        this.main = main;
    }
    
}
