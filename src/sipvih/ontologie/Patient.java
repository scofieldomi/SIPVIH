/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih.ontologie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;

/**
 *
 * @author Abdramane
 */
public class Patient {
    
      public static ResultSet listePatient(){
        
        Configuration configura=new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
        String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 "prefix i: <http://www.inria.fr/human/data#> "+
                 " SELECT ?code ?serologie ?dateNaissance ?traitement"+
                 " WHERE { ?patient a h:Patient ."+
                "?patient h:aPourCode ?code ."+
                 "?patient h:aPourSerologie ?vih ."+
                 "?vih h:nom ?serologie ."+
                 "?patient h:date_naissance ?dateNaissance ."+
                 "OPTIONAL { ?patient h:aPourTraitement ?traitementARV."+
                 "?traitementARV h:nom ?traitement.}"+
                 "}"+
                "ORDER BY ?code";
         
       ResultSet result=configura.resultat(requete, infmodele);
       return result;
    }
      
      
       public static ResultSet propositionARVRecommende(String serologie,String patient,String ligne){
        
        Configuration configura=new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
         String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 " SELECT ?traitement ?nom "+
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
                 " SELECT ?traitement ?nom"+
                 " WHERE { ?traitement a h:"+ligne+" ."+
                 " ?traitement h:aPourSerologie h:"+serologie+" ."+
                " ?traitement h:indiqueePour h:"+patient+" ."+
                 " ?traitement h:nom ?nom ."+
                 " ?traitement h:appartient h:alternatif "+
                 "}";
       ResultSet result=configura.resultat(requete, infmodele);
       return result;
    }
     
    
           //Select * from modelearv operation
    public static ObservableList<modelePatient> getPatientList(ResultSet resultat) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<modelePatient> patientList = FXCollections.observableArrayList();

        while (resultat.hasNext()) {
             QuerySolution qsol = resultat.nextSolution();
             Literal codebd = qsol.getLiteral("code"); 
             Literal serologiebd = qsol.getLiteral("serologie");
             Literal dateNaissancebd = qsol.getLiteral("dateNaissance");
             Literal traitementbd = qsol.getLiteral("traitement");
              
           modelePatient patientIns = new modelePatient();
            
            patientIns.setCode(""+codebd);
            patientIns.setSerologie(""+serologiebd);
            patientIns.setDateNaissance(""+dateNaissancebd);
            patientIns.setTraitement(""+traitementbd);
            //Add modelearv to the ObservableList
            patientList.add(patientIns);
        }
        //return empList (ObservableList of Employees)
        return patientList;
    }
    
     public static ResultSet getTraitementPatient(String code){
        
        Configuration configura=new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
         String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 " SELECT ?nom"+
                 " WHERE { ?patient a h:Patient."+
                 " ?patient h:aPourCode '"+code+"' ."+
                 " ?patient h:aPourTraitement ?traitement."+
                 " ?traitement h:nom ?nom ."+
                 "}";
       ResultSet result=configura.resultat(requete, infmodele);
       return result;
    }
    
     public static void insertionPatient(String codeR,LocalDate isoDate,String serologieR){
         OntModel ontologie=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        
                try {
                     FileInputStream fichierInit= new FileInputStream("C:/Ontology/ontologie.owl");
                     ontologie.read(fichierInit,"RDF/XML");
                    } catch (Exception e) {
                  }
        
        
            String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 "INSERT DATA"+
                 "{ h:patient"+codeR+" a h:Patient;"+
                    "h:aPourCode '"+codeR+"';"+
                    "h:date_naissance "+"'"+isoDate+"';"+
                    "h:aPourSerologie h:"+serologieR+
                 "}";
            UpdateRequest query= UpdateFactory.create(requete);
            UpdateAction.execute(query,ontologie);
        
            try {
                FileOutputStream out = new FileOutputStream("C:/Ontology/ontologie.owl");
                ontologie.write(out, "RDF/XML");
                //RDFDataMgr.write(System.out, model, Lang.TTL);
               } catch (FileNotFoundException ex) {
                    Logger.getLogger(org.apache.jena.iri.impl.Main.class.getName()).log(Level.SEVERE,null, ex);
                }
            ontologie.close();
     }
     public static void modificationPatient(String code,String traite,String datenaissance,String serologie){
         
             OntModel ontologie=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        
                try {
                     FileInputStream fichierInit= new FileInputStream("C:/Ontology/ontologie.owl");
                     ontologie.read(fichierInit,"RDF/XML");
                    } catch (Exception e) {
                  }

            String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 "DELETE { ?patient a h:Patient }"+
                 "INSERT "+
                 "{ ?patient a h:Patient;"+
                    "h:aPourTraitement "+traite+";"+
                    "h:aPourCode '"+code+"';"+
                    "h:date_naissance "+"'"+datenaissance+"';"+
                    "h:aPourSerologie h:"+serologie+
                 "}"+
                 "WHERE { ?patient h:aPourCode '"+code+"'}";
            UpdateRequest query= UpdateFactory.create(requete);
            UpdateAction.execute(query,ontologie);
        
            try {
                FileOutputStream out = new FileOutputStream("C:/Ontology/ontologie.owl");
                ontologie.write(out, "RDF/XML");
                //RDFDataMgr.write(System.out, model, Lang.TTL);
               } catch (FileNotFoundException ex) {
                    Logger.getLogger(org.apache.jena.iri.impl.Main.class.getName()).log(Level.SEVERE,null, ex);
                }
            ontologie.close();        
     }
      public static void modificationPatientTraitement(String traiteAnc,String code,String traite,String datenaissance,String serologie){
         
             OntModel ontologie=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        
                try {
                     FileInputStream fichierInit= new FileInputStream("C:/Ontology/ontologie.owl");
                     ontologie.read(fichierInit,"RDF/XML");
                    } catch (Exception e) {
                  }

            String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 "DELETE { ?patient a h:Patient;"+
                            "h:aPourTraitement "+traiteAnc+";"+
                    " }"+
                 "INSERT "+
                 "{ ?patient a h:Patient;"+
                    "h:aPourTraitement "+traite+";"+
                    "h:aPourCode '"+code+"';"+
                    "h:date_naissance "+"'"+datenaissance+"';"+
                    "h:aPourSerologie h:"+serologie+
                 "}"+
                 "WHERE { ?patient h:aPourCode '"+code+"'}";
            UpdateRequest query= UpdateFactory.create(requete);
            UpdateAction.execute(query,ontologie);
        
            try {
                FileOutputStream out = new FileOutputStream("C:/Ontology/ontologie.owl");
                ontologie.write(out, "RDF/XML");
                //RDFDataMgr.write(System.out, model, Lang.TTL);
               } catch (FileNotFoundException ex) {
                    Logger.getLogger(org.apache.jena.iri.impl.Main.class.getName()).log(Level.SEVERE,null, ex);
                }
            ontologie.close();        
     }
      
      
      
   public static void insertionTraitement(String schema){
         OntModel ontologie=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        
                try {
                     FileInputStream fichierInit= new FileInputStream("C:/Ontology/ontologie.owl");
                     ontologie.read(fichierInit,"RDF/XML");
                    } catch (Exception e) {
                  }
        
            String traite="<http://www.medecine.fr/maladies#"+schema+">";
        
            String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 "INSERT DATA"+
                 "{ "+traite+" a h:TraitementARV ;"+
                    "h:nom '"+schema+"';"+
                 "}";
            UpdateRequest query= UpdateFactory.create(requete);
            UpdateAction.execute(query,ontologie);
        
            try {
                FileOutputStream out = new FileOutputStream("C:/Ontology/ontologie.owl");
                ontologie.write(out, "RDF/XML");
                //RDFDataMgr.write(System.out, model, Lang.TTL);
               } catch (FileNotFoundException ex) {
                    Logger.getLogger(org.apache.jena.iri.impl.Main.class.getName()).log(Level.SEVERE,null, ex);
                }
            ontologie.close();
     }
     
      public static ResultSet getPatient(String code){
        
        Configuration configura=new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
         String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 " SELECT ?patient"+
                 " WHERE { ?patient a h:Patient."+
                 " ?patient h:aPourCode '"+code+"' ."+
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
      
         public static ResultSet getRessourceTraitement(String nomTraitement){
        
        Configuration configura=new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
        String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 " SELECT ?traitement"+
                 " WHERE { ?traitement a h:TraitementARV ."+
                  " ?traitement h:nom '"+nomTraitement+"' ."+
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
     
}
