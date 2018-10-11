/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import sipvih.Main;
import sipvih.ontologie.ARV;
import static sipvih.ontologie.ARV.getARVList;
import static sipvih.ontologie.ARV.listeARV;
import static sipvih.ontologie.MO.stadeClinique;
import sipvih.ontologie.modeleArv;

/**
 *
 * @author Abdramane
 */
public class AccueilController {
    
    public Main main ;
    @FXML
    public ComboBox categoryPatient;
    
    @FXML
    private ComboBox centreSante;
    @FXML
    private ComboBox profession;
    
    
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
    private ComboBox serologieVIH;
    @FXML
    private TextField poids;
    @FXML
    private TextField hemoglobine;
    @FXML
    private TextField clairance;
    @FXML
    private Text recomEnfant;
    
    
    @FXML
    private TextField schemaTherpeutique;
    
    @FXML
    private TableView arvTable;
    @FXML
    private TableColumn<modeleArv, String>  arvNameColumn;
    @FXML
    private TableColumn<modeleArv, String> arvNameAbregeColumn;
    @FXML
    private TableColumn<modeleArv, String> posologieColumn;
    @FXML
    private TableColumn<modeleArv, String> effetIndesirableColumn;
    
    
    
    @FXML
    private CheckBox effetIndes;
    @FXML
    private CheckBox immunologique;
    @FXML
    private CheckBox virologique;
    @FXML
    private ComboBox serologieChangement;
    @FXML
    private ComboBox typeTraitement;
     @FXML
    private Label D ;
    @FXML
    private Label D1 ;
    @FXML
    private Label D2 ;
     @FXML
    private Label D3 ;
     @FXML
    private Label D4 ;
     @FXML
    private Label D5 ;
    @FXML
    private Label D6 ;
    @FXML
    private Label D7 ;
    @FXML
    private Label D8 ;
    
    @FXML
    private Label message1 ;
    @FXML
    private Label message2 ;
    @FXML
    private Label message3 ;
    
    @FXML
    private Label resultatDiagnostique;
     
     @FXML
    private RadioButton SGnon;

    @FXML
    private RadioButton SGoui;
    
            @FXML
    private RadioButton SGnon1;

    @FXML
    private RadioButton SGoui1;
    
            @FXML
    private RadioButton SGnon2;

    @FXML
    private RadioButton SGoui2;
    
    @FXML
    private RadioButton SGnon3;

    @FXML
    private RadioButton SGoui3;
    
     @FXML
    private RadioButton SGnon4;
      
    @FXML
    private RadioButton SGoui4;
     @FXML
    private RadioButton negatif;
      
      @FXML
    private RadioButton positif;
      @FXML
    private RadioButton SGnon6;
      
      @FXML
    private RadioButton SGoui6;
          @FXML
    private RadioButton SGnon7;
      
      @FXML
    private RadioButton SGoui7;
          @FXML
    private RadioButton SGnon8;
      
      @FXML
    private RadioButton SGoui8;
      
    @FXML
    private BorderPane panneausigne;
    
    @FXML
    private AnchorPane anchorpane00;

    
   // List<Checkbox> checkboxes ;
   
    @FXML
    private CheckBox LGP ;    
    
     @FXML
    private CheckBox CBP ;  
     
     @FXML
    private CheckBox Toxoplasmose ;    
     
     @FXML
    private CheckBox Isosporose ;  
      
     @FXML
    private CheckBox Zona ;    
    
     @FXML
    private CheckBox Candidose_buccale_persistante ;  
     
     @FXML
    private CheckBox Lymphadenopathie_generalisee_persistante ;
          
    @FXML
    private Label sc ;

    @FXML
    private ComboBox signe;
     
     @FXML
    private Label temoin ;  
   
    @FXML
    private TableColumn tableau; 

    @FXML
    private TextField tauxCD4;
    @FXML
    private CheckBox tuberculose;
    @FXML
    private CheckBox VHB;   
    @FXML
    private ToggleGroup femmeEnceinte;
    @FXML
    private ToggleGroup femmeAllaitante;
    @FXML
    private ToggleGroup signeDeGravite;
    @FXML
    private ToggleGroup signeDeGravite1;
    @FXML
    private ToggleGroup signeDeGravite2;
    @FXML
    private ToggleGroup signeDeGravite3;
    @FXML
    private ToggleGroup signeDeGravite4;
    
    String centre = "CSPS" ;
    
    
    
    
    
    
    
    
    @FXML
    public void handleButtonAction() throws Exception {
       // String professionR = (String) profession.getValue();
        //String centreSanteR = (String) centreSante.getValue();
      //  main = new Main();      
      //  System.out.println("Bien cliquer");
         main.MainView();
    }
    
   
    @FXML
    public void affichePageARV() throws IOException{
        main.afficheARV();
    }
    
     @FXML
    public void affichePageChargeVirale() throws IOException{
        main.afficheChargeVirale();
    }
    @FXML
    public void affichePagePatient() throws IOException{
        main.affichePagePatient();
    }
    
    @FXML
    public void initierARV() throws IOException{
    
        String serologie = (String) serologieVIH.getValue();
        String patientR = (String) categoryPatient.getValue();
        //Float hemoglobineR =  Float.parseFloat(hemoglobine.getText());
        //Float clairanceR =  Float.parseFloat(clairance.getText());
        
        ResultSet resultat=null;
        ResultSet result=null;
         String ligne="PremiereLigne";
        
        if ((patientR.compareTo("Adolescent")==0) || (patientR.compareTo("Adulte")==0)) {
            String patient="Adulte";
            recomEnfant.setVisible(false);
            resultat=ARV.propositionARVRecommende(serologie, patient,ligne);
            result=ARV.propositionARVAlternatif(serologie, patient,ligne);
        }
        else if(patientR.compareTo("Enfant moins de 3 ans")==0)
        {
            String patient="EnfantMoins3ans";
            recomEnfant.setVisible(true);
            resultat=ARV.propositionARVRecommende(serologie, patient,ligne);
            result=ARV.propositionARVAlternatif(serologie, patient,ligne);
        }
        else
        {
            String patient="EnfantPlus3Ans";
            recomEnfant.setVisible(false);
            resultat=ARV.propositionARVRecommende(serologie, patient,ligne);
            result=ARV.propositionARVAlternatif(serologie, patient,ligne);
        }
        affichesSchema(resultat, result);
        couleurSchema();
    }
    
    public void affichesSchema(ResultSet resultat,ResultSet result){
            String schemaChaine="";
             String schem="";
             String schemaRecommande="";
            
            Label prop[]={prop2c,prop3c,prop4c,prop5c,prop6c,prop7c};
            
          while (resultat.hasNext()) {
             QuerySolution qsol = resultat.nextSolution();
             Literal nomarv = qsol.getLiteral("nom"); 
             schemaRecommande=""+nomarv;
            }
          
          String tabRecomande[]=schemaRecommande.split("\\+");
          if (tabRecomande.length==3) {
              prop1.setText(tabRecomande[0]);
              prop1a.setText("+"+tabRecomande[1]);
              prop1b.setText("+"+tabRecomande[2]);
              prop1c.setText(schemaRecommande);
        }
          else if (tabRecomande.length==2) {
              String tabRecommandeA[]=tabRecomande[0].split("/");
            prop1.setText(tabRecommandeA[0]);
            prop1a.setText("/"+tabRecommandeA[1]);
            prop1b.setText("+"+tabRecomande[1]);
            prop1c.setText(schemaRecommande);
        }
          else{
               String tabRecommandeA[]=tabRecomande[0].split("/");
            prop1.setText(tabRecommandeA[0]);
            prop1a.setText("/"+tabRecommandeA[1]);
            prop1b.setText("/"+tabRecommandeA[2]);
            prop1c.setText(schemaRecommande);
          }
          
          while (result.hasNext()) {
             QuerySolution qsol = result.nextSolution();
             Literal nomarv = qsol.getLiteral("nom"); 
            schemaChaine=schemaChaine+nomarv+" ";
            }
          
      
          
          String schema[]=schemaChaine.split(" ");
          for (int i = 0; i <prop.length; i++) {
              if (i<schema.length) {
                  prop[i].setText(""+schema[i]);
              }
              else
                   prop[i].setText("");
        }
          
          Label propa[]={prop2,prop3,prop4,prop5,prop6,prop7};
          Label propb[]={prop2a,prop3a,prop4a,prop5a,prop6a,prop7a};
          Label propc[]={prop2b,prop3b,prop4b,prop5b,prop6b,prop7b};
          
        for (int i = 0; i <propa.length; i++) {
              if (i<schema.length) {
                    String tabAlternatif1[]= schema[i].split("\\+");
                if (tabAlternatif1.length==3) {
                        propa[i].setText(tabAlternatif1[0]);
                        propb[i].setText("+"+tabAlternatif1[1]);
                        propc[i].setText("+"+tabAlternatif1[2]);
                }
                else if (tabAlternatif1.length==2) {
                        String tabAlternatifA[]=tabAlternatif1[0].split("/");
                        propa[i].setText(tabAlternatifA[0]);
                        propb[i].setText("/"+tabAlternatifA[1]);
                        propc[i].setText("+"+tabAlternatif1[1]);
                }
                else{
                        String tabAlternatifA[]=tabAlternatif1[0].split("/");
                        propa[i].setText(tabAlternatifA[0]);
                        propb[i].setText("/"+tabAlternatifA[1]);
                        propc[i].setText("/"+tabAlternatifA[2]);
                }
                  
              }
              else{
                  propa[i].setText("");
                  propb[i].setText("");
                  propc[i].setText("");
                  
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
    
    private void affichePosologie(String schema){
        String patientR = (String) categoryPatient.getValue();
        
        Alert dialog = new Alert(AlertType.INFORMATION);
        String posologie=null;
        dialog.setTitle("Posologie");
        dialog.setHeaderText(""+schema);
        
        
        if (patientR.compareTo("Adulte")==0 || (patientR.compareTo("Adolescent")==0)) {
         posologie=posologieAdulte(schema);   
        }
        else{
            posologie=posologieEnfant(schema);
        }
        
        dialog.setContentText(""+posologie);
        dialog.showAndWait();
    }
    
    
    private String posologieEnfant(String schema){
        String posologie = null;
       
        
        String schemaARV[] = schema.split("\\+");
        
        if(poids.getText().compareTo("")==0){
            posologie="Veuillez donner le poids du patient";
        }
        else{
             Float poidsR =  Float.parseFloat(poids.getText());
            //Si le chema est du type TDF/FTC/EFV
                if (schemaARV.length==2) {
                         if ((schemaARV[0].contains("ABC")) || (schemaARV[0].contains("AZT"))) {
                                if(schemaARV[1].compareTo("LPV/r")==0){
                                        if((poidsR>=3) && (poidsR<=5.9)){
                                            posologie="2cpX2/jour (Matin et Soir:60/30mg) + 1 à 1.5 ml(Matin et Soir: 80mg/20mg/ml)";
                                         }
                                        else if((poidsR>=6) && (poidsR<=9.9)){
                                            posologie="3cpX2/jour (1.5 Matin et 1.5 Soir:60/30mg) + 1.5 ml(Matin et Soir: 80mg/20mg/ml)";
                                         }
                                        else if((poidsR>=10) && (poidsR<=13.9)){
                                            posologie="4cpX2/jour (2 Matin et 2 Soir:60/30mg) + 2 ml (Matin et Soir: 80mg/20mg/ml)";
                                         }
                                        else if((poidsR>=14) && (poidsR<=19.9)){
                                            posologie="5cpX2/jour (2.5 Matin et 2.5 Soir:60/30mg) + 2.5 ml (Matin et Soir: 80mg/20mg/ml)";
                                         }
                                        else if((poidsR>=20) && (poidsR<=24.9)){
                                            posologie="6cpX2/jour (3 Matin et 3 Soir:60/30mg) + 3 ml (Matin et Soir: 80mg/20mg/ml)";
                                         }
                                        else{
                                            posologie="2cpX2/jour (1 Matin et 1 Soir:300/150mg) + 6cpX2/jour (3 Matin et 3 Soir: 80mg/20mg/ml)";
                                        }
                                }
                                else if(schemaARV[1].compareTo("EFV")==0){
                                            if((poidsR>=10) && (poidsR<=13.9)){
                                                    posologie="4cpX2/jour (2 Matin et 2 Soir:60/30mg) + 7 ml(Matin et Soir:30mg/ml)";
                                            }
                                            else if((poidsR>=14) && (poidsR<=19.9)){
                                                posologie="5cpX2/jour (2.5 Matin et 2.5 Soir:60/30mg) + 3cpX2/jour ( 1.5 Matin et 1.5 Soir: 200mg)";
                                            }
                                             else if((poidsR>=20) && (poidsR<=24.9)){
                                                posologie="6cpX2/jour (3 Matin et 3 Soir:60/30mg) + 3cpX2/jour ( 1.5 Matin et 1.5 Soir: 200mg)";
                                            }
                                             else if(poidsR<10){
                                                posologie="EFV n'est pas recommandé";
                                            }
                                             else{
                                                posologie="2cpX2/jour (1 Matin et 1 Soir:300/150mg) + 4cpX2/jour ( 2 Matin et 2 Soir: 200mg)";
                                            }
                                }
                                else{
                                         if((poidsR>=3) && (poidsR<=5.9)){
                                            posologie="2cpX2/jour (Matin et Soir:60/30mg) + 2cpX2/jour(Matin et Soir: 50mg) ou 5 ml(Matin et Soir: 10mg/ml)";
                                         }
                                        else if((poidsR>=6) && (poidsR<=9.9)){
                                            posologie="3cpX2/jour (1.5 Matin et 1.5 Soir:60/30mg) + 3cpX2/jour(1.5 Matin et 1.5 Soir: 50mg) ou 8 ml(Matin et Soir: 10mg/ml)";
                                         }
                                        else if((poidsR>=10) && (poidsR<=13.9)){
                                            posologie="4cpX2/jour (2 Matin et 2 Soir:60/30mg) + 4cpX2/jour(2 Matin et 2 Soir: 50mg) ou 10 ml(Matin et Soir: 10mg/ml)";
                                         }
                                        else if((poidsR>=14) && (poidsR<=19.9)){
                                            posologie="5cpX2/jour (2.5 Matin et 2.5 Soir:60/30mg) + 5cpX2/jour(2.5 Matin et 2.5 Soir: 50mg)";
                                         }
                                        else if((poidsR>=20) && (poidsR<=24.9)){
                                            posologie="6cpX2/jour (3 Matin et 3 Soir:60/30mg) + 6cpX2/jour(3 Matin et 3 Soir: 50mg)";
                                         }
                                        else{
                                            posologie="2cpX2/jour (1 Matin et 1 Soir:300/150mg) + 2cpX2/jour (1 Matin et 1 Soir: 200mg)";
                                        }
                                }
                         }
                         else{
                                        if(schemaARV[1].compareTo("EFV")==0){
                                            if((poidsR>=10) && (poidsR<=13.9)){
                                                    posologie="1cpX2/jour (Matin ou Soir) + 7 ml(Matin et Soir:30mg/ml)";
                                            }
                                            else if((poidsR>=14) && (poidsR<=19.9)){
                                                posologie="1cpX2/jour (Matin ou Soir) + 3cpX2/jour ( 1.5 Matin et 1.5 Soir: 200mg)";
                                            }
                                             else if((poidsR>=20) && (poidsR<=24.9)){
                                                posologie="1cpX2/jour (Matin ou Soir) + 3cpX2/jour ( 1.5 Matin et 1.5 Soir: 200mg)";
                                            }
                                             else if(poidsR<10){
                                                posologie="EFV n'est pas recommandé";
                                            }
                                             else{
                                                posologie="1cpX2/jour (Matin ou Soir) + 4cpX2/jour ( 2 Matin et 2 Soir: 200mg)";
                                            }
                                }
                                else{
                                         if((poidsR>=3) && (poidsR<=5.9)){
                                            posologie="1cpX2/jour (Matin ou Soir) + 2cpX2/jour(Matin et Soir: 50mg) ou 5 ml(Matin et Soir: 10mg/ml)";
                                         }
                                        else if((poidsR>=6) && (poidsR<=9.9)){
                                            posologie="1cpX2/jour (Matin ou Soir)  + 3cpX2/jour(1.5 Matin et 1.5 Soir: 50mg) ou 8 ml(Matin et Soir: 10mg/ml)";
                                         }
                                        else if((poidsR>=10) && (poidsR<=13.9)){
                                            posologie="1cpX2/jour (Matin ou Soir) + 4cpX2/jour(2 Matin et 2 Soir: 50mg) ou 10 ml(Matin et Soir: 10mg/ml)";
                                         }
                                        else if((poidsR>=14) && (poidsR<=19.9)){
                                            posologie="1cpX2/jour (Matin ou Soir) + 5cpX2/jour(2.5 Matin et 2.5 Soir: 50mg)";
                                         }
                                        else if((poidsR>=20) && (poidsR<=24.9)){
                                            posologie="1cpX2/jour (Matin ou Soir) + 6cpX2/jour(3 Matin et 3 Soir: 50mg)";
                                         }
                                        else{
                                            posologie="1cpX2/jour (Matin ou Soir) + 2cpX2/jour (1 Matin et 1 Soir: 200mg)";
                                        }
                                }
                         }
                         
                }
                else if(schemaARV.length==3){
                    if (schema.compareTo("ABC+3TC+AZT")==0) {
                                     if((poidsR>=3) && (poidsR<=5.9)){
                                            posologie="2cpX2/jour ( 1 Matin et 1 Soir: 60mg+60mg+30mg) ";
                                         }
                                        else if((poidsR>=6) && (poidsR<=9.9)){
                                            posologie="3cpX2/jour ( 1.5 Matin et 1.5 Soir: 60mg+60mg+30mg) ";
                                         }
                                        else if((poidsR>=10) && (poidsR<=13.9)){
                                            posologie="4cpX2/jour ( 2 Matin et 2 Soir: 60mg+60mg+30mg) ";
                                         }
                                        else if((poidsR>=14) && (poidsR<=19.9)){
                                            posologie="5cpX2/jour ( 2.5 Matin et 2.5 Soir: 60mg+60mg+30mg) ";
                                         }
                                        else if((poidsR>=20) && (poidsR<=24.9)){
                                            posologie="6cpX2/jour ( 3 Matin et 3 Soir: 60mg+60mg+30mg) ";
                                         }
                                        else{
                                            posologie="2cpX2/jour ( 1 Matin et 1 Soir: 300mg+300mg+150mg) ";
                                        }
                    }
                    else if(schema.compareTo("ABC+3TC+LPV/r")==0){
                             if((poidsR>=3) && (poidsR<=5.9)){
                                            posologie="2cpX2/jour ( 1 Matin et 1 Soir: 60mg) ou 3ml(Matin et Soir) + 3ml(Matin et Soir:10mg/ml)+ 1 à 1.5 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=6) && (poidsR<=9.9)){
                                            posologie="3cpX2/jour ( 1.5 Matin et 1.5 Soir: 60mg) ou 4ml(Matin et Soir:20mg/ml) + 4ml(Matin et Soir:10mg/ml)+ 1.5 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=10) && (poidsR<=13.9)){
                                            posologie="3cpX2/jour ( 1.5 Matin et 1.5 Soir: 60mg) ou 4ml(Matin et Soir:20mg/ml) + 6ml(Matin et Soir:10mg/ml)+ 2 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=14) && (poidsR<=19.9)){
                                            posologie="5cpX2/jour ( 2.5 Matin et 2.5 Soir: 60mg) + 1cpX2/jour(0.5 Matin et 0.5 Soir:150mg/ml)+ 4cpX2/jour(2 Matin et 2 Soir:100mg/25mg) ou 2.5 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=20) && (poidsR<=24.9)){
                                            posologie="6cpX2/jour ( 3 Matin et 3 Soir: 60mg) + 1.5cpX2/jour(1 Matin et 0.5 Soir: 150mg/ml)+ 4cpX2/jour(2 Matin et 2 Soir:100mg/25mg) ou 3 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else{
                                            posologie="2cpX2/jour ( 1 Matin et 1 Soir: 300mg) + 2cpX2/jour(Matin et Soir:150mg)+6cpX2/jour(3 Matin et 3 Soir : 100mg/25mg) ";
                                        }
                    }
                    else if(schema.compareTo("AZT+3TC+LPV/r")==0){
                             if((poidsR>=3) && (poidsR<=5.9)){
                                            posologie="2cpX2/jour ( 1 Matin et 1 Soir: 60mg) ou 6ml(Matin et Soir) + 3ml(Matin et Soir:10mg/ml)+ 1 à 1.5 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=6) && (poidsR<=9.9)){
                                            posologie="3cpX2/jour ( 1.5 Matin et 1.5 Soir: 60mg) ou 9ml(Matin et Soir:20mg/ml) + 4ml(Matin et Soir:10mg/ml)+ 1.5 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=10) && (poidsR<=13.9)){
                                            posologie="4cpX2/jour ( 2 Matin et 2 Soir: 60mg) ou 12ml(Matin et Soir:20mg/ml) + 6ml(Matin et Soir:10mg/ml)+ 2 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=14) && (poidsR<=19.9)){
                                            posologie="5cpX2/jour ( 2.5 Matin et 2.5 Soir: 60mg) + 1cpX2/jour(0.5 Matin et 0.5 Soir:150mg/ml)+ 4cpX2/jour(2 Matin et 2 Soir:100mg/25mg) ou 2.5 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=20) && (poidsR<=24.9)){
                                            posologie="6cpX2/jour ( 3 Matin et 3 Soir: 60mg) + 1.5cpX2/jour(1 Matin et 0.5 Soir: 150mg/ml)+ 4cpX2/jour(2 Matin et 2 Soir:100mg/25mg) ou 3 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else{
                                            posologie="2cpX2/jour ( 1 Matin et 1 Soir: 300mg) + 2cpX2/jour(Matin et Soir:150mg)+6cpX2/jour(3 Matin et 3 Soir : 100mg/25mg) ";
                                        }
                    }
                     else if(schema.compareTo("TDF+FTC+LPV/r")==0){
                             if((poidsR>=3) && (poidsR<=5.9)){
                                            posologie="1cp/jour ( Matin ou Soir: 300mg) + 1cp/jour (Matin et Soir:200mg)+ 1 à 1.5 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=6) && (poidsR<=9.9)){
                                            posologie="1cp/jour ( Matin ou Soir: 300mg) + 1cp/jour (Matin et Soir:200mg)+ 1.5 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=10) && (poidsR<=13.9)){
                                            posologie="1cp/jour ( Matin ou Soir: 300mg) + 1cp/jour (Matin et Soir:200mg)+ 2 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=14) && (poidsR<=19.9)){
                                            posologie="1cp/jour ( Matin ou Soir: 300mg) + 1cp/jour (Matin et Soir:200mg)+ 4cpX2/jour(2 Matin et 2 Soir:100mg/25mg) ou 2.5 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else if((poidsR>=20) && (poidsR<=24.9)){
                                            posologie="1cp/jour ( Matin ou Soir: 300mg) + 1cp/jour (Matin et Soir:200mg)+ 4cpX2/jour(2 Matin et 2 Soir:100mg/25mg) ou 3 ml(Matin et Soir:80mg/20mg/ml) ";
                                         }
                                        else{
                                            posologie="1cp/jour ( Matin ou Soir: 300mg) + 1cp/jour (Matin et Soir:200mg)+6cpX2/jour(3 Matin et 3 Soir : 100mg/25mg) ";
                                        }
                    }
                    
                }
        }     
        return posologie;
    }
    
    private String posologieAdulte(String schema){
        String posologie = null;
        String schemaARV[] = schema.split("\\+");
        
         //Si le chema est du type TDF/FTC/EFV
        if (schemaARV.length==1) {
                if(schema.contains("AZT")){
                        posologie= "1cpX2/jour (matin et soir) ";
                }else if(schema.contains("DTG")){
                        posologie= "1cp/jour (matin ou soir) ";
                }
                else {
                        posologie= "1cp/jour (soir) ";
                }
        }//Si le schema est du type TDF/FTC+NVP
        else if(schemaARV.length==2)
        {
               if (schemaARV[0].contains("TDF")) {
                        if (schemaARV[1].compareTo("LPV/r")==0) {
                           posologie= "1cp/jour (Matin ou Soir) + 2cpX2/jour (Matin et Soir)  ";
                   }else{
                            posologie= "1cp/jour (Matin ou Soir) + 1cpX2/jour (Matin et Soir)  ";
                        }
                        
               }
               else if(schemaARV[0].contains("AZT")){
                        if (schemaARV[1].compareTo("DTG")==0) {
                                posologie= "1cpX2/jour (Matin et Soir) + 1cpX2/jour (Matin et Soir)  ";
                        }else if(schemaARV[1].compareTo("LPV/r")==0){
                            posologie= "1cpX2/jour (Matin et Soir) + 2cpX2/jour (Matin et Soir)  ";
                        }
                        else{
                            posologie= "1cpX2/jour (Matin et Soir) + 1cp/jour (Soir)  ";
                        }
               }
        }
        return posologie;
    }
    
     @FXML
    public void affichePosologie1(){
         if (prop1.getText().compareTo("")!=0) {
             affichePosologie(prop1c.getText());
         }
    }
    
     @FXML
    public void affichePosologie2(){
        if (prop2.getText().compareTo("")!=0) {
             affichePosologie(prop2c.getText());
         }
    }
    
     @FXML
    public void affichePosologie3(){
        if (prop3.getText().compareTo("")!=0) {
             affichePosologie(prop3c.getText());
         }
    }
     @FXML
    public void affichePosologie4(){
        if (prop4.getText().compareTo("")!=0) {
             affichePosologie(prop4c.getText());
         }
    }
     @FXML
    public void affichePosologie5(){
        if (prop5.getText().compareTo("")!=0) {
             affichePosologie(prop5c.getText());
         }
    }
     @FXML
    public void affichePosologie6(){
        if (prop6.getText().compareTo("")!=0) {
             affichePosologie(prop6c.getText());
         }
    }
     @FXML
    public void affichePosologie7(){
        if (prop7.getText().compareTo("")!=0) {
             affichePosologie(prop7c.getText());
         }
    }
    
    @FXML
    public void donnerListeArv() throws SQLException, ClassNotFoundException{
        ResultSet resultat=listeARV();
        ObservableList<modeleArv> arvData = getARVList(resultat);
        populateARV(arvData);

        arvNameColumn.setCellValueFactory(cellData -> cellData.getValue().NomARVProperty());
        arvNameAbregeColumn.setCellValueFactory(cellData -> cellData.getValue().nomAbregeProperty());
        posologieColumn.setCellValueFactory(cellData -> cellData.getValue().posologieProperty());
        effetIndesirableColumn.setCellValueFactory(cellData -> cellData.getValue().effetIndesirableProperty());
    }
    
    
    private void populateARV (ObservableList<modeleArv> arvData) throws ClassNotFoundException {
        //Set items to the employeeTable
        arvTable.setItems(arvData);
    }
    
    public void changerARV() throws IOException{
        String serologie = (String) serologieVIH.getValue();
        
        if(effetIndes.isSelected()){
                main.pageChangerARV("clinique");
        }
        else if (immunologique.isSelected()) {
            main.pageChangerARV("immunologique");
        }
        else if (virologique.isSelected()) {
            main.pageChangerARV("virologique");
        }
        else{
            System.out.println(" Pas de selection");
        }
    }
    
    public void dialogueInitiationARV(){
        Alert dbox=new Alert(Alert.AlertType.CONFIRMATION);
            
        dbox.setTitle("Initier ARV");
        dbox.setHeaderText("Ce patient ne remplit pas les conditions d'initiation d'ARV!");
        dbox.setContentText("Voulez-vous continuer???");
            
        ButtonType btnYes = new ButtonType("Oui");
        ButtonType btnNo = new ButtonType("Non");
            
        dbox.getButtonTypes().setAll(btnYes,btnNo);
            
        Optional<ButtonType> choice=dbox.showAndWait();
        
        if (choice.get()==btnNo) {
           
        }
        else if (choice.get()==btnYes) {
            
        }
    }
  
     public void setMain(Main main){
        this.main = main;
    }
     
     
        @FXML
    public void pageMO() throws Exception {

         main.pageMaladiesOpportunistes();
    }
    
        @FXML
    public void stadeclinique() throws Exception {
        
   //     checkboxes = new ArrayList<Checkbox>() ;
        
   //      String labels[] = {"Zona"};
         
  //     for (int i = 0; i < labels.length; i++) {
  //    Checkbox Zona = new Checkbox(labels[i]);
  //      checkboxes.add(Zona); //for further use you add it to the list
 //   }
       String stadeclinique = "Stade Clinique 0" , b ="";
       
     CheckBox maladie[] = {Zona, Toxoplasmose, Isosporose, Candidose_buccale_persistante, Lymphadenopathie_generalisee_persistante} ;   
  
     
    for(int i = 0; i < maladie.length; i++) {  
        
      //  System.out.println(maladie[3].getId());
        
        if (maladie[i].isSelected()) {
  
      //      System.out.println(checkboxes.get(i));
            
           String m = (String)maladie[i].getId() ;
       
           ResultSet resultat = stadeClinique(m);
           
            while(resultat.hasNext()) {
                               // System.out.println("Le while");
                                QuerySolution qsol = resultat.nextSolution();
                             //   Resource stadeclinik = qsol.getResource("name") ;
                                Literal n = qsol.getLiteral("nomStade") ;
                                   b = ""+n ;
                             //   System.out.println(b);
                                
                                if(b.compareTo(stadeclinique) > 0 ){
                                
                                        stadeclinique = b ;
                                        
                                        System.out.println(b);
                                }  
                                                   
             }            
        }
        
         sc.setText(stadeclinique);
        
   }     
                        
}
    
            @FXML
    public void diagnostique() throws Exception {
        
        String symptome = (String) signe.getValue(); 
        
//                System.out.println(symptome);

      // panneausigne.getChildren().clear();
//      RadioButton selec=(RadioButton) signeDeGravite.getSelectedToggle();
   //   String txtsigneDeGravite=selec.getText();
  
   
   ScrollPane scr = new ScrollPane() ;
   
  if (symptome.equals("Problèmes respiratoirs") && centre.equals("CSPS") ){     
      
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/PRCSPS.fxml"));
     AnchorPane p = loader.load() ;
     
      AccueilController controller = loader.getController();
      controller.setMain(main);
    // scr.setContent(p);
    // scr.setPrefSize(600, 200);
    // scr.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    //scr.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
     
      panneausigne.setCenter(p);
      
   }

  if (symptome.equals("Problèmes respiratoirs") && centre.equals("CMA") ){     
      
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/PRCMA.fxml"));
     AnchorPane p = loader.load() ;
     
    // scr.setContent(p);
    // scr.setPrefSize(600, 200);
    // scr.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    //scr.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
     
      panneausigne.setCenter(p);
      
   }
  
    if (symptome.equals("Diarrhée chronique") && centre.equals("CSPS") ){     
      
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/DCCSPS.fxml"));
     AnchorPane p = loader.load() ;
        
     panneausigne.setCenter(p);
     
   }
    
     if (symptome.equals("Diarrhée chronique") && centre.equals("CMA") ){     
      
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/DCCMA.fxml"));
     AnchorPane p = loader.load() ;
        
     panneausigne.setCenter(p);
     
   }

  
}
    
            @FXML
    public void algorithme() throws Exception {
      
      RadioButton selec=(RadioButton) signeDeGravite.getSelectedToggle();
   //   String txtsigneDeGravite=selec.getText();
      
         
     if(SGoui.isSelected()){
          
          D1.setDisable(true);
          SGoui1.setDisable(true);
          SGnon1.setDisable(true); 
          D2.setDisable(true);
          SGoui2.setDisable(true);
          SGnon2.setDisable(true);
          D3.setDisable(true);
          SGoui3.setDisable(true);
          SGnon3.setDisable(true);
          D4.setDisable(true);
          SGoui4.setDisable(true);
          SGnon4.setDisable(true);
          D5.setDisable(true);
          positif.setDisable(true);
          negatif.setDisable(true);
          D6.setDisable(true);
          SGoui6.setDisable(true);
          SGnon6.setDisable(true);


      if(centre.equals("CSPS")){
          D7.setDisable(true);
          SGoui7.setDisable(true);
          SGnon7.setDisable(true);
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Veuillez Référez le patient dans un CMA/Clinique");
        dbox.setContentText("Merci !");
            
        ButtonType btnClose = new ButtonType("Fermer");

        Optional<ButtonType> choice=dbox.showAndWait();
        

      }
      
      if(centre.equals("CMA")){    
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
         dbox.setHeaderText("Veuillez réferer le patient dans un CHR/CHU/Polyclinique !");
        dbox.setContentText("Merci !");
            
        ButtonType btnClose = new ButtonType("Fermer");

        Optional<ButtonType> choice=dbox.showAndWait();
        
      }
       
        
        // return ;
      }


      if(SGnon.isSelected()){
          
          D1.setDisable(false);
          SGoui1.setDisable(false);
          SGnon1.setDisable(false);
          D2.setDisable(false);
          SGoui2.setDisable(false);
          SGnon2.setDisable(false);
          D3.setDisable(false);
          SGoui3.setDisable(false);
          SGnon3.setDisable(false);
          D4.setDisable(false);
          SGoui4.setDisable(false);
          SGnon4.setDisable(false);
          
          D5.setDisable(false);
          negatif.setDisable(false);
          positif.setDisable(false);
          
          D6.setDisable(false);
          SGoui6.setDisable(false);
          SGnon6.setDisable(false); 
          
       //   D5.setDisable(true);
       //   SGoui5.setDisable(true);
        //  SGnon5.setDisable(true);


      } 

      if(SGoui1.isSelected() || SGoui2.isSelected() || SGoui3.isSelected() || SGoui4.isSelected()){
          
      RadioButton x1 = (RadioButton) signeDeGravite1.getSelectedToggle();
      RadioButton x2 = (RadioButton) signeDeGravite2.getSelectedToggle();
      RadioButton x3 = (RadioButton) signeDeGravite3.getSelectedToggle();
      RadioButton x4 = (RadioButton) signeDeGravite4.getSelectedToggle();
           
     //  System.out.println(x1.getText().equals("Oui") );
       
       
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("EXPERT BAAR");
        dbox.setContentText("Merci !");
        
         Optional<ButtonType> choice=dbox.showAndWait();
           

          D2.setDisable(true);
          SGoui2.setDisable(true);
          SGnon2.setDisable(true);
          
          D3.setDisable(true);
          SGoui3.setDisable(true);
          SGnon3.setDisable(true);
          
          D4.setDisable(true);
          SGoui4.setDisable(true);
          SGnon4.setDisable(true);
          
          D5.setDisable(false);
          negatif.setDisable(false);
          positif.setDisable(false);
          
          D6.setDisable(true);
          SGoui6.setDisable(true);
          SGnon6.setDisable(true);
          
          D7.setDisable(true);
          SGoui7.setDisable(true);
          SGnon7.setDisable(true);
           
      }
      if(SGnon1.isSelected() == true && SGnon2.isSelected() == true && SGnon3.isSelected() == true && SGnon4.isSelected() == true){
          
          D6.setDisable(false);
          SGoui6.setDisable(false);
          SGnon6.setDisable(false); 
          
           D5.setDisable(true);
          positif.setDisable(true);
          negatif.setDisable(true); 
          
          D8.setDisable(true);
          SGoui8.setDisable(true);
          SGnon8.setDisable(true); 
          
          System.out.println("Prophylaxie !!! ");
          
        }

 }
    
     public void expertbaar() throws Exception {
              
      if(positif.isSelected() && centre.equals("CSPS")){
          
            
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Veuillez effectuer un traitement anti tuberculeux ");
        dbox.setContentText("Merci !");
        
         Optional<ButtonType> choice=dbox.showAndWait();
      
          resultatDiagnostique.setText("Effectuez un traitement anti tuberculeux ");
          
          D6.setDisable(true);
          SGoui6.setDisable(true);
          SGnon6.setDisable(true); 
          D7.setDisable(true);
          SGoui7.setDisable(true);
          SGnon7.setDisable(true); 
      
      }
      
                    
      if(positif.isSelected() && centre.equals("CMA")){
          
            
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Veuillez effectuer un traitement anti tuberculeux ");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait();
      
        message1.setText("Effectuez un traitement anti tuberculeux ");
        message2.setText("");
        
          D8.setDisable(false);
          SGoui8.setDisable(false);
          SGnon8.setDisable(false); 
      
      }
      
      if (negatif.isSelected() && centre.equals("CSPS")){
      
           D6.setDisable(false);
          SGoui6.setDisable(false);
          SGnon6.setDisable(false); 
          
          resultatDiagnostique.setText("");
      
      }
      
      if (negatif.isSelected() && centre.equals("CMA")){
   
          message1.setText("--> Suspicion : PCP, Pneumophatie bactérienne, TB ?");
          message2.setText("--> Traitement specifique");
      
          D8.setDisable(false);
          SGoui8.setDisable(false);
          SGnon8.setDisable(false); 
      }
      

   
     
       //  System.out.println("Juste un clique !");
  }
     
      public void prophilaxieaucotri() throws Exception {
      
                
     if(SGoui6.isSelected()){
         
                     
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Traiter Avec l'Amoxicilline et poursuivre le cotrimoxazole ");
        dbox.setContentText("Merci !");
        
         Optional<ButtonType> choice=dbox.showAndWait();
         
          D7.setDisable(false);
          SGoui7.setDisable(false);
          SGnon7.setDisable(false); 
          
          
          SGnon7.setDisable(true);
         
         
     } 
     if(SGnon6.isSelected() ){
         
                     
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Traiter Avec le cotrimoxazole ");
        dbox.setContentText("Merci !");
        
         Optional<ButtonType> choice=dbox.showAndWait();
         
          D7.setDisable(false);
          SGoui7.setDisable(false);
          SGnon7.setDisable(false); 
      
          SGoui7.setDisable(true);
          
     } 
     
    if(SGoui8.isSelected() && centre.equals("CMA")){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Terminer le traitement");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait();
         
        message3.setText("Terminer le traitement !") ; 
     }
    
    if(SGnon8.isSelected() && centre.equals("CMA")){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Veuillez réferer le patient dans un CHR/CHU/Polyclinique");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait();
         
        message3.setText("Veuillez réferer le patient dans un CHR/CHU/Polyclinique !") ; 
     }
      
}
      
public void apres3jours() throws Exception {
         
           
     if(SGoui6.isSelected() && SGoui7.isSelected() ){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Completez le traitement à 10 jours ");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait();
         
     }
     
     if(SGnon6.isSelected() && SGnon7.isSelected()){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Veuillez Référez le patient dans un CMA/Clinique");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait();
         
     }
     
          if(SGnon7.isSelected() && centre.equals("CSPS")){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Poursuivre le même traitement");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait();
        
          D7.setDisable(true);
          SGoui7.setDisable(true);
          SGnon7.setDisable(true);
         
     }
              
    if(SGoui7.isSelected() && centre.equals("CSPS")){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Antidiarrhéique + Albendazole ou Mébendazole");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait(); 
        
          D.setDisable(true);
          SGoui.setDisable(true);
          SGnon.setDisable(true);
        
     }
    

    
  }

public void apres3joursDC() throws Exception {


    if(SGoui.isSelected() && centre.equals("CSPS")){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Terminer le traitement");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait();
         
     }

    if(SGnon.isSelected() && centre.equals("CSPS")){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Arrêter le traitement et référer au CMA/Clinique");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait();
     }



}


public void rechuteDC() throws Exception {

 if(SGoui6.isSelected() && centre.equals("CSPS")){
     
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Suivre à la demande");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait();

 }
 
  if(SGnon6.isSelected() && centre.equals("CSPS")){
     
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Retraiter avec Cotrimoxazole ou Métronidazole");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait();
 }

}

public void germePathogeneDCCMA() throws Exception {
    
    

          if(SGnon7.isSelected() && centre.equals("CMA")){


         
     }
              
    if(SGoui7.isSelected() && centre.equals("CMA")){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Traitement specifique");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait(); 
        
        
     }

}
     
public void LeucoDCCMA() throws Exception {
    
      if(SGnon6.isSelected() && centre.equals("CMA")){

        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Antidiarréiques + Albendazole ou Mébendazole");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait(); 
        
         
     }
              
    if(SGoui6.isSelected() && centre.equals("CMA")){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Ciprofloxacine 1g/j + Métronidazole 1,5g/j pendant 10 jours");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait(); 
        
        
     }

}

public void apres3joursDCCMA() throws Exception {

     if(SGnon.isSelected() && centre.equals("CMA")){

        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Arrêter le traitement et référer au CHR/CHU/Polyclinique");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait(); 
     }
              
    if(SGoui.isSelected() && centre.equals("CMA")){
         
        Alert dbox=new Alert(Alert.AlertType.INFORMATION);
        dbox.setHeaderText("Terminée le traitement");
        dbox.setContentText("Merci !");
        
        Optional<ButtonType> choice=dbox.showAndWait(); 
        
        
     }
    


}

    public void initialize(URL url, ResourceBundle rb){
      //   myButton.setOnAction(this::handleButtonAction);
      
        //  HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
         // burgerTask2.setRate(-1);
         // hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)-> {
           //     burgerTask2.setRate(burgerTask2.getRate() * -1);
         //       burgerTask2.play() ;
         // });
    }    
  
}
