/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih;

import java.io.IOException;
import java.text.ParseException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sipvih.view.AccueilController;
import sipvih.view.ChangerARVController;
import sipvih.view.ChargeViraleController;
import sipvih.view.PatientChangeController;
import sipvih.view.PatientController;
import sipvih.view.PatientSuiteController;
/**
 *
 * @author Domi
 */
public class Main extends Application {
    
    public Stage primaryStage ;
  //  public AnchorPane centreSanteLayout ;
    public BorderPane mainLayout ;
  
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        this.primaryStage = primaryStage ;
        this.primaryStage.setTitle("SIPVIH");
        
       centreSante();
       // MainView();
    }
    
    
    public void MainView() throws Exception {
    
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/MainView.fxml"));
     mainLayout = loader.load() ;
     
     Scene scene = new Scene(mainLayout) ;
     primaryStage.setScene(scene);
     primaryStage.show();   
     
     accueil() ;
    
    }
    
     public void accueil() throws Exception {
    
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/Accueil.fxml"));
     BorderPane accueilLayout = loader.load() ;
     
    // accueilLayout = loader.load() ;
     AccueilController controller = loader.getController();
      controller.setMain(this);
      
     mainLayout.setCenter(accueilLayout) ;
    
    }
    
     
     public void centreSante() throws Exception {
    
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/centreSante.fxml"));
     AnchorPane centreSanteLayout = loader.load() ;

      AccueilController controller = loader.getController();
      controller.setMain(this);
     
     
      Scene scene = new Scene(centreSanteLayout) ;
     
     primaryStage.setScene(scene);
     primaryStage.show();   
    
    }
    
    public void afficheARV() throws IOException{
        
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/pageARV.fxml"));
     AnchorPane pageARVLayout = loader.load() ;
     
      AccueilController controller = loader.getController();
      controller.setMain(this);
     
    // accueilLayout = loader.load() ;
 
     mainLayout.setCenter(pageARVLayout) ;
    }
    
    public void afficheChargeVirale() throws IOException{
        
          FXMLLoader loader = new FXMLLoader() ;
          loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/pageChargeVirale.fxml"));
          AnchorPane pageARVLayout = loader.load() ;
     
          ChargeViraleController controller = loader.getController();
          controller.setMain(this);
     
        // accueilLayout = loader.load() ;
 
           mainLayout.setCenter(pageARVLayout) ;
    }
    
    public void affichePagePatient() throws IOException{
        
          FXMLLoader loader = new FXMLLoader() ;
          loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/patient.fxml"));
          AnchorPane pageARVLayout = loader.load() ;
     
          PatientController controller = loader.getController();
          controller.setMain(this);
     
        // accueilLayout = loader.load() ;
 
           mainLayout.setCenter(pageARVLayout) ;
    }
    public void pageInitTraitement(String codeR,String serologie,String dateNaissance) throws IOException{
        
          FXMLLoader loader = new FXMLLoader() ;
          loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/initTraitement.fxml"));
          AnchorPane pageARVLayout = loader.load() ;
          
          PatientSuiteController controller = loader.getController();
          controller.initialize(codeR,serologie,dateNaissance);
          controller.setMain(this);
     
        // accueilLayout = loader.load() ;
 
           mainLayout.setCenter(pageARVLayout) ;
    }
    
     public void pageChangerTraitementPatient(String typeEchec,String code,String serologie,String dateNaissance,String traitement) throws IOException{
       
            FXMLLoader loader = new FXMLLoader() ;     
            if(typeEchec.compareTo("Echec virologique")==0){
                loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/echecVirologiquePatient.fxml"));
            }
            else{
                loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/echecCliniquePatient.fxml"));
            }
            AnchorPane pageARVLayout = loader.load() ;;
            PatientChangeController controller = loader.getController();
            controller.initialize(code,serologie,dateNaissance,traitement);
            controller.setMain(this);
            mainLayout.setCenter(pageARVLayout) ;
    }
    
    public void pageChangerARV(String typeEchec) throws IOException{
        
        
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/initierARV.fxml"));
     AnchorPane pageMO = loader.load() ;
     
     FXMLLoader loaderP = new FXMLLoader() ;
     if(typeEchec.compareTo("virologique")==0){
         loaderP.setLocation(getClass().getClassLoader().getResource("sipvih/view/echecVirologique.fxml"));
     }
     else{
         loaderP.setLocation(getClass().getClassLoader().getResource("sipvih/view/echecClinique.fxml"));
     }
     Pane pagVir = loaderP.load() ;
      ChangerARVController controller = loaderP.getController();
      controller.setMain(this);
    Pane paneau=new Pane();
    paneau.getChildren().add(pagVir);
    pageMO.getChildren().add(paneau);

     mainLayout.setCenter(pageMO) ;     
     
    }
     public void pageSchemaChangement() throws IOException{
        
        
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/initierARV.fxml"));
     AnchorPane pageMO = loader.load() ;
     
     FXMLLoader loaderP = new FXMLLoader() ;
     loaderP.setLocation(getClass().getClassLoader().getResource("sipvih/view/schemaDeuxièmeLigne.fxml"));
     Pane pagVir = loaderP.load() ;
     
    Pane paneau=new Pane();
    paneau.getChildren().add(pagVir);
    pageMO.getChildren().add(paneau);

     mainLayout.setCenter(pageMO) ;     
     
    }
     
    
    
    
     public void pageMaladiesOpportunistes() throws IOException{
        
        
     FXMLLoader loader = new FXMLLoader() ;
     loader.setLocation(getClass().getClassLoader().getResource("sipvih/view/Maladiesopportunistes.fxml"));
     AnchorPane pageMO = loader.load() ;

     mainLayout.setCenter(pageMO) ;     
     
    }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
