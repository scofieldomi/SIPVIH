/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih.ontologie;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.ReasonerVocabulary;

/**
 *
 * @author Abdramane
 */
public class Configuration {
     
    Model modele;
    Resource config;
    InfModel inferenceModele;
    Model donnees;
    ResultSet result=null;

    public Configuration() {
        modele=ModelFactory.createDefaultModel();
        config=modele.createResource();
    }
    
    //Fonction chargeant les données de l'ontologies dans un modèle
    public Model chargeModeleBrute(String srcOntolgoie)
    {
        donnees=FileManager.get().loadModel(srcOntolgoie);
        return donnees;
    }
    
    //Fonction chargé de faire des inference sur une ontologie à partir des règles definies
    public InfModel inference(String srcRegle,Model ontologie)
    {
        config.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
        config.addProperty(ReasonerVocabulary.PROPruleSet, srcRegle);
        
        Reasoner raisonneur=GenericRuleReasonerFactory.theInstance().create(config);
        
        inferenceModele=ModelFactory.createInfModel(raisonneur,ontologie);
        
        return inferenceModele;
    }
    
    public ResultSet resultat(String requete, InfModel infmodele)
    {
        Query query=QueryFactory.create(requete);
        QueryExecution qexec=QueryExecutionFactory.create(query,infmodele);
        
         try {
            result= qexec.execSelect();
        } catch (Exception e) {
            qexec.close();
        }
         return result;
    }
    
    
}
