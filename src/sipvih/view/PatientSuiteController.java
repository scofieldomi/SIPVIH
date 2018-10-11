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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;
import sipvih.Main;
import sipvih.ontologie.Patient;



/**
 *
 * @author Abdramane
 */
public class PatientSuiteController {
    @FXML
    Label codeR;
     @FXML
    Label serologieR;
    @FXML
    Label dateNaissanceR;
    @FXML
    private TextField hemoglobine;
    @FXML
    private TextField clairance;
    
    public Main main ;
    
    @FXML
    private Label prop1;  
    @FXML
    private Label prop1a;  
    @FXML
    private Label prop1b;  
    @FXML
    private Label prop1c;   
    @FXML
    private Label prop2;
    @FXML
    private Label prop2a;
    @FXML
    private Label prop2b;
    @FXML
    private Label prop2c;
    @FXML
    private Label prop3;
    @FXML
    private Label prop3a;
    @FXML
    private Label prop3b;
    @FXML
    private Label prop3c;
    @FXML
    private Label prop4;
    @FXML
    private Label prop4a;
    @FXML
    private Label prop4b;
    @FXML
    private Label prop4c;
    @FXML
    private Label prop5;
    @FXML
    private Label prop5a;
    @FXML
    private Label prop5b;
    @FXML
    private Label prop5c;
    @FXML
    private Label prop6;
    @FXML
    private Label prop6a;
    @FXML
    private Label prop6b;
    @FXML
    private Label prop6c;
    @FXML
    private Label prop7;
    @FXML
    private Label prop7a;
    @FXML
    private Label prop7b;
    @FXML
    private Label prop7c;
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
    
    public void initialize(String code,String serologie,String dateNaissance){
        codeR.setText(code);
        serologieR.setText(serologie);        
        dateNaissanceR.setText(dateNaissance);
       
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
    public  void proposeTraitement(){
         String serologie = serologieR.getText();
         String dateNaissance = dateNaissanceR.getText();
         
         int age = getAge(dateNaissance);
         String patientR = getCategoriePatient(age);
        
        //Float hemoglobineR =  Float.parseFloat(hemoglobine.getText());
        //Float clairanceR =  Float.parseFloat(clairance.getText());
        
        ResultSet resultat=null;
        ResultSet result=null;
        String ligne="PremiereLigne";
        
        resultat= Patient.propositionARVRecommende(serologie, patientR,ligne);
        result = Patient.propositionARVAlternatif(serologie, patientR,ligne);
        affichesSchema(resultat, result);
        couleurSchema();
    }
    
    
    public void affichesSchema(ResultSet resultat,ResultSet result){
            String schemaChaine="";
            String resourceSchema="";
             String schem="";
             String schemaRecommande="";
             String resourceSchemaRecommande="";
            Label prop[]={prop2,prop3,prop4,prop5,prop6,prop7};
            Label propb[]={prop9,prop10,prop11,prop12,prop13,prop14};
          while (resultat.hasNext()) {
             QuerySolution qsol = resultat.nextSolution();
             Literal nomarv = qsol.getLiteral("nom"); 
             Resource traitement=qsol.getResource("traitement");
             schemaRecommande=""+nomarv;
             resourceSchemaRecommande=""+traitement;
             prop8.setText(""+traitement);
            }
          
           String tabRecomande[]=schemaRecommande.split("\\+");
          if (tabRecomande.length==3) {
              prop1.setText(tabRecomande[0]);
              prop1a.setText("+"+tabRecomande[1]);
              prop1b.setText("+"+tabRecomande[2]);
        }
          else if (tabRecomande.length==2) {
              String tabRecommandeA[]=tabRecomande[0].split("/");
            prop1.setText(tabRecommandeA[0]);
            prop1a.setText("/"+tabRecommandeA[1]);
            prop1b.setText("+"+tabRecomande[1]);
        }
          else{
               String tabRecommandeA[]=tabRecomande[0].split("/");
            prop1.setText(tabRecommandeA[0]);
            prop1a.setText("/"+tabRecommandeA[1]);
            prop1b.setText("/"+tabRecommandeA[2]);
          }
          
          
          while (result.hasNext()) {
             QuerySolution qsol = result.nextSolution();
             Literal nomarv = qsol.getLiteral("nom");
             Resource traitement=qsol.getResource("traitement");
             schemaChaine=schemaChaine+nomarv+" ";
             resourceSchema=resourceSchema+traitement+" ";
            }
      
          
          String schema[]=schemaChaine.split(" ");
          String resource[]=resourceSchema.split(" ");
          for (int i = 0; i <propb.length; i++) {
              if (i<schema.length) {
                  propb[i].setText(""+resource[i]);
              }
              else{
                   propb[i].setText("");
              }     
        }
          
          Label propa[]={prop2,prop3,prop4,prop5,prop6,prop7};
          Label propc[]={prop2a,prop3a,prop4a,prop5a,prop6a,prop7a};
          Label propd[]={prop2b,prop3b,prop4b,prop5b,prop6b,prop7b};
          
        for (int i = 0; i <propa.length; i++) {
              if (i<schema.length) {
                    String tabAlternatif1[]= schema[i].split("\\+");
                if (tabAlternatif1.length==3) {
                        propa[i].setText(tabAlternatif1[0]);
                        propc[i].setText("+"+tabAlternatif1[1]);
                        propd[i].setText("+"+tabAlternatif1[2]);
                }
                else if (tabAlternatif1.length==2) {
                        String tabAlternatifA[]=tabAlternatif1[0].split("/");
                        propa[i].setText(tabAlternatifA[0]);
                        propc[i].setText("/"+tabAlternatifA[1]);
                        propd[i].setText("+"+tabAlternatif1[1]);
                }
                else{
                        String tabAlternatifA[]=tabAlternatif1[0].split("/");
                        propa[i].setText(tabAlternatifA[0]);
                        propc[i].setText("/"+tabAlternatifA[1]);
                        propd[i].setText("/"+tabAlternatifA[2]);
                }
                  
              }
              else{
                  propa[i].setText("");
                  propc[i].setText("");
                  propd[i].setText("");
                  
              }
                   
        }
        
    }
    
    
    private void couleurSchema(){
        Label prop[]={prop1,prop1a,prop1b,prop2,prop2a,prop2b,prop3,prop3a,prop3b,prop4,prop4a,prop4b,
            prop5,prop5a,prop5b,prop6,prop6a,prop6b,prop7,prop7a,prop7b};
        
        if (hemoglobine.getText().compareTo("")!=0) {
            Float hemoglobineR =  Float.parseFloat(hemoglobine.getText());
            
            if (clairance.getText().compareTo("")!=0) {
                Float clairanceR =  Float.parseFloat(clairance.getText());
                
                    for (int i = 0; i < prop.length; i++) {
                        if (prop[i].getText().contains("AZT") && (hemoglobineR<7.5)) {
                            prop[i].setTextFill(Color.RED);
                        }
                        else if (prop[i].getText().contains("TDF") && (clairanceR<50)) {
                            prop[i].setTextFill(Color.RED);
                        }
                        else{
                            prop[i].setTextFill(Color.BLACK);
                        }
                        
                       
                     }
            }
            else{
                     for (int i = 0; i < prop.length; i++) {
                        if (prop[i].getText().contains("AZT") && (hemoglobineR<7.5)) {
                            prop[i].setTextFill(Color.RED);
                        }
                        else{
                            prop[i].setTextFill(Color.BLACK);
                        }
                     }
            }
        }
        else{
             if (clairance.getText().compareTo("")!=0) {
            Float clairanceR =  Float.parseFloat(clairance.getText());
                for (int i = 0; i < prop.length; i++) {
                    if (prop[i].getText().contains("TDF") && (clairanceR<50)) {
                        prop[i].setTextFill(Color.RED);
                    }
                    else{
                         prop[i].setTextFill(Color.BLACK);
                    }
                }
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
             String schema[]=prop8.getText().split("#");
             choisirSchema(schema[1],prop8.getText());
         }
    }
    
     @FXML
    public void choisirSchema2(){
        if (prop2.getText().compareTo("")!=0) {
            String schema[]=prop9.getText().split("#");
             choisirSchema(schema[1],prop9.getText());
         }
    }
    
     @FXML
    public void choisirSchema3(){
        if (prop3.getText().compareTo("")!=0) {
            String schema[]=prop10.getText().split("#");
             choisirSchema(schema[1],prop10.getText());
         }
    }
     @FXML
    public void choisirSchema4(){
        if (prop4.getText().compareTo("")!=0) {
            String schema[]=prop11.getText().split("#");
             choisirSchema(schema[1],prop11.getText());
         }
    }
     @FXML
    public void choisirSchema5(){
        if (prop5.getText().compareTo("")!=0) {
            String schema[]=prop12.getText().split("#");
             choisirSchema(schema[1],prop12.getText());
         }
    }
     @FXML
    public void choisirSchema6(){
        if (prop6.getText().compareTo("")!=0) {
            String schema[]=prop13.getText().split("#");
             choisirSchema(schema[1],prop13.getText());
         }
    }
     @FXML
    public void choisirSchema7(){
        if (prop7.getText().compareTo("")!=0) {
            String schema[]=prop14.getText().split("#");
             choisirSchema(schema[1],prop14.getText());
         }
    }
    
    private void boiteDialogue(String info){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Information");
        dialog.setHeaderText(""+info);
        dialog.setContentText(null);
        dialog.showAndWait();
    }
    
      @FXML
    public void retourPagePatient() throws IOException{
        main.affichePagePatient();
    }
     public void setMain(Main main){
        this.main = main;
    }
    
}
