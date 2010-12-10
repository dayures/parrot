package es.ctic.parrot.reader.jena;

import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;

public class JenaOWLReader implements DocumentReader {
    
    private static final Logger logger = Logger.getLogger(JenaOWLReader.class);
	
	/* (non-Javadoc)
	 * @see es.ctic.parrot.reader.DocumentReader#readDocumentableObjects(es.ctic.parrot.reader.Input, es.ctic.parrot.de.DocumentableObjectRegister)
	 */
	public void readDocumentableObjects(Input input, DocumentableObjectRegister register) throws IOException {
        OntModel model = ModelFactory.createOntologyModel();
        String base = null;
        logger.debug("Parsing OWL file");
        model.read(input.openReader(), base, getJenaFormat(input));
        loadOntology(model, register);
        loadOntClasses(model, register);
		loadOntProperties(model, register);
		loadOntIndividuals(model, register);
	}

	private static String getJenaFormat(Input input) {
	    if ("application/rdf+xml".equals(input.getMimeType())) {
	        return "RDF/XML";
	    } else if ("text/turtle".equals(input.getMimeType())) {
	        return "TURTLE";
	    } else {
	        return "RDF/XML"; // default
	    }
    }

    private static void loadOntClasses(OntModel model, DocumentableObjectRegister register) {
		Iterator<OntClass> it= model.listNamedClasses();
		while(it.hasNext()){
			OntClass ontclass=it.next();
			if (isDomainSpecific(ontclass)) {
			    AbstractJenaDocumentableObject oce=new OntologyClassJenaImpl(ontclass);
			    register.registerDocumentableObject(oce);
			}
		}
	}
	
	private static void loadOntProperties(OntModel model, DocumentableObjectRegister register) {
	    Iterator<OntProperty> it = model.listAllOntProperties();
	    while (it.hasNext()) {
	        OntProperty ontProperty = it.next();
	        if (isDomainSpecific(ontProperty)) {
	            OntologyPropertyJenaImpl docObject = new OntologyPropertyJenaImpl(ontProperty);
	            register.registerDocumentableObject(docObject);	        
	        }
	    }
	}
	
	private static void loadOntology(OntModel model, DocumentableObjectRegister register) {
	    Iterator<Ontology> it = model.listOntologies();
	    while (it.hasNext()) {
	    	Ontology ontology = it.next();
            OntologyJenaImpl docObject = new OntologyJenaImpl(ontology);
            register.registerDocumentableObject(docObject);	        
	    }
	}

	private static void loadOntIndividuals(OntModel model, DocumentableObjectRegister register) {
	    Iterator<Individual> it = model.listIndividuals();
	    while (it.hasNext()) {
	    	Individual individual = it.next();
            OntologyIndividualJenaImpl docObject = new OntologyIndividualJenaImpl(individual);
            register.registerDocumentableObject(docObject);	        
	    }
	}
	
	private static boolean isDomainSpecific(OntResource ontResource) {
        return !ontResource.getURI().startsWith(RDFS.getURI()) && !ontResource.getURI().startsWith(RDF.getURI());
    }

}
