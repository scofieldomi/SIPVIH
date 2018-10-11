/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipvih.ontologie;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;

/**
 *
 * @author Domi
 */
public class MO {
  
           public static ResultSet stadeClinique(String maladie){
        
        Configuration configura = new Configuration();
        
        Model ontologie=configura.chargeModeleBrute("C:/Ontology/ontologie.owl");
        InfModel infmodele=configura.inference("C:/Ontology/regle.rules", ontologie);
        
         String requete="prefix h: <http://www.medecine.fr/maladies#> "+
                 " SELECT ?nomStade "+
                 " WHERE { h:"+maladie+" a h:Maladie ."+
                 "?stadeclinique h:aPourMaladie h:"+maladie+"."+
                 "?stadeclinique h:nom ?nomStade ."+
                 "}";
       ResultSet result=configura.resultat(requete, infmodele);
       return result;
    }
     
}
