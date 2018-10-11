/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih.ontologie;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author Abdramane
 */
public class ARV {
    
     public static ResultSet propositionARVRecommende(String serologie,String patient,String ligne){
        
        Configuration configura=new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
         String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 " SELECT ?nom"+
                 " WHERE { ?traitement a h:"+ligne+" ."+
                 " ?traitement h:aPourSerologie h:"+serologie+" ."+
                 " ?traitement h:indiqueePour h:"+patient+" ."+
                 " ?traitement h:nom ?nom ."+
                 " ?traitement h:appartient h:recommende "+
                 "}";
       ResultSet result=configura.resultat(requete, infmodele);
       return result;
    }
     
     
     
     
     public static ResultSet propositionARVAlternatif(String serologie,String patient,String ligne){
        
        Configuration configura=new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
        String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 " SELECT ?nom"+
                 " WHERE { ?traitement a h:"+ligne+" ."+
                 " ?traitement h:aPourSerologie h:"+serologie+" ."+
                " ?traitement h:indiqueePour h:"+patient+" ."+
                 " ?traitement h:nom ?nom ."+
                 " ?traitement h:appartient h:alternatif "+
                 "}";
       ResultSet result=configura.resultat(requete, infmodele);
       return result;
    }
     
      public static ResultSet getARVeffet(String effet){
        
        Configuration configura=new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
        String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 " SELECT ?nom"+
                 " WHERE { ?arv a h:ARV ."+
                 " ?arv h:aPourEffetIndesirable h:"+effet+" ."+
                 " ?arv h:nomAbrge ?nom "+
                 "}";
       ResultSet result=configura.resultat(requete, infmodele);
       return result;
    }
      
      public static ResultSet getARVSubstitue(String effet){
        
        Configuration configura=new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
        String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 " SELECT ?nom"+
                 " WHERE { ?effet a h:Effet_Indesirable ."+
                 " ?effet h:name '"+effet+"' ."+
                 "?effet h:substituerPar ?arv ."+
                 "?arv h:nomAbrge ?nom"+
                 "}";
       ResultSet result=configura.resultat(requete, infmodele);
       return result;
    }
     
          public static ResultSet listeARV(){
        
        Configuration configura=new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
        String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 "prefix i: <http://www.inria.fr/human/data#> "+
                 " SELECT ?nomARV ?nomAbrege ?posologie ?effetIndesirable"+
                 " WHERE { ?arv a h:ARV ."+
                 "?arv h:nom ?nomARV ."+
                 "?arv h:nomAbrge ?nomAbrege ."+
                "?arv h:posologieARV ?posologie ."+
                "?arv h:effetIndesirable ?effetIndesirable ."+
                 "}"+
                "ORDER BY ?nomARV";
         
       ResultSet result=configura.resultat(requete, infmodele);
       return result;
    }
          
 
          
          //Select * from modelearv operation
    public static ObservableList<modeleArv> getARVList(ResultSet resultat) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<modeleArv> arvList = FXCollections.observableArrayList();

        while (resultat.hasNext()) {
             QuerySolution qsol = resultat.nextSolution();
             Literal nomarvbd = qsol.getLiteral("nomARV"); 
             Literal nomabregebd = qsol.getLiteral("nomAbrege");
             Literal posologiebd = qsol.getLiteral("posologie");
             Literal effetIndesirablebd = qsol.getLiteral("effetIndesirable");
              
           modeleArv arvIns = new modeleArv();
            
            arvIns.setNomARV(""+nomarvbd);
            arvIns.setNomAbrege(""+nomabregebd);
            arvIns.setPosologie(""+posologiebd);
            arvIns.setEffetIndesirable(""+effetIndesirablebd);
            //Add modelearv to the ObservableList
            arvList.add(arvIns);
        }
        //return empList (ObservableList of Employees)
        return arvList;
    }
    
    
    
    public static String getPosologieARV(String arv){
        
        Configuration configura=new Configuration();
        String posologie="";
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
        String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 "prefix i: <http://www.inria.fr/human/data#> "+
                 " SELECT ?posologie"+
                 " WHERE { ?arv a h:ARV ."+
                "?arv h:nomAbrge '"+arv+"' ."+
                "?arv h:posologieARV ?posologie ."+
                 "}";
         
       ResultSet result=configura.resultat(requete, infmodele);
       while (result.hasNext()) {
             QuerySolution qsol = result.nextSolution();
             Literal posologiebd = qsol.getLiteral("posologie");
             posologie=""+posologiebd;
        }
       return posologie;
    }
         
}
