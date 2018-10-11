/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih.view;



import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;
import sipvih.Main;
import static sipvih.ontologie.Patient.getPatientList;
import static sipvih.ontologie.Patient.listePatient;
import static sipvih.ontologie.Patient.insertionPatient;
import static sipvih.ontologie.Patient.getPatient;
import sipvih.ontologie.modelePatient;

/**
 *
 * @author Abdramane
 */
public class PatientController {
    
     public Main main ;
     
    @FXML
    public TextField code;
    
    @FXML
    public Label recup;
    
    @FXML
    public ComboBox serologie;
    
    @FXML
    public DatePicker date_naissance;
    
    @FXML
    public TabPane tabP;

    
    
    @FXML
    private TableView<modelePatient> patientTable;
    @FXML
    private TableColumn<modelePatient, String>  codeColumn;
    @FXML
    private TableColumn<modelePatient, String> serologieColumn;
    @FXML
    private TableColumn<modelePatient, String> dateNaissanceColumn;
    @FXML
    private TableColumn<modelePatient, String> traitementColumn;
   
    @FXML
    private Label codeRee;
    
    public modelePatient pat;
    
    @FXML
    public  void initialize(){
        
       
    }
    
    @FXML
    public void EnregistrerPatient(){
        
        String codeR = (String) code.getText();
        String serologieR = (String) serologie.getValue();
       
        LocalDate isoDate = date_naissance.getValue();
        
        if (codeR.compareTo("")==0) {
                Alert dialog = new Alert(AlertType.INFORMATION);
                dialog.setTitle("Patient");
                dialog.setHeaderText("Veuillez saisir le code du patient!!!");
                dialog.showAndWait();
        }
        else
        {
            String resourcePatient="";
            ResultSet resultat=getPatient(codeR);
            while (resultat.hasNext()) {
             QuerySolution qsol = resultat.nextSolution();
             Resource patient=qsol.getResource("patient");
             resourcePatient=resourcePatient+patient+" ";
            }
            if (resourcePatient.compareTo("")==0) {
                
                 insertionPatient(codeR,isoDate,serologieR);
                 boiteDialogue("Patient Bien enregistré!");
                 code.setText("");
                 SingleSelectionModel<Tab> selectionModel = tabP.getSelectionModel();
                 selectionModel.select(1);
            }
            else{
                boiteDialogue("Ce patient est déjà enregistré!");
            }
           /**/
            
        }
    }

   
     @FXML
    public void initTraitement() throws Exception{
        pat= patientTable.getSelectionModel().getSelectedItem();
        if(pat==null){
            boiteDialogue("Veuillez sélectionner un patient dans le tableau!");
        }
        else{
        String codeR=pat.getCode();
        String dateNaissanceR=pat.getDateNaissance();
        String serologieR=pat.getSerologie();
        String traitementR=pat.getTraitement();
         if (traitementR.compareTo("null")==0) {
             main.pageInitTraitement(codeR,serologieR,dateNaissanceR);
         }
         else{
             boiteDialogue("Ce patient est déjà sous traitement ARV");
         }
        }
        
    }
    
    
    
     @FXML
    public void changerTraitement() throws Exception{
        pat= patientTable.getSelectionModel().getSelectedItem();
        if(pat==null){
            boiteDialogue("Veuillez sélectionner un patient dans le tableau!");
        }
        else{
            String codeR=pat.getCode();
            String dateNaissanceR=pat.getDateNaissance();
            String serologieR=pat.getSerologie();
            String traitementR=pat.getTraitement();
            String causeChangement="";
            if (traitementR.compareTo("null")==0) {
                    boiteDialogue("Ce patient ne suit pas un traitement ARV!");
            }
            else{
                    String[]  choices = {"Effets indésirables", "Echec virologique", "Echec immunologique"};
                    ChoiceDialog<String> cDial = new ChoiceDialog<>(choices[0], choices);
                    cDial.setTitle("A Choice Dialog");
                    cDial.setHeaderText("Pourquoi voulez-vous changer de protocoles ?");
                    cDial.setContentText("Pour :");
                    Optional<String> selection = cDial.showAndWait();
                    if (selection.isPresent()) {
                            causeChangement= selection.get();
                            main.pageChangerTraitementPatient(causeChangement,codeR,serologieR,dateNaissanceR,traitementR);

                    }
            }
            
        
        }
        
    }
   
    
    
     private void boiteDialogue(String info){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Information");
        dialog.setHeaderText(""+info);
        dialog.setContentText(null);
        dialog.showAndWait();
    }
     

    public void retourPagePatient() throws IOException{
        main.affichePagePatient();
    }
     
    @FXML
    public void btnRetour() throws Exception{
       main.MainView();
    }
    
    public void setMain(Main main){
        this.main = main;
    }
    
    @FXML
    public void donnerListePatient() throws SQLException, ClassNotFoundException{
        ResultSet resultat=listePatient();
        ObservableList<modelePatient> patientData = getPatientList(resultat);
        populatePatient(patientData);

        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        serologieColumn.setCellValueFactory(cellData -> cellData.getValue().serologieProperty());
        dateNaissanceColumn.setCellValueFactory(cellData -> cellData.getValue().dateNaissanceProperty());
        traitementColumn.setCellValueFactory(cellData -> cellData.getValue().traitementProperty());
    }
    
    
    private void populatePatient (ObservableList<modelePatient> patientData) throws ClassNotFoundException {
        //Set items to the employeeTable
        patientTable.setItems(patientData);
    }
    
}
